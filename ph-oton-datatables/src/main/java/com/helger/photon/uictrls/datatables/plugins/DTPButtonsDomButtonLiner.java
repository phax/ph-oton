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
package com.helger.photon.uictrls.datatables.plugins;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringHelper;
import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;

public class DTPButtonsDomButtonLiner
{
  /**
   * a string value which defines the HTML tag to use. There should be no spaces
   * or any other formatting - e.g. it should simply be span, div, li etc.
   */
  private EHTMLElement m_eTag;
  /**
   * a string value which defines the element's class name. Multiple classes can
   * be given using space separation.
   */
  private final HCHasCSSClasses m_aClassNames = new HCHasCSSClasses ();

  @NonNull
  public DTPButtonsDomButtonLiner setTag (@Nullable final EHTMLElement eTag)
  {
    m_eTag = eTag;
    return this;
  }

  @NonNull
  public DTPButtonsDomButtonLiner addClassName (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.addClass (aCSSClassProvider);
    return this;
  }

  @NonNull
  public DTPButtonsDomButtonLiner addClassName (@Nullable final ICSSClassProvider... aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @NonNull
  public DTPButtonsDomButtonLiner addClassName (@Nullable final Iterable <? extends ICSSClassProvider> aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @NonNull
  public IJSExpression getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_eTag != null)
      ret.add ("tag", m_eTag.getElementName ());
    final String sClassName = m_aClassNames.getAllClassesAsString ();
    if (StringHelper.isNotEmpty (sClassName))
      ret.add ("className", sClassName);
    return ret;
  }
}
