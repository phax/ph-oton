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
package com.helger.html.entities;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.string.ToStringGenerator;

/**
 * Contains an arbitrary entities.
 * 
 * @author Philip Helger
 */
@Immutable
public class HTMLEntity implements IHTMLEntity
{
  private final String m_sEntityName;
  private final String m_sEntityReference;

  public HTMLEntity (@Nonnull @Nonempty final String sName)
  {
    m_sEntityName = sName;
    m_sEntityReference = '&' + sName + ';';
  }

  @Nonnull
  @Nonempty
  public String getEntityName ()
  {
    return m_sEntityName;
  }

  @Nonnull
  @Nonempty
  public String getEntityReference ()
  {
    return m_sEntityReference;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("name", m_sEntityName).toString ();
  }
}
