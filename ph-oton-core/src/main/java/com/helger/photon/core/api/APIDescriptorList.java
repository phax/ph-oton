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
package com.helger.photon.core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.api.path.PathDescriptorHelper;
import com.helger.photon.core.api.path.PathMatchingResult;
import com.helger.web.http.EHTTPMethod;

/**
 * This class manages a list of {@link APIDescriptor} objects.
 *
 * @author Philip Helger
 */
public class APIDescriptorList
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (APIDescriptorList.class);

  private final List <APIDescriptor> m_aList = new ArrayList <> ();

  public APIDescriptorList ()
  {}

  public void addDescriptor (@Nonnull final APIDescriptor aDescriptor)
  {
    ValueEnforcer.notNull (aDescriptor, "Descriptor");
    m_aList.add (aDescriptor);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <? extends IAPIDescriptor> getAllDescriptors ()
  {
    return CollectionHelper.newList (m_aList);
  }

  @Nullable
  public InvokableAPIDescriptor getMatching (@Nonnull final String sPath, @Nonnull final EHTTPMethod eHTTPMethod)
  {
    return getMatching (sPath, eHTTPMethod, aDescriptors -> {
      if (!aDescriptors.isEmpty ())
        s_aLogger.warn ("Found more than one API descriptor matching path '" + sPath + "': " + aDescriptors);
      return null;
    });
  }

  @Nullable
  public InvokableAPIDescriptor getMatching (@Nonnull @Nonempty final String sPath,
                                             @Nonnull final EHTTPMethod eHTTPMethod,
                                             @Nonnull final Function <List <InvokableAPIDescriptor>, InvokableAPIDescriptor> aAmbiguityResolver)
  {
    ValueEnforcer.notEmpty (sPath, "Path");

    // Split only once for performance reasons
    final List <String> aPathParts = PathDescriptorHelper.getCleanPathParts (sPath);

    final List <InvokableAPIDescriptor> aMatching = new ArrayList <> ();
    for (final APIDescriptor aDescriptor : m_aList)
      if (aDescriptor.getHTTPMethod ().equals (eHTTPMethod))
      {
        final PathMatchingResult aMatchResult = aDescriptor.getPath ().matchesParts (aPathParts);
        if (aMatchResult.isMatch ())
          aMatching.add (new InvokableAPIDescriptor (sPath, aDescriptor, aMatchResult.getAllVariableValues ()));
      }

    // Now get the result
    final int nMatching = aMatching.size ();
    if (nMatching == 1)
    {
      // 1 match - straight forward
      return aMatching.get (0);
    }

    // Use the resolver...
    return aAmbiguityResolver.apply (aMatching);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("List", m_aList).toString ();
  }
}
