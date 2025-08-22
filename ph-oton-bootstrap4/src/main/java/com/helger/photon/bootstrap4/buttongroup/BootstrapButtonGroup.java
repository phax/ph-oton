/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.buttongroup;

import com.helger.html.EHTMLRole;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

import jakarta.annotation.Nonnull;

public class BootstrapButtonGroup extends AbstractBootstrapDiv <BootstrapButtonGroup>
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

  public BootstrapButtonGroup (@Nonnull final EBootstrapButtonGroupType eType, @Nonnull final EBootstrapButtonGroupSize eSize)
  {
    addClasses (eType, eSize);
    setRole (EHTMLRole.GROUP);
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
}
