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
package com.helger.appbasics.migration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.IMicroTypeConverter;
import com.helger.commons.microdom.impl.MicroElement;
import com.helger.commons.microdom.utils.MicroUtils;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;

public final class SystemMigrationResultMicroTypeConverter implements IMicroTypeConverter
{
  private static final String ATTR_MIGRATION_ID = "id";
  private static final String ATTR_EXECUTION_DT = "executiondt";
  private static final String ATTR_SUCCESS = "success";
  private static final String ELEMENT_ERROR_MSG = "errormsg";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull @Nonempty final String sTagName)
  {
    final SystemMigrationResult aValue = (SystemMigrationResult) aObject;
    final IMicroElement aElement = new MicroElement (sNamespaceURI, sTagName);
    aElement.setAttribute (ATTR_MIGRATION_ID, aValue.getID ());
    aElement.setAttributeWithConversion (ATTR_EXECUTION_DT, aValue.getExecutionDateTime ());
    aElement.setAttribute (ATTR_SUCCESS, Boolean.toString (aValue.isSuccess ()));
    if (StringHelper.hasText (aValue.getErrorMessage ()))
      aElement.appendElement (sNamespaceURI, ELEMENT_ERROR_MSG).appendText (aValue.getErrorMessage ());
    return aElement;
  }

  @Nonnull
  public SystemMigrationResult convertToNative (@Nonnull final IMicroElement aElement)
  {
    final String sID = aElement.getAttributeValue (ATTR_MIGRATION_ID);
    final DateTime aExecDT = aElement.getAttributeValueWithConversion (ATTR_EXECUTION_DT, DateTime.class);
    final String sSuccess = aElement.getAttributeValue (ATTR_SUCCESS);
    final boolean bSuccess = StringParser.parseBool (sSuccess);
    final String sErrorMsg = MicroUtils.getChildTextContent (aElement, ELEMENT_ERROR_MSG);
    return new SystemMigrationResult (sID, aExecDT, bSuccess, sErrorMsg);
  }
}
