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
package com.helger.photon.core.execcontext;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.security.user.IUser;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * This object is instantiated per page view and contains the current request
 * scope, the display locale and a set of custom attributes.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class SimpleWebExecutionContext implements ISimpleWebExecutionContext
{
  private final IRequestWebScopeWithoutResponse m_aRequestScope;
  private final Locale m_aDisplayLocale;
  private final IMenuTree m_aMenuTree;
  private final IUser m_aLoggedInUser;
  private final boolean m_bIsLoggedInUserAdministrator;

  public SimpleWebExecutionContext (@Nonnull final ISimpleWebExecutionContext aSWEC)
  {
    this (aSWEC.getRequestScope (), aSWEC.getDisplayLocale (), aSWEC.getMenuTree (), aSWEC.getLoggedInUser ());
  }

  public SimpleWebExecutionContext (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final Locale aDisplayLocale,
                                    @Nonnull final IMenuTree aMenuTree,
                                    @Nullable final IUser aLoggedInUser)
  {
    m_aRequestScope = ValueEnforcer.notNull (aRequestScope, "RequestScope");
    m_aDisplayLocale = ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    m_aMenuTree = ValueEnforcer.notNull (aMenuTree, "MenuTree");
    m_aLoggedInUser = aLoggedInUser;
    m_bIsLoggedInUserAdministrator = aLoggedInUser != null && aLoggedInUser.isAdministrator ();
  }

  @Nonnull
  public final IRequestWebScopeWithoutResponse getRequestScope ()
  {
    return m_aRequestScope;
  }

  @Nonnull
  public final Locale getDisplayLocale ()
  {
    return m_aDisplayLocale;
  }

  @Nonnull
  public final IMenuTree getMenuTree ()
  {
    return m_aMenuTree;
  }

  @Nullable
  public final IUser getLoggedInUser ()
  {
    return m_aLoggedInUser;
  }

  public final boolean isLoggedInUserAdministrator ()
  {
    return m_bIsLoggedInUserAdministrator;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("RequestURL", m_aRequestScope.getURLEncoded ())
                                       .append ("DisplayLocale", m_aDisplayLocale)
                                       .append ("MenuTree#", m_aMenuTree.getItemCount ())
                                       .append ("LoggedInUserID", getLoggedInUserID ())
                                       .append ("LoggedInUserAdministrator", m_bIsLoggedInUserAdministrator)
                                       .getToString ();
  }
}
