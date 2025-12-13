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
package com.helger.html.hc.ext;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.version.Version;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCConditionalCommentNode;
import com.helger.html.hc.impl.AbstractHCWrappingNode;
import com.helger.html.hc.impl.HCCommentNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;
import com.helger.xml.serialize.write.IXMLWriterSettings;

/**
 * Represents an HTML conditional comment for IE specific usage. E.g.
 *
 * <pre>
 * &lt;!--[if IE 6]&gt;
 * Special instructions for IE 6 here
 * &lt;![endif]--&gt;
 * </pre>
 *
 * Since 8.4.0 (dropping of IE support) this should not be needed anymore.
 *
 * @author Philip Helger
 */
@Immutable
public class HCConditionalCommentNode extends AbstractHCWrappingNode implements IHCConditionalCommentNode
{
  public static final Version IE5 = new Version (5);
  public static final Version IE6 = new Version (6);
  public static final Version IE7 = new Version (7);
  public static final Version IE8 = new Version (8);
  public static final Version IE9 = new Version (9);
  public static final Version IE10 = new Version (10);
  public static final Version IE11 = new Version (11);
  public static final String CONDITION_IF_IE_GENERIC = "if IE";
  public static final String CONDITION_IF_IE = "if IE ";
  public static final String CONDITION_IF_NOT_IE = "if !IE ";
  public static final String CONDITION_IF_LT_IE = "if lt IE ";
  public static final String CONDITION_IF_LTE_IE = "if lte IE ";
  public static final String CONDITION_IF_GT_IE = "if gt IE ";
  public static final String CONDITION_IF_GTE_IE = "if gte IE ";

  private final String m_sCondition;
  private final IHCNode m_aWrappedNode;

  public HCConditionalCommentNode (@NonNull @Nonempty final String sCondition, @NonNull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notEmpty (sCondition, "Condition");
    ValueEnforcer.notNull (aWrappedNode, "WrappedNode");
    ValueEnforcer.isFalse (aWrappedNode instanceof HCCommentNode,
                           "You cannot wrap a comment inside a conditional comment");
    ValueEnforcer.isFalse (aWrappedNode instanceof HCConditionalCommentNode,
                           "You cannot wrap a conditional comment inside another conditional comment");
    m_sCondition = sCondition;
    m_aWrappedNode = aWrappedNode;
  }

  /**
   * @return The condition for the conditional comment. Neither <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public String getCondition ()
  {
    return m_sCondition;
  }

  @NonNull
  public IHCNode getWrappedNode ()
  {
    return m_aWrappedNode;
  }

  @Override
  @Nullable
  protected IMicroNode internalConvertToMicroNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    // First convert the contained node to a micro node
    final IMicroNode aWrappedMicroNode = super.internalConvertToMicroNode (aConversionSettings);
    if (aWrappedMicroNode == null)
      return null;

    // Only create a newline when alignment is enabled
    final IXMLWriterSettings aXMLWriterSettings = aConversionSettings.getXMLWriterSettings ();
    final String sLineSeparator = aXMLWriterSettings.getIndent ().isAlign () ? aXMLWriterSettings.getNewLineString ()
                                                                             : "";
    final String sPayload = MicroWriter.getNodeAsString (aWrappedMicroNode, aXMLWriterSettings);

    // Now wrap the created XML in the special format required for a conditional
    // comment
    // Note: this class assumes XML comment rendering with no space between
    // "<!--" and the provided payload
    final HCCommentNode aCommentNode = new HCCommentNode ('[' +
                                                          m_sCondition +
                                                          "]>" +
                                                          sLineSeparator +
                                                          sPayload +
                                                          "<![endif]");

    return HCRenderer.getAsNode (aCommentNode, aConversionSettings);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("condition", m_sCondition)
                                       .append ("wrappedNode", m_aWrappedNode)
                                       .getToString ();
  }

  @NonNull
  public static HCConditionalCommentNode createForIE (@NonNull final IHCNode aWrappedNode)
  {
    return new HCConditionalCommentNode (CONDITION_IF_IE_GENERIC, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion (@NonNull final Version aVersion,
                                                                  @NonNull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_IE + aVersion.getAsString (), aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion5 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE5, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion6 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE6, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion7 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE7, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion8 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE8, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion9 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE9, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion10 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE10, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEExactVersion11 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE11, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIENotVersion (@NonNull final Version aVersion,
                                                                @NonNull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_NOT_IE + aVersion.getAsString (), aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion (@NonNull final Version aVersion,
                                                                      @NonNull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");
    return new HCConditionalCommentNode (CONDITION_IF_LT_IE + aVersion.getAsString (), aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion5 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE5, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion6 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE6, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion7 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE7, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion8 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE8, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion9 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE9, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion10 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE10, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerThanVersion11 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE11, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion (@NonNull final Version aVersion,
                                                                             @NonNull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_LTE_IE + aVersion.getAsString (), aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion5 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE5, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion6 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE6, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion7 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE7, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion8 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE8, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion9 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE9, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion10 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE10, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion11 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE11, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion (@NonNull final Version aVersion,
                                                                        @NonNull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_GT_IE + aVersion.getAsString (), aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion5 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE5, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion6 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE6, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion7 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE7, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion8 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE8, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion9 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE9, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion10 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE10, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterThanVersion11 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE11, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion (@NonNull final Version aVersion,
                                                                               @NonNull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_GTE_IE + aVersion.getAsString (), aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion5 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE5, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion6 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE6, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion7 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE7, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion8 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE8, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion9 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE9, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion10 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE10, aWrappedNode);
  }

  @NonNull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion11 (@NonNull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE11, aWrappedNode);
  }

  @Nullable
  public static HCConditionalCommentNode getFromStringOrNull (@Nullable final String sCondition,
                                                              @NonNull final IHCNode aNode)
  {
    if (StringHelper.isEmpty (sCondition))
      return null;
    return getAsConditionalCommentNode (sCondition, aNode);
  }

  /**
   * Get the passed node wrapped in a conditional comment. This is a sanity method for
   * <code>new HCConditionalCommentNode (this, sCondition)</code>. If this node is already an
   * {@link HCConditionalCommentNode} the object is simply casted.
   *
   * @param sCondition
   *        The condition to us. May neither be <code>null</code> nor empty.
   * @param aNode
   *        The HC node to be wrapped. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @NonNull
  public static HCConditionalCommentNode getAsConditionalCommentNode (@NonNull @Nonempty final String sCondition,
                                                                      @NonNull final IHCNode aNode)
  {
    if (aNode instanceof final HCConditionalCommentNode aCondNode)
      return aCondNode;
    return new HCConditionalCommentNode (sCondition, aNode);
  }
}
