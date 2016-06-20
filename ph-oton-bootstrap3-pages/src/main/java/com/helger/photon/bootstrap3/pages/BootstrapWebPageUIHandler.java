package com.helger.photon.bootstrap3.pages;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.page.IWebPageUIHandler;

public class BootstrapWebPageUIHandler implements IWebPageUIHandler <BootstrapForm, BootstrapButtonToolbar>
{
  public static final BootstrapWebPageUIHandler INSTANCE = new BootstrapWebPageUIHandler ();

  protected BootstrapWebPageUIHandler ()
  {}

  @Override
  @Nonnull
  public BootstrapForm createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return BootstrapUI.createFormSelf (aLEC);
  }

  @Override
  @Nonnull
  public BootstrapForm createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return BootstrapUI.createFormFileUploadSelf (aLEC);
  }

  @Override
  @Nonnull
  public final BootstrapButtonToolbar createToolbar (@Nonnull final ILayoutExecutionContext aWPEC)
  {
    return new BootstrapButtonToolbar (aWPEC);
  }

  @Override
  @Nonnull
  public final BootstrapErrorBox createErrorBox (@Nonnull final ILayoutExecutionContext aWPEC,
                                                 @Nullable final String sErrorMsg)
  {
    return BootstrapUI.createErrorBox (sErrorMsg);
  }

  @Override
  @Nonnull
  public final BootstrapErrorBox createIncorrectInputBox (@Nonnull final ILayoutExecutionContext aWPEC)
  {
    return BootstrapUI.createIncorrectInputBox (aWPEC);
  }

}
