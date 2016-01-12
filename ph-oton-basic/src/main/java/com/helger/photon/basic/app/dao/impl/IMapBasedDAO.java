/**
 * Copyright (C) 2012-2015 winenet GmbH - www.winenet.at
 * All Rights Reserved
 *
 * This file is part of the winenet-Kellerbuch software.
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is
 * strictly prohibited.
 */
package com.helger.photon.basic.app.dao.impl;

import java.util.Collection;
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

  @Nullable
  INTERFACETYPE getFirst (@Nullable Predicate <INTERFACETYPE> aFilter);

  boolean containsAny (@Nullable Predicate <INTERFACETYPE> aFilter);

  boolean containsWithID (@Nullable String sID);
}
