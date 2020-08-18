package com.helger.photon.uictrls.prism;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.xml.microdom.IMicroElement;

public class PrismPluginLineHighlight implements IPrismPlugin
{
  private String m_sLine;

  public PrismPluginLineHighlight ()
  {}

  @Nonnull
  public PrismPluginLineHighlight setLine (@Nullable final String s)
  {
    m_sLine = s;
    return this;
  }

  public void applyOnPre (@Nonnull final IMicroElement aPreElement,
                          @Nonnull final HCHasCSSClasses aPreClasses,
                          @Nonnull final HCHasCSSStyles aPreStyles)
  {
    if (StringHelper.hasText (m_sLine))
      aPreElement.setAttribute ("data-line", m_sLine);
  }
}
