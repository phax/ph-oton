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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.attr.AttributeContainerAny;
import com.helger.commons.string.StringHelper;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.photon.core.ajax.executor.IAjaxExecutor;
import com.helger.photon.core.form.FormState;
import com.helger.photon.core.form.FormStateManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class AjaxExecutorSaveFormState implements IAjaxExecutor
{
  /** Special field for the flow ID */
  public static final String FIELD_FLOW_ID = CPageParam.FIELD_ATTRIBUTE_PREFIX_INTERNAL + "flowid";
  /** Special field to restore a flow ID */
  public static final String FIELD_RESTORE_FLOW_ID = CPageParam.FIELD_ATTRIBUTE_PREFIX_INTERNAL + "restoreflowid";
  /** Special prefix for field names */
  public static final String PREFIX_FIELD = "field-";
  // Same as in form.js!
  private static final String ATTR_PAGE_ID = "$pageID";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AjaxExecutorSaveFormState.class);

  @OverrideOnDemand
  protected void saveFormState (@Nonnull final String sPageID,
                                @Nonnull final String sFlowID,
                                @Nonnull final AttributeContainerAny <String> aFieldCont)
  {
    FormStateManager.getInstance ().saveFormState (new FormState (sPageID, sFlowID, aFieldCont));
  }

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    // Extract page ID
    final String sPageID = aRequestScope.attrs ().getAsString (ATTR_PAGE_ID);
    if (sPageID == null)
    {
      s_aLogger.error ("Page ID '" + sPageID + "' not found!");
      aAjaxResponse.createNotFound ();
      return;
    }

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
    {
      s_aLogger.error ("Flow ID '" + sFlowID + "' not found!");
      aAjaxResponse.createNotFound ();
      return;
    }

    aFieldCont.removeObject (FIELD_FLOW_ID);
    aFieldCont.removeObject (CPageParam.PARAM_SUBACTION);
    // Leave action and object

    saveFormState (sPageID, sFlowID, aFieldCont);
    aAjaxResponse.jsonEmpty ();
  }
}
