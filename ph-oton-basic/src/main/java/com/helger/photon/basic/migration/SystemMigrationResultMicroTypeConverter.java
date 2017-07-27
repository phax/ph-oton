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
package com.helger.photon.basic.migration;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ContainsSoftMigration;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringParser;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.convert.IMicroTypeConverter;
import com.helger.xml.microdom.util.MicroHelper;

public final class SystemMigrationResultMicroTypeConverter implements IMicroTypeConverter <SystemMigrationResult>
{
  private static final String ATTR_MIGRATION_ID = "id";
  private static final String ATTR_EXECUTION_LDT = "executionldt";
  private static final String ATTR_SUCCESS = "success";
  private static final String ELEMENT_ERROR_MSG = "errormsg";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final SystemMigrationResult aValue,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_MIGRATION_ID, aValue.getID ());
    aElement.setAttributeWithConversion (ATTR_EXECUTION_LDT, aValue.getExecutionDateTime ());
    aElement.setAttribute (ATTR_SUCCESS, aValue.isSuccess ());
    if (aValue.hasErrorMessage ())
      aElement.appendElement (sNamespaceURI, ELEMENT_ERROR_MSG).appendText (aValue.getErrorMessage ());
    return aElement;
  }

  @Nonnull
  @ContainsSoftMigration
  public SystemMigrationResult convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sID = aElement.getAttributeValue (ATTR_MIGRATION_ID);
    LocalDateTime aExecLDT = aElement.getAttributeValueWithConversion (ATTR_EXECUTION_LDT, LocalDateTime.class);
    if (aExecLDT == null)
    {
      // Soft migration
      final ZonedDateTime aExecDT = aElement.getAttributeValueWithConversion ("executiondt", ZonedDateTime.class);
      if (aExecDT != null)
        aExecLDT = aExecDT.toLocalDateTime ();
    }
    final String sSuccess = aElement.getAttributeValue (ATTR_SUCCESS);
    final boolean bSuccess = StringParser.parseBool (sSuccess);
    final String sErrorMsg = MicroHelper.getChildTextContent (aElement, ELEMENT_ERROR_MSG);

    return new SystemMigrationResult (sID, aExecLDT, bSuccess, sErrorMsg);
  }
}
