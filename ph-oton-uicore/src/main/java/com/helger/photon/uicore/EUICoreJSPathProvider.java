/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

/**
 * Contains default JS paths for this project.
 *
 * @author Philip Helger
 */
public enum EUICoreJSPathProvider implements IJSPathProvider
{
  COOKIE_CONSENT ("uicore/cookieconsent/3.1.1/cookieconsent.js"),
  /** JQuery 3.x */
  JQUERY_3 ("uicore/jquery/jquery-3.6.1.js"),
  JQUERY_HIGHLIGHT ("uicore/jqueryplugins/jquery.highlight.js"),
  JQUERY_HOTKEYS ("uicore/jqueryplugins/jquery.hotkeys.js"),
  JQUERY_MIGRATE ("uicore/jqueryplugins/jquery-migrate-1.4.1.js"),
  JQUERY_MIGRATE_3 ("uicore/jqueryplugins/jquery-migrate-3.4.0.js"),
  JQUERY_MOUSEWHEEL ("uicore/jqueryplugins/jquery.mousewheel.js"),
  JS_COOKIE ("uicore/jqueryplugins/js.cookie.js"),
  JS_STORAGE ("uicore/jqueryplugins/js.storage.js"),
  MOMENT ("uicore/moment/2.29.4/moment.js"),
  MOMENT_WITH_LOCALES ("uicore/moment/2.29.4/moment-with-locales.js"),
  POPPER ("uicore/popper/1.16.1-lts/popper.js"),
  POPPER_UTILS ("uicore/popper/1.16.1-lts/popper-utils.js"),
  SERVERLOG ("ph-oton/serverlog/serverlog.js"),
  /** https://github.com/stacktracejs/stacktrace.js/ */
  STACKTRACE ("uicore/stacktrace/stacktrace.js"),
  @Deprecated
  SWFOBJECT ("uicore/swf/swfobject.js"),
  UICORE_FORM ("ph-oton/uicore-form.js"),
  UICORE_JQUERY ("ph-oton/uicore-jquery.js");

  private final ConstantJSPathProvider m_aPP;

  EUICoreJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.create (sPath);
  }

  EUICoreJSPathProvider (@Nonnull @Nonempty final String sPath, @Nullable final String sConditionalComment)
  {
    m_aPP = ConstantJSPathProvider.createWithConditionalComment (sPath, sConditionalComment);
  }

  @Nonnull
  @Nonempty
  public String getJSItemPath (final boolean bRegular)
  {
    return m_aPP.getJSItemPath (bRegular);
  }

  @Nullable
  public String getConditionalComment ()
  {
    return m_aPP.getConditionalComment ();
  }

  public boolean isBundlable ()
  {
    return m_aPP.isBundlable ();
  }
}
