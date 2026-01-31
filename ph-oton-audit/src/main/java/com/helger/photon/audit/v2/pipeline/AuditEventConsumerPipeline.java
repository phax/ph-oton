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
package com.helger.photon.audit.v2.pipeline;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.callback.CallbackList;
import com.helger.base.callback.exception.IExceptionCallback;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.diagnostics.callback.exception.LoggingExceptionCallback;
import com.helger.photon.audit.v2.domain.AuditEvent;

/**
 * Pipeline of {@link IAuditEventConsumer} being itself an
 * {@link IAuditEventConsumer}. So basically a list of other consumers.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class AuditEventConsumerPipeline implements IAuditEventConsumer
{
  private final ICommonsList <IAuditEventConsumer> m_aConsumers = new CommonsArrayList <> ();
  private final CallbackList <IExceptionCallback <? super Exception>> m_aExCallbacks = new CallbackList <> ();

  /**
   * Constructor. Registers a logging exception callback by default.
   */
  public AuditEventConsumerPipeline ()
  {
    m_aExCallbacks.add (new LoggingExceptionCallback ());
  }

  /**
   * @return The mutable list of all consumers. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableObject
  public ICommonsList <IAuditEventConsumer> consumers ()
  {
    return m_aConsumers;
  }

  /**
   * @return The mutable exception callback list. Each callback is invoked, if
   *         one of the consumers throws an Exception.
   */
  @NonNull
  @ReturnsMutableObject
  public CallbackList <IExceptionCallback <? super Exception>> exceptionCallbacks ()
  {
    return m_aExCallbacks;
  }

  public void consumeAuditEvent (@NonNull final AuditEvent aAuditEvent)
  {
    for (final IAuditEventConsumer aConsumer : m_aConsumers)
      try
      {
        aConsumer.consumeAuditEvent (aAuditEvent);
      }
      catch (final Exception ex)
      {
        m_aExCallbacks.forEach (x -> x.onException (ex));
      }
  }
}
