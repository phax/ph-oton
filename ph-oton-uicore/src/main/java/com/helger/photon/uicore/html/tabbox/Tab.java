/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.tabbox;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.IHCNode;

/**
 * Represents a single tab within a tab box
 *
 * @author Philip Helger
 */
public class Tab implements IHasID <String>, Serializable
{
  private final String m_sID;
  private final boolean m_bHasGeneratedID;
  private IHCNode m_aLabel;
  private IHCNode m_aContent;
  private boolean m_bDisabled;

  public Tab (@Nullable final String sID, @Nullable final IHCNode aLabel, @Nullable final IHCNode aContent, final boolean bDisabled)
  {
    if (StringHelper.hasText (sID))
    {
      m_sID = sID;
      m_bHasGeneratedID = false;
    }
    else
    {
      m_sID = GlobalIDFactory.getNewStringID ();
      m_bHasGeneratedID = true;
    }
    m_aLabel = aLabel;
    m_aContent = aContent;
    m_bDisabled = bDisabled;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean isGeneratedID ()
  {
    return m_bHasGeneratedID;
  }

  @Nonnull
  public ISimpleURL getLinkURL ()
  {
    return new SimpleURL ().setAnchor (m_sID);
  }

  @Nullable
  public IHCNode getLabel ()
  {
    return m_aLabel;
  }

  @Nonnull
  public Tab setLabel (@Nullable final IHCNode aLabel)
  {
    m_aLabel = aLabel;
    return this;
  }

  @Nullable
  public IHCNode getContent ()
  {
    return m_aContent;
  }

  @Nonnull
  public Tab setContent (@Nullable final IHCNode aContent)
  {
    m_aContent = aContent;
    return this;
  }

  public boolean isDisabled ()
  {
    return m_bDisabled;
  }

  @Nonnull
  public Tab setDisabled (final boolean bDisabled)
  {
    m_bDisabled = bDisabled;
    return this;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .append ("generatedID", m_bHasGeneratedID)
                                       .append ("label", m_aLabel)
                                       .append ("content", m_aContent)
                                       .append ("disabled", m_bDisabled)
                                       .getToString ();
  }
}
