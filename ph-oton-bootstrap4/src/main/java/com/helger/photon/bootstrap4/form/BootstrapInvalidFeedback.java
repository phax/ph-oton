/**
 * Copyright (C) 2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.form;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.error.IError;
import com.helger.commons.error.list.IErrorList;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;

public class BootstrapInvalidFeedback extends AbstractBootstrapDiv <BootstrapInvalidFeedback>
{
  public BootstrapInvalidFeedback ()
  {
    addClass (CBootstrapCSS.INVALID_FEEDBACK);
  }

  @Nonnull
  public static HCNodeList create (@Nullable final IErrorList aErrorList, @Nonnull final Locale aDisplayLocale)
  {
    final HCNodeList ret = new HCNodeList ();
    if (aErrorList != null)
      for (final IError aError : aErrorList)
        ret.addChild (new BootstrapInvalidFeedback ().addChild (aError.getErrorText (aDisplayLocale)));
    return ret;
  }
}
