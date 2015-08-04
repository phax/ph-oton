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
import com.helger.html.hc.IHCHasID;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.html.hc.html.IHCElement;

/**
 * Base class for common bootstrap objects.
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractBootstrapObject <THISTYPE extends AbstractBootstrapObject <THISTYPE>> implements IHCHasID <THISTYPE>
{
  private String m_sID;
  private HCHasCSSClasses m_aCSSClasses;
  private HCHasCSSStyles m_aCSSStyles;

  public AbstractBootstrapObject ()
  {}

  @Nonnull
  protected final THISTYPE thisAsT ()
  {
    // Avoid the unchecked cast warning in all places
    return GenericReflection.<AbstractBootstrapObject <THISTYPE>, THISTYPE> uncheckedCast (this);
  }

  public final boolean hasID ()
  {
    return StringHelper.hasText (m_sID);
  }

  @Nullable
  public final String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public final THISTYPE setID (@Nullable final String sID)
  {
    // Check for existing ID
    return setID (sID, false);
  }

  @Nonnull
  public final THISTYPE setID (@Nullable final String sID, final boolean bImSureToOverwriteAnExistingID)
  {
    if (!bImSureToOverwriteAnExistingID && m_sID != null)
      if (StringHelper.hasText (sID))
      {
        if (!m_sID.equals (sID))
          HCConsistencyChecker.consistencyError ("Overwriting HC object ID '" +
                                                 m_sID +
                                                 "' with '" +
                                                 sID +
                                                 "' - this may have side effects!");
      }
      else
      {
        HCConsistencyChecker.consistencyError ("The HC object ID '" +
                                               m_sID +
                                               "' will be removed - this may have side effects");
      }
    m_sID = sID;
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE setUniqueID ()
  {
    return setID (GlobalIDFactory.getNewStringID ());
  }

  @Nonnull
  public THISTYPE ensureID ()
  {
    if (!hasID ())
      setUniqueID ();
    return thisAsT ();
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
  public final THISTYPE setHidden (final boolean bHidden)
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
