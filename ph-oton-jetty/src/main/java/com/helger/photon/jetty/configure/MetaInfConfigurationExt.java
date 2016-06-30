package com.helger.photon.jetty.configure;

import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Work around for https://github.com/eclipse/jetty.project/issues/680
 */
public class MetaInfConfigurationExt extends MetaInfConfiguration
{
  @Override
  public void preConfigure (final WebAppContext context) throws Exception
  {
    super.preConfigure (context);
    scanJars (context, context.getMetaData ().getWebInfClassesDirs (), false);
  }
}
