/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.supplementary.tools;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.impl.CommonsTreeSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.string.StringHelper;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSSelector;
import com.helger.css.decl.CSSSelectorSimpleMember;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.ICSSSelectorMember;
import com.helger.css.decl.visit.CSSVisitor;
import com.helger.css.decl.visit.DefaultCSSVisitor;
import com.helger.css.reader.CSSReader;
import com.helger.photon.bootstrap4.EBootstrapCSSPathProvider;

public final class MainExtractBootstrap4CSSClasses
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MainExtractBootstrap4CSSClasses.class);

  public static void main (final String [] args)
  {
    final StringBuilder aSB = new StringBuilder ();
    final CascadingStyleSheet aCSS = CSSReader.readFromStream (new ClassPathResource (EBootstrapCSSPathProvider.BOOTSTRAP.getCSSItemPath (true)),
                                                               StandardCharsets.UTF_8,
                                                               ECSSVersion.CSS30);
    final ICommonsSet <String> aClasses = new CommonsTreeSet <> ();
    CSSVisitor.visitCSS (aCSS, new DefaultCSSVisitor ()
    {
      @Override
      public void onStyleRuleSelector (@Nonnull final CSSSelector aSelector)
      {
        for (final ICSSSelectorMember aMember : aSelector.getAllMembers ())
          if (aMember instanceof CSSSelectorSimpleMember)
          {
            final CSSSelectorSimpleMember aSM = (CSSSelectorSimpleMember) aMember;
            if (aSM.isClass ())
              aClasses.add (aSM.getValue ());
            else
              if (aSM.getValue ().contains ("."))
                aSB.append (aSM.getValue ()).append ('\n');
          }
      }
    });
    for (final String sClass : aClasses)
    {
      final String sClassName = sClass.substring (1);
      String sFieldName = sClassName.toUpperCase (Locale.US);
      sFieldName = StringHelper.replaceAll (sFieldName, '-', '_');
      aSB.append ("public static final ICSSClassProvider ")
         .append (sFieldName)
         .append (" = DefaultCSSClassProvider.create (\"")
         .append (sClassName)
         .append ("\");\n");
    }

    LOGGER.info (aSB.toString ());
  }
}
