package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;

import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;

/**
 * Interface for handling delete actions inside an {@link AbstractWebPageForm}.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type of the object to be handled.
 * @param <WPECTYPE>
 *        Web page execution context type
 * @param <FORM_TYPE>
 *        The form implementation type.
 * @param <TOOLBAR_TYPE>
 *        The form implementation type.
 */
public interface IWebPageDeleteHandler <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
{
  /**
   * This is the main entry to deletion handling.
   *
   * @param aWPEC
   *        Web page execution context
   * @param aSelectedObject
   *        Currently selected object
   * @return <code>true</code> to show the list of all objects,
   *         <code>false</code> to not do so.
   */
  boolean handleDelete (@Nonnull WPECTYPE aWPEC, @Nonnull DATATYPE aSelectedObject);
}
