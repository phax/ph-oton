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
package com.helger.photon.core.menu;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link IMenuItemRedirectToPage} interface.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
@NotThreadSafe
public class MenuItemRedirectToPage extends AbstractMenuObject <MenuItemRedirectToPage> implements IMenuItemRedirectToPage
{
  private final IMenuItemPage m_aMenuItemPage;

  public MenuItemRedirectToPage (@Nonnull @Nonempty final String sItemID, @Nonnull final IMenuItemPage aMenuItemPage)
  {
    super (sItemID);
    m_aMenuItemPage = ValueEnforcer.notNull (aMenuItemPage, "MenuItemPage");
  }

  @Nonnull
  public final EMenuObjectType getMenuObjectType ()
  {
    return EMenuObjectType.REDIRECT_TO_PAGE;
  }

  @Nonnull
  public IMenuItemPage getTargetMenuItemPage ()
  {
    return m_aMenuItemPage;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final MenuItemRedirectToPage rhs = (MenuItemRedirectToPage) o;
    return m_aMenuItemPage.getID ().equals (rhs.m_aMenuItemPage.getID ());
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aMenuItemPage).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("MenuItemPage", m_aMenuItemPage).getToString ();
  }
}
