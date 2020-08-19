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
package com.helger.photon.bootstrap4.pages.utils;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.network.port.ENetworkPortStatus;
import com.helger.network.port.NetworkPortHelper;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.button.BootstrapSubmitButton;
import com.helger.photon.bootstrap4.card.BootstrapCard;
import com.helger.photon.bootstrap4.card.BootstrapCardBody;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Page with the possibility to check if remote ports are open using the current
 * configuration or not.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageUtilsPortChecker <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayText
  {
    MSG_NOTE ("Hinweis: es können nur TCP-Ports geprüft werden.", "Note: only TCP ports can be checked."),
    MSG_HOSTNAME ("Hostname", "Hostname"),
    MSG_HOSTNAME_HELPTEXT ("Es kann ein Hostname oder eine IP-Adresse angegeben werden.", "A hostname or an IP address can be provided"),
    MSG_PORTS ("Port Nummer(n)", "Port(s)"),
    MSG_PORTS_HELPTEXT ("Mehrere Port-Nummern können durch Leerzeichen getrennt angegeben werden.",
                        "Multiple port numbers can be provided, separated by space characters."),
    MSG_BUTTON_CHECK ("Prüfe Ports", "Check ports"),
    MSG_ERROR_HOSTNAME_MISSING ("Es muss ein Hostname angegeben werden.", "The hostname to check is mandatory."),
    MSG_ERROR_PORT_MISSING ("Es muss mindestens eine Port-Nummer angegeben werden.", "At least one port number must be provided."),
    MSG_RESULT_HEADER ("Überprüfungsergebnisse", "Port check results"),
    MSG_RESULT_STATUS_PREFIX ("Status von ", "Status for ");

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

  private static final String FIELD_HOST = "host";
  private static final String FIELD_PORT = "port";

  public BasePageUtilsPortChecker (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_UTILS_PORT_CHECKER.getAsMLT ());
  }

  public BasePageUtilsPortChecker (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageUtilsPortChecker (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageUtilsPortChecker (@Nonnull @Nonempty final String sID,
                                   @Nonnull final IMultilingualText aName,
                                   @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  public void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (info (EText.MSG_NOTE.getDisplayText (aDisplayLocale)));

    final FormErrorList aFormErrors = new FormErrorList ();

    if (CPageParam.ACTION_PERFORM.equals (aWPEC.params ().getAsString (CPageParam.PARAM_ACTION)))
    {
      final String sHost = aWPEC.params ().getAsStringTrimmed (FIELD_HOST);
      final String sPorts = aWPEC.params ().getAsStringTrimmed (FIELD_PORT);
      final ICommonsList <Integer> aPorts = new CommonsArrayList <> ();
      if (sPorts != null)
        for (final String sPart : RegExHelper.getSplitToArray (sPorts, "\\s+"))
        {
          final int n = StringParser.parseInt (sPart, -1);
          if (n > 0)
            aPorts.add (Integer.valueOf (n));
        }

      if (StringHelper.hasNoText (sHost))
        aFormErrors.addFieldError (FIELD_HOST, EText.MSG_ERROR_HOSTNAME_MISSING.getDisplayText (aDisplayLocale));

      if (aPorts.isEmpty ())
        aFormErrors.addFieldError (FIELD_PORT, EText.MSG_ERROR_PORT_MISSING.getDisplayText (aDisplayLocale));

      if (aFormErrors.isEmpty ())
      {
        final BootstrapCard aResult = new BootstrapCard ();
        aResult.createAndAddHeader ().addChild (EText.MSG_RESULT_HEADER.getDisplayText (aDisplayLocale));
        final BootstrapCardBody aBody = aResult.createAndAddBody ();
        for (final Integer aPort : aPorts)
        {
          final ENetworkPortStatus eStatus = NetworkPortHelper.checkPortOpen (sHost, aPort.intValue (), 3_000);
          aBody.addChild (div ().addChild (EText.MSG_RESULT_STATUS_PREFIX.getDisplayText (aDisplayLocale))
                                .addChild (code (sHost + ":" + aPort))
                                .addChild (" = ")
                                .addChild (eStatus.isPortOpen () ? badgeSuccess (eStatus.toString ()) : badgeDanger (eStatus.toString ())));
        }
        aNodeList.addChild (aResult.addClass (CBootstrapCSS.MB_2));
      }
    }

    final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
    aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_HOSTNAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_HOST)))
                                                 .setHelpText (EText.MSG_HOSTNAME_HELPTEXT.getDisplayText (aDisplayLocale))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_HOST)));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.MSG_PORTS.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_PORT)))
                                                 .setHelpText (EText.MSG_PORTS_HELPTEXT.getDisplayText (aDisplayLocale))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_PORT)));
    aForm.addChild (new BootstrapSubmitButton ().addChild (EText.MSG_BUTTON_CHECK.getDisplayText (aDisplayLocale))
                                                .setIcon (EDefaultIcon.YES));
  }
}
