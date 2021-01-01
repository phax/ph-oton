/**
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
package com.helger.photon.tinymce4;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.EHCTextDirection;
import com.helger.html.hc.html.forms.AbstractHCTextArea;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.html.jscode.JSInvocation;
import com.helger.json.IJsonObject;
import com.helger.json.JsonObject;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.tinymce4.type.ETinyMCE4ExternalPlugin;
import com.helger.photon.tinymce4.type.ETinyMCE4Language;
import com.helger.photon.tinymce4.type.ETinyMCE4Plugin;
import com.helger.photon.tinymce4.type.ETinyMCE4Resize;
import com.helger.photon.tinymce4.type.ETinyMCE4Skin;
import com.helger.photon.tinymce4.type.ETinyMCE4Theme;
import com.helger.photon.tinymce4.type.TinyMCE4ExternalPlugin;
import com.helger.photon.tinymce4.type.TinyMCE4MenubarItemList;
import com.helger.photon.tinymce4.type.TinyMCE4ToolbarControlList;
import com.helger.photon.uicore.EUICoreJSPathProvider;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Wraps TinyMCE4 into an HC node. The only required settings is
 * {@link #setSelector(String)} but this does not need to be called, as the
 * default value {@link #DEFAULT_SELECTOR} is used automatically.<br>
 * Note: all options that are not explicitly wrapped can be set by
 * {@link #addCustomOption(String, IJSExpression)}
 *
 * @author Philip Helger
 */
public class HCTinyMCE4 extends AbstractHCTextArea <HCTinyMCE4>
{
  // General options
  public static final boolean DEFAULT_BROWSER_SPELLCHECK = false;
  public static final boolean DEFAULT_NOWRAP = false;
  public static final boolean DEFAULT_OBJECT_RESIZING = true;
  public static final String DEFAULT_SELECTOR = "textarea";
  public static final boolean DEFAULT_INLINE = false;
  public static final boolean DEFAULT_HIDDEN_INPUT = true;

  // Cleanup/output
  public static final boolean DEFAULT_CONVERT_FONTS_TO_SPANS = true;

  // Content style

  // Visual aids

  // Undo/Redo

  // User interface
  public static final boolean DEFAULT_TOOLBAR_DISABLED = false;
  public static final boolean DEFAULT_MENUBAR_DISABLED = false;
  public static final boolean DEFAULT_STATUSBAR = true;
  public static final boolean DEFAULT_PREVIEW_STYLES = true;

  // URL

  // Callbacks

  // General
  private String m_sAutoFocus;
  private EHCTextDirection m_eDirectionality;
  private ETriState m_eBrowserSpellcheck = ETriState.UNDEFINED;
  private ETinyMCE4Language m_eTinyMCELanguage;
  private ISimpleURL m_aLanguageURL;
  private ETriState m_eNoWrap = ETriState.UNDEFINED;
  private ETriState m_eObjectResizing = ETriState.UNDEFINED;
  private final ICommonsOrderedSet <ETinyMCE4Plugin> m_aPlugins = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <TinyMCE4ExternalPlugin> m_aExternalPlugins = new CommonsLinkedHashSet <> ();
  private String m_sSelector = DEFAULT_SELECTOR;
  private ETinyMCE4Skin m_eSkin;
  private ISimpleURL m_aSkinURL;
  private ETinyMCE4Theme m_eTheme;
  private ISimpleURL m_aThemeURL;
  private ETriState m_eInline = ETriState.UNDEFINED;
  private ETriState m_eHiddenInput = ETriState.UNDEFINED;

  // Cleanup/output
  private ETriState m_eConvertFontsToSpans = ETriState.UNDEFINED;
  // TODO custom_elements
  // TODO doctype
  // TODO element_format
  // TODO entities
  // TODO entity_encoding
  // TODO extended_valid_elements
  // TODO fix_list_elements
  // TODO font_formats
  // TODO fontsize_formats
  // TODO force_p_newlines
  // TODO force_hex_style_colors
  // TODO forced_root_block
  // TODO forced_root_block_attrs
  // TODO formats
  // TODO indentation
  // TODO invalid_elements
  // TODO keep_styles
  // TODO protect
  // TODO schema
  // TODO style_formats
  // TODO block_formats
  // TODO valid_children
  // TODO valid_elements
  // TODO valid_styles

  // Content style
  // TODO body_id
  // TODO body_class
  // TODO content_css

  // Visual aids
  // TODO visual

