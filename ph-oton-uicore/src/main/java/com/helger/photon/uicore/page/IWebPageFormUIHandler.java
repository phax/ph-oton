package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;

public interface IWebPageFormUIHandler <FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                       extends IWebPageUIHandler
{
  /**
   * Create a new in-page header
   * 
   * @param sHeaderText
   *        The header text to be displayed. May be <code>null</code> or empty.
   * @return <code>null</code> if the passed header text is <code>null</code> or
   *         empty, a non-<code>null</code> node otherwise.
   */
  @Nullable
  IHCNode createPageHeader (@Nullable String sHeaderText);

  /**
   * @param aLEC
   *        Layout execution context
   * @return A form that links to the current page.
   */
  @Nonnull
  FORM_TYPE createFormSelf (@Nonnull ILayoutExecutionContext aLEC);

  /**
   * @param aLEC
   *        Layout execution context
   * @return A file upload form that links to the current page.
   */
  @Nonnull
  FORM_TYPE createFormFileUploadSelf (@Nonnull ILayoutExecutionContext aLEC);

  @Nonnull
  TOOLBAR_TYPE createToolbar (@Nonnull ILayoutExecutionContext aWPEC);

  @Nullable
  IHCNode createErrorBox (@Nonnull ILayoutExecutionContext aWPEC, @Nullable String sErrorMsg);

  @Nullable
  IHCNode createIncorrectInputBox (@Nonnull ILayoutExecutionContext aWPEC);
}
