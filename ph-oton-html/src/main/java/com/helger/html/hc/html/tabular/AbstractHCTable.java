/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.tabular;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;table&gt; element with open semantics.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The implementing type
 */
public abstract class AbstractHCTable <IMPLTYPE extends AbstractHCTable <IMPLTYPE>>
                                      extends AbstractHCBaseTable <IMPLTYPE>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractHCTable.class);

  public AbstractHCTable ()
  {
    super (EHTMLElement.TABLE);
  }

  /**
   * @param aCol
   *        Column to be added. <code>null</code> values are ignored!
   */
  public AbstractHCTable (@Nullable final IHCCol <?> aCol)
  {
    this ();
    addColumn (aCol);
  }

  /**
   * @param aCols
   *        Columns to be added. <code>null</code> values are ignored!
   */
  public AbstractHCTable (@Nullable final IHCCol <?>... aCols)
  {
    this ();
    addColumns (aCols);
  }

  /**
   * @param aCols
   *        Columns to be added. <code>null</code> values are ignored!
   */
  public AbstractHCTable (@Nullable final Iterable <? extends IHCCol <?>> aCols)
  {
    this ();
    addColumns (aCols);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (@Nonnull final IMicroElement aElement,
                                   @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);

    if (false)
    {
      // May happen when a table is filled via AJAX
      // Required by XHTML 1.1
      if (!hasBodyRows () && !hasBodyID () && !hasBodyClasses () && aConversionSettings.getHTMLVersion ().isXHTML11 ())
        LOGGER.warn ("Tables without body rows are prohibited by XHTML 1.1!");
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
      aElement.appendChild (getColGroup ().convertToMicroNode (aConversionSettings));

    // Table header
    aElement.appendChild (getHead ().convertToMicroNode (aConversionSettings));

    // Table footer
    aElement.appendChild (getFoot ().convertToMicroNode (aConversionSettings));

    // Table body
    aElement.appendChild (getBody ().convertToMicroNode (aConversionSettings));
  }
}
