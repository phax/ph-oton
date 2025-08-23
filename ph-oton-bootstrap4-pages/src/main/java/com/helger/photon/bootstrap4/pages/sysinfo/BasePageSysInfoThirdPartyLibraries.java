/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.sysinfo;

import java.util.Locale;
import java.util.Set;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.name.IHasDisplayName;
import com.helger.base.thirdparty.IThirdPartyModule;
import com.helger.base.thirdparty.ThirdPartyModuleRegistry;
import com.helger.collection.helper.CollectionSort;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.text.IMultilingualText;
import com.helger.text.compare.ComparatorHelper;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;
import com.helger.url.SimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Page with all linked third party libraries
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoThirdPartyLibraries <WPECTYPE extends IWebPageExecutionContext> extends
                                                AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_TPM_HEADER ("Folgende externen Module werden verwendet", "The following external libraries are used"),
    MSG_LICENSED_UNDER (" lizensiert unter ", " licensed under ");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public BasePageSysInfoThirdPartyLibraries (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_THIRDPARTYLIBS.getAsMLT ());
  }

  public BasePageSysInfoThirdPartyLibraries (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoThirdPartyLibraries (@Nonnull @Nonempty final String sID,
                                             @Nonnull final String sName,
                                             @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoThirdPartyLibraries (@Nonnull @Nonempty final String sID,
                                             @Nonnull final IMultilingualText aName,
                                             @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Nonnull
  private static IHCNode _getModuleHCNode (@Nonnull final IThirdPartyModule aModule,
                                           @Nonnull final Locale aDisplayLocale)
  {
    final HCNodeList aNL = new HCNodeList ();

    // Module name
    String sModuleText = aModule.getDisplayName ();
    if (aModule.getVersion () != null)
      sModuleText += ' ' + aModule.getVersion ().getAsString ();

    // Link (if available)
    if (aModule.getWebSiteURL () == null)
      aNL.addChild (sModuleText);
    else
      aNL.addChild (new HCA (new SimpleURL (aModule.getWebSiteURL ())).setTargetBlank ().addChild (sModuleText));
    aNL.addChild (EText.MSG_LICENSED_UNDER.getDisplayText (aDisplayLocale));

    // License text
    String sLicenseText = aModule.getLicense ().getDisplayName ();
    if (aModule.getLicense ().getVersion () != null)
      sLicenseText += ' ' + aModule.getLicense ().getVersion ().getAsString ();

    // Link (if available)
    if (aModule.getLicense ().getURL () == null)
      aNL.addChild (sLicenseText);
    else
      aNL.addChild (new HCA (new SimpleURL (aModule.getLicense ().getURL ())).setTargetBlank ()
                                                                             .addChild (sLicenseText));
    return aNL;
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (h4 (EText.MSG_TPM_HEADER.getDisplayText (aDisplayLocale)));

    // Third party modules
    final Set <IThirdPartyModule> aModules = ThirdPartyModuleRegistry.getInstance ()
                                                                     .getAllRegisteredThirdPartyModules ();
    final HCUL aUL = aNodeList.addAndReturnChild (new HCUL ());

    // Show all required modules, sorted by name
    for (final IThirdPartyModule aModule : CollectionSort.getSorted (aModules,
                                                                     ComparatorHelper.getComparatorCollating (IHasDisplayName::getDisplayName,
                                                                                                              aDisplayLocale)))
      if (!aModule.isOptional ())
        aUL.addItem (_getModuleHCNode (aModule, aDisplayLocale));
  }
}
