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
package com.helger.html.js.marshal;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.json.IJson;

/**
 * Implementation of the {@link IJSToStringConverter} interface, for converting
 * native {@link IJson} objects.
 * 
 * @author Philip Helger
 */
@Immutable
public final class JsonToStringConverter implements IJSToStringConverter, Serializable
{
  @Nullable
  public String objectToJSString (@Nullable final Object aObject, @Nonnull final JSType aType)
  {
    if (aObject != null && !(aObject instanceof IJson))
      throw new IllegalArgumentException ("The passed object is not of type IJson but " +
                                          aObject.getClass ().getName ());
    if (aType != JSType.JSON)
      throw new IllegalArgumentException ("Unexpected JSType '" + aType + "'! Only JSON is supported!");
    return aObject == null ? null : ((IJson) aObject).getAsString ();
  }

  @Override
  public boolean equals (final Object o)
  {
    return o instanceof JsonToStringConverter;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
