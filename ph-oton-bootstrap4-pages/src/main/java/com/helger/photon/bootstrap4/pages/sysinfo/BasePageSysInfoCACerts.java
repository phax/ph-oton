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

import java.io.File;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.compare.ESortOrder;
import com.helger.base.lang.clazz.ClassHelper;
import com.helger.base.string.StringHelper;
import com.helger.base.system.SystemProperties;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.datetime.format.PDTToString;
import com.helger.datetime.helper.PDTFactory;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.grouping.HCOL;
import com.helger.html.hc.html.grouping.IHCLI;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.bootstrap4.uictrls.ext.BootstrapTechnicalUI;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;
import com.helger.security.keystore.EKeyStoreType;
import com.helger.security.keystore.KeyStoreHelper;
import com.helger.security.keystore.LoadedKeyStore;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Page showing "cacerts" file
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSysInfoCACerts <WPECTYPE extends IWebPageExecutionContext> extends
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

  public BasePageSysInfoCACerts (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_CACERTS.getAsMLT ());
  }

  public BasePageSysInfoCACerts (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoCACerts (@Nonnull @Nonempty final String sID,
                                 @Nonnull final String sName,
                                 @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoCACerts (@Nonnull @Nonempty final String sID,
                                 @Nonnull final IMultilingualText aName,
                                 @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  private void _showKeyStore (@Nonnull final WPECTYPE aWPEC,
                              final int nFileIndex,
                              @Nonnull final IHCElementWithChildren <?> aTarget,
                              @Nonnull final KeyStore aKS)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    try
    {
      final ICommonsList <String> aAliases = new CommonsArrayList <> (aKS.aliases ());
      final HCTable aTable = new HCTable (new DTCol ("Alias"),
                                          new DTCol ("Subject").setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol ("Root cert?"),
                                          new DTCol ("Valid from").setWidth (170)
                                                                  .setDisplayType (EDTColType.DATETIME, aDisplayLocale),
                                          new DTCol ("Valid to").setWidth (170)
                                                                .setDisplayType (EDTColType.DATETIME, aDisplayLocale))
                                                                                                                      .setID (getID () +
                                                                                                                              nFileIndex);
      for (final String sAlias : aAliases)
      {
        final HCRow aRow = aTable.addBodyRow ();
        aRow.addCell (sAlias);
        try
        {
          final KeyStore.Entry aEntry = aKS.getEntry (sAlias, null);
          if (aEntry instanceof KeyStore.TrustedCertificateEntry)
          {
            final KeyStore.TrustedCertificateEntry aTCEntry = (TrustedCertificateEntry) aEntry;
            final Certificate aCert = aTCEntry.getTrustedCertificate ();
            if (aCert instanceof X509Certificate)
            {
              final X509Certificate aX509Cert = (X509Certificate) aCert;
              final String sSubject = aX509Cert.getSubjectX500Principal ().getName ();
              final String sIssuer = aX509Cert.getIssuerX500Principal ().getName ();
              final boolean bIsRoot = sSubject.equals (sIssuer);
              aRow.addCell (sSubject);
              // Show the issuer only if it is not root
              aRow.addCell (EPhotonCoreText.getYesOrNo (bIsRoot, aDisplayLocale), bIsRoot ? null : sIssuer);
              final LocalDateTime aNotBefore = PDTFactory.createLocalDateTime (aX509Cert.getNotBefore ());
              aRow.addCell (PDTToString.getAsString (aNotBefore, aDisplayLocale));
              final LocalDateTime aNotAfter = PDTFactory.createLocalDateTime (aX509Cert.getNotAfter ());
              aRow.addCell (PDTToString.getAsString (aNotAfter, aDisplayLocale));
            }
            else
            {
              aRow.addCell (em ("Not an X509 certificate"));
              aRow.addCell ();
              aRow.addCell ();
              aRow.addCell ();
            }
          }
          else
          {
            aRow.addCell (em ("Unsupported type: " + ClassHelper.getClassLocalName (aEntry)));
            aRow.addCell ();
            aRow.addCell ();
            aRow.addCell ();
          }
        }
        catch (final GeneralSecurityException ex)
        {
          aRow.addCell (em ("password required?"));
          aRow.addCell ();
          aRow.addCell ();
          aRow.addCell ();
        }
      }
      aTarget.addChild (aTable).addChild (BootstrapDataTables.createDefaultDataTables (aWPEC, aTable));
    }
    catch (final GeneralSecurityException ex)
    {
      aTarget.addChild (error (div ("Error traversing trust store.")).addChild (BootstrapTechnicalUI.getTechnicalDetailsNode (ex,
                                                                                                                              aDisplayLocale)));
    }
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final ICommonsList <String> aPossiblePaths = new CommonsArrayList <> ();
    aPossiblePaths.add (SystemProperties.getPropertyValueOrNull ("javax.net.ssl.trustStore"));
    aPossiblePaths.add (SystemProperties.getJavaHome () + "/lib/security/cacerts");
    aPossiblePaths.add (SystemProperties.getJavaHome () + "/jre/lib/security/cacerts");

    final HCOL aOL = new HCOL ();
    int nFileIndex = 0;
    for (final String sPath : aPossiblePaths)
      if (StringHelper.isNotEmpty (sPath))
      {
        final File aFile = new File (sPath);
        final IHCLI <?> aLI = aOL.addAndReturnItem (div ("Checking file ").addChild (code (aFile.getAbsolutePath ())));
        if (aFile.exists ())
        {
          if (aFile.canRead ())
          {
            final LoadedKeyStore aLKS = KeyStoreHelper.loadKeyStore (EKeyStoreType.JKS,
                                                                     sPath,
                                                                     "changeit".toCharArray ());
            if (aLKS.isSuccess ())
            {
              final KeyStore aKS = aLKS.getKeyStore ();
              _showKeyStore (aWPEC, nFileIndex, aLI, aKS);
            }
            else
              aLI.addChild (error ("Failed to load the keystore - " + aLKS.getErrorText (aDisplayLocale)));
          }
          else
            aLI.addChild (error ("File exist but cannot be read"));
        }
        else
          aLI.addChild (badgeWarn ("File does not exist"));
        ++nFileIndex;
      }
    aNodeList.addChild (aOL);
  }
}
