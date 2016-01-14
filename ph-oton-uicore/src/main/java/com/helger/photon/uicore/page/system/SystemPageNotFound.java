/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page.system;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.uicore.page.AbstractWebPage;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Default page showing a very rudimentary "page not found"
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class SystemPageNotFound <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPage <WPECTYPE>
{
  @Translatable
  protected static enum ETextBase implements IHasDisplayText
  {
    PAGENAME ("Seite nicht gefunden", "Page not found"),
    MESSAGE ("Die von Ihnen gesuchte Seite existiert leider nicht!", "The page you are looking for does not exist!");

    private final IMultilingualText m_aTP;

    private ETextBase (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public static final String PAGEID_SYSTEM_NOTFOUND = "system.notfound";
  private static final Logger s_aLogger = LoggerFactory.getLogger (SystemPageNotFound.class);

  public SystemPageNotFound ()
  {
    super (PAGEID_SYSTEM_NOTFOUND, ETextBase.PAGENAME.m_aTP);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    s_aLogger.info ("PAGE NOT FOUND " + aWPEC.getRequestScope ().getURL ());
    aNodeList.addChild (new HCH1 ().addChild (ETextBase.MESSAGE.getDisplayText (aDisplayLocale)));
  }
}
