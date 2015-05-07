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
package com.helger.appbasics.migration;

import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.compare.AbstractIntegerComparator;
import com.helger.commons.compare.ESortOrder;

/**
 * Comparator for {@link SystemMigrationResult} objects by execution time.
 * 
 * @author Philip Helger
 */
public class ComparatorSystemMigrationResultExecutionDate extends AbstractIntegerComparator <SystemMigrationResult>
{
  public ComparatorSystemMigrationResultExecutionDate ()
  {
    super ();
  }

  /**
   * Compare with a special order.
   *
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   */
  public ComparatorSystemMigrationResultExecutionDate (@Nonnull final ESortOrder eSortOrder)
  {
    super (eSortOrder);
  }

  /**
   * Comparator with default sort order and a nested comparator.
   *
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorSystemMigrationResultExecutionDate (@Nullable final Comparator <? super SystemMigrationResult> aNestedComparator)
  {
    super (aNestedComparator);
  }

  /**
   * Comparator with sort order and a nested comparator.
   *
   * @param eSortOrder
   *        The sort order to use. May not be <code>null</code>.
   * @param aNestedComparator
   *        The nested comparator to be invoked, when the main comparison
   *        resulted in 0.
   */
  public ComparatorSystemMigrationResultExecutionDate (@Nonnull final ESortOrder eSortOrder,
                                                       @Nullable final Comparator <? super SystemMigrationResult> aNestedComparator)
  {
    super (eSortOrder, aNestedComparator);
  }

  @Override
  protected long asLong (@Nonnull final SystemMigrationResult aObject)
  {
    return aObject.getExecutionDateTime ().getMillis ();
  }
}
