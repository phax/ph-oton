/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.appinfo;

import java.io.File;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCBR;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.app.PhotonAppSettings;
import com.helger.photon.app.io.WebFileIO;
import com.helger.photon.app.resource.WebSiteResourceBundleManager;
import com.helger.photon.app.resource.WebSiteResourceBundleSerialized;
import com.helger.photon.app.resource.WebSiteResourceCache;
import com.helger.photon.bootstrap4.button.BootstrapButton;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.table.BootstrapTable;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.photon.uicore.css.CPageParam;
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
public class BasePageAppInfoWebSiteResourceBundles <WPECTYPE extends IWebPageExecutionContext> extends
                                                   AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_CACHE_NOW_ENABLED ("Der Cache ist jetzt aktiviert", "Cache is now enabled"),
    MSG_CACHE_NOW_DISABLED ("Der Cache ist jetzt deaktiviert", "Cache is now disabled"),
    MSG_MERGE_CSS_NOW_ENABLED ("Das Zusammenführen von CSS ist jetzt aktiviert", "CSS merging is now enabled"),
    MSG_MERGE_CSS_NOW_DISABLED ("Das Zusammenführen von CSS ist jetzt deaktiviert", "CSS merging is now disabled"),
    MSG_MERGE_JS_NOW_ENABLED ("Das Zusammenführen von JS ist jetzt aktiviert", "JS merging is now enabled"),
    MSG_MERGE_JS_NOW_DISABLED ("Das Zusammenführen von JS ist jetzt deaktiviert", "JS merging is now disabled"),
    MSG_RESBUNDLE_SERVLET ("ResourceBundleServlet registriert", "ResourceBundleServlet registered"),
    MSG_CACHE_ENABLED ("Cache aktiv", "Cache enabled"),
    MSG_MERGE_CSS_ENABLED ("Das Zusammenführen von CSS ist aktiviert", "CSS merging is enabled"),
    MSG_MERGE_JS_ENABLED ("Das Zusammenführen von JS ist aktiviert", "JS merging is enabled"),
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

  private static final String ACTION_CHANGE_CACHE_STATE = "changecachestate";
  private static final String ACTION_CHANGE_MERGE_CSS_STATE = "changemergecssstate";
  private static final String ACTION_CHANGE_MERGE_JS_STATE = "changemergejsstate";

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

    if (aWPEC.hasAction (ACTION_CHANGE_CACHE_STATE))
    {
      WebSiteResourceCache.setCacheEnabled (!WebSiteResourceCache.isCacheEnabled ());
      final boolean bCacheEnabled = WebSiteResourceCache.isCacheEnabled ();
      aWPEC.postRedirectGetInternal (success (bCacheEnabled ? EText.MSG_CACHE_NOW_ENABLED.getDisplayText (aDisplayLocale)
                                                            : EText.MSG_CACHE_NOW_DISABLED.getDisplayText (aDisplayLocale)));
      return;
    }

    if (aWPEC.hasAction (ACTION_CHANGE_MERGE_CSS_STATE))
    {
      PhotonAppSettings.setMergeCSSResources (!PhotonAppSettings.isMergeCSSResources ());
      final boolean bResBundleActive = PhotonAppSettings.isMergeCSSResources ();
      aWPEC.postRedirectGetInternal (success (bResBundleActive ? EText.MSG_MERGE_CSS_NOW_ENABLED.getDisplayText (aDisplayLocale)
                                                               : EText.MSG_MERGE_CSS_NOW_DISABLED.getDisplayText (aDisplayLocale)));
      return;
    }

    if (aWPEC.hasAction (ACTION_CHANGE_MERGE_JS_STATE))
    {
      PhotonAppSettings.setMergeJSResources (!PhotonAppSettings.isMergeJSResources ());
      final boolean bResBundleActive = PhotonAppSettings.isMergeJSResources ();
      aWPEC.postRedirectGetInternal (success (bResBundleActive ? EText.MSG_MERGE_JS_NOW_ENABLED.getDisplayText (aDisplayLocale)
                                                               : EText.MSG_MERGE_JS_NOW_DISABLED.getDisplayText (aDisplayLocale)));
      return;
    }

    // Refresh button
    final BootstrapButtonToolbar aToolbar = new BootstrapButtonToolbar (aWPEC);
    aToolbar.addButton (EPhotonCoreText.BUTTON_REFRESH.getDisplayText (aDisplayLocale),
                        aWPEC.getSelfHref (),
                        EDefaultIcon.REFRESH);
    aNodeList.addChild (aToolbar);

    {
      final boolean bCacheEnabled = WebSiteResourceCache.isCacheEnabled ();
      final boolean bServletRegistered = ResourceBundleServlet.isServletRegisteredInServletContext ();
      final boolean bMergeCSSActive = PhotonAppSettings.isMergeCSSResources ();
      final boolean bMergeJSActive = PhotonAppSettings.isMergeJSResources ();

      final BootstrapTable aTable = new BootstrapTable (HCCol.star (),
                                                        HCCol.star (),
                                                        bServletRegistered ? HCCol.star () : null).setStriped (true);
      final HCRow aRegisteredRow = aTable.addBodyRow ()
                                         .addCell (EText.MSG_RESBUNDLE_SERVLET.getDisplayText (aDisplayLocale))
                                         .addCell (EPhotonCoreText.getYesOrNo (bServletRegistered, aDisplayLocale));
      final HCRow aCacheRow = aTable.addBodyRow ()
                                    .addCell (EText.MSG_CACHE_ENABLED.getDisplayText (aDisplayLocale))
                                    .addCell (EPhotonCoreText.getYesOrNo (bCacheEnabled, aDisplayLocale));
      final HCRow aMergeCSSRow = aTable.addBodyRow ()
                                       .addCell (EText.MSG_MERGE_CSS_ENABLED.getDisplayText (aDisplayLocale))
                                       .addCell (EPhotonCoreText.getYesOrNo (bMergeCSSActive, aDisplayLocale));
      final HCRow aMergeJSRow = aTable.addBodyRow ()
                                      .addCell (EText.MSG_MERGE_JS_ENABLED.getDisplayText (aDisplayLocale))
                                      .addCell (EPhotonCoreText.getYesOrNo (bMergeJSActive, aDisplayLocale));
      if (bServletRegistered)
      {
        aRegisteredRow.addCell ();
        aCacheRow.addCell (new BootstrapButton ().addChild (bCacheEnabled ? EPhotonCoreText.BUTTON_DISABLE.getDisplayText (aDisplayLocale)
                                                                          : EPhotonCoreText.BUTTON_ENABLE.getDisplayText (aDisplayLocale))
                                                 .setOnClick (aWPEC.getSelfHref ()
                                                                   .add (CPageParam.PARAM_ACTION,
                                                                         ACTION_CHANGE_CACHE_STATE)));
        aMergeCSSRow.addCell (new BootstrapButton ().addChild (bMergeCSSActive ? EPhotonCoreText.BUTTON_DISABLE.getDisplayText (aDisplayLocale)
                                                                               : EPhotonCoreText.BUTTON_ENABLE.getDisplayText (aDisplayLocale))
                                                    .setOnClick (aWPEC.getSelfHref ()
                                                                      .add (CPageParam.PARAM_ACTION,
                                                                            ACTION_CHANGE_MERGE_CSS_STATE)));
        aMergeJSRow.addCell (new BootstrapButton ().addChild (bMergeJSActive ? EPhotonCoreText.BUTTON_DISABLE.getDisplayText (aDisplayLocale)
                                                                             : EPhotonCoreText.BUTTON_ENABLE.getDisplayText (aDisplayLocale))
                                                   .setOnClick (aWPEC.getSelfHref ()
                                                                     .add (CPageParam.PARAM_ACTION,
                                                                           ACTION_CHANGE_MERGE_JS_STATE)));
      }
      aNodeList.addChild (aTable);

      aNodeList.addChild (new HCBR ());
    }

    final HCTable aTable = new HCTable (new DTCol (EText.MSG_ID.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.MSG_DATE.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DATETIME,
                                                                                                                   aDisplayLocale),
                                        new DTCol (EText.MSG_RESOURCES.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.MSG_COND_COMMENT.getDisplayText (aDisplayLocale)).setDataSort (3,
                                                                                                                        1),
                                        new DTCol (EText.MSG_CSS_MEDIA.getDisplayText (aDisplayLocale)).setDataSort (4,
                                                                                                                     1)).setID (getID ());

    final ICommonsList <WebSiteResourceBundleSerialized> aBundles = m_aResBundleMgr.getAllResourceBundlesSerialized ();
    for (final WebSiteResourceBundleSerialized aBundle : aBundles)
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

    if (GlobalDebug.isDebugMode ())
    {
      aNodeList.addChild (getUIHandler ().createDataGroupHeader ("Resource bundle directory listing"));

      final File aDir = WebFileIO.getDataIO ().getFile (WebSiteResourceBundleSerialized.RESOURCE_BUNDLE_PATH);
      final HCTable aTable2 = new HCTable (new DTCol ("Filename"), new DTCol ("Active?")).setID (getID () + "2");
      for (final File aFile : new FileSystemIterator (aDir))
      {
        final HCRow aRow = aTable2.addBodyRow ();
        aRow.addCell (aFile.getName ());
        aRow.addCell (m_aResBundleMgr.containsResourceBundleOfID (aFile.getName ()) ? "yes" : "no");
      }
      aNodeList.addChild (aTable2);
      final DataTables aDataTables2 = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable2);
      aNodeList.addChild (aDataTables2);
    }
  }
}
