/*
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
package com.helger.html.hc;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.DevelopersNote;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Special node interface for objects containing other objects
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The type of the contained child objects.
 */
public interface IHCNodeWithChildren <IMPLTYPE extends IHCNodeWithChildren <IMPLTYPE>> extends IHCHasChildrenMutable <IMPLTYPE, IHCNode>
{
  /**
   * Add a new text node, if the passed text is non-<code>null</code>.
   * 
   * @param sText
   *        Child text to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  default IMPLTYPE addChild (@Nullable final String sText)
  {
    // Empty text is OK!!!
    if (sText != null)
      addChild (new HCTextNode (sText));
    return thisAsT ();
  }

  /**
   * Remove all existing children and set only the provided text.
   * 
   * @param sText
   *        Child text to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  default IMPLTYPE setChild (@Nullable final String sText)
  {
    removeAllChildren ();
    return addChild (sText);
  }

  /**
   * @param nIndex
   *        The index to where the element should be inserted.
   * @param sText
   *        Child text to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  default IMPLTYPE addChildAt (@Nonnegative final int nIndex, @Nullable final String sText)
  {
    // Empty text is OK!!!
    if (sText != null)
      addChildAt (nIndex, new HCTextNode (sText));
    return thisAsT ();
  }

  /**
   * Use {@link #addChild(String)} instead
   *
   * @param sChild
   *        Child to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  @DevelopersNote ("Use addChild instead")
  @Deprecated
  default IMPLTYPE addChildren (@Nullable final String sChild)
  {
    return addChild (sChild);
  }

  /**
   * @param aChildren
   *        Children to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  default IMPLTYPE addChildren (@Nullable final String... aChildren)
  {
    if (aChildren != null)
      for (final String sChild : aChildren)
        addChild (sChild);
    return thisAsT ();
  }
}
