/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.typeahead;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.AbstractHCInput;
import com.helger.html.hc.html.forms.EHCAutoComplete;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQuerySelector;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSParam;
import com.helger.html.request.IHCRequestField;

/**
 * This class encapsulates a HTML input field, a hidden field for the ID and the
 * script to execute the typeahead action
 *
 * @author Philip Helger
 */
public class TypeaheadEdit extends AbstractHCInput <TypeaheadEdit>
{
  public static final String JSON_ID = TypeaheadDatum.JSON_ID;

  private final IHCRequestField m_aRFHidden;
  private final String m_sHiddenFieldID;
  private final JSAnonymousFunction m_aSelectionCallback;
  private final HCTypeahead m_aScript;

  public TypeaheadEdit (@Nonnull final IHCRequestField aRFEdit,
                        @Nonnull final IHCRequestField aRFHidden,
                        @Nonnull final ISimpleURL aAjaxInvocationURL,
                        @Nonnull final Locale aDisplayLocale)
  {
    super (EHCInputType.TEXT);

    ValueEnforcer.notNull (aRFEdit, "RequestFieldEdit");
    ValueEnforcer.notNull (aRFHidden, "RequestFieldHidden");
    ValueEnforcer.notNull (aAjaxInvocationURL, "AjaxInvocationURL");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");

    setName (aRFEdit.getFieldName ());
    setValue (aRFEdit.getRequestValue ());
    setAutoComplete (EHCAutoComplete.OFF);
    setPlaceholder (ETypeaheadText.ENTER_SEARCH_STRING.getDisplayText (aDisplayLocale));

    m_aRFHidden = aRFHidden;
    m_sHiddenFieldID = GlobalIDFactory.getNewStringID ();

    // Callback has 3 params:
    // 1. event
    // 2. selected datum
    // 3. selected dataset
    m_aSelectionCallback = new JSAnonymousFunction ();
    m_aSelectionCallback.param ("evt");
    final JSParam aJSDatum = m_aSelectionCallback.param ("datum");
    m_aSelectionCallback.param ("dsname");
    // Need to manually call the "change" handler, because otherwise onchange
    // event is not triggered for hidden fields!
    m_aSelectionCallback.body ().add (JQuery.idRef (m_sHiddenFieldID).val (aJSDatum.ref (JSON_ID)).change ());

    final SimpleURL aRealURL = new SimpleURL (aAjaxInvocationURL).add (AbstractAjaxExecutorTypeaheadFinder.PARAM_QUERY,
                                                                       TypeaheadRemote.DEFAULT_WILDCARD);
    final TypeaheadRemote aRemote = new TypeaheadRemote (aRealURL).setCache (false);
    // Ensure unique dataset name
    final TypeaheadDataset aDS = new TypeaheadDataset (m_sHiddenFieldID).setRemote (aRemote);
    m_aScript = new HCTypeahead (JQuerySelector.id (this)).addDataset (aDS).setOnSelected (m_aSelectionCallback);
  }

  /**
   * @return The ID of the hidden field that is generated for this edit. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public final String getHiddenFieldID ()
  {
    return m_sHiddenFieldID;
  }

  /**
   * @return The JS callback function that is invoked, when an item is selected.
   *         Never <code>null</code>. Do not modify the original body content,
   *         as this is required to be present for the correct working of this
   *         control!
   */
  @Nonnull
  public final JSAnonymousFunction getJSSelectionCallback ()
  {
    return m_aSelectionCallback;
  }

  /**
   * @return The type ahead script that handles the AJAX trigger based on the
   *         input. Never <code>null</code>. Changes on the script only have an
   *         effect if they are performed before this control is build!
   */
  @Nonnull
  public final HCTypeahead getScript ()
  {
    return m_aScript;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // HiddenField
    aTargetNode.addChild (new HCHiddenField (m_aRFHidden).setID (m_sHiddenFieldID));

    // JS code
    aTargetNode.addChild (m_aScript);
  }
}
