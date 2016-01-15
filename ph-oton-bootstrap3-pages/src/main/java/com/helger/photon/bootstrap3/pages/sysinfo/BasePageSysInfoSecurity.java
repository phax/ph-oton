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
package com.helger.photon.bootstrap3.pages.sysinfo;

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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.AbstractComparator;
import com.helger.commons.compare.CompareHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

/**
 * Page with information on the current security settings
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoSecurity <WPECTYPE extends IWebPageExecutionContext>
                                     extends AbstractBootstrapWebPage <WPECTYPE>
{
  public static final class ComparatorProviderNameAndVersion extends AbstractComparator <Provider>
  {
    @Override
    protected int mainCompare (@Nonnull final Provider aElement1, @Nonnull final Provider aElement2)
    {
      int ret = aElement1.getName ().compareTo (aElement2.getName ());
      if (ret == 0)
        ret = CompareHelper.compare (aElement1.getVersion (), aElement2.getVersion ());
      return ret;
    }
  }

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
    MSG_CLASSNAME ("Klassenname", "Class name"),
    MSG_DEFAULT_PROTOCOLS ("Standard Protokolle", "Default protocols"),
    MSG_DEFAULT_CIPHER_SUITES ("Standard Cipher Suites", "Default cipher suites"),
    MSG_SUPPORTED_PROTOCOLS ("Unterstützte Protokolle", "Supported protocols"),
    MSG_SUPPORTED_CIPHER_SUITES ("Unterstützte Cipher Suites", "Supported cipher suites");

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
                                  @Nonnull final IMultilingualText aName,
                                  @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    final List <Provider> aSortedProviders = CollectionHelper.getSorted (Security.getProviders (),
                                                                         new ComparatorProviderNameAndVersion ());

    // show all providers
    {
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_VERSION.getDisplayText (aDisplayLocale)).setDisplayType (EDTColType.DOUBLE,
                                                                                                                        aDisplayLocale),
                                          new DTCol (EText.MSG_INFO.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                             "-providers");

      for (final Provider aSecurityProvider : aSortedProviders)
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCell (aSecurityProvider.getName ());
        aRow.addCell (Double.toString (aSecurityProvider.getVersion ()));
        aRow.addCell (aSecurityProvider.getInfo ());
      }

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setPageLengthAll ();

      aTabBox.addTab (EText.TAB_PROVIDERS.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }

    // Show all algorithms of all providers
    {
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_PROVIDER.getDisplayText (aDisplayLocale)).setDataSort (0,
                                                                                                                      1,
                                                                                                                      2),
                                          new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)).setDataSort (1, 2)
                                                                                                    .setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_ALGORITHM.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_CLASSNAME.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                  "-algorithm");
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
      aDataTables.setPageLengthAll ();
      aTabBox.addTab (EText.TAB_ALGORITHMS.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }

    // one tab per provider
    {
      for (final Provider aSecurityProvider : aSortedProviders)
      {
        final String sProviderName = aSecurityProvider.getName () + " " + aSecurityProvider.getVersion ();
        final HCTable aTable = new HCTable (new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)).setDataSort (0,
                                                                                                                    1),
                                            new DTCol (EText.MSG_ALGORITHM.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                            new DTCol (EText.MSG_CLASSNAME.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                    "-" +
                                                                                                                    RegExHelper.getAsIdentifier (sProviderName));

        // Services of this providers
        for (final Service aService : aSecurityProvider.getServices ())
        {
          final HCRow aRow = aTable.addBodyRow ();
          aRow.addCell (aService.getType ());
          aRow.addCell (aService.getAlgorithm ());
          aRow.addCell (aService.getClassName ());
        }

        final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
        aDataTables.setPageLengthAll ();

        // Add properties of this provider
        final BootstrapTable aPropsTable = new BootstrapTable (HCCol.star (), HCCol.star ());
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
                                         .addChild (createDataGroupHeader (EText.MSG_PROPS.getDisplayText (aDisplayLocale)))
                                         .addChild (aPropsTable));
      }
    }

    // List details of all SSLContexts
    {
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_PROVIDER.getDisplayText (aDisplayLocale)).setDataSort (0,
                                                                                                                      1,
                                                                                                                      2),
                                          new DTCol (EText.MSG_TYPE.getDisplayText (aDisplayLocale)).setDataSort (1, 2),
                                          new DTCol (EText.MSG_ALGORITHM.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_DEFAULT_PROTOCOLS.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_DEFAULT_CIPHER_SUITES.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_SUPPORTED_PROTOCOLS.getDisplayText (aDisplayLocale)),
                                          new DTCol (EText.MSG_SUPPORTED_CIPHER_SUITES.getDisplayText (aDisplayLocale))).setID (getID () +
                                                                                                                                "-sslcontexts");
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
      aDataTables.setPageLengthAll ();
      aTabBox.addTab (EText.TAB_SSLCONTEXT.getDisplayText (aDisplayLocale),
                      new HCNodeList ().addChild (aTable).addChild (aDataTables));
    }

    aNodeList.addChild (aTabBox);
  }
}
