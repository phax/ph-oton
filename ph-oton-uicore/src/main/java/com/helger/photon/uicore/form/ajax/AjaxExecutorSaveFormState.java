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
package com.helger.photon.uicore.form.ajax;

import java.util.Map;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.attr.AttributeContainerAny;
import com.helger.commons.string.StringHelper;
import com.helger.photon.core.ajax.executor.AbstractAjaxExecutor;
import com.helger.photon.core.ajax.response.AjaxJsonResponse;
import com.helger.photon.core.form.FormState;
import com.helger.photon.core.form.FormStateManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class AjaxExecutorSaveFormState extends AbstractAjaxExecutor
{
  /** Special field for the flow ID */
  public static final String FIELD_FLOW_ID = CPageParam.FIELD_ATTRIBUTE_PREFIX_INTERNAL + "flowid";
  /** Special field to restore a flow ID */
  public static final String FIELD_RESTORE_FLOW_ID = CPageParam.FIELD_ATTRIBUTE_PREFIX_INTERNAL + "restoreflowid";
  /** Special prefix for field names */
  public static final String PREFIX_FIELD = "field-";
  // Same as in form.js!
  private static final String ATTR_PAGE_ID = "$pageID";

  @OverrideOnDemand
  protected void saveFormState (@Nonnull final String sPageID,
                                @Nonnull final String sFlowID,
                                @Nonnull final AttributeContainerAny <String> aFieldCont)
  {
    FormStateManager.getInstance ().saveFormState (new FormState (sPageID, sFlowID, aFieldCont));
  }

  @Override
  @Nonnull
  protected AjaxJsonResponse mainHandleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope) throws Exception
  {
    // Extract page ID
    final String sPageID = aRequestScope.attrs ().getAsString (ATTR_PAGE_ID);
    if (sPageID == null)
      return AjaxJsonResponse.createError ("Page ID is missing!");

    // Filter all params
    final AttributeContainerAny <String> aFieldCont = new AttributeContainerAny <> ();
    for (final Map.Entry <String, Object> aEntry : aRequestScope.params ().entrySet ())
      if (aEntry.getKey ().startsWith (PREFIX_FIELD))
      {
        // Skip the prefix
        final String sFieldName = aEntry.getKey ().substring (PREFIX_FIELD.length ());
        // Array value are suffixes with "[]" which is important, as they must
        // be restored as array values!
        // This affects checkboxes, radio buttons and multi selects
        if (StringHelper.hasText (sFieldName))
          aFieldCont.putIn (sFieldName, aEntry.getValue ());
      }

    // Extract the flow ID
    final String sFlowID = aFieldCont.getAsString (FIELD_FLOW_ID);
    if (sFlowID == null)
      return AjaxJsonResponse.createError ("Flow ID is missing!");

    aFieldCont.removeObject (FIELD_FLOW_ID);
    aFieldCont.removeObject (CPageParam.PARAM_SUBACTION);
    // Leave action and object

    saveFormState (sPageID, sFlowID, aFieldCont);
    return AjaxJsonResponse.createSuccess ();
  }
}
