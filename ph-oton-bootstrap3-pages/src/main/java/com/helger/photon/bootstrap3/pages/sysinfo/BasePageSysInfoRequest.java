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
package com.helger.photon.bootstrap3.pages.sysinfo;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.compare.IComparator;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.servlet.cookie.CookieHelper;
import com.helger.servlet.request.RequestLogger;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Page with information on the current request
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoRequest <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
    MSG_HTTP_HEADERS ("HTTP Header", "HTTP header"),
    MSG_COOKIES ("Cookies", "Cookies"),
    MSG_PARAMETERS ("Request-Parameter", "Request parameters"),
    MSG_PROPERTIES ("Request-Eigenschaften", "Request properties"),
    MSG_ATTRIBUTES ("Request-Attribute", "Request attributes"),
    MSG_NAME ("Name", "Name"),
    MSG_TYPE ("Typ", "Type"),
    MSG_VALUE ("Wert", "Value"),
    MSG_DETAILS ("Details", "Details");

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

  public BasePageSysInfoRequest (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_REQUEST.getAsMLT ());
  }

  public BasePageSysInfoRequest (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoRequest (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoRequest (@Nonnull @Nonempty final String sID,
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

    final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();
    final HttpServletRequest aHttpRequest = aRequestScope.getRequest ();
    final int nFirstColWidth = 250;

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    // HTTP headers
    {
      final BootstrapTable aTable = new BootstrapTable (new HCCol (nFirstColWidth), HCCol.star ());
      aTable.setID (getID () + "http");
      aTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale), EText.MSG_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, ICommonsList <String>> aEntry : aRequestScope.headers ()
                                                                                 .getAllHeaders ()
                                                                                 .getSortedByKey (IComparator.getComparatorCollating (aDisplayLocale))
                                                                                 .entrySet ())
      {
        aTable.addBodyRow ().addCell (aEntry.getKey ()).addCell (HCExtHelper.list2divList (aEntry.getValue ()));
      }
      final BootstrapDataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aTabBox.addTab ("http", EText.MSG_HTTP_HEADERS.getDisplayText (aDisplayLocale), new HCNodeList ().addChildren (aTable, aDT));
    }

    // Cookies
    {
      final BootstrapTable aTable = new BootstrapTable (new HCCol (), new HCCol (), new HCCol ());
      aTable.setID (getID () + "cookies");
      aTable.addHeaderRow ()
            .addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                       EText.MSG_VALUE.getDisplayText (aDisplayLocale),
                       EText.MSG_DETAILS.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, Cookie> aEntry : CookieHelper.getAllCookies (aHttpRequest)
                                                                 .getSortedByKey (IComparator.getComparatorCollating (aDisplayLocale))
                                                                 .entrySet ())
      {
        final Cookie aCookie = aEntry.getValue ();
        String sOther = "";
        if (StringHelper.hasText (aCookie.getPath ()))
          sOther += "[path: " + aCookie.getPath () + "]";
        if (StringHelper.hasText (aCookie.getDomain ()))
          sOther += "[domain: " + aCookie.getDomain () + "]";
        if (aCookie.getSecure ())
          sOther += "[secure]";
        sOther += "[maxage: " + aCookie.getMaxAge () + "]";

        aTable.addBodyRow ().addCell (aEntry.getKey ()).addCell (aCookie.getValue ()).addCell (sOther);
      }
      final BootstrapDataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aTabBox.addTab ("cookies", EText.MSG_COOKIES.getDisplayText (aDisplayLocale), new HCNodeList ().addChildren (aTable, aDT));
    }

    // Request parameters
    {
      final BootstrapTable aTable = new BootstrapTable (new HCCol (nFirstColWidth), HCCol.star ());
      aTable.setID (getID () + "params");
      aTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale), EText.MSG_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, String> aEntry : RequestLogger.getRequestParameterMap (aHttpRequest).entrySet ())
      {
        aTable.addBodyRow ().addCell (aEntry.getKey ()).addCell (aEntry.getValue ());
      }
      final BootstrapDataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aTabBox.addTab ("params", EText.MSG_PARAMETERS.getDisplayText (aDisplayLocale), new HCNodeList ().addChildren (aTable, aDT));
    }

    // Request attributes
    {
      final BootstrapTable aTable = new BootstrapTable (new HCCol (nFirstColWidth), HCCol.star (), HCCol.star ());
      aTable.setID (getID () + "attrs");
      aTable.addHeaderRow ()
            .addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale),
                       EText.MSG_TYPE.getDisplayText (aDisplayLocale),
                       EText.MSG_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, Object> aEntry : aRequestScope.attrs ()
                                                                  .getSortedByKey (IComparator.getComparatorCollating (aDisplayLocale))
                                                                  .entrySet ())
      {
        aTable.addBodyRow ()
              .addCell (aEntry.getKey ())
              .addCell (ClassHelper.getClassLocalName (aEntry.getValue ()))
              .addCell (String.valueOf (aEntry.getValue ()));
      }
      final BootstrapDataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aTabBox.addTab ("attrs", EText.MSG_ATTRIBUTES.getDisplayText (aDisplayLocale), new HCNodeList ().addChildren (aTable, aDT));
    }

    // Request properties
    {
      final BootstrapTable aTable = new BootstrapTable (new HCCol (nFirstColWidth), HCCol.star ());
      aTable.setID (getID () + "props");
      aTable.addHeaderRow ().addCells (EText.MSG_NAME.getDisplayText (aDisplayLocale), EText.MSG_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, String> aEntry : RequestLogger.getRequestFieldMap (aHttpRequest).entrySet ())
      {
        aTable.addBodyRow ().addCell (aEntry.getKey ()).addCell (aEntry.getValue ());
      }
      final BootstrapDataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aTabBox.addTab ("props", EText.MSG_PROPERTIES.getDisplayText (aDisplayLocale), new HCNodeList ().addChildren (aTable, aDT));
    }
    aNodeList.addChild (aTabBox);
  }
}
