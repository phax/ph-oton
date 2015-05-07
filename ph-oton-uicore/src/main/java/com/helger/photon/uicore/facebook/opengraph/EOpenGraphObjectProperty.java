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
package com.helger.photon.uicore.facebook.opengraph;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;
import com.helger.photon.uicore.facebook.CFacebook;

/**
 * The various open graph object properties taken from <a href=
 * "https://developers.facebook.com/docs/opengraph/creating-object-types/#properties"
 * >developers.facebook.com</a>
 * 
 * @author Boris Gregorcic
 */
public enum EOpenGraphObjectProperty implements IHasID <String>
{
  /**
   * The URL of the object, which acts as each object's unique identifier,
   * otherwise known as the canonical URL - each URL can only contain a single
   * object - and in most cases this is the same URL as the page on which the
   * property tags are placed. It should not include any session variables, user
   * identifying parameters, or counters. If you use this improperly, likes and
   * shares will not be aggregated for this URL and will be spread across all of
   * the variations of the URL.
   */
  URL ("url"),
  /** The title, headline or name of the object. */
  TITLE ("title"),
  /**
   * The URL of an image which is used in stories published about this object.
   * We suggest that you give us an image of at least 200x200 pixels. However,
   * bigger is better, so if you have a 1500x1500 image that you can use, please
   * give it to us. We downsample and crop it for for people using
   * smaller-resolution devices but will use it on a larger device. The larger
   * this image is, the better engagement stories featuring it will get. (Note:
   * image sizes must be no more than 5MB in size.) Note that you can include
   * more than that one og:image in your object if you have more than one
   * resolution available.
   */
  IMAGE ("image"),
  /**
   * The object type for this object - to see what the type is for your og:type,
   * go to the Open Graph section of the App Dashboard, click on Types, choose
   * your object type and check the Open Graph Type field under the Advanced
   * section. Note that once an object's type is set and has been used in a
   * story that its type can not be changed. For a list of built-in types,
   * please see our list of common open graph object types.
   */
  TYPE ("type"),
  /**
   * The language locale that the object properties use. This defaults to en_US
   * if not specified.
   */
  LOCALE ("locale"),
  /**
   * The URL of a video that complements this object. You may specify more than
   * one og:video. If you specify more than one og:video, then og:video:type is
   * required for each video Please see the Video type reference for information
   * on using og:video as a complex type and how to include fallbacks. You must
   * include a valid og:image for your video to be displayed in the news feed.
   */
  VIDEO ("video"),
  /**
   * The URL of audio that to accompany this object. See the Audio type
   * reference for more information about this property.
   */
  AUDIO ("audio"),
  /**
   * A short description or summary of the object.
   */
  DESCRIPTION ("description"),
  /**
   * Human-readable name of site hosting the object.
   */
  SITE_NAME ("site_name"),
  /**
   * The word that appears before the object's title in a story or sentence
   * (such as "an Omelette"). The value of this property should be a string that
   * is a member of the Enum {a, an, the, "", auto}. When 'auto' is selected,
   * Facebook will choose between 'a' or 'an'. Default is blank.
   */
  DETERMINER ("determiner"),
  /**
   * The restrictions (country, age, or content-based) that are applied to this
   * object. Read more about <a href=
   * "https://developers.facebook.com/docs/technical-guides/opengraph/user-restrictions/#object-level"
   * >object-level restrictions</a>.
   */
  RESTRICTIONS ("restrictions"),
  /**
   * When the object was last updated.
   */
  UPDATED_TIME ("updated_time"),
  /**
   * Used to supply an additional link that shows related content to the object.
   */
  SEE_ALSO ("see_also");

  private final String m_sID;

  private EOpenGraphObjectProperty (@Nonnull @Nonempty final String sID)
  {
    m_sID = CFacebook.OPENGRAPH_PREFIX + ':' + sID;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public static EOpenGraphObjectProperty getFromID (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EOpenGraphObjectProperty.class, sID);
  }
}
