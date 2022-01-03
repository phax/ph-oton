/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.lang.ICloneable;
import com.helger.html.EHTMLVersion;

/**
 * Settings interface that is used to convert HC* nodes to micro nodes, to plain
 * text and for consistency checks.
 *
 * @author Philip Helger
 */
public interface IHCConversionSettings extends IHCConversionSettingsToNode, ICloneable <IHCConversionSettings>
{
  /**
   * Get a clone of this settings, but with a different HTML version.
   *
   * @param eHTMLVersion
   *        The new HTML version to use. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  IHCConversionSettings getClone (@Nonnull EHTMLVersion eHTMLVersion);

  /**
   * Get a clone of this settings, but with a different HTML version. If the
   * passed HTML version equals this HTML version than this is returned
   * unchanged.
   *
   * @param eHTMLVersion
   *        The new HTML version to use. May not be <code>null</code>.
   * @return this or a clone of this.
   */
  @Nonnull
  IHCConversionSettings getCloneIfNecessary (@Nonnull EHTMLVersion eHTMLVersion);
}
