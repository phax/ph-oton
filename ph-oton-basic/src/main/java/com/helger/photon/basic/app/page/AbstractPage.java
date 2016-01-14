/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.page;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsImmutableObject;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.attr.IAttributeContainer;
import com.helger.commons.collection.attr.IMutableAttributeContainerAny;
import com.helger.commons.collection.attr.MapBasedAttributeContainerAny;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.ReadOnlyMultilingualText;

/**
 * Abstract base implementation for {@link IPage}.
 *
 * @author Philip Helger
 */
public abstract class AbstractPage implements IPage
{
  private final String m_sID;
  private IMultilingualText m_aName;
  private IMultilingualText m_aDescription;
  private final MapBasedAttributeContainerAny <String> m_aAttrs = new MapBasedAttributeContainerAny <> ();

  @Nonnull
  private static ReadOnlyMultilingualText _getAsMLT (@Nonnull final String sText)
  {
    return new ReadOnlyMultilingualText (CGlobal.LOCALE_INDEPENDENT, sText);
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   */
  public AbstractPage (@Nonnull @Nonempty final String sID)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param sName
   *        The constant (non-translatable) name of the page. May not be
   *        <code>null</code>.
   */
  public AbstractPage (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    this (sID, _getAsMLT (sName));
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param sName
   *        The constant (non-translatable) name of the page. May not be
   *        <code>null</code>.
   * @param sDescription
   *        The constant (non-translatable) description of the page. May be
   *        <code>null</code>.
   */
  public AbstractPage (@Nonnull @Nonempty final String sID,
                       @Nonnull final String sName,
                       @Nullable final String sDescription)
  {
    this (sID, _getAsMLT (sName), sDescription == null ? null : _getAsMLT (sDescription));
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param aName
   *        The name of the page. May not be <code>null</code>.
   */
  public AbstractPage (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    this (sID, aName, null);
  }

  /**
   * Constructor
   *
   * @param sID
   *        The unique page ID. May not be <code>null</code>.
   * @param aName
   *        The name of the page. May not be <code>null</code>.
   * @param aDescription
   *        Optional description of the page. May be <code>null</code>.
   */
  public AbstractPage (@Nonnull @Nonempty final String sID,
                       @Nonnull final IMultilingualText aName,
                       @Nullable final IMultilingualText aDescription)
  {
    this (sID);
    setName (aName);
    setDescription (aDescription);
  }

  /*
   * Get the unique page ID
   */
  @Nonnull
  @Nonempty
  public final String getID ()
  {
    return m_sID;
  }

  /**
   * Set the name of the page.
   *
   * @param aName
   *        The multilingual name of the page. May not be <code>null</code>.
   */
  public final void setName (@Nonnull final IMultilingualText aName)
  {
    m_aName = ValueEnforcer.notNull (aName, "Name");
  }

  /**
   * @return The complete name of the page in all available locales. Never
   *         <code>null</code>.
   */
  @Nonnull
  public final IMultilingualText getName ()
  {
    return m_aName;
  }

  /*
   * Get the name of the page in the passed locale.
   */
  @Nullable
  public final String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aName.getText (aContentLocale);
  }

  /**
   * Set the description of the page.
   *
   * @param aDescription
   *        The multilingual description of the page. May be <code>null</code>.
   */
  public final void setDescription (@Nullable final IMultilingualText aDescription)
  {
    m_aDescription = aDescription;
  }

  /**
   * @return The complete description of the page in all available locales. May
   *         be <code>null</code>.
   */
  @Nullable
  public final IMultilingualText getDescription ()
  {
    return m_aDescription;
  }

  @Nullable
  public final String getDescription (@Nonnull final Locale aContentLocale)
  {
    return m_aDescription == null ? null : m_aDescription.getText (aContentLocale);
  }

  @Nonnull
  @ReturnsImmutableObject
  public IAttributeContainer <String, Object> getAttributes ()
  {
    return m_aAttrs;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public IMutableAttributeContainerAny <String> getMutableAttributes ()
  {
    return m_aAttrs;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, Object> getAllAttributes ()
  {
    return m_aAttrs.getAllAttributes ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .append ("name", m_aName)
                                       .appendIfNotNull ("description", m_aDescription)
                                       .toString ();
  }
}
