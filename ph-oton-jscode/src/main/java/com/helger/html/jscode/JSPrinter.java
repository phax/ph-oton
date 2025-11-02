/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.jscode;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.WillClose;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.io.nonblocking.NonBlockingStringWriter;
import com.helger.html.js.IJSWriterSettings;

@Immutable
public final class JSPrinter
{
  private JSPrinter ()
  {}

  public static void writeGeneratable (@NonNull @WillClose final Writer aWriter,
                                       @Nullable final IJSWriterSettings aSettings,
                                       @NonNull final IJSGeneratable aGeneratable)
  {
    ValueEnforcer.notNull (aGeneratable, "Generatable");
    try (final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings))
    {
      aFormatter.generatable (aGeneratable);
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  public static void writeDeclaration (@NonNull @WillClose final Writer aWriter,
                                       @Nullable final IJSWriterSettings aSettings,
                                       @NonNull final IJSDeclaration aDeclaration)
  {
    ValueEnforcer.notNull (aDeclaration, "Declaration");
    try (final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings))
    {
      aFormatter.decl (aDeclaration);
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  public static void writeStatement (@NonNull @WillClose final Writer aWriter,
                                     @Nullable final IJSWriterSettings aSettings,
                                     @NonNull final IJSStatement aStatement)
  {
    ValueEnforcer.notNull (aStatement, "Statement");
    try (final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings))
    {
      aFormatter.stmt (aStatement);
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  public static void writePackage (@NonNull @WillClose final Writer aWriter,
                                   @Nullable final IJSWriterSettings aSettings,
                                   @NonNull final JSPackage aPackage)
  {
    ValueEnforcer.notNull (aPackage, "Package");
    try (final JSFormatter aFormatter = new JSFormatter (aWriter, aSettings))
    {
      aFormatter.pkg (aPackage);
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  @NonNull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings, @NonNull final IJSGeneratable aGeneratable)
  {
    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writeGeneratable (aSW, aSettings, aGeneratable);
    return aSW.getAsString ().trim ();
  }

  @NonNull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings, @NonNull final IJSDeclaration aDecl)
  {
    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writeDeclaration (aSW, aSettings, aDecl);
    return aSW.getAsString ().trim ();
  }

  @NonNull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings, @NonNull final IJSStatement aStatement)
  {
    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writeStatement (aSW, aSettings, aStatement);
    return aSW.getAsString ().trim ();
  }

  @NonNull
  public static String getAsString (@Nullable final IJSWriterSettings aSettings, @NonNull final JSPackage aPackage)
  {
    ValueEnforcer.notNull (aPackage, "Package");
    if (aPackage.memberCount () == 0)
      return "";

    final NonBlockingStringWriter aSW = new NonBlockingStringWriter ();
    writePackage (aSW, aSettings, aPackage);
    return aSW.getAsString ().trim ();
  }
}
