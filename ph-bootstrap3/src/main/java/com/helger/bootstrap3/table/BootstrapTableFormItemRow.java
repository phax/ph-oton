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
package com.helger.bootstrap3.table;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.BootstrapHelper;
import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.bootstrap3.form.BootstrapHelpBlock;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;
import com.helger.photon.uicore.html.table.HCTableFormItemRow;
import com.helger.validation.error.IError;

public class BootstrapTableFormItemRow extends HCTableFormItemRow
{
  public BootstrapTableFormItemRow (final boolean bHeader, final boolean bHasNoteColumn)
  {
    super (bHeader, bHasNoteColumn);
  }

  @Override
  protected void onLabelModified (@Nullable final HCFormLabel aLabel)
  {
    aLabel.addClass (CBootstrapCSS.CONTROL_LABEL);
  }

  @Override
  protected IHCNode createErrorNode (@Nonnull final IError aError)
  {
    return new BootstrapHelpBlock ().addChild (aError.getErrorText ());
  }

  @Override
  protected void modifyControls (@Nonnull final Iterable <? extends IHCNode> aCtrls, final boolean bHasErrors)
  {
    // Add/remove a class from the table row
    if (bHasErrors)
      addClass (CBootstrapCSS.HAS_ERROR);
    else
      removeClass (CBootstrapCSS.HAS_ERROR);

    // Add form-control class
    BootstrapHelper.markAsFormControls (HCUtils.getAllHCControls (aCtrls));
  }
}
