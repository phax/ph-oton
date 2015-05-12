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
package com.helger.photon.bootstrap3.pages.monitoring;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageExt;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.photon.core.resource.WebSiteResourceBundleManager;
import com.helger.photon.core.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.core.resource.WebSiteResourceCache;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.comparator.ComparatorDTDateTime;

/**
 * Show web site resource bundles.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageMonitoringWebSiteResourceBundles <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageExt <WPECTYPE>
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
    private final TextProvider m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
    }
  }

  private final WebSiteResourceBundleManager m_aResBundleMgr;

  public BasePageMonitoringWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                   @Nonnull final WebSiteResourceBundleManager aResBundleMgr)
  {
    super (sID, EWebPageText.PAGE_NAME_MONITORING_WEBRESBUNDLE.getAsMLT ());
    m_aResBundleMgr = ValueEnforcer.notNull (aResBundleMgr, "ResBundleMgr");
  }

  public BasePageMonitoringWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                   @Nonnull final String sName,
                                                   @Nonnull final WebSiteResourceBundleManager aResBundleMgr)
  {
    super (sID, sName);
    m_aResBundleMgr = ValueEnforcer.notNull (aResBundleMgr, "ResBundleMgr");
  }

  public BasePageMonitoringWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                   @Nonnull final String sName,
                                                   @Nullable final String sDescription,
                                                   @Nonnull final WebSiteResourceBundleManager aResBundleMgr)
  {
    super (sID, sName, sDescription);
    m_aResBundleMgr = ValueEnforcer.notNull (aResBundleMgr, "ResBundleMgr");
  }

  public BasePageMonitoringWebSiteResourceBundles (@Nonnull @Nonempty final String sID,
                                                   @Nonnull final IReadonlyMultiLingualText aName,
                                                   @Nullable final IReadonlyMultiLingualText aDescription,
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
    final IButtonToolbar <?> aToolbar = new BootstrapButtonToolbar (aWPEC);
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

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (),
                                                    HCCol.star (),
                                                    HCCol.star (),
                                                    HCCol.star (),
                                                    HCCol.star ()).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.MSG_ID.getDisplayText (aDisplayLocale),
                                     EText.MSG_DATE.getDisplayText (aDisplayLocale),
                                     EText.MSG_RESOURCES.getDisplayText (aDisplayLocale),
                                     EText.MSG_COND_COMMENT.getDisplayText (aDisplayLocale),
                                     EText.MSG_CSS_MEDIA.getDisplayText (aDisplayLocale));

    for (final WebSiteResourceBundleSerialized aBundle : m_aResBundleMgr.getAllResourceBundles ().values ())
    {
      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aBundle.getBundleID ());
      aRow.addCell (PDTToString.getAsString (aBundle.getCreationDT (), aDisplayLocale));
      aRow.addCell (HCUtils.list2divList (aBundle.getBundle ().getAllResourcePaths ()));
      aRow.addCell (aBundle.getBundle ().getConditionalComment ());
      aRow.addCell (aBundle.getBundle ().hasMediaList () ? aBundle.getBundle ().getMediaList ().getMediaString ()
                                                        : null);
    }
    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (1)
               .addClass (CSS_CLASS_RIGHT)
               .setComparator (new ComparatorDTDateTime (aDisplayLocale));
    aDataTables.getOrCreateColumnOfTarget (3).setDataSort (3, 1);
    aDataTables.getOrCreateColumnOfTarget (4).setDataSort (4, 1);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
