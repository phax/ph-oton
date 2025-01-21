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
package com.helger.photon.security.config;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.photon.security.object.accarea.AccountingArea;
import com.helger.photon.security.object.accarea.AccountingAreaMicroTypeConverter;
import com.helger.photon.security.object.tenant.Tenant;
import com.helger.photon.security.object.tenant.TenantMicroTypeConverter;
import com.helger.photon.security.role.Role;
import com.helger.photon.security.role.RoleMicroTypeConverter;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.AccessTokenMicroTypeConverter;
import com.helger.photon.security.token.revocation.RevocationStatus;
import com.helger.photon.security.token.revocation.RevocationStatusMicroTypeConverter;
import com.helger.photon.security.token.user.UserToken;
import com.helger.photon.security.token.user.UserTokenMicroTypeConverter;
import com.helger.photon.security.user.User;
import com.helger.photon.security.user.UserMicroTypeConverter;
import com.helger.photon.security.usergroup.UserGroup;
import com.helger.photon.security.usergroup.UserGroupMicroTypeConverter;
import com.helger.tenancy.tenant.ITenantResolver;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.helger.xml.microdom.convert.IMicroTypeConverterRegistry;

/**
 * Special micro type converter for this project.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class MicroTypeConverterRegistrar_ph_oton_security implements IMicroTypeConverterRegistrarSPI
{
  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    aRegistry.registerMicroElementTypeConverter (AccessToken.class, new AccessTokenMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (Tenant.class, new TenantMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (RevocationStatus.class, new RevocationStatusMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (Role.class, new RoleMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (User.class, new UserMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (UserGroup.class, new UserGroupMicroTypeConverter ());
    aRegistry.registerMicroElementTypeConverter (UserToken.class, new UserTokenMicroTypeConverter ());
  }

  public static void registerSpecialMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry,
                                                        @Nonnull final ITenantResolver aTenantResolver)
  {
    aRegistry.registerMicroElementTypeConverter (AccountingArea.class, new AccountingAreaMicroTypeConverter (aTenantResolver));
  }
}
