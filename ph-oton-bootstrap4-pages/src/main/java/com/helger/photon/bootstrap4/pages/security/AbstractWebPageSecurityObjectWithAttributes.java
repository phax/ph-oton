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
package com.helger.photon.bootstrap4.pages.security;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.id.IHasID;
import com.helger.commons.text.IMultilingualText;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapViewForm;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractWebPageSecurityObjectWithAttributes <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                                                  extends
                                                                  AbstractBootstrapWebPageForm <DATATYPE, WPECTYPE>
{
  private void _initSowa ()
  {
    setObjectLockingEnabled (true);
  }

  public AbstractWebPageSecurityObjectWithAttributes (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
    _initSowa ();
  }

  public AbstractWebPageSecurityObjectWithAttributes (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    super (sID, aName);
    _initSowa ();
  }

  public AbstractWebPageSecurityObjectWithAttributes (@Nonnull @Nonempty final String sID,
                                                      @Nonnull final String sName,
                                                      @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
    _initSowa ();
  }

  public AbstractWebPageSecurityObjectWithAttributes (@Nonnull @Nonempty final String sID,
                                                      @Nonnull final IMultilingualText aName,
                                                      @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
    _initSowa ();
  }

  /**
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aForm
   *        Table to be filled. Never <code>null</code>.
   * @param aSelectedObject
   *        Current object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onShowSelectedObjectTableStart (@Nonnull final WPECTYPE aWPEC,
                                                 @Nonnull final BootstrapViewForm aForm,
                                                 @Nonnull final DATATYPE aSelectedObject)
  {}

  /**
   * Callback for manually extracting custom attributes. This method is called
   * independently if custom attributes are present or not.
   *
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The object currently shown. Never <code>null</code>.
   * @param aCustomAttrs
   *        The available custom attributes. Never <code>null</code> but maybe
   *        empty.
   * @param aViewForm
   *        The table to be add custom information
   * @return A set of all attribute names that were handled in this method or
   *         <code>null</code>. All attributes handled in this method will not
   *         be displayed generically.
   */
  @Nullable
  @OverrideOnDemand
  protected ICommonsSet <String> onShowSelectedObjectCustomAttrs (@Nonnull final WPECTYPE aWPEC,
                                                                  @Nonnull final DATATYPE aSelectedObject,
                                                                  @Nonnull final Map <String, String> aCustomAttrs,
                                                                  @Nonnull final BootstrapViewForm aViewForm)
  {
    return null;
  }

  /**
   * @param aWPEC
   *        The current web page execution context. Never <code>null</code>.
   * @param aForm
   *        Table to be filled.
   * @param aSelectedObject
   *        Current object. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onShowSelectedObjectTableEnd (@Nonnull final WPECTYPE aWPEC,
                                               @Nonnull final BootstrapViewForm aForm,
                                               @Nonnull final DATATYPE aSelectedObject)
  {}

  /**
   * Validate custom data of the input field.
   *
   * @param aWPEC
   *        Current web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The selected object. May be <code>null</code>.
   * @param aFormErrors
   *        The form errors to be filled. Never <code>null</code>.
   * @param eFormAction
   *        The form action mode. Either create, copy or edit.
   * @return The custom parameter to be added to the used upon success. If an
   *         error occurred, this map may be <code>null</code>.
   */
  @OverrideOnDemand
  @Nullable
  protected ICommonsMap <String, String> validateCustomInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                                        @Nullable final DATATYPE aSelectedObject,
                                                                        @Nonnull final FormErrorList aFormErrors,
                                                                        @Nonnull final EWebPageFormAction eFormAction)
  {
    return null;
  }

  /**
   * Add details after the regular show form.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        The currently selected object. May be <code>null</code> for newly
   *        created objects.
   * @param aForm
   *        The parent form. Use this as parent and not the node list from the
   *        web page execution context! Never <code>null</code>.
   * @param eFormAction
   *        The form action in use. Either create, copy or edit.
   * @param aFormErrors
   *        Previous errors from validation. Never <code>null</code> but maybe
   *        empty.
   */
  @OverrideOnDemand
  protected void onShowInputFormEnd (@Nonnull final WPECTYPE aWPEC,
                                     @Nullable final DATATYPE aSelectedObject,
                                     @Nonnull final BootstrapForm aForm,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nonnull final FormErrorList aFormErrors)
  {}
}
