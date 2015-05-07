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
package com.helger.appbasics.object.config;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.appbasics.object.accarea.AccountingArea;
import com.helger.appbasics.object.accarea.AccountingAreaMicroTypeConverter;
import com.helger.appbasics.object.client.Client;
import com.helger.appbasics.object.client.ClientMicroTypeConverter;
import com.helger.appbasics.object.client.IClientResolver;
import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.microdom.convert.IMicroTypeConverterRegistry;

@Immutable
public final class AppBasicsMicroTypeConverterRegistry
{
  @PresentForCodeCoverage
  private static final AppBasicsMicroTypeConverterRegistry s_aInstance = new AppBasicsMicroTypeConverterRegistry ();

  private AppBasicsMicroTypeConverterRegistry ()
  {}

  public static void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry,
                                                 @Nonnull final IClientResolver aClientResolver)
  {
    aRegistry.registerMicroElementTypeConverter (AccountingArea.class,
                                                 new AccountingAreaMicroTypeConverter (aClientResolver));
    aRegistry.registerMicroElementTypeConverter (Client.class, new ClientMicroTypeConverter ());
  }
}
