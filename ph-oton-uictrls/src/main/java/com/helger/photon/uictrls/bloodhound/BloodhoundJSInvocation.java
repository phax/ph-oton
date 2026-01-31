/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.bloodhound;

import java.util.List;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.array.ArrayHelper;
import com.helger.collection.CollectionHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.html.jscode.AbstractJSInvocation;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.IJSGeneratable;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSExpr;

/**
 * Special Bloodhound invocation. Offers all methods as of 0.10.2
 *
 * @author Philip Helger
 */
public class BloodhoundJSInvocation extends AbstractJSInvocation <BloodhoundJSInvocation>
{
  public BloodhoundJSInvocation (final IJSGeneratable aType)
  {
    super (aType);
  }

  public BloodhoundJSInvocation (@Nullable final IJSExpression aLhs, @NonNull @Nonempty final String sMethod)
  {
    super (aLhs, sMethod);
  }

  /**
   * Invoke an arbitrary function on this jQuery object.
   *
   * @param sMethod
   *        The method to be invoked. May neither be <code>null</code> nor empty.
   * @return A new jQuery invocation object. Never <code>null</code>.
   */
  @NonNull
  public BloodhoundJSInvocation bloodhoundInvoke (@NonNull @Nonempty final String sMethod)
  {
    return new BloodhoundJSInvocation (this, sMethod);
  }

  @NonNull
  public BloodhoundJSInvocation initialize ()
  {
    return bloodhoundInvoke ("initialize");
  }

  @NonNull
  public BloodhoundJSInvocation initialize (final boolean bReinitialize)
  {
    return initialize ().arg (bReinitialize);
  }

  @NonNull
  public BloodhoundJSInvocation add (@Nullable final BloodhoundDatum aDatum)
  {
    if (aDatum != null)
      return add (new CommonsArrayList <> (aDatum));
    return this;
  }

  @NonNull
  public BloodhoundJSInvocation add (@Nullable final BloodhoundDatum... aDatums)
  {
    if (ArrayHelper.isNotEmpty (aDatums))
      return add (new CommonsArrayList <> (aDatums));
    return this;
  }

  @NonNull
  public BloodhoundJSInvocation add (@Nullable final List <? extends BloodhoundDatum> aDatums)
  {
    if (CollectionHelper.isNotEmpty (aDatums))
    {
      final JSArray aArray = new JSArray ();
      for (final BloodhoundDatum aDatum : aDatums)
        aArray.add (aDatum.getAsJson ());
      return bloodhoundInvoke ("add").arg (aArray);
    }
    return this;
  }

  @NonNull
  public BloodhoundJSInvocation clear ()
  {
    return bloodhoundInvoke ("clear");
  }

  @NonNull
  public BloodhoundJSInvocation clearPrefetchCache ()
  {
    return bloodhoundInvoke ("clearPrefetchCache");
  }

  @NonNull
  public BloodhoundJSInvocation clearRemoteCache ()
  {
    return bloodhoundInvoke ("clearRemoteCache");
  }

  @NonNull
  public BloodhoundJSInvocation get ()
  {
    return bloodhoundInvoke ("get");
  }

  /**
   * Computes a set of suggestions for <code>query</code>. <code>cb</code> will be invoked with an
   * array of datums that represent said set. <code>cb</code> will always be invoked once
   * synchronously with suggestions that were available on the client. If those suggestions are
   * insufficient (# of suggestions is less than <code>limit</code>) and remote was configured,
   * <code>cb</code> may also be invoked asynchronously with the suggestions available on the client
   * mixed with suggestions from the <code>remote</code> source.
   *
   * @param sQuery
   *        Query string
   * @param aCallback
   *        Callback function. Takes one argument: array of suggestions
   * @return new {@link BloodhoundJSInvocation}
   */
  @NonNull
  public BloodhoundJSInvocation get (@NonNull final String sQuery, @NonNull final JSAnonymousFunction aCallback)
  {
    return get (JSExpr.lit (sQuery), aCallback);
  }

  /**
   * Computes a set of suggestions for <code>query</code>. <code>cb</code> will be invoked with an
   * array of datums that represent said set. <code>cb</code> will always be invoked once
   * synchronously with suggestions that were available on the client. If those suggestions are
   * insufficient (# of suggestions is less than <code>limit</code>) and remote was configured,
   * <code>cb</code> may also be invoked asynchronously with the suggestions available on the client
   * mixed with suggestions from the <code>remote</code> source.
   *
   * @param aQuery
   *        Query string
   * @param aCallback
   *        Callback function. Takes one argument: array of suggestions
   * @return new {@link BloodhoundJSInvocation}
   */
  @NonNull
  public BloodhoundJSInvocation get (@NonNull final IJSExpression aQuery, @NonNull final IJSExpression aCallback)
  {
    return get ().arg (aQuery).arg (aCallback);
  }

  /**
   * To be used as the <code>source</code> parameter for Typeahead integration.
   *
   * @return Invocation of <code>ttAdapter</code>
   */
  @NonNull
  public BloodhoundJSInvocation ttAdapter ()
  {
    return bloodhoundInvoke ("ttAdapter");
  }
}
