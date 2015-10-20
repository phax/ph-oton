/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.security.object.accarea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.masterdata.address.IAddress;
import com.helger.masterdata.currency.ECurrency;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.basic.object.accarea.IAccountingArea;
import com.helger.photon.basic.object.accarea.IAccountingAreaResolver;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.security.object.ObjectHelper;

/**
 * Manages all available accounting areas.
 *
 * @author Philip Helger
 */
public final class AccountingAreaManager extends AbstractSimpleDAO implements IAccountingAreaResolver
{
  private static final String ELEMENT_ROOT = "accountingareas";
  private static final String ELEMENT_ITEM = "accountingarea";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AccountingAreaManager.class);

  @GuardedBy ("m_aRWLock")
  private final Map <String, AccountingArea> m_aMap = new HashMap <String, AccountingArea> ();

  private final CallbackList <IAccountingAreaModificationCallback> m_aCallbacks = new CallbackList <IAccountingAreaModificationCallback> ();

  public AccountingAreaManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eAccountingArea : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
      _addAccountingArea (MicroTypeConverter.convertToNative (eAccountingArea, AccountingArea.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ROOT);
    for (final AccountingArea aAccountingArea : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aAccountingArea, ELEMENT_ITEM));
    return aDoc;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAccountingAreaModificationCallback> getAccountingAreaModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @MustBeLocked (ELockType.WRITE)
  private void _addAccountingArea (@Nonnull final AccountingArea aAccountingArea)
  {
    ValueEnforcer.notNull (aAccountingArea, "AccountingArea");

    final String sAccountingAreaID = aAccountingArea.getID ();
    if (m_aMap.containsKey (sAccountingAreaID))
      throw new IllegalArgumentException ("AccountingArea ID '" + sAccountingAreaID + "' is already in use!");
    m_aMap.put (aAccountingArea.getID (), aAccountingArea);
  }

  @Nonnull
  public IAccountingArea createAccountingArea (@Nonnull final IClient aClient,
                                               @Nonnull @Nonempty final String sDisplayName,
                                               @Nullable final String sCompanyType,
                                               @Nullable final String sCompanyVATIN,
                                               @Nullable final String sCompanyNumber,
                                               @Nullable final String sCustomerNumber,
                                               @Nonnull final IAddress aAddress,
                                               @Nonnull final String sTelephone,
                                               @Nullable final String sFax,
                                               @Nullable final String sEmailAddress,
                                               @Nullable final String sWebSite,
                                               @Nonnull final ECurrency eDefaultCurrency,
                                               @Nullable final String sOfficeLocation,
                                               @Nullable final String sCommercialRegistrationNumber,
                                               @Nullable final String sCommercialCourt,
                                               @Nonnull final Locale aDisplayLocale)
  {
    final AccountingArea aAccountingArea = new AccountingArea (aClient,
                                                               sDisplayName,
                                                               sCompanyType,
                                                               sCompanyVATIN,
                                                               sCompanyNumber,
                                                               sCustomerNumber,
                                                               aAddress,
                                                               sTelephone,
                                                               sFax,
                                                               sEmailAddress,
                                                               sWebSite,
                                                               eDefaultCurrency,
                                                               sOfficeLocation,
                                                               sCommercialRegistrationNumber,
                                                               sCommercialCourt,
                                                               aDisplayLocale);

    m_aRWLock.writeLock ().lock ();
    try
    {
      _addAccountingArea (aAccountingArea);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (AccountingArea.OT,
                                      aAccountingArea.getID (),
                                      sDisplayName,
                                      sCompanyType,
                                      sCompanyVATIN,
                                      sCompanyNumber,
                                      sCustomerNumber,
                                      aAddress,
                                      sTelephone,
                                      sFax,
                                      sEmailAddress,
                                      eDefaultCurrency,
                                      sOfficeLocation,
                                      sCommercialRegistrationNumber,
                                      sCommercialCourt);

    // Execute callback as the very last action
    for (final IAccountingAreaModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAccountingAreaCreated (aAccountingArea);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAccountingAreaCreated callback on " + aAccountingArea.toString (), t);
      }

    return aAccountingArea;
  }

  @Nonnull
  public EChange updateAccountingArea (@Nonnull @Nonempty final String sAccountingAreaID,
                                       @Nonnull @Nonempty final String sDisplayName,
                                       @Nullable final String sCompanyType,
                                       @Nullable final String sCompanyVATIN,
                                       @Nullable final String sCompanyNumber,
                                       @Nullable final String sCustomerNumber,
                                       @Nonnull final IAddress aAddress,
                                       @Nonnull final String sTelephone,
                                       @Nullable final String sFax,
                                       @Nullable final String sEmailAddress,
                                       @Nullable final String sWebSite,
                                       @Nonnull final ECurrency eDefaultCurrency,
                                       @Nullable final String sOfficeLocation,
                                       @Nullable final String sCommercialRegistrationNumber,
                                       @Nullable final String sCommercialCourt,
                                       @Nonnull final Locale aDisplayLocale)
  {
    AccountingArea aAccountingArea;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aAccountingArea = m_aMap.get (sAccountingAreaID);
      if (aAccountingArea == null)
      {
        AuditHelper.onAuditModifyFailure (AccountingArea.OT, sAccountingAreaID, "no-such-id");
        return EChange.UNCHANGED;
      }

      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aAccountingArea.setDisplayName (sDisplayName));
      eChange = eChange.or (aAccountingArea.setCompanyType (sCompanyType));
      eChange = eChange.or (aAccountingArea.setCompanyVATIN (sCompanyVATIN));
      eChange = eChange.or (aAccountingArea.setCompanyNumber (sCompanyNumber));
      eChange = eChange.or (aAccountingArea.setCustomerNumber (sCustomerNumber));
      eChange = eChange.or (aAccountingArea.setAddress (aAddress, aDisplayLocale));
      eChange = eChange.or (aAccountingArea.setTelephone (sTelephone));
      eChange = eChange.or (aAccountingArea.setFax (sFax));
      eChange = eChange.or (aAccountingArea.setEmailAddress (sEmailAddress));
      eChange = eChange.or (aAccountingArea.setWebSite (sWebSite));
      eChange = eChange.or (aAccountingArea.setDefaultCurrency (eDefaultCurrency));
      eChange = eChange.or (aAccountingArea.setOfficeLocation (sOfficeLocation));
      eChange = eChange.or (aAccountingArea.setCommercialRegistrationNumber (sCommercialRegistrationNumber));
      eChange = eChange.or (aAccountingArea.setCommercialCourt (sCommercialCourt));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      ObjectHelper.setLastModificationNow (aAccountingArea);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (AccountingArea.OT,
                                      "all",
                                      sAccountingAreaID,
                                      sDisplayName,
                                      sCompanyType,
                                      sCompanyVATIN,
                                      sCompanyNumber,
                                      aAddress,
                                      sTelephone,
                                      sFax,
                                      sEmailAddress,
                                      eDefaultCurrency,
                                      sOfficeLocation,
                                      sCommercialRegistrationNumber,
                                      sCommercialCourt);

    // Execute callback as the very last action
    for (final IAccountingAreaModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAccountingAreaUpdated (aAccountingArea);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAccountingAreaUpdated callback on " + aAccountingArea.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteAccountingArea (@Nullable final String sAccountingAreaID)
  {
    final AccountingArea aDeletedAccountingArea = _getAccountingAreaOfID (sAccountingAreaID);
    if (aDeletedAccountingArea == null)
    {
      AuditHelper.onAuditDeleteFailure (AccountingArea.OT, "no-such-accountingareaid-id", sAccountingAreaID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (ObjectHelper.setDeletionNow (aDeletedAccountingArea).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (AccountingArea.OT, "already-deleted", sAccountingAreaID);
        return EChange.UNCHANGED;
      }
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (AccountingArea.OT, sAccountingAreaID);

    // Execute callback as the very last action
    for (final IAccountingAreaModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAccountingAreaDeleted (aDeletedAccountingArea);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAccountingAreaDeleted callback on " +
                         aDeletedAccountingArea.toString (),
                         t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IAccountingArea> getAllAccountingAreas ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aMap.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IAccountingArea> getAllAccountingAreasOfClient (@Nullable final String sClientID)
  {
    final List <IAccountingArea> ret = new ArrayList <IAccountingArea> ();
    if (StringHelper.hasText (sClientID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IAccountingArea aAccountingArea : m_aMap.values ())
          if (aAccountingArea.getClientID ().equals (sClientID))
            ret.add (aAccountingArea);
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IAccountingArea> getAllAccountingAreasOfClient (@Nullable final IClient aClient)
  {
    final List <IAccountingArea> ret = new ArrayList <IAccountingArea> ();
    if (aClient != null)
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IAccountingArea aAccountingArea : m_aMap.values ())
          if (aAccountingArea.hasSameClient (aClient))
            ret.add (aAccountingArea);
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <String> getAllAccountingAreaIDsOfClient (@Nullable final String sClientID)
  {
    final List <String> ret = new ArrayList <String> ();
    if (StringHelper.hasText (sClientID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IAccountingArea aAccountingArea : m_aMap.values ())
          if (aAccountingArea.getClientID ().equals (sClientID))
            ret.add (aAccountingArea.getID ());
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <String> getAllAccountingAreaIDsOfClient (@Nullable final IClient aClient)
  {
    final List <String> ret = new ArrayList <String> ();
    if (aClient != null)
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IAccountingArea aAccountingArea : m_aMap.values ())
          if (aAccountingArea.hasSameClient (aClient))
            ret.add (aAccountingArea.getID ());
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
  }

  @Nullable
  private AccountingArea _getAccountingAreaOfID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IAccountingArea getAccountingAreaOfID (@Nullable final String sID)
  {
    // Return type
    return _getAccountingAreaOfID (sID);
  }

  @Nullable
  public IAccountingArea getAccountingAreaOfID (@Nullable final String sID, @Nullable final IClient aClient)
  {
    final IAccountingArea aAccountingArea = getAccountingAreaOfID (sID);
    return aAccountingArea != null && aAccountingArea.hasSameClient (aClient) ? aAccountingArea : null;
  }

  public boolean containsAccountingAreaWithID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return false;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.containsKey (sID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean containsAccountingAreaWithID (@Nullable final String sID, @Nullable final IClient aClient)
  {
    return getAccountingAreaOfID (sID, aClient) != null;
  }

  @Nullable
  public IAccountingArea getAccountingAreaOfName (@Nullable final String sName, @Nullable final IClient aClient)
  {
    if (StringHelper.hasText (sName) && aClient != null)
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IAccountingArea aAccountingArea : m_aMap.values ())
          if (aAccountingArea.hasSameClient (aClient) && aAccountingArea.getDisplayName ().equals (sName))
            return aAccountingArea;
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return null;
  }
}
