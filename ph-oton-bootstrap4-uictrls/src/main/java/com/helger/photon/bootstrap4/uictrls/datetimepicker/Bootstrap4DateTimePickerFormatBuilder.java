/*
 * Copyright (C) 2018-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.cache.Cache;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.compare.IComparator;
import com.helger.commons.datetime.PDTFromString;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.uicore.datetime.IDateFormatBuilder;

public class Bootstrap4DateTimePickerFormatBuilder implements IDateFormatBuilder
{
  // List of Character or EMomentsDateTimePickerFormatToken
  private final ICommonsList <Object> m_aList = new CommonsArrayList <> ();

  public Bootstrap4DateTimePickerFormatBuilder ()
  {}

  @Nonnull
  public Bootstrap4DateTimePickerFormatBuilder append (@Nonnull final EMomentsDateTimePickerFormatToken eToken)
  {
    ValueEnforcer.notNull (eToken, "Token");
    m_aList.add (eToken);
    return this;
  }

  @Nonnull
  public Bootstrap4DateTimePickerFormatBuilder append (final char c)
  {
    m_aList.add (Character.valueOf (c));
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Object> getAllInternalObjects ()
  {
    return m_aList.getClone ();
  }

  @Nonnull
  public String getJSCalendarFormatString ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final Object o : m_aList)
      if (o instanceof EMomentsDateTimePickerFormatToken)
        aSB.append (((EMomentsDateTimePickerFormatToken) o).getJSToken ());
      else
        aSB.append (((Character) o).charValue ());
    return aSB.toString ();
  }

  @Nonnull
  public String getJavaFormatString ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final Object o : m_aList)
      if (o instanceof EMomentsDateTimePickerFormatToken)
        aSB.append (((EMomentsDateTimePickerFormatToken) o).getJavaToken ());
      else
        aSB.append (((Character) o).charValue ());
    return aSB.toString ();
  }

  @Nonnull
  public LocalDate getDateFormatted (@Nullable final String sDate)
  {
    return PDTFromString.getLocalDateFromString (sDate, getJavaFormatString ());
  }

  @Nonnull
  public LocalTime getTimeFormatted (@Nullable final String sTime)
  {
    return PDTFromString.getLocalTimeFromString (sTime, getJavaFormatString ());
  }

  @Nonnull
  public LocalDateTime getLocalDateTimeFormatted (@Nullable final String sDateTime)
  {
    return PDTFromString.getLocalDateTimeFromString (sDateTime, getJavaFormatString ());
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("list", m_aList).getToString ();
  }

  private static final class Searcher
  {
    private String m_sRest;
    private final ICommonsMap <String, EMomentsDateTimePickerFormatToken> m_aAllMatching = new CommonsHashMap <> ();
    private final Comparator <String> m_aComp = IComparator.getComparatorStringLongestFirst ();

    public Searcher (@Nonnull final String sRest)
    {
      ValueEnforcer.notNull (sRest, "Rest");
      m_sRest = sRest;
    }

    public boolean hasMore ()
    {
      return m_sRest.length () > 0;
    }

    @Nullable
    public EMomentsDateTimePickerFormatToken getNextToken ()
    {
      m_aAllMatching.clear ();
      for (final EMomentsDateTimePickerFormatToken eToken : EMomentsDateTimePickerFormatToken.values ())
      {
        final String sJavaToken = eToken.getJavaToken ();
        if (m_sRest.startsWith (sJavaToken))
          m_aAllMatching.put (sJavaToken, eToken);
      }
      if (m_aAllMatching.isEmpty ())
        return null;

      Map.Entry <String, EMomentsDateTimePickerFormatToken> aEntry;
      if (m_aAllMatching.size () == 1)
        aEntry = m_aAllMatching.getFirstEntry ();
      else
        aEntry = m_aAllMatching.getSortedByKey (m_aComp).getFirstEntry ();
      m_sRest = m_sRest.substring (aEntry.getKey ().length ());
      return aEntry.getValue ();
    }

    public char getNextChar ()
    {
      final char ret = m_sRest.charAt (0);
      m_sRest = m_sRest.substring (1);
      return ret;
    }
  }

  private static final class PatternCache extends Cache <String, Bootstrap4DateTimePickerFormatBuilder>
  {
    public PatternCache ()
    {
      super (sJavaPattern -> {
        ValueEnforcer.notNull (sJavaPattern, "JavaPattern");

        // Do parsing
        final Bootstrap4DateTimePickerFormatBuilder aDFB = new Bootstrap4DateTimePickerFormatBuilder ();
        final Searcher aSearcher = new Searcher (sJavaPattern);
        while (aSearcher.hasMore ())
        {
          final EMomentsDateTimePickerFormatToken eToken = aSearcher.getNextToken ();
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
      }, CGlobal.ILLEGAL_UINT, "BootstrapDateTimePickerFormatBuilder.PatternCache");
    }
  }

  private static final PatternCache CACHE = new PatternCache ();

  @Nonnull
  public static IDateFormatBuilder fromJavaPattern (@Nonnull final String sJavaPattern)
  {
    ValueEnforcer.notEmpty (sJavaPattern, "JavaPattern");

    return CACHE.getFromCache (sJavaPattern);
  }
}
