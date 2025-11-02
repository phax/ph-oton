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
package com.helger.photon.uicore.page;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EValidity;
import com.helger.base.state.IValidityIndicator;
import com.helger.base.string.StringHelper;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.traits.IHCTrait;
import com.helger.photon.ajax.GlobalAjaxInvoker;
import com.helger.photon.ajax.decl.AjaxFunctionDeclaration;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.core.page.AbstractPage;
import com.helger.photon.uicore.css.CUICoreCSS;
import com.helger.text.IMultilingualText;

/**
 * Abstract base implementation for {@link IWebPage}.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public abstract class AbstractWebPage <WPECTYPE extends IWebPageExecutionContext> extends AbstractPage implements
                                      IWebPage <WPECTYPE>,
                                      IHCTrait
{
  // Commonly used CSS classes
  protected static final ICSSClassProvider CSS_CLASS_LEFT = CUICoreCSS.CSS_CLASS_LEFT;
  protected static final ICSSClassProvider CSS_CLASS_CENTER = CUICoreCSS.CSS_CLASS_CENTER;
  protected static final ICSSClassProvider CSS_CLASS_RIGHT = CUICoreCSS.CSS_CLASS_RIGHT;
  protected static final ICSSClassProvider CSS_CLASS_NOWRAP = CUICoreCSS.CSS_CLASS_NOWRAP;

  private IWebPageCSRFHandler m_aCSRFHandler = WebPageCSRFHandler.INSTANCE;

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param aName
   *        The name of the page. May not be <code>null</code>.
   * @param aDescription
   *        Optional description of the page. May be <code>null</code>.
   */
  public AbstractWebPage (@NonNull @Nonempty final String sID,
                          @NonNull final IMultilingualText aName,
                          @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @NonNull
  public final IWebPageCSRFHandler getCSRFHandler ()
  {
    return m_aCSRFHandler;
  }

  /**
   * Set the CSRF handler to be used.
   *
   * @param aCSRFHandler
   *        The new handler. May not be <code>null</code>.
   */
  public final void setCSRFHandler (@NonNull final IWebPageCSRFHandler aCSRFHandler)
  {
    m_aCSRFHandler = ValueEnforcer.notNull (aCSRFHandler, "CSRFHandler");
  }

  /**
   * Check some pre-requisites. This is called as the very first action on each
   * page view.
   *
   * @param aWPEC
   *        The web page execution context
   * @return Never <code>null</code>.
   */
  @OverrideOnDemand
  @NonNull
  protected IValidityIndicator isValidToDisplayPage (@NonNull final WPECTYPE aWPEC)
  {
    return EValidity.VALID;
  }

  /**
   * This method is called before the main
   * {@link #fillContent(IWebPageExecutionContext)} method is called.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void beforeFillContent (@NonNull final WPECTYPE aWPEC)
  {}

  /**
   * Abstract method to be implemented by subclasses, that creates the main page
   * content. This method is only called, when
   * {@link #isValidToDisplayPage(IWebPageExecutionContext)} returned
   * <code>true</code>.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  protected abstract void fillContent (@NonNull WPECTYPE aWPEC);

  /**
   * This method is called after the main
   * {@link #fillContent(IWebPageExecutionContext)} method is called.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void afterFillContent (@NonNull final WPECTYPE aWPEC)
  {}

  /**
   * A callback method that is is invoked if
   * {@link #isValidToDisplayPage(IWebPageExecutionContext)} returned
   * <code>false</code>.
   *
   * @param aWPEC
   *        The web page execution context. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onInvalidToDisplayPage (@NonNull final WPECTYPE aWPEC)
  {}

  /**
   * Default implementation calling the abstract fillContent method and creating
   * the help node if desired.
   */
  public final void getContent (@NonNull final WPECTYPE aWPEC)
  {
    if (isValidToDisplayPage (aWPEC).isValid ())
    {
      // "before"-callback
      beforeFillContent (aWPEC);

      // Create the main page content
      fillContent (aWPEC);

      // "after"-callback
      afterFillContent (aWPEC);
    }
    else
    {
      // Invalid to display page
      onInvalidToDisplayPage (aWPEC);
    }
  }

  /**
   * Add a per-page AJAX executor, with an automatically generated name. It is
   * automatically generated with the global AjaxInvoker.
   *
   * @param aExecutor
   *        The executor to be executed. May not be <code>null</code>.
   * @return The create {@link AjaxFunctionDeclaration} to be invoked.
   */
  @NonNull
  public static final AjaxFunctionDeclaration addAjax (@NonNull final IAjaxExecutor aExecutor)
  {
    return addAjax (null, aExecutor);
  }

  /**
   * Add a per-page AJAX executor, with an automatically generated name. It is
   * automatically generated with the global AjaxInvoker.
   *
   * @param sPrefix
   *        Function name prefix. If one is provided, this will be used as URL
   *        name so only limited chars are allowed.
   * @param aExecutor
   *        The executor to be executed. May not be <code>null</code>.
   * @return The create {@link AjaxFunctionDeclaration} to be invoked.
   */
  @NonNull
  public static final AjaxFunctionDeclaration addAjax (@Nullable final String sPrefix,
                                                       @NonNull final IAjaxExecutor aExecutor)
  {
    // null means "random" name
    final String sFuncName = StringHelper.isNotEmpty (sPrefix) ? sPrefix + AjaxFunctionDeclaration.getUniqueFunctionID ()
                                                            : null;
    final AjaxFunctionDeclaration aFunction = AjaxFunctionDeclaration.builder (sFuncName).executor (aExecutor).build ();
    GlobalAjaxInvoker.getInstance ().getRegistry ().registerFunction (aFunction);
    return aFunction;
  }
}
