/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.dao;

import java.io.InputStream;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.state.EChange;

/**
 * The external data provider for a DAO
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IDAODataProvider
{
  /**
   * @return The name of the character set to use. May not be <code>null</code>.
   */
  @Nonnull
  Charset getCharset ();

  /**
   * Prepare the data structure for an initial save. This is only called if no
   * file was found to be read from.
   *
   * @return {@link EChange#CHANGED} if reading the document changed the content
   *         and so a call to the save action should be performed. Never
   *         <code>null</code>.
   * @throws Exception
   *         Any error that may occur on initialization.
   */
  @Nonnull
  EChange initForFirstTimeUsage () throws Exception;

  /**
   * Initially fill the DAO from an input stream. This is only called if the
   * underlying data is resolvable.
   *
   * @param aIS
   *        The input stream to read from. Is never <code>null</code>.
   * @return {@link EChange#CHANGED} if reading the document changed the content
   *         and so a call to the save action should be performed. Never
   *         <code>null</code>.
   * @throws Exception
   *         In case something goes wrong.
   */
  @Nonnull
  EChange readFromStream (@Nonnull InputStream aIS) throws Exception;

  /**
   * Is called to fill the data to be written to the file. Just add your text to
   * the passed StringBuilder.
   *
   * @param aSB
   *        The string builder to be filled. Is never <code>null</code>.
   * @throws Exception
   *         In case something goes wrong.
   */
  void fillStringBuilderForSaving (@Nonnull StringBuilder aSB) throws Exception;

  /**
   * Perform an additional check to ensure that only valid data is saved. It is
   * called with the data previously created via
   * {@link #fillStringBuilderForSaving(StringBuilder)}.
   *
   * @param sContent
   *        The content that would be written to the file.
   * @return <code>true</code> if the content is valid, <code>false</code> if
   *         the content is invalid and should not be saved.
   */
  boolean isContentValidForSaving (@Nullable String sContent);
}
