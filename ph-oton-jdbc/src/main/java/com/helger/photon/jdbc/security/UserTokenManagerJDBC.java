package com.helger.photon.jdbc.security;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTWebDateHelper;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.mutable.MutableLong;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.StringHelper;
import com.helger.commons.wrapper.Wrapper;
import com.helger.db.api.helper.DBValueHelper;
import com.helger.db.jdbc.callback.ConstantPreparedStatementDataProvider;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.executor.DBResultRow;
import com.helger.json.IJsonArray;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.json.serialize.JsonReader;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.object.AccessTokenList;
import com.helger.photon.security.token.object.IAccessTokenList;
import com.helger.photon.security.token.revocation.IRevocationStatus;
import com.helger.photon.security.token.revocation.RevocationStatus;
import com.helger.photon.security.token.user.IUserToken;
import com.helger.photon.security.token.user.IUserTokenManager;
import com.helger.photon.security.token.user.IUserTokenModificationCallback;
import com.helger.photon.security.token.user.UserToken;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;

public class UserTokenManagerJDBC extends AbstractJDBCEnabledSecurityManager implements IUserTokenManager
{
  private final String m_sTableName;
  private final IUserManager m_aUserMgr;
  private final CallbackList <IUserTokenModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserTokenManagerJDBC (@Nonnull final Supplier <? extends DBExecutor> aDBExecSupplier,
                               @Nonnull final Function <String, String> aTableNameCustomizer,
                               @Nonnull final IUserManager aUserMgr)
  {
    super (aDBExecSupplier);
    m_sTableName = aTableNameCustomizer.apply ("secusertoken");
    m_aUserMgr = ValueEnforcer.notNull (aUserMgr, "UserManager");
  }

