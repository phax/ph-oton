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
package com.helger.webctrls.datatables;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public final class DataTablesDomTest
{
  @Test
  public void testBasic ()
  {
    assertEquals ("", new DataTablesDom ().getAsString ());
    assertEquals ("t", new DataTablesDom ().addTable ().getAsString ());
    assertEquals ("<'row hidden-print'<'col-xs-6'l><'col-xs-6'f>r>t<'row hidden-print'<'col-xs-12 col-sm-4'i><'col-xs-12 col-sm-8'p>>",
                  new DataTablesDom ().openDiv ("row hidden-print")
                                      .openDiv ("col-xs-6")
                                      .addLengthMenu ()
                                      .closeDiv ()
                                      .openDiv ("col-xs-6")
                                      .addSearchBox ()
                                      .closeDiv ()
                                      .addProcessing ()
                                      .closeDiv ()
                                      .addTable ()
                                      .openDiv ("row hidden-print")
                                      .openDiv ("col-xs-12 col-sm-4")
                                      .addPositionIndicator ()
                                      .closeDiv ()
                                      .openDiv ("col-xs-12 col-sm-8")
                                      .addPagination ()
                                      .closeDiv ()
                                      .closeDiv ()
                                      .getAsString ());
  }
}
