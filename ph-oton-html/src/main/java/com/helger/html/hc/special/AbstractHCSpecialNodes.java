/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html.hc.special;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.collection.multimap.MultiLinkedHashMapLinkedHashSetBased;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.traits.IGenericImplTrait;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.js.IHasJSCode;
import com.helger.html.resource.css.ICSSCodeProvider;

/**
 * Abstract implementation of {@link IHCSpecialNodes}.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractHCSpecialNodes <IMPLTYPE extends AbstractHCSpecialNodes <IMPLTYPE>> implements
                                             IHCSpecialNodes,
                                             IGenericImplTrait <IMPLTYPE>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractHCSpecialNodes.class);

  private final MultiLinkedHashMapLinkedHashSetBased <ICSSMediaList, String> m_aExternalCSSs = new MultiLinkedHashMapLinkedHashSetBased <> ();
  private final InlineCSSList m_aInlineCSSBeforeExternal = new InlineCSSList ();
  private final InlineCSSList m_aInlineCSSAfterExternal = new InlineCSSList ();

  private final ICommonsOrderedSet <String> m_aExternalJSs = new CommonsLinkedHashSet <> ();
  private final CollectingJSCodeProvider m_aInlineJSBeforeExternal = new CollectingJSCodeProvider ();
  private final CollectingJSCodeProvider m_aInlineJSAfterExternal = new CollectingJSCodeProvider ();

  public AbstractHCSpecialNodes ()
  {}

  /**
   * Remove all contained content.
   */
  public void clear ()
  {
    m_aExternalCSSs.clear ();
    m_aInlineCSSBeforeExternal.clear ();
    m_aInlineCSSAfterExternal.clear ();
    m_aExternalJSs.clear ();
    m_aInlineJSBeforeExternal.reset ();
    m_aInlineJSAfterExternal.reset ();
  }

  public boolean isEmpty ()
  {
    return m_aExternalCSSs.isEmpty () &&
           m_aInlineCSSBeforeExternal.isEmpty () &&
           m_aInlineCSSAfterExternal.isEmpty () &&
           m_aExternalJSs.isEmpty () &&
           m_aInlineJSBeforeExternal.isEmpty () &&
           m_aInlineJSAfterExternal.isEmpty ();
  }

  @Nonnull
  protected ICSSMediaList getSafeCSSMediaList (@Nullable final ICSSMediaList aMediaList)
  {
    if (aMediaList != null && !aMediaList.hasNoMediaOrAll ())
    {
      // A special media list is present that is neither null nor empty nor does
      // it contain the "all" keyword
      return aMediaList;
    }

    // Create a new one without any media
    return new CSSMediaList ();
  }

  @Nonnull
  public IMPLTYPE addExternalCSS (@Nullable final ICSSMediaList aMediaList, @Nonnull @Nonempty final String sCSSURI)
  {
    ValueEnforcer.notEmpty (sCSSURI, "CSSURI");

    final ICSSMediaList aRealMediaList = getSafeCSSMediaList (aMediaList);
    if (m_aExternalCSSs.putSingle (aRealMediaList, sCSSURI).isUnchanged ())
      LOGGER.warn ("Duplicate CSS URI '" + sCSSURI + "' with media list '" + aRealMediaList + "' ignored");
    return thisAsT ();
  }

  public boolean hasExternalCSSs ()
  {
    return !m_aExternalCSSs.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsOrderedMap <ICSSMediaList, ICommonsList <String>> getAllExternalCSSs ()
  {
    final ICommonsOrderedMap <ICSSMediaList, ICommonsList <String>> ret = new CommonsLinkedHashMap <> ();
    for (final Map.Entry <ICSSMediaList, ICommonsOrderedSet <String>> aEntry : m_aExternalCSSs.entrySet ())
      ret.put (aEntry.getKey (), aEntry.getValue ().getCopyAsList ());
    return ret;
  }

  @Nonnull
  public IMPLTYPE addInlineCSSBeforeExternal (@Nullable final ICSSMediaList aMediaList,
                                              @Nonnull final CharSequence aInlineCSS)
  {
    ValueEnforcer.notNull (aInlineCSS, "InlineCSS");

    m_aInlineCSSBeforeExternal.addInlineCSS (aMediaList, aInlineCSS);
    return thisAsT ();
  }

  public boolean hasInlineCSSBeforeExternal ()
  {
    return m_aInlineCSSBeforeExternal.isNotEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <ICSSCodeProvider> getAllInlineCSSBeforeExternal ()
  {
    return m_aInlineCSSBeforeExternal.getAll ();
  }

  @Nonnull
  public IMPLTYPE addInlineCSSAfterExternal (@Nullable final ICSSMediaList aMediaList,
                                             @Nonnull final CharSequence aInlineCSS)
  {
    ValueEnforcer.notNull (aInlineCSS, "InlineCSS");

    m_aInlineCSSAfterExternal.addInlineCSS (aMediaList, aInlineCSS);
    return thisAsT ();
  }

  public boolean hasInlineCSSAfterExternal ()
  {
    return m_aInlineCSSAfterExternal.isNotEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <ICSSCodeProvider> getAllInlineCSSAfterExternal ()
  {
    return m_aInlineCSSAfterExternal.getAll ();
  }

  @Nonnull
  public IMPLTYPE addExternalJS (@Nonnull @Nonempty final String sJSURI)
  {
    ValueEnforcer.notEmpty (sJSURI, "JSURI");

    if (!m_aExternalJSs.add (sJSURI))
      LOGGER.warn ("Duplicate JS URI '" + sJSURI + "' ignored");
    return thisAsT ();
  }

  public boolean hasExternalJSs ()
  {
    return m_aExternalJSs.isNotEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllExternalJSs ()
  {
    return m_aExternalJSs.getCopyAsList ();
  }

  @Nonnull
  public IMPLTYPE addInlineJSBeforeExternal (@Nonnull final IHasJSCode aInlineJS)
  {
    ValueEnforcer.notNull (aInlineJS, "InlineJS");

    m_aInlineJSBeforeExternal.appendFlattened (aInlineJS);
    return thisAsT ();
  }

  public boolean hasInlineJSBeforeExternal ()
  {
    return m_aInlineJSBeforeExternal.isNotEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public CollectingJSCodeProvider getInlineJSBeforeExternal ()
  {
    return m_aInlineJSBeforeExternal.getClone ();
  }

  @Nonnull
  public IMPLTYPE addInlineJSAfterExternal (@Nonnull final IHasJSCode aInlineJS)
  {
    ValueEnforcer.notNull (aInlineJS, "InlineJS");

    m_aInlineJSAfterExternal.appendFlattened (aInlineJS);
    return thisAsT ();
  }

  public boolean hasInlineJSAfterExternal ()
  {
    return m_aInlineJSAfterExternal.isNotEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public CollectingJSCodeProvider getInlineJSAfterExternal ()
  {
    return m_aInlineJSAfterExternal.getClone ();
  }

  @Nonnull
  public IMPLTYPE addAll (@Nonnull final IHCSpecialNodes aSpecialNodes)
  {
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");

    // CSS
    for (final Map.Entry <ICSSMediaList, ICommonsList <String>> aEntry : aSpecialNodes.getAllExternalCSSs ()
                                                                                      .entrySet ())
      for (final String sCSSFile : aEntry.getValue ())
        addExternalCSS (aEntry.getKey (), sCSSFile);
    for (final ICSSCodeProvider aEntry : aSpecialNodes.getAllInlineCSSBeforeExternal ())
      addInlineCSSBeforeExternal (aEntry.getMediaList (), aEntry.getCSSCode ());
    for (final ICSSCodeProvider aEntry : aSpecialNodes.getAllInlineCSSAfterExternal ())
      addInlineCSSAfterExternal (aEntry.getMediaList (), aEntry.getCSSCode ());

    // JS
    for (final String sJSFile : aSpecialNodes.getAllExternalJSs ())
      addExternalJS (sJSFile);
    addInlineJSBeforeExternal (aSpecialNodes.getInlineJSBeforeExternal ());
    addInlineJSAfterExternal (aSpecialNodes.getInlineJSAfterExternal ());

    return thisAsT ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractHCSpecialNodes <?> rhs = (AbstractHCSpecialNodes <?>) o;
    return m_aExternalCSSs.equals (rhs.m_aExternalCSSs) &&
           EqualsHelper.equals (m_aInlineCSSBeforeExternal, rhs.m_aInlineCSSBeforeExternal) &&
           EqualsHelper.equals (m_aInlineCSSAfterExternal, rhs.m_aInlineCSSAfterExternal) &&
           m_aExternalJSs.equals (rhs.m_aExternalJSs) &&
           m_aInlineJSBeforeExternal.equals (rhs.m_aInlineJSBeforeExternal) &&
           m_aInlineJSAfterExternal.equals (rhs.m_aInlineJSAfterExternal);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aExternalCSSs)
                                       .append (m_aInlineCSSBeforeExternal)
                                       .append (m_aInlineCSSAfterExternal)
                                       .append (m_aExternalJSs)
                                       .append (m_aInlineJSBeforeExternal)
                                       .append (m_aInlineJSAfterExternal)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIf ("externalCSS", m_aExternalCSSs, CollectionHelper::isNotEmpty)
                                       .append ("inlineCSSBeforeExternal", m_aInlineCSSBeforeExternal)
                                       .append ("inlineCSSAfterExternal", m_aInlineCSSAfterExternal)
                                       .appendIf ("externalJS", m_aExternalJSs, CollectionHelper::isNotEmpty)
                                       .append ("inlineJSBeforeExternal", m_aInlineJSBeforeExternal)
                                       .append ("inlineJSAfterExternal", m_aInlineJSBeforeExternal)
                                       .getToString ();
  }
}
