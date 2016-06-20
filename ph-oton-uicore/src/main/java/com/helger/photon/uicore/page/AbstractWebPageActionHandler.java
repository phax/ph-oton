package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;

public abstract class AbstractWebPageActionHandler <WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
{
  /**
   * @param aLEC
   *        Layout execution context
   * @return A form that links to the current page.
   */
  @Nonnull
  protected abstract FORM_TYPE createFormSelf (@Nonnull final ILayoutExecutionContext aLEC);

  /**
   * @param aLEC
   *        Layout execution context
   * @return A file upload form that links to the current page.
   */
  @Nonnull
  protected abstract FORM_TYPE createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC);

  @Nonnull
  protected abstract TOOLBAR_TYPE createToolbar (@Nonnull WPECTYPE aWPEC);

  @Nullable
  protected abstract IHCNode createErrorBox (@Nonnull WPECTYPE aWPEC, @Nullable String sErrorMsg);

  @Nullable
  protected abstract IHCNode createIncorrectInputBox (@Nonnull WPECTYPE aWPEC);
}
