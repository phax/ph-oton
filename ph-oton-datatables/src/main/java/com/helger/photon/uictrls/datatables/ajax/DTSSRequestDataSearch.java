/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.ajax;

import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Represents search settings, either for the global request or per-column.
 *
 * @author Philip Helger
 */
public final class DTSSRequestDataSearch
{
  private final String [] m_aSearchTexts;
  private final boolean m_bRegEx;

  @Nullable
  public static String [] getSearchTexts (@Nullable final String sSearchText)
  {
    if (StringHelper.hasNoTextAfterTrim (sSearchText))
      return null;

    return RegExHelper.getSplitToArray (sSearchText, "\\s+");
  }

  public DTSSRequestDataSearch (@Nullable final String sSearchText, final boolean bSearchRegEx)
  {
    m_aSearchTexts = getSearchTexts (sSearchText);
    m_bRegEx = bSearchRegEx;
  }

  /**
   * @return <code>true</code> if any search text is present
   */
  public boolean hasSearchText ()
  {
    return ArrayHelper.isNotEmpty (m_aSearchTexts);
  }

  /**
   * @return All search texts. May be <code>null</code>.
   */
  @Nullable
  @ReturnsMutableCopy
  public String [] getSearchTexts ()
  {
    return ArrayHelper.getCopy (m_aSearchTexts);
  }

  /**
   * @return <code>true</code> if the filter should be treated as a regular
   *         expression for advanced filtering, <code>false</code> if not.
   */
  public boolean isRegEx ()
  {
    return m_bRegEx;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("searchTexts", m_aSearchTexts).append ("regEx", m_bRegEx).toString ();
  }
}
