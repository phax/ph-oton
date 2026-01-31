/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.config;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.style.IsSPIImplementation;
import com.helger.photon.security.object.accarea.AccountingArea;
import com.helger.photon.security.object.accarea.AccountingAreaMicroTypeConverter;
import com.helger.photon.security.object.tenant.Tenant;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistry;

/**
 * Special micro type converter for this project.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class MicroTypeConverterRegistrar_ph_oton_security_Mock implements IMicroTypeConverterRegistrarSPI
{
  public void registerMicroTypeConverter (@NonNull final IMicroTypeConverterRegistry aRegistry)
  {
    try
    {
      aRegistry.registerMicroElementTypeConverter (AccountingArea.class,
                                                   new AccountingAreaMicroTypeConverter (sID -> new Tenant (sID, "Dummy-" + sID)));
    }
    catch (final IllegalArgumentException ex)
    {
      // Another micro element converter is already registered - ignore
    }
  }
}
