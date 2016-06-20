package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;

public abstract class AbstractWebPageActionHandler <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                   implements IWebPageActionHandler <DATATYPE, WPECTYPE>
{
  private final String m_sActionName;
  private final boolean m_bSelectedObjectRequired;
  private final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> m_aUIHandler;

  protected AbstractWebPageActionHandler (@Nonnull @Nonempty final String sActionName,
                                          final boolean bSelectedObjectRequired,
                                          @Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler)
  {
    m_sActionName = ValueEnforcer.notEmpty (sActionName, "ActionName");
    m_bSelectedObjectRequired = bSelectedObjectRequired;
    m_aUIHandler = ValueEnforcer.notNull (aUIHandler, "UIHandler");
  }

  @Nonnull
  @Nonempty
  public final String getActionName ()
  {
    return m_sActionName;
  }

  public final boolean isSelectedObjectRequired ()
  {
    return m_bSelectedObjectRequired;
  }

  @Nonnull
  protected final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> getUIHandler ()
  {
    return m_aUIHandler;
  }
}
