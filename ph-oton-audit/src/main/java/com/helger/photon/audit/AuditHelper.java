/**
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
package com.helger.photon.audit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.DevelopersNote;
import com.helger.commons.annotation.UnsupportedOperation;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.type.ObjectType;
import com.helger.photon.app.mock.MockCurrentUserIDProvider;

/**
 * Simplify system auditing calls.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class AuditHelper
{
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

  private static final IAuditor DEFAULT_AUDITOR = new LoggingAuditor (new MockCurrentUserIDProvider (null), "!DEFAULT-AUDITOR! ");

  // This is the default dummy auditor that should be replaced with something
  // meaningful!
  private static IAuditor s_aAuditor = DEFAULT_AUDITOR;

  private AuditHelper ()
  {}

  @Nonnull
  public static IAuditor getAuditor ()
  {
    return s_aRWLock.readLockedGet ( () -> s_aAuditor);
  }

  /**
   * Set the global auditor to use.
   *
   * @param aAuditor
   *        The auditor to be set. May not be <code>null</code>.
   */
  public static void setAuditor (@Nonnull final IAuditor aAuditor)
  {
    ValueEnforcer.notNull (aAuditor, "Auditor");

    s_aRWLock.writeLockedGet ( () -> s_aAuditor = aAuditor);
  }

  /**
   * Set the default auditor again. This may be helpful when shutting down the
   * main auditor, and at least want some prove, when something auditible
   * happens.
   */
  public static void setDefaultAuditor ()
  {
    setAuditor (DEFAULT_AUDITOR);
  }

  public static void onAuditCreateSuccess (@Nonnull final ObjectType aObjectType)
  {
    getAuditor ().onCreateSuccess (aObjectType);
  }

  public static void onAuditCreateSuccess (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onCreateSuccess (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditCreateFailure (@Nonnull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditCreateFailure (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onCreateFailure (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditModifySuccess (@Nonnull final ObjectType aObjectType, @Nonnull final String sWhat)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditModifySuccess (@Nonnull final ObjectType aObjectType,
                                           @Nonnull final String sWhat,
                                           @Nullable final Object... aArgs)
  {
    getAuditor ().onModifySuccess (aObjectType, sWhat, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditModifyFailure (@Nonnull final ObjectType aObjectType, @Nonnull final String sWhat)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditModifyFailure (@Nonnull final ObjectType aObjectType,
                                           @Nonnull final String sWhat,
                                           @Nullable final Object... aArgs)
  {
    getAuditor ().onModifyFailure (aObjectType, sWhat, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditDeleteSuccess (@Nonnull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditDeleteSuccess (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onDeleteSuccess (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditDeleteFailure (@Nonnull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditDeleteFailure (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onDeleteFailure (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditUndeleteSuccess (@Nonnull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditUndeleteSuccess (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onUndeleteSuccess (aObjectType, aArgs);
  }

  @SuppressWarnings ("unused")
  @Deprecated
  @UnsupportedOperation
  @DevelopersNote ("Use the version with parameters!")
  public static void onAuditUndeleteFailure (@Nonnull final ObjectType aObjectType)
  {
    throw new UnsupportedOperationException ();
  }

  public static void onAuditUndeleteFailure (@Nonnull final ObjectType aObjectType, @Nullable final Object... aArgs)
  {
    getAuditor ().onUndeleteFailure (aObjectType, aArgs);
  }

  public static void onAuditExecuteSuccess (@Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteSuccess (sWhat, aArgs);
  }

  public static void onAuditExecuteFailure (@Nonnull final String sWhat, @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteFailure (sWhat, aArgs);
  }

  public static void onAuditExecuteSuccess (@Nonnull final ObjectType aObjectType,
                                            @Nonnull final String sWhat,
                                            @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteSuccess (aObjectType, sWhat, aArgs);
  }

  public static void onAuditExecuteFailure (@Nonnull final ObjectType aObjectType,
                                            @Nonnull final String sWhat,
                                            @Nullable final Object... aArgs)
  {
    getAuditor ().onExecuteFailure (aObjectType, sWhat, aArgs);
  }
}
