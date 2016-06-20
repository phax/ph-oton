package com.helger.photon.bootstrap3.pages;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.id.IHasID;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.page.AbstractWebPageActionHandlerUndelete;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractBootstrapWebPageActionHandlerUndelete <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                                                    extends
                                                                    AbstractWebPageActionHandlerUndelete <DATATYPE, WPECTYPE, BootstrapForm, BootstrapButtonToolbar>
{
  public AbstractBootstrapWebPageActionHandlerUndelete ()
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
  protected final BootstrapButtonToolbar createToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return new BootstrapButtonToolbar (aWPEC);
  }

  @Override
  @Nonnull
  protected final BootstrapErrorBox createErrorBox (@Nonnull final WPECTYPE aWPEC, @Nullable final String sErrorMsg)
  {
    return BootstrapUI.createErrorBox (sErrorMsg);
  }

  @Override
  @Nonnull
  protected final BootstrapErrorBox createIncorrectInputBox (@Nonnull final WPECTYPE aWPEC)
  {
    return BootstrapUI.createIncorrectInputBox (aWPEC);
  }
}