  // Undo/Redo
  // TODO custom_undo_redo_levels

  // User interface
  private TinyMCE4ToolbarControlList m_aToolbar;
  private boolean m_bToolbarDisabled = DEFAULT_TOOLBAR_DISABLED;
  // TODO toolbar<N>
  private TinyMCE4MenubarItemList m_aMenubar;
  private boolean m_bMenubarDisabled = DEFAULT_MENUBAR_DISABLED;
  // TODO menubar
  // TODO menu
  private ETriState m_eStatusbar = ETriState.UNDEFINED;
  private ETinyMCE4Resize m_eResize;
  private int m_nWidth = CGlobal.ILLEGAL_UINT;
  private int m_nHeight = CGlobal.ILLEGAL_UINT;
  private ETriState m_ePreviewStyles = ETriState.UNDEFINED;
  // TODO fixed_toolbar_container

  // URL
  // TODO convert_urls
  // TODO relative_urls
  // TODO remove_script_host
  // TODO document_base_url

  // Callbacks
  private JSAnonymousFunction m_aFileBrowserCallback;

  // Custom
  private final ICommonsOrderedMap <String, IJSExpression> m_aCustom = new CommonsLinkedHashMap <> ();

  private void _init ()
  {
    // Select this textarea by default
    setSelector ('#' + ensureID ().getID ());
  }

  public HCTinyMCE4 ()
  {
    _init ();
  }

  public HCTinyMCE4 (@Nonnull final RequestField aRF)
  {
    super (aRF);
    _init ();
  }

  @Nullable
  public String getAutoFocus ()
  {
    return m_sAutoFocus;
  }

