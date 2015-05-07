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
package com.helger.html.hc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.XMLConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.microdom.impl.MicroElement;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.property.CSSPropertyFree;
import com.helger.css.property.ECSSProperty;
import com.helger.css.propertyvalue.ICSSValue;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLRole;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.api.EHCTextDirection;
import com.helger.html.hc.api5.EHCContentEditable;
import com.helger.html.hc.api5.EHCDraggable;
import com.helger.html.hc.api5.EHCDropZone;
import com.helger.html.hc.conversion.HCConsistencyChecker;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.JSEventMap;

@NotThreadSafe
public abstract class AbstractHCElement <IMPLTYPE extends AbstractHCElement <IMPLTYPE>> extends AbstractHCNode implements IHCElement <IMPLTYPE>
{
  /** Default translate mode */
  public static final ETriState DEFAULT_TRANSLATE = ETriState.UNDEFINED;

  /** By default an element is not unfocusable */
  public static final boolean DEFAULT_UNFOCUSABLE = false;

  /** By default an element is not hidden */
  public static final boolean DEFAULT_HIDDEN = false;

  /** By default an element is not spell checked */
  public static final boolean DEFAULT_SPELLCHECK = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractHCElement.class);

  /** The HTML enum element */
  private final EHTMLElement m_eElement;
  /** The cached element name */
  private final String m_sElementName;

  private String m_sID;
  private String m_sTitle;
  private String m_sLanguage;
  private EHCTextDirection m_eDirection;
  // Must be a LinkedHashSet:
  private Set <ICSSClassProvider> m_aCSSClassProviders;
  // Must be a LinkedHashMap:
  private Map <ECSSProperty, ICSSValue> m_aStyles;
  /*
   * Use 1 pointer instead of many to save memory if no handler is used at all
   * (which happens quite often)!
   */
  private JSEventMap m_aJSHandler;
  private boolean m_bUnfocusable = DEFAULT_UNFOCUSABLE;
  private long m_nTabIndex = DEFAULT_TABINDEX;
  private String m_sAccessKey;

  // HTML5 global attributes
  private ETriState m_eTranslate = DEFAULT_TRANSLATE;
  private EHCContentEditable m_eContentEditable;
  private String m_sContextMenuID;
  private EHCDraggable m_eDraggable;
  private EHCDropZone m_eDropZone;
  private boolean m_bHidden = DEFAULT_HIDDEN;
  private boolean m_bSpellCheck = DEFAULT_SPELLCHECK;
  private EHTMLRole m_eRole;

  // Must be a LinkedHashMap_
  private Map <String, String> m_aCustomAttrs;

  protected AbstractHCElement (@Nonnull final EHTMLElement eElement)
  {
    m_eElement = ValueEnforcer.notNull (eElement, "Element");
    m_sElementName = eElement.getElementNameLowerCase ();
  }

  @Nonnull
  public final EHTMLElement getElement ()
  {
    return m_eElement;
  }

  @Nonnull
  @Nonempty
  public final String getTagName ()
  {
    return m_sElementName;
  }

  @Nonnull
  protected final IMPLTYPE thisAsT ()
  {
    // Avoid the unchecked cast warning in all places
    return GenericReflection.<AbstractHCElement <IMPLTYPE>, IMPLTYPE> uncheckedCast (this);
  }

  public final boolean hasID ()
  {
    return StringHelper.hasText (m_sID);
  }

  @Nullable
  public final String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public final IMPLTYPE setID (@Nullable final String sID)
  {
    if (StringHelper.hasText (sID))
    {
      // RegEx check: !CXMLRegEx.PATTERN_NCNAME.matcher (sID).matches ()
      // Happens to often, since "[" and "]" occur very often and are not
      // allowed

      // Check if a whitespace is contained
      if (RegExHelper.stringMatchesPattern (".*\\s.*", sID))
        throw new IllegalArgumentException ("ID '" + sID + "' may not contains whitespace chars!");
    }
    m_sID = sID;
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setUniqueID ()
  {
    return setID (GlobalIDFactory.getNewStringID ());
  }

  @Nonnull
  public IMPLTYPE ensureID ()
  {
    if (!hasID ())
      setUniqueID ();
    return thisAsT ();
  }

  @Nullable
  protected final String getTitle ()
  {
    return m_sTitle;
  }

  @Nonnull
  public final IMPLTYPE setTitle (@Nullable final String sTitle)
  {
    m_sTitle = sTitle;
    return thisAsT ();
  }

  public final boolean containsClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return m_aCSSClassProviders != null &&
           aCSSClassProvider != null &&
           m_aCSSClassProviders.contains (aCSSClassProvider);
  }

  @Nonnull
  public final IMPLTYPE addClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    if (aCSSClassProvider != null)
    {
      if (m_aCSSClassProviders == null)
        m_aCSSClassProviders = new LinkedHashSet <ICSSClassProvider> ();
      m_aCSSClassProviders.add (aCSSClassProvider);
    }
    return thisAsT ();
  }

  @Deprecated
  @DevelopersNote ("Use addClass - singular")
  @Nonnull
  public final IMPLTYPE addClasses (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return addClass (aCSSClassProvider);
  }

  @Nonnull
  public final IMPLTYPE addClasses (@Nullable final ICSSClassProvider... aCSSClassProviders)
  {
    if (aCSSClassProviders != null)
      for (final ICSSClassProvider aProvider : aCSSClassProviders)
        addClass (aProvider);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addClasses (@Nullable final Iterable <? extends ICSSClassProvider> aCSSClassProviders)
  {
    if (aCSSClassProviders != null)
      for (final ICSSClassProvider aProvider : aCSSClassProviders)
        addClass (aProvider);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    if (m_aCSSClassProviders != null && aCSSClassProvider != null)
      m_aCSSClassProviders.remove (aCSSClassProvider);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllClasses ()
  {
    if (m_aCSSClassProviders != null)
      m_aCSSClassProviders.clear ();
    return thisAsT ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Set <ICSSClassProvider> getAllClasses ()
  {
    return CollectionHelper.newOrderedSet (m_aCSSClassProviders);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Set <String> getAllClassNames ()
  {
    final Set <String> ret = new LinkedHashSet <String> ();
    if (m_aCSSClassProviders != null)
      for (final ICSSClassProvider aCSSClassProvider : m_aCSSClassProviders)
      {
        final String sCSSClass = aCSSClassProvider.getCSSClass ();
        if (StringHelper.hasText (sCSSClass))
          ret.add (sCSSClass);
      }
    return ret;
  }

  public final boolean hasAnyClass ()
  {
    return m_aCSSClassProviders != null && !m_aCSSClassProviders.isEmpty ();
  }

  @Nullable
  public final String getAllClassesAsString ()
  {
    if (m_aCSSClassProviders == null || m_aCSSClassProviders.isEmpty ())
      return null;

    final StringBuilder aSB = new StringBuilder ();
    for (final ICSSClassProvider aCSSClassProvider : m_aCSSClassProviders)
    {
      final String sCSSClass = aCSSClassProvider.getCSSClass ();
      if (StringHelper.hasText (sCSSClass))
      {
        if (aSB.length () > 0)
          aSB.append (' ');
        aSB.append (sCSSClass);
      }
    }
    return aSB.toString ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Map <ECSSProperty, ICSSValue> getAllStyles ()
  {
    return CollectionHelper.newMap (m_aStyles);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Collection <ICSSValue> getAllStyleValues ()
  {
    return m_aStyles == null ? new ArrayList <ICSSValue> () : CollectionHelper.newList (m_aStyles.values ());
  }

  @Nullable
  public final ICSSValue getStyleValue (@Nullable final ECSSProperty eProperty)
  {
    return eProperty == null || m_aStyles == null ? null : m_aStyles.get (eProperty);
  }

  public final boolean containsStyle (@Nullable final ECSSProperty eProperty)
  {
    return m_aStyles != null && m_aStyles.containsKey (eProperty);
  }

  public final boolean hasStyle (@Nullable final ICSSValue aValue)
  {
    if (aValue == null || m_aStyles == null)
      return false;

    // Contained styles can never have a null value!
    final ECSSProperty eProp = aValue.getProp ();
    return EqualsUtils.equals (m_aStyles.get (eProp), aValue);
  }

  public final boolean hasAnyStyle ()
  {
    return m_aStyles != null && !m_aStyles.isEmpty ();
  }

  @Nonnull
  public final IMPLTYPE addStyle (@Nonnull final ECSSProperty eProperty, @Nonnull @Nonempty final String sPropertyValue)
  {
    return addStyle (new CSSPropertyFree (eProperty).newValue (sPropertyValue));
  }

  @Nonnull
  public final IMPLTYPE addStyle (@Nullable final ICSSValue aValue)
  {
    if (aValue != null)
    {
      if (m_aStyles == null)
        m_aStyles = new LinkedHashMap <ECSSProperty, ICSSValue> ();
      m_aStyles.put (aValue.getProp (), aValue);
    }
    return thisAsT ();
  }

  @Nonnull
  @DevelopersNote ("Use addStyle - singular")
  @Deprecated
  public final IMPLTYPE addStyles (@Nullable final ICSSValue aValue)
  {
    return addStyle (aValue);
  }

  @Nonnull
  public final IMPLTYPE addStyles (@Nullable final ICSSValue... aValues)
  {
    if (aValues != null)
      for (final ICSSValue aValue : aValues)
        addStyle (aValue);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addStyles (@Nullable final Iterable <? extends ICSSValue> aValues)
  {
    if (aValues != null)
      for (final ICSSValue aValue : aValues)
        addStyle (aValue);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeStyle (@Nonnull final ECSSProperty eProperty)
  {
    if (m_aStyles != null)
      m_aStyles.remove (eProperty);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllStyles ()
  {
    m_aStyles.clear ();
    return thisAsT ();
  }

  @Nullable
  public final String getAllStylesAsString (@Nonnull final ICSSWriterSettings aCSSSettings)
  {
    if (m_aStyles == null || m_aStyles.isEmpty ())
      return null;
    final StringBuilder aSB = new StringBuilder ();
    for (final ICSSValue aValue : m_aStyles.values ())
      aSB.append (aValue.getAsCSSString (aCSSSettings, 0));
    return aSB.toString ();
  }

  @Nullable
  public final EHCTextDirection getDirection ()
  {
    return m_eDirection;
  }

  @Nonnull
  public final IMPLTYPE setDirection (@Nullable final EHCTextDirection eDirection)
  {
    m_eDirection = eDirection;
    return thisAsT ();
  }

  @Nullable
  public final String getLanguage ()
  {
    return m_sLanguage;
  }

  @Nonnull
  public final IMPLTYPE setLanguage (@Nullable final String sLanguage)
  {
    m_sLanguage = sLanguage;
    return thisAsT ();
  }

  @Nullable
  public final IJSCodeProvider getEventHandler (@Nullable final EJSEvent eJSEvent)
  {
    return m_aJSHandler == null ? null : m_aJSHandler.getHandler (eJSEvent);
  }

  public final boolean containsEventHandler (@Nullable final EJSEvent eJSEvent)
  {
    return m_aJSHandler != null && m_aJSHandler.containsHandler (eJSEvent);
  }

  @Nonnull
  public final IMPLTYPE addEventHandler (@Nonnull final EJSEvent eJSEvent, @Nullable final IJSCodeProvider aJSCode)
  {
    if (aJSCode != null)
    {
      if (m_aJSHandler == null)
        m_aJSHandler = new JSEventMap ();
      m_aJSHandler.addHandler (eJSEvent, aJSCode);
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE prependEventHandler (@Nonnull final EJSEvent eJSEvent, @Nullable final IJSCodeProvider aJSCode)
  {
    if (aJSCode != null)
    {
      if (m_aJSHandler == null)
        m_aJSHandler = new JSEventMap ();
      m_aJSHandler.prependHandler (eJSEvent, aJSCode);
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setEventHandler (@Nonnull final EJSEvent eJSEvent, @Nullable final IJSCodeProvider aJSCode)
  {
    if (aJSCode != null)
    {
      if (m_aJSHandler == null)
        m_aJSHandler = new JSEventMap ();
      m_aJSHandler.setHandler (eJSEvent, aJSCode);
    }
    else
      if (m_aJSHandler != null)
        m_aJSHandler.removeHandler (eJSEvent);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllEventHandler (@Nullable final EJSEvent eJSEvent)
  {
    if (m_aJSHandler != null)
      m_aJSHandler.removeHandler (eJSEvent);
    return thisAsT ();
  }

  public final boolean isUnfocusable ()
  {
    return m_bUnfocusable;
  }

  @Nonnull
  public final IMPLTYPE setUnfocusable (final boolean bUnfocusable)
  {
    m_bUnfocusable = bUnfocusable;
    return thisAsT ();
  }

  public final boolean isHidden ()
  {
    return m_bHidden;
  }

  @Nonnull
  public final IMPLTYPE setHidden (final boolean bHidden)
  {
    m_bHidden = bHidden;
    return thisAsT ();
  }

  @CheckForSigned
  public final long getTabIndex ()
  {
    return m_nTabIndex;
  }

  @Nonnull
  public final IMPLTYPE setTabIndex (final long nTabIndex)
  {
    m_nTabIndex = nTabIndex;
    return thisAsT ();
  }

  @Nullable
  public final String getAccessKey ()
  {
    return m_sAccessKey;
  }

  @Nonnull
  public final IMPLTYPE setAccessKey (@Nullable final String sAccessKey)
  {
    m_sAccessKey = sAccessKey;
    return thisAsT ();
  }

  public final boolean isTranslateOn ()
  {
    return m_eTranslate.isTrue ();
  }

  public final boolean isTranslateOff ()
  {
    return m_eTranslate.isFalse ();
  }

  public final boolean isTranslateUndefined ()
  {
    return m_eTranslate.isUndefined ();
  }

  @Nonnull
  public final IMPLTYPE setTranslate (final boolean bTranslate)
  {
    return setTranslate (ETriState.valueOf (bTranslate));
  }

  @Nonnull
  public final IMPLTYPE setTranslate (@Nonnull final ETriState eTranslate)
  {
    m_eTranslate = ValueEnforcer.notNull (eTranslate, "Translate");
    return thisAsT ();
  }

  @Nullable
  public final EHCContentEditable getContentEditable ()
  {
    return m_eContentEditable;
  }

  @Nonnull
  public final IMPLTYPE setContentEditable (@Nullable final EHCContentEditable eContentEditable)
  {
    m_eContentEditable = eContentEditable;
    return thisAsT ();
  }

  @Nullable
  public final String getContextMenu ()
  {
    return m_sContextMenuID;
  }

  @Nonnull
  public final IMPLTYPE setContextMenu (@Nullable final String sContextMenuID)
  {
    m_sContextMenuID = sContextMenuID;
    return thisAsT ();
  }

  @Nullable
  public final EHCDraggable getDraggable ()
  {
    return m_eDraggable;
  }

  @Nonnull
  public final IMPLTYPE setDraggable (@Nullable final EHCDraggable eDraggable)
  {
    m_eDraggable = eDraggable;
    return thisAsT ();
  }

  @Nullable
  public final EHCDropZone getDropZone ()
  {
    return m_eDropZone;
  }

  @Nonnull
  public final IMPLTYPE setDropZone (@Nullable final EHCDropZone eDropZone)
  {
    m_eDropZone = eDropZone;
    return thisAsT ();
  }

  public final boolean isSpellCheck ()
  {
    return m_bSpellCheck;
  }

  @Nonnull
  public final IMPLTYPE setSpellCheck (final boolean bSpellCheck)
  {
    m_bSpellCheck = bSpellCheck;
    return thisAsT ();
  }

  @Nullable
  public final EHTMLRole getRole ()
  {
    return m_eRole;
  }

  @Nonnull
  public final IMPLTYPE setRole (@Nullable final EHTMLRole eRole)
  {
    m_eRole = eRole;
    return thisAsT ();
  }

  public boolean hasCustomAttrs ()
  {
    return CollectionHelper.isNotEmpty (m_aCustomAttrs);
  }

  @Nonnegative
  public int getCustomAttrCount ()
  {
    return CollectionHelper.getSize (m_aCustomAttrs);
  }

  public boolean containsCustomAttr (@Nullable final String sName)
  {
    return m_aCustomAttrs != null && StringHelper.hasText (sName) ? m_aCustomAttrs.containsKey (sName) : false;
  }

  @Nullable
  public final String getCustomAttrValue (@Nullable final String sName)
  {
    return m_aCustomAttrs != null && StringHelper.hasText (sName) ? m_aCustomAttrs.get (sName) : null;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Map <String, String> getAllCustomAttrs ()
  {
    return CollectionHelper.newOrderedMap (m_aCustomAttrs);
  }

  @Nonnull
  public final IMPLTYPE setCustomAttr (@Nullable final String sName, @Nullable final String sValue)
  {
    if (StringHelper.hasText (sName) && sValue != null)
    {
      if (m_aCustomAttrs == null)
        m_aCustomAttrs = new LinkedHashMap <String, String> ();
      m_aCustomAttrs.put (sName, sValue);
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setCustomAttr (@Nullable final String sName, final int nValue)
  {
    return setCustomAttr (sName, Integer.toString (nValue));
  }

  @Nonnull
  public final IMPLTYPE setCustomAttr (@Nullable final String sName, final long nValue)
  {
    return setCustomAttr (sName, Long.toString (nValue));
  }

  @Nonnull
  public final IMPLTYPE removeCustomAttr (@Nullable final String sName)
  {
    if (m_aCustomAttrs != null && sName != null)
      m_aCustomAttrs.remove (sName);
    return thisAsT ();
  }

  public static boolean isDataAttrName (@Nullable final String sName)
  {
    return StringHelper.startsWith (sName, CHTMLAttributes.HTML5_PREFIX_DATA);
  }

  @Nullable
  public static String makeDataAttrName (@Nullable final String sName)
  {
    return StringHelper.hasNoText (sName) ? sName : CHTMLAttributes.HTML5_PREFIX_DATA + sName;
  }

  public boolean hasDataAttrs ()
  {
    if (m_aCustomAttrs != null)
      for (final String sName : m_aCustomAttrs.keySet ())
        if (isDataAttrName (sName))
          return true;
    return false;
  }

  public boolean containsDataAttr (@Nullable final String sName)
  {
    return containsCustomAttr (makeDataAttrName (sName));
  }

  @Nullable
  public String getDataAttrValue (@Nullable final String sName)
  {
    return getCustomAttrValue (makeDataAttrName (sName));
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, String> getAllDataAttrs ()
  {
    final Map <String, String> ret = new LinkedHashMap <String, String> ();
    if (m_aCustomAttrs != null)
      for (final Map.Entry <String, String> aEntry : m_aCustomAttrs.entrySet ())
        if (isDataAttrName (aEntry.getKey ()))
          ret.put (aEntry.getKey (), aEntry.getValue ());
    return ret;
  }

  @Nonnull
  public IMPLTYPE setDataAttr (@Nullable final String sName, final int nValue)
  {
    return setCustomAttr (makeDataAttrName (sName), nValue);
  }

  @Nonnull
  public IMPLTYPE setDataAttr (@Nullable final String sName, final long nValue)
  {
    return setCustomAttr (makeDataAttrName (sName), nValue);
  }

  @Nonnull
  public IMPLTYPE setDataAttr (@Nullable final String sName, @Nullable final String sValue)
  {
    return setCustomAttr (makeDataAttrName (sName), sValue);
  }

  @Nonnull
  public IMPLTYPE removeDataAttr (@Nullable final String sName)
  {
    return removeCustomAttr (makeDataAttrName (sName));
  }

  /**
   * @param aConversionSettings
   *        The conversion settings to be used
   * @return The created micro element for this HC element. May not be
   *         <code>null</code>.
   */
  @OverrideOnDemand
  @Nonnull
  protected IMicroElement createElement (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return new MicroElement (aConversionSettings.getHTMLNamespaceURI (), m_sElementName);
  }

  /**
   * Set all attributes and child elements of this object
   *
   * @param aElement
   *        The current micro element to be filled
   * @param aConversionSettings
   *        The conversion settings to be used
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (@Nonnull final IMicroElement aElement,
                                  @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    final boolean bHTML5 = aConversionSettings.getHTMLVersion ().isAtLeastHTML5 ();

    if (StringHelper.hasText (m_sID))
      aElement.setAttribute (CHTMLAttributes.ID, m_sID);

    if (StringHelper.hasText (m_sTitle))
      aElement.setAttribute (CHTMLAttributes.TITLE, m_sTitle);

    if (StringHelper.hasText (m_sLanguage))
    {
      // Both "xml:lang" and "lang"
      aElement.setAttribute (XMLConstants.XML_NS_URI, CHTMLAttributes.LANG, m_sLanguage);
      aElement.setAttribute (CHTMLAttributes.LANG, m_sLanguage);
    }

    if (m_eDirection != null)
      aElement.setAttribute (CHTMLAttributes.DIR, m_eDirection);

    aElement.setAttribute (CHTMLAttributes.CLASS, getAllClassesAsString ());

    aElement.setAttribute (CHTMLAttributes.STYLE, getAllStylesAsString (aConversionSettings.getCSSWriterSettings ()));

    // Emit all JS events
    if (m_aJSHandler != null)
      m_aJSHandler.applyToElement (aElement, aConversionSettings.getJSWriterSettings ());

    // unfocusable is handled by the customizer as it is non-standard

    // Global attributes
    if (m_nTabIndex != DEFAULT_TABINDEX)
      aElement.setAttribute (CHTMLAttributes.TABINDEX, m_nTabIndex);
    if (StringHelper.hasNoText (m_sAccessKey))
      aElement.setAttribute (CHTMLAttributes.ACCESSKEY, m_sAccessKey);

    // Global HTML5 attributes
    if (bHTML5)
    {
      if (m_eTranslate.isDefined ())
        aElement.setAttribute (CHTMLAttributes.TRANSLATE, m_eTranslate.isTrue () ? CHTMLAttributeValues.YES
                                                                                : CHTMLAttributeValues.NO);
      if (m_eContentEditable != null)
        aElement.setAttribute (CHTMLAttributes.CONTENTEDITABLE, m_eContentEditable);
      if (StringHelper.hasNoText (m_sContextMenuID))
        aElement.setAttribute (CHTMLAttributes.CONTEXTMENU, m_sContextMenuID);
      if (m_eDraggable != null)
        aElement.setAttribute (CHTMLAttributes.DRAGGABLE, m_eDraggable);
      if (m_eDropZone != null)
        aElement.setAttribute (CHTMLAttributes.DROPZONE, m_eDropZone);
      if (m_bHidden)
        aElement.setAttribute (CHTMLAttributes.HIDDEN, CHTMLAttributeValues.HIDDEN);
      if (m_bSpellCheck)
        aElement.setAttribute (CHTMLAttributes.SPELLCHECK, CHTMLAttributeValues.SPELLCHECK);
    }

    if (m_eRole != null)
      aElement.setAttribute (CHTMLAttributes.ROLE, m_eRole.getID ());

    if (m_aCustomAttrs != null && !m_aCustomAttrs.isEmpty ())
      for (final Map.Entry <String, String> aEntry : m_aCustomAttrs.entrySet ())
      {
        final String sAttrName = aEntry.getKey ();
        // Link element often contains arbitrary attributes
        if (bHTML5 &&
            aConversionSettings.areConsistencyChecksEnabled () &&
            !StringHelper.startsWith (sAttrName, CHTMLAttributes.HTML5_PREFIX_DATA) &&
            !StringHelper.startsWith (sAttrName, CHTMLAttributes.PREFIX_ARIA) &&
            m_eElement != EHTMLElement.LINK)
        {
          s_aLogger.warn ("Custom attribute '" +
                          sAttrName +
                          "' does not start with proposed prefix '" +
                          CHTMLAttributes.HTML5_PREFIX_DATA +
                          "' or '" +
                          CHTMLAttributes.PREFIX_ARIA +
                          "'");
        }
        aElement.setAttribute (sAttrName, aEntry.getValue ());
      }
  }

  /**
   * This method is called after the element itself was created and filled.
   * Overwrite this method to perform actions that can only be done after the
   * element was build finally.
   *
   * @param eElement
   *        The created micro element
   * @param aConversionSettings
   *        The conversion settings to be used
   */
  @OverrideOnDemand
  protected void finishAfterApplyProperties (@Nonnull final IMicroElement eElement,
                                             @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {}

  /*
   * Note: return type cannot by IMicroElement since the checkbox object
   * delivers an IMicroNodeList!
   */
  @Override
  @Nonnull
  @OverridingMethodsMustInvokeSuper
  protected IMicroNode internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Run some consistency checks if desired
    if (aConversionSettings.areConsistencyChecksEnabled ())
      HCConsistencyChecker.runConsistencyCheckBeforeCreation (this, aConversionSettings.getHTMLVersion ());

    // Create the element
    final IMicroElement ret = createElement (aConversionSettings);
    if (ret == null)
      throw new IllegalStateException ("Created a null element!");

    // Set all HTML attributes etc.
    applyProperties (ret, aConversionSettings);

    // Optional callback after everything was done (implementation dependent)
    finishAfterApplyProperties (ret, aConversionSettings);
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("element", m_eElement)
                                       .appendIfNotNull ("ID", m_sID)
                                       .appendIfNotNull ("title", m_sTitle)
                                       .appendIfNotNull ("language", m_sLanguage)
                                       .appendIfNotNull ("direction", m_eDirection)
                                       .appendIfNotNull ("classes", m_aCSSClassProviders)
                                       .appendIfNotNull ("styles", m_aStyles)
                                       .appendIfNotNull ("JSHandler", m_aJSHandler)
                                       .append ("unfocusable", m_bUnfocusable)
                                       .append ("tabIndex", m_nTabIndex)
                                       .appendIfNotNull ("accessKey", m_sAccessKey)
                                       .appendIfNotNull ("contentEditable", m_eContentEditable)
                                       .appendIfNotNull ("contextMenu", m_sContextMenuID)
                                       .appendIfNotNull ("draggable", m_eDraggable)
                                       .appendIfNotNull ("dropZone", m_eDropZone)
                                       .append ("hidden", m_bHidden)
                                       .append ("spellcheck", m_bSpellCheck)
                                       .appendIfNotNull ("customAttrs", m_aCustomAttrs)
                                       .toString ();
  }
}
