/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.textlevel.HCCode;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.network.port.ENetworkPortStatus;
import com.helger.network.port.NetworkPortHelper;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap4.badge.BootstrapBadge;
import com.helger.photon.bootstrap4.badge.EBootstrapBadgeType;
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
public class BasePageSysInfoPortChecker <WPECTYPE extends IWebPageExecutionContext> extends
                                        AbstractBootstrapWebPage <WPECTYPE>
{
  private static final String FIELD_HOST = "host";
  private static final String FIELD_PORT = "port";

  public BasePageSysInfoPortChecker (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SYSINFO_PORT_CHECKER.getAsMLT ());
  }

  public BasePageSysInfoPortChecker (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageSysInfoPortChecker (@Nonnull @Nonempty final String sID,
                                     @Nonnull final String sName,
                                     @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSysInfoPortChecker (@Nonnull @Nonempty final String sID,
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

    // TODO translate
    aNodeList.addChild (new BootstrapInfoBox ().addChild ("Only TCP ports can be checked"));

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
        aFormErrors.addFieldError (FIELD_HOST, "Please provide a host name to check!");

      if (aPorts.isEmpty ())
        aFormErrors.addFieldError (FIELD_PORT, "Please provide at least one port number!");

      if (aFormErrors.isEmpty ())
      {
        final BootstrapCard aResult = new BootstrapCard ();
        aResult.createAndAddHeader ().addChild ("Port check results");
        final BootstrapCardBody aBody = aResult.createAndAddBody ();
        for (final Integer aPort : aPorts)
        {
          final ENetworkPortStatus eStatus = NetworkPortHelper.checkPortOpen (sHost, aPort.intValue (), 3_000);
          aBody.addChild (new HCDiv ().addChild ("Status for ")
                                      .addChild (new HCCode ().addChild (sHost + ":" + aPort))
                                      .addChild (" = ")
                                      .addChild (new BootstrapBadge (eStatus.isPortOpen () ? EBootstrapBadgeType.SUCCESS
                                                                                           : EBootstrapBadgeType.DANGER).addChild (eStatus.toString ())));
        }
        aNodeList.addChild (aResult.addClass (CBootstrapCSS.MB_2));
      }
    }

    final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
    aForm.setEncTypeFileUpload ();
    aForm.addChild (new HCHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Hostname")
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_HOST)))
                                                 .setHelpText ("You can put host names or IP addresses here")
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_HOST)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Port(s)")
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_PORT)))
                                                 .setHelpText ("You can add multiple ports here, separated by spaces")
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_PORT)));
    aForm.addChild (new BootstrapSubmitButton ().addChild ("Check ports").setIcon (EDefaultIcon.YES));
  }
}
