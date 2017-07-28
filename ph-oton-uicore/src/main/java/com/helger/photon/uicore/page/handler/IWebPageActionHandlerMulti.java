/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page.handler;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.id.IHasID;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Interface for handling multi object actions.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type of the object to be handled.
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public interface IWebPageActionHandlerMulti <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                            extends IWebPageActionHandler <DATATYPE, WPECTYPE>
{
  /**
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @return A list of all selected objects. May neither be <code>null</code>
   *         nor empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <DATATYPE> getAllSelectedObjects (@Nonnull WPECTYPE aWPEC);

  /**
   * This is the main entry to action handling. This method is only called if
   * the passed action is provided and if the preconditions are met.
   *
   * @param aWPEC
   *        Web page execution context. Never <code>null</code>.
   * @param aSelectedObjects
   *        Selected objects. May not be <code>null</code>.
   * @return <code>true</code> to show the list of all objects afterwards,
   *         <code>false</code> to not do so.
   */
  boolean handleMultiAction (@Nonnull WPECTYPE aWPEC, @Nonnull ICommonsList <DATATYPE> aSelectedObjects);
}
