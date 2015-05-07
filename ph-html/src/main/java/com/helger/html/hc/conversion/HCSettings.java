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
package com.helger.html.hc.conversion;

import java.nio.charset.Charset;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.GlobalDebug;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroNode;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.customize.IHCCustomizer;

@ThreadSafe
public final class HCSettings
{
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  private static IHCConversionSettingsProvider s_aSettingsProvider = new HCConversionSettingsProvider (EHTMLVersion.DEFAULT);

  private HCSettings ()
  {}

  /**
   * Set the global conversion settings provider.
   *
   * @param aConversionSettingsProvider
   *        The object to be used. May not be <code>null</code>.
   */
  public static void setConversionSettingsProvider (@Nonnull final IHCConversionSettingsProvider aConversionSettingsProvider)
  {
    ValueEnforcer.notNull (aConversionSettingsProvider, "ConversionSettingsProvider");

    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aSettingsProvider = aConversionSettingsProvider;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return The global conversion settings provider. Never <code>null</code>.
   *         By default a {@link HCConversionSettingsProvider} object is
   *         returned.
   */
  @Nonnull
  public static IHCConversionSettingsProvider getConversionSettingsProvider ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aSettingsProvider;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Get the conversion settings from the current conversion settings provider
   * using default pretty print mode
   *
   * @return The non-<code>null</code> conversion settings
   */
  @Nonnull
  public static IHCConversionSettings getConversionSettings ()
  {
    return getConversionSettingsProvider ().getConversionSettings ();
  }

  /**
   * Convert the passed HC node builder to a micro node using the conversion
   * settings provider.
   *
   * @param aNodeBuilder
   *        The node to be converted. May not be <code>null</code>.
   * @return The fully created HTML node
   */
  @Nullable
  public static IMicroNode getAsNode (@Nonnull final IHCNodeBuilder aNodeBuilder)
  {
    return getAsNode (aNodeBuilder.build (), getConversionSettings ());
  }

  /**
   * Convert the passed HC node builder to a micro node using the conversion
   * settings provider.
   *
   * @param aNodeBuilder
   *        The node to be converted. May not be <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The fully created HTML node
   */
  @Nullable
  public static IMicroNode getAsNode (@Nonnull final IHCNodeBuilder aNodeBuilder,
                                      @Nonnull final IHCConversionSettings aConversionSettings)
  {
    return getAsNode (aNodeBuilder.build (), aConversionSettings);
  }

  /**
   * Convert the passed HC node to a micro node using the conversion settings
   * provider.
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @return The fully created HTML node
   */
  @Nullable
  public static IMicroNode getAsNode (@Nonnull final IHCNode aHCNode)
  {
    return getAsNode (aHCNode, getConversionSettings ());
  }

  /**
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The fully created HTML node
   */
  @Nullable
  public static IMicroNode getAsNode (@Nonnull final IHCNode aHCNode,
                                      @Nonnull final IHCConversionSettings aConversionSettings)
  {
    return aHCNode.convertToNode (aConversionSettings);
  }

  /**
   * Convert the passed HC node builder to an HTML string using default pretty
   * print mode.
   *
   * @param aNodeBuilder
   *        The node to be converted. May not be <code>null</code>.
   * @return The node as HTML.
   */
  @Nonnull
  public static String getAsHTMLString (@Nonnull final IHCNodeBuilder aNodeBuilder)
  {
    return getAsHTMLString (aNodeBuilder.build (), getConversionSettings ());
  }

  /**
   * Convert the passed HC node builder to an HTML string using the passed
   * conversion settings.
   *
   * @param aNodeBuilder
   *        The node to be converted. May not be <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The node as XML optionally without indentation.
   */
  @Nonnull
  public static String getAsHTMLString (@Nonnull final IHCNodeBuilder aNodeBuilder,
                                        @Nonnull final IHCConversionSettings aConversionSettings)
  {
    return getAsHTMLString (aNodeBuilder.build (), aConversionSettings);
  }

  /**
   * Convert the passed HC node to an HTML string. Indent and align status is
   * determined from {@link GlobalDebug#isDebugMode()}
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @return The node as XML with or without indentation.
   */
  @Nonnull
  public static String getAsHTMLString (@Nonnull final IHCNode aHCNode)
  {
    return getAsHTMLString (aHCNode, getConversionSettings ());
  }

  /**
   * Convert the passed HC node to an HTML string using the passed conversion
   * settings.
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The node as XML optionally without indentation.
   */
  @Nonnull
  public static String getAsHTMLString (@Nonnull final IHCNode aHCNode,
                                        @Nonnull final IHCConversionSettings aConversionSettings)
  {
    return aHCNode.getAsHTMLString (aConversionSettings);
  }

  /**
   * Convert the passed HC node to an HTML string without namespaces. Indent and
   * align status is determined from {@link GlobalDebug#isDebugMode()}
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @return The node as XML with or without indentation.
   */
  @Nonnull
  public static String getAsHTMLStringWithoutNamespaces (@Nonnull final IHCNode aHCNode)
  {
    return getAsHTMLStringWithoutNamespaces (aHCNode, getConversionSettings ());
  }

  /**
   * Convert the passed HC node to an HTML string without namespaces.
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The node as XML with or without indentation.
   */
  @Nonnull
  public static String getAsHTMLStringWithoutNamespaces (@Nonnull final IHCNode aHCNode,
                                                         @Nonnull final IHCConversionSettings aConversionSettings)
  {
    // Create a copy
    final HCConversionSettings aRealCS = new HCConversionSettings (aConversionSettings);
    // And modify the copied XML settings
    aRealCS.getXMLWriterSettings ().setEmitNamespaces (false);
    return getAsHTMLString (aHCNode, aRealCS);
  }

  /**
   * Get the {@link Charset} that is used to create the HTML code.
   * 
   * @return The non-<code>null</code> Charset object
   */
  @Nonnull
  public static Charset getHTMLCharset ()
  {
    return getConversionSettings ().getXMLWriterSettings ().getCharsetObj ();
  }

  /**
   * Get the customizer currently used.
   * 
   * @return The customizer to use. May be <code>null</code>.
   */
  @Nullable
  public static IHCCustomizer getCustomizer ()
  {
    return getConversionSettings ().getCustomizer ();
  }
}