  /**
   * @return The name of the database table this class is operating on. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public final String getTableName ()
  {
    return m_sTableName;
  }

  @Nonnull
  public final IUserManager getUserManager ()
  {
    return m_aUserMgr;
  }

  @Nonnull
  private static IJsonObject _asJson (@Nonnull final IRevocationStatus aStatus)
  {
    return new JsonObject ().add ("revoked", aStatus.isRevoked ())
                            .addIfNotNull ("userid", aStatus.getRevocationUserID ())
                            .addIfNotNull ("dt", PDTWebDateHelper.getAsStringXSD (aStatus.getRevocationDateTime ()))
                            .addIfNotNull ("reason", aStatus.getRevocationReason ());
  }

  @Nonnull
  private static IJsonObject _asJson (@Nonnull final IAccessToken aAccessToken)
  {
    return new JsonObject ().add ("token", aAccessToken.getTokenString ())
                            .add ("notbefore", PDTWebDateHelper.getAsStringXSD (aAccessToken.getNotBefore ()))
                            .addIfNotNull ("notafter", PDTWebDateHelper.getAsStringXSD (aAccessToken.getNotAfter ()))
                            .add ("revocation", _asJson (aAccessToken.getRevocationStatus ()));
  }

  @Nonnull
  private static String _asString (@Nonnull final IAccessTokenList aTokens)
  {
    return new JsonArray ().addAllMapped (aTokens.getAllAccessTokens (), UserTokenManagerJDBC::_asJson)
                           .getAsJsonString ();
  }

  @Nullable
  private static AccessToken _parseAccessToken (@Nonnull final IJsonObject aJson)
  {
    final IJsonObject aRevocJson = aJson.getAsObject ("revocation");
    final RevocationStatus aRevocationStatus = new RevocationStatus (aRevocJson.getAsBoolean ("revoked"),
                                                                     aRevocJson.getAsString ("userid"),
                                                                     PDTWebDateHelper.getLocalDateTimeFromXSD (aRevocJson.getAsString ("dt")),
                                                                     aRevocJson.getAsString ("reason"));
    return new AccessToken (aJson.getAsString ("token"),
                            PDTWebDateHelper.getLocalDateTimeFromXSD (aJson.getAsString ("notbefore")),
                            PDTWebDateHelper.getLocalDateTimeFromXSD (aJson.getAsString ("notafter")),
                            aRevocationStatus);
  }

  @Nullable
  private static ICommonsList <AccessToken> _parseAccessTokens (@Nullable final String sAccessTokens)
  {
    if (StringHelper.hasNoText (sAccessTokens))
      return null;

    final IJsonArray aJson = JsonReader.builder ().source (sAccessTokens).readAsArray ();
    if (aJson == null || aJson.isEmpty ())
      return null;

    return new CommonsArrayList <> (aJson.iteratorObjects (), UserTokenManagerJDBC::_parseAccessToken);
  }

  @Nonnull
  @ReturnsMutableCopy
  private ICommonsList <IUserToken> _getAllWhere (@Nullable final String sCondition,
                                                  @Nullable final ConstantPreparedStatementDataProvider aDataProvider)
  {
    final ICommonsList <IUserToken> ret = new CommonsArrayList <> ();
    final ICommonsList <DBResultRow> aDBResult;
    String sSQL = "SELECT id, creationdt, creationuserid, lastmoddt, lastmoduserid, deletedt, deleteuserid, attrs," +
                  " accesstokens, userid, description" +
                  " FROM " +
                  m_sTableName;
    if (StringHelper.hasText (sCondition))
    {
      // Condition present
      sSQL += " WHERE " + sCondition;
      if (aDataProvider != null)
        aDBResult = newExecutor ().queryAll (sSQL, aDataProvider);
      else
        aDBResult = newExecutor ().queryAll (sSQL);
    }
    else
    {
      // Simply all
      aDBResult = newExecutor ().queryAll (sSQL);
    }

    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
      {
        final StubObject aStub = new StubObject (aRow.getAsString (0),
                                                 aRow.getAsLocalDateTime (1),
                                                 aRow.getAsString (2),
                                                 aRow.getAsLocalDateTime (3),
                                                 aRow.getAsString (4),
                                                 aRow.getAsLocalDateTime (5),
                                                 aRow.getAsString (6),
                                                 attrsToMap (aRow.getAsString (7)));
        final String sAccessTokens = aRow.getAsString (8);
        final ICommonsList <AccessToken> aAccessTokens = _parseAccessTokens (sAccessTokens);

        final String sUserID = aRow.getAsString (9);
        final IUser aUser = m_aUserMgr.getUserOfID (sUserID);

        final String sDescription = aRow.getAsString (10);

        ret.add (new UserToken (aStub, aAccessTokens, aUser, sDescription));
      }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserToken> getAll ()
  {
    return _getAllWhere (null, null);
  }

  public boolean containsWithID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return false;

    final long nCount = newExecutor ().queryCount ("SELECT COUNT(*) FROM " + m_sTableName + " WHERE id=?",
                                                   new ConstantPreparedStatementDataProvider (sID));
    return nCount > 0;
  }

  public boolean containsAllIDs (@Nullable final Iterable <String> aIDs)
  {
    if (aIDs != null)
    {
      // Make unique, maintain order
      final ICommonsOrderedSet <String> aUniqueIDs = new CommonsLinkedHashSet <> (aIDs);
      final int nIDCount = aUniqueIDs.size ();
      if (nIDCount == 1)
        return containsWithID (aUniqueIDs.getFirst ());

      if (nIDCount > 0)
      {
        final StringBuilder aCond = new StringBuilder (nIDCount * 2);
        for (int i = 0; i < nIDCount; ++i)
        {
          if (i > 0)
            aCond.append (',');
          aCond.append ('?');
        }

        final long nCount = newExecutor ().queryCount ("SELECT COUNT(*) FROM " +
                                                       m_sTableName +
                                                       " WHERE id IN (" +
                                                       aCond.toString () +
                                                       ")",
                                                       new ConstantPreparedStatementDataProvider (aIDs));
        return nCount == nIDCount;
      }
    }
    return true;
  }

  @Nonnull
  @ReturnsMutableObject
  public CallbackList <IUserTokenModificationCallback> userTokenModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  private ESuccess _internalCreateItem (@Nonnull final UserToken aUserToken)
  {
    final DBExecutor aExecutor = newExecutor ();
    return aExecutor.performInTransaction ( () -> {
      // Create new
      final long nCreated = aExecutor.insertOrUpdateOrDelete ("INSERT INTO " +
                                                              m_sTableName +
                                                              " (id, creationdt, creationuserid, lastmoddt, lastmoduserid, deletedt, deleteuserid, attrs," +
                                                              " accesstokens, userid, description)" +
                                                              " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                                              new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (aUserToken.getID (),
                                                                                                                                           IUserToken.USER_TOKEN_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aUserToken.getCreationDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aUserToken.getCreationUserID (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aUserToken.getLastModificationDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aUserToken.getLastModificationUserID (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aUserToken.getDeletionDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aUserToken.getDeletionUserID (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         attrsToString (aUserToken.attrs ()),
                                                                                                         _asString (aUserToken.getAccessTokenList ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aUserToken.getUserID (),
                                                                                                                                           IUser.USER_ID_MAX_LENGTH),
                                                                                                         aUserToken.getDescription ()));
      if (nCreated != 1)
        throw new IllegalStateException ("Failed to create new DB entry (" + nCreated + ")");
    });
  }

  @Nullable
  public UserToken internalCreateUserToken (@Nonnull final UserToken aUserToken, final boolean bRunCallback)
  {
    // Store
    if (_internalCreateItem (aUserToken).isFailure ())
    {
      AuditHelper.onAuditCreateFailure (UserToken.OT,
                                        aUserToken.getID (),
                                        aUserToken.attrs (),
                                        aUserToken.getUserID (),
                                        aUserToken.getDescription (),
                                        "database-error");
      return null;
    }

    AuditHelper.onAuditCreateSuccess (UserToken.OT,
                                      aUserToken.getID (),
                                      aUserToken.attrs (),
                                      aUserToken.getUserID (),
                                      aUserToken.getDescription ());

    if (bRunCallback)
    {
      // Execute callback as the very last action
      m_aCallbacks.forEach (aCB -> aCB.onUserTokenCreated (aUserToken));
    }

    return aUserToken;
  }

  @Nullable
  public UserToken createUserToken (@Nullable final String sTokenString,
                                    @Nullable final Map <String, String> aCustomAttrs,
                                    @Nonnull final IUser aUser,
                                    @Nullable final String sDescription)
  {
    // The AccessToken is created internally
    final UserToken aUserToken = new UserToken (sTokenString, aCustomAttrs, aUser, sDescription);
    return internalCreateUserToken (aUserToken, true);
  }

  @Nonnull
  public EChange updateUserToken (@Nullable final String sUserTokenID,
                                  @Nullable final Map <String, String> aNewCustomAttrs,
                                  @Nullable final String sNewDescription)
  {
    if (StringHelper.hasNoText (sUserTokenID))
      return EChange.UNCHANGED;

    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      // Update existing
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET attrs=?, description=?, lastmoddt=?, lastmoduserid=? WHERE id=?",
                                                              new ConstantPreparedStatementDataProvider (attrsToString (aNewCustomAttrs),
                                                                                                         sNewDescription,
                                                                                                         DBValueHelper.toTimestamp (PDTFactory.getCurrentLocalDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (BusinessObjectHelper.getUserIDOrFallback (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.getTrimmedToLength (sUserTokenID,
                                                                                                                                           IUserToken.USER_TOKEN_ID_MAX_LENGTH)));
      aUpdated.set (nUpdated);
    });

    if (eSuccess.isFailure ())
    {
      // DB error
      AuditHelper.onAuditModifyFailure (UserToken.OT,
                                        "set-all",
                                        sUserTokenID,
                                        aNewCustomAttrs,
                                        sNewDescription,
                                        "database-error");
      return EChange.UNCHANGED;
    }

    if (aUpdated.is0 ())
    {
      // No such user token ID
      AuditHelper.onAuditModifyFailure (UserToken.OT, "set-all", sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    AuditHelper.onAuditModifySuccess (UserToken.OT, "set-all", sUserTokenID, aNewCustomAttrs, sNewDescription);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenUpdated (sUserTokenID));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteUserToken (@Nullable final String sUserTokenID)
  {
    if (StringHelper.hasNoText (sUserTokenID))
      return EChange.UNCHANGED;

    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      // Update existing
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET deletedt=?, deleteuserid=? WHERE id=?",
                                                              new ConstantPreparedStatementDataProvider (DBValueHelper.toTimestamp (PDTFactory.getCurrentLocalDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (BusinessObjectHelper.getUserIDOrFallback (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.getTrimmedToLength (sUserTokenID,
                                                                                                                                           IUserToken.USER_TOKEN_ID_MAX_LENGTH)));
      aUpdated.set (nUpdated);
    });

    if (eSuccess.isFailure ())
    {
      // DB error
      AuditHelper.onAuditDeleteFailure (UserToken.OT, sUserTokenID, "database-error");
      return EChange.UNCHANGED;
    }

    if (aUpdated.is0 ())
    {
      // No such user ID
      AuditHelper.onAuditDeleteFailure (UserToken.OT, sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    AuditHelper.onAuditDeleteSuccess (UserToken.OT, sUserTokenID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenDeleted (sUserTokenID));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange createNewAccessToken (@Nullable final String sUserTokenID,
                                       @Nonnull @Nonempty final String sRevocationUserID,
                                       @Nonnull final LocalDateTime aRevocationDT,
                                       @Nonnull @Nonempty final String sRevocationReason,
                                       @Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sUserTokenID))
      return EChange.UNCHANGED;

    // Read existing access tokens from DB
    final Wrapper <DBResultRow> aDBResult = new Wrapper <> ();
    newExecutor ().querySingle ("SELECT accesstokens FROM " + m_sTableName + " WHERE id=?",
                                new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (sUserTokenID,
                                                                                                             IUserToken.USER_TOKEN_ID_MAX_LENGTH)),
                                aDBResult::set);

    if (aDBResult.isNotSet ())
      return EChange.UNCHANGED;

    final DBResultRow aRow = aDBResult.get ();
    final String sAccessTokens = aRow.getAsString (0);
    final ICommonsList <AccessToken> aAccessTokens = _parseAccessTokens (sAccessTokens);
    final AccessTokenList aAccessTokenList = new AccessTokenList (aAccessTokens);

    // Main actions
    aAccessTokenList.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason);
    final AccessToken aAccessToken = aAccessTokenList.createNewAccessToken (sTokenString);

    // Update in DB
    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      // Update existing
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET accesstokens=?, lastmoddt=?, lastmoduserid=? WHERE id=?",
                                                              new ConstantPreparedStatementDataProvider (_asString (aAccessTokenList),
                                                                                                         DBValueHelper.toTimestamp (PDTFactory.getCurrentLocalDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (BusinessObjectHelper.getUserIDOrFallback (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.getTrimmedToLength (sUserTokenID,
                                                                                                                                           IUserToken.USER_TOKEN_ID_MAX_LENGTH)));
      aUpdated.set (nUpdated);
    });

    if (eSuccess.isFailure ())
    {
      // DB error
      AuditHelper.onAuditModifyFailure (UserToken.OT, "create-new-access-token", sUserTokenID, "database-error");
      return EChange.UNCHANGED;
    }

    if (aUpdated.is0 ())
    {
      // No such user ID - would be unexpected
      AuditHelper.onAuditModifyFailure (UserToken.OT, "create-new-access-token", sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    AuditHelper.onAuditModifySuccess (UserToken.OT,
                                      "create-new-access-token",
                                      sUserTokenID,
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason,
                                      sTokenString);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenCreateAccessToken (sUserTokenID, aAccessToken));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange revokeAccessToken (@Nullable final String sUserTokenID,
                                    @Nonnull @Nonempty final String sRevocationUserID,
                                    @Nonnull final LocalDateTime aRevocationDT,
                                    @Nonnull @Nonempty final String sRevocationReason)
  {
    if (StringHelper.hasNoText (sUserTokenID))
      return EChange.UNCHANGED;

    // Read existing access tokens from DB
    final Wrapper <DBResultRow> aDBResult = new Wrapper <> ();
    newExecutor ().querySingle ("SELECT accesstokens FROM " + m_sTableName + " WHERE id=?",
                                new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (sUserTokenID,
                                                                                                             IUserToken.USER_TOKEN_ID_MAX_LENGTH)),
                                aDBResult::set);

    if (aDBResult.isNotSet ())
      return EChange.UNCHANGED;

    final DBResultRow aRow = aDBResult.get ();
    final String sAccessTokens = aRow.getAsString (0);
    final ICommonsList <AccessToken> aAccessTokens = _parseAccessTokens (sAccessTokens);
    final AccessTokenList aAccessTokenList = new AccessTokenList (aAccessTokens);

    // Main actions
    if (aAccessTokenList.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason).isUnchanged ())
    {
      AuditHelper.onAuditModifyFailure (UserToken.OT, "revoke-access-token", sUserTokenID, "already-revoked");
      return EChange.UNCHANGED;
    }

    // Update in DB
    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      // Update existing
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET accesstokens=?, lastmoddt=?, lastmoduserid=? WHERE id=?",
                                                              new ConstantPreparedStatementDataProvider (_asString (aAccessTokenList),
                                                                                                         DBValueHelper.toTimestamp (PDTFactory.getCurrentLocalDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (BusinessObjectHelper.getUserIDOrFallback (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.getTrimmedToLength (sUserTokenID,
                                                                                                                                           IUserToken.USER_TOKEN_ID_MAX_LENGTH)));
      aUpdated.set (nUpdated);
    });

    if (eSuccess.isFailure ())
    {
      // DB error
      AuditHelper.onAuditModifyFailure (UserToken.OT, "revoke-access-token", sUserTokenID, "database-error");
      return EChange.UNCHANGED;
    }

    if (aUpdated.is0 ())
    {
      // No such user ID - would be unexpected
      AuditHelper.onAuditModifyFailure (UserToken.OT, "revoke-access-token", sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    AuditHelper.onAuditModifySuccess (UserToken.OT,
                                      "revoke-access-token",
                                      sUserTokenID,
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenRevokeAccessToken (sUserTokenID));

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserToken> getAllActiveUserTokens ()
  {
    return _getAllWhere ("deletedt IS NULL", null);
  }

  @Nullable
  public IUserToken getUserTokenOfID (@Nullable final String sUserTokenID)
  {
    if (StringHelper.hasNoText (sUserTokenID))
      return null;

    final Wrapper <DBResultRow> aDBResult = new Wrapper <> ();
    newExecutor ().querySingle ("SELECT creationdt, creationuserid, lastmoddt, lastmoduserid, deletedt, deleteuserid, attrs," +
                                " accesstokens, userid, description" +
                                " FROM " +
                                m_sTableName +
                                " WHERE id=?",
                                new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (sUserTokenID,
                                                                                                             IUserToken.USER_TOKEN_ID_MAX_LENGTH)),
                                aDBResult::set);
    if (aDBResult.isNotSet ())
      return null;

    final DBResultRow aRow = aDBResult.get ();
    final StubObject aStub = new StubObject (sUserTokenID,
                                             aRow.getAsLocalDateTime (0),
                                             aRow.getAsString (1),
                                             aRow.getAsLocalDateTime (2),
                                             aRow.getAsString (3),
                                             aRow.getAsLocalDateTime (4),
                                             aRow.getAsString (5),
                                             attrsToMap (aRow.getAsString (6)));

    final String sAccessTokens = aRow.getAsString (7);
    final ICommonsList <AccessToken> aAccessTokens = _parseAccessTokens (sAccessTokens);

    final String sUserID = aRow.getAsString (8);
    final IUser aUser = m_aUserMgr.getUserOfID (sUserID);

    final String sDescription = aRow.getAsString (9);

    return new UserToken (aStub, aAccessTokens, aUser, sDescription);
  }

  @Nullable
  public IUserToken getUserTokenOfTokenString (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return null;

    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT id, accesstokens" +
                                                                          " FROM " +
                                                                          m_sTableName);
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
      {
        final String sUserTokenID = aRow.getAsString (0);
        final String sAccessTokens = aRow.getAsString (1);
        final ICommonsList <AccessToken> aAccessTokens = _parseAccessTokens (sAccessTokens);
        final AccessTokenList aAccessTokenList = new AccessTokenList (aAccessTokens);

        if (sTokenString.equals (aAccessTokenList.getActiveTokenString ()))
          return getUserTokenOfID (sUserTokenID);
      }

    return null;
  }

  public boolean isAccessTokenUsed (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return false;

    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT accesstokens" +
                                                                          " FROM " +
                                                                          m_sTableName);
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
      {
        final String sAccessTokens = aRow.getAsString (0);
        final ICommonsList <AccessToken> aAccessTokens = _parseAccessTokens (sAccessTokens);
        final AccessTokenList aAccessTokenList = new AccessTokenList (aAccessTokens);

        if (aAccessTokenList.findFirstAccessToken (x -> x.getTokenString ().equals (sTokenString)) != null)
          return true;
      }

    return false;
  }
}
