/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core;

import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.io.resource.ClassPathResource;

import jakarta.annotation.Nonnull;

/**
 * Constants in the field of Java web-related specifications
 *
 * @author Philip Helger
 */
public final class CWebSpecs
{
  private static final String PREFIX = "external/schemas/servlet/";

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return CWebSpecs.class.getClassLoader ();
  }

  public static final ClassPathResource JSP_20_XSD = new ClassPathResource (PREFIX + "jsp_2_0.xsd", _getCL ());
  public static final ClassPathResource JSP_21_XSD = new ClassPathResource (PREFIX + "jsp_2_1.xsd", _getCL ());
  public static final ClassPathResource JSP_22_XSD = new ClassPathResource (PREFIX + "jsp_2_2.xsd", _getCL ());
  public static final ClassPathResource JSP_23_XSD = new ClassPathResource (PREFIX + "jsp_2_3.xsd", _getCL ());

  public static final ClassPathResource JSP_TAG_LIBRARY_20_XSD = new ClassPathResource (PREFIX +
                                                                                        "web-jsptaglibrary_2_0.xsd",
                                                                                        _getCL ());
  public static final ClassPathResource JSP_TAG_LIBRARY_21_XSD = new ClassPathResource (PREFIX +
                                                                                        "web-jsptaglibrary_2_1.xsd",
                                                                                        _getCL ());

  public static final ClassPathResource WEB_APP_24_XSD = new ClassPathResource (PREFIX + "web-app_2_4.xsd", _getCL ());
  public static final ClassPathResource WEB_APP_25_XSD = new ClassPathResource (PREFIX + "web-app_2_5.xsd", _getCL ());
  public static final ClassPathResource WEB_APP_30_XSD = new ClassPathResource (PREFIX + "web-app_3_0.xsd", _getCL ());
  public static final ClassPathResource WEB_APP_31_XSD = new ClassPathResource (PREFIX + "web-app_3_1.xsd", _getCL ());

  public static final ClassPathResource WEB_FRAGMENT_30_XSD = new ClassPathResource (PREFIX + "web-fragment_3_0.xsd",
                                                                                     _getCL ());
  public static final ClassPathResource WEB_FRAGMENT_31_XSD = new ClassPathResource (PREFIX + "web-fragment_3_1.xsd",
                                                                                     _getCL ());

  public static final String WEB_XML_PATH = "WEB-INF/web.xml";
  public static final String WEB_FRAGMENT_PATH = "META-INF/web-fragment.xml";

  @PresentForCodeCoverage
  private static final CWebSpecs INSTANCE = new CWebSpecs ();

  private CWebSpecs ()
  {}
}
