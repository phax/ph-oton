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
package com.helger.photon.markdown;

import org.jspecify.annotations.NonNull;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.render.HCRenderer;

/**
 * The result of a Markdown processing
 *
 * @author Philip Helger
 */
public class MarkdownProcessingResult
{
  private final HCNodeList m_aNodeList;

  public MarkdownProcessingResult (@NonNull final MarkdownHCStack aResult)
  {
    ValueEnforcer.notNull (aResult, "Result");

    m_aNodeList = aResult.getRoot ();
  }

  @NonNull
  public HCNodeList getNodeList ()
  {
    return m_aNodeList;
  }

  @NonNull
  public String getAsHTMLString ()
  {
    return HCRenderer.getAsHTMLStringWithoutNamespaces (m_aNodeList).trim ();
  }

  @NonNull
  public String getAsHTMLString (@NonNull final IHCConversionSettings aConversionSettings)
  {
    return HCRenderer.getAsHTMLString (m_aNodeList, aConversionSettings).trim ();
  }
}
