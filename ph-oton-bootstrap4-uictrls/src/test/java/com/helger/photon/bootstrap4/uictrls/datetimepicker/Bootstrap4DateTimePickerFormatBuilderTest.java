/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jakarta.annotation.Nonnull;

/**
 * Test class for class {@link Bootstrap4DateTimePickerFormatBuilder}.
 * 
 * @author Philip Helger
 */
public final class Bootstrap4DateTimePickerFormatBuilderTest
{
  @Nonnull
  private static String _getAsJS (@Nonnull final String s)
  {
    return Bootstrap4DateTimePickerFormatBuilder.fromJavaPattern (s).getJSCalendarFormatString ();
  }

  @Test
  public void testBasic ()
  {
    assertEquals ("DD.MM.YYYY", _getAsJS ("dd.MM.yyyy"));
    assertEquals ("DD.MM.YYYY", _getAsJS ("dd.MM.uuuu"));
    assertEquals ("DD/MM", _getAsJS ("dd/MM"));
  }
}
