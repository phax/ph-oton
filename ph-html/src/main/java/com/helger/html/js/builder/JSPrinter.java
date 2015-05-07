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
package com.helger.html.js.builder;

import java.io.Writer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.streams.NonBlockingStringWriter;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.html.js.writer.IJSWriterSettings;

@Immutable
public final class JSPrinter
{
  private JSPrinter ()
  {}

  public static void writeGeneratable (@Nonnull @WillClose final Writer aWriter,
                                       @Nullable final IJSWriterSettings aSettings,
                                       @Nonnull final IJSGeneratable aGeneratable)
  {
    ValueEnforcer.notNull (aGeneratable, "Generatable");
    final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings);
    try
    {
      aFormatter.generatable (aGeneratable);
    }
    finally
    {
      StreamUtils.close (aFormatter);
    }
  }

  public static void writeDeclaration (@Nonnull @WillClose final Writer aWriter,
                                       @Nullable final IJSWriterSettings aSettings,
                                       @Nonnull final IJSDeclaration aDeclaration)
  {
    ValueEnforcer.notNull (aDeclaration, "Declaration");
    final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings);
    try
    {
      aFormatter.decl (aDeclaration);
    }
    finally
    {
      StreamUtils.close (aFormatter);
    }
  }

  public static void writeStatement (@Nonnull @WillClose final Writer aWriter,
                                     @Nullable final IJSWriterSettings aSettings,
                                     @Nonnull final IJSStatement aStatement)
  {
    ValueEnforcer.notNull (aStatement, "Statement");
    final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings);
    try
    {
      aFormatter.stmt (aStatement);
    }
    finally
    {
      StreamUtils.close (aFormatter);
    }
  }

  public static void writePackage (@Nonnull @WillClose final Writer aWriter,
                                   @Nullable final IJSWriterSettings aSettings,
                                   @Nonnull final JSPackage aPackage)
  {
    ValueEnforcer.notNull (aPackage, "Package");
    final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings);
    try
    {
      aFormatter.pkg (aPackage);
    }
    finally
    {
      StreamUtils.close (aFormatter);
    }
  }

  @Nonnull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings,
                                    @Nonnull final IJSGeneratable aGeneratable)
  {
    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writeGeneratable (aSW, aSettings, aGeneratable);
    return aSW.getAsString ().trim ();
  }

  @Nonnull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings, @Nonnull final IJSDeclaration aDecl)
  {
    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writeDeclaration (aSW, aSettings, aDecl);
    return aSW.getAsString ().trim ();
  }

  @Nonnull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings,
                                    @Nonnull final IJSStatement aStatement)
  {
    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writeStatement (aSW, aSettings, aStatement);
    return aSW.getAsString ().trim ();
  }

  @Nonnull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings, @Nonnull final JSPackage aPackage)
  {
    ValueEnforcer.notNull (aPackage, "Package");
    if (aPackage.memberCount () == 0)
      return "";

    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writePackage (aSW, aSettings, aPackage);
    return aSW.getAsString ().trim ();
  }
}
