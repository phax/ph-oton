/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.html.hc.ext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.email.IEmailAddress;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.EURLProtocol;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.textlevel.AbstractHCA;

public class HCA_MailTo extends AbstractHCA <HCA_MailTo>
{
  private final String m_sEmailAddress;

  public HCA_MailTo (@Nonnull final String sEmailAddress)
  {
    super (new SimpleURL (EURLProtocol.MAILTO.getProtocol () + sEmailAddress));
    m_sEmailAddress = ValueEnforcer.notNull (sEmailAddress, "Email");
  }

  @Nonnull
  public String getEmail ()
  {
    return m_sEmailAddress;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("email", m_sEmailAddress).getToString ();
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
