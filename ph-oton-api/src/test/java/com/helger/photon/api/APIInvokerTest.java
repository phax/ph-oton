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
package com.helger.photon.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jspecify.annotations.NonNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.helger.base.CGlobal;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.http.EHttpMethod;
import com.helger.http.EHttpVersion;
import com.helger.photon.app.mock.PhotonAppWebTestRule;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.statistics.api.IMutableStatisticsHandlerKeyedCounter;
import com.helger.statistics.api.IMutableStatisticsHandlerKeyedTimer;
import com.helger.statistics.impl.StatisticsManager;
import com.helger.web.scope.mgr.WebScoped;

/**
 * Test class for class {@link APIInvoker}.
 *
 * @author Philip Helger
 */
public final class APIInvokerTest
{
  private static final String ROUTE = "/user/{id}";
  private static final String PATH1 = "/user/4711";
  private static final String PATH2 = "/user/4712";

  @Rule
  public final TestRule m_aRule = new PhotonAppWebTestRule ();

  @NonNull
  private static UnifiedResponse _createResponse (@NonNull final WebScoped aWebScoped)
  {
    // The mock request has no protocol, so createSimple (...) cannot be used
    return new UnifiedResponse (EHttpVersion.HTTP_11, EHttpMethod.GET, aWebScoped.getRequestScope ().getRequest ());
  }

  private static void _invoke (@NonNull final String sPath) throws Exception
  {
    final APIDescriptor aDescriptor = new APIDescriptor (APIPath.get (ROUTE), (a, b, c, d, e) -> {});
    final InvokableAPIDescriptor aInvokable = new InvokableAPIDescriptor (aDescriptor,
                                                                          sPath,
                                                                          new CommonsLinkedHashMap <> ());
    try (final WebScoped aWebScoped = new WebScoped ())
    {
      new APIInvoker ().invoke (aInvokable, aWebScoped.getRequestScope (), _createResponse (aWebScoped));
    }
  }

  private static long _nonNegative (final long nValue)
  {
    // A key that was never used yields ILLEGAL_ULONG
    return nValue == CGlobal.ILLEGAL_ULONG ? 0 : nValue;
  }

  @Test
  public void testStatisticsAreKeyedByRoute () throws Exception
  {
    // These are the very same instances that APIInvoker holds in its static fields
    final IMutableStatisticsHandlerKeyedCounter aFunc = StatisticsManager.getKeyedCounterHandler (APIInvoker.class.getName () +
                                                                                                  "$func");
    final IMutableStatisticsHandlerKeyedTimer aTimer = StatisticsManager.getKeyedTimerHandler (APIInvoker.class.getName () +
                                                                                               "$timer");
    // Other tests of this module invoke APIs as well
    final long nFuncBefore = _nonNegative (aFunc.getCount (ROUTE));
    final long nTimerBefore = _nonNegative (aTimer.getInvocationCount (ROUTE));

    // Two different concrete paths of the same API
    _invoke (PATH1);
    _invoke (PATH2);

    // Both are counted under the single bounded route key
    assertTrue (aFunc.getAllKeys ().contains (ROUTE));
    assertEquals (nFuncBefore + 2, aFunc.getCount (ROUTE));
    assertTrue (aTimer.getAllKeys ().contains (ROUTE));
    assertEquals (nTimerBefore + 2, aTimer.getInvocationCount (ROUTE));

    // The concrete paths are unbounded and must never become a key
    for (final String sPath : new String [] { PATH1, PATH2 })
    {
      assertFalse (aFunc.getAllKeys ().contains (sPath));
      assertFalse (aTimer.getAllKeys ().contains (sPath));
    }
  }
}
