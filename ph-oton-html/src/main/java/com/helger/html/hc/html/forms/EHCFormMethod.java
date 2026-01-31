/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.forms;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.lang.EnumHelper;
import com.helger.base.string.StringHelper;
import com.helger.html.hc.html.IHCHasHTMLAttributeValue;

/**
 * An enumeration that can be used in
 * {@link com.helger.html.hc.html.forms.HCForm} objects to define the used
 * method.
 *
 * @author Philip Helger
 */
public enum EHCFormMethod implements IHCHasHTMLAttributeValue
{
  GET ("GET"),
  POST ("POST");

  private final String m_sMethod;

  EHCFormMethod (@NonNull final String sMethod)
  {
    m_sMethod = sMethod;
  }

  @NonNull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sMethod;
  }

  @Nullable
  public static EHCFormMethod getFromAttrValueOrNull (@Nullable final String sAttrValue)
  {
    if (StringHelper.isEmpty (sAttrValue))
      return null;
    return EnumHelper.findFirst (EHCFormMethod.class, x -> x.getAttrValue ().equals (sAttrValue));
  }
}
