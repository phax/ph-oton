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
package com.helger.photon.core.interror;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IHasMicroNodeRepresentation;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

@NotThreadSafe
public class InternalErrorMetadata implements IHasMicroNodeRepresentation
{
  @Immutable
  public static final class Entry implements IHasMicroNodeRepresentation
  {
    private final String m_sKey;
    private final String m_sValue;

    public Entry (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
    {
      m_sKey = ValueEnforcer.notEmpty (sKey, "Key");
      m_sValue = sValue;
    }

    @Nonnull
    @Nonempty
    public String getKey ()
    {
      return m_sKey;
    }

    @Nullable
    public String getValue ()
    {
      return m_sValue;
    }

    @Nonnull
    @Nonempty
    public String getAsString ()
    {
      return m_sKey + ": " + m_sValue;
    }

    @Nonnull
    public IMicroElement getAsMicroNode ()
    {
      final IMicroElement eEntry = new MicroElement ("entry");
      eEntry.setAttribute ("key", m_sKey);
      eEntry.appendText (m_sValue);
      return eEntry;
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger (InternalErrorMetadata.class);

  private final String m_sErrorID;
  private final ICommonsList <Entry> m_aFields = new CommonsArrayList <> ();
  private final ICommonsList <Entry> m_aRequestFields = new CommonsArrayList <> ();
  private final ICommonsList <Entry> m_aRequestHeaders = new CommonsArrayList <> ();
  private final ICommonsList <Entry> m_aRequestParameters = new CommonsArrayList <> ();
  private final ICommonsList <Entry> m_aRequestCookies = new CommonsArrayList <> ();
  private final ICommonsList <Entry> m_aSessionFields = new CommonsArrayList <> ();

  public InternalErrorMetadata (@Nullable final String sErrorID)
  {
    m_sErrorID = sErrorID;
  }

  @Nullable
  public String getErrorID ()
  {
    return m_sErrorID;
  }

  @Nonnull
  public InternalErrorMetadata addField (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
  {
    m_aFields.add (new Entry (sKey, sValue));
    return this;
  }

  @Nonnull
  public InternalErrorMetadata addFieldRetrievalError (@Nonnull @Nonempty final String sKey, @Nonnull final Throwable t)
  {
    final String sValue = "Failed to get " + sKey + ": " + t.getMessage () + " -- " + t.getClass ().getName ();
    LOGGER.warn (sValue);
    return addField (sKey, sValue);
  }

  @Nullable
  public String getFieldValue (@Nullable final String sKey, @Nullable final String sDefaultValue)
  {
    // Linear effort
    final Entry aEntry = StringHelper.hasNoText (sKey) ? null : m_aFields.findFirst (x -> x.getKey ().equals (sKey));
    return aEntry != null ? aEntry.getValue () : sDefaultValue;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Entry> getAllFields ()
  {
    return m_aFields.getClone ();
  }

  @Nonnull
  public InternalErrorMetadata addRequestField (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
  {
    m_aRequestFields.add (new Entry (sKey, sValue));
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Entry> getAllRequestFields ()
  {
    return m_aRequestFields.getClone ();
  }

  @Nonnull
  public InternalErrorMetadata addRequestHeader (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
  {
    m_aRequestHeaders.add (new Entry (sKey, sValue));
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Entry> getAllRequestHeaders ()
  {
    return m_aRequestHeaders.getClone ();
  }

  @Nonnull
  public InternalErrorMetadata addRequestParameter (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
  {
    m_aRequestParameters.add (new Entry (sKey, sValue));
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Entry> getAllRequestParameters ()
  {
    return m_aRequestParameters.getClone ();
  }

  @Nonnull
  public InternalErrorMetadata addRequestCookie (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
  {
    m_aRequestCookies.add (new Entry (sKey, sValue));
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Entry> getAllRequestCookies ()
  {
    return m_aRequestCookies.getClone ();
  }

  @Nonnull
  public InternalErrorMetadata addSessionField (@Nonnull @Nonempty final String sKey, @Nullable final String sValue)
  {
    m_aSessionFields.add (new Entry (sKey, sValue));
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Entry> getAllSessionFields ()
  {
    return m_aSessionFields.getClone ();
  }

  @Nonnull
  public String getAsString ()
  {
    final StringBuilder aSB = new StringBuilder ();
    for (final Entry aEntry : m_aFields)
      aSB.append (aEntry.getAsString ()).append ('\n');

    if (!m_aRequestFields.isEmpty ())
    {
      aSB.append ("Request:\n");
      for (final Entry aEntry : m_aRequestFields)
        aSB.append ("  ").append (aEntry.getAsString ()).append ('\n');
    }

    if (!m_aRequestHeaders.isEmpty ())
    {
      aSB.append ("Request header:\n");
      for (final Entry aEntry : m_aRequestHeaders)
        aSB.append ("  ").append (aEntry.getAsString ()).append ('\n');
    }

    if (!m_aRequestParameters.isEmpty ())
    {
      aSB.append ("Request parameters:\n");
      for (final Entry aEntry : m_aRequestParameters)
        aSB.append ("  ").append (aEntry.getAsString ()).append ('\n');
    }

    if (!m_aRequestCookies.isEmpty ())
    {
      aSB.append ("Request cookies:\n");
      for (final Entry aEntry : m_aRequestCookies)
        aSB.append ("  ").append (aEntry.getAsString ()).append ('\n');
    }

    if (!m_aSessionFields.isEmpty ())
    {
      aSB.append ("Session field:\n");
      for (final Entry aEntry : m_aSessionFields)
        aSB.append ("  ").append (aEntry.getAsString ()).append ('\n');
    }

    return aSB.toString ();
  }

  @Nonnull
  public IMicroElement getAsMicroNode ()
  {
    final IMicroElement eMetadata = new MicroElement ("metadata");
    eMetadata.setAttribute ("errorid", m_sErrorID);

    {
      final IMicroElement eFields = eMetadata.appendElement ("fields");
      for (final Entry aEntry : m_aFields)
        eFields.appendChild (aEntry.getAsMicroNode ());
    }

    if (!m_aRequestFields.isEmpty ())
    {
      final IMicroElement eRequestFields = eMetadata.appendElement ("requestfields");
      for (final Entry aEntry : m_aRequestFields)
        eRequestFields.appendChild (aEntry.getAsMicroNode ());
    }

    if (!m_aRequestHeaders.isEmpty ())
    {
      final IMicroElement eRequestHeaders = eMetadata.appendElement ("requestheaders");
      for (final Entry aEntry : m_aRequestHeaders)
        eRequestHeaders.appendChild (aEntry.getAsMicroNode ());
    }

    if (!m_aRequestParameters.isEmpty ())
    {
      final IMicroElement eRequestParameters = eMetadata.appendElement ("requestparameters");
      for (final Entry aEntry : m_aRequestParameters)
        eRequestParameters.appendChild (aEntry.getAsMicroNode ());
    }

    if (!m_aRequestCookies.isEmpty ())
    {
      final IMicroElement eRequestCookies = eMetadata.appendElement ("requestcookies");
      for (final Entry aEntry : m_aRequestCookies)
        eRequestCookies.appendChild (aEntry.getAsMicroNode ());
    }

    if (!m_aSessionFields.isEmpty ())
    {
      final IMicroElement eSessionFields = eMetadata.appendElement ("sessionfields");
      for (final Entry aEntry : m_aSessionFields)
        eSessionFields.appendChild (aEntry.getAsMicroNode ());
    }
    return eMetadata;
  }
}
