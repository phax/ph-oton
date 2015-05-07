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
package com.helger.html.hc.htmlext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.EURLProtocol;
import com.helger.commons.url.ReadonlySimpleURL;
import com.helger.html.hc.html.AbstractHCA;

public class HCA_MailTo extends AbstractHCA <HCA_MailTo>
{
  private final String m_sEmail;

  public HCA_MailTo (@Nonnull final String sEmail)
  {
    super (new ReadonlySimpleURL (EURLProtocol.MAILTO.getProtocol () + sEmail));
    m_sEmail = ValueEnforcer.notNull (sEmail, "Email");
  }

  @Nonnull
  public String getEmail ()
  {
    return m_sEmail;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("email", m_sEmail).toString ();
  }

  @Nullable
  public static HCA_MailTo createLinkedEmail (@Nullable final String sAddress)
  {
    return createLinkedEmail (sAddress, sAddress);
  }

  @Nullable
  public static HCA_MailTo createLinkedEmail (@Nullable final IEmailAddress aAddress)
  {
    return aAddress == null ? null : createLinkedEmail (aAddress.getAddress (), aAddress.getDisplayName ());
  }

  @Nullable
  public static HCA_MailTo createLinkedEmail (@Nullable final String sAddress, @Nullable final String sPersonal)
  {
    if (StringHelper.hasNoText (sAddress))
      return null;

    final HCA_MailTo ret = new HCA_MailTo (sAddress);
    ret.addChild (StringHelper.getNotNull (sPersonal, sAddress));
    return ret;
  }
}
