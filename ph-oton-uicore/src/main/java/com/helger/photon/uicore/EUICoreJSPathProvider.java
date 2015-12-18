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
  FACEBOOK ("uicore/facebook/facebook.js"),
  /** Insert in &lt;head&gt; element (after or before your CSS) for IE &lt; 9 */
  HTML5SHIV_3_7_2 ("uicore/html5shiv/3.7.2/html5shiv.js", "if lt IE 9"),
  /** JQuery 1.x */
  JQUERY_1 ("uicore/jquery/jquery-1.11.3.js"),
  /** JQuery 2.x */
  JQUERY_2 ("uicore/jquery/jquery-2.1.4.js"),
  JQUERY_COOKIE ("uicore/jqueryplugins/jquery.cookie.js"),
  JQUERY_HIGHLIGHT ("uicore/jqueryplugins/jquery.highlight.js"),
  JQUERY_HOTKEYS ("uicore/jqueryplugins/jquery.hotkeys.js"),
  JQUERY_MIGRATE ("uicore/jqueryplugins/jquery-migrate-1.2.1.js"),
  JQUERY_MOUSEWHEEL ("uicore/jqueryplugins/jquery.mousewheel.js"),
  JQUERY_PLACEHOLDER ("uicore/jqueryplaceholder/2.1.2/jquery.placeholder.js", "if lt IE 10"),
  JQUERY_PLACEHOLDER_ALL ("uicore/jqueryplaceholder/jquery.placeholder-all.js", "if lt IE 10"),

  /** Edit placeholder fix for IE &lt; 10 */
  @Deprecated PLACEHOLDER_FIX ("uicore/placeholder/placeholder-fix.js", "if lt IE 10"),
  /**
   * Source: https://github.com/scottjehl/Respond - only for IE6-8 so use it
   * only in a conditional comment!
   */
  RESPOND ("uicore/respond/respond.js", "if lt IE 9"),
  SERVERLOG ("uicore/serverlog/serverlog.js"),
  /** https://github.com/stacktracejs/stacktrace.js/ */
  STACKTRACE ("uicore/stacktrace/stacktrace.js"),
  SWFOBJECT ("uicore/swf/swfobject.js"),
  UICORE_FORM ("uicore/uicore-form.js"),
  UICORE_JQUERY ("uicore/uicore-jquery.js");

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