  /**
   * This option enables you to auto focus an editor instance. The value of this
   * option should be an editor instance id. The editor instance id is the id
   * for the original textarea or div element that got replaced.
   *
   * @param sAutoFocus
   *        Editor ID
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setAutoFocus (@Nullable final String sAutoFocus)
  {
    m_sAutoFocus = sAutoFocus;
    return this;
  }

  @Nullable
  public EHCTextDirection getDirectionality ()
  {
    return m_eDirectionality;
  }

  /**
   * <pre>
   * Set the default directionality of the editor.
   * Possible values are:
   * - ltr
   *  - rtl
   * </pre>
   *
   * @param eDirectionality
   *        direction
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setDirectionality (@Nullable final EHCTextDirection eDirectionality)
  {
    m_eDirectionality = eDirectionality;
    return this;
  }

  public boolean isBrowserSpellcheck ()
  {
    return m_eBrowserSpellcheck.getAsBooleanValue (DEFAULT_BROWSER_SPELLCHECK);
  }

  /**
   * This is a true/false value if the usage of the browsers internal
   * spellchecker should be used. Default value is false.
   *
   * @param bBrowserSpellcheck
   *        <code>true</code> to enabled, <code>false</code> to disable
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setBrowserSpellcheck (final boolean bBrowserSpellcheck)
  {
    m_eBrowserSpellcheck = ETriState.valueOf (bBrowserSpellcheck);
    return this;
  }

  /**
   * This is a true/false value if the usage of the browsers internal
   * spellchecker should be used. Default value is false.
   *
   * @param aBrowserSpellcheck
   *        <code>true</code> to enabled, <code>false</code> to disable and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setBrowserSpellcheck (@Nullable final Boolean aBrowserSpellcheck)
  {
    m_eBrowserSpellcheck = ETriState.valueOf (aBrowserSpellcheck);
    return this;
  }

  @Nullable
  public ETinyMCE4Language getTinyMCELanguage ()
  {
    return m_eTinyMCELanguage;
  }

  /**
   * Set the language of the UI texts
   *
   * @param eTinyMCELanguage
   *        The language to use. <code>null</code> means English
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setTinyMCELanguage (@Nullable final ETinyMCE4Language eTinyMCELanguage)
  {
    m_eTinyMCELanguage = eTinyMCELanguage;
    return this;
  }

  @Nullable
  public ISimpleURL getLanguageURL ()
  {
    return m_aLanguageURL;
  }

  /**
   * A simple URL to where the language file to use. We recommend using a site
   * absolute URL.
   *
   * @param aLanguageURL
   *        The language URL to use.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setLanguageURL (@Nullable final ISimpleURL aLanguageURL)
  {
    m_aLanguageURL = aLanguageURL;
    return this;
  }

  public boolean isNoWrap ()
  {
    return m_eNoWrap.getAsBooleanValue (DEFAULT_NOWRAP);
  }

  /**
   * This option will make the editable are behave like very much like a
   * &lt;pre&gt; tag, and add a scroll instead of wrapping text.
   *
   * @param bNoWrap
   *        <code>true</code> to enabled, <code>false</code> to disable
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setNoWrap (final boolean bNoWrap)
  {
    m_eNoWrap = ETriState.valueOf (bNoWrap);
    return this;
  }

  /**
   * This option will make the editable are behave like very much like a
   * &lt;pre&gt; tag, and add a scroll instead of wrapping text.
   *
   * @param aNoWrap
   *        <code>true</code> to enabled, <code>false</code> to disable and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setNoWrap (@Nullable final Boolean aNoWrap)
  {
    m_eNoWrap = ETriState.valueOf (aNoWrap);
    return this;
  }

  public boolean isObjectResizing ()
  {
    return m_eObjectResizing.getAsBooleanValue (DEFAULT_OBJECT_RESIZING);
  }

  /**
   * This options allows you to turn on/off the resizing handles on images,
   * tables or media objects.
   *
   * @param bObjectResizing
   *        <code>true</code> to enabled, <code>false</code> to disable
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setObjectResizing (final boolean bObjectResizing)
  {
    m_eObjectResizing = ETriState.valueOf (bObjectResizing);
    return this;
  }

  /**
   * This options allows you to turn on/off the resizing handles on images,
   * tables or media objects.
   *
   * @param aObjectResizing
   *        <code>true</code> to enabled, <code>false</code> to disable and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setObjectResizing (@Nullable final Boolean aObjectResizing)
  {
    m_eObjectResizing = ETriState.valueOf (aObjectResizing);
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <ETinyMCE4Plugin> getAllPlugins ()
  {
    return m_aPlugins.getClone ();
  }

  @Nonnegative
  public int getPluginCount ()
  {
    return m_aPlugins.size ();
  }

  /**
   * Add a TinyMCE plugin to use.
   *
   * @param ePlugin
   *        The plugin to add. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addPlugin (@Nonnull final ETinyMCE4Plugin ePlugin)
  {
    ValueEnforcer.notNull (ePlugin, "Plugin");
    m_aPlugins.add (ePlugin);
    return this;
  }

  /**
   * Add multiple TinyMCE plugins at once.
   *
   * @param aPlugins
   *        The plugins to add. The array may be <code>null</code> but the
   *        contained elements may not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addPlugins (@Nullable final ETinyMCE4Plugin... aPlugins)
  {
    if (aPlugins != null)
      for (final ETinyMCE4Plugin ePlugin : aPlugins)
        addPlugin (ePlugin);
    return this;
  }

  /**
   * Add multiple TinyMCE plugins at once.
   *
   * @param aPlugins
   *        The plugins to add. The array may be <code>null</code> but the
   *        contained elements may not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addPlugins (@Nullable final Iterable <ETinyMCE4Plugin> aPlugins)
  {
    if (aPlugins != null)
      for (final ETinyMCE4Plugin ePlugin : aPlugins)
        addPlugin (ePlugin);
    return this;
  }

  /**
   * Add all available plugins there are.
   *
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addAllPlugins ()
  {
    return addPlugins (ETinyMCE4Plugin.values ());
  }

  /**
   * Remove the specified plugin.
   *
   * @param ePlugin
   *        The plugin to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if the plugin was successfully removed.
   */
  @Nonnull
  public EChange removePlugin (@Nullable final ETinyMCE4Plugin ePlugin)
  {
    return EChange.valueOf (ePlugin != null && m_aPlugins.remove (ePlugin));
  }

  /**
   * Remove the specified TinyMCE plugins.
   *
   * @param aPlugins
   *        The plugins to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} it at least one plugin was removed
   *         successfully.
   */
  @Nonnull
  public EChange removePlugins (@Nullable final ETinyMCE4Plugin... aPlugins)
  {
    EChange ret = EChange.UNCHANGED;
    if (aPlugins != null)
      for (final ETinyMCE4Plugin ePlugin : aPlugins)
        ret = ret.or (removePlugin (ePlugin));
    return ret;
  }

