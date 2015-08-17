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
package com.helger.photon.uictrls.datatables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.css.ICSSClassProvider;

public class DataTablesDom implements Serializable, ICloneable <DataTablesDom>
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTablesDom.class);
  private static final String OPEN_DIV = "<";
  private static final String CLOSE_DIV = ">";

  private final List <String> m_aElements;
  private int m_nOpenDivs = 0;
  private int m_nPos = -1;

  public DataTablesDom ()
  {
    m_aElements = new ArrayList <String> ();
  }

  public DataTablesDom (@Nonnull final DataTablesDom aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aElements = new ArrayList <String> (aOther.m_aElements);
  }

  @CheckForSigned
  public int getPosition ()
  {
    return m_nPos;
  }

  @Nonnull
  public DataTablesDom setPosition (final int nNewPosition)
  {
    m_nPos = nNewPosition;
    return this;
  }

  @Nonnull
  public DataTablesDom setPositionToEnd ()
  {
    return setPosition (m_aElements.size ());
  }

  @CheckForSigned
  public int indexOf (@Nullable final String s)
  {
    if (StringHelper.hasNoText (s))
      return -1;
    return m_aElements.indexOf (s);
  }

  @Nonnull
  public EChange remove (@Nullable final String s)
  {
    final int nIndex = indexOf (s);
    if (nIndex < 0)
      return EChange.UNCHANGED;
    return remove (nIndex);
  }

  @Nonnull
  public EChange remove (@Nonnegative final int nIndex)
  {
    if (nIndex < 0 || nIndex >= m_aElements.size ())
      return EChange.UNCHANGED;
    m_aElements.remove (nIndex);
    return EChange.CHANGED;
  }

  private void _internalAdd (@Nonnull final String s)
  {
    if (m_nPos < 0)
    {
      // append
      m_aElements.add (s);
    }
    else
    {
      // add at specified position
      m_aElements.add (m_nPos, s);
      m_nPos++;
    }
  }

  @Nonnull
  private DataTablesDom _internalOpenDiv (@Nonnull final String s)
  {
    _internalAdd (s);
    ++m_nOpenDivs;
    return this;
  }

  @Nonnull
  public DataTablesDom openDiv ()
  {
    return _internalOpenDiv (OPEN_DIV);
  }

  @Nullable
  public static String getDivString (@Nullable final ICSSClassProvider aCSSClass)
  {
    final String sCSSClass = aCSSClass == null ? null : aCSSClass.getCSSClass ();
    return StringHelper.hasNoText (sCSSClass) ? OPEN_DIV : OPEN_DIV + "'" + sCSSClass + "'";
  }

  @Nonnull
  public DataTablesDom openDiv (@Nullable final ICSSClassProvider aCSSClass)
  {
    return _internalOpenDiv (getDivString (aCSSClass));
  }

  @Nullable
  public static String getDivString (@Nullable final ICSSClassProvider... aCSSClasses)
  {
    if (ArrayHelper.isNotEmpty (aCSSClasses))
    {
      final StringBuilder aClassesSB = new StringBuilder ();
      for (final ICSSClassProvider aCSSClass : aCSSClasses)
      {
        final String sCSSClass = aCSSClass.getCSSClass ();
        if (StringHelper.hasText (sCSSClass))
        {
          if (aClassesSB.length () > 0)
            aClassesSB.append (' ');
          aClassesSB.append (sCSSClass);
        }
      }
      if (aClassesSB.length () > 0)
        return OPEN_DIV + "'" + aClassesSB.toString () + "'";
    }
    return OPEN_DIV;
  }

  @Nonnull
  public DataTablesDom openDiv (@Nullable final ICSSClassProvider... aCSSClasses)
  {
    return _internalOpenDiv (getDivString (aCSSClasses));
  }

  @Nonnull
  public DataTablesDom openDiv (@Nullable final String sCSSClasses)
  {
    if (StringHelper.hasText (sCSSClasses))
      return _internalOpenDiv (OPEN_DIV + "'" + sCSSClasses + "'");
    return _internalOpenDiv (OPEN_DIV);
  }

  @Nonnull
  public DataTablesDom openDivWithID (@Nullable final String sID)
  {
    if (StringHelper.hasText (sID))
      return _internalOpenDiv (OPEN_DIV + "'#" + sID + "'");
    return _internalOpenDiv (OPEN_DIV);
  }

  @Nonnull
  public DataTablesDom closeDiv ()
  {
    _internalAdd (CLOSE_DIV);
    if (--m_nOpenDivs < 0)
      s_aLogger.error ("Too many DIVs are closed: " + m_nOpenDivs);
    return this;
  }

  /**
   * Add "l"
   *
   * @return this
   */
  @Nonnull
  public DataTablesDom addLengthMenu ()
  {
    _internalAdd ("l");
    return this;
  }

  /**
   * Add "f"
   *
   * @return this
   */
  @Nonnull
  public DataTablesDom addFiltering ()
  {
    _internalAdd ("f");
    return this;
  }

  /**
   * Add "t"
   *
   * @return this
   */
  @Nonnull
  public DataTablesDom addTable ()
  {
    _internalAdd ("t");
    return this;
  }

  /**
   * Add "i"
   *
   * @return this
   */
  @Nonnull
  public DataTablesDom addInformationSummary ()
  {
    _internalAdd ("i");
    return this;
  }

  /**
   * Add "p"
   *
   * @return this
   */
  @Nonnull
  public DataTablesDom addPagination ()
  {
    _internalAdd ("p");
    return this;
  }

  /**
   * Add "r"
   *
   * @return this
   */
  @Nonnull
  public DataTablesDom addProcessing ()
  {
    _internalAdd ("r");
    return this;
  }

  /**
   * Add a custom element
   *
   * @param sStr
   *        Custom element to add. May not be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public DataTablesDom addCustom (@Nonnull @Nonempty final String sStr)
  {
    ValueEnforcer.notEmpty (sStr, "Str");
    _internalAdd (sStr);
    return this;
  }

  @Nonnull
  public String getAsString ()
  {
    if (m_nOpenDivs != 0)
      s_aLogger.error ("The DIVs are not balanced: " +
                       (m_nOpenDivs > 0 ? m_nOpenDivs + " DIVs are open!"
                                        : m_nOpenDivs + " DIVs too many are closed!"));
    return StringHelper.getImploded (m_aElements);
  }

  @Nonnull
  public DataTablesDom getClone ()
  {
    return new DataTablesDom (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("elements", m_aElements)
                                       .append ("openDivCount", m_nOpenDivs)
                                       .append ("pos", m_nPos)
                                       .toString ();
  }
}
