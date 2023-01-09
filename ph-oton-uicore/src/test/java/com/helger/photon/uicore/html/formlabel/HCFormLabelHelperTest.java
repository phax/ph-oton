/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.formlabel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test class for class {@link HCFormLabelHelper}.
 *
 * @author Philip Helger
 */
public final class HCFormLabelHelperTest
{
  @Test
  public void testBasic ()
  {
    // Ensure defaults are set
    assertEquals (HCFormLabelHelper.DEFAULT_SIGN_OPTIONAL, HCFormLabelHelper.getSuffixString (ELabelType.OPTIONAL));
    assertEquals (HCFormLabelHelper.DEFAULT_SIGN_ALTERNATIVE, HCFormLabelHelper.getSuffixString (ELabelType.ALTERNATIVE));
    assertEquals (HCFormLabelHelper.DEFAULT_SIGN_MANDATORY, HCFormLabelHelper.getSuffixString (ELabelType.MANDATORY));
    assertEquals (HCFormLabelHelper.DEFAULT_LABEL_END, HCFormLabelHelper.getDefaultLabelEnd ());

    assertNull (HCFormLabelHelper.trimAllKnownSuffixes (null));
    assertEquals ("", HCFormLabelHelper.trimAllKnownSuffixes (""));
    assertEquals ("abc", HCFormLabelHelper.trimAllKnownSuffixes ("abc:"));
    assertEquals ("abc", HCFormLabelHelper.trimAllKnownSuffixes ("abc*"));
    assertEquals ("abc", HCFormLabelHelper.trimAllKnownSuffixes ("abc°"));
    // Order matters ...
    assertEquals ("abc", HCFormLabelHelper.trimAllKnownSuffixes ("abc:*°:"));
    assertEquals ("abc", HCFormLabelHelper.trimAllKnownSuffixes ("abc:°*:"));
    assertEquals ("abc", HCFormLabelHelper.trimAllKnownSuffixes ("abc:°*::°*::°*::°*::°*::°*::°*::°*::°*::°*::°*:"));
  }
}
