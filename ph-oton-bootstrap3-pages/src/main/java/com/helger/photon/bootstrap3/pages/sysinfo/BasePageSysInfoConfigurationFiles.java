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
package com.helger.photon.bootstrap3.pages.sysinfo;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hcapi.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.prism.EPrismPlugin;
import com.helger.photon.uictrls.prism.HCPrismJS;

/**
 * This page displays information about the certificate configured in the SMP
 * Server configuration file.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoConfigurationFiles <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
   MSG_DOES_NOT_EXIST ("Die Konfigurationsdatei existiert nicht!", "The configuration file does not exist!"),
   MSG_NO_CONFIG_FILES ("Es sind keine Konfigurationsdateien registriert.", "No configuration files are registered.");

    private final IMultilingualText m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageSysInfoConfigurationFiles (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_CONFIG_FILES.getAsMLT ());
  }

  public BasePageSysInfoConfigurationFiles (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoConfigurationFiles (@Nonnull @Nonempty final String sID,
                                            @Nonnull final String sName,
                                            @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoConfigurationFiles (@Nonnull @Nonempty final String sID,
                                            @Nonnull final IMultilingualText aName,
                                            @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final ConfigurationFileManager aCFM = ConfigurationFileManager.getInstance ();

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();
    for (final ConfigurationFile aCF : aCFM.getAllConfigurationFiles ())
    {
      final HCNodeList aContent = new HCNodeList ();
      String sTabLabelSuffix = "";

      if (aCF.hasDescription ())
        aContent.addChild (createDataGroupHeader (aCF.getDescription ()));

      if (aCF.exists ())
      {
        final String sContent = aCF.getContentAsString ();
        aContent.addChild (new HCPrismJS (aCF.getSyntaxHighlightLanguage ()).addPlugin (EPrismPlugin.LINE_NUMBERS)
                                                                            .addChild (sContent));
      }
      else
      {
        aContent.addChild (new BootstrapErrorBox ().addChild (EText.MSG_DOES_NOT_EXIST.getDisplayText (aDisplayLocale)));
        sTabLabelSuffix = " (!)";
      }

      aTabBox.addTab (aCF.getResource ().getPath () + sTabLabelSuffix, aContent);
    }

    if (aTabBox.hasNoTabs ())
      aNodeList.addChild (new BootstrapInfoBox ().addChild (EText.MSG_NO_CONFIG_FILES.getDisplayText (aDisplayLocale)));
    else
      aNodeList.addChild (aTabBox);
  }
}