  /**
   * Remove the specified TinyMCE plugins.
   *
   * @param aPlugins
   *        The plugins to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} it at least one plugin was removed
   *         successfully.
   */
  @Nonnull
  public EChange removePlugins (@Nullable final Iterable <ETinyMCE4Plugin> aPlugins)
  {
    EChange ret = EChange.UNCHANGED;
    if (aPlugins != null)
      for (final ETinyMCE4Plugin ePlugin : aPlugins)
        ret = ret.or (removePlugin (ePlugin));
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <TinyMCE4ExternalPlugin> getAllExternalPlugins ()
  {
    return m_aExternalPlugins.getClone ();
  }

  @Nonnegative
  public int getExternalPluginCount ()
  {
    return m_aExternalPlugins.size ();
  }

  /**
   * Add an external plugin to the editor.
   *
   * @param eExternalPlugin
   *        The external plugin to be added. May not be <code>null</code>.
   * @param aRequestScope
   *        The request to be used. Required if cookies are disabled.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addExternalPlugin (@Nonnull final ETinyMCE4ExternalPlugin eExternalPlugin,
                                       @Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return addExternalPlugin (eExternalPlugin.getAsExternalPlugin (aRequestScope));
  }

  /**
   * Add an external plugin to the editor.
   *
   * @param aExternalPlugin
   *        The plugin to be added. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addExternalPlugin (@Nonnull final TinyMCE4ExternalPlugin aExternalPlugin)
  {
    ValueEnforcer.notNull (aExternalPlugin, "ExternalPlugin");
    m_aExternalPlugins.add (aExternalPlugin);
    return this;
  }

  /**
   * Add multiple external plugins at once.
   *
   * @param aExternalPlugins
   *        The plugins to be added. May be <code>null</code> but no contained
   *        plugin may be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addExternalPlugins (@Nullable final TinyMCE4ExternalPlugin... aExternalPlugins)
  {
    if (aExternalPlugins != null)
      for (final TinyMCE4ExternalPlugin aExternalPlugin : aExternalPlugins)
        addExternalPlugin (aExternalPlugin);
    return this;
  }

  /**
   * Add multiple external plugins at once.
   *
   * @param aExternalPlugins
   *        The plugins to be added. May be <code>null</code> but no contained
   *        plugin may be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 addExternalPlugins (@Nullable final Iterable <? extends TinyMCE4ExternalPlugin> aExternalPlugins)
  {
    if (aExternalPlugins != null)
      for (final TinyMCE4ExternalPlugin aExternalPlugin : aExternalPlugins)
        addExternalPlugin (aExternalPlugin);
    return this;
  }

  /**
   * Remove the specified external plugin.
   *
   * @param aExternalPlugin
   *        The plugin to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if the plugin was removed successfully.
   */
  @Nonnull
  public EChange removeExternalPlugin (@Nullable final TinyMCE4ExternalPlugin aExternalPlugin)
  {
    return EChange.valueOf (aExternalPlugin != null && m_aExternalPlugins.remove (aExternalPlugin));
  }

  /**
   * Remove the specified external plugins.
   *
   * @param aExternalPlugins
   *        The plugins to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} it at least one plugin was removed
   *         successfully.
   */
  @Nonnull
  public EChange removeExternalPlugins (@Nullable final TinyMCE4ExternalPlugin... aExternalPlugins)
  {
    EChange ret = EChange.UNCHANGED;
    if (aExternalPlugins != null)
      for (final TinyMCE4ExternalPlugin aExternalPlugin : aExternalPlugins)
        ret = ret.or (removeExternalPlugin (aExternalPlugin));
    return ret;
  }

  /**
   * Remove the specified external plugins.
   *
   * @param aExternalPlugins
   *        The plugins to be removed. May be <code>null</code>.
   * @return {@link EChange#CHANGED} it at least one plugin was removed
   *         successfully.
   */
  @Nonnull
  public EChange removeExternalPlugins (@Nullable final Iterable <? extends TinyMCE4ExternalPlugin> aExternalPlugins)
  {
    EChange ret = EChange.UNCHANGED;
    if (aExternalPlugins != null)
      for (final TinyMCE4ExternalPlugin aExternalPlugin : aExternalPlugins)
        ret = ret.or (removeExternalPlugin (aExternalPlugin));
    return ret;
  }

  /**
   * @return The jQuery selector to be used to convert nodes to TinyMCE
   *         elements.
   */
  @Nonnull
  @Nonempty
  public String getSelector ()
  {
    return m_sSelector;
  }

  /**
   * <pre>
   * Selector option, allows you to use CSS selector syntax for determining what areas should be editable, this is the recommended way of selecting what elements should be editable.
   * Some examples of usage.
   * This would select all textarea elements in the page.
   * selector: "textarea",
   * This would select all textareas with the class .editme in your page.
   * selector: "textarea.editme",
   * If you use the inline option, the selector can be used on any block element.
   * selector: "h1.editme",
   * selector: "div.editme",
   * </pre>
   *
   * @param sSelector
   *        The selector to use. May neither be <code>null</code> nor empty.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setSelector (@Nonnull @Nonempty final String sSelector)
  {
    ValueEnforcer.notEmpty (sSelector, "Selector");
    m_sSelector = sSelector;
    return this;
  }

  @Nullable
  public ETinyMCE4Skin getSkin ()
  {
    return m_eSkin;
  }

  /**
   * Select what skin to use, this should match the foldername of the skin.
   *
   * @param eSkin
   *        Skin to use. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setSkin (@Nullable final ETinyMCE4Skin eSkin)
  {
    m_eSkin = eSkin;
    return this;
  }

  @Nullable
  public ISimpleURL getSkinURL ()
  {
    return m_aSkinURL;
  }

  /**
   * This option enables you to specify location of the current skin. Enables
   * you to load TinyMCE from one URL for example a CDN then load a local skin
   * on the current server.
   *
   * @param aSkinURL
   *        The skin URL to use.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setSkinURL (@Nullable final ISimpleURL aSkinURL)
  {
    m_aSkinURL = aSkinURL;
    return this;
  }

  @Nullable
  public ETinyMCE4Theme getTheme ()
  {
    return m_eTheme;
  }

  /**
   * Set the theme of TinyMCE.
   *
   * @param eTheme
   *        Theme to use.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setTheme (@Nullable final ETinyMCE4Theme eTheme)
  {
    m_eTheme = eTheme;
    return this;
  }

  @Nullable
  public ISimpleURL getThemeURL ()
  {
    return m_aThemeURL;
  }

  /**
   * This option enables you to specify location of the current theme. Enables
   * you to load TinyMCE from one URL for example a CDN then load a local theme
   * on the current server.
   *
   * @param aThemeURL
   *        The theme URL to use.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setThemeURL (@Nullable final ISimpleURL aThemeURL)
  {
    m_aThemeURL = aThemeURL;
    return this;
  }

  public boolean isInline ()
  {
    return m_eInline.getAsBooleanValue (DEFAULT_INLINE);
  }

  /**
   * This option changes the behaviour of the editor to allow the usage of
   * inline elements instead of a textarea.
   *
   * @param bInline
   *        <code>true</code> to enabled, <code>false</code> to disable
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setInline (final boolean bInline)
  {
    m_eInline = ETriState.valueOf (bInline);
    return this;
  }

  /**
   * This option changes the behaviour of the editor to allow the usage of
   * inline elements instead of a textarea.
   *
   * @param aInline
   *        <code>true</code> to enabled, <code>false</code> to disable and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setInline (@Nullable final Boolean aInline)
  {
    m_eInline = ETriState.valueOf (aInline);
    return this;
  }

  public boolean isHiddenInput ()
  {
    return m_eHiddenInput.getAsBooleanValue (DEFAULT_HIDDEN_INPUT);
  }

  /**
   * This option gives you the ability to disable the auto generation of hidden
   * input fields for inline editing elements. By default all inline editors
   * gets an hidden input element that contents gets saved to when you do
   * editor.save() or tinymce.triggerSave(); this can be disabled if you don't
   * need these controls.
   *
   * @param bHiddenInput
   *        <code>true</code> to enabled, <code>false</code> to disable
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setHiddenInput (final boolean bHiddenInput)
  {
    m_eHiddenInput = ETriState.valueOf (bHiddenInput);
    return this;
  }

  /**
   * This option gives you the ability to disable the auto generation of hidden
   * input fields for inline editing elements. By default all inline editors
   * gets an hidden input element that contents gets saved to when you do
   * editor.save() or tinymce.triggerSave(); this can be disabled if you don't
   * need these controls.
   *
   * @param aHiddenInput
   *        <code>true</code> to enabled, <code>false</code> to disable and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setHiddenInput (@Nullable final Boolean aHiddenInput)
  {
    m_eHiddenInput = ETriState.valueOf (aHiddenInput);
    return this;
  }

  // --- Cleanup/Output ---

  public boolean isConvertFontsToSpans ()
  {
    return m_eConvertFontsToSpans.getAsBooleanValue (DEFAULT_CONVERT_FONTS_TO_SPANS);
  }

  /**
   * If you set this option to true, TinyMCE will convert all font elements to
   * span elements and generate span elements instead of font elements. This
   * option should be used in order to get more W3C compatible code, since font
   * elements are deprecated.
   *
   * @param bConvertFontsToSpans
   *        <code>true</code> to enabled, <code>false</code> to disable
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setConvertFontsToSpans (final boolean bConvertFontsToSpans)
  {
    m_eConvertFontsToSpans = ETriState.valueOf (bConvertFontsToSpans);
    return this;
  }

  /**
   * If you set this option to true, TinyMCE will convert all font elements to
   * span elements and generate span elements instead of font elements. This
   * option should be used in order to get more W3C compatible code, since font
   * elements are deprecated.
   *
   * @param aConvertFontsToSpans
   *        <code>true</code> to enabled, <code>false</code> to disable and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setConvertFontsToSpans (@Nullable final Boolean aConvertFontsToSpans)
  {
    m_eConvertFontsToSpans = ETriState.valueOf (aConvertFontsToSpans);
    return this;
  }

  // --- Content style ---

  // --- Visual aids ---

  // --- Undo/Redo ---

  // --- User interface ---

  @Nullable
  @ReturnsMutableObject ("Design")
  public TinyMCE4ToolbarControlList getToolbar ()
  {
    return m_aToolbar;
  }

  /**
   * This controls what buttons you want show up in the toolbar.
   *
   * @param aToolbar
   *        The toolbar to be set. May be <code>null</code>. If not
   *        <code>null</code> a clone of the object is stored.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setToolbar (@Nullable final TinyMCE4ToolbarControlList aToolbar)
  {
    m_aToolbar = aToolbar == null ? null : aToolbar.getClone ();
    return this;
  }

  public boolean isToolbarDisabled ()
  {
    return m_bToolbarDisabled;
  }

  /**
   * Manually enable or disable the toolbar.
   *
   * @param bToolbarDisabled
   *        <code>true</code> to disable it
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setToolbarDisabled (final boolean bToolbarDisabled)
  {
    m_bToolbarDisabled = bToolbarDisabled;
    return this;
  }

  @Nullable
  @ReturnsMutableObject ("Design")
  public TinyMCE4MenubarItemList getMenubar ()
  {
    return m_aMenubar;
  }

  /**
   * This option allows you to configure the menus you want to appear in the
   * menu bar.
   *
   * @param aMenubar
   *        The menu bar to be set. May be <code>null</code>. If not
   *        <code>null</code> a clone of the object is stored.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setMenubar (@Nullable final TinyMCE4MenubarItemList aMenubar)
  {
    m_aMenubar = aMenubar == null ? null : aMenubar.getClone ();
    return this;
  }

  public boolean isMenubarDisabled ()
  {
    return m_bMenubarDisabled;
  }

  /**
   * Manually enable or disable the toolbar.
   *
   * @param bMenubarDisabled
   *        <code>true</code> to disable it
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setMenubarDisabled (final boolean bMenubarDisabled)
  {
    m_bMenubarDisabled = bMenubarDisabled;
    return this;
  }

  public boolean isStatusbar ()
  {
    return m_eStatusbar.getAsBooleanValue (DEFAULT_STATUSBAR);
  }

  /**
   * Show or hide the statusbar.
   *
   * @param bStatusbar
   *        <code>true</code> to show, <code>false</code> to hide
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setStatusbar (final boolean bStatusbar)
  {
    m_eStatusbar = ETriState.valueOf (bStatusbar);
    return this;
  }

  /**
   * Show or hide the statusbar.
   *
   * @param aStatusbar
   *        <code>true</code> to show, <code>false</code> to hide and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setStatusbar (@Nullable final Boolean aStatusbar)
  {
    m_eStatusbar = ETriState.valueOf (aStatusbar);
    return this;
  }

  @Nullable
  public ETinyMCE4Resize getResize ()
  {
    return m_eResize;
  }

  @Nonnull
  public HCTinyMCE4 setResize (@Nullable final ETinyMCE4Resize eResize)
  {
    m_eResize = eResize;
    return this;
  }

  public int getWidth ()
  {
    return m_nWidth;
  }

  /**
   * Set the width of the editor.
   *
   * @param nWidth
   *        New width. Only values &ge; 0 are considered!
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setWidth (final int nWidth)
  {
    m_nWidth = nWidth;
    return this;
  }

  public int getHeight ()
  {
    return m_nHeight;
  }

  /**
   * Set the height of the editor.
   *
   * @param nHeight
   *        New height. Only values &ge; 0 are considered!
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setHeight (final int nHeight)
  {
    m_nHeight = nHeight;
    return this;
  }

  public boolean isPreviewStyles ()
  {
    return m_ePreviewStyles.getAsBooleanValue (DEFAULT_PREVIEW_STYLES);
  }

  /**
   * The enables you to turn of the preview of styles in format/style listboxes.
   * It's turned on by default.
   *
   * @param bPreviewStyles
   *        <code>true</code> to show, <code>false</code> to hide
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setPreviewStyles (final boolean bPreviewStyles)
  {
    m_ePreviewStyles = ETriState.valueOf (bPreviewStyles);
    return this;
  }

  /**
   * The enables you to turn of the preview of styles in format/style listboxes.
   * It's turned on by default.
   *
   * @param aPreviewStyles
   *        <code>true</code> to show, <code>false</code> to hide and
   *        <code>null</code> for default value.
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setPreviewStyles (@Nullable final Boolean aPreviewStyles)
  {
    m_ePreviewStyles = ETriState.valueOf (aPreviewStyles);
    return this;
  }

  // --- URL ---

  // --- Callbacks ---

  @Nullable
  public JSAnonymousFunction getFileBrowserCallback ()
  {
    return m_aFileBrowserCallback;
  }

  /**
   * This option enables you to add your own file browser/image browser to
   * TinyMCE. If this option is set with a value a browse button will appear in
   * different dialogues such as "insert/edit link" or "insert/edit image". If
   * this option hasn't got a value set (or equals to false or null) the
   * dialogues in question won't show any browse button. This function is
   * executed each time a user clicks on the "browse" buttons in various
   * dialogues. The format of this callback function is: fileBrowser(field_name,
   * url, type, win) where field_name is the id/name of the form element that
   * the browser should insert its URL into. The url parameter contains the URL
   * value that is currently inside the field. The type parameter contains what
   * type of browser to present; this value can be file, image or flash
   * depending on what dialogue is calling the function. The win parameter
   * contains a reference to the dialog/window that executes the function.
   *
   * @param aFileBrowserCallback
   *        Callback function
   * @return this
   */
  @Nonnull
  public HCTinyMCE4 setFileBrowserCallback (@Nullable final JSAnonymousFunction aFileBrowserCallback)
  {
    m_aFileBrowserCallback = aFileBrowserCallback;
    return this;
  }

  // --- custom options ---

  @Nonnull
  public HCTinyMCE4 addCustomOption (@Nonnull @Nonempty final String sName, final boolean bValue)
  {
    return addCustomOption (sName, JSExpr.lit (bValue));
  }

  @Nonnull
  public HCTinyMCE4 addCustomOption (@Nonnull @Nonempty final String sName, final int nValue)
  {
    return addCustomOption (sName, JSExpr.lit (nValue));
  }

  @Nonnull
  public HCTinyMCE4 addCustomOption (@Nonnull @Nonempty final String sName, final double dValue)
  {
    return addCustomOption (sName, JSExpr.lit (dValue));
  }

  @Nonnull
  public HCTinyMCE4 addCustomOption (@Nonnull @Nonempty final String sName, @Nonnull final BigDecimal aValue)
  {
    return addCustomOption (sName, JSExpr.lit (aValue));
  }

  @Nonnull
  public HCTinyMCE4 addCustomOption (@Nonnull @Nonempty final String sName, @Nonnull final String sValue)
  {
    return addCustomOption (sName, JSExpr.lit (sValue));
  }

  @Nonnull
  public HCTinyMCE4 addCustomOption (@Nonnull @Nonempty final String sName, @Nonnull final IJSExpression aValue)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notNull (aValue, "Value");
    m_aCustom.put (sName, aValue);
    return this;
  }

