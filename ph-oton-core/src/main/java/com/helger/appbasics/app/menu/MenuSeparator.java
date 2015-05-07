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
package com.helger.appbasics.app.menu;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.idfactory.GlobalIDFactory;

/**
 * Default implementation of the {@link IMenuSeparator} interface.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MenuSeparator extends AbstractMenuObject <MenuSeparator> implements IMenuSeparator
{
  public MenuSeparator ()
  {
    this (GlobalIDFactory.getNewStringID ());
  }

  public MenuSeparator (@Nonnull @Nonempty final String sID)
  {
    super (sID);
  }

  @Nonnull
  public final EMenuObjectType getMenuObjectType ()
  {
    return EMenuObjectType.SEPARATOR;
  }
}
