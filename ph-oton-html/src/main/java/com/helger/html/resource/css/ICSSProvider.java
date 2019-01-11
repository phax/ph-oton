/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html.resource.css;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.css.media.ICSSMediaList;

/**
 * Base for external CSS provider with all constraints
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface ICSSProvider extends Serializable
{
  String DEFAULT_CONDITIONAL_COMMENT = null;
  ICSSMediaList DEFAULT_CSS_MEDIA_LIST = null;
  boolean DEFAULT_IS_BUNDLABLE = true;

  /**
   * @return The conditional comment required for this JS item or
   *         <code>null</code> if it applies to all browsers.
   */
  @Nullable
  String getConditionalComment ();

  /**
   * @return The media list for which this CSS item applies. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICSSMediaList getMediaList ();

  /**
   * @return Whether or not this script can be bundled to a big CSS profile. For
   *         some files this is not possible.
   */
  boolean isBundlable ();
}
