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
package com.helger.html.hc;

import javax.annotation.Nonnull;

/**
 * This enum represents the internal state of {@link IHCNode} objects.
 *
 * @author Philip Helger
 */
public enum EHCNodeState
{
  /** The stated when the HCNode was initialized */
  INITIAL (0),
  /** Node was customized with {@link IHCCustomizer} */
  CUSTOMIZED (1),
  /** Protected method onFinalizeNodeState was called on the node */
  FINALIZED (2),
  /**
   * Consistency checks are disabled or protected method onConsistencyCheck was
   * called
   */
  CONSISTENCY_CHECKED (3),
  /** Protected method onRegisterExternalResources was called on the node */
  RESOURCES_REGISTERED (4);

  private final int m_nID;

  private EHCNodeState (final int nID)
  {
    m_nID = nID;
  }

  public int getID ()
  {
    return m_nID;
  }

  public boolean isBefore (@Nonnull final EHCNodeState eOther)
  {
    return m_nID < eOther.m_nID;
  }

  public boolean isEqualOrBefore (@Nonnull final EHCNodeState eOther)
  {
    return m_nID <= eOther.m_nID;
  }

  public boolean isAfter (@Nonnull final EHCNodeState eOther)
  {
    return m_nID > eOther.m_nID;
  }

  public boolean isEqualOrAfter (@Nonnull final EHCNodeState eOther)
  {
    return m_nID >= eOther.m_nID;
  }
}
