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
package com.helger.webbasics.favorites;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.IMicroTypeConverter;
import com.helger.commons.microdom.impl.MicroElement;

/**
 * Convert {@link Favorite} objects to {@link IMicroElement} and vice versa.
 *
 * @author Philip Helger
 */
@Immutable
public class FavoriteMicroTypeConverter implements IMicroTypeConverter
{
  private static final String ATTR_ID = "id";
  private static final String ATTR_USER_ID = "userid";
  private static final String ATTR_APPLICATION_ID = "applicationid";
  private static final String ATTR_MENU_ITEM_ID = "menuitemid";
  private static final String ATTR_DISPLAY_NAME = "displayname";
  private static final String ELEMENT_ADDITIONAL_PARAM = "additionalparam";
  private static final String ATTR_NAME = "name";
  private static final String ATTR_VALUE = "value";

  @Nonnull
  public IMicroElement convertToMicroElement (@Nonnull final Object aObject,
                                              @Nullable final String sNamespaceURI,
                                              @Nonnull final String sTagName)
  {
    final IFavorite aFavourite = (IFavorite) aObject;
    final IMicroElement eFavourite = new MicroElement (sNamespaceURI, sTagName);
    eFavourite.setAttribute (ATTR_ID, aFavourite.getID ());
    eFavourite.setAttribute (ATTR_USER_ID, aFavourite.getUserID ());
    eFavourite.setAttribute (ATTR_APPLICATION_ID, aFavourite.getApplicationID ());
    eFavourite.setAttribute (ATTR_MENU_ITEM_ID, aFavourite.getMenuItemID ());
    eFavourite.setAttribute (ATTR_DISPLAY_NAME, aFavourite.getDisplayName ());

    for (final Map.Entry <String, String> aEntry : CollectionHelper.getSortedByKey (aFavourite.getAdditionalParams ())
                                                                   .entrySet ())
    {
      final IMicroElement eParam = eFavourite.appendElement (ELEMENT_ADDITIONAL_PARAM);
      eParam.setAttribute (ATTR_NAME, aEntry.getKey ());
      eParam.setAttribute (ATTR_VALUE, aEntry.getValue ());
    }

    return eFavourite;
  }

  @Nonnull
  public Favorite convertToNative (@Nonnull final IMicroElement eFavourite)
  {
    final String sID = eFavourite.getAttributeValue (ATTR_ID);
    final String sUserID = eFavourite.getAttributeValue (ATTR_USER_ID);
    final String sApplicationID = eFavourite.getAttributeValue (ATTR_APPLICATION_ID);
    final String sMenuItemID = eFavourite.getAttributeValue (ATTR_MENU_ITEM_ID);
    final String sDisplayName = eFavourite.getAttributeValue (ATTR_DISPLAY_NAME);

    final Map <String, String> aAdditionalParams = new LinkedHashMap <String, String> ();
    for (final IMicroElement eCustom : eFavourite.getAllChildElements (ELEMENT_ADDITIONAL_PARAM))
      aAdditionalParams.put (eCustom.getAttributeValue (ATTR_NAME), eCustom.getAttributeValue (ATTR_VALUE));

    return new Favorite (sID, sUserID, sApplicationID, sMenuItemID, sDisplayName, aAdditionalParams);
  }
}
