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
package com.helger.photon.basic.security.role;

import com.helger.commons.annotations.MustImplementEqualsAndHashcode;
import com.helger.commons.collections.attrs.IReadonlyAttributeContainer;
import com.helger.commons.name.IHasDescription;
import com.helger.commons.name.IHasName;
import com.helger.commons.type.ITypedObject;

/**
 * Represents a single role that can be assigned to user groups.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IRole extends ITypedObject <String>, IHasName, IHasDescription, IReadonlyAttributeContainer
{
  /* empty */
}
