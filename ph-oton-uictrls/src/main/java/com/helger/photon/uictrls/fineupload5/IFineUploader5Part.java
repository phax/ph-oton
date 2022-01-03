/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.fineupload5;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.jscode.JSAssocArray;

/**
 * Base interface for the Fine Uploader configuration parts
 *
 * @author Philip Helger
 */
public interface IFineUploader5Part extends Serializable
{
  @Nonnull
  JSAssocArray getJSCode ();

  @Nonnull
  @ReturnsMutableCopy
  default JSAssocArray getAsJSAA (@Nonnull final Map <String, String> aMap)
  {
    final JSAssocArray ret = new JSAssocArray ();
    for (final Map.Entry <String, String> aEntry : aMap.entrySet ())
      ret.add (aEntry.getKey (), aEntry.getValue ());
    return ret;
  }
}
