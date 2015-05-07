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
package com.helger.html.hc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.api.EHCButtonType;
import com.helger.html.js.IJSCodeProvider;

public interface IHCButton <THISTYPE extends IHCButton <THISTYPE>> extends IHCElementWithChildren <THISTYPE>, IHCCanBeDisabled <THISTYPE>, IHCHasName <THISTYPE>
{
  /**
   * @return The value of the button. May be <code>null</code>.
   */
  @Nullable
  String getValue ();

  /**
   * Set the value of the button.
   * 
   * @param sValue
   *        The value to set. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE setValue (@Nullable String sValue);

  /**
   * @return The type of the button (button, submit or reset). Never
   *         <code>null</code>.
   */
  @Nonnull
  EHCButtonType getType ();

  /**
   * Set the type of the button.
   * 
   * @param eType
   *        The new type. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE setType (@Nonnull EHCButtonType eType);

  /**
   * Shortcut for <code>setEventHandler(EJSEvent.ONCLICK, aOnClick)</code>
   * 
   * @param aOnClick
   *        JS event to trigger
   * @return this
   */
  @Nonnull
  THISTYPE setOnClick (@Nullable IJSCodeProvider aOnClick);

  /**
   * Shortcut for <code>setOnClick(JSHtml.windowLocationHref (aURL))</code>
   * 
   * @param aURL
   *        URL to link to
   * @return this
   */
  @Nonnull
  THISTYPE setOnClick (@Nonnull ISimpleURL aURL);

  /**
   * Shortcut for <code>addEventHandler(EJSEvent.ONCLICK, aOnClick)</code>
   * 
   * @param aOnClick
   *        JS event to trigger
   * @return this
   */
  @Nonnull
  THISTYPE addOnClick (@Nullable IJSCodeProvider aOnClick);

  /**
   * Shortcut for <code>addOnClick(JSHtml.windowLocationHref (aURL))</code>
   * 
   * @param aURL
   *        URL to link to
   * @return this
   */
  @Nonnull
  THISTYPE addOnClick (@Nonnull ISimpleURL aURL);
}
