/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.audit;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.auth.ICurrentUserIDProvider;

/**
 * The class handles audit actions in a very simple way. All actions are
 * synchronously written to a single large file.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class SimpleAuditManager extends AbstractSimpleDAO implements IAuditor
{
  public static final String ELEMENT_ITEMS = "items";
  public static final String ELEMENT_ITEM = "item";

  private final ICurrentUserIDProvider m_aCurrentUserIDProvider;
  private final AuditItemList m_aItems = new AuditItemList ();

  /**
   * Constructor
   *
   * @param sFilename
   *        The filename to write the audits to. May be <code>null</code> to
   *        indicate an in-memory auditor only.
   * @param aCurrentUserIDProvider
   *        The current user ID provider. May not be <code>null</code>.
   * @throws DAOException
   *         In case reading failed
   */
  public SimpleAuditManager (@Nullable final String sFilename,
                             @Nonnull final ICurrentUserIDProvider aCurrentUserIDProvider) throws DAOException
  {
    super (sFilename);
    m_aCurrentUserIDProvider = ValueEnforcer.notNull (aCurrentUserIDProvider, "UserIDProvider");
    initialRead ();
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    AuditManager.readFromXML (aDoc, aItem -> m_aItems.internalAddItem (aItem));
    // read-only :)
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ITEMS);
    // Is sorted internally!
    for (final IAuditItem aAuditItem : m_aItems.getAllItems ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aAuditItem, ELEMENT_ITEM));
    return aDoc;
  }

  public void createAuditItem (@Nonnull final EAuditActionType eActionType,
                               @Nonnull final ESuccess eSuccess,
                               @Nonnull final String sAction,
                               @Nullable final Object... aArgs)
  {
    final String sFullAction = IAuditActionStringProvider.JSON.createAuditString (sAction, aArgs);
    final AuditItem aAuditItem = new AuditItem (m_aCurrentUserIDProvider.getCurrentUserID (),
                                                eActionType,
                                                eSuccess,
                                                sFullAction);

    m_aRWLock.writeLocked ( () -> {
      m_aItems.internalAddItem (aAuditItem);

      // In write-lock - it should be safe anyway since the caller
      // serializes the calls to this method
      markAsChanged ();
    });
  }

  @Nonnegative
  public int getAuditItemCount ()
  {
    return m_aRWLock.readLocked (m_aItems::getItemCount);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IAuditItem> getLastAuditItems (@Nonnegative final int nMaxItems)
  {
    return m_aRWLock.readLocked ( () -> m_aItems.getLastItems (nMaxItems));
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
                            .toString ();
  }
}
