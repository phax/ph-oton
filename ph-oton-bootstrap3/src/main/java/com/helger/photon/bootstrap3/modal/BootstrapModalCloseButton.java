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
package com.helger.photon.bootstrap3.modal;

import javax.annotation.Nonnull;

import com.helger.photon.bootstrap3.button.BootstrapButton;
import com.helger.photon.bootstrap3.button.EBootstrapButtonSize;
import com.helger.photon.bootstrap3.button.EBootstrapButtonType;

/**
 * A special button that closes the current modal. This button may only be used
 * within a modal dialog!
 * 
 * @author Philip Helger
 */
public class BootstrapModalCloseButton extends BootstrapButton
{
  private void _init ()
  {
    setDataAttr ("dismiss", "modal");
  }

  public BootstrapModalCloseButton ()
  {
    _init ();
  }

  public BootstrapModalCloseButton (@Nonnull final EBootstrapButtonType eButtonType)
  {
    super (eButtonType);
    _init ();
  }

  public BootstrapModalCloseButton (@Nonnull final EBootstrapButtonSize eButtonSize)
  {
    super (eButtonSize);
    _init ();
  }

  public BootstrapModalCloseButton (@Nonnull final EBootstrapButtonType eButtonType,
                                    @Nonnull final EBootstrapButtonSize eButtonSize)
  {
    super (eButtonType, eButtonSize);
    _init ();
  }
}
