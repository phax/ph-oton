package com.helger.photon.bootstrap3.pages;

import com.helger.commons.id.IHasID;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.uicore.page.AbstractWebPageActionHandlerDelete;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractBootstrapWebPageActionHandlerDelete <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                                                  extends
                                                                  AbstractWebPageActionHandlerDelete <DATATYPE, WPECTYPE, BootstrapForm, BootstrapButtonToolbar>
{
  public AbstractBootstrapWebPageActionHandlerDelete ()
  {
    super (BootstrapWebPageUIHandler.INSTANCE);
  }
}
