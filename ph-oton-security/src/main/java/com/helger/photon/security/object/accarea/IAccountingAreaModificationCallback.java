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
package com.helger.photon.security.object.accarea;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.photon.basic.object.accarea.IAccountingArea;

/**
 * Callback interface when a accounting area is created, modified or deleted.
 *
 * @author Philip Helger
 */
public interface IAccountingAreaModificationCallback extends ICallback
{
  /**
   * Called after an accounting area was created.
   *
   * @param aAccountingArea
   *        The created accounting area. Never <code>null</code>.
   */
  void onAccountingAreaCreated (@Nonnull IAccountingArea aAccountingArea);

  /**
   * Called after an accounting area was edited fully.
   *
   * @param aAccountingArea
   *        The modified accounting area. Never <code>null</code>.
   */
  void onAccountingAreaUpdated (@Nonnull IAccountingArea aAccountingArea);

  /**
   * Called after an accounting area was deleted.
   *
   * @param aAccountingArea
   *        The deleted accounting area. Never <code>null</code>.
   */
  void onAccountingAreaDeleted (@Nonnull IAccountingArea aAccountingArea);
}
