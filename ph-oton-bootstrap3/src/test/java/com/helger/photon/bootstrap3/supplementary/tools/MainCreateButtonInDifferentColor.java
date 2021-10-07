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
package com.helger.photon.bootstrap3.supplementary.tools;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainCreateButtonInDifferentColor
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainCreateButtonInDifferentColor.class);

  public static String createButtonCSSCode (@Nonnull final String sClassName,
                                            @Nonnull final String sLightColor,
                                            @Nonnull final String sDarkColor)
  {
    return "/* default */\n" +
           ".btn-" +
           sClassName +
           " {\n" +
           "  color: #fff;\n" +
           "  background-color: #" +
           sLightColor +
           ";\n" +
           "  border-color: #" +
           sDarkColor +
           ";\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           ":hover,\n" +
           ".btn-" +
           sClassName +
           ":focus,\n" +
           ".btn-" +
           sClassName +
           ":active,\n" +
           ".btn-" +
           sClassName +
           ".active,\n" +
           ".open > .dropdown-toggle.btn-" +
           sClassName +
           " {\n" +
           "  color: #fff;\n" +
           "  background-color: #" +
           sLightColor +
           ";\n" +
           "  border-color: #" +
           sDarkColor +
           ";\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           ":active,\n" +
           ".btn-" +
           sClassName +
           ".active,\n" +
           ".open > .dropdown-toggle.btn-" +
           sClassName +
           " {\n" +
           "  background-image: none;\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           ".disabled,\n" +
           ".btn-" +
           sClassName +
           "[disabled],\n" +
           "fieldset[disabled] .btn-" +
           sClassName +
           ",\n" +
           ".btn-" +
           sClassName +
           ".disabled:hover,\n" +
           ".btn-" +
           sClassName +
           "[disabled]:hover,\n" +
           "fieldset[disabled] .btn-" +
           sClassName +
           ":hover,\n" +
           ".btn-" +
           sClassName +
           ".disabled:focus,\n" +
           ".btn-" +
           sClassName +
           "[disabled]:focus,\n" +
           "fieldset[disabled] .btn-" +
           sClassName +
           ":focus,\n" +
           ".btn-" +
           sClassName +
           ".disabled:active,\n" +
           ".btn-" +
           sClassName +
           "[disabled]:active,\n" +
           "fieldset[disabled] .btn-" +
           sClassName +
           ":active,\n" +
           ".btn-" +
           sClassName +
           ".disabled.active,\n" +
           ".btn-" +
           sClassName +
           "[disabled].active,\n" +
           "fieldset[disabled] .btn-" +
           sClassName +
           ".active {\n" +
           "  background-color: #" +
           sLightColor +
           ";\n" +
           "  border-color: #" +
           sDarkColor +
           ";\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           " .badge {\n" +
           "  color: #" +
           sLightColor +
           ";\n" +
           "  background-color: #fff;\n" +
           "}\n" +
           "\n" +
           "/* theme */\n" +
           ".btn-" +
           sClassName +
           " {\n" +
           "  text-shadow: 0 -1px 0 rgba(0, 0, 0, .2);\n" +
           "  -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, .15), 0 1px 1px rgba(0, 0, 0, .075);\n" +
           "          box-shadow: inset 0 1px 0 rgba(255, 255, 255, .15), 0 1px 1px rgba(0, 0, 0, .075);\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           ":active,\n" +
           ".btn-" +
           sClassName +
           ".active {\n" +
           "  -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);\n" +
           "          box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);\n" +
           "}\n" +
           ".btn-" +
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
           "  filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);\n" +
           "  background-repeat: repeat-x;\n" +
           "  border-color: #" +
           sDarkColor +
           ";\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           ":hover,\n" +
           ".btn-" +
           sClassName +
           ":focus {\n" +
           "  background-color: #" +
           sDarkColor +
           ";\n" +
           "  background-position: 0 -15px;\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           ":active,\n" +
           ".btn-" +
           sClassName +
           ".active {\n" +
           "  background-color: #" +
           sLightColor +
           ";\n" +
           "  border-color: #" +
           sDarkColor +
           ";\n" +
           "}\n" +
           ".btn-" +
           sClassName +
           ":disabled,\n" +
           ".btn-" +
           sClassName +
           "[disabled] {\n" +
           "  background-color: #" +
           sLightColor +
           ";\n" +
           "  background-image: none;\n" +
           "}";
  }

  public static void main (final String [] args)
  {
    LOGGER.info (createButtonCSSCode ("orange", "f4ac16", "f8951f"));
  }
}
