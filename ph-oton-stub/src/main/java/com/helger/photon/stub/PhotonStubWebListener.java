package com.helger.photon.stub;

import javax.annotation.Nonnull;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PhotonStubWebListener implements ServletContextListener
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonStubWebListener.class);

  public void contextInitialized (@Nonnull final ServletContextEvent aSCE)
  {
    final ServletContext aSC = aSCE.getServletContext ();
    if (aSC.getMajorVersion () < 3)
      throw new IllegalStateException ("At least servlet version 3 is required!");

    s_aLogger.info ("Registering default ph-oton listeners and servlets");
  }

  public void contextDestroyed (@Nonnull final ServletContextEvent aSCE)
  {}
}
