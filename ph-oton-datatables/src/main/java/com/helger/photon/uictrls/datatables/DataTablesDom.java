/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.Nonempty;
import com.helger.base.array.ArrayHelper;
import com.helger.base.clone.ICloneable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.string.StringImplode;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.css.ICSSClassProvider;

public class DataTablesDom implements ICloneable <DataTablesDom>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DataTablesDom.class);
  private static final String OPEN_DIV = "<";
  private static final String CLOSE_DIV = ">";

  private final ICommonsList <String> m_aElements;
  private int m_nOpenDivs = 0;
  private int m_nPos = -1;

  public DataTablesDom ()
  {
    m_aElements = new CommonsArrayList <> ();
  }

  public DataTablesDom (@NonNull final DataTablesDom aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aElements = new CommonsArrayList <> (aOther.m_aElements);
  }

  @CheckForSigned
  public int getPosition ()
  {
    return m_nPos;
  }

  @NonNull
  public DataTablesDom setPosition (final int nNewPosition)
  {
    m_nPos = nNewPosition;
    return this;
  }

  @NonNull
  public DataTablesDom setPositionToEnd ()
  {
    return setPosition (m_aElements.size ());
  }

  @CheckForSigned
  public int indexOf (@Nullable final String s)
  {
    if (StringHelper.isEmpty (s))
      return -1;
    return m_aElements.indexOf (s);
  }

  public boolean contains (@Nullable final String s)
  {
    return indexOf (s) >= 0;
  }

  @NonNull
  public EChange remove (@Nullable final String s)
  {
    final int nIndex = indexOf (s);
    if (nIndex < 0)
      return EChange.UNCHANGED;
    return remove (nIndex);
  }

  @NonNull
  public EChange remove ()
  {
    return remove (m_nPos);
  }

  @NonNull
  public EChange remove (final int nIndex)
  {
    if (nIndex < 0 || nIndex >= m_aElements.size ())
      return EChange.UNCHANGED;
    final String sValue = m_aElements.remove (nIndex);
    if (sValue != null && sValue.startsWith (OPEN_DIV))
      m_nOpenDivs--;
    return EChange.CHANGED;
  }

  private void _internalAdd (@NonNull final String s)
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

  @NonNull
  private DataTablesDom _internalOpenDiv (@NonNull final String s)
  {
    _internalAdd (s);
    ++m_nOpenDivs;
    return this;
  }

  @NonNull
  public DataTablesDom openDiv ()
  {
    return _internalOpenDiv (OPEN_DIV);
  }

  @Nullable
  public static String getDivString (@Nullable final ICSSClassProvider aCSSClass)
  {
    final String sCSSClass = aCSSClass == null ? null : aCSSClass.getCSSClass ();
    return StringHelper.isEmpty (sCSSClass) ? OPEN_DIV : OPEN_DIV + "'" + sCSSClass + "'";
  }

  @NonNull
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
        if (StringHelper.isNotEmpty (sCSSClass))
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

  @NonNull
  public DataTablesDom openDiv (@Nullable final ICSSClassProvider... aCSSClasses)
  {
    return _internalOpenDiv (getDivString (aCSSClasses));
  }

  @NonNull
  public DataTablesDom openDiv (@Nullable final String sCSSClasses)
  {
    if (StringHelper.isNotEmpty (sCSSClasses))
      return _internalOpenDiv (OPEN_DIV + "'" + sCSSClasses + "'");
    return _internalOpenDiv (OPEN_DIV);
  }

  @Nullable
  public static String getDivStringWithID (@Nullable final String sID)
  {
    return StringHelper.isEmpty (sID) ? OPEN_DIV : OPEN_DIV + "'#" + sID + "'";
  }

  @NonNull
  public DataTablesDom openDivWithID (@Nullable final String sID)
  {
    return _internalOpenDiv (getDivStringWithID (sID));
  }

  @NonNull
  public DataTablesDom closeDiv ()
  {
    _internalAdd (CLOSE_DIV);
    if (--m_nOpenDivs < 0)
      LOGGER.error ("Too many DIVs are closed: " + m_nOpenDivs);
    return this;
  }

  /**
   * Add "l"
   *
   * @return this
   */
  @NonNull
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
  @NonNull
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
  @NonNull
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
  @NonNull
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
  @NonNull
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
  @NonNull
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
  @NonNull
  public DataTablesDom addCustom (@NonNull @Nonempty final String sStr)
  {
    ValueEnforcer.notEmpty (sStr, "Str");
    _internalAdd (sStr);
    return this;
  }

  @NonNull
  public String getAsString ()
  {
    if (m_nOpenDivs != 0)
      LOGGER.error ("The DIVs are not balanced: " +
                    (m_nOpenDivs > 0 ? m_nOpenDivs + " DIVs are open!" : m_nOpenDivs + " DIVs too many are closed!"));
    return StringImplode.getImploded (m_aElements);
  }

  @NonNull
  public DataTablesDom getClone ()
  {
    return new DataTablesDom (this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final DataTablesDom rhs = (DataTablesDom) o;
    return m_aElements.equals (rhs.m_aElements);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aElements).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("elements", m_aElements)
                                       .append ("openDivCount", m_nOpenDivs)
                                       .append ("pos", m_nPos)
                                       .getToString ();
  }
}
