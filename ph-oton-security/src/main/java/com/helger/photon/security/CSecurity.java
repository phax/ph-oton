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
package com.helger.photon.security;

import javax.annotation.concurrent.Immutable;

import com.helger.commons.type.ObjectType;
import com.helger.photon.basic.auth.CBasicSecurity;

/**
 * Constants for user handling
 *
 * @author Philip Helger
 */
@Immutable
public final class CSecurity
{
  /** Bit field for undefined access. */
  public static final int UNDEFINED_ACCESS = 0x01;

  /** Bit field for granted access. */
  public static final int HAS_ACCESS = 0x02;

  /** Bit field for denied access. */
  public static final int HAS_NO_ACCESS = 0x04;

  /** Bit field for derived access (from user group). */
  public static final int INHERITED_ACCESS_FROM_USERGROUP = 0x80;

  /** Bit field for derived access (from parent object). */
  public static final int INHERITED_ACCESS_FROM_OBJECT = 0x78;

  /**
   * If no right settings are applied to a right object, does this mean that a
   * user has access, or not?
   */
  public static final boolean NO_RIGHT_SPECIFIED_MEANS_HAS_ACCESS = true;

  /**
   * If a user is not assigned to a single user group, does it mean he has
   * access?
   */
  public static final boolean USER_WITHOUT_USERGROUP_HAS_ACCESS = false;

  /**
   * This is relevant for hierarchical right objects only. If set to true,
   * rights set on parent objects will dominate rights set on parent user groups
   * for the current object
   */
  public static final boolean INHERIT_OBJECT_BEFORE_USERGROUP = true;

  /**
   * Define whether an explicit "forbidden" access right has higher precedence
   * than an explicit "allowed".
   */
  public static final boolean FORBIDDEN_HAS_HIGHER_PRECEDENCE = false;

  // Default right object facet names
  public static final String FACET_READ = "read";
  public static final String FACET_WRITE = "write";

  // Object types
  public static final ObjectType TYPE_USER = new ObjectType ("user");
  public static final ObjectType TYPE_USERGROUP = new ObjectType ("usergroup");
  public static final ObjectType TYPE_ROLE = new ObjectType ("role");

  // Default users
  public static final String USER_ADMINISTRATOR_ID = "admin";
  public static final String USER_ADMINISTRATOR_LOGIN = "Admin";
  public static final String USER_ADMINISTRATOR_EMAIL = "admin@helger.com";
  public static final String USER_ADMINISTRATOR_NAME = "Administrator";
  public static final String USER_ADMINISTRATOR_PASSWORD = "password";

  public static final String USER_USER_ID = "user";
  public static final String USER_USER_LOGIN = "User";
  public static final String USER_USER_EMAIL = "user@helger.com";
  public static final String USER_USER_NAME = "User";
  public static final String USER_USER_PASSWORD = "user";

  public static final String USER_GUEST_ID = "guest";
  public static final String USER_GUEST_LOGIN = "Guest";
  public static final String USER_GUEST_EMAIL = "guest@helger.com";
  public static final String USER_GUEST_NAME = "Guest";
  public static final String USER_GUEST_PASSWORD = "guest";

  /** The user ID to be used, if no user is logged in */
  public static final String USER_ID_NONE_LOGGED_IN = CBasicSecurity.USER_ID_NONE_LOGGED_IN;

  // Default roles
  public static final String ROLE_ADMINISTRATOR_ID = "radmin";
  public static final String ROLE_ADMINISTRATOR_NAME = "Administrator";
  public static final String ROLE_USER_ID = "ruser";
  public static final String ROLE_USER_NAME = "User";

  // Default user groups
  public static final String USERGROUP_ADMINISTRATORS_ID = "ugadmin";
  public static final String USERGROUP_ADMINISTRATORS_NAME = "Administrators";
  public static final String USERGROUP_USERS_ID = "uguser";
  public static final String USERGROUP_USERS_NAME = "Users";
  public static final String USERGROUP_GUESTS_ID = "ugguest";
  public static final String USERGROUP_GUESTS_NAME = "Guests";

  private CSecurity ()
  {}
}
