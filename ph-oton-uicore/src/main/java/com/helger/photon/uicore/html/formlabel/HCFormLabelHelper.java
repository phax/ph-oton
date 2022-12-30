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
package com.helger.photon.uicore.html.formlabel;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.impl.CommonsEnumMap;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCNodeWithChildren;

@NotThreadSafe
public final class HCFormLabelHelper
{
  /** The default sign for optional elements */
  public static final String DEFAULT_SIGN_OPTIONAL = "";
  /** The default sign for mandatory elements */
  public static final String DEFAULT_SIGN_MANDATORY = "*";
  /**
   * The default sign for mandatory elements.
   *
   * @deprecated Since v8.3.1; Use {@link #DEFAULT_SIGN_MANDATORY} instead
   */
  @Deprecated
  public static final String SIGN_MANDATORY = DEFAULT_SIGN_MANDATORY;
  /** The default sign for alternative elements */
  public static final String DEFAULT_SIGN_ALTERNATIVE = "°";
  /**
   * The default sign for alternative elements.
   *
   * @deprecated Since v8.3.1; Use {@link #DEFAULT_SIGN_ALTERNATIVE} instead
   */
  @Deprecated
  public static final String SIGN_ALTERNATIVE = DEFAULT_SIGN_ALTERNATIVE;
  /** The default label to be appended to form labels */
  public static final String DEFAULT_LABEL_END = ":";
  /**
   * The default label to be appended to form labels.
   *
   * @deprecated Since v8.3.1; Use {@link #DEFAULT_LABEL_END} instead
   */
  @Deprecated
  public static final String LABEL_END = DEFAULT_LABEL_END;

  private static final Logger LOGGER = LoggerFactory.getLogger (HCFormLabelHelper.class);

  private static final Map <ELabelType, String> DEFAULT_SUFFIXES = new CommonsEnumMap <> (ELabelType.class);
  private static String s_sDefaultLabelEnd;

  static
  {
    resetToDefault (false);
  }

  private HCFormLabelHelper ()
  {}

  public static void resetToDefault (final boolean bLog)
  {
    s_sDefaultLabelEnd = DEFAULT_LABEL_END;
    DEFAULT_SUFFIXES.clear ();
    DEFAULT_SUFFIXES.put (ELabelType.OPTIONAL, DEFAULT_SIGN_OPTIONAL);
    DEFAULT_SUFFIXES.put (ELabelType.MANDATORY, DEFAULT_SIGN_MANDATORY);
    DEFAULT_SUFFIXES.put (ELabelType.ALTERNATIVE, DEFAULT_SIGN_ALTERNATIVE);

    if (bLog)
      LOGGER.info ("The UI label configuration was reset to default");
  }

  /**
   * @return The global "label end" string to use. Never <code>null</code> but
   *         maybe empty.
   * @since 8.3.1
   */
  @Nonnull
  public static String getDefaultLabelEnd ()
  {
    return s_sDefaultLabelEnd;
  }

  /**
   * Set the global "label end" literal. By default it is
   * {@link #DEFAULT_LABEL_END} which is a single colon. This can only be set on
   * a global scale, so set this initially.
   *
   * @param sDefaultLabelEnd
   *        The new label end to be used. May be <code>null</code>.
   * @since 8.3.1
   */
  public static void setDefaultLabelEnd (@Nullable final String sDefaultLabelEnd)
  {
    final String sRealValue = StringHelper.getNotNull (sDefaultLabelEnd);
    s_sDefaultLabelEnd = sRealValue;

    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("The UI default 'label end' was set to '" + sRealValue + "'");
  }

  @Nullable
  public static String getSuffixStringOrNull (@Nonnull final ELabelType eType)
  {
    return DEFAULT_SUFFIXES.get (eType);
  }

  @Nonnull
  public static String getSuffixString (@Nonnull final ELabelType eType)
  {
    return StringHelper.getNotNull (getSuffixStringOrNull (eType));
  }

  /**
   * Change the default suffix string for a certain form label type. This is a
   * global change and hence should be set on application startup only.
   *
   * @param eType
   *        The label type to change. May not be <code>null</code>.
   * @param sValue
   *        The default suffix string to be used. May be <code>null</code> to
   *        indicate "none".
   * @since 8.3.1
   */
  public static void setDefaultSuffixString (@Nonnull final ELabelType eType, @Nullable final String sValue)
  {
    ValueEnforcer.notNull (eType, "Type");

    final String sRealValue = StringHelper.getNotNull (sValue);
    DEFAULT_SUFFIXES.put (eType, sRealValue);

    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("The UI default suffix for type " + eType + " was set to '" + sRealValue + "'");
  }

