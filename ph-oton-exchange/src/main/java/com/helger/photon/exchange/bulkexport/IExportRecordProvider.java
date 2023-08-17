/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.exchange.bulkexport;

import java.io.Serializable;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 * The main record provider. Major rewrite in 7.0.4
 *
 * @author Philip Helger
 */
public interface IExportRecordProvider extends Serializable
{
  /**
   * @param aConsumer
   *        The consumer invoked for each header record. May not be
   *        <code>null</code>.
   */
  default void forEachHeaderRecord (@Nonnull final Consumer <? super IExportRecord> aConsumer)
  {}

  /**
   * @param aConsumer
   *        The consumer invoked for each body record. May not be
   *        <code>null</code>.
   */
  default void forEachBodyRecord (@Nonnull final Consumer <? super IExportRecord> aConsumer)
  {}

  /**
   * @param aConsumer
   *        The consumer invoked for each footer record. May not be
   *        <code>null</code>.
   */
  default void forEachFooterRecord (@Nonnull final Consumer <? super IExportRecord> aConsumer)
  {}
}
