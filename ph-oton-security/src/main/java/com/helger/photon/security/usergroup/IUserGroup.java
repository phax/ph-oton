/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.security.usergroup;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.name.IHasName;
import com.helger.commons.text.IHasDescription;
import com.helger.photon.security.role.IRoleContainer;
import com.helger.photon.security.user.IUserContainer;
import com.helger.tenancy.IBusinessObject;

/**
 * Represents a single user group encapsulating 0-n users.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IUserGroup extends IBusinessObject, IHasName, IHasDescription, IUserContainer, IRoleContainer
{
  /* empty */
}
