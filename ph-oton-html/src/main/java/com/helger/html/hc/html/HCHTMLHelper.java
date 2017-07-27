/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EFinish;
import com.helger.commons.wrapper.Wrapper;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCNode;

@Immutable
public final class HCHTMLHelper
{
  private HCHTMLHelper ()
  {}

  /**
   * Helper method to enforce correct element nesting. See
   * http://www.w3.org/TR/xhtml1#prohibitions
   *
   * @param aOwner
   *        Owner where to start searching
   * @param aElements
   *        The tag names to search. May not be <code>null</code>.
   * @return All elements with the passed element name on any level. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IHCElement <?>> recursiveGetAllChildrenWithTagName (@Nonnull final IHCNode aOwner,
                                                                                  @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    ValueEnforcer.notEmpty (aElements, "Elements");

    final ICommonsList <IHCElement <?>> ret = new CommonsArrayList <> ();
    HCHelper.iterateChildrenNoCopy (aOwner, (aParentNode, aChildNode) -> {
      if (aChildNode instanceof IHCElement <?>)
      {
        final IHCElement <?> aCurrentElement = (IHCElement <?>) aChildNode;
        final String sCurrentTagName = aCurrentElement.getTagName ();
        for (final EHTMLElement aElement : aElements)
          if (sCurrentTagName.equalsIgnoreCase (aElement.getElementName ()))
          {
            ret.add (aCurrentElement);
            break;
          }
      }
      return EFinish.UNFINISHED;
    });
    return ret;
  }

  /**
   * Helper method to enforce correct element nesting. See
   * http://www.w3.org/TR/xhtml1#prohibitions
   *
   * @param aOwner
   *        Owner where to start searching
   * @param aElements
   *        The tag names to search. May not be <code>null</code>.
   * @return The first element with a different than the passed tag name on any
   *         level, or <code>null</code> if no such element exists.
   */
  @Nullable
  public static IHCElement <?> recursiveGetFirstChildWithDifferentTagName (@Nonnull final IHCNode aOwner,
                                                                           @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    ValueEnforcer.notEmpty (aElements, "Elements");

    final Wrapper <IHCElement <?>> ret = new Wrapper <> ();
    HCHelper.iterateChildrenNoCopy (aOwner, (aParentNode, aChildNode) -> {
      if (aChildNode instanceof IHCElement <?>)
      {
        final IHCElement <?> aCurrentElement = (IHCElement <?>) aChildNode;
        final String sCurrentTagName = aCurrentElement.getTagName ();
        boolean bFound = false;
        for (final EHTMLElement aElement : aElements)
          if (sCurrentTagName.equalsIgnoreCase (aElement.getElementName ()))
          {
            bFound = true;
            break;
          }
        if (!bFound)
        {
          ret.set (aCurrentElement);
          return EFinish.FINISHED;
        }
      }
      return EFinish.UNFINISHED;
    });
    return ret.get ();
  }

  public static boolean recursiveContainsChildWithDifferentTagName (@Nonnull final IHCNode aOwner,
                                                                    @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    return recursiveGetFirstChildWithDifferentTagName (aOwner, aElements) != null;
  }

  /**
   * Helper method to enforce correct element nesting. See
   * http://www.w3.org/TR/xhtml1#prohibitions
   *
   * @param aOwner
   *        Owner to start searching
   * @param aElements
   *        The tag names to search. May not be <code>null</code>.
   * @return The first element with the passed tag name on any level, or
   *         <code>null</code> if no such element exists.
   */
  @Nullable
  public static IHCElement <?> recursiveGetFirstChildWithTagName (@Nonnull final IHCNode aOwner,
                                                                  @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    ValueEnforcer.notNull (aOwner, "Owner");
    ValueEnforcer.notEmpty (aElements, "Elements");

    final Wrapper <IHCElement <?>> ret = new Wrapper <> ();
    HCHelper.iterateChildrenNoCopy (aOwner, (aParentNode, aChildNode) -> {
      if (aChildNode instanceof IHCElement <?>)
      {
        final IHCElement <?> aCurrentElement = (IHCElement <?>) aChildNode;
        final String sCurrentTagName = aCurrentElement.getTagName ();
        for (final EHTMLElement aElement : aElements)
          if (sCurrentTagName.equalsIgnoreCase (aElement.getElementName ()))
          {
            ret.set (aCurrentElement);
            return EFinish.FINISHED;
          }
      }
      return EFinish.UNFINISHED;
    });
    return ret.get ();
  }

  public static boolean recursiveContainsChildWithTagName (@Nonnull final IHCNode aOwner,
                                                           @Nonnull @Nonempty final EHTMLElement... aElements)
  {
    return recursiveGetFirstChildWithTagName (aOwner, aElements) != null;
  }
}
