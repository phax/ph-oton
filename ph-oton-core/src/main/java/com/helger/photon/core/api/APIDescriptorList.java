/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsEnumMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.http.EHttpMethod;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.api.pathdescriptor.PathDescriptorHelper;
import com.helger.photon.core.api.pathdescriptor.PathMatchingResult;

/**
 * This class manages a list of {@link APIDescriptor} objects.
 *
 * @author Philip Helger
 */
public class APIDescriptorList implements Serializable
{
  /** Store APIDescriptor per HTTP method for quick access. */
  private final ICommonsMap <EHttpMethod, ICommonsList <APIDescriptor>> m_aMap = new CommonsEnumMap <> (EHttpMethod.class);

  public APIDescriptorList ()
  {
    // Init map
    for (final EHttpMethod e : EHttpMethod.values ())
      m_aMap.put (e, new CommonsArrayList <> ());
  }

  public void addDescriptor (@Nonnull final APIDescriptor aDescriptor)
  {
    ValueEnforcer.notNull (aDescriptor, "Descriptor");

    // Save for correct HTTP method
    m_aMap.get (aDescriptor.getHTTPMethod ()).add (aDescriptor);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IAPIDescriptor> getAllDescriptors ()
  {
    final ICommonsList <IAPIDescriptor> ret = new CommonsArrayList <> ();
    for (final ICommonsList <APIDescriptor> aList : m_aMap.values ())
      ret.addAll (aList);
    return ret;
  }

  @Nullable
  public InvokableAPIDescriptor getMatching (@Nonnull final APIPath aPath)
  {
    return getMatching (aPath, new LoggingAPIPathAmbiguityResolver ());
  }

  @Nullable
  public InvokableAPIDescriptor getMatching (@Nonnull final APIPath aPath,
                                             @Nonnull final IAPIPathAmbiguityResolver aAmbiguityResolver)
  {
    ValueEnforcer.notNull (aPath, "Path");
    ValueEnforcer.notNull (aAmbiguityResolver, "AmbiguityResolver");

    // Split only once for performance reasons
    final String sSourcePath = aPath.getPath ();
    final ICommonsList <String> aPathParts = PathDescriptorHelper.getCleanPathParts (sSourcePath);

    final ICommonsList <InvokableAPIDescriptor> aMatching = new CommonsArrayList <> ();

    // HTTP Method must match
    for (final APIDescriptor aDescriptor : m_aMap.get (aPath.getHTTPMethod ()))
    {
      final PathMatchingResult aMatchResult = aDescriptor.getPathDescriptor ().matchesParts (aPathParts);
      if (aMatchResult.isMatch ())
      {
        // A match was found
        aMatching.add (new InvokableAPIDescriptor (aDescriptor, sSourcePath, aMatchResult.getAllVariableValues ()));
      }
    }

    // Now get the result
    final int nMatching = aMatching.size ();
    if (nMatching == 1)
    {
      // 1 match - straight forward
      return aMatching.getFirst ();
    }

    // Use the resolver...
    return aAmbiguityResolver.apply (aPath, aMatching);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Map", m_aMap).getToString ();
  }
}
