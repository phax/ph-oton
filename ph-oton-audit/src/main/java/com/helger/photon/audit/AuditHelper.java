/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.misc.DevelopersNote;
import com.helger.annotation.style.UnsupportedOperation;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.type.ObjectType;

/**
 * Simplify system auditing calls.<br>
 * For each predefined action (see {@link EAuditActionType} - CREATE, MODIFY, DELETE, UNDELETE,
 * EXECUTE) this class provided static helpers methods.<br>
 * For MODIFY actions, the performed action (like "set-name") should always be first.<br>
 * Error details should always go last.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class AuditHelper
{
  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();

  private static final IAuditor DEFAULT_AUDITOR = new LoggingAuditor ( () -> "dummyUserID", "!DEFAULT-AUDITOR! ");

  // This is the default dummy auditor that should be replaced with something
  // meaningful!
  private static IAuditor s_aAuditor = DEFAULT_AUDITOR;

  private AuditHelper ()
  {}

  @NonNull
  public static IAuditor getAuditor ()
  {
    return RW_LOCK.readLockedGet ( () -> s_aAuditor);
  }

  /**
   * @return <code>true</code> if the current Auditor is the default auditor.
   * @since 8.3.2
   */
  public static boolean isDefaultAuditorSet ()
  {
    return RW_LOCK.readLockedBoolean ( () -> EqualsHelper.identityEqual (s_aAuditor, DEFAULT_AUDITOR));
  }

  /**
   * Set the global auditor to use.
   *
   * @param aAuditor
   *        The auditor to be set. May not be <code>null</code>.
   */
  public static void setAuditor (@NonNull final IAuditor aAuditor)
  {
    ValueEnforcer.notNull (aAuditor, "Auditor");

    RW_LOCK.writeLocked ( () -> s_aAuditor = aAuditor);
  }

  /**
   * Set the default auditor again. This may be helpful when shutting down the main auditor, and at
   * least want some prove, when something auditible happens.
   */
  public static void setDefaultAuditor ()
  {
    setAuditor (DEFAULT_AUDITOR);
  }

  public static void onAuditCreateSuccess (@NonNull final ObjectType aObjectType)
  {
    getAuditor ().onCreateSuccess (aObjectType);
  }

  public static void onAuditCreateSuccess (@NonNull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onCreateSuccess (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated (forRemoval = false)
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditCreateFailure (@NonNull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditCreateFailure (@NonNull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onCreateFailure (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated (forRemoval = false)
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditModifySuccess (@NonNull final ObjectType aObjectType, @NonNull final String sWhat)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditModifySuccess (@NonNull final ObjectType aObjectType,
                                           @NonNull final String sWhat,
                                           @Nullable final Object... aArgs)
  {
    getAuditor ().onModifySuccess (aObjectType, sWhat, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated (forRemoval = false)
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditModifyFailure (@NonNull final ObjectType aObjectType, @NonNull final String sWhat)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditModifyFailure (@NonNull final ObjectType aObjectType,
                                           @NonNull final String sWhat,
                                           @Nullable final Object... aArgs)
  {
    getAuditor ().onModifyFailure (aObjectType, sWhat, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated (forRemoval = false)
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditDeleteSuccess (@NonNull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditDeleteSuccess (@NonNull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onDeleteSuccess (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated (forRemoval = false)
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditDeleteFailure (@NonNull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditDeleteFailure (@NonNull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onDeleteFailure (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated (forRemoval = false)
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditUndeleteSuccess (@NonNull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditUndeleteSuccess (@NonNull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onUndeleteSuccess (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated (forRemoval = false)
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditUndeleteFailure (@NonNull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditUndeleteFailure (@NonNull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onUndeleteFailure (aObjectType, aArgs);
  }

  public static void onAuditExecuteSuccess (@NonNull final String sWhat, @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteSuccess (sWhat, aArgs);
  }

  public static void onAuditExecuteFailure (@NonNull final String sWhat, @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteFailure (sWhat, aArgs);
  }

  public static void onAuditExecuteSuccess (@NonNull final ObjectType aObjectType,
                                            @NonNull final String sWhat,
                                            @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteSuccess (aObjectType, sWhat, aArgs);
  }

  public static void onAuditExecuteFailure (@NonNull final ObjectType aObjectType,
                                            @NonNull final String sWhat,
                                            @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteFailure (aObjectType, sWhat, aArgs);
  }
}
