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
package com.helger.photon.bootstrap3.pages.appinfo;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.photon.core.resource.WebSiteResourceBundleManager;
import com.helger.photon.core.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.core.resource.WebSiteResourceCache;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

/**
 * Show web site resource bundles.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageAppInfoWebSiteResourceBundles <WPECTYPE extends IWebPageExecutionContext>
                                                   extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_CACHE_ACTIVE ("Cache aktiv: ", "Cache enabled: "),
    MSG_RESBUNDLE_SERVLET ("ResourceBundleServlet registriert: ", "ResourceBundleServlet registered: "),
    MSG_RESBUNDLE_ACTIVE ("ResourceBundleServlet aktiviert: ", "ResourceBundleServlet active: "),
    MSG_ID ("ID", "ID"),
    MSG_DATE ("Datum", "Date"),
    MSG_RESOURCES ("Resourcen", "Resources"),
    MSG_COND_COMMENT ("Cond Comment", "Cond comment"),
    MSG_CSS_MEDIA ("CSS Medien", "CSS media");

    @Nonnull
    private final IMultilingualText m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  private final WebSiteResourceBundleManager m_aResBundleMgr;

  public BasePageAppInfoWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                @Nonnull final WebSiteResourceBundleManager aResBundleMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_APPINFO_WEBRESBUNDLE.getAsMLT ());
    m_aResBundleMgr = ValueEnforcer.notNull (aResBundleMgr, "ResBundleMgr");
  }

  public BasePageAppInfoWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                @Nonnull final String sName,
                                                @Nonnull final WebSiteResourceBundleManager aResBundleMgr)
  {
    super (sID, sName);
    m_aResBundleMgr = ValueEnforcer.notNull (aResBundleMgr, "ResBundleMgr");
  }

  public BasePageAppInfoWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                @Nonnull final String sName,
                                                @Nullable final String sDescription,
                                                @Nonnull final WebSiteResourceBundleManager aResBundleMgr)
  {
    super (sID, sName, sDescription);
    m_aResBundleMgr = ValueEnforcer.notNull (aResBundleMgr, "ResBundleMgr");
  }

  public BasePageAppInfoWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                @Nonnull final IMultilingualText aName,
                                                @Nullable final IMultilingualText aDescription,
                                                @Nonnull final WebSiteResourceBundleManager aResBundleMgr)
  {
    super (sID, aName, aDescription);
    m_aResBundleMgr = ValueEnforcer.notNull (aResBundleMgr, "ResBundleMgr");
  }

  @Nonnull
  protected final WebSiteResourceBundleManager getResBundleMgr ()
  {
    return m_aResBundleMgr;
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    aNodeList.addChild (new HCDiv ().addChild (EText.MSG_CACHE_ACTIVE.getDisplayText (aDisplayLocale) +
                                               EPhotonCoreText.getYesOrNo (WebSiteResourceCache.isCacheEnabled (),
                                                                           aDisplayLocale)));
    aNodeList.addChild (new HCDiv ().addChild (EText.MSG_RESBUNDLE_SERVLET.getDisplayText (aDisplayLocale) +
                                               EPhotonCoreText.getYesOrNo (ResourceBundleServlet.isServletRegisteredInServletContext (),
                                                                           aDisplayLocale)));
    aNodeList.addChild (new HCDiv ().addChild (EText.MSG_RESBUNDLE_ACTIVE.getDisplayText (aDisplayLocale) +
                                               EPhotonCoreText.getYesOrNo (ResourceBundleServlet.isActive (),
                                                                           aDisplayLocale)));

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_DATE.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                   aDisplayLocale),
                                        new DTCol (EText.MSG_RESOURCES.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_COND_COMMENT.getDisplayText (aDisplayLocale)).setDataSort (3,
                                                                                                                        1),
                                        new DTCol (EText.MSG_CSS_MEDIA.getDisplayText (aDisplayLocale)).setDataSort (4,
                                                                                                                     1)).setID (getID ());

    for (final WebSiteResourceBundleSerialized aBundle : m_aResBundleMgr.getAllResourceBundles ().values ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aBundle.getBundleID ());
      aRow.addCell (PDTToString.getAsString (aBundle.getCreationDT (), aDisplayLocale));
      aRow.addCell (HCExtHelper.list2divList (aBundle.getBundle ().getAllResourcePaths ()));
      aRow.addCell (aBundle.getBundle ().getConditionalComment ());
      aRow.addCell (aBundle.getBundle ().hasMediaList () ? aBundle.getBundle ().getMediaList ().getMediaString ()
                                                         : null);
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
