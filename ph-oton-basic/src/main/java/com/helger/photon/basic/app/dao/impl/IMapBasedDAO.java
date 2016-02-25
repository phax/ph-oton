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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
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
  Collection <? extends INTERFACETYPE> getAll (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  void findAll (@Nullable Predicate <? super INTERFACETYPE> aFilter,
                @Nonnull Consumer <? super INTERFACETYPE> aConsumer);

  @Nonnull
  @ReturnsMutableCopy
  <RETTYPE> Collection <RETTYPE> getAllMapped (@Nullable Predicate <? super INTERFACETYPE> aFilter,
                                               @Nonnull Function <? super INTERFACETYPE, ? extends RETTYPE> aMapper);

  <RETTYPE> void findAllMapped (@Nullable Predicate <? super INTERFACETYPE> aFilter,
                                @Nonnull Function <? super INTERFACETYPE, ? extends RETTYPE> aMapper,
                                @Nonnull Consumer <? super RETTYPE> aConsumer);

  @Nullable
  INTERFACETYPE getFirst (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  @Nullable
  <RETTYPE> RETTYPE getFirstMapped (@Nullable Predicate <? super INTERFACETYPE> aFilter,
                                    @Nonnull Function <? super INTERFACETYPE, ? extends RETTYPE> aMapper);

  boolean containsAny (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  boolean containsNone (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  boolean containsOnly (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  boolean containsWithID (@Nullable String sID);

  @Nonnegative
  int getCount ();

  @Nonnegative
  int getCount (@Nullable Predicate <? super INTERFACETYPE> aFilter);
}
