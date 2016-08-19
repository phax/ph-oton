/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.exchange.bulkexport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.ToStringGenerator;

/**
 * An implementation of {@link IExportRecordProvider} that uses a constant list
 * of records.
 *
 * @author Philip Helger
 */
@Immutable
public class ConstantExportRecordProvider implements IExportRecordProvider
{
  private final ICommonsList <IExportRecord> m_aHeader = new CommonsArrayList<> ();
  private final ICommonsList <IExportRecord> m_aBody;
  private final ICommonsList <IExportRecord> m_aFooter = new CommonsArrayList<> ();

  public ConstantExportRecordProvider (@Nonnull final Iterable <? extends IExportRecord> aBody)
  {
    this (null, aBody, null);
  }

  public ConstantExportRecordProvider (@Nullable final IExportRecord aHeader,
                                       @Nonnull final Iterable <? extends IExportRecord> aBody)
  {
    this (aHeader, aBody, null);
  }

  public ConstantExportRecordProvider (@Nullable final IExportRecord aHeader,
                                       @Nonnull final Iterable <? extends IExportRecord> aBody,
                                       @Nullable final IExportRecord aFooter)
  {
    ValueEnforcer.notNull (aBody, "Body");
    if (aHeader != null)
      m_aHeader.add (aHeader);
    m_aBody = new CommonsArrayList<> (aBody);
    if (aFooter != null)
      m_aFooter.add (aFooter);
  }

  @Nonnull
  public ICommonsList <? extends IExportRecord> getHeaderRecords ()
  {
    return m_aHeader;
  }

  @Nonnull
  public ICommonsList <? extends IExportRecord> getBodyRecords ()
  {
    return m_aBody;
  }

  @Nonnull
  public ICommonsList <? extends IExportRecord> getFooterRecords ()
  {
    return m_aFooter;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("header", m_aHeader)
                                       .append ("body", m_aBody)
                                       .append ("footer", m_aFooter)
                                       .toString ();
  }
}
