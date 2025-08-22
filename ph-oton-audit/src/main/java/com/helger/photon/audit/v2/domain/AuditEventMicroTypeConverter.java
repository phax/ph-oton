/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.audit.v2.domain;

import java.time.LocalDateTime;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.state.ESuccess;
import com.helger.base.string.StringHelper;
import com.helger.base.string.StringParser;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.photon.audit.EAuditActionType;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.MicroQName;
import com.helger.xml.microdom.convert.IMicroTypeConverter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Convert {@link AuditEvent} objects to {@link IMicroElement} and vice versa.
 *
 * @author Philip Helger
 */
@Immutable
public class AuditEventMicroTypeConverter implements IMicroTypeConverter <AuditEvent>
{
  public static final IMicroQName ATTR_ID = new MicroQName ("id");
  public static final IMicroQName ATTR_CDT = new MicroQName ("cdt");
  public static final IMicroQName ATTR_ACTOR = new MicroQName ("actor");
  public static final IMicroQName ATTR_ORIGIN = new MicroQName ("origin");
  public static final IMicroQName ATTR_ACTION = new MicroQName ("action");
  public static final IMicroQName ATTR_SUCCESS = new MicroQName ("success");
  public static final String ELEMENT_FIELD = "field";
  public static final IMicroQName ATTR_NAME = new MicroQName ("name");
  public static final IMicroQName ATTR_VALUE = new MicroQName ("value");

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final AuditEvent aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_ID, aObject.getID ());
    aElement.setAttributeWithConversion (ATTR_CDT, aObject.getCreationDateTime ());
    aElement.setAttribute (ATTR_ACTOR, aObject.getActor ());
    aElement.setAttribute (ATTR_ORIGIN, aObject.getOrigin ());
    aElement.setAttribute (ATTR_ACTION, aObject.getActionID ());
    if (aObject.hasSuccess ())
      aElement.setAttribute (ATTR_SUCCESS, aObject.getSuccess ().isSuccess ());
    for (final AuditField aField : aObject.fields ())
      aElement.addElementNS (sNamespaceURI, ELEMENT_FIELD)
              .setAttribute (ATTR_NAME, aField.getName ())
              .setAttribute (ATTR_VALUE, aField.getValue ());
    return aElement;
  }

  @Nonnull
  public AuditEvent convertToNative (@Nonnull final IMicroElement aElement)
  {
    final long nID = aElement.getAttributeValueAsLong (ATTR_ID, -1);
    final LocalDateTime aCDT = aElement.getAttributeValueWithConversion (ATTR_CDT, LocalDateTime.class);

    final String sActor = aElement.getAttributeValue (ATTR_ACTOR);
    final String sOrigin = aElement.getAttributeValue (ATTR_ORIGIN);

    final String sActionID = aElement.getAttributeValue (ATTR_ACTION);
    final EAuditActionType eAction = EAuditActionType.getFromIDOrNull (sActionID);
    if (eAction == null && StringHelper.isNotEmpty (sActionID))
      throw new IllegalStateException ("Failed to parse action type '" + sActionID + "'");

    final String sSuccess = aElement.getAttributeValue (ATTR_SUCCESS);
    final ESuccess eSuccess = StringHelper.isEmpty (sSuccess) ? null : ESuccess.valueOf (StringParser.parseBool (
                                                                                                                 sSuccess));

    final ICommonsList <AuditField> aFields = new CommonsArrayList <> ();
    for (final IMicroElement eField : aElement.getAllChildElements (ELEMENT_FIELD))
      aFields.add (new AuditField (eField.getAttributeValue (ATTR_NAME), eField.getAttributeValue (ATTR_VALUE)));

    return new AuditEvent (nID, aCDT, sActor, sOrigin, eAction, eSuccess, aFields);
  }
}
