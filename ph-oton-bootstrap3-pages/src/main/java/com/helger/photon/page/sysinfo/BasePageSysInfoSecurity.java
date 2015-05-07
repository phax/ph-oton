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
package com.helger.photon.page.sysinfo;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;

import com.helger.bootstrap3.datatables.BootstrapDataTables;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.AbstractComparator;
import com.helger.commons.compare.CompareUtils;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCH3;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.html.HCSpan;
import com.helger.html.hc.html.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.uicore.html.tabbox.ITabBox;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.datatables.DataTablesLengthMenuList;

/**
 * Page with information on the current security settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoSecurity <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    TAB_PROVIDERS ("Provider", "Providers"),
    TAB_ALGORITHMS ("Algorithmen", "Algorithms"),
    TAB_SSLCONTEXT ("SSLContext", "SSLContext"),
    MSG_KEY ("Name", "Name"),
    MSG_VALUE ("Wert", "Value"),
    MSG_NAME ("Name", "Name"),
    MSG_VERSION ("Version", "Version"),
    MSG_INFO ("Info", "Info"),
    MSG_PROPS ("Eigenschaften", "Properties"),
    MSG_PROVIDER ("Provider", "Provider"),
    MSG_TYPE ("Typ", "Type"),
    MSG_ALGORITHM ("Algorithmus", "Algorithm"),
    MSG_CLASSNAME ("Klassenname", "Class name");

    private final TextProvider m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
    }
  }

  public BasePageSysInfoSecurity (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_SECURITY.getAsMLT ());
  }

  public BasePageSysInfoSecurity (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoSecurity (@Nonnull @Nonempty final String sID,
                                  @Nonnull final String sName,
                                  @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoSecurity (@Nonnull @Nonempty final String sID,
                                  @Nonnull final IReadonlyMultiLingualText aName,
                                  @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final ITabBox <?> aTabBox = new BootstrapTabBox();

    final List <Provider> aSortedProviders = CollectionHelper.getSorted (Security.getProviders (),
                                                                        new AbstractComparator <Provider> ()
                                                                        {
                                                                          @Override
                                                                          protected int mainCompare (@Nonnull final Provider aElement1,
                                                                                                     @Nonnull final Provider aElement2)
                                                                          {
                                                                            int ret = aElement1.getName ()
                                                                                               .compareTo (aElement2.getName ());
                                                                            if (ret == 0)
                                                                              ret = CompareUtils.compare (aElement1.getVersion (),
                                                                                                          aElement2.getVersion ());
                                                                            return ret;
                                                                          }
                                                                        });

    // show all providers
    {
      final HCTable aTable = new HCTable (HCCol.star (), HCCol.star (), HCCol.star ());
      aTable.setID (getID () + "-providers");
      aTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_VERSION.getDisplayText (aDisplayLocale),
                                       EText.MSG_INFO.getDisplayText (aDisplayLocale));

      for (final Provider aSecurityProvider : aSortedProviders)
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCell (aSecurityProvider.getName ());
        aRow.addCell (Double.toString (aSecurityProvider.getVersion ()));
        aRow.addCell (aSecurityProvider.getInfo ());
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setDisplayLength (DataTablesLengthMenuList.COUNT_ALL);
      aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);

      aTabBox.addTab (EText.TAB_PROVIDERS.getDisplayText (aDisplayLocale), new HCNodeList ().addChild (aTable)
                                                                                            .addChild (aDataTables));
    }

    // Show all algorithms of all providers
    {
      final HCTable aTable = new HCTable (HCCol.star (), HCCol.star (), HCCol.star (), HCCol.star ());
      aTable.setID (getID () + "-algorithm");
      aTable.addHeaderRow ().addCells (EText.MSG_PROVIDER.getDisplayText (aDisplayLocale),
                                       EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                       EText.MSG_ALGORITHM.getDisplayText (aDisplayLocale),
                                       EText.MSG_CLASSNAME.getDisplayText (aDisplayLocale));
      for (final Provider aSecurityProvider : aSortedProviders)
      {
        final String sProviderName = aSecurityProvider.getName () + " " + aSecurityProvider.getVersion ();

        for (final Service aService : aSecurityProvider.getServices ())
        {
          final HCRow aRow = aTable.addBodyRow ();
          aRow.addCell (new HCSpan ().addChild (sProviderName).addClass (CSS_CLASS_NOWRAP));
          aRow.addCell (aService.getType ());
          aRow.addCell (aService.getAlgorithm ());
          aRow.addCell (aService.getClassName ());
        }
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setDisplayLength (DataTablesLengthMenuList.COUNT_ALL);
      aDataTables.getOrCreateColumnOfTarget (0).setDataSort (0, 1, 2);
      aDataTables.getOrCreateColumnOfTarget (1).setDataSort (1, 2);
      aDataTables.setInitialSorting (1, ESortOrder.ASCENDING);

      aTabBox.addTab (EText.TAB_ALGORITHMS.getDisplayText (aDisplayLocale), new HCNodeList ().addChild (aTable)
                                                                                             .addChild (aDataTables));
    }

    // one tab per provider
    {
      for (final Provider aSecurityProvider : aSortedProviders)
      {
        final String sProviderName = aSecurityProvider.getName () + " " + aSecurityProvider.getVersion ();
        final HCTable aTable = new HCTable (HCCol.star (), HCCol.star (), HCCol.star ());
        aTable.setID (getID () + "-" + RegExHelper.getAsIdentifier (sProviderName));
        aTable.addHeaderRow ().addCells (EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                         EText.MSG_ALGORITHM.getDisplayText (aDisplayLocale),
                                         EText.MSG_CLASSNAME.getDisplayText (aDisplayLocale));

        // Services of this providers
        for (final Service aService : aSecurityProvider.getServices ())
        {
          final HCRow aRow = aTable.addBodyRow ();
          aRow.addCell (aService.getType ());
          aRow.addCell (aService.getAlgorithm ());
          aRow.addCell (aService.getClassName ());
        }

        final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
        aDataTables.setDisplayLength (DataTablesLengthMenuList.COUNT_ALL);
        aDataTables.getOrCreateColumnOfTarget (0).setDataSort (0, 1);
        aDataTables.setInitialSorting (1, ESortOrder.ASCENDING);

        // Add properties of this provider
        final IHCTable <?> aPropsTable = new BootstrapTable (HCCol.star (), HCCol.star ());
        aPropsTable.addHeaderRow ().addCells (EText.MSG_KEY.getDisplayText (aDisplayLocale),
                                              EText.MSG_VALUE.getDisplayText (aDisplayLocale));
        final Set <String> aKeys = new HashSet <String> ();
        for (final Object aName : aSecurityProvider.keySet ())
          aKeys.add ((String) aName);
        for (final String sName : CollectionHelper.getSorted (aKeys))
        {
          final HCRow aPropsRow = aPropsTable.addBodyRow ();
          aPropsRow.addCell (sName);
          aPropsRow.addCell (aSecurityProvider.getProperty (sName));
        }

        aTabBox.addTab (sProviderName,
                        new HCNodeList ().addChild (aTable)
                                         .addChild (aDataTables)
                                         .addChild (new HCH3 ().addChild (EText.MSG_PROPS.getDisplayText (aDisplayLocale)))
                                         .addChild (aPropsTable));
      }
    }

    // List details of all SSLContexts
    {
      final HCTable aTable = new HCTable (HCCol.star (),
                                          HCCol.star (),
                                          HCCol.star (),
                                          HCCol.star (),
                                          HCCol.star (),
                                          HCCol.star (),
                                          HCCol.star ());
      aTable.setID (getID () + "-sslcontexts");
      aTable.addHeaderRow ().addCells (EText.MSG_PROVIDER.getDisplayText (aDisplayLocale),
                                       EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                                       EText.MSG_ALGORITHM.getDisplayText (aDisplayLocale),
                                       "Default protocols",
                                       "Default cipher suites",
                                       "Supported protocols",
                                       "Supported cipher suites");
      for (final Provider aSecurityProvider : aSortedProviders)
      {
        final String sProviderName = aSecurityProvider.getName () + " " + aSecurityProvider.getVersion ();

        for (final Service aService : aSecurityProvider.getServices ())
          if ("SSLContext".equals (aService.getType ()))
          {
            final HCRow aRow = aTable.addBodyRow ();
            aRow.addCell (new HCSpan ().addChild (sProviderName).addClass (CSS_CLASS_NOWRAP));
            aRow.addCell (aService.getType ());
            aRow.addCell (aService.getAlgorithm ());
            try
            {
              final SSLContext aSSLCtx = SSLContext.getInstance (aService.getAlgorithm ());
              if (!"Default".equals (aService.getAlgorithm ()))
              {
                // Default SSLContext is initialized automatically
                aSSLCtx.init (null, null, null);
              }
              SSLParameters aParams = aSSLCtx.getDefaultSSLParameters ();
              aRow.addCell (StringHelper.getImploded (", ", aParams.getProtocols ()));
              aRow.addCell (StringHelper.getImploded (", ", aParams.getCipherSuites ()));
              aParams = aSSLCtx.getSupportedSSLParameters ();
              aRow.addCell (StringHelper.getImploded (", ", aParams.getProtocols ()));
              aRow.addCell (StringHelper.getImploded (", ", aParams.getCipherSuites ()));
            }
            catch (final Throwable ex)
            {
              aRow.addCell (new BootstrapErrorBox ().addChild (ex.getMessage ()));
              aRow.addCell ();
              aRow.addCell ();
              aRow.addCell ();
            }
          }
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setDisplayLength (DataTablesLengthMenuList.COUNT_ALL);
      aDataTables.getOrCreateColumnOfTarget (0).setDataSort (0, 1, 2);
      aDataTables.getOrCreateColumnOfTarget (1).setDataSort (1, 2);
      aDataTables.setInitialSorting (2, ESortOrder.ASCENDING);

      aTabBox.addTab (EText.TAB_SSLCONTEXT.getDisplayText (aDisplayLocale), new HCNodeList ().addChild (aTable)
                                                                                             .addChild (aDataTables));
    }

    aNodeList.addChild (aTabBox);
  }
}
