package com.helger.photon.bootstrap3.pages;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.EBootstrapFormType;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.page.IWebPageFormUIHandler;

public class BootstrapWebPageUIHandler implements IWebPageFormUIHandler <BootstrapForm, BootstrapButtonToolbar>
{
  public static final BootstrapWebPageUIHandler INSTANCE = new BootstrapWebPageUIHandler ();

  protected BootstrapWebPageUIHandler ()
  {}

  @Override
  @Nonnull
  public BootstrapForm createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new BootstrapForm (EBootstrapFormType.HORIZONTAL).setAction (aLEC.getSelfHref ());
  }

  @Override
  @Nonnull
  public BootstrapForm createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createFormSelf (aLEC).setEncTypeFileUpload ();
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
    return new BootstrapErrorBox ().addChild (sErrorMsg);
  }

  @Override
  @Nonnull
  public final BootstrapErrorBox createIncorrectInputBox (@Nonnull final ILayoutExecutionContext aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return new BootstrapErrorBox ().addChild (EPhotonCoreText.ERR_INCORRECT_INPUT.getDisplayText (aDisplayLocale));
  }
}
