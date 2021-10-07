/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.photon.audit.v2.domain.AuditEvent;

/**
 * Abstract {@link AuditEvent} handler.
 *
 * @author Philip Helger
 */
public interface IAuditEventConsumer extends Serializable
{
  /**
   * Handle the provided audit event.
   *
   * @param aAuditEvent
   *        The event to be handled. Never <code>null</code>.
   * @throws RuntimeException
   *         in case something goes wrong. This exception will be handled
   *         separately.
   */
  void consumeAuditEvent (@Nonnull AuditEvent aAuditEvent);
}
