/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.state.ETriState;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCHasName;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;

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

  @Nonnull
  IMPLTYPE setAcceptCharset (@Nullable String sAcceptCharset);

  @Nullable
  ISimpleURL getActionURL ();

  @Nullable
  IHasJSCode getActionJS ();

  @Nonnull
  IMPLTYPE setAction (@Nullable ISimpleURL aAction);

  @Nonnull
  IMPLTYPE setAction (@Nullable IHasJSCodeWithSettings aAction);

  boolean isAutoCompleteOn ();

  boolean isAutoCompleteOff ();

  boolean isAutoCompleteUndefined ();

  @Nonnull
  default IMPLTYPE setAutoComplete (final boolean bAutoComplete)
  {
    return setAutoComplete (ETriState.valueOf (bAutoComplete));
  }

  @Nonnull
  IMPLTYPE setAutoComplete (@Nonnull ETriState eAutoComplete);

  @Nullable
  IMimeType getEncType ();

  /**
   * Make this form a file-upload form.
   *
   * @return this
   */
  @Nonnull
  default IMPLTYPE setEncTypeFileUpload ()
  {
    return setEncType (CMimeType.MULTIPART_FORMDATA);
  }

  /**
   * Set the enctype to text/plain
   *
   * @return this
   */
  @Nonnull
  default IMPLTYPE setEncTypeTextPlain ()
  {
    return setEncType (CMimeType.TEXT_PLAIN);
  }

  @Nonnull
  IMPLTYPE setEncType (@Nullable IMimeType aEncType);

  @Nullable
  EHCFormMethod getMethod ();

  @Nonnull
  IMPLTYPE setMethod (@Nullable EHCFormMethod eMethod);

  boolean isNoValidate ();

  @Nonnull
  IMPLTYPE setNoValidate (boolean bNoValidate);

  @Nullable
  HC_Target getTarget ();

  @Nonnull
  IMPLTYPE setTarget (@Nullable HC_Target aTarget);

  boolean isSubmitPressingEnter ();

  int getSubmitButtonTabIndex ();

  @Nonnull
  default IMPLTYPE setSubmitPressingEnter (final boolean bSubmitPressingEnter)
  {
    return setSubmitPressingEnter (bSubmitPressingEnter, CGlobal.ILLEGAL_UINT);
  }

  @Nonnull
  IMPLTYPE setSubmitPressingEnter (boolean bSubmitPressingEnter, int nSubmitButtonTabIndex);
}
