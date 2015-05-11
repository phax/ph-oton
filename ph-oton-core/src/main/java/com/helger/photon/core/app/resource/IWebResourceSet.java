package com.helger.photon.core.app.resource;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.state.EChange;

/**
 * Base interface for CSS and JS resource sets.
 * 
 * @author Philip Helger
 * @param <T>
 *        The contained data type.
 */
public interface IWebResourceSet <T> extends Serializable, Iterable <T>
{
  /**
   * Add an item
   *
   * @param aItem
   *        The item to add. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange addItem (@Nonnull T aItem);

  /**
   * Add all items from another list.
   *
   * @param aItems
   *        The items to be added. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange addItems (@Nonnull IWebResourceSet <? extends T> aItems);

  /**
   * Unregister an existing item
   *
   * @param aItem
   *        The item to be removed. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange removeItem (@Nonnull T aItem);

  /**
   * Remove all items.
   *
   * @return {@link EChange}
   */
  @Nonnull
  EChange removeAll ();

  /**
   * @return A non-<code>null</code> set with all items. Order is ensured using
   *         LinkedHashSet.
   */
  @Nonnull
  @ReturnsMutableCopy
  Set <T> getAllItems ();

  /**
   * Add all items to the provided target container.
   *
   * @param aTarget
   *        The container to add the items to. May not be <code>null</code>.
   */
  void getAllItems (@Nonnull Collection <? super T> aTarget);

  /**
   * @return <code>true</code> if no item is contained
   */
  boolean isEmpty ();

  /**
   * @return <code>true</code> if at least a single item is contained
   */
  boolean isNotEmpty ();

  /**
   * @return The number of contained items. Always &ge; 0.
   */
  int getCount ();
}
