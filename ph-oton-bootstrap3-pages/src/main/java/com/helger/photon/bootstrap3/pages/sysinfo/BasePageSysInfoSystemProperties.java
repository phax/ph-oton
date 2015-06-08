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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.SystemProperties;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.io.file.iterate.FileSystemIterator;
import com.helger.commons.io.misc.SizeHelper;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.name.IHasDisplayTextWithArgs;
import com.helger.commons.string.StringHelper;
import com.helger.commons.system.SystemHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCEM;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.html.HCUL;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageExt;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.table.BootstrapTableFormView;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.html.table.IHCTableFormView;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;

/**
 * Page with all system properties
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoSystemProperties <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageExt <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText, IHasDisplayTextWithArgs
  {
    MSG_HEADER_SPECIAL_SYSPROPS ("Spezielle System-Eigenschaften:", "Special system properties:"),
    MSG_HEADER_SYSPROPS ("Alle System-Eigenschaften:", "All system properties:"),
    MSG_HEADER_NAME ("Name", "Name"),
    MSG_HEADER_VALUE ("Wert", "Value"),
    MSG_SYSTEM_OS ("Betriebssystem", "Operating system"),
    MSG_SYSTEM_NUM_PROCESSORS ("Anzahl Prozessoren", "Number of processors"),
    MSG_SYSTEM_CHARSET ("System-Zeichensatz", "System charset"),
    MSG_SYSTEM_LOCALE ("System-Sprache", "System locale"),
    MSG_SYSTEM_MEM_FREE ("Freier Speicher", "Free memory"),
    MSG_SYSTEM_MEM_MAX ("Max Speicher", "Max memory"),
    MSG_SYSTEM_MEM_TOTAL ("Totaler Speicher", "Total memory"),
    MSG_CONTEXT_CLASSLOADER ("Context Classloader", "Context classloader"),
    MSG_SYSTEM_CLASSLOADER ("System Classloader", "System classloader"),
    MSG_SYSTEM_BASEDIR ("Datenverzeichnis", "Data directory"),
    MSG_SYSTEM_BASEDIR_TOTAL ("Speicherplatz im Datenverzeichnis", "Total space in the data directory"),
    MSG_SYSTEM_BASEDIR_FREE ("Freier Speicherplatz im Datenverzeichnis", "Free space in the data directory"),
    MSG_SYSTEM_BASEDIR_USABLE ("Verwendbarer Speicherplatz im Datenverzeichnis", "Usable space in the data directory"),
    MSG_SYSTEM_SC_DIR ("WebApp-Verzeichnis", "WebApp directory"),
    MSG_SYSTEM_SC_DIR_TOTAL ("Speicherplatz im WebApp-Verzeichnis", "Total space in the WebApp directory"),
    MSG_SYSTEM_SC_DIR_FREE ("Freier Speicherplatz im WebApp-Verzeichnis", "Free space in the WebApp directory"),
    MSG_SYSTEM_SC_DIR_USABLE ("Verwendbarer Speicherplatz im WebApp-Verzeichnis", "Usable space in the WebApp directory"),
    MSG_ENDORSED_DIR ("Endorsed Verzeichnis", "Endorsed directory"),
    MSG_EXT_DIR ("Extension Verzeichnis", "Extension directory"),
    MSG_DIR_NOT_EXISTING ("Das Verzeichnis existiert nicht", "The directory does not exist"),
    MSG_DIR_EMPTY ("Das Verzeichnis ist leer", "The directory is empty"),
    MSG_DIR_FILE_ENTRY ("{0} ({1} Bytes - {2})", "{0} ({1} bytes - {2})");

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

    @Nullable
    public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
    {
      return DefaultTextResolver.getTextWithArgs (this, m_aTP, aContentLocale, aArgs);
    }
  }

  public BasePageSysInfoSystemProperties (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_SYSPROPS.getAsMLT ());
  }

  public BasePageSysInfoSystemProperties (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoSystemProperties (@Nonnull @Nonempty final String sID,
                                          @Nonnull final String sName,
                                          @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoSystemProperties (@Nonnull @Nonempty final String sID,
                                          @Nonnull final IReadonlyMultiLingualText aName,
                                          @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  private static void _addDirectoryContent (@Nonnull final IHCTableFormView <?> aTable,
                                            @Nonnull final String sSysPropName,
                                            @Nonnull final IHCNode aLabel,
                                            @Nonnull final SizeHelper aSH,
                                            @Nonnull final Locale aDisplayLocale)
  {
    final String sDir = SystemProperties.getPropertyValue (sSysPropName);
    if (sDir != null)
    {
      final List <IHCNode> aList = new ArrayList <IHCNode> ();
      // The property may contain several paths
      for (final String sPart : StringHelper.getExploded (SystemProperties.getPathSeparator (), sDir))
      {
        aList.add (HCDiv.create (sPart));
        final HCUL aUL = new HCUL ();
        aList.add (aUL);
        final File aEndorsedDir = new File (sPart);
        if (!aEndorsedDir.exists ())
        {
          // Directory does not exist
          aUL.addItem (HCEM.create (EText.MSG_DIR_NOT_EXISTING.getDisplayText (aDisplayLocale)));
        }
        else
        {
          // Directory exists - scan content
          final List <File> aFiles = CollectionHelper.newList (new FileSystemIterator (aEndorsedDir));
          if (aFiles.isEmpty ())
          {
            // Directory is empty
            aUL.addItem (HCEM.create (EText.MSG_DIR_EMPTY.getDisplayText (aDisplayLocale)));
          }
          else
          {
            // List content
            for (final File aFile : aFiles)
              aUL.addItem (EText.MSG_DIR_FILE_ENTRY.getDisplayTextWithArgs (aDisplayLocale,
                                                                            aFile.getName (),
                                                                            Long.valueOf (aFile.length ()),
                                                                            aSH.getAsMatching (aFile.length (), 2)));
          }
        }
      }
      aTable.addBodyRow ().addCell (aLabel).addCell (aList);
    }
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    {
      aNodeList.addChild (createActionHeader (EText.MSG_HEADER_SPECIAL_SYSPROPS.getDisplayText (aDisplayLocale)));
      final IHCTableFormView <?> aTable = new BootstrapTableFormView (new HCCol (250), HCCol.star ());
      aTable.setID (getID () + "$special");
      aTable.addHeaderRow ().addCells (EText.MSG_HEADER_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_HEADER_VALUE.getDisplayText (aDisplayLocale));

      // add some other properties
      {
        final SizeHelper aSH = SizeHelper.getSizeHelperOfLocale (aDisplayLocale);

        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_OS.getDisplayText (aDisplayLocale),
                                       SystemHelper.getOperatingSystem ().getDisplayName () +
                                           " / " +
                                           SystemHelper.getOperatingSystemName ());
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_NUM_PROCESSORS.getDisplayText (aDisplayLocale),
                                       Integer.toString (SystemHelper.getNumberOfProcessors ()));
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_CHARSET.getDisplayText (aDisplayLocale),
                                       SystemHelper.getSystemCharset ().toString ());
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_LOCALE.getDisplayText (aDisplayLocale),
                                       SystemHelper.getSystemLocale ().toString ());
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_MEM_FREE.getDisplayText (aDisplayLocale),
                                       Long.toString (SystemHelper.getFreeMemory ()) +
                                           " / " +
                                           aSH.getAsMatching (SystemHelper.getFreeMemory (), 2));
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_MEM_MAX.getDisplayText (aDisplayLocale),
                                       Long.toString (SystemHelper.getMaxMemory ()) +
                                           " / " +
                                           aSH.getAsMatching (SystemHelper.getMaxMemory (), 2));
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_MEM_TOTAL.getDisplayText (aDisplayLocale),
                                       Long.toString (SystemHelper.getTotalMemory ()) +
                                           " / " +
                                           aSH.getAsMatching (SystemHelper.getTotalMemory (), 2));
        aTable.addBodyRow ().addCells (EText.MSG_CONTEXT_CLASSLOADER.getDisplayText (aDisplayLocale),
                                       ClassHelper.getContextClassLoader ().toString ());
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_CLASSLOADER.getDisplayText (aDisplayLocale),
                                       ClassHelper.getSystemClassLoader ().toString ());

        final File aBaseDir = WebFileIO.getBasePathFile ();
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_BASEDIR.getDisplayText (aDisplayLocale), aBaseDir.toString ());
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_BASEDIR_TOTAL.getDisplayText (aDisplayLocale),
                                       aSH.getAsMatching (aBaseDir.getTotalSpace (), 2));
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_BASEDIR_FREE.getDisplayText (aDisplayLocale),
                                       aSH.getAsMatching (aBaseDir.getFreeSpace (), 2));
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_BASEDIR_USABLE.getDisplayText (aDisplayLocale),
                                       aSH.getAsMatching (aBaseDir.getUsableSpace (), 2));

        final File aSCDir = WebFileIO.getServletContextIO ().getBasePathFile ();
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_SC_DIR.getDisplayText (aDisplayLocale), aSCDir.toString ());
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_SC_DIR_TOTAL.getDisplayText (aDisplayLocale),
                                       aSH.getAsMatching (aSCDir.getTotalSpace (), 2));
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_SC_DIR_FREE.getDisplayText (aDisplayLocale),
                                       aSH.getAsMatching (aSCDir.getFreeSpace (), 2));
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_SC_DIR_USABLE.getDisplayText (aDisplayLocale),
                                       aSH.getAsMatching (aSCDir.getUsableSpace (), 2));

        _addDirectoryContent (aTable,
                              "java.endorsed.dirs",
                              new HCTextNode (EText.MSG_ENDORSED_DIR.getDisplayText (aDisplayLocale)),
                              aSH,
                              aDisplayLocale);
        _addDirectoryContent (aTable,
                              "java.ext.dirs",
                              new HCTextNode (EText.MSG_EXT_DIR.getDisplayText (aDisplayLocale)),
                              aSH,
                              aDisplayLocale);
      }
      aNodeList.addChild (aTable);
    }

    {
      aNodeList.addChild (createActionHeader (EText.MSG_HEADER_SYSPROPS.getDisplayText (aDisplayLocale)));
      final IHCTable <?> aTable = new BootstrapTable (new HCCol (250), HCCol.star ());
      aTable.setID (getID ());
      aTable.addHeaderRow ().addCells (EText.MSG_HEADER_NAME.getDisplayText (aDisplayLocale),
                                       EText.MSG_HEADER_VALUE.getDisplayText (aDisplayLocale));

      // For all system properties
      final String sPathSep = SystemProperties.getPathSeparator ();
      for (final Map.Entry <String, String> aEntry : CollectionHelper.getSortedByKey (SystemProperties.getAllProperties ())
                                                                     .entrySet ())
      {
        final String sName = aEntry.getKey ();
        final String sValue = aEntry.getValue ();
        final HCRow aRow = aTable.addBodyRow ();

        if ((sName.endsWith (".path") || sName.endsWith (".dirs")) && sValue.contains (sPathSep))
        {
          // Special handling for paths
          aRow.addCell (sName);
          aRow.addCell (HCUtils.nl2brList (StringHelper.replaceAll (sValue, sPathSep, "\n")));
        }
        else
          aRow.addCells (sName, sValue);
      }
      aNodeList.addChild (aTable);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
      aNodeList.addChild (aDataTables);
    }
  }
}
