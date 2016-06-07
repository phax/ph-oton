/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.config;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.photon.core.smtp.NamedSMTPSettings;
import com.helger.photon.core.smtp.NamedSMTPSettingsMicroTypeConverter;
import com.helger.photon.core.userdata.UserDataObject;
import com.helger.photon.core.userdata.UserDataObjectMicroTypeConverter;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistry;

/**
 * Register all MicroTypeConverter implementations of this project.
 *
 * @author Philip Helger
 */
@Immutable
@IsSPIImplementation
public final class MicroTypeConverterRegistrar_ph_oton_core implements IMicroTypeConverterRegistrarSPI
{
  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (NamedSMTPSettings.class, new NamedSMTPSettingsMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (UserDataObject.class, new UserDataObjectMicroTypeConverter ());
  }
}
