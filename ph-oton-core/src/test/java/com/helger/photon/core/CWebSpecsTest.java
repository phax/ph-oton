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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.io.resource.IReadableResource;
import com.helger.xml.schema.XMLSchemaCache;

import jakarta.annotation.Nonnull;

/**
 * Test class for class {@link CWebSpecs}.
 *
 * @author Philip Helger
 */
public final class CWebSpecsTest
{
  private void _testXSD (@Nonnull final IReadableResource aXSD)
  {
    assertNotNull (aXSD);
    assertTrue (aXSD.exists ());
    assertNotNull (XMLSchemaCache.getInstance ().getSchema (aXSD));
    assertNotNull (XMLSchemaCache.getInstance ().getValidator (aXSD));
  }

  @Test
  public void testXSDs ()
  {
    _testXSD (CWebSpecs.JSP_20_XSD);
    _testXSD (CWebSpecs.JSP_21_XSD);
    _testXSD (CWebSpecs.JSP_22_XSD);
    _testXSD (CWebSpecs.JSP_23_XSD);

    _testXSD (CWebSpecs.JSP_TAG_LIBRARY_20_XSD);
    _testXSD (CWebSpecs.JSP_TAG_LIBRARY_21_XSD);

    _testXSD (CWebSpecs.WEB_APP_24_XSD);
    _testXSD (CWebSpecs.WEB_APP_25_XSD);
    _testXSD (CWebSpecs.WEB_APP_30_XSD);
    _testXSD (CWebSpecs.WEB_APP_31_XSD);

    _testXSD (CWebSpecs.WEB_FRAGMENT_30_XSD);
    _testXSD (CWebSpecs.WEB_FRAGMENT_31_XSD);
  }
}
