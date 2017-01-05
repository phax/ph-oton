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
package com.helger.photon.uicore.page.external;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.xml.microdom.IMicroContainer;

/**
 * Base interface for external pages.
 *
 * @author Philip Helger
 * @since 7.0.2
 */
public interface IWebPageResourceContent
{
  /**
   * @return <code>true</code> if the underlying resource should be read each
   *         time <code>fillContent(IWebPageExecutionContext)</code> is invoked
   *         or <code>false</code> if the resource is read once in the
   *         constructor and re-used over and over.
   */
  boolean isReadEveryTime ();

  /**
   * @param bReadEveryTime
   *        <code>true</code> if the underlying resource should be read each
   *        time <code>fillContent(IWebPageExecutionContext)</code> is invoked
   *        or <code>false</code> if the resource should be read once in the
   *        constructor and re-used over and over.
   * @return this for chaining
   */
  @Nonnull
  IWebPageResourceContent setReadEveryTime (boolean bReadEveryTime);

  /**
   * Re-read the content from the underlying resource. This only makes sense, if
   * {@link #isReadEveryTime()} is <code>false</code>.
   *
   * @see #isReadEveryTime()
   * @see #setReadEveryTime(boolean)
   */
  void updateFromResource ();

  /**
   * @return The additional cleanup consumer that is invoked after reading from
   *         the resource. May be <code>null</code>.
   */
  @Nullable
  Consumer <? super IMicroContainer> getContentCleanser ();
}
