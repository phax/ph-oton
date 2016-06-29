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
package com.helger.photon.basic.app.menu;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.url.IHasSimpleURL;

/**
 * Default implementation of the {@link IMenuItemExternal} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MenuItemExternal extends AbstractMenuObject <MenuItemExternal> implements IMenuItemExternal
{
  private final IHasSimpleURL m_aURLProvider;
  private final IHasDisplayText m_aDisplayText;
  private String m_sTarget;

  public MenuItemExternal (@Nonnull @Nonempty final String sItemID,
                           @Nonnull final IHasSimpleURL aURLProvider,
                           @Nonnull final IHasDisplayText aDisplayText)
  {
    super (sItemID);
    m_aURLProvider = ValueEnforcer.notNull (aURLProvider, "URL");
    m_aDisplayText = ValueEnforcer.notNull (aDisplayText, "DisplayText");
  }

  @Nonnull
  public final EMenuObjectType getMenuObjectType ()
  {
    return EMenuObjectType.EXTERNAL;
  }

  @Nonnull
  public IHasSimpleURL getURLProvider ()
  {
    return m_aURLProvider;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aDisplayLocale)
  {
    return m_aDisplayText.getDisplayText (aDisplayLocale);
  }

  @Nullable
  public String getTarget ()
  {
    return m_sTarget;
  }

  @Nonnull
  public MenuItemExternal setTarget (@Nullable final String sTarget)
  {
    m_sTarget = sTarget;
    return this;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final MenuItemExternal rhs = (MenuItemExternal) o;
    return m_aURLProvider.equals (rhs.m_aURLProvider);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aURLProvider).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("URLProvider", m_aURLProvider)
                            .append ("DisplayText", m_aDisplayText)
                            .append ("Target", m_sTarget)
                            .toString ();
  }
}
