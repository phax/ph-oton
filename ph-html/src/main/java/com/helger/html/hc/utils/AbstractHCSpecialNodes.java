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
package com.helger.html.hc.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.provider.CollectingJSCodeProvider;

/**
 * Abstract implementation of {@link IHCSpecialNodes}.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractHCSpecialNodes <IMPLTYPE extends AbstractHCSpecialNodes <IMPLTYPE>> implements IHCSpecialNodes
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractHCSpecialNodes.class);

  private final Set <String> m_aExternalCSSs = new LinkedHashSet <String> ();
  private final StringBuilder m_aInlineCSS = new StringBuilder ();
  private final Set <String> m_aExternalJSs = new LinkedHashSet <String> ();
  private final CollectingJSCodeProvider m_aInlineJS = new CollectingJSCodeProvider ();

  public AbstractHCSpecialNodes ()
  {}

  /**
   * Remove all contained content.
   */
  public void clear ()
  {
    m_aExternalCSSs.clear ();
    m_aInlineCSS.setLength (0);
    m_aExternalJSs.clear ();
    m_aInlineJS.reset ();
  }

  @SuppressWarnings ("unchecked")
  @Nonnull
  protected final IMPLTYPE thisAsT ()
  {
    return (IMPLTYPE) this;
  }

  @Nonnull
  public IMPLTYPE addExternalCSS (@Nonnull @Nonempty final String sCSSFile)
  {
    ValueEnforcer.notEmpty (sCSSFile, "CSSFile");

    if (!m_aExternalCSSs.add (sCSSFile))
      s_aLogger.warn ("Duplicate CSS file '" + sCSSFile + "' ignored");
    return thisAsT ();
  }

  public boolean hasExternalCSSs ()
  {
    return !m_aExternalCSSs.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllExternalCSSs ()
  {
    return CollectionHelper.newList (m_aExternalCSSs);
  }

  @Nonnull
  public IMPLTYPE addInlineCSS (@Nonnull final CharSequence aInlineCSS)
  {
    ValueEnforcer.notNull (aInlineCSS, "InlineCSS");

    m_aInlineCSS.append (aInlineCSS);
    return thisAsT ();
  }

  public boolean hasInlineCSS ()
  {
    return m_aInlineCSS.length () > 0;
  }

  @Nonnull
  @ReturnsMutableCopy
  public StringBuilder getInlineCSS ()
  {
    return new StringBuilder (m_aInlineCSS);
  }

  @Nonnull
  public IMPLTYPE addExternalJS (@Nonnull @Nonempty final String sJSFile)
  {
    ValueEnforcer.notEmpty (sJSFile, "JSFile");

    if (!m_aExternalJSs.add (sJSFile))
      s_aLogger.warn ("Duplicate JS file '" + sJSFile + "' ignored");
    return thisAsT ();
  }

  public boolean hasExternalJSs ()
  {
    return !m_aExternalJSs.isEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <String> getAllExternalJSs ()
  {
    return CollectionHelper.newList (m_aExternalJSs);
  }

  @Nonnull
  public IMPLTYPE addInlineJS (@Nonnull final IJSCodeProvider aInlineJS)
  {
    ValueEnforcer.notNull (aInlineJS, "InlineJS");

    m_aInlineJS.appendFlattened (aInlineJS);
    return thisAsT ();
  }

  public boolean hasInlineJS ()
  {
    return m_aInlineJS.isNotEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public CollectingJSCodeProvider getInlineJS ()
  {
    return m_aInlineJS.getClone ();
  }

  @Nonnull
  public IMPLTYPE addAll (@Nonnull final IHCSpecialNodes aSpecialNodes)
  {
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");

    // CSS
    for (final String sCSSFile : aSpecialNodes.getAllExternalCSSs ())
      addExternalCSS (sCSSFile);
    addInlineCSS (aSpecialNodes.getInlineCSS ());

    // JS
    for (final String sJSFile : aSpecialNodes.getAllExternalJSs ())
      addExternalJS (sJSFile);
    addInlineJS (aSpecialNodes.getInlineJS ());

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
           EqualsUtils.equals (m_aInlineCSS, rhs.m_aInlineCSS) &&
           m_aExternalJSs.equals (rhs.m_aExternalJSs) &&
           m_aInlineJS.equals (rhs.m_aInlineJS);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aExternalCSSs)
                                       .append (m_aInlineCSS)
                                       .append (m_aExternalJSs)
                                       .append (m_aInlineJS)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotEmpty ("cssFiles", m_aExternalCSSs)
                                       .append ("inlineCSS", m_aInlineCSS)
                                       .appendIfNotEmpty ("jsFiles", m_aExternalJSs)
                                       .append ("inlineJS", m_aInlineJS)
                                       .toString ();
  }
}
