/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.version.Version;
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

  public HCConditionalCommentNode (@Nonnull @Nonempty final String sCondition, @Nonnull final IHCNode aWrappedNode)
  {
    m_sCondition = ValueEnforcer.notEmpty (sCondition, "Condition");
    ValueEnforcer.notNull (aWrappedNode, "WrappedNode");
    ValueEnforcer.isFalse (aWrappedNode instanceof HCCommentNode,
                           "You cannot wrap a comment inside a conditional comment");
    ValueEnforcer.isFalse (aWrappedNode instanceof HCConditionalCommentNode,
                           "You cannot wrap a conditional comment inside another conditional comment");
    m_aWrappedNode = aWrappedNode;
  }

  /**
   * @return The condition for the conditional comment. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getCondition ()
  {
    return m_sCondition;
  }

  @Nonnull
  public IHCNode getWrappedNode ()
  {
    return m_aWrappedNode;
  }

  @Override
  @Nullable
  protected IMicroNode internalConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // First convert the contained node to a micro node
    final IMicroNode aWrappedMicroNode = super.internalConvertToMicroNode (aConversionSettings);

    // Only create a newline when alignment is enabled
    final IXMLWriterSettings aXMLWriterSettings = aConversionSettings.getXMLWriterSettings ();
    final String sLineSeparator = aXMLWriterSettings.getIndent ().isAlign () ? aXMLWriterSettings.getNewLineString ()
                                                                             : "";

    // Now wrap the created XML in the special format required for a conditional
    // comment
    final HCCommentNode aCommentNode = new HCCommentNode ('[' +
                                                          m_sCondition +
                                                          "]>" +
                                                          sLineSeparator +
                                                          MicroWriter.getNodeAsString (aWrappedMicroNode,
                                                                                       aXMLWriterSettings) +
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

  @Nonnull
  public static HCConditionalCommentNode createForIE (@Nonnull final IHCNode aWrappedNode)
  {
    return new HCConditionalCommentNode (CONDITION_IF_IE_GENERIC, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion (@Nonnull final Version aVersion,
                                                                  @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_IE + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEExactVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEExactVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIENotVersion (@Nonnull final Version aVersion,
                                                                @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_NOT_IE + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion (@Nonnull final Version aVersion,
                                                                      @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");
    return new HCConditionalCommentNode (CONDITION_IF_LT_IE + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerThanVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion (@Nonnull final Version aVersion,
                                                                             @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_LTE_IE + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIELowerOrEqualThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIELowerOrEqualThanVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion (@Nonnull final Version aVersion,
                                                                        @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_GT_IE + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterThanVersion (IE11, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion (@Nonnull final Version aVersion,
                                                                               @Nonnull final IHCNode aWrappedNode)
  {
    ValueEnforcer.notNull (aVersion, "Version");

    return new HCConditionalCommentNode (CONDITION_IF_GTE_IE + aVersion.getAsString (), aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion5 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE5, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion6 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE6, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion7 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE7, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion8 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE8, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion9 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE9, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion10 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE10, aWrappedNode);
  }

  @Nonnull
  public static HCConditionalCommentNode createForIEGreaterOrEqualThanVersion11 (@Nonnull final IHCNode aWrappedNode)
  {
    return createForIEGreaterOrEqualThanVersion (IE11, aWrappedNode);
  }

  @Nullable
  public static HCConditionalCommentNode getFromStringOrNull (@Nullable final String sCondition,
                                                              @Nonnull final IHCNode aNode)
  {
    if (StringHelper.hasNoText (sCondition))
      return null;
    return getAsConditionalCommentNode (sCondition, aNode);
  }

  /**
   * Get the passed node wrapped in a conditional comment. This is a sanity
   * method for <code>new HCConditionalCommentNode (this, sCondition)</code>. If
   * this node is already an {@link HCConditionalCommentNode} the object is
   * simply casted.
   *
   * @param sCondition
   *        The condition to us. May neither be <code>null</code> nor empty.
   * @param aNode
   *        The HC node to be wrapped. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static HCConditionalCommentNode getAsConditionalCommentNode (@Nonnull @Nonempty final String sCondition,
                                                                      @Nonnull final IHCNode aNode)
  {
    if (aNode instanceof HCConditionalCommentNode)
      return (HCConditionalCommentNode) aNode;
    return new HCConditionalCommentNode (sCondition, aNode);
  }
}
