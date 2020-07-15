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
package com.helger.html.markdown;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;

/**
 * Txtmark configuration.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
public class MarkdownConfiguration
{
  /**
   * <p>
   * This is the default configuration for txtmark's <code>process</code>
   * methods
   * </p>
   * <ul>
   * <li><code>safeMode = false</code></li>
   * <li><code>encoding = UTF-8</code></li>
   * <li><code>decorator = DefaultDecorator</code></li>
   * <li><code>codeBlockEmitter = null</code></li>
   * </ul>
   */
  public static final MarkdownConfiguration DEFAULT = MarkdownConfiguration.builder ().build ();

  /**
   * <p>
   * This is the default configuration for txtmark's <code>process</code>
   * methods
   * </p>
   * <ul>
   * <li><code>safeMode = false</code></li>
   * <li><code>encoding = UTF-8</code></li>
   * <li><code>decorator = DefaultDecorator</code></li>
   * <li><code>codeBlockEmitter = null</code></li>
   * </ul>
   */
  public static final MarkdownConfiguration DEFAULT_EXTENSIONS = MarkdownConfiguration.builder ().setExtendedProfile (true).build ();

  /**
   * <p>
   * Default safe configuration
   * </p>
   * <ul>
   * <li><code>safeMode = true</code></li>
   * <li><code>encoding = UTF-8</code></li>
   * <li><code>decorator = DefaultDecorator</code></li>
   * <li><code>codeBlockEmitter = null</code></li>
   * </ul>
   */
  public static final MarkdownConfiguration DEFAULT_SAFE = MarkdownConfiguration.builder ().setSafeMode (true).build ();

  /**
   * <p>
   * Default safe configuration
   * </p>
   * <ul>
   * <li><code>safeMode = true</code></li>
   * <li><code>encoding = UTF-8</code></li>
   * <li><code>decorator = DefaultDecorator</code></li>
   * <li><code>codeBlockEmitter = null</code></li>
   * </ul>
   */
  public static final MarkdownConfiguration DEFAULT_SAFE_EXTENSIONS = MarkdownConfiguration.builder ()
                                                                                           .setSafeMode (true)
                                                                                           .setExtendedProfile (true)
                                                                                           .build ();

  private final boolean m_bSafeMode;
  private final Charset m_aEncoding;
  private final IMarkdownDecorator m_aDecorator;
  private final IMarkdownBlockEmitter m_aCodeBlockEmitter;
  private final boolean m_bForceExtendedProfile;
  private final boolean m_bConvertNewline2Br;
  private final IMarkdownSpanEmitter m_aSpecialLinkEmitter;
  private final ICommonsList <AbstractMarkdownPlugin> m_aPlugins;

  /**
   * Constructor.
   *
   * @param bSafeMode
   *        safe mode?
   * @param aEncoding
   *        Encoding to use
   * @param aDecorator
   *        Decorator to use
   * @param aCodeBlockEmitter
   *        Code block emitter to use
   * @param bForceExtendedProfile
   *        Force extended profile?
   * @param bConvertNewline2Br
   *        Convert newline to &lt;br&gt;
   * @param aSpecialLinkEmitter
   *        Special link emitted
   * @param aPlugins
   *        Custom plugins
   */
  public MarkdownConfiguration (final boolean bSafeMode,
                                @Nonnull final Charset aEncoding,
                                @Nonnull final IMarkdownDecorator aDecorator,
                                @Nullable final IMarkdownBlockEmitter aCodeBlockEmitter,
                                final boolean bForceExtendedProfile,
                                final boolean bConvertNewline2Br,
                                @Nullable final IMarkdownSpanEmitter aSpecialLinkEmitter,
                                @Nullable final List <? extends AbstractMarkdownPlugin> aPlugins)
  {
    ValueEnforcer.notNull (aEncoding, "Encoding");
    ValueEnforcer.notNull (aDecorator, "Decorator");

    m_bSafeMode = bSafeMode;
    m_aEncoding = aEncoding;
    m_aDecorator = aDecorator;
    m_aCodeBlockEmitter = aCodeBlockEmitter;
    m_bForceExtendedProfile = bForceExtendedProfile;
    m_bConvertNewline2Br = bConvertNewline2Br;
    m_aSpecialLinkEmitter = aSpecialLinkEmitter;
    m_aPlugins = CollectionHelper.newList (aPlugins);
  }

  public boolean isSafeMode ()
  {
    return m_bSafeMode;
  }

  @Nonnull
  public Charset getEncoding ()
  {
    return m_aEncoding;
  }

  @Nonnull
  public IMarkdownDecorator getDecorator ()
  {
    return m_aDecorator;
  }

  @Nullable
  public IMarkdownBlockEmitter getCodeBlockEmitter ()
  {
    return m_aCodeBlockEmitter;
  }

  public boolean isExtendedProfile ()
  {
    return m_bForceExtendedProfile;
  }

  public boolean isConvertNewline2Br ()
  {
    return m_bConvertNewline2Br;
  }

  @Nullable
  public IMarkdownSpanEmitter getSpecialLinkEmitter ()
  {
    return m_aSpecialLinkEmitter;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <AbstractMarkdownPlugin> getAllPlugins ()
  {
    return m_aPlugins.getClone ();
  }

  /**
   * Creates a new Builder instance.
   *
   * @return A new Builder instance.
   */
  @Nonnull
  public static Builder builder ()
  {
    return new Builder ();
  }

  /**
   * Configuration builder.
   *
   * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
   * @since 0.7
   */
  public static class Builder
  {
    private boolean m_bSafeMode = false;
    private boolean m_bForceExtendedProfile = false;
    private boolean m_bConvertNewline2Br = false;
    private Charset m_aEncoding = StandardCharsets.UTF_8;
    private IMarkdownDecorator m_aDecorator = new MarkdownDefaultDecorator ();
    private IMarkdownBlockEmitter m_aCodeBlockEmitter = null;
    private IMarkdownSpanEmitter m_aSpecialLinkEmitter = null;
    private final ICommonsList <AbstractMarkdownPlugin> m_aPlugins = new CommonsArrayList <> ();

    /**
     * Constructor.
     */
    Builder ()
    {}

    /**
     * Forces extended profile to be enabled by default.
     *
     * @param bExtendedProfile
     *        true to use extended profile
     * @return This builder.
     * @since 0.7
     */
    @Nonnull
    public Builder setExtendedProfile (final boolean bExtendedProfile)
    {
      m_bForceExtendedProfile = bExtendedProfile;
      return this;
    }

    /**
     * convertNewline2Br.
     *
     * @return This builder.
     */
    @Nonnull
    public Builder convertNewline2Br ()
    {
      m_bConvertNewline2Br = true;
      return this;
    }

    /**
     * Sets the HTML safe mode flag. Default: <code>false</code>
     *
     * @param flag
     *        <code>true</code> to enable safe mode
     * @return This builder
     * @since 0.7
     */
    @Nonnull
    public Builder setSafeMode (final boolean flag)
    {
      m_bSafeMode = flag;
      return this;
    }

    /**
     * Sets the character encoding for txtmark. Default:
     * <code>&quot;UTF-8&quot;</code>
     *
     * @param aEncoding
     *        The encoding
     * @return This builder
     * @since 0.7
     */
    @Nonnull
    public Builder setEncoding (@Nonnull final Charset aEncoding)
    {
      m_aEncoding = ValueEnforcer.notNull (aEncoding, "Encoding");
      return this;
    }

    @Nonnull
    public IMarkdownDecorator getDecorator ()
    {
      return m_aDecorator;
    }

    /**
     * Sets the decorator for txtmark. Default: <code>DefaultDecorator()</code>
     *
     * @param aDecorator
     *        The decorator
     * @return This builder
     * @see MarkdownDefaultDecorator
     * @since 0.7
     */
    @Nonnull
    public Builder setDecorator (@Nonnull final IMarkdownDecorator aDecorator)
    {
      m_aDecorator = ValueEnforcer.notNull (aDecorator, "Decorator");
      return this;
    }

    /**
     * Sets the code block emitter. Default: <code>null</code>
     *
     * @param emitter
     *        The BlockEmitter
     * @return This builder
     * @see IMarkdownBlockEmitter
     * @since 0.7
     */
    @Nonnull
    public Builder setCodeBlockEmitter (@Nullable final IMarkdownBlockEmitter emitter)
    {
      m_aCodeBlockEmitter = emitter;
      return this;
    }

    /**
     * Sets the emitter for special link spans ([[ ... ]]).
     *
     * @param emitter
     *        The emitter.
     * @return This builder.
     * @since 0.7
     */
    @Nonnull
    public Builder setSpecialLinkEmitter (@Nullable final IMarkdownSpanEmitter emitter)
    {
      m_aSpecialLinkEmitter = emitter;
      return this;
    }

    /**
     * Sets the plugins.
     *
     * @param aPlugins
     *        The plugins.
     * @return This builder.
     */
    @Nonnull
    public Builder registerPlugins (@Nonnull final AbstractMarkdownPlugin... aPlugins)
    {
      for (final AbstractMarkdownPlugin aPlugin : aPlugins)
        m_aPlugins.add (aPlugin);
      return this;
    }

    /**
     * Builds a configuration instance.
     *
     * @return a Configuration instance
     * @since 0.7
     */
    @Nonnull
    public MarkdownConfiguration build ()
    {
      return new MarkdownConfiguration (m_bSafeMode,
                                        m_aEncoding,
                                        m_aDecorator,
                                        m_aCodeBlockEmitter,
                                        m_bForceExtendedProfile,
                                        m_bConvertNewline2Br,
                                        m_aSpecialLinkEmitter,
                                        m_aPlugins);
    }
  }
}