  @Nullable
  public IJSExpression removeCustomOption (@Nullable final String sName)
  {
    return m_aCustom.remove (sName);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <String, IJSExpression> getAllCustomOptions ()
  {
    return m_aCustom.getClone ();
  }

  public boolean containsCustomOption (@Nullable final String sName)
  {
    return m_aCustom.containsKey (sName);
  }

  @Nullable
  public IJSExpression getCustomOptionValue (@Nullable final String sName)
  {
    return m_aCustom.get (sName);
  }

  @Nonnegative
  public int getCustomOptionCount ()
  {
    return m_aCustom.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getJSInitOptions ()
  {
    final JSAssocArray aOptions = new JSAssocArray ();

    // General options
    if (StringHelper.hasText (m_sAutoFocus))
      aOptions.add ("auto_focus", m_sAutoFocus);
    if (m_eBrowserSpellcheck.isDefined ())
      aOptions.add ("browser_spellcheck", isBrowserSpellcheck ());
    if (m_eDirectionality != null)
      aOptions.add ("directionality", m_eDirectionality.getAttrValue ());
    if (m_eTinyMCELanguage != null)
      aOptions.add ("language", m_eTinyMCELanguage.getValue ());
    if (m_aLanguageURL != null)
      aOptions.add ("language_url", m_aLanguageURL.getAsStringWithEncodedParameters ());
    if (m_eNoWrap.isDefined ())
      aOptions.add ("nowrap", isNoWrap ());
    if (m_eObjectResizing.isDefined ())
      aOptions.add ("object_resizing", isObjectResizing ());
    if (m_aPlugins.isNotEmpty ())
    {
      final StringBuilder aSB = new StringBuilder ();
      for (final ETinyMCE4Plugin ePlugin : m_aPlugins)
      {
        if (aSB.length () > 0)
          aSB.append (' ');
        aSB.append (ePlugin.getValue ());
      }
      aOptions.add ("plugins", aSB.toString ());
    }
    if (m_aExternalPlugins.isNotEmpty ())
    {
      final IJsonObject aJsonObject = new JsonObject ();
      for (final TinyMCE4ExternalPlugin aExternalPlugin : m_aExternalPlugins)
        aJsonObject.add (aExternalPlugin.getPluginName (), aExternalPlugin.getPluginURL ().getAsStringWithEncodedParameters ());
      aOptions.add ("external_plugins", aJsonObject);
    }
    aOptions.add ("selector", m_sSelector);
    if (m_eSkin != null)
      aOptions.add ("skin", m_eSkin.getValue ());
    if (m_aSkinURL != null)
      aOptions.add ("skin_url", m_aSkinURL.getAsStringWithEncodedParameters ());
    if (m_eTheme != null)
      aOptions.add ("theme", m_eTheme.getValue ());
    if (m_aThemeURL != null)
      aOptions.add ("theme_url", m_aThemeURL.getAsStringWithEncodedParameters ());
    if (m_eInline.isDefined ())
      aOptions.add ("inline", isInline ());
    if (m_eHiddenInput.isDefined ())
      aOptions.add ("hidden_input", isHiddenInput ());

    // Cleanup/output
    if (m_eConvertFontsToSpans.isDefined ())
      aOptions.add ("convert_fonts_to_spans", isConvertFontsToSpans ());

    // Content style

    // Visual aids

    // Undo/Redo

    // User interface
    if (m_bToolbarDisabled)
      aOptions.add ("toolbar", JSExpr.FALSE);
    else
      if (m_aToolbar != null)
        aOptions.add ("toolbar", m_aToolbar.getAsOptionString ());
    if (m_bMenubarDisabled)
      aOptions.add ("menubar", JSExpr.FALSE);
    else
      if (m_aMenubar != null)
        aOptions.add ("menubar", m_aMenubar.getAsOptionString ());
    if (m_eStatusbar.isDefined ())
      aOptions.add ("statusbar", isStatusbar ());
    if (m_eResize != null)
      aOptions.add ("resize", m_eResize.getValue ());
    if (m_nWidth >= 0)
      aOptions.add ("width", m_nWidth);
    if (m_nHeight >= 0)
      aOptions.add ("height", m_nHeight);
    if (m_ePreviewStyles.isDefined ())
      aOptions.add ("preview_styles", isPreviewStyles ());

    // URL

    // Callbacks
    if (m_aFileBrowserCallback != null)
      aOptions.add ("file_browser_callback", m_aFileBrowserCallback);

    // Custom
    for (final Map.Entry <String, IJSExpression> aEntry : m_aCustom.entrySet ())
      aOptions.add (aEntry.getKey (), aEntry.getValue ());

    return aOptions;
  }

  /**
   * @return The JSInvocation with the tinymce.init code and all options
   *         specified for this instance.
   */
  @Nonnull
  public JSInvocation getJSInvocation ()
  {
    return JSTinyMCE4.init (getJSInitOptions ());
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    aTargetNode.addChild (new HCScriptInline (getJSInvocation ()));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (EUICoreJSPathProvider.JQUERY_3);
    PhotonJS.registerJSIncludeForThisRequest (ETinyMCE4JSPathProvider.TINYMCE_4);
  }
}
