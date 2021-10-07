/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.typeahead;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Domain object to easily handle the selected elements of a
 * {@link TypeaheadEdit}.
 *
 * @author Philip Helger
 */
public class TypeaheadEditSelection
{
  private final String m_sEditValue;
  private final String m_sHiddenFieldValue;

  /**
   * Constructor
   *
   * @param sEditValue
   *        The value of the edit field. May be <code>null</code>.
   * @param sHiddenFieldValue
   *        The value of the hidden field with the ID. May be <code>null</code>.
   */
  public TypeaheadEditSelection (@Nullable final String sEditValue, @Nullable final String sHiddenFieldValue)
  {
    m_sEditValue = sEditValue;
    m_sHiddenFieldValue = sHiddenFieldValue;
  }

  /**
   * @return The value of the edit field. May be <code>null</code> if nothing
   *         was entered.
   */
  @Nullable
  public String getEditValue ()
  {
    return m_sEditValue;
  }

  /**
   * @return The value of the hidden field with the selected ID of the object.
   *         May be <code>null</code> if no valid object was selected.
   */
  @Nullable
  public String getHiddenFieldValue ()
  {
    return m_sHiddenFieldValue;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("editValue", m_sEditValue).append ("hiddenFieldValue", m_sHiddenFieldValue).getToString ();
  }

  /**
   * Get the current selection in the case that it is mandatory to select an
   * available object.
   *
   * @param aWPEC
   *        The current web page execution context. May not be <code>null</code>
   *        .
   * @param sEditFieldName
   *        The name of the edit input field.
   * @param sHiddenFieldName
   *        The name of the hidden field with the ID.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static TypeaheadEditSelection getSelectionForRequiredObject (@Nonnull final IWebPageExecutionContext aWPEC,
                                                                      @Nullable final String sEditFieldName,
                                                                      @Nullable final String sHiddenFieldName)
  {
    ValueEnforcer.notNull (aWPEC, "WPEC");

    String sEditValue = aWPEC.params ().getAsString (sEditFieldName);
    String sHiddenFieldValue = aWPEC.params ().getAsString (sHiddenFieldName);
    if (StringHelper.hasText (sHiddenFieldValue))
    {
      if (StringHelper.hasNoText (sEditValue))
      {
        // The content of the edit field was deleted after a valid item was once
        // selected
        sHiddenFieldValue = null;
      }
    }
    else
    {
      if (StringHelper.hasText (sEditValue))
      {
        // No ID but a text -> no object selected but only a string typed
        sEditValue = null;
      }
    }

    return new TypeaheadEditSelection (sEditValue, sHiddenFieldValue);
  }
}
