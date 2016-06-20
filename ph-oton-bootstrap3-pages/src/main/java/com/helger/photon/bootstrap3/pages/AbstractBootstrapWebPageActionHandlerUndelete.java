package com.helger.photon.bootstrap3.pages;

import com.helger.commons.id.IHasID;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.uicore.page.AbstractWebPageActionHandlerUndelete;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractBootstrapWebPageActionHandlerUndelete <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                                                    extends
                                                                    AbstractWebPageActionHandlerUndelete <DATATYPE, WPECTYPE, BootstrapForm, BootstrapButtonToolbar>
{
  public AbstractBootstrapWebPageActionHandlerUndelete ()
  {
    super (BootstrapWebPageUIHandler.INSTANCE);
  }
}
