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
package com.helger.appbasics.app.dao.container;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.appbasics.app.dao.IDAO;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * The default implementation of {@link IDAOContainer} using a list of DAOs
 * provider in the constructor.
 *
 * @author Philip Helger
 */
public class DefaultDAOContainer extends AbstractDAOContainer
{
  private final List <IDAO> m_aDAOs;

  public DefaultDAOContainer (@Nonnull @Nonempty final IDAO... aDAOs)
  {
    ValueEnforcer.notEmptyNoNullValue (aDAOs, "DAOs");
    m_aDAOs = CollectionHelper.newList (aDAOs);
  }

  public DefaultDAOContainer (@Nonnull @Nonempty final Iterable <? extends IDAO> aDAOs)
  {
    ValueEnforcer.notEmptyNoNullValue (aDAOs, "DAOs");
    m_aDAOs = CollectionHelper.newList (aDAOs);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <IDAO> getAllContainedDAOs ()
  {
    return CollectionHelper.newList (m_aDAOs);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("DAOs", m_aDAOs).toString ();
  }
}
