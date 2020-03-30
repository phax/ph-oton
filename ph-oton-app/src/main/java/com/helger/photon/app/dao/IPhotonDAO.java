package com.helger.photon.app.dao;

import java.io.Serializable;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.id.IHasID;

public interface IPhotonDAO <INTERFACETYPE extends IHasID <String> & Serializable>
{
  /**
   * @param sID
   *        The object ID to be checked
   * @return <code>true</code> if a object with this ID is contained,
   *         <code>false</code> if not
   */
  boolean containsWithID (@Nullable String sID);

  /**
   * Check if all IDs are contained
   *
   * @param aIDs
   *        IDs to check
   * @return <code>true</code> if all IDs are contained
   */
  boolean containsAllIDs (@Nullable Iterable <String> aIDs);

  /**
   * @return A non-<code>null</code> but maybe empty list of all contained
   *         objects.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <INTERFACETYPE> getAll ();

  /**
   * @param aFilter
   *        The filter to be applied. May not be <code>null</code>.
   * @return A non-<code>null</code> but maybe empty list of all contained
   *         objects.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <INTERFACETYPE> getAll (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  @Nonnegative
  int getCount (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  @Nullable
  INTERFACETYPE findFirst (@Nullable Predicate <? super INTERFACETYPE> aFilter);

  boolean containsAny (@Nullable Predicate <? super INTERFACETYPE> aFilter);
}
