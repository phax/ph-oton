/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.markdown;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

public abstract class AbstractMarkdownPlugin
{
  protected String m_sPluginID;

  public AbstractMarkdownPlugin (@Nonnull final String sPluginID)
  {
    m_sPluginID = sPluginID;
  }

  @Nonnull
  public String getPluginID ()
  {
    return m_sPluginID;
  }

  public abstract void emit (final MarkdownHCStack out, final List <String> lines, final Map <String, String> params);
}
