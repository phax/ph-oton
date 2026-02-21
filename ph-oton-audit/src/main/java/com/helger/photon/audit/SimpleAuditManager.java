/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.audit;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.ELockType;
import com.helger.annotation.concurrent.IsLocked;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.misc.ChangeNextMajorRelease;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.state.EChange;
import com.helger.base.state.ESuccess;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.type.ObjectType;
import com.helger.collection.commons.ICommonsList;
import com.helger.dao.DAOException;
import com.helger.dao.EDAOActionType;
import com.helger.photon.io.dao.AbstractPhotonWALDAO;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

/**
 * The class handles audit actions in a very simple way. All actions are synchronously written to a
 * single large file.
 *
 * @author Philip Helger
 */
@ThreadSafe
@ChangeNextMajorRelease ("Rename to AuditManagerXML")
public class SimpleAuditManager extends AbstractPhotonWALDAO <AuditItem> implements IAuditor
{
  private final ICurrentUserIDProvider m_aCurrentUserIDProvider;
  private final AuditItemList m_aItems = new AuditItemList ();

  /**
   * Constructor
   *
   * @param sFilename
   *        The filename to write the audits to. May be <code>null</code> to indicate an in-memory
   *        auditor only.
   * @param aCurrentUserIDProvider
   *        The current user ID provider. May not be <code>null</code>.
   * @throws DAOException
   *         In case reading failed
   */
  public SimpleAuditManager (@Nullable final String sFilename,
                             @NonNull final ICurrentUserIDProvider aCurrentUserIDProvider) throws DAOException
  {
    super (AuditItem.class, sFilename);
    m_aCurrentUserIDProvider = ValueEnforcer.notNull (aCurrentUserIDProvider, "UserIDProvider");
    initialRead ();
  }

  @Override
  @IsLocked (ELockType.WRITE)
  protected void onRecoveryCreate (@NonNull final AuditItem aItem)
  {
    m_aItems.internalAddItem (aItem);
  }

  @Override
  @IsLocked (ELockType.WRITE)
  protected void onRecoveryUpdate (@NonNull final AuditItem aElement)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  @IsLocked (ELockType.WRITE)
  protected void onRecoveryDelete (@NonNull final AuditItem aElement)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  @NonNull
  protected EChange onRead (@NonNull final IMicroDocument aDoc)
  {
    AuditManager.readFromXML (aDoc, m_aItems::internalAddItem);
    // read-only :)
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.addElement ("root");
    // Is sorted internally!
    for (final IAuditItem aAuditItem : m_aItems.getAllItems ())
      eRoot.addChild (MicroTypeConverter.convertToMicroElement (aAuditItem, AuditManager.ELEMENT_ITEM));
    return aDoc;
  }

  public void createAuditItem (@NonNull final EAuditActionType eActionType,
                               @NonNull final ESuccess eSuccess,
                               @Nullable final ObjectType aActionObjectType,
                               @Nullable final String sAction,
                               @Nullable final Object... aArgs)
  {
    final String sFullAction = IAuditActionStringProvider.JSON.apply (aActionObjectType != null ? aActionObjectType
                                                                                                                   .getName ()
                                                                                                : sAction, aArgs);
    final AuditItem aAuditItem = new AuditItem (m_aCurrentUserIDProvider.getCurrentUserID (),
                                                eActionType,
                                                eSuccess,
                                                sFullAction);

    m_aRWLock.writeLocked ( () -> {
      m_aItems.internalAddItem (aAuditItem);

      markAsChanged (aAuditItem, EDAOActionType.CREATE);
    });
  }

  @Nonnegative
  public int getAuditItemCount ()
  {
    return m_aRWLock.readLockedInt (m_aItems::getItemCount);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IAuditItem> getAllAuditItems ()
  {
    return m_aRWLock.readLockedGet (m_aItems::getAllItems);
  }

  @NonNull
  @ReturnsMutableCopy
  public List <IAuditItem> getLastAuditItems (@Nonnegative final int nMaxItems)
  {
    return m_aRWLock.readLockedGet ( () -> m_aItems.getLastItems (nMaxItems));
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final SimpleAuditManager rhs = (SimpleAuditManager) o;
    return m_aItems.equals (rhs.m_aItems);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aItems).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("CurrentUserIDProvider", m_aCurrentUserIDProvider)
                            .append ("Items", m_aItems)
                            .getToString ();
  }
}
