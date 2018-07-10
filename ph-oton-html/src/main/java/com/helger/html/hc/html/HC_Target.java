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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * Represents the value of the "target" attribute of an HTML &lt;a&gt; element
 *
 * @author Philip Helger
 */
@Immutable
public class HC_Target implements IHCHasHTMLAttributeValue
{
  // See http://de.selfhtml.org/html/verweise/definieren.htm#zielfenster
  /** New window */
  public static final HC_Target BLANK = new HC_Target ("_blank");
  /** This window */
  public static final HC_Target SELF = new HC_Target ("_self");
  /** Parent frame */
  public static final HC_Target PARENT = new HC_Target ("_parent");
  /** Out of frames */
  public static final HC_Target TOP = new HC_Target ("_top");

  private final String m_sName;

  public HC_Target (@Nonnull @Nonempty final String sName)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
  }

  @Nonnull
  @Nonempty
  public final String getName ()
  {
    return m_sName;
  }

  @Nonnull
  @Nonempty
  public final String getAttrValue ()
  {
    return getName ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (super.toString ()).append ("name", m_sName).getToString ();
  }

  /**
   * Try to find one of the default targets by name. The name comparison is
   * performed case insensitive.
   *
   * @param sName
   *        The name to check. May not be <code>null</code>.
   * @param aDefault
   *        The default value to be returned in case the name was never found.
   *        May be <code>null</code>.
   * @return The constant link target representing the name or the default
   *         value. May be <code>null</code> if the passed default value is
   *         <code>null</code> and the name was not found.
   */
  @Nullable
  public static HC_Target getFromName (@Nonnull final String sName, @Nullable final HC_Target aDefault)
  {
    if (BLANK.getAttrValue ().equalsIgnoreCase (sName))
      return BLANK;
    if (SELF.getAttrValue ().equalsIgnoreCase (sName))
      return SELF;
    if (PARENT.getAttrValue ().equalsIgnoreCase (sName))
      return PARENT;
    if (TOP.getAttrValue ().equalsIgnoreCase (sName))
      return TOP;
    return aDefault;
  }
}
