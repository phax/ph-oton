/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
  /** Insert in &lt;head&gt; element (after or before your CSS) for IE &lt; 9 */
  HTML5SHIV ("uicore/html5shiv/3.7.3/html5shiv.js", "if lt IE 9"),
  HTML5SHIV_PRINTSHIV ("uicore/html5shiv/3.7.3/html5shiv-printshiv.js", "if lt IE 9"),
  /** JQuery 3.x */
  JQUERY_3 ("uicore/jquery/jquery-3.5.1.js"),
  JQUERY_HIGHLIGHT ("uicore/jqueryplugins/jquery.highlight.js"),
  JQUERY_HOTKEYS ("uicore/jqueryplugins/jquery.hotkeys.js"),
  JQUERY_MIGRATE ("uicore/jqueryplugins/jquery-migrate-1.4.1.js"),
  JQUERY_MIGRATE_3 ("uicore/jqueryplugins/jquery-migrate-3.0.0.js"),
  JQUERY_MOUSEWHEEL ("uicore/jqueryplugins/jquery.mousewheel.js"),
  JQUERY_PLACEHOLDER ("uicore/jqueryplaceholder/2.1.2/jquery.placeholder.js", "if lt IE 10"),
  JQUERY_PLACEHOLDER_ALL ("uicore/jqueryplaceholder/jquery.placeholder-all.js", "if lt IE 10"),
  JS_COOKIE ("uicore/jqueryplugins/js.cookie.js"),
  JS_STORAGE ("uicore/jqueryplugins/js.storage.js"),
  MOMENT ("uicore/moment/2.22.2/moment-with-locales.js"),
  POPPER ("uicore/popper/1.16.1-lts/popper.js"),
  POPPER_UTILS ("uicore/popper/1.16.1-lts/popper-utils.js"),
  /**
   * Source: https://github.com/scottjehl/Respond - only for IE6-8 so use it
   * only in a conditional comment!
   */
  RESPOND ("uicore/respond/respond.js", "if lt IE 9"),
  SERVERLOG ("ph-oton/serverlog/serverlog.js"),
  /** https://github.com/stacktracejs/stacktrace.js/ */
  STACKTRACE ("uicore/stacktrace/stacktrace.js"),
  SWFOBJECT ("uicore/swf/swfobject.js"),
  UICORE_FORM ("ph-oton/uicore-form.js"),
  UICORE_JQUERY ("ph-oton/uicore-jquery.js");

  private final ConstantJSPathProvider m_aPP;

  private EUICoreJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.create (sPath);
  }

  private EUICoreJSPathProvider (@Nonnull @Nonempty final String sPath, @Nullable final String sConditionalComment)
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
