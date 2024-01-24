/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.security.token.object;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.tenancy.AbstractBusinessObject;
import com.helger.tenancy.IBusinessObject;

/**
 * Abstract base implementation if {@link IObjectWithAccessToken}.
 *
 * @author Philip Helger
 */
public abstract class AbstractObjectWithAccessToken extends AbstractBusinessObject implements IObjectWithAccessToken
{
  private final AccessTokenList m_aAccessTokenList;

  public AbstractObjectWithAccessToken (@Nonnull final IBusinessObject aStubObject,
                                        @Nonnull @Nonempty final List <AccessToken> aAccessTokens)
  {
    super (aStubObject);
    ValueEnforcer.notEmptyNoNullValue (aAccessTokens, "AccessTokens");
    m_aAccessTokenList = new AccessTokenList (aAccessTokens);
  }

  @Nonnull
  @ReturnsMutableObject
  public AccessTokenList getAccessTokenList ()
  {
    return m_aAccessTokenList;
  }

  @Override
  public boolean equals (final Object o)
  {
    // New fields, no changes
    return super.equals (o);
  }

  @Override
  public int hashCode ()
  {
    // New fields, no changes
    return super.hashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("AccessTokenList", m_aAccessTokenList)
                            .getToString ();
  }
}
