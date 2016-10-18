package com.helger.photon.uictrls.datatables.column;

import java.util.Comparator;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.compare.CompareHelper;
import com.helger.commons.compare.IComparator;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.string.ToStringGenerator;

public final class DTOrderSpec
{
  public static final boolean DEFAULT_COLLATING = true;

  private Function <String, String> m_aFormatter;
  private IComparableExtractor <?> m_aComparableExtractor;
  private boolean m_bCollating = DEFAULT_COLLATING;
  private Locale m_aDisplayLocale;
  // Status vars
  private Comparator <String> m_aComparator;

  public DTOrderSpec ()
  {}

  public void setFrom (@Nonnull final DTOrderSpec aOther)
  {
    m_aFormatter = aOther.m_aFormatter;
    m_aComparableExtractor = aOther.m_aComparableExtractor;
    m_bCollating = aOther.m_bCollating;
    m_aDisplayLocale = aOther.m_aDisplayLocale;
    m_aComparator = aOther.m_aComparator;
  }

  @Nullable
  public Function <String, String> getFormatter ()
  {
    return m_aFormatter;
  }

  @Nullable
  public IComparableExtractor <?> getComparableExtractor ()
  {
    return m_aComparableExtractor;
  }

  @Nonnull
  public <T extends Comparable <? super T>> DTOrderSpec setComparableExtractor (@Nullable final Function <String, String> aFormatter,
                                                                                @Nullable final IComparableExtractor <T> aComparableExtractor)
  {
    m_aFormatter = aFormatter;
    if (aFormatter == null)
      m_aComparableExtractor = aComparableExtractor;
    else
    {
      final IComparableExtractor <T> aRealCE = x -> aComparableExtractor.apply (x == null ? null
                                                                                          : aFormatter.apply (x));
      m_aComparableExtractor = aRealCE;
    }
    // reset
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
      // reset
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
      // reset
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
    return new ToStringGenerator (this).append ("Formatter", m_aFormatter)
                                       .append ("ComparableExtractor", m_aComparableExtractor)
                                       .append ("Collating", m_bCollating)
                                       .append ("DisplayLocale", m_aDisplayLocale)
                                       .append ("Comparator", m_aComparator)
                                       .toString ();
  }
}
