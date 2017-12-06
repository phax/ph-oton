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
package com.helger.photon.uictrls.datatables.column;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.compare.CompareHelper;
import com.helger.commons.compare.IComparator;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.string.ToStringGenerator;

public final class DTOrderSpec implements Serializable
{
  public static final boolean DEFAULT_COLLATING = true;

  private IComparableExtractor <?> m_aComparableExtractor;
  private boolean m_bCollating = DEFAULT_COLLATING;
  private Locale m_aDisplayLocale;
  // Status vars
  private transient Comparator <String> m_aComparator;

  public DTOrderSpec ()
  {}

  public void setFrom (@Nonnull final DTOrderSpec aOther)
  {
    m_aComparableExtractor = aOther.m_aComparableExtractor;
    m_bCollating = aOther.m_bCollating;
    m_aDisplayLocale = aOther.m_aDisplayLocale;
    m_aComparator = aOther.m_aComparator;
  }

  @Nullable
  public IComparableExtractor <?> getComparableExtractor ()
  {
    return m_aComparableExtractor;
  }

  /**
   * @param aFormatter
   *        Optional formatter to be used, to e.g. trim off certain prefixes or
   *        suffixes. May be <code>null</code>.
   * @param aComparableExtractor
   *        The converter from String to any comparable type. May be
   *        <code>null</code> if no formatter is present. Must be
   *        non-<code>null</code> if a formatter is present!
   * @return this for chaining
   */
  @Nonnull
  public <T extends Comparable <? super T>> DTOrderSpec setComparableExtractor (@Nullable final Function <String, String> aFormatter,
                                                                                @Nullable final IComparableExtractor <T> aComparableExtractor)
  {
    if (aFormatter == null)
    {
      // Use as is
      m_aComparableExtractor = aComparableExtractor;
    }
    else
    {
      // format, than extract
      final IComparableExtractor <T> aRealCE = x -> aComparableExtractor.apply (x == null ? null
                                                                                          : aFormatter.apply (x));
      m_aComparableExtractor = aRealCE;
    }
    // reset status vars
    m_aComparator = null;
    return this;
  }

  public boolean isCollating ()
  {
    return m_bCollating;
  }

  @Nonnull
  public DTOrderSpec setCollating (final boolean bCollating)
  {
    if (bCollating != m_bCollating)
    {
      m_bCollating = bCollating;
      // reset status vars
      m_aComparator = null;
    }
    return this;
  }

  @Nullable
  public Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nonnull
  public DTOrderSpec setDisplayLocale (@Nonnull final Locale aDisplayLocale)
  {
    if (!EqualsHelper.equals (aDisplayLocale, m_aDisplayLocale))
    {
      m_aDisplayLocale = aDisplayLocale;
      // reset status vars
      m_aComparator = null;
    }
    return this;
  }

  /**
   * @return A Comparator independent of the sort order and without
   *         <code>null</code> handling.
   */
  @SuppressWarnings ({ "unchecked", "rawtypes" })
  @Nonnull
  public Comparator <String> getComparator ()
  {
    // Cached version available?
    Comparator <String> ret = m_aComparator;
    if (ret == null)
    {
      final IComparableExtractor <?> aCE = m_aComparableExtractor;
      if (aCE != null)
      {
        // Use the provided extractor for comparison
        ret = (o1, o2) -> {
          final Comparable aObj1 = aCE.apply (o1);
          final Comparable aObj2 = aCE.apply (o2);
          return CompareHelper.compare (aObj1, aObj2, true);
        };
      }
      else
      {
        final Locale aDisplayLocale = m_aDisplayLocale;
        if (m_bCollating && aDisplayLocale != null)
        {
          // Compare with Collator of provided locale
          ret = IComparator.getComparatorCollating (aDisplayLocale);
        }
        else
        {
          // Compare Strings as-is
          ret = Comparator.naturalOrder ();
        }
      }

      // Cache now
      m_aComparator = ret;
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ComparableExtractor", m_aComparableExtractor)
                                       .append ("Collating", m_bCollating)
                                       .append ("DisplayLocale", m_aDisplayLocale)
                                       .append ("Comparator", m_aComparator)
                                       .getToString ();
  }
}
