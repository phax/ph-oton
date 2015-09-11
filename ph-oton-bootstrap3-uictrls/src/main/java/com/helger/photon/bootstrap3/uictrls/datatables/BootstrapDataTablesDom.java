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
package com.helger.photon.bootstrap3.uictrls.datatables;

import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.uictrls.datatables.DataTablesDom;

/**
 * The data tables "dom" to be used for Bootstrap. This is default DOM as
 * created by dataTables.bootstrap except for the added "hidden-print" classes.
 *
 * @author Philip Helger
 */
public class BootstrapDataTablesDom extends DataTablesDom
{
  public BootstrapDataTablesDom ()
  {
    openDiv (CBootstrapCSS.ROW, CBootstrapCSS.HIDDEN_PRINT);
    {
      openDiv (CBootstrapCSS.COL_SM_6);
      addLengthMenu ();
      closeDiv ();
      openDiv (CBootstrapCSS.COL_SM_6);
      addFiltering ();
      closeDiv ();
      addProcessing ();
    }
    closeDiv ();
    openDiv (CBootstrapCSS.ROW);
    {
      openDiv (CBootstrapCSS.COL_SM_12);
      addTable ();
      closeDiv ();
    }
    closeDiv ();
    openDiv (CBootstrapCSS.ROW, CBootstrapCSS.HIDDEN_PRINT);
    {
      openDiv (CBootstrapCSS.COL_SM_5);
      addInformationSummary ();
      closeDiv ();
      openDiv (CBootstrapCSS.COL_SM_7);
      addPagination ();
      closeDiv ();
    }
    closeDiv ();
  }
}
