/*
 * Copyright (C) 2021-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.jdbc.security;

import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.string.StringHelper;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.mgr.AbstractJDBCEnabledManager;
import com.helger.json.IJson;
import com.helger.json.IJsonArray;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.json.serialize.JsonReader;

/**
 * A special JDBC enabled manager with common methods for the security managers.
 *
 * @author Philip Helger
 */
public abstract class AbstractJDBCEnabledSecurityManager extends AbstractJDBCEnabledManager
{
  protected AbstractJDBCEnabledSecurityManager (@Nonnull final Supplier <? extends DBExecutor> aDBExecSupplier)
  {
    super (aDBExecSupplier);
  }

  @Nonnull
  @ReturnsMutableCopy
  public <T> ICommonsList <T> getNone ()
  {
    return new CommonsArrayList <> ();
  }

  @Nullable
  protected static final String attrsToString (@Nullable final Map <String, String> aAttrs)
  {
    if (CollectionHelper.isEmpty (aAttrs))
      return null;
    return new JsonObject ().addAll (aAttrs).getAsJsonString ();
  }

  @Nullable
  protected static final ICommonsOrderedMap <String, String> attrsToMap (@Nullable final String sAttrs)
  {
    if (StringHelper.hasNoText (sAttrs))
      return null;

    final IJsonObject aJson = JsonReader.builder ().source (sAttrs).readAsObject ();
    if (aJson == null)
      return null;
    final ICommonsOrderedMap <String, String> ret = new CommonsLinkedHashMap <> ();
    for (final Map.Entry <String, IJson> aEntry : aJson)
      ret.put (aEntry.getKey (), aEntry.getValue ().getAsValue ().getAsString ());
    return ret;
  }

  @Nullable
  protected static final String idsToString (@Nullable final Iterable <String> aIDs)
  {
    if (CollectionHelper.isEmpty (aIDs))
      return null;
    return new JsonArray ().addAll (aIDs).getAsJsonString ();
  }

  @Nullable
  protected static final ICommonsSet <String> idsToSet (@Nullable final String sIDs)
  {
    if (StringHelper.hasNoText (sIDs))
      return null;

    final IJsonArray aJson = JsonReader.builder ().source (sIDs).readAsArray ();
    if (aJson == null)
      return null;
    final ICommonsSet <String> ret = new CommonsHashSet <> ();
    for (final IJson aEntry : aJson)
      ret.add (aEntry.getAsValue ().getAsString ());
    return ret;
  }
}
