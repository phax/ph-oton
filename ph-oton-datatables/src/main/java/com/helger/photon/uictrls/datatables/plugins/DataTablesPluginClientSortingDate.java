/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.plugins;

import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.datetime.EDTFormatterMode;
import com.helger.commons.datetime.EDTType;
import com.helger.commons.datetime.PDTFormatter;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.EDataTablesJSPathProvider;
import com.helger.photon.uictrls.datatables.column.EDTColType;

/**
 * Add date/time based sorting for client side DataTables. This is based on
 * moment.js
 *
 * @author Philip Helger
 */
public class DataTablesPluginClientSortingDate extends AbstractDataTablesPlugin
{
  public static final String PLUGIN_NAME = "clientSortingDate";
  private final ICommonsSet <EDTColType> m_aDateTimeTypes;

  public DataTablesPluginClientSortingDate (@Nonnull @Nonempty final Set <EDTColType> aDateTimeTypes)
  {
    super (PLUGIN_NAME);
    ValueEnforcer.notEmptyNoNullValue (aDateTimeTypes, "DateTimeTypes");
    m_aDateTimeTypes = new CommonsHashSet <> (aDateTimeTypes);
  }

  @Override
  public boolean canBeApplied (@Nonnull final DataTables aDT)
  {
    return aDT.isClientSide ();
  }

  @Nonnull
  private static String _fixFormatter (@Nonnull final String s)
  {
    String ret = s;
    ret = StringHelper.replaceAll (ret, 'y', 'Y');
    ret = StringHelper.replaceAll (ret, 'd', 'D');
    return ret;
  }

  @Override
  public void finalizeDataTablesSettings (@Nonnull final DataTables aDT)
  {
    aDT.setJSBeforeModifier (p -> {
      final Locale aDisplayLocale = aDT.getDisplayLocale ();
      for (final EDTColType eColType : m_aDateTimeTypes)
      {
        EDTType eDTType = null;
        switch (eColType)
        {
          case DATE:
            eDTType = EDTType.LOCAL_DATE;
            break;
          case TIME:
            eDTType = EDTType.LOCAL_TIME;
            break;
          case DATETIME:
            eDTType = EDTType.LOCAL_DATE_TIME;
            break;
        }
        if (eDTType != null)
          p.add (DataTables.invokeDataTablesMoment ()
                           .arg (_fixFormatter (PDTFormatter.getPattern (eDTType,
                                                                         aDisplayLocale,
                                                                         FormatStyle.MEDIUM,
                                                                         EDTFormatterMode.PARSE)))
                           .arg (aDisplayLocale.toString ()));
      }
    });
  }

  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.MOMENT);
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesJSPathProvider.DATATABLES_SORTING_MOMENT);
  }

  public IJSExpression getInitParams ()
  {
    return null;
  }
}
