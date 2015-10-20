package com.helger.photon.security.object.client;

import javax.annotation.Nonnull;

import com.helger.photon.basic.object.client.IClient;

/**
 * An empty implementation of {@link IClientModificationCallback}. Use this as
 * the base class for implementation so in case the interface is modified you do
 * not need to modify your code.
 *
 * @author Philip Helger
 */
public class DefaultClientModificationCallback implements IClientModificationCallback
{
  public void onClientCreated (@Nonnull final IClient aClient)
  {}

  public void onClientUpdated (@Nonnull final IClient aClient)
  {}

  public void onClientDeleted (@Nonnull final IClient aClient)
  {}
}
