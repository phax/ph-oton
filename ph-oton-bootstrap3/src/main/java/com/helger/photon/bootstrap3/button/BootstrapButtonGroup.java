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
package com.helger.photon.bootstrap3.button;

import javax.annotation.Nonnull;

import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.dropdown.BootstrapDropdown;
import com.helger.photon.bootstrap3.dropdown.BootstrapDropdownMenu;

public class BootstrapButtonGroup extends AbstractHCDiv <BootstrapButtonGroup>
{
  private final EBootstrapButtonGroupType m_eType;
  private final EBootstrapButtonGroupSize m_eSize;

  public BootstrapButtonGroup ()
  {
    this (EBootstrapButtonGroupType.DEFAULT, EBootstrapButtonGroupSize.DEFAULT);
  }

  public BootstrapButtonGroup (@Nonnull final EBootstrapButtonGroupType eType)
  {
    this (eType, EBootstrapButtonGroupSize.DEFAULT);
  }

  public BootstrapButtonGroup (@Nonnull final EBootstrapButtonGroupSize eSize)
  {
    this (EBootstrapButtonGroupType.DEFAULT, eSize);
  }

  public BootstrapButtonGroup (@Nonnull final EBootstrapButtonGroupType eType,
                               @Nonnull final EBootstrapButtonGroupSize eSize)
  {
    addClasses (eType.getAllCSSClasses ());
    addClass (eSize);
    m_eType = eType;
    m_eSize = eSize;
  }

  @Nonnull
  public EBootstrapButtonGroupType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public EBootstrapButtonGroupSize getSize ()
  {
    return m_eSize;
  }

  @Nonnull
  public BootstrapDropdownMenu addDropDownMenu ()
  {
    final BootstrapDropdownMenu aDDM = addAndReturnChild (new BootstrapDropdownMenu ());
    // Overwrite default "menu" role
    aDDM.setRole (null);
    return aDDM;
  }

  /**
   * Add a button and convert it to a dropdown menu
   *
   * @param aButton
   *        The button to be added. May not be <code>null</code>.
   * @return The created drop down button
   */
  @Nonnull
  @Deprecated
  public BootstrapDropdownMenu addButtonAsDropDownMenu (@Nonnull final BootstrapButton aButton)
  {
    // Add caret to button
    BootstrapDropdown.makeDropdownToggle (aButton);

    addClass (CBootstrapCSS.DROPDOWN);
    addChild (aButton);
    return addDropDownMenu ();
  }

  /**
   * Add a button and convert it to a dropdown menu
   *
   * @param aButton
   *        The button to be added. May not be <code>null</code>.
   * @return The created drop down button
   */
  @Nonnull
  @Deprecated
  public BootstrapDropdownMenu addButtonAsDropDownMenuWithSeparateCaret (@Nonnull final BootstrapButton aButton)
  {
    addChild (aButton);

    final BootstrapButton aCaret = new BootstrapButton (aButton.getButtonType (), aButton.getButtonSize ());
    BootstrapDropdown.makeDropdownToggle (aCaret);
    addChild (aCaret);

    return addDropDownMenu ();
  }
}
