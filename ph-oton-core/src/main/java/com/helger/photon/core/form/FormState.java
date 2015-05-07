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
package com.helger.photon.core.form;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.collections.attrs.MapBasedAttributeContainer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ITypedObject;
import com.helger.commons.type.ObjectType;
import com.helger.datetime.PDTFactory;
import com.helger.html.js.builder.JSArray;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.json.impl.JsonObject;

@Immutable
public class FormState implements ITypedObject <String>, Serializable
{
  public static final ObjectType OT_FORM_STATE = new ObjectType ("formstate");

  private final String m_sPageID;
  private final DateTime m_aDT;
  private final String m_sFlowID;
  private final MapBasedAttributeContainer m_aAttrs;

  public FormState (@Nonnull @Nonempty final String sPageID,
                    @Nonnull @Nonempty final String sFlowID,
                    @Nonnull final MapBasedAttributeContainer aAttrs)
  {
    ValueEnforcer.notEmpty (sPageID, "PageID");
    ValueEnforcer.notEmpty (sFlowID, "FlowID");
    ValueEnforcer.notNull (aAttrs, "Attrs");

    m_sPageID = sPageID;
    m_aDT = PDTFactory.getCurrentDateTime ();
    m_sFlowID = sFlowID;
    m_aAttrs = aAttrs;
  }

  @Nonnull
  public ObjectType getTypeID ()
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
  public DateTime getDateTime ()
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
  public MapBasedAttributeContainer getAttributes ()
  {
    return m_aAttrs;
  }

  @Nonnull
  public JSAssocArray getAsAssocArray ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    for (final Map.Entry <String, Object> aEntry : m_aAttrs.getAllAttributes ().entrySet ())
    {
      final String sKey = aEntry.getKey ();
      final Object aValue = aEntry.getValue ();
      if (aValue instanceof String)
        ret.add (sKey, (String) aValue);
      else
        if (aValue instanceof String [])
        {
          final JSArray aArray = new JSArray ();
          for (final String sElement : (String []) aValue)
            aArray.add (sElement);
          ret.add (sKey, aArray);
        }
      // else e.g. fileitem -> ignore
    }
    return ret;
  }

  @Nonnull
  public JsonObject getAsJsonObject ()
  {
    final JsonObject ret = new JsonObject ();
    for (final Map.Entry <String, Object> aEntry : m_aAttrs.getAllAttributes ().entrySet ())
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
                                       .append ("attrs", m_aAttrs)
                                       .toString ();
  }
}
