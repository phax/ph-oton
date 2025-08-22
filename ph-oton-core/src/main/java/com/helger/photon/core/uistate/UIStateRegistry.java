/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.uistate;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.reflection.GenericReflection;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.base.type.ObjectType;
import com.helger.cache.regex.RegExHelper;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.scope.IScopeRenewalAware;
import com.helger.web.scope.session.ISessionWebScopeDontPassivate;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Per session registry for UI control state data.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class UIStateRegistry extends AbstractSessionWebSingleton implements
                                   IScopeRenewalAware,
                                   ISessionWebScopeDontPassivate
{
  /** ObjectType */
  public static final ObjectType OT_HCNODE = new ObjectType ("hcnode");
  private static final Logger LOGGER = LoggerFactory.getLogger (UIStateRegistry.class);

  private final ICommonsMap <ObjectType, ICommonsMap <String, IHasUIState>> m_aMap = new CommonsHashMap <> ();

  @UsedViaReflection
  @Deprecated (forRemoval = false)
  public UIStateRegistry ()
  {}

  @Nonnull
  public static UIStateRegistry getCurrent ()
  {
    return getSessionSingleton (UIStateRegistry.class);
  }

  /**
   * Get the state with the passed ID for the current session. In case the state is a
   * {@link UIStateWrapper} instance, it is returned as is.
   *
   * @param aOT
   *        Object type
   * @param sStateID
   *        The state ID to be searched
   * @return the {@link IHasUIState} for the specified control ID, if already registered or
   *         <code>null</code>
   */
  @Nullable
  public IHasUIState getState (@Nullable final ObjectType aOT, @Nullable final String sStateID)
  {
    if (aOT == null)
      return null;
    if (StringHelper.isEmpty (sStateID))
      return null;

    return m_aRWLock.readLockedGet ( () -> {
      IHasUIState ret = null;

      // Get mapping for requested ObjectType
      final Map <String, IHasUIState> aMap = m_aMap.get (aOT);
      if (aMap != null)
      {
        // Lookup control ID for this object type
        ret = aMap.get (sStateID);
        if (ret == null)
        {
          // Try regular expressions (required for auto suggests in ebiz)
          for (final Map.Entry <String, IHasUIState> aEntry : aMap.entrySet ())
            if (RegExHelper.stringMatchesPattern (aEntry.getKey (), sStateID))
            {
              ret = aEntry.getValue ();
              break;
            }
        }
      }
      return ret;
    });
  }

  /**
   * Get the state object in the specified type. If the saved state is a {@link UIStateWrapper}
   * instance, the contained value is returned!
   *
   * @param aOT
   *        The ObjectType to be resolved. May be <code>null</code>.
   * @param sStateID
   *        The state ID to be resolved.
   * @return <code>null</code> if no such object was found.
   * @param <T>
   *        Return type
   */
  @Nullable
  public <T> T getCastedState (@Nullable final ObjectType aOT, @Nullable final String sStateID)
  {
    final IHasUIState aObject = getState (aOT, sStateID);
    if (aObject == null)
      return null;

    // Special handling for UI state wrapper to retrieve the contained object
    if (aObject instanceof UIStateWrapper <?>)
      return ((UIStateWrapper <?>) aObject).<T> getCastedObject ();

    // Regular cast
    return GenericReflection.uncheckedCast (aObject);
  }

  /**
   * Registers a new control for the passed tree ID
   *
   * @param sStateID
   *        the ID of the state in register. May neither be <code>null</code> nor empty.
   * @param aNewState
   *        The state to set. May not be <code>null</code>.
   * @return {@link EChange#CHANGED} if the control was registered<br>
   *         {@link EChange#UNCHANGED} if an illegal argument was passed or a control has already
   *         been registered for that ID
   */
  @Nonnull
  public EChange registerState (@Nonnull @Nonempty final String sStateID, @Nonnull final IHasUIState aNewState)
  {
    ValueEnforcer.notEmpty (sStateID, "StateID");
    ValueEnforcer.notNull (aNewState, "NewState");

    final ObjectType aOT = aNewState.getObjectType ();
    if (aOT == null)
      throw new IllegalStateException ("Object has no typeID: " + aNewState);

    return m_aRWLock.writeLockedGet ( () -> {
      final Map <String, IHasUIState> aMap = m_aMap.computeIfAbsent (aOT, k -> new CommonsHashMap <> ());

      if (LOGGER.isDebugEnabled () && aMap.containsKey (sStateID))
        LOGGER.debug ("Overwriting " + aOT.getName () + " with ID " + sStateID + " with new object");

      aMap.put (sStateID, aNewState);
      return EChange.CHANGED;
    });
  }

  /**
   * Register a state for the passed HC element, using the internal ID of the element.
   *
   * @param aNewElement
   *        The element to be added to the registry. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public EChange registerState (@Nonnull final IHCElement <?> aNewElement)
  {
    ValueEnforcer.notNull (aNewElement, "NewElement");
    if (aNewElement.hasNoID ())
      LOGGER.warn ("Registering the state for an object that has no ID - creating a new ID now!");

    return registerState (aNewElement.ensureID ().getID (), aNewElement);
  }

  @Nonnull
  public EChange registerState (@Nonnull @Nonempty final String sStateID, @Nonnull final IHCNode aNewNode)
  {
    ValueEnforcer.notNull (aNewNode, "NewNode");

    return registerState (sStateID, UIStateWrapper.create (OT_HCNODE, aNewNode));
  }

  @Nonnull
  public EChange removeState (@Nullable final ObjectType aObjectType, @Nonnull @Nonempty final String sStateID)
  {
    ValueEnforcer.notEmpty (sStateID, "StateID");

    return m_aRWLock.writeLockedGet ( () -> {
      final ICommonsMap <String, IHasUIState> aMap = m_aMap.get (aObjectType);
      if (aMap == null)
        return EChange.UNCHANGED;
      return aMap.removeObject (sStateID);
    });
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final UIStateRegistry rhs = (UIStateRegistry) o;
    return m_aMap.equals (rhs.m_aMap);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aMap).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("map", m_aMap).getToString ();
  }
}
