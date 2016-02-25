package com.helger.photon.basic.app.dao.impl;

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.commons.id.IHasID;

public interface IDAOChangeCallback <INTERFACETYPE extends IHasID <String> & Serializable> extends ICallback
{
  /**
   * Called after a new item was created.
   *
   * @param sID
   *        The ID of the created item. Never <code>null</code> nor empty.
   * @param aNewItem
   *        The newly created item. Never <code>null</code>.
   */
  void onCreateItem (@Nonnull INTERFACETYPE aNewItem);

  /**
   * Called after an item was updated.
   *
   * @param aItem
   *        The updated item. Never <code>null</code>.
   */
  void onUpdateItem (@Nonnull INTERFACETYPE aItem);

  /**
   * Called after an item was removed.
   *
   * @param aItem
   *        The removed item. Never <code>null</code>.
   */
  void onDeleteItem (@Nonnull INTERFACETYPE aItem);

  /**
   * Called after an item was marked as deleted (but is still present - special
   * case of update!).
   *
   * @param aItem
   *        The item that was marked as deleted. Never <code>null</code>.
   */
  void onMarkItemDeleted (@Nonnull INTERFACETYPE aItem);
}
