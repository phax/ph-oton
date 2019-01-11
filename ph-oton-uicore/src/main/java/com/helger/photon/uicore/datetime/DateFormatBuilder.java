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
package com.helger.photon.uicore.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Comparator;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.cache.Cache;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.compare.IComparator;
import com.helger.commons.datetime.PDTFromString;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

public final class DateFormatBuilder implements IDateFormatBuilder
{
  private final ICommonsList <Object> m_aList = new CommonsArrayList <> ();

  public DateFormatBuilder ()
  {}

  @Nonnull
  public DateFormatBuilder append (@Nonnull final EDateTimeFormatToken eToken)
  {
    ValueEnforcer.notNull (eToken, "Token");
    m_aList.add (eToken);
    return this;
  }

  @Nonnull
  public DateFormatBuilder append (@Nonnull @Nonempty final String sText)
  {
    ValueEnforcer.notEmpty (sText, "Text");

    StringHelper.iterateChars (sText, c -> {
      // Handle JS Calendar stuff separately
      if (c == '\n')
        append (EDateTimeFormatToken.CHAR_NEWLINE);
      else
        if (c == '\t')
          append (EDateTimeFormatToken.CHAR_TAB);
        else
          if (c == '%')
            append (EDateTimeFormatToken.CHAR_PERC);
          else
            m_aList.add (Character.valueOf (c));
    });
    return this;
  }

  @Nonnull
  public ICommonsList <Object> getAllInternalObjects ()
  {
    return m_aList.getClone ();
  }

  @Nonnull
  public String getJSCalendarFormatString ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final Object o : m_aList)
      if (o instanceof EDateTimeFormatToken)
        aSB.append (((EDateTimeFormatToken) o).getJSCalendarToken ());
      else
        aSB.append (((Character) o).charValue ());
    return aSB.toString ();
  }

  @Nonnull
  public String getJavaFormatString ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final Object o : m_aList)
      if (o instanceof EDateTimeFormatToken)
        aSB.append (((EDateTimeFormatToken) o).getJavaToken ());
      else
        aSB.append (((Character) o).charValue ());
    return aSB.toString ();
  }

  @Nonnull
  public DateTimeFormatter getJavaFormatter ()
  {
    final DateTimeFormatterBuilder aBuilder = new DateTimeFormatterBuilder ();
    for (final Object o : m_aList)
      if (o instanceof EDateTimeFormatToken)
        ((EDateTimeFormatToken) o).addToFormatterBuilder (aBuilder);
      else
        aBuilder.appendLiteral (((Character) o).charValue ());
    return aBuilder.toFormatter ();
  }

  private static final boolean USE_FORMATTER = true;

  @Nonnull
  public LocalDate getDateFormatted (@Nullable final String sDate)
  {
    if (USE_FORMATTER)
      return PDTFromString.getLocalDateFromString (sDate, getJavaFormatter ());

    return PDTFromString.getLocalDateFromString (sDate, getJavaFormatString ());
  }

  @Nonnull
  public LocalTime getTimeFormatted (@Nullable final String sTime)
  {
    if (USE_FORMATTER)
      return PDTFromString.getLocalTimeFromString (sTime, getJavaFormatter ());

    return PDTFromString.getLocalTimeFromString (sTime, getJavaFormatString ());
  }

  @Nonnull
  public LocalDateTime getLocalDateTimeFormatted (@Nullable final String sDateTime)
  {
    if (USE_FORMATTER)
      return PDTFromString.getLocalDateTimeFromString (sDateTime, getJavaFormatter ());

    return PDTFromString.getLocalDateTimeFromString (sDateTime, getJavaFormatString ());
  }

  @Nonnull
  public ZonedDateTime getDateTimeFormatted (@Nullable final String sDateTime)
  {
    if (USE_FORMATTER)
      return PDTFromString.getZonedDateTimeFromString (sDateTime, getJavaFormatter ());

    return PDTFromString.getZonedDateTimeFromString (sDateTime, getJavaFormatString ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("list", m_aList).getToString ();
  }

  private static final class Searcher
  {
    private String m_sRest;
    private final ICommonsMap <String, EDateTimeFormatToken> m_aAllMatching = new CommonsHashMap <> ();
    private final Comparator <String> m_aComp = IComparator.getComparatorStringLongestFirst ();

    public Searcher (@Nonnull final String sRest)
    {
      m_sRest = ValueEnforcer.notNull (sRest, "Rest");
    }

    public boolean hasMore ()
    {
      return m_sRest.length () > 0;
    }

    @Nullable
    public EDateTimeFormatToken getNextToken ()
    {
      m_aAllMatching.clear ();
      for (final EDateTimeFormatToken eToken : EDateTimeFormatToken.values ())
      {
        final String sJavaToken = eToken.getJavaToken ();
        if (m_sRest.startsWith (sJavaToken))
          m_aAllMatching.put (sJavaToken, eToken);
      }
      if (m_aAllMatching.isEmpty ())
        return null;

      Map.Entry <String, EDateTimeFormatToken> aEntry;
      if (m_aAllMatching.size () == 1)
        aEntry = m_aAllMatching.getFirstEntry ();
      else
        aEntry = m_aAllMatching.getSortedByKey (m_aComp).getFirstEntry ();
      m_sRest = m_sRest.substring (aEntry.getKey ().length ());
      return aEntry.getValue ();
    }

    @Nonnull
    public String getNextChar ()
    {
      final String ret = m_sRest.substring (0, 1);
      m_sRest = m_sRest.substring (1);
      return ret;
    }
  }

  private static final class PatternCache extends Cache <String, DateFormatBuilder>
  {
    public PatternCache ()
    {
      super (sJavaPattern -> {
        ValueEnforcer.notNull (sJavaPattern, "JavaPattern");

        // Do parsing
        final DateFormatBuilder aDFB = new DateFormatBuilder ();
        final Searcher aSearcher = new Searcher (sJavaPattern);
        while (aSearcher.hasMore ())
        {
          final EDateTimeFormatToken eToken = aSearcher.getNextToken ();
          if (eToken != null)
            aDFB.append (eToken);
          else
          {
            // It's not a token -> use a single char and check for the next
            // token
            aDFB.append (aSearcher.getNextChar ());
          }
        }
        return aDFB;
      }, CGlobal.ILLEGAL_UINT, "DateFormatBuilder.PatternCache");
    }
  }

  private static final PatternCache s_aCache = new PatternCache ();

  @Nonnull
  public static IDateFormatBuilder fromJavaPattern (@Nonnull final String sJavaPattern)
  {
    ValueEnforcer.notEmpty (sJavaPattern, "JavaPattern");

    return s_aCache.getFromCache (sJavaPattern);
  }
}
