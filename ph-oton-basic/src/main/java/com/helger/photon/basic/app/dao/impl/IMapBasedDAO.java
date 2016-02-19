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
package com.helger.photon.basic.app.dao.impl;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.id.IHasID;

@ThreadSafe
public interface IMapBasedDAO <INTERFACETYPE extends IHasID <String>>
{
  @Nonnull
  @ReturnsMutableCopy
  Collection <? extends INTERFACETYPE> getAll ();

  @Nonnull
  @ReturnsMutableCopy
  Collection <? extends INTERFACETYPE> getAll (@Nullable Predicate <INTERFACETYPE> aFilter);

  @Nonnull
  @ReturnsMutableCopy
  <RETTYPE> Collection <RETTYPE> getAllMapped (@Nullable Predicate <INTERFACETYPE> aFilter,
                                               @Nonnull Function <INTERFACETYPE, RETTYPE> aMapper);

  @Nullable
  INTERFACETYPE getFirst (@Nullable Predicate <INTERFACETYPE> aFilter);

  @Nullable
  <RETTYPE> RETTYPE getFirstMapped (@Nullable Predicate <INTERFACETYPE> aFilter,
                                    @Nonnull Function <INTERFACETYPE, RETTYPE> aMapper);

  boolean containsAny (@Nullable Predicate <INTERFACETYPE> aFilter);

  boolean containsWithID (@Nullable String sID);
}
