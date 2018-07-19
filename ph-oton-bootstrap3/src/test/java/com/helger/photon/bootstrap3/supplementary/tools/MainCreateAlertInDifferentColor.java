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
package com.helger.photon.bootstrap3.supplementary.tools;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainCreateAlertInDifferentColor
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateAlertInDifferentColor.class);

  public static String createAlertCSSCode (@Nonnull final String sClassName,
                                           @Nonnull final String sLightColor,
                                           @Nonnull final String sDarkColor,
                                           @Nonnull final String sBorderColor)
  {
    return ".alert-" +
           sClassName +
           " {\n" +
           "  background-image: -webkit-linear-gradient(top, #" +
           sLightColor +
           " 0%, #" +
           sDarkColor +
           " 100%);\n" +
           "  background-image:      -o-linear-gradient(top, #" +
           sLightColor +
           " 0%, #" +
           sDarkColor +
           " 100%);\n" +
           "  background-image: -webkit-gradient(linear, left top, left bottom, from(#" +
           sLightColor +
           "), to(#" +
           sDarkColor +
           "));\n" +
           "  background-image:         linear-gradient(to bottom, #" +
           sLightColor +
           " 0%, #" +
           sDarkColor +
           " 100%);\n" +
           "  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff" +
           sLightColor +
           "', endColorstr='#ff" +
           sDarkColor +
           "', GradientType=0);\n" +
           "  background-repeat: repeat-x;\n" +
           "  border-color: #" +
           sBorderColor +
           ";\n" +
           "}";
  }

  public static void main (final String [] args)
  {
    LOGGER.info (createAlertCSSCode ("grey", "eeeeee", "cccccc", "999999"));
  }
}
