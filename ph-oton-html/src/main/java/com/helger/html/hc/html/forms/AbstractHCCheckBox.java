/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.request.IHCRequestFieldBoolean;
import com.helger.html.request.IHCRequestFieldBooleanMultiValue;

/**
 * Represents an HTML &lt;input&gt; element with type "checkbox"
 *
 * @author Philip Helger, Boris Gregorcic
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCCheckBox <IMPLTYPE extends AbstractHCCheckBox <IMPLTYPE>> extends
                                         AbstractHCInput <IMPLTYPE>
{
  /** The default value of the "value" attribute in HTML */
  public static final String DEFAULT_VALUE = Boolean.TRUE.toString ();

  /**
   * Emit a hidden field that indicates that the check-box was in the request.
   */
  public static final boolean DEFAULT_EMIT_HIDDEN_FIELD = true;

  // Must match the constant in IRequestParamContainer (ph-web)
  public static final String HIDDEN_FIELD_PREFIX = "__";

  private boolean m_bEmitHiddenField = DEFAULT_EMIT_HIDDEN_FIELD;

  /**
   * Constructor
   */
  public AbstractHCCheckBox ()
  {
    super (EHCInputType.CHECKBOX);
    setValue (DEFAULT_VALUE);
  }

  /**
   * Constructor
   *
   * @param sName
   *        The name of this check-box (used as field name)
   * @param bChecked
   *        Whether or not it should be initially checked
   * @param sValue
   *        The value to be set for this check-box
   */
  protected AbstractHCCheckBox (@Nullable final String sName, final boolean bChecked, @Nullable final String sValue)
  {
    this ();
    setName (sName);
    setChecked (bChecked);
    setValue (sValue);
  }

  /**
   * Constructor
   *
   * @param aRF
   *        The request field
   */
  public AbstractHCCheckBox (@Nonnull final IHCRequestFieldBoolean aRF)
  {
    this (aRF.getFieldName (), aRF.isChecked (), DEFAULT_VALUE);
  }

  /**
   * Constructor
   *
   * @param aRF
   *        The request field
   */
  public AbstractHCCheckBox (@Nonnull final IHCRequestFieldBooleanMultiValue aRF)
  {
    this (aRF.getFieldName (), aRF.isChecked (), aRF.getValue ());
  }

  /**
   * @return Whether or not hidden fields will be emitted
   */
  public final boolean isEmitHiddenField ()
  {
    return m_bEmitHiddenField;
  }

  /**
   * Sets whether or not hidden fields will be emitted according to the passed
   * value
   *
   * @param bEmitHiddenField
   *        <code>true</code> to emit the hidden field, <code>false</code> to
   *        avoid.
   * @return This object for chaining
   */
  @Nonnull
  public final IMPLTYPE setEmitHiddenField (final boolean bEmitHiddenField)
  {
    m_bEmitHiddenField = bEmitHiddenField;
    return thisAsT ();
  }

  /**
   * Get the hidden field name for this checkbox.
   *
   * @return <code>null</code> if no field name ({@link #getName()}) is present
   *         or a non-<code>null</code> and non-empty string.
   */
  @Nullable
  public final String getHiddenFieldName ()
  {
    final String sFieldName = getName ();
    if (StringHelper.hasNoText (sFieldName))
      return null;

    return HIDDEN_FIELD_PREFIX + sFieldName;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    if (m_bEmitHiddenField)
    {
      final String sHiddenFieldName = getHiddenFieldName ();
      if (StringHelper.hasText (sHiddenFieldName))
        aTargetNode.addChild (new HCHiddenField (sHiddenFieldName, getValue ()));
    }
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("emitHiddenField", m_bEmitHiddenField)
                            .getToString ();
  }
}
