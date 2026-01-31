/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.html.hc.html.script;

import org.jspecify.annotations.NonNull;

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
    public void apply (@NonNull final HCScriptFile aScript)
    {
      aScript.setAsync (false);
      aScript.setDefer (false);
    }
  },
  ASYNC
  {
    @Override
    public void apply (@NonNull final HCScriptFile aScript)
    {
      aScript.setAsync (true);
      aScript.setDefer (false);
    }
  },
  DEFER
  {
    @Override
    public void apply (@NonNull final HCScriptFile aScript)
    {
      aScript.setAsync (false);
      aScript.setDefer (true);
    }
  };

  public abstract void apply (@NonNull HCScriptFile aScript);
}
