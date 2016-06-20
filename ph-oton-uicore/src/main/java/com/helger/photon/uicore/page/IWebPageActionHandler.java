package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;

/**
 * Interface for handling delete actions inside an {@link AbstractWebPageForm}.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type of the object to be handled.
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public interface IWebPageActionHandler <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
{
  /**
   * @return The name of the action this handler can handle.
   */
  @Nonnull
  @Nonempty
  String getActionName ();

  /**
   * @return <code>true</code> if this action can only be executed when an
   *         object is selected, <code>false</code> otherwise.
   */
  boolean isSelectedObjectRequired ();

  /**
   * This is the main entry to action handling. This method is only called if
   * the passed action is provided and if the preconditions are met.
   *
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param aSelectedObject
   *        Currently selected object. Never <code>null</code>.
   * @return <code>true</code> to show the list of all objects afterwards,
   *         <code>false</code> to not do so.
   */
  boolean handleAction (@Nonnull WPECTYPE aWPEC, @Nonnull DATATYPE aSelectedObject);
}
