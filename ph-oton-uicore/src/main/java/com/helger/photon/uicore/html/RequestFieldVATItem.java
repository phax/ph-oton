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
package com.helger.photon.uicore.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.masterdata.vat.IVATItem;
import com.helger.masterdata.vat.VATManager;
import com.helger.photon.core.form.RequestField;

/**
 * Special {@link RequestField} child class, that handles {@link IVATItem}
 * objects, and falls back to the 0% item if nothing is selected.
 *
 * @author Philip Helger
 */
public class RequestFieldVATItem extends RequestField
{
  public RequestFieldVATItem (@Nonnull final String sFieldName, @Nullable final IVATItem aVATItem)
  {
    super (sFieldName, aVATItem == null ? VATManager.VATTYPE_NONE.getID () : aVATItem.getID ());
  }
}
