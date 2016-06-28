/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page.handler;

import java.util.function.Function;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uicore.page.IWebPageFormUIHandler;

public abstract class AbstractWebPageActionHandlerMulti <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                        implements IWebPageActionHandlerMulti <DATATYPE, WPECTYPE>
{
  private final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> m_aUIHandler;
  private final String m_sFieldName;
  private final Function <String, DATATYPE> m_aResolver;

  protected AbstractWebPageActionHandlerMulti (@Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler,
                                               @Nonnull @Nonempty final String sFieldName,
                                               @Nonnull final Function <String, DATATYPE> aResolver)
  {
    m_aUIHandler = ValueEnforcer.notNull (aUIHandler, "UIHandler");
    m_sFieldName = ValueEnforcer.notEmpty (sFieldName, "FieldName");
    m_aResolver = ValueEnforcer.notNull (aResolver, "Resolver");
  }

  @Nonnull
  @Nonempty
  protected final String getFieldName ()
  {
    return m_sFieldName;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <DATATYPE> getAllSelectedObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final ICommonsList <DATATYPE> ret = new CommonsArrayList<> ();
    final ICommonsList <String> aIDs = aWPEC.getAttributeAsList (m_sFieldName);
    if (aIDs != null)
      for (final String sID : aIDs)
        ret.addIfNotNull (m_aResolver.apply (sID));
    return ret;
  }

  public final boolean isSelectedObjectRequired ()
  {
    return false;
  }

  @Nonnull
  protected final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> getUIHandler ()
  {
    return m_aUIHandler;
  }

  public final boolean handleAction (@Nonnull final WPECTYPE aWPEC, final DATATYPE aSelectedObject)
  {
    final ICommonsList <DATATYPE> aSelectedObjects = getAllSelectedObjects (aWPEC);
    return handleMultiAction (aWPEC, aSelectedObjects);
  }
}