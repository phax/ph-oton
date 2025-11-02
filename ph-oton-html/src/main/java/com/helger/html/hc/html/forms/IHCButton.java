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
package com.helger.html.hc.html.forms;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.html.hc.IHCHasName;
import com.helger.html.hc.html.FakeJS;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.IHCHasState;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.mime.CMimeType;
import com.helger.mime.IMimeType;
import com.helger.url.ISimpleURL;

public interface IHCButton <IMPLTYPE extends IHCButton <IMPLTYPE>> extends
                           IHCElementWithChildren <IMPLTYPE>,
                           IHCHasState <IMPLTYPE>,
                           IHCHasName <IMPLTYPE>,
                           IHCHasFocus <IMPLTYPE>
{
  @Nullable
  String getForm ();

  @NonNull
  IMPLTYPE setForm (@Nullable String sForm);

  @Nullable
  ISimpleURL getFormActionURL ();

  @Nullable
  IHasJSCode getFormActionJS ();

  @NonNull
  IMPLTYPE setFormAction (@Nullable ISimpleURL aAction);

  @NonNull
  IMPLTYPE setFormAction (@Nullable IHasJSCodeWithSettings aAction);

  @Nullable
  IMimeType getFormEncType ();

  /**
   * Make this form a file-upload form.
   *
   * @return this
   */
  @NonNull
  default IMPLTYPE setFormEncTypeFileUpload ()
  {
    return setFormEncType (CMimeType.MULTIPART_FORMDATA);
  }

  /**
   * Set the enctype to text/plain
   *
   * @return this
   */
  @NonNull
  default IMPLTYPE setFormEncTypeTextPlain ()
  {
    return setFormEncType (CMimeType.TEXT_PLAIN);
  }

  @NonNull
  IMPLTYPE setFormEncType (@Nullable IMimeType aFormEncType);

  @Nullable
  EHCFormMethod getFormMethod ();

  @NonNull
  IMPLTYPE setFormMethod (@Nullable EHCFormMethod eFormMethod);

  boolean isFormNoValidate ();

  @NonNull
  IMPLTYPE setFormNoValidate (boolean bFormNoValidate);

  @Nullable
  HC_Target getFormTarget ();

  @NonNull
  default IMPLTYPE setFormTargetBlank ()
  {
    return setFormTarget (HC_Target.BLANK);
  }

  @NonNull
  IMPLTYPE setFormTarget (@Nullable HC_Target aFormTarget);

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
  @NonNull
  IMPLTYPE setValue (@Nullable String sValue);

  /**
   * @return The type of the button (button, submit or reset). Never
   *         <code>null</code>.
   */
  @NonNull
  EHCButtonType getType ();

  /**
   * Set the type of the button.
   *
   * @param eType
   *        The new type. May not be <code>null</code>.
   * @return this
   */
  @NonNull
  IMPLTYPE setType (@NonNull EHCButtonType eType);

  /**
   * Shortcut for <code>setEventHandler(EJSEvent.ONCLICK, aOnClick)</code>
   *
   * @param aOnClick
   *        JS event to trigger
   * @return this
   */
  @NonNull
  default IMPLTYPE setOnClick (@Nullable final IHasJSCode aOnClick)
  {
    return setEventHandler (EJSEvent.CLICK, aOnClick);
  }

  /**
   * Shortcut for <code>setOnClick(JSHtml.windowLocationHref (aURL))</code>
   *
   * @param aURL
   *        URL to link to
   * @return this
   */
  @NonNull
  default IMPLTYPE setOnClick (@NonNull final ISimpleURL aURL)
  {
    return setOnClick (FakeJS.windowLocationHref (aURL));
  }

  /**
   * Shortcut for <code>addEventHandler(EJSEvent.ONCLICK, aOnClick)</code>
   *
   * @param aOnClick
   *        JS event to trigger
   * @return this
   */
  @NonNull
  default IMPLTYPE addOnClick (@Nullable final IHasJSCode aOnClick)
  {
    return addEventHandler (EJSEvent.CLICK, aOnClick);
  }

  /**
   * Shortcut for <code>addOnClick(JSHtml.windowLocationHref (aURL))</code>
   *
   * @param aURL
   *        URL to link to
   * @return this
   */
  @NonNull
  default IMPLTYPE addOnClick (@NonNull final ISimpleURL aURL)
  {
    return addOnClick (FakeJS.windowLocationHref (aURL));
  }

}
