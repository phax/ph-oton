package com.helger.html.hc.html.script;

import javax.annotation.Nonnull;

/**
 * Simple way to define the script loading mode.
 *
 * @author Philip Helger
 */
public enum EHCScriptLoadingMode
{
  DEFAULT
  {
    @Override
    public void apply (@Nonnull final HCScriptFile aScript)
    {
      aScript.setAsync (false);
      aScript.setDefer (false);
    }
  },
  ASYNC
  {
    @Override
    public void apply (@Nonnull final HCScriptFile aScript)
    {
      aScript.setAsync (true);
      aScript.setDefer (false);
    }
  },
  DEFER
  {
    @Override
    public void apply (@Nonnull final HCScriptFile aScript)
    {
      aScript.setAsync (false);
      aScript.setDefer (true);
    }
  };

  public abstract void apply (@Nonnull HCScriptFile aScript);
}
