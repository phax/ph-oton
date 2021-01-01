/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.core.interror;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.lang.ICloneable;
import com.helger.smtp.settings.ISMTPSettings;

/**
 * Special email settings for Internal Error handling.
 *
 * @author Philip Helger
 * @since 7.0.4 - was previously an internal class to
 *        {@link InternalErrorHandler}
 */
@NotThreadSafe
public final class InternalErrorEmailSettings implements ICloneable <InternalErrorEmailSettings>
{
  private ISMTPSettings m_aSMTPSettings;
  private IEmailAddress m_aSenderAddress;
  private final ICommonsList <IEmailAddress> m_aReceiverAddresses = new CommonsArrayList <> ();

  public InternalErrorEmailSettings ()
  {}

  public InternalErrorEmailSettings (@Nonnull final InternalErrorEmailSettings aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aSMTPSettings = aOther.m_aSMTPSettings;
    m_aSenderAddress = aOther.m_aSenderAddress;
    m_aReceiverAddresses.setAll (aOther.m_aReceiverAddresses);
  }

  @Nonnull
  public InternalErrorEmailSettings setSMTPSettings (@Nullable final ISMTPSettings aSMTPSettings)
  {
    m_aSMTPSettings = aSMTPSettings;
    return this;
  }

  @Nullable
  public ISMTPSettings getSMTPSettings ()
  {
    return m_aSMTPSettings;
  }

  @Nonnull
  public InternalErrorEmailSettings setSenderAddress (@Nullable final IEmailAddress aSenderAddress)
  {
    m_aSenderAddress = aSenderAddress;
    return this;
  }

  @Nullable
  public IEmailAddress getSenderAddress ()
  {
    return m_aSenderAddress;
  }

  @Nonnull
  public InternalErrorEmailSettings setReceiverAddress (@Nullable final IEmailAddress aReceiverAddress)
  {
    return setReceiverAddresses (aReceiverAddress == null ? null : new CommonsArrayList <> (aReceiverAddress));
  }

  @Nonnull
  public InternalErrorEmailSettings setReceiverAddresses (@Nullable final Iterable <? extends IEmailAddress> aReceiverAddresses)
  {
    if (aReceiverAddresses != null && CollectionHelper.containsAnyNullElement (aReceiverAddresses))
      throw new IllegalArgumentException ("The list of receiver addresses may not contain any null element!");

    m_aReceiverAddresses.setAll (aReceiverAddresses);
    return this;
  }

  @Nonnull
  public InternalErrorEmailSettings setReceiverAddresses (@Nullable final IEmailAddress... aReceiverAddresses)
  {
    if (aReceiverAddresses != null && ArrayHelper.containsAnyNullElement (aReceiverAddresses))
      throw new IllegalArgumentException ("The array of receiver addresses may not contain any null element!");

    m_aReceiverAddresses.setAll (aReceiverAddresses);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IEmailAddress> getAllReceiverAddresses ()
  {
    return m_aReceiverAddresses.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public InternalErrorEmailSettings getClone ()
  {
    return new InternalErrorEmailSettings (this);
  }
}
