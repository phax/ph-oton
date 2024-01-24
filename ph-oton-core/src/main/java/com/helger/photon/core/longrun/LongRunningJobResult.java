/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.core.longrun;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.json.IJson;
import com.helger.json.serialize.JsonWriter;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * This class represents the result of a single long running job execution.
 *
 * @author Philip Helger
 */
@Immutable
public class LongRunningJobResult
{
  private final ELongRunningJobResultType m_eType;
  private final Object m_aResult;

  protected LongRunningJobResult (@Nonnull final ELongRunningJobResultType eType, @Nonnull final Object aResult)
  {
    ValueEnforcer.notNull (eType, "Type");
    ValueEnforcer.notNull (aResult, "Result");
    m_eType = eType;
    m_aResult = aResult;
  }

  /**
   * @return The type as specified in the constructor. Never <code>null</code>.
   */
  @Nonnull
  public final ELongRunningJobResultType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final Object getResultObject ()
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

  @Nullable
  public IJson getResultJson ()
  {
    return getType ().equals (ELongRunningJobResultType.JSON) ? (IJson) m_aResult : null;
  }

  @Nonnull
  public String getAsString ()
  {
    switch (m_eType)
    {
      case FILE:
        return getResultFile ().getAbsolutePath ();
      case XML:
        return MicroWriter.getNodeAsString (getResultXML ());
      case TEXT:
        return getResultText ();
      case LINK:
        return getResultLink ().getAsStringWithEncodedParameters ();
      case JSON:
        return new JsonWriter ().writeAsString (getResultJson ());
      default:
        throw new IllegalStateException ("Unhandled type: " + m_eType);
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Type", m_eType).append ("Result", m_aResult).getToString ();
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
  public static LongRunningJobResult createXML (@Nonnull final IHCNode aResult)
  {
    return createXML (HCRenderer.getAsNode (aResult));
  }

  @Nonnull
  public static LongRunningJobResult createText (@Nonnull final String sResult)
  {
    return new LongRunningJobResult (ELongRunningJobResultType.TEXT, sResult);
  }

  @Nonnull
  public static LongRunningJobResult createExceptionText (@Nonnull final Throwable t)
  {
    return createText (t.getClass ()
                        .getName () +
                       " -  " +
                       t.getMessage () +
                       "\n" +
                       StackTraceHelper.getStackAsString (t));
  }

  @Nonnull
  public static LongRunningJobResult createLink (@Nonnull final ISimpleURL aResult)
  {
    return new LongRunningJobResult (ELongRunningJobResultType.LINK, aResult);
  }

  @Nonnull
  public static LongRunningJobResult createJson (@Nonnull final IJson aResult)
  {
    return new LongRunningJobResult (ELongRunningJobResultType.JSON, aResult);
  }
}
