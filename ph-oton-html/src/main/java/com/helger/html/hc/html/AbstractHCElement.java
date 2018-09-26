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
package com.helger.html.hc.html;

import java.util.Map;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.XMLConstants;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.cache.AnnotationUsageCache;
import com.helger.commons.collection.attr.AttributeContainer;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.property.ECSSProperty;
import com.helger.css.propertyvalue.ICSSValue;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLRole;
import com.helger.html.EHTMLVersion;
import com.helger.html.annotation.DeprecatedInHTML5;
import com.helger.html.annotation.SinceHTML5;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.impl.AbstractHCNode;
import com.helger.html.js.CJS;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.js.EJSEvent;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSEventMap;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.IMicroQName;
import com.helger.xml.microdom.MicroElement;
import com.helger.xml.microdom.MicroQName;

/**
 * Base class for an HC element.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The implementation type.
 */
@NotThreadSafe
public abstract class AbstractHCElement <IMPLTYPE extends AbstractHCElement <IMPLTYPE>> extends AbstractHCNode
                                        implements
                                        IHCElement <IMPLTYPE>
{
  /** Default translate mode */
  public static final ETriState DEFAULT_TRANSLATE = ETriState.UNDEFINED;

  /** By default an element is not unfocusable */
  public static final boolean DEFAULT_UNFOCUSABLE = false;

  /** By default an element is not hidden */
  public static final boolean DEFAULT_HIDDEN = false;

  /** By default an element is not spell checked */
  public static final boolean DEFAULT_SPELLCHECK = false;

  /** The HTML enum element */
  private final EHTMLElement m_eElement;
  /** The cached element name */
  private final String m_sElementName;

  private String m_sID;
  private String m_sTitle;
  private String m_sLanguage;
  private EHCTextDirection m_eDirection;
  private ICommonsOrderedSet <ICSSClassProvider> m_aCSSClassProviders;
  private ICommonsOrderedMap <ECSSProperty, ICSSValue> m_aStyles;
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

  private static final class HCAttrCont extends AttributeContainer <IMicroQName, String> implements IHCAttrContainer
  {}

  private final HCAttrCont m_aCustomAttrs = new HCAttrCont ();

  protected AbstractHCElement (@Nonnull final EHTMLElement eElement)
  {
    m_eElement = ValueEnforcer.notNull (eElement, "Element");
    // Always use lowercase element names
    m_sElementName = eElement.getElementName ();
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

  @Nullable
  public final String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public final IMPLTYPE setID (@Nullable final String sID)
  {
    // Check for existing ID
    return setID (sID, false);
  }

  public static boolean isValidID (@Nullable final String sID)
  {
    if (StringHelper.hasText (sID))
    {
      // RegEx check: !CXMLRegEx.PATTERN_NCNAME.matcher (sID).matches ()
      // Happens to often, since "[" and "]" occur very often and are not
      // allowed

      // Check if a whitespace is contained
      if (RegExHelper.stringMatchesPattern (".*\\s.*", sID))
        return false;
    }

    return true;
  }

  @Nonnull
  public final IMPLTYPE setID (@Nullable final String sID, final boolean bImSureToOverwriteAnExistingID)
  {
    if (!isValidID (sID))
    {
      // If the ID is absolutely invalid, log an error and don't set it
      HCConsistencyChecker.consistencyError ("HC object ID '" + sID + "' is invalid!");
    }
    else
    {
      if (!bImSureToOverwriteAnExistingID && m_sID != null)
        if (StringHelper.hasText (sID))
        {
          if (!m_sID.equals (sID))
            HCConsistencyChecker.consistencyError ("Overwriting HC object ID '" +
                                                   m_sID +
                                                   "' with '" +
                                                   sID +
                                                   "' - this may have side effects!");
        }
        else
        {
          HCConsistencyChecker.consistencyError ("The HC object ID '" +
                                                 m_sID +
                                                 "' will be removed - this may have side effects");
        }
      m_sID = sID;
    }
    return thisAsT ();
  }

  @Nullable
  public final String getTitle ()
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
        m_aCSSClassProviders = new CommonsLinkedHashSet <> ();
      m_aCSSClassProviders.add (aCSSClassProvider);
    }
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
  public final ICommonsOrderedSet <ICSSClassProvider> getAllClasses ()
  {
    return new CommonsLinkedHashSet <> (m_aCSSClassProviders);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsOrderedSet <String> getAllClassNames ()
  {
    final ICommonsOrderedSet <String> ret = new CommonsLinkedHashSet <> ();
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
    return m_aCSSClassProviders != null && m_aCSSClassProviders.isNotEmpty ();
  }

  @Nullable
  public final String getAllClassesAsString ()
  {
    if (m_aCSSClassProviders == null)
      return null;

    final int nCount = m_aCSSClassProviders.size ();
    if (nCount == 0)
      return null;

    if (nCount == 1)
      return m_aCSSClassProviders.getFirst ().getCSSClass ();

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
  public final ICommonsOrderedMap <ECSSProperty, ICSSValue> getAllStyles ()
  {
    return new CommonsLinkedHashMap <> (m_aStyles);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <ICSSValue> getAllStyleValues ()
  {
    return m_aStyles == null ? new CommonsArrayList <> () : m_aStyles.copyOfValues ();
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
    return EqualsHelper.equals (m_aStyles.get (eProp), aValue);
  }

  public final boolean hasAnyStyle ()
  {
    return m_aStyles != null && m_aStyles.isNotEmpty ();
  }

  @Nonnull
  public final IMPLTYPE addStyle (@Nullable final ICSSValue aValue)
  {
    if (aValue != null)
    {
      if (m_aStyles == null)
        m_aStyles = new CommonsLinkedHashMap <> ();
      m_aStyles.put (aValue.getProp (), aValue);
    }
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
      aSB.append (aValue.getAsCSSString (aCSSSettings));
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
  @ReturnsMutableObject ("design")
  public final JSEventMap getEventMap ()
  {
    return m_aJSHandler;
  }

  @Nullable
  public final IHasJSCode getEventHandler (@Nullable final EJSEvent eJSEvent)
  {
    return m_aJSHandler == null ? null : m_aJSHandler.getHandler (eJSEvent);
  }

  public final boolean containsEventHandler (@Nullable final EJSEvent eJSEvent)
  {
    return m_aJSHandler != null && m_aJSHandler.containsHandler (eJSEvent);
  }

  @Nonnull
  public final IMPLTYPE addEventHandler (@Nonnull final EJSEvent eJSEvent, @Nullable final IHasJSCode aJSCode)
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
  public final IMPLTYPE prependEventHandler (@Nonnull final EJSEvent eJSEvent, @Nullable final IHasJSCode aJSCode)
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
  public final IMPLTYPE setEventHandler (@Nonnull final EJSEvent eJSEvent, @Nullable final IHasJSCode aJSCode)
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
  public final ETriState getTranslate ()
  {
    return m_eTranslate;
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

  @Nonnull
  @ReturnsMutableCopy
  public final IHCAttrContainer customAttrs ()
  {
    return m_aCustomAttrs;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    // Unfocusable?
    if (m_bUnfocusable)
      addEventHandler (EJSEvent.FOCUS, FakeJS.JS_BLUR);
  }

  private static final AnnotationUsageCache s_aAUC_D_HTML5 = new AnnotationUsageCache (DeprecatedInHTML5.class);
  private static final AnnotationUsageCache s_aAUC_S_HTML5 = new AnnotationUsageCache (SinceHTML5.class);

  private static void _checkDeprecation (final Class <?> aElementClass,
                                         final String sElementName,
                                         final EHTMLVersion eHTMLVersion)
  {
    if (eHTMLVersion.isAtLeastHTML5 ())
    {
      // HTML5 specifics checks
      if (s_aAUC_D_HTML5.hasAnnotation (aElementClass))
        HCConsistencyChecker.consistencyError ("The element '" + sElementName + "' is deprecated in HTML5");
    }
    else
    {
      // pre-HTML5 checks
      if (s_aAUC_S_HTML5.hasAnnotation (aElementClass))
        HCConsistencyChecker.consistencyError ("The element '" + sElementName + "' is only available in HTML5");
    }
  }

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    final EHTMLVersion eHTMLVersion = aConversionSettings.getHTMLVersion ();

    // Deprecation is checked for all elements
    _checkDeprecation (getClass (), getTagName (), eHTMLVersion);

    if (eHTMLVersion.isAtLeastHTML5 ())
    {
      if (m_aCustomAttrs != null)
        for (final Map.Entry <IMicroQName, String> aEntry : m_aCustomAttrs.entrySet ())
        {
          final String sAttrName = aEntry.getKey ().getName ();
          // Link element often contains arbitrary attributes
          if (!StringHelper.startsWith (sAttrName, CHTMLAttributes.HTML5_PREFIX_DATA) &&
              !StringHelper.startsWith (sAttrName, CHTMLAttributes.PREFIX_ARIA) &&
              m_eElement != EHTMLElement.LINK)
          {
            HCConsistencyChecker.consistencyError ("Custom HTML5 attribute '" +
                                                   sAttrName +
                                                   "' does not start with one of the proposed prefixes '" +
                                                   CHTMLAttributes.HTML5_PREFIX_DATA +
                                                   "' or '" +
                                                   CHTMLAttributes.PREFIX_ARIA +
                                                   "'");
          }
        }
    }
  }

  /**
   * @param aConversionSettings
   *        The conversion settings to be used
   * @return The created micro element for this HC element. May not be
   *         <code>null</code>.
   */
  @OverrideOnDemand
  @Nonnull
  protected IMicroElement createMicroElement (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return new MicroElement (aConversionSettings.getHTMLNamespaceURI (), m_sElementName);
  }

  /**
   * Set all attributes and child elements of this object
   *
   * @param aElement
   *        The current micro element to be filled. Never <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. Never <code>null</code>.
   */
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (@Nonnull final IMicroElement aElement,
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
      aElement.setAttribute (new MicroQName (XMLConstants.XML_NS_URI, CHTMLAttributes.LANG.getName ()), m_sLanguage);
      aElement.setAttribute (CHTMLAttributes.LANG, m_sLanguage);
    }

    if (m_eDirection != null)
      aElement.setAttribute (CHTMLAttributes.DIR, m_eDirection);

    aElement.setAttribute (CHTMLAttributes.CLASS, getAllClassesAsString ());

    aElement.setAttribute (CHTMLAttributes.STYLE, getAllStylesAsString (aConversionSettings.getCSSWriterSettings ()));

    // Emit all JS events
    if (m_aJSHandler != null)
    {
      final IJSWriterSettings aJSWriterSettings = aConversionSettings.getJSWriterSettings ();

      // Loop over all events in the defined order for consistent results
      for (final EJSEvent eEvent : EJSEvent.values ())
      {
        final CollectingJSCodeProvider aProvider = m_aJSHandler.getHandler (eEvent);
        if (aProvider != null)
        {
          final String sJSCode = aProvider.getJSCode (aJSWriterSettings);
          aElement.setAttribute (eEvent.getHTMLEventName (), CJS.JS_PREFIX + sJSCode);
        }
      }
    }

    // unfocusable is handled by the customizer as it is non-standard

    // Global attributes
    if (m_nTabIndex != DEFAULT_TABINDEX)
      aElement.setAttribute (CHTMLAttributes.TABINDEX, m_nTabIndex);
    if (StringHelper.hasText (m_sAccessKey))
      aElement.setAttribute (CHTMLAttributes.ACCESSKEY, m_sAccessKey);

    // Global HTML5 attributes
    if (bHTML5)
    {
      if (m_eTranslate.isDefined ())
        aElement.setAttribute (CHTMLAttributes.TRANSLATE,
                               m_eTranslate.isTrue () ? CHTMLAttributeValues.YES : CHTMLAttributeValues.NO);
      if (m_eContentEditable != null)
        aElement.setAttribute (CHTMLAttributes.CONTENTEDITABLE, m_eContentEditable);
      if (StringHelper.hasText (m_sContextMenuID))
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

    if (m_aCustomAttrs != null)
      for (final Map.Entry <IMicroQName, String> aEntry : m_aCustomAttrs.entrySet ())
        aElement.setAttribute (aEntry.getKey (), aEntry.getValue ());
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
  protected void finishMicroElement (@Nonnull final IMicroElement eElement,
                                     @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {}

  /*
   * Note: return type cannot by IMicroElement since the checkbox object delivers
   * an IMicroNodeList!
   */
  @Override
  @Nonnull
  @OverridingMethodsMustInvokeSuper
  protected IMicroNode internalConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Create the element
    final IMicroElement ret = createMicroElement (aConversionSettings);
    if (ret == null)
      throw new IllegalStateException ("Created a null element!");

    // Set all HTML attributes etc.
    fillMicroElement (ret, aConversionSettings);

    // Optional callback after everything was done (implementation dependent)
    finishMicroElement (ret, aConversionSettings);
    return ret;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("element", m_eElement)
                            .append ("elementName", m_sElementName)
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
                            .appendIfNotNull ("translate", m_eTranslate)
                            .appendIfNotNull ("contentEditable", m_eContentEditable)
                            .appendIfNotNull ("contextMenu", m_sContextMenuID)
                            .appendIfNotNull ("draggable", m_eDraggable)
                            .appendIfNotNull ("dropZone", m_eDropZone)
                            .append ("hidden", m_bHidden)
                            .append ("spellcheck", m_bSpellCheck)
                            .appendIfNotNull ("role", m_eRole)
                            .appendIfNotNull ("customAttrs", m_aCustomAttrs)
                            .getToString ();
  }
}
