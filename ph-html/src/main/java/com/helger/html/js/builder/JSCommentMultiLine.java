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
package com.helger.html.js.builder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * JSDoc comment.
 *
 * @author Philip Helger
 */
public class JSCommentMultiLine extends JSCommentPart implements IJSGeneratable
{
  private static final String INDENT = " *     ";

  /** list of @-param tags */
  private final Map <String, JSCommentPart> m_aParams = new LinkedHashMap <String, JSCommentPart> ();

  /** list of xdoclets */
  private final Map <String, Map <String, String>> m_aXDoclets = new LinkedHashMap <String, Map <String, String>> ();

  /** The @-return tag part. */
  private JSCommentPart m_aReturn;

  /** The @-deprecated tag */
  private JSCommentPart m_aDeprecated;

  public JSCommentMultiLine ()
  {}

  @Override
  @Nonnull
  public JSCommentMultiLine append (@Nullable final Object aObj)
  {
    add (aObj);
    return this;
  }

  /**
   * Append a text to a @-param tag to the JSDoc
   *
   * @param sParam
   *        Param name
   * @return Comment part
   */
  @Nonnull
  public JSCommentPart addParam (@Nonnull final String sParam)
  {
    ValueEnforcer.notNull (sParam, "Param");

    JSCommentPart aPart = m_aParams.get (sParam);
    if (aPart == null)
    {
      aPart = new JSCommentPart ();
      m_aParams.put (sParam, aPart);
    }
    return aPart;
  }

  /**
   * Append a text to an @-param tag.
   *
   * @param aParam
   *        Param name
   * @return Comment part
   */
  @Nonnull
  public JSCommentPart addParam (@Nonnull final JSVar aParam)
  {
    return addParam (aParam.name ());
  }

  /**
   * Appends a text to @return tag.
   *
   * @return Comment part
   */
  @Nonnull
  public JSCommentPart addReturn ()
  {
    if (m_aReturn == null)
      m_aReturn = new JSCommentPart ();
    return m_aReturn;
  }

  /**
   * add an @-deprecated tag to the JSDoc, with the associated message.
   *
   * @return Comment part
   */
  @Nonnull
  public JSCommentPart addDeprecated ()
  {
    if (m_aDeprecated == null)
      m_aDeprecated = new JSCommentPart ();
    return m_aDeprecated;
  }

  /**
   * add an xdoclet.
   *
   * @param sName
   *        xdoclet name
   * @return XDoclect to be filled
   */
  @Nonnull
  public Map <String, String> addXdoclet (@Nonnull final String sName)
  {
    ValueEnforcer.notNull (sName, "Name");

    Map <String, String> aMap = m_aXDoclets.get (sName);
    if (aMap == null)
    {
      aMap = new HashMap <String, String> ();
      m_aXDoclets.put (sName, aMap);
    }
    return aMap;
  }

  /**
   * add an xdoclet.
   *
   * @param sName
   *        xdoclet name
   * @param aAttributes
   *        Attributes to add
   * @return XDoclect to be filled
   */
  @Nonnull
  public Map <String, String> addXdoclet (@Nonnull final String sName, @Nonnull final Map <String, String> aAttributes)
  {
    final Map <String, String> p = addXdoclet (sName);
    p.putAll (aAttributes);
    return p;
  }

  /**
   * add an xdoclet.
   *
   * @param sName
   *        xdoclet name
   * @param sAttributeName
   *        Attributes name
   * @param sAttributeValue
   *        Attribute value
   * @return XDoclect to be filled
   */
  @Nonnull
  public Map <String, String> addXdoclet (@Nonnull final String sName,
                                          @Nonnull final String sAttributeName,
                                          @Nonnull final String sAttributeValue)
  {
    final Map <String, String> p = addXdoclet (sName);
    p.put (sAttributeName, sAttributeValue);
    return p;
  }

  public void generate (@Nonnull final JSFormatter aFormatter)
  {
    if (aFormatter.getSettings ().isGenerateComments ())
    {
      aFormatter.plain ("/**").nlFix ();

      // Main content start
      format (aFormatter, " * ");

      if (!m_aParams.isEmpty () || m_aReturn != null || m_aDeprecated != null || !m_aXDoclets.isEmpty ())
      {
        aFormatter.plain (" * ").nlFix ();
        for (final Map.Entry <String, JSCommentPart> aEntry : m_aParams.entrySet ())
        {
          aFormatter.plain (" * @param ").plain (aEntry.getKey ()).nlFix ();
          aEntry.getValue ().format (aFormatter, INDENT);
        }
        if (m_aReturn != null)
        {
          aFormatter.plain (" * @return").nlFix ();
          m_aReturn.format (aFormatter, INDENT);
        }
        if (m_aDeprecated != null)
        {
          aFormatter.plain (" * @deprecated").nlFix ();
          m_aDeprecated.format (aFormatter, INDENT);
        }
        for (final Map.Entry <String, Map <String, String>> aEntry : m_aXDoclets.entrySet ())
        {
          aFormatter.plain (" * @").plain (aEntry.getKey ());
          if (aEntry.getValue () != null)
          {
            for (final Map.Entry <String, String> aEntry2 : aEntry.getValue ().entrySet ())
              aFormatter.plain (" ").plain (aEntry2.getKey ()).plain ("= \"").plain (aEntry2.getValue ()).plain ("\"");
          }
          aFormatter.nlFix ();
        }
      }
      aFormatter.plain (" */").nlFix ();
    }
  }

  @Nonnull
  public final String getJSCode ()
  {
    return getJSCode ((IJSWriterSettings) null);
  }

  @Nonnull
  public String getJSCode (@Nullable final IJSWriterSettings aSettings)
  {
    return JSPrinter.getAsString (aSettings, this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final JSCommentMultiLine rhs = (JSCommentMultiLine) o;
    return m_aParams.equals (rhs.m_aParams) &&
           m_aXDoclets.equals (rhs.m_aXDoclets) &&
           EqualsUtils.equals (m_aReturn, rhs.m_aReturn) &&
           EqualsUtils.equals (m_aDeprecated, rhs.m_aDeprecated);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aParams)
                                       .append (m_aXDoclets)
                                       .append (m_aReturn)
                                       .append (m_aDeprecated)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("params", m_aParams)
                                       .append ("xdoclets", m_aXDoclets)
                                       .append ("return", m_aReturn)
                                       .append ("deprecated", m_aDeprecated)
                                       .toString ();
  }
}
