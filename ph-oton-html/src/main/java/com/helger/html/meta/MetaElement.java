/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.meta;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import javax.xml.XMLConstants;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.clone.ICloneable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.collection.commons.ICommonsOrderedSet;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.text.locale.LocaleHelper;
import com.helger.xml.microdom.IMicroContainer;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.MicroContainer;
import com.helger.xml.microdom.MicroQName;

/**
 * Represents a single HTML meta element.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MetaElement implements IMutableMetaElement, ICloneable <MetaElement>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MetaElement.class);

  /** HTTP equivalent or not? */
  private EMetaElementType m_eType;

  /** tag name. */
  private String m_sName;

  /** optional scheme string. */
  private String m_sScheme;

  /** locale to value map. */
  private final ICommonsOrderedMap <Locale, String> m_aContents = new CommonsLinkedHashMap <> ();

  public MetaElement (@NonNull final IMetaElement aOther)
  {
    setType (aOther.getType ());
    setName (aOther.getName ());
    setScheme (aOther.getScheme ());
    m_aContents.putAll (aOther.getContentMap ());
  }

  protected MetaElement (@NonNull final EMetaElementType eType,
                         @NonNull final String sName,
                         @Nullable final String sScheme,
                         @Nullable final Locale aContentLocale,
                         @Nullable final String sContent)
  {
    setType (eType);
    setName (sName);
    setScheme (sScheme);
    setContent (aContentLocale, sContent);
  }

  @NonNull
  public EMetaElementType getType ()
  {
    return m_eType;
  }

  @NonNull
  public final EChange setType (@NonNull final EMetaElementType eType)
  {
    ValueEnforcer.notNull (eType, "Type");

    if (eType.equals (m_eType))
      return EChange.UNCHANGED;
    m_eType = eType;
    return EChange.CHANGED;
  }

  @NonNull
  public String getName ()
  {
    return m_sName;
  }

  @NonNull
  public final EChange setName (@NonNull final String sName)
  {
    ValueEnforcer.notNull (sName, "Name");

    if (sName.equals (m_sName))
      return EChange.UNCHANGED;
    m_sName = sName;
    return EChange.CHANGED;
  }

  @Nullable
  public String getScheme ()
  {
    return m_sScheme;
  }

  @NonNull
  public EChange setScheme (@Nullable final String sScheme)
  {
    if (EqualsHelper.equals (sScheme, m_sScheme))
      return EChange.UNCHANGED;
    m_sScheme = sScheme;
    return EChange.CHANGED;
  }

  public boolean isLanguageIndependent ()
  {
    return m_aContents.containsKey (LocaleHelper.LOCALE_INDEPENDENT);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedSet <Locale> getAllLocales ()
  {
    return m_aContents.copyOfKeySet ();
  }

  @Nullable
  public String getContentLanguageIndependent ()
  {
    return m_aContents.get (LocaleHelper.LOCALE_INDEPENDENT);
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <Locale, String> getContentMap ()
  {
    return m_aContents.getClone ();
  }

  @NonNull
  @ReturnsMutableCopy
  public Iterable <Map.Entry <Locale, String>> getContent ()
  {
    return m_aContents.entrySet ();
  }

  @NonNull
  public static Locale getRealContentLocale (@Nullable final Locale aContentLocale)
  {
    return aContentLocale == null ? LocaleHelper.LOCALE_INDEPENDENT : aContentLocale;
  }

  @NonNull
  public EChange setContent (@Nullable final Locale aContentLocale, @Nullable final String sContent)
  {
    final Locale aRealContentLocale = getRealContentLocale (aContentLocale);
    final String sOldContent = m_aContents.get (aRealContentLocale);
    if (EqualsHelper.equals (sOldContent, sContent))
      return EChange.UNCHANGED;

    if (sContent == null)
      m_aContents.remove (aRealContentLocale);
    else
      m_aContents.put (aRealContentLocale, sContent);
    return EChange.CHANGED;
  }

  @NonNull
  public EChange removeContent (@Nullable final Locale aContentLocale)
  {
    final Locale aRealContentLocale = getRealContentLocale (aContentLocale);
    // Manually check for key, as the value can be null
    if (!m_aContents.containsKey (aRealContentLocale))
      return EChange.UNCHANGED;

    m_aContents.remove (aRealContentLocale);
    return EChange.CHANGED;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <IMetaElementValue> getAsMetaElementValueList ()
  {
    final ICommonsList <IMetaElementValue> ret = new CommonsArrayList <> (m_aContents.size ());
    for (final Map.Entry <Locale, String> aEntry : m_aContents.entrySet ())
      ret.add (new MetaElementValue (m_eType, m_sName, aEntry.getKey (), aEntry.getValue ()));
    return ret;
  }

  @NonNull
  @Nonempty
  @OverrideOnDemand
  protected String getNamespaceURI (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    return aConversionSettings.getHTMLNamespaceURI ();
  }

  @Nullable
  public IMicroNode convertToNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    if (m_aContents.isEmpty ())
    {
      LOGGER.info ("Meta element '" + m_sName + "' has no content!");
      return null;
    }

    final String sNamespaceURI = getNamespaceURI (aConversionSettings);
    final boolean bAtLeastHTML5 = aConversionSettings.getHTMLVersion ().isAtLeastHTML5 ();

    final IMicroContainer ret = new MicroContainer ();
    for (final Map.Entry <Locale, String> aMetaEntry : m_aContents.entrySet ())
    {
      final IMicroElement aMeta = ret.addElementNS (sNamespaceURI, EHTMLElement.META.getElementName ());
      final String sValue = aMetaEntry.getValue ();

      switch (m_eType)
      {
        case DOCUMENT_LEVEL:
        case PRAGMA_DIRECTIVE:
          aMeta.setAttribute (m_eType.getAttrName (), m_sName);
          aMeta.setAttribute (CHTMLAttributes.CONTENT, sValue);
          break;
        case CHARSET:
          // Name does not matter!
          aMeta.setAttribute (m_eType.getAttrName (), sValue);
          break;
        default:
          throw new IllegalStateException ("Unsupported meta element type!");
      }

      if (m_eType.isMultilingual ())
      {
        final Locale aContentLocale = aMetaEntry.getKey ();
        if (aContentLocale != null && !LocaleHelper.isSpecialLocale (aContentLocale))
        {
          final String sLang = aContentLocale.toString ();
          aMeta.setAttribute (new MicroQName (XMLConstants.XML_NS_URI, CHTMLAttributes.LANG.getName ()), sLang);
          if (bAtLeastHTML5)
          {
            // When the attribute xml:lang in no namespace is specified, the
            // element must also have the attribute lang present with the same
            // value
            aMeta.setAttribute (CHTMLAttributes.LANG, sLang);
          }
        }
      }

      if (bAtLeastHTML5)
      {
        // Special HTML 5 handling
        // No scheme attr in HTML5
      }
      else
      {
        if (StringHelper.isNotEmpty (m_sScheme))
          aMeta.setAttribute (CHTMLAttributes.SCHEME, m_sScheme);
      }
    }
    return ret;
  }

  @NonNull
  @ReturnsMutableCopy
  public MetaElement getClone ()
  {
    return new MetaElement (this);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final MetaElement rhs = (MetaElement) o;
    return m_eType.equals (rhs.m_eType) &&
           m_sName.equals (rhs.m_sName) &&
           EqualsHelper.equals (m_sScheme, rhs.m_sScheme) &&
           m_aContents.equals (rhs.m_aContents);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eType)
                                       .append (m_sName)
                                       .append (m_sScheme)
                                       .append (m_aContents)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("type", m_eType)
                                       .append ("name", m_sName)
                                       .appendIfNotNull ("scheme", m_sScheme)
                                       .append ("contents", m_aContents)
                                       .getToString ();
  }

  @NonNull
  public static MetaElement createMeta (@NonNull final String sName, @NonNull final String sValue)
  {
    return new MetaElement (EMetaElementType.DOCUMENT_LEVEL, sName, null, (Locale) null, sValue);
  }

  @NonNull
  public static MetaElement createMetaHttpEquiv (@NonNull final String sName, @NonNull final String sValue)
  {
    return new MetaElement (EMetaElementType.PRAGMA_DIRECTIVE, sName, null, (Locale) null, sValue);
  }

  @NonNull
  public static MetaElement createMetaCharset (@NonNull final Charset aCharset)
  {
    return new MetaElement (EMetaElementType.CHARSET, "", null, (Locale) null, aCharset.name ());
  }
}
