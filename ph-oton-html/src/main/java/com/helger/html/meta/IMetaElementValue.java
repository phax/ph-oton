/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.meta;

import java.io.Serializable;
import java.util.Locale;

import com.helger.base.name.IHasName;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents the value of a single meta element
 *
 * @author Philip Helger
 */
public interface IMetaElementValue extends IHasName, Serializable
{
  /**
   * @return The type of the meta element. Never <code>null</code>.
   */
  @Nonnull
  EMetaElementType getType ();

  /**
   * @return The content of the meta tag. May not be <code>null</code>.
   */
  @Nonnull
  String getContent ();

  /**
   * @return The specific locale of the meta tag. May be <code>null</code>.
   */
  @Nullable
  Locale getContentLocale ();
}
