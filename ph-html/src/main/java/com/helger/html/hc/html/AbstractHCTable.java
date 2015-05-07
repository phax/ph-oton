/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.microdom.IMicroElement;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Represents an HTML &lt;table&gt; element with open semantics.
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        The implementing type
 */
public abstract class AbstractHCTable <THISTYPE extends AbstractHCTable <THISTYPE>> extends AbstractHCBaseTable <THISTYPE>
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractHCTable.class);

  public AbstractHCTable ()
  {
    super (EHTMLElement.TABLE);
  }

  public AbstractHCTable (@Nullable final HCCol aCol)
  {
    this ();
    addColumn (aCol);
  }

  public AbstractHCTable (@Nullable final HCCol... aCols)
  {
    this ();
    addColumns (aCols);
  }

  public AbstractHCTable (@Nullable final Iterable <? extends HCCol> aCols)
  {
    this ();
    addColumns (aCols);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (@Nonnull final IMicroElement aElement,
                                  @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);

    if (false)
    {
      // May happen when a table is filled via AJAX
      // Required by XHTML 1.1
      if (!hasBodyRows () && !hasBodyID () && !hasBodyClasses () && aConversionSettings.getHTMLVersion ().isXHTML11 ())
        s_aLogger.warn ("Tables without body rows are prohibited by XHTML 1.1!");
    }

    // Table specific attributes
    if (aConversionSettings.getHTMLVersion ().isPriorToHTML5 ())
    {
      // These attributes are obsolete in HTML5
      if (getCellSpacing () >= 0)
        aElement.setAttribute (CHTMLAttributes.CELLSPACING, getCellSpacing ());
      if (getCellPadding () >= 0)
        aElement.setAttribute (CHTMLAttributes.CELLPADDING, getCellPadding ());
    }

    // Append colgroup
    if (getColGroup () != null && getColGroup ().hasColumns ())
      aElement.appendChild (getColGroup ().convertToNode (aConversionSettings));

    // Table header
    aElement.appendChild (getHead ().convertToNode (aConversionSettings));

    // Table footer
    aElement.appendChild (getFoot ().convertToNode (aConversionSettings));

    // Table body
    aElement.appendChild (getBody ().convertToNode (aConversionSettings));
  }
}
