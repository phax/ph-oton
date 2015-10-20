package com.helger.photon.security.object.accarea;

import javax.annotation.Nonnull;

import com.helger.photon.basic.object.accarea.IAccountingArea;

/**
 * An empty implementation of {@link IAccountingAreaModificationCallback}. Use
 * this as the base class for implementation so in case the interface is
 * modified you do not need to modify your code.
 *
 * @author Philip Helger
 */
public class DefaultAccountingAreaModificationCallback implements IAccountingAreaModificationCallback
{
  public void onAccountingAreaCreated (@Nonnull final IAccountingArea aAccountingArea)
  {}

  public void onAccountingAreaUpdated (@Nonnull final IAccountingArea aAccountingArea)
  {}

  public void onAccountingAreaDeleted (@Nonnull final IAccountingArea aAccountingArea)
  {}
}
