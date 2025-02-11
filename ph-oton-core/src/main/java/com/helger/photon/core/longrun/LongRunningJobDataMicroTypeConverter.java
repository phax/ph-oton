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
package com.helger.photon.core.longrun;

import java.io.File;
import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.state.ETriState;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.ReadOnlyMultilingualText;
import com.helger.commons.url.SimpleURL;
import com.helger.json.serialize.JsonReader;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.IMicroTypeConverter;
import com.helger.xml.microdom.convert.MicroTypeConverter;
import com.helger.xml.microdom.serialize.MicroReader;

/**
 * Micro type converter for class {@link LongRunningJobData}.
 *
 * @author Philip Helger
 */
public final class LongRunningJobDataMicroTypeConverter implements IMicroTypeConverter <LongRunningJobData>
{
  private static final String ATTR_ID = "id";
  private static final String ATTR_STARTDT = "startdt";
  private static final String ATTR_ENDDT = "enddt";
  private static final String ATTR_EXECSUCCESS = "execsuccess";
  private static final String ATTR_STARTINGUSERID = "startinguserid";
  private static final String ELEMENT_DESCRIPTION = "description";
  private static final String ELEMENT_RESULT = "result";
  private static final String ATTR_TYPE = "type";

  @Nullable
  public IMicroElement convertToMicroElement (@Nonnull final LongRunningJobData aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IMicroElement eJobData = new MicroElement (sNamespaceURI, sTagName);
    eJobData.setAttribute (ATTR_ID, aValue.getID ());

    // Description
    eJobData.appendChild (MicroTypeConverter.convertToMicroElement (aValue.getJobDescription (), sNamespaceURI, ELEMENT_DESCRIPTION));

    eJobData.setAttributeWithConversion (ATTR_STARTDT, aValue.getStartDateTime ());
    eJobData.setAttribute (ATTR_STARTINGUSERID, aValue.getStartingUserID ());

    eJobData.setAttributeWithConversion (ATTR_ENDDT, aValue.getEndDateTime ());
    eJobData.setAttribute (ATTR_EXECSUCCESS, aValue.getExecutionSuccess ().getID ());

    // Result
    final IMicroElement eResult = eJobData.appendElement (sNamespaceURI, ELEMENT_RESULT);
    eResult.setAttribute (ATTR_TYPE, aValue.getResult ().getType ().getID ());
    eResult.appendText (aValue.getResult ().getAsString ());
    return eJobData;
  }

  @Nullable
  public LongRunningJobData convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sID = aElement.getAttributeValue (ATTR_ID);
    final IMultilingualText aJobDescription = MicroTypeConverter.convertToNative (aElement.getFirstChildElement (ELEMENT_DESCRIPTION),
                                                                                  ReadOnlyMultilingualText.class);
    final LocalDateTime aStartDateTime = aElement.getAttributeValueWithConversion (ATTR_STARTDT, LocalDateTime.class);
    final String sStartingUserID = aElement.getAttributeValue (ATTR_STARTINGUSERID);

    final LocalDateTime aEndDateTime = aElement.getAttributeValueWithConversion (ATTR_ENDDT, LocalDateTime.class);
    final ETriState eExecSuccess = ETriState.getFromIDOrUndefined (aElement.getAttributeValue (ATTR_EXECSUCCESS));

    final IMicroElement eResult = aElement.getFirstChildElement (ELEMENT_RESULT);
    final ELongRunningJobResultType eResultType = ELongRunningJobResultType.getFromIDOrNull (eResult.getAttributeValue (ATTR_TYPE));
    final String sResultText = eResult.getTextContent ();
    LongRunningJobResult aResult;
    switch (eResultType)
    {
      case FILE:
        aResult = LongRunningJobResult.createFile (new File (sResultText));
        break;
      case XML:
        aResult = LongRunningJobResult.createXML (MicroReader.readMicroXML (sResultText));
        break;
      case TEXT:
        aResult = LongRunningJobResult.createText (sResultText);
        break;
      case LINK:
        aResult = LongRunningJobResult.createLink (new SimpleURL (sResultText));
        break;
      case JSON:
        aResult = LongRunningJobResult.createJson (JsonReader.readFromString (sResultText));
        break;
      default:
        throw new IllegalStateException ("Unknown type: " + eResultType);
    }

    return new LongRunningJobData (sID, aStartDateTime, aEndDateTime, eExecSuccess, sStartingUserID, aJobDescription, aResult);
  }
}
