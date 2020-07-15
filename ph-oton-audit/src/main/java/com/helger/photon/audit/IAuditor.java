/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.audit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.state.ESuccess;
import com.helger.commons.type.ObjectType;

/**
 * Main interface for an auditing service. Not serializable.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IAuditor
{
  /**
   * Create a new audit item.
   *
   * @param eActionType
   *        Action type. May not be <code>null</code>.
   * @param eSuccess
   *        Success or failure? May not be <code>null</code>.
   * @param aActionObjectType
   *        Action object type. May be <code>null</code> if sAction is set.
   * @param sAction
   *        The performed action. May be <code>null</code> if aActionObjectType
   *        is set.
   * @param aArgs
   *        An optional array of arguments. May be <code>null</code> or empty.
   */
  void createAuditItem (@Nonnull EAuditActionType eActionType,
                        @Nonnull ESuccess eSuccess,
                        @Nullable ObjectType aActionObjectType,
                        @Nullable final String sAction,
                        @Nullable final Object... aArgs);

  /**
   * The creation of an object succeeded.
   *
   * @param aObjectType
   *        The object type
   * @param aArgs
   *        Additional arguments
   */
  default void onCreateSuccess (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.CREATE, ESuccess.SUCCESS, aObjectType, null, aArgs);
  }

  /**
   * The creation of an object failed.
   *
   * @param aObjectType
   *        The object type
   * @param aArgs
   *        Additional arguments
   */
  default void onCreateFailure (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.CREATE, ESuccess.FAILURE, aObjectType, null, aArgs);
  }

  /**
   * The modification of an object succeeded.
   *
   * @param aObjectType
   *        The object type
   * @param sWhat
   *        What was modified?
   * @param aArgs
   *        Additional arguments
   */
  default void onModifySuccess (@Nonnull final ObjectType aObjectType, @Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.MODIFY,
                     ESuccess.SUCCESS,
                     aObjectType,
                     null,
                     ArrayHelper.getConcatenated (sWhat, aArgs, Object.class));
  }

  /**
   * The modification of an object failed.
   *
   * @param aObjectType
   *        The object type
   * @param sWhat
   *        What was modified?
   * @param aArgs
   *        Additional arguments
   */
  default void onModifyFailure (@Nonnull final ObjectType aObjectType, @Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.MODIFY,
                     ESuccess.FAILURE,
                     aObjectType,
                     null,
                     ArrayHelper.getConcatenated (sWhat, aArgs, Object.class));
  }

  /**
   * The deletion of an object succeeded.
   *
   * @param aObjectType
   *        The object type
   * @param aArgs
   *        Additional arguments
   */
  default void onDeleteSuccess (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.DELETE, ESuccess.SUCCESS, aObjectType, null, aArgs);
  }

  /**
   * The deletion of an object failed.
   *
   * @param aObjectType
   *        The object type
   * @param aArgs
   *        Additional arguments
   */
  default void onDeleteFailure (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.DELETE, ESuccess.FAILURE, aObjectType, null, aArgs);
  }

  /**
   * The undeletion of an object succeeded.
   *
   * @param aObjectType
   *        The object type
   * @param aArgs
   *        Additional arguments
   */
  default void onUndeleteSuccess (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.UNDELETE, ESuccess.SUCCESS, aObjectType, null, aArgs);
  }

  /**
   * The undeletion of an object failed.
   *
   * @param aObjectType
   *        The object type
   * @param aArgs
   *        Additional arguments
   */
  default void onUndeleteFailure (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.UNDELETE, ESuccess.FAILURE, aObjectType, null, aArgs);
  }

  /**
   * The execution of something succeeded.
   *
   * @param sWhat
   *        What has been executed?
   * @param aArgs
   *        Additional arguments
   */
  default void onExecuteSuccess (@Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    createAuditItem (EAuditActionType.EXECUTE, ESuccess.SUCCESS, null, sWhat, aArgs);
  }

  /**
   * The execution of something failed.
   *
   * @param sWhat
   *        What has been executed?
   * @param aArgs
   *        Additional arguments
   */
  default void onExecuteFailure (@Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    createAuditItem (EAuditActionType.EXECUTE, ESuccess.FAILURE, null, sWhat, aArgs);
  }

  /**
   * The execution of something on an object succeeded.
   *
   * @param aObjectType
   *        The object type
   * @param sWhat
   *        What has been executed?
   * @param aArgs
   *        Additional arguments
   */
  default void onExecuteSuccess (@Nonnull final ObjectType aObjectType, @Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.EXECUTE,
                     ESuccess.SUCCESS,
                     aObjectType,
                     null,
                     ArrayHelper.getConcatenated (sWhat, aArgs, Object.class));
  }

  /**
   * The execution of something on an object failed.
   *
   * @param aObjectType
   *        The object type
   * @param sWhat
   *        What has been executed?
   * @param aArgs
   *        Additional arguments
   */
  default void onExecuteFailure (@Nonnull final ObjectType aObjectType, @Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    ValueEnforcer.notNull (aObjectType, "ObjectType");
    createAuditItem (EAuditActionType.EXECUTE,
                     ESuccess.FAILURE,
                     aObjectType,
                     null,
                     ArrayHelper.getConcatenated (sWhat, aArgs, Object.class));
  }
}