  /**
   * Build the overall suffix based on the suffix of the label type and
   * optionally the label end
   *
   * @param eType
   *        The label type to use
   * @param bAppendLabelEnd
   *        <code>true</code> to append the label end, <code>false</code> if not
   * @return Never <code>null</code> but maybe an empty string.
   * @see #getSuffixString(ELabelType)
   * @see #getDefaultLabelEnd()
   */
  @Nonnull
  public static String getSuffix (@Nonnull final ELabelType eType, final boolean bAppendLabelEnd)
  {
    final String sSuffix = getSuffixString (eType);
    return bAppendLabelEnd ? sSuffix + getDefaultLabelEnd () : sSuffix;
  }

  private static boolean _isNoLabelEndNeeded (@Nonnull final ELabelType eType, @Nonnull final String sText)
  {
    if (eType == ELabelType.NONE)
      return true;

    if (StringHelper.endsWith (sText, '?'))
      return true;
    return false;
  }

  @Nonnull
  private static String _getTextToAppend (@Nonnull final ELabelType eType, @Nonnull final String sPlainText)
  {
    final String sSuffixString = getSuffixString (eType);
    final String sLabelEnd = getDefaultLabelEnd ();

    if (StringHelper.hasText (sSuffixString) && StringHelper.endsWith (sPlainText, sSuffixString))
    {
      // Append only "label end"
      // No check if "label end is needed", because it would be weird if "Suffix
      // string" and "label end" would be the same string
      return sLabelEnd;
    }

    if (StringHelper.hasText (sLabelEnd))
    {
      if (StringHelper.endsWith (sPlainText, sLabelEnd))
      {
        // The text already ends with "label end" - nothing to do
      }
      else
      {
        if (_isNoLabelEndNeeded (eType, sPlainText))
        {
          // Append suffix only
          return sSuffixString;
        }

        // Append suffix and label end
        return sSuffixString + sLabelEnd;
      }
    }
    else
    {
      // Append suffix only (which might be empty), as there is no "label end"
      return sSuffixString;
    }

    // Fallback - nothing to append
    return "";
  }

  @Nonnull
  public static String getTextWithState (@Nonnull final String sText, @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (sText, "Text");
    ValueEnforcer.notNull (eType, "Type");

    String sPlainText = sText;
    if (StringHelper.hasText (sPlainText))
    {
      // Append suffix only, if at least some text is present
      sPlainText += _getTextToAppend (eType, sPlainText);
    }
    return sPlainText;
  }

  @Nonnull
  public static <T extends IHCNodeWithChildren <?>> T getNodeWithState (@Nonnull final T aNode,
                                                                        @Nonnull final ELabelType eType)
  {
    ValueEnforcer.notNull (aNode, "Node");
    ValueEnforcer.notNull (eType, "Type");

    // Only append the suffix, if at least one text child is present
    if (HCHelper.recursiveContainsAtLeastOneTextNode (aNode))
    {
      final String sPlainText = aNode.getPlainText ();
      if (sPlainText.length () > 0)
      {
        // Don't trim or modify the plain text, because it would not have any
        // impact in the application, as the real displayed text is part of e.g.
        // a HCTextNode
        final String sTextToAppend = _getTextToAppend (eType, sPlainText);
        if (!sTextToAppend.isEmpty ())
          aNode.addChild (sTextToAppend);
      }
    }
    return aNode;
  }

  @Nullable
  public static String trimAllKnownSuffixes (@Nullable final String s)
  {
    if (StringHelper.hasNoText (s))
      return s;

    // Get known suffixes list
    final ICommonsSet <String> aKnownSuffixes = new CommonsHashSet <> ();
    for (final String sSuffix : DEFAULT_SUFFIXES.values ())
      if (StringHelper.hasText (sSuffix))
        aKnownSuffixes.add (sSuffix);

    final String sLabelEnd = getDefaultLabelEnd ();
    if (StringHelper.hasText (sLabelEnd))
      aKnownSuffixes.add (sLabelEnd);

    String ret = s;
    // Repeat until no more suffix was found
    while (true)
    {
      boolean bChange = false;

      // Try each suffix
      for (final String sSuffix : aKnownSuffixes)
      {
        final String ret2 = StringHelper.trimEnd (ret, sSuffix);
        if (ret2.length () != ret.length ())
        {
          bChange = true;
          ret = ret2;
          break;
        }
      }

      if (!bChange)
      {
        // No more suffix found -> stop endless loop
        break;
      }
    }
    return ret;
  }
}
