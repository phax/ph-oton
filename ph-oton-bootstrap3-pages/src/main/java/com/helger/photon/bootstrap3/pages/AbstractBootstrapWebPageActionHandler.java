package com.helger.photon.bootstrap3.pages;

import com.helger.commons.id.IHasID;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.uicore.page.AbstractWebPageActionHandler;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractBootstrapWebPageActionHandler <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                                            extends
                                                            AbstractWebPageActionHandler <DATATYPE, WPECTYPE, BootstrapForm, BootstrapButtonToolbar>
{
  public AbstractBootstrapWebPageActionHandler (final boolean bSelectedObjectRequired)
  {
    super (bSelectedObjectRequired, BootstrapWebPageUIHandler.INSTANCE);
  }
}
