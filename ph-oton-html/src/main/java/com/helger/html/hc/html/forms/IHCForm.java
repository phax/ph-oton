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

import com.helger.base.CGlobal;
import com.helger.base.state.ETriState;
import com.helger.html.hc.IHCHasName;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.mime.CMimeType;
import com.helger.mime.IMimeType;
import com.helger.url.ISimpleURL;

/**
 * Interface for FORMs
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCForm <IMPLTYPE extends IHCForm <IMPLTYPE>> extends IHCElementWithChildren <IMPLTYPE>, IHCHasName <IMPLTYPE>
{
  @Nullable
  String getAcceptCharset ();

  @NonNull
  IMPLTYPE setAcceptCharset (@Nullable String sAcceptCharset);

  @Nullable
  ISimpleURL getActionURL ();

  @Nullable
  IHasJSCode getActionJS ();

  @NonNull
  IMPLTYPE setAction (@Nullable ISimpleURL aAction);

  @NonNull
  IMPLTYPE setAction (@Nullable IHasJSCodeWithSettings aAction);

  boolean isAutoCompleteOn ();

  boolean isAutoCompleteOff ();

  boolean isAutoCompleteUndefined ();

  @NonNull
  default IMPLTYPE setAutoComplete (final boolean bAutoComplete)
  {
    return setAutoComplete (ETriState.valueOf (bAutoComplete));
  }

  @NonNull
  IMPLTYPE setAutoComplete (@NonNull ETriState eAutoComplete);

  @Nullable
  IMimeType getEncType ();

  /**
   * Make this form a file-upload form.
   *
   * @return this
   */
  @NonNull
  default IMPLTYPE setEncTypeFileUpload ()
  {
    return setEncType (CMimeType.MULTIPART_FORMDATA);
  }

  /**
   * Set the enctype to text/plain
   *
   * @return this
   */
  @NonNull
  default IMPLTYPE setEncTypeTextPlain ()
  {
    return setEncType (CMimeType.TEXT_PLAIN);
  }

  @NonNull
  IMPLTYPE setEncType (@Nullable IMimeType aEncType);

  @Nullable
  EHCFormMethod getMethod ();

  @NonNull
  IMPLTYPE setMethod (@Nullable EHCFormMethod eMethod);

  boolean isNoValidate ();

  @NonNull
  IMPLTYPE setNoValidate (boolean bNoValidate);

  @Nullable
  HC_Target getTarget ();

  @NonNull
  IMPLTYPE setTarget (@Nullable HC_Target aTarget);

  boolean isSubmitPressingEnter ();

  int getSubmitButtonTabIndex ();

  @NonNull
  default IMPLTYPE setSubmitPressingEnter (final boolean bSubmitPressingEnter)
  {
    return setSubmitPressingEnter (bSubmitPressingEnter, CGlobal.ILLEGAL_UINT);
  }

  @NonNull
  IMPLTYPE setSubmitPressingEnter (boolean bSubmitPressingEnter, int nSubmitButtonTabIndex);
}
