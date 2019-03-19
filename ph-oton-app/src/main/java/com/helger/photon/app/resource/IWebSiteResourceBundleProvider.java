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
package com.helger.photon.app.resource;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;

/**
 * An interface for merging resource bundles.
 *
 * @author Philip Helger
 * @since 8.2.0
 */
public interface IWebSiteResourceBundleProvider
{
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <WebSiteResourceBundleSerialized> getResourceBundles (@Nonnull @Nonempty ICommonsList <WebSiteResourceWithCondition> aList,
                                                                     boolean bRegular);
}
