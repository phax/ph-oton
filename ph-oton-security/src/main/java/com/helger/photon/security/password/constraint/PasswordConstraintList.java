/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.password.constraint;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;

/**
 * The default implementation of {@link IPasswordConstraintList}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class PasswordConstraintList implements IPasswordConstraintList
{
  private final ICommonsList <IPasswordConstraint> m_aConstraints = new CommonsArrayList <> ();

  public PasswordConstraintList ()
  {}

  public PasswordConstraintList (@NonNull final IPasswordConstraintList aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_aConstraints.addAll (aOther.getAllPasswordConstraints ());
  }

  public PasswordConstraintList (@Nullable final IPasswordConstraint... aPasswordConstraints)
  {
    if (aPasswordConstraints != null)
      for (final IPasswordConstraint aPasswordConstraint : aPasswordConstraints)
        addConstraint (aPasswordConstraint);
  }

  public PasswordConstraintList (@Nullable final Iterable <? extends IPasswordConstraint> aPasswordConstraints)
  {
    if (aPasswordConstraints != null)
      for (final IPasswordConstraint aPasswordConstraint : aPasswordConstraints)
        addConstraint (aPasswordConstraint);
  }

  public boolean hasConstraints ()
  {
    return m_aConstraints.isNotEmpty ();
  }

  @Nonnegative
  public int getConstraintCount ()
  {
    return m_aConstraints.size ();
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IPasswordConstraint> getAllPasswordConstraints ()
  {
    return m_aConstraints.getClone ();
  }

  /**
   * Added a password constraint.
   *
   * @param aPasswordConstraint
   *        The constraint to be added. May not be <code>null</code>.
   * @return this
   */
  @NonNull
  public PasswordConstraintList addConstraint (@NonNull final IPasswordConstraint aPasswordConstraint)
  {
    ValueEnforcer.notNull (aPasswordConstraint, "PasswordConstraint");
    m_aConstraints.add (aPasswordConstraint);
    return this;
  }

  /**
   * Remove the passed constraint.
   *
   * @param aPasswordConstraint
   *        The constraint to be removed. May be <code>null</code>.
   * @return {@link EChange}.
   */
  @NonNull
  public EChange removeConstraint (@Nullable final IPasswordConstraint aPasswordConstraint)
  {
    if (aPasswordConstraint == null)
      return EChange.UNCHANGED;
    return m_aConstraints.removeObject (aPasswordConstraint);
  }

  public boolean isPasswordValid (@Nullable final String sPlainTextPassword)
  {
    for (final IPasswordConstraint aPasswordConstraint : m_aConstraints)
      if (!aPasswordConstraint.isPasswordValid (sPlainTextPassword))
        return false;
    return true;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <String> getInvalidPasswordDescriptions (@Nullable final String sPlainTextPassword,
                                                               @NonNull final Locale aContentLocale)
  {
    final ICommonsList <String> ret = new CommonsArrayList <> ();
    for (final IPasswordConstraint aPasswordConstraint : m_aConstraints)
      if (!aPasswordConstraint.isPasswordValid (sPlainTextPassword))
        ret.add (aPasswordConstraint.getDescription (aContentLocale));
    return ret;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllPasswordConstraintDescriptions (@NonNull final Locale aContentLocale)
  {
    final ICommonsList <String> ret = new CommonsArrayList <> ();
    for (final IPasswordConstraint aPasswordConstraint : m_aConstraints)
      ret.add (aPasswordConstraint.getDescription (aContentLocale));
    return ret;
  }

  @NonNull
  public PasswordConstraintList getClone ()
  {
    return new PasswordConstraintList (this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PasswordConstraintList rhs = (PasswordConstraintList) o;
    return m_aConstraints.equals (rhs.m_aConstraints);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aConstraints).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("constraints", m_aConstraints).getToString ();
  }
}
