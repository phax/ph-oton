package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;

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
   * @return <code>true</code> if this action can only be executed when an
   *         object is selected, <code>false</code> otherwise.
   */
  boolean isSelectedObjectRequired ();

  /**
   * Check if the action handler can be executed on the provided object.
   *
   * @param aSelectedObject
   *        The selected object. May be <code>null</code> if
   *        {@link #isSelectedObjectRequired()} is <code>false</code>.
   * @return <code>true</code> if
   *         {@link #handleAction(IWebPageExecutionContext, IHasID)} can be
   *         called on the provided object, <code>false</code> otherwise.
   */
  default boolean canHandleAction (final DATATYPE aSelectedObject)
  {
    return true;
  }

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
  boolean handleAction (@Nonnull WPECTYPE aWPEC, DATATYPE aSelectedObject);
}
