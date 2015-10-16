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
package com.helger.photon.security.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.commons.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.helger.commons.microdom.convert.IMicroTypeConverterRegistry;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.basic.object.client.IClientResolver;
import com.helger.photon.security.object.accarea.AccountingArea;
import com.helger.photon.security.object.accarea.AccountingAreaMicroTypeConverter;
import com.helger.photon.security.object.client.Client;

/**
 * Special micro type converter for this project.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class MicroTypeConverterRegistrar_ph_oton_security_Mock implements IMicroTypeConverterRegistrarSPI
{
  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    try
    {
      aRegistry.registerMicroElementTypeConverter (AccountingArea.class,
                                                   new AccountingAreaMicroTypeConverter (new IClientResolver ()
                                                   {
                                                     @Nullable
                                                     public IClient getClientOfID (@Nullable final String sID)
                                                     {
                                                       return new Client (sID, "Dummy-" + sID);
                                                     }
                                                   }));
    }
    catch (final IllegalArgumentException ex)
    {
      // Another micro element converter is already registered - ignore
    }
  }
}
