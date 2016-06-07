/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.longrun;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * This class represents the result of a single long running job execution.
 *
 * @author Philip Helger
 */
public final class LongRunningJobResult
{
  private final ELongRunningJobResultType m_eType;
  private final Object m_aResult;

  private LongRunningJobResult (@Nonnull final ELongRunningJobResultType eType, @Nonnull final Object aResult)
  {
    ValueEnforcer.notNull (eType, "Type");
    ValueEnforcer.notNull (aResult, "Result");
    m_eType = eType;
    m_aResult = aResult;
  }

  @Nonnull
  public ELongRunningJobResultType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public Object getResultObject ()
  {
    return m_aResult;
  }

  @Nullable
  public File getResultFile ()
  {
    return getType ().equals (ELongRunningJobResultType.FILE) ? (File) m_aResult : null;
  }

  @Nullable
  public IMicroNode getResultXML ()
  {
    return getType ().equals (ELongRunningJobResultType.XML) ? (IMicroNode) m_aResult : null;
  }

  @Nullable
  public String getResultText ()
  {
    return getType ().equals (ELongRunningJobResultType.TEXT) ? (String) m_aResult : null;
  }

  @Nullable
  public ISimpleURL getResultLink ()
  {
    return getType ().equals (ELongRunningJobResultType.LINK) ? (ISimpleURL) m_aResult : null;
  }

  @Nonnull
  public String getAsString ()
  {
    switch (m_eType)
    {
      case FILE:
        return getResultFile ().getAbsolutePath ();
      case XML:
        return MicroWriter.getXMLString (getResultXML ());
      case TEXT:
        return getResultText ();
      case LINK:
        return getResultLink ().getAsStringWithEncodedParameters ();
      default:
        throw new IllegalStateException ("Unhandled type: " + m_eType);
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("type", m_eType).append ("result", m_aResult).toString ();
  }

  @Nonnull
  public static LongRunningJobResult createFile (@Nonnull final File aResult)
  {
    return new LongRunningJobResult (ELongRunningJobResultType.FILE, aResult);
  }

  @Nonnull
  public static LongRunningJobResult createXML (@Nonnull final IMicroNode aResult)
  {
    return new LongRunningJobResult (ELongRunningJobResultType.XML, aResult);
  }

  @Nonnull
  public static LongRunningJobResult createText (@Nonnull final String sResult)
  {
    return new LongRunningJobResult (ELongRunningJobResultType.TEXT, sResult);
  }

  @Nonnull
  public static LongRunningJobResult createLink (@Nonnull final ISimpleURL aResult)
  {
    return new LongRunningJobResult (ELongRunningJobResultType.LINK, aResult);
  }
}
