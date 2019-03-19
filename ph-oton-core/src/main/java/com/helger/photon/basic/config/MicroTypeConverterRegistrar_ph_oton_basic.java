/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.config;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.photon.audit.AuditItem;
import com.helger.photon.audit.AuditItemMicroTypeConverter;
import com.helger.photon.basic.favorites.Favorite;
import com.helger.photon.basic.favorites.FavoriteMicroTypeConverter;
import com.helger.photon.basic.longrun.LongRunningJobData;
import com.helger.photon.basic.longrun.LongRunningJobDataMicroTypeConverter;
import com.helger.photon.basic.migration.SystemMigrationResult;
import com.helger.photon.basic.migration.SystemMigrationResultMicroTypeConverter;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistry;

/**
 * Special micro type converter for this project.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class MicroTypeConverterRegistrar_ph_oton_basic implements IMicroTypeConverterRegistrarSPI
{
  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (AuditItem.class, new AuditItemMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (Favorite.class, new FavoriteMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (LongRunningJobData.class, new LongRunningJobDataMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (SystemMigrationResult.class,
                                                 new SystemMigrationResultMicroTypeConverter ());
  }
}
