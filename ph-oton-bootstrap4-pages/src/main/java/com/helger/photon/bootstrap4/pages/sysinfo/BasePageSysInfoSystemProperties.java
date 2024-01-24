/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.misc.SizeHelper;
import com.helger.commons.lang.ClassLoaderHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.system.CryptoPolicy;
import com.helger.commons.system.SystemHelper;
import com.helger.commons.system.SystemProperties;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.table.BootstrapTable;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.io.WebFileIO;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.web.scope.mgr.WebScopeManager;

/**
 * Page with all system properties
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoSystemProperties <WPECTYPE extends IWebPageExecutionContext> extends
                                             AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
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
    MSG_SYSTEM_SC_DIR_USABLE ("Verwendbarer Speicherplatz im WebApp-Verzeichnis",
                              "Usable space in the WebApp directory"),
    MSG_SYSTEM_SC_NO_DIR ("Kein Verzeichnis: {0}", "Not a directory: {0}"),
    MSG_STARTUP_DATE_TIME ("Startzeit der Anwendung", "Application startup time"),
    MSG_UPTIME ("Uptime", "Uptime"),
    MSG_JCE_UNLIMITED_STRENGTH ("JCE Unlimited Strength Policy", "JCE Unlimited Strength Policy"),
    MSG_ENDORSED_DIR ("Endorsed Verzeichnis", "Endorsed directory"),
    MSG_EXT_DIR ("Extension Verzeichnis", "Extension directory"),
    MSG_DIR_NOT_EXISTING ("Das Verzeichnis existiert nicht", "The directory does not exist"),
    MSG_DIR_EMPTY ("Das Verzeichnis ist leer", "The directory is empty"),
    MSG_DIR_FILE_ENTRY ("{0} ({1} Bytes - {2})", "{0} ({1} bytes - {2})");

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
                                          @Nonnull final IMultilingualText aName,
                                          @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  private void _addDirectoryContent (@Nonnull final BootstrapTable aTable,
                                     @Nonnull final String sSysPropName,
                                     @Nonnull final IHCNode aLabel,
                                     @Nonnull final SizeHelper aSH,
                                     @Nonnull final Locale aDisplayLocale)
  {
    final String sDir = SystemProperties.getPropertyValue (sSysPropName);
    if (sDir != null)
    {
      final ICommonsList <IHCNode> aList = new CommonsArrayList <> ();
      // The property may contain several paths
      for (final String sPart : StringHelper.getExploded (SystemProperties.getPathSeparator (), sDir))
      {
        aList.add (div (sPart));
        final HCUL aUL = new HCUL ();
        aList.add (aUL);
        final File aEndorsedDir = new File (sPart);
        if (!aEndorsedDir.exists ())
        {
          // Directory does not exist
          aUL.addItem (em (EText.MSG_DIR_NOT_EXISTING.getDisplayText (aDisplayLocale)));
        }
        else
        {
          // Directory exists - scan content
          final ICommonsList <File> aFiles = new CommonsArrayList <> (new FileSystemIterator (aEndorsedDir));
          if (aFiles.isEmpty ())
          {
            // Directory is empty
            aUL.addItem (em (EText.MSG_DIR_EMPTY.getDisplayText (aDisplayLocale)));
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
      aNodeList.addChild (getUIHandler ().createActionHeader (EText.MSG_HEADER_SPECIAL_SYSPROPS.getDisplayText (aDisplayLocale)));
      final BootstrapTable aTable = new BootstrapTable (HCCol.star (), HCCol.star ());
      aTable.setID (getID () + "$special");
      aTable.addHeaderRow ()
            .addCells (EText.MSG_HEADER_NAME.getDisplayText (aDisplayLocale),
                       EText.MSG_HEADER_VALUE.getDisplayText (aDisplayLocale));

      // add some other properties
      {
        final SizeHelper aSH = SizeHelper.getSizeHelperOfLocale (aDisplayLocale);

        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_OS.getDisplayText (aDisplayLocale),
                         SystemHelper.getOperatingSystem ().getDisplayName () +
                                                                              " / " +
                                                                              SystemHelper.getOperatingSystemName ());
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_NUM_PROCESSORS.getDisplayText (aDisplayLocale),
                         Integer.toString (SystemHelper.getNumberOfProcessors ()));
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_CHARSET.getDisplayText (aDisplayLocale),
                         SystemHelper.getSystemCharset ().toString ());
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_LOCALE.getDisplayText (aDisplayLocale),
                         SystemHelper.getSystemLocale ().toString ());
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_MEM_FREE.getDisplayText (aDisplayLocale),
                         Long.toString (SystemHelper.getFreeMemory ()) +
                                                                                    " / " +
                                                                                    aSH.getAsMatching (SystemHelper.getFreeMemory (),
                                                                                                       2));
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_MEM_MAX.getDisplayText (aDisplayLocale),
                         Long.toString (SystemHelper.getMaxMemory ()) +
                                                                                   " / " +
                                                                                   aSH.getAsMatching (SystemHelper.getMaxMemory (),
                                                                                                      2));
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_MEM_TOTAL.getDisplayText (aDisplayLocale),
                         Long.toString (SystemHelper.getTotalMemory ()) +
                                                                                     " / " +
                                                                                     aSH.getAsMatching (SystemHelper.getTotalMemory (),
                                                                                                        2));
        aTable.addBodyRow ()
              .addCells (EText.MSG_CONTEXT_CLASSLOADER.getDisplayText (aDisplayLocale),
                         ClassLoaderHelper.getContextClassLoader ().toString ());
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_CLASSLOADER.getDisplayText (aDisplayLocale),
                         ClassLoaderHelper.getSystemClassLoader ().toString ());

        final File aBaseDir = WebFileIO.getDataIO ().getBasePathFile ();
        aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_BASEDIR.getDisplayText (aDisplayLocale), aBaseDir.toString ());
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_BASEDIR_TOTAL.getDisplayText (aDisplayLocale),
                         aSH.getAsMatching (aBaseDir.getTotalSpace (), 2));
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_BASEDIR_FREE.getDisplayText (aDisplayLocale),
                         aSH.getAsMatching (aBaseDir.getFreeSpace (), 2));
        aTable.addBodyRow ()
              .addCells (EText.MSG_SYSTEM_BASEDIR_USABLE.getDisplayText (aDisplayLocale),
                         aSH.getAsMatching (aBaseDir.getUsableSpace (), 2));

        String sSCDir = WebFileIO.getServletContextIO ().getBasePath ();
        final File aSCDir = new File (sSCDir);
        if (aSCDir.exists ())
        {
          final String sSCAbsDir = aSCDir.getAbsolutePath ();
          if (!sSCAbsDir.equals (sSCDir))
            sSCDir += " (" + sSCAbsDir + ")";
          aTable.addBodyRow ().addCells (EText.MSG_SYSTEM_SC_DIR.getDisplayText (aDisplayLocale), sSCDir);
          aTable.addBodyRow ()
                .addCells (EText.MSG_SYSTEM_SC_DIR_TOTAL.getDisplayText (aDisplayLocale),
                           aSH.getAsMatching (aSCDir.getTotalSpace (), 2));
          aTable.addBodyRow ()
                .addCells (EText.MSG_SYSTEM_SC_DIR_FREE.getDisplayText (aDisplayLocale),
                           aSH.getAsMatching (aSCDir.getFreeSpace (), 2));
          aTable.addBodyRow ()
                .addCells (EText.MSG_SYSTEM_SC_DIR_USABLE.getDisplayText (aDisplayLocale),
                           aSH.getAsMatching (aSCDir.getUsableSpace (), 2));
        }
        else
        {
          aTable.addBodyRow ()
                .addCells (EText.MSG_SYSTEM_SC_DIR.getDisplayText (aDisplayLocale),
                           EText.MSG_SYSTEM_SC_NO_DIR.getDisplayTextWithArgs (aDisplayLocale, sSCDir));
        }

        // Startup time
        final LocalDateTime aCreationDT = WebScopeManager.getGlobalScope ().getCreationDateTime ();
        aTable.addBodyRow ()
              .addCells (EText.MSG_STARTUP_DATE_TIME.getDisplayText (aDisplayLocale),
                         PDTToString.getAsString (aCreationDT, aDisplayLocale));
        aTable.addBodyRow ()
              .addCells (EText.MSG_UPTIME.getDisplayText (aDisplayLocale),
                         Duration.between (aCreationDT, PDTFactory.getCurrentLocalDateTime ()).toString ());

        // JCE unlimited strength available?
        aTable.addBodyRow ()
              .addCells (EText.MSG_JCE_UNLIMITED_STRENGTH.getDisplayText (aDisplayLocale),
                         EPhotonCoreText.getYesOrNo (CryptoPolicy.isUnlimitedStrengthCryptoAvailable (),
                                                     aDisplayLocale));

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
      aNodeList.addChild (getUIHandler ().createActionHeader (EText.MSG_HEADER_SYSPROPS.getDisplayText (aDisplayLocale)));
      final HCTable aTable = new HCTable (new DTCol (EText.MSG_HEADER_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol (EText.MSG_HEADER_VALUE.getDisplayText (aDisplayLocale))).setID (getID ());

      final String sPathSep = SystemProperties.getPathSeparator ();

      // For all system properties
      for (final Map.Entry <String, String> aEntry : SystemProperties.getAllProperties ()
                                                                     .getSortedByKey (Comparator.naturalOrder ())
                                                                     .entrySet ())
      {
        final String sName = aEntry.getKey ();
        final String sNameLC = sName.toLowerCase (Locale.ROOT);
        final String sValue = aEntry.getValue ();
        final HCRow aRow = aTable.addBodyRow ();

        aRow.addCell (sName);

        if ((sNameLC.endsWith (".path") || sNameLC.endsWith (".dirs")) && sValue.contains (sPathSep))
        {
          // Special handling for paths
          aRow.addCell (HCExtHelper.nl2brList (StringHelper.replaceAll (sValue, sPathSep, "\n")));
        }
        else
        {
          aRow.addCell (sValue);
        }
      }
      aNodeList.addChild (aTable);

      final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
      aNodeList.addChild (aDataTables);
    }
  }
}
