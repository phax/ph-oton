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
package com.helger.photon.bootstrap4.config;

import javax.annotation.Nullable;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.commons.thirdparty.ELicense;
import com.helger.commons.thirdparty.IThirdPartyModule;
import com.helger.commons.thirdparty.IThirdPartyModuleProviderSPI;
import com.helger.commons.thirdparty.ThirdPartyModule;
import com.helger.photon.bootstrap4.CBootstrap;

/**
 * Implement this SPI interface if your JAR file contains external third party
 * modules.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class ThirdPartyModuleProvider_ph_oton_bootstrap4 implements IThirdPartyModuleProviderSPI
{
  public static final IThirdPartyModule BOOTSTRAP4 = new ThirdPartyModule ("Bootstrap",
                                                                           "Bootstrap",
                                                                           ELicense.MIT,
                                                                           CBootstrap.BOOTSTRAP_VERSION_46,
                                                                           "https://getbootstrap.com/docs/4.6");

  @Nullable
  public IThirdPartyModule [] getAllThirdPartyModules ()
  {
    return new IThirdPartyModule [] { BOOTSTRAP4 };
  }
}
