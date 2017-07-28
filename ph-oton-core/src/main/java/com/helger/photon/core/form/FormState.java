/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.form;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.attr.AttributeContainerAny;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ITypedObject;
import com.helger.commons.type.ObjectType;
import com.helger.json.JsonObject;

@Immutable
public class FormState implements ITypedObject <String>, Serializable
{
  public static final ObjectType OT_FORM_STATE = new ObjectType ("formstate");

  private final String m_sPageID;
  private final LocalDateTime m_aDT;
  private final String m_sFlowID;
  private final AttributeContainerAny <String> m_aAttrs;

  public FormState (@Nonnull @Nonempty final String sPageID,
                    @Nonnull @Nonempty final String sFlowID,
                    @Nonnull final AttributeContainerAny <String> aAttrs)
  {
    ValueEnforcer.notEmpty (sPageID, "PageID");
    ValueEnforcer.notEmpty (sFlowID, "FlowID");
    ValueEnforcer.notNull (aAttrs, "Attrs");

    m_sPageID = sPageID;
    m_aDT = PDTFactory.getCurrentLocalDateTime ();
    m_sFlowID = sFlowID;
    m_aAttrs = aAttrs;
  }

  @Nonnull
  public ObjectType getObjectType ()
  {
    return OT_FORM_STATE;
  }

  @Nonnull
  @Nonempty
  public String getPageID ()
  {
    return m_sPageID;
  }

  @Nonnull
  public LocalDateTime getDateTime ()
  {
    return m_aDT;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return getFlowID ();
  }

  @Nonnull
  @Nonempty
  public String getFlowID ()
  {
    return m_sFlowID;
  }

  @Nonnull
  @ReturnsMutableObject
  public AttributeContainerAny <String> attrs ()
  {
    return m_aAttrs;
  }

  @Nonnull
  public JsonObject getAsJsonObject ()
  {
    final JsonObject ret = new JsonObject ();
    for (final Map.Entry <String, Object> aEntry : m_aAttrs.entrySet ())
    {
      final String sKey = aEntry.getKey ();
      final Object aValue = aEntry.getValue ();
      if (aValue instanceof String)
        ret.add (sKey, aValue);
      else
        if (aValue instanceof String [])
          ret.add (sKey, aValue);
      // else e.g. fileitem -> ignore
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("pageID", m_sPageID)
                                       .append ("DT", m_aDT)
                                       .append ("flowID", m_sFlowID)
                                       .append ("Attrs", m_aAttrs)
                                       .getToString ();
  }
}
