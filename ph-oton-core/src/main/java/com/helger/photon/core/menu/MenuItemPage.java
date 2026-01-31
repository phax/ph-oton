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
package com.helger.photon.core.menu;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.photon.core.page.IPage;
import com.helger.text.display.IHasDisplayText;

/**
 * Default implementation of the {@link IMenuItemPage} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MenuItemPage extends AbstractMenuObject <MenuItemPage> implements IMenuItemPage
{
  private final IPage m_aPage;
  private final IHasDisplayText m_aDisplayText;
  private String m_sTarget;

  public MenuItemPage (@NonNull @Nonempty final String sItemID, @NonNull final IPage aPage)
  {
    this (sItemID, aPage, aPage);
  }

  public MenuItemPage (@NonNull @Nonempty final String sItemID, @NonNull final IPage aPage, @NonNull final IHasDisplayText aDisplayText)
  {
    super (sItemID);
    m_aPage = ValueEnforcer.notNull (aPage, "Page");
    m_aDisplayText = ValueEnforcer.notNull (aDisplayText, "DisplayText");
  }

  @NonNull
  public final EMenuObjectType getMenuObjectType ()
  {
    return EMenuObjectType.PAGE;
  }

  @NonNull
  public IPage getPage ()
  {
    return m_aPage;
  }

  @Nullable
  public String getDisplayText (@NonNull final Locale aDisplayLocale)
  {
    return m_aDisplayText.getDisplayText (aDisplayLocale);
  }

  @Nullable
  public String getTarget ()
  {
    return m_sTarget;
  }

  @NonNull
  public MenuItemPage setTarget (@Nullable final String sTarget)
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
    final MenuItemPage rhs = (MenuItemPage) o;
    return m_aPage.getID ().equals (rhs.m_aPage.getID ());
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aPage).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Page", m_aPage)
                            .append ("DisplayText", m_aDisplayText)
                            .append ("Target", m_sTarget)
                            .getToString ();
  }
}
