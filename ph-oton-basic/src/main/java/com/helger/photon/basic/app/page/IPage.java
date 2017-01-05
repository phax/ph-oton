/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.page;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.attr.IHasMutableAttributesAny;
import com.helger.commons.id.IHasID;
import com.helger.commons.text.display.IHasDisplayText;

/**
 * The base interface for a single page of content.
 *
 * @author Philip Helger
 */
public interface IPage extends IHasID <String>, IHasDisplayText, IHasMutableAttributesAny <String>
{
  /**
   * Get the description of the page in the passed locale.
   *
   * @param aContentLocale
   *        The content locale to get the description from. May not be
   *        <code>null</code>.
   * @return <code>null</code> if no description text in the specified locale is
   *         available.
   */
  @Nullable
  String getDescription (@Nonnull Locale aContentLocale);
}
