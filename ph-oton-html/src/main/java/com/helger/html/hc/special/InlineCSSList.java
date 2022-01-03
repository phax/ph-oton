/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.resource.css.ConstantCSSCodeProvider;
import com.helger.html.resource.css.ICSSCodeProvider;

/**
 * This is a very special list used only to group inline CSS code for correct
 * merging. It maintains the original order and combines only those with the
 * same media lists.
 *
 * @author Philip Helger
 */
public class InlineCSSList
{
  @Nonnull
  public static ICSSMediaList getSafeCSSMediaList (@Nullable final ICSSMediaList aMediaList)
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

  private static final class Key implements Serializable
  {
    private final ICSSMediaList m_aMediaList;

    public Key (@Nullable final ICSSMediaList aMediaList)
    {
      m_aMediaList = getSafeCSSMediaList (aMediaList);
    }

    @Nonnull
    public ICSSMediaList getMediaList ()
    {
      return m_aMediaList;
    }

    @Override
    public boolean equals (final Object o)
    {
      if (o == this)
        return true;
      if (o == null || !getClass ().equals (o.getClass ()))
        return false;
      final Key rhs = (Key) o;
      return m_aMediaList.equals (rhs.m_aMediaList);
    }

    @Override
    public int hashCode ()
    {
      return new HashCodeGenerator (this).append (m_aMediaList).getHashCode ();
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (null).append ("MediaList", m_aMediaList).getToString ();
    }
  }

  private static final class Item implements Serializable
  {
    private final Key m_aKey;
    private final StringBuilder m_aCSS = new StringBuilder ();

    public Item (@Nonnull final Key aKey)
    {
      m_aKey = ValueEnforcer.notNull (aKey, "Key");
    }

    void appendCSS (@Nonnull final CharSequence aInlineCSS)
    {
      m_aCSS.append (aInlineCSS);
    }

    @Nonnull
    Key getKey ()
    {
      return m_aKey;
    }

    @Nonnull
    public ICSSMediaList getMediaList ()
    {
      return m_aKey.getMediaList ();
    }

    @Nonnull
    public String getCSS ()
    {
      return m_aCSS.toString ();
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (this).append ("Key", m_aKey).append ("CSS", m_aCSS).getToString ();
    }
  }

  private final ICommonsList <Item> m_aItems = new CommonsArrayList <> ();

  public InlineCSSList ()
  {}

  public void addInlineCSS (@Nullable final ICSSMediaList aMediaList, @Nonnull final CharSequence aInlineCSS)
  {
    final Key aKey = new Key (aMediaList);
    final Item aLastItem = m_aItems.getLast ();
    final Key aLastKey = aLastItem == null ? null : aLastItem.getKey ();
    Item aItemToUse;
    if (aLastKey != null && aLastKey.equals (aKey))
    {
      aItemToUse = aLastItem;
    }
    else
    {
      aItemToUse = new Item (aKey);
      m_aItems.add (aItemToUse);
    }
    aItemToUse.appendCSS (aInlineCSS);
  }

  void clear ()
  {
    m_aItems.clear ();
  }

  public boolean isEmpty ()
  {
    return m_aItems.isEmpty ();
  }

  public boolean isNotEmpty ()
  {
    return m_aItems.isNotEmpty ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <ICSSCodeProvider> getAll ()
  {
    return m_aItems.getAllMapped (aItem -> new ConstantCSSCodeProvider (aItem.getCSS (), null, aItem.getMediaList (), true));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Items", m_aItems).getToString ();
  }
}
