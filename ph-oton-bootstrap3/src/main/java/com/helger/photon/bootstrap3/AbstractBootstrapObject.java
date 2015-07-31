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
package com.helger.photon.bootstrap3;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.string.StringHelper;
import com.helger.css.property.CCSSProperties;
import com.helger.css.property.ECSSProperty;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.html.hc.html.IHCElement;

/**
 * Base class for common bootstrap objects.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractBootstrapObject <IMPLTYPE extends AbstractBootstrapObject <IMPLTYPE>>
{
  private String m_sID;
  private HCHasCSSClasses m_aCSSClasses;
  private HCHasCSSStyles m_aCSSStyles;

  public AbstractBootstrapObject ()
  {}

  @Nonnull
  protected final IMPLTYPE thisAsT ()
  {
    // Avoid the unchecked cast warning in all places
    return GenericReflection.<AbstractBootstrapObject <IMPLTYPE>, IMPLTYPE> uncheckedCast (this);
  }

  /**
   * Ensure that this form group has a unique ID. If an ID is already present,
   * nothing happens.
   *
   * @return this
   */
  @Nonnull
  public final IMPLTYPE ensureID ()
  {
    if (StringHelper.hasNoText (m_sID))
      m_sID = GlobalIDFactory.getNewStringID ();
    return thisAsT ();
  }

  /**
   * Set the ID of this form group.
   *
   * @param sFormGroupID
   *        The from group ID to be set. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public final IMPLTYPE setID (@Nullable final String sFormGroupID)
  {
    m_sID = sFormGroupID;
    return thisAsT ();
  }

  /**
   * @return <code>true</code> if an ID is present, <code>false</code>
   *         otherwise.
   */
  public final boolean hasID ()
  {
    return StringHelper.hasText (m_sID);
  }

  @Nullable
  public final String getID ()
  {
    return m_sID;
  }

  public final boolean hasCSSClasses ()
  {
    return m_aCSSClasses != null;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public final HCHasCSSClasses getCSSClasses ()
  {
    if (m_aCSSClasses == null)
      m_aCSSClasses = new HCHasCSSClasses ();
    return m_aCSSClasses;
  }

  public final boolean hasCSSStyles ()
  {
    return m_aCSSStyles != null;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public final HCHasCSSStyles getCSSStyles ()
  {
    if (m_aCSSStyles == null)
      m_aCSSStyles = new HCHasCSSStyles ();
    return m_aCSSStyles;
  }

  @Nonnull
  public final IMPLTYPE setHidden (final boolean bHidden)
  {
    if (bHidden)
      getCSSStyles ().addStyle (CCSSProperties.DISPLAY_NONE);
    else
      if (m_aCSSStyles != null)
        m_aCSSStyles.removeStyle (ECSSProperty.DISPLAY);
    return thisAsT ();
  }

  public final void applyBasicHTMLTo (@Nonnull final IHCElement <?> aTarget)
  {
    if (hasID ())
      aTarget.setID (getID ());

    if (m_aCSSClasses != null)
      aTarget.addClasses (m_aCSSClasses.getAllClasses ());

    if (m_aCSSStyles != null)
      aTarget.addStyles (m_aCSSStyles.getAllStyleValues ());
  }
}
