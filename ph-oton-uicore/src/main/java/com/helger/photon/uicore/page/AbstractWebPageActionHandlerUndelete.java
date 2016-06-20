package com.helger.photon.uicore.page;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

public abstract class AbstractWebPageActionHandlerUndelete <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                    extends
                                                    AbstractWebPageActionHandler <WPECTYPE, FORM_TYPE, TOOLBAR_TYPE>
                                                    implements IWebPageActionHandler <DATATYPE, WPECTYPE>
{
  public static final String FORM_ID_UNDELETE = "undeleteform";

  public AbstractWebPageActionHandlerUndelete ()
  {}

  @Nonnull
  @Nonempty
  public final String getActionName ()
  {
    return CPageParam.ACTION_UNDELETE;
  }

  /**
   * Show the undelete query.
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        The object to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void showUndeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final FORM_TYPE aForm,
                                    @Nonnull final DATATYPE aSelectedObject)
  {}

  /**
   * Perform object undelete
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The object to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void performUndelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {}

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object. Never <code>null</code>.
   * @return <code>true</code> to show the delete toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showUndeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    return true;
  }

  /**
   * Add additional elements to the undelete toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyUndeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  @Nullable
  @OverrideOnDemand
  protected String getUndeleteToolbarSubmitButtonText (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected IIcon getUndeleteToolbarSubmitButtonIcon ()
  {
    return EDefaultIcon.YES;
  }

  /**
   * Create toolbar for undeleting an existing object
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        Selected object. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createUndeleteToolbar (@Nonnull final WPECTYPE aWPEC,
                                                @Nonnull final FORM_TYPE aForm,
                                                @Nonnull final DATATYPE aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = createToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_UNDELETE);
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
    // Yes button
    aToolbar.addSubmitButton (getUndeleteToolbarSubmitButtonText (aDisplayLocale),
                              getUndeleteToolbarSubmitButtonIcon ());
    // No button
    aToolbar.addButtonNo (aDisplayLocale);

    // Callback
    modifyUndeleteToolbar (aWPEC, aToolbar);
    return aToolbar;
  }

  public final boolean handleAction (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    final boolean bIsFormSubmitted = aWPEC.hasSubAction (CPageParam.ACTION_SAVE);
    final IWebPageCSRFHandler aCSRFHandler = aWPEC.getWebPage ().getCSRFHandler ();
    boolean bShowList;

    if (bIsFormSubmitted)
    {
      // Check if the nonce matches
      if (aCSRFHandler.checkCSRFNonce (aWPEC).isContinue ())
      {
        performUndelete (aWPEC, aSelectedObject);
      }

      bShowList = true;
    }
    else
    {
      final FORM_TYPE aForm = createFormSelf (aWPEC);
      aWPEC.getNodeList ().addChild (aForm);

      // Set unique ID
      aForm.setID (FORM_ID_UNDELETE);

      // Add the nonce for CSRF check
      aForm.addChild (aCSRFHandler.createCSRFNonceField ());

      // SHow the main query
      showUndeleteQuery (aWPEC, aForm, aSelectedObject);

      // Show the toolbar?
      if (showUndeleteToolbar (aWPEC, aSelectedObject))
        aForm.addChild (createUndeleteToolbar (aWPEC, aForm, aSelectedObject));
      bShowList = false;
    }

    return bShowList;
  }
}
