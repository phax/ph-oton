/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.core.smtp;

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ITypedObject;
import com.helger.commons.type.ObjectType;
import com.helger.smtp.settings.ISMTPSettings;

public class NamedSMTPSettings implements ITypedObject <String>, Serializable
{
  public static final ObjectType OT = new ObjectType ("named-smtp-settings");

  private final String m_sID;
  private String m_sName;
  private ISMTPSettings m_aSMTPSettings;

  public NamedSMTPSettings (@Nonnull @Nonempty final String sName, @Nonnull final ISMTPSettings aSMTPSettings)
  {
    this (GlobalIDFactory.getNewPersistentStringID (), sName, aSMTPSettings);
  }

  NamedSMTPSettings (@Nonnull @Nonempty final String sID,
                     @Nonnull @Nonempty final String sName,
                     @Nonnull final ISMTPSettings aSMTPSettings)
  {
    ValueEnforcer.notEmpty (sID, "ID");
    m_sID = sID;
    setName (sName);
    setSMTPSettings (aSMTPSettings);
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public EChange setName (@Nonnull @Nonempty final String sName)
  {
    ValueEnforcer.notEmpty (sName, "Name");

    if (sName.equals (m_sName))
      return EChange.UNCHANGED;
    m_sName = sName;
    return EChange.CHANGED;
  }

  @Nonnull
  public ISMTPSettings getSMTPSettings ()
  {
    return m_aSMTPSettings;
  }

  @Nonnull
  public EChange setSMTPSettings (@Nonnull final ISMTPSettings aSMTPSettings)
  {
    ValueEnforcer.notNull (aSMTPSettings, "SMTPSettings");

    if (aSMTPSettings.equals (m_aSMTPSettings))
      return EChange.UNCHANGED;
    m_aSMTPSettings = aSMTPSettings;
    return EChange.CHANGED;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final NamedSMTPSettings rhs = (NamedSMTPSettings) o;
    return m_sID.equals (rhs.m_sID);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sID).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .append ("name", m_sName)
                                       .append ("SMTPsettings", m_aSMTPSettings)
                                       .getToString ();
  }
}
