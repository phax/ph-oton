/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap.demo.app.ui;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.name.IHasDisplayName;
import com.helger.html.hc.IHCNode;
import com.helger.html.jscode.JSArray;
import com.helger.photon.bootstrap.demo.secure.ajax.CAjaxSecure;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.core.app.layout.CLayout;
import com.helger.photon.uicore.form.ajax.AjaxExecutorSaveFormState;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.js.JSFormHelper;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public abstract class AbstractAppWebPageForm <DATATYPE extends IHasID <String>>
                                             extends AbstractBootstrapWebPageForm <DATATYPE, WebPageExecutionContext>
{
  public AbstractAppWebPageForm (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  @Override
  @Nullable
  public String getHeaderText (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final DATATYPE aSelectedObject = getSelectedObject (aWPEC, getSelectedObjectID (aWPEC));
    if (aSelectedObject instanceof IHasDisplayName)
      return ((IHasDisplayName) aSelectedObject).getDisplayName ();
    return super.getHeaderText (aWPEC);
  }

  @Override
  protected void modifyCreateToolbar (@Nonnull final WebPageExecutionContext aWPEC,
                                      @Nonnull final BootstrapButtonToolbar aToolbar)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();
    final JSArray aSuccessUpdates = new JSArray ();
    // Update menu via Ajax
    aSuccessUpdates.add (JSFormHelper.createUpdateParam (aRequestScope,
                                                         CLayout.LAYOUT_AREAID_MENU,
                                                         CAjaxSecure.UPDATE_MENU_VIEW));

    // Update special area directly with code
    IHCNode aSpecialNode = new BootstrapInfoBox ().addChild ("Data was successfully saved!");
    aSuccessUpdates.add (JSFormHelper.createUpdateParam (CLayout.LAYOUT_AREAID_SPECIAL, aSpecialNode));
    final JSArray aFailureUpdates = new JSArray ();
    // Update special area directly with code
    aSpecialNode = new BootstrapErrorBox ().addChild ("Error saving the data!");
    aFailureUpdates.add (JSFormHelper.createUpdateParam (CLayout.LAYOUT_AREAID_SPECIAL, aSpecialNode));
    aToolbar.addButton ("Remember",
                        JSFormHelper.saveFormData (aRequestScope,
                                                   FORM_ID_INPUT,
                                                   AjaxExecutorSaveFormState.PREFIX_FIELD,
                                                   getID (),
                                                   CAjaxSecure.SAVE_FORM_STATE,
                                                   aSuccessUpdates,
                                                   aFailureUpdates),
                        EDefaultIcon.SAVE);
  }
}