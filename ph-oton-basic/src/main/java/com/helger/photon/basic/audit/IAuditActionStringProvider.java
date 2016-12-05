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
package com.helger.photon.basic.audit;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.string.StringHelper;
import com.helger.json.IJsonArray;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;

/**
 * Interface that is used to convert audit parameters to an audit string
 * 
 * @author Philip Helger
 */
@FunctionalInterface
public interface IAuditActionStringProvider extends Serializable
{
  /**
   * Create the audit string that should be persisted.
   *
   * @param sAction
   *        Action
   * @param aArgs
   *        Optional arguments.
   * @return The string representation.
   */
  @Nonnull
  String createAuditString (@Nonnull String sAction, @Nullable Object [] aArgs);

  IAuditActionStringProvider PLAIN_STRING = (sAction, aArgs) -> {
    if (ArrayHelper.isEmpty (aArgs))
      return sAction;
    return sAction + '(' + StringHelper.getImploded (',', aArgs) + ')';
  };

  IAuditActionStringProvider JSON = (sAction, aArgs) -> {
    final IJsonArray aData = new JsonArray ().addAll (aArgs);
    return new JsonObject ().add (sAction, aData).getAsJsonString ();
  };
}
