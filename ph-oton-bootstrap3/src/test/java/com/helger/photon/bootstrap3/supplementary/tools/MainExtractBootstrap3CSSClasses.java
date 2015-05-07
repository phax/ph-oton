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
package com.helger.photon.bootstrap3.supplementary.tools;

import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnull;

import com.helger.commons.charset.CCharset;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSSelector;
import com.helger.css.decl.CSSSelectorSimpleMember;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.ICSSSelectorMember;
import com.helger.css.decl.visit.CSSVisitor;
import com.helger.css.decl.visit.DefaultCSSVisitor;
import com.helger.css.reader.CSSReader;
import com.helger.photon.bootstrap3.EBootstrapCSSPathProvider;

public class MainExtractBootstrap3CSSClasses
{
  public static void main (final String [] args)
  {
    final CascadingStyleSheet aCSS = CSSReader.readFromStream (new ClassPathResource (EBootstrapCSSPathProvider.BOOTSTRAP_334.getCSSItemPath (true)),
                                                               CCharset.CHARSET_UTF_8_OBJ,
                                                               ECSSVersion.CSS30);
    final Set <String> aClasses = new TreeSet <String> ();
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
                System.out.println (aSM.getValue ());
          }
      }
    });
    for (final String sClass : aClasses)
    {
      final String sClassName = sClass.substring (1);
      String sFieldName = sClassName.toUpperCase (Locale.US);
      sFieldName = sFieldName.replace ('-', '_');
      System.out.println ("public static final ICSSClassProvider " +
                          sFieldName +
                          " = DefaultCSSClassProvider.create (\"" +
                          sClassName +
                          "\");");
    }

    System.out.println ();

    // Icons
    for (final String sClass : aClasses)
      if (sClass.startsWith (".glyphicon-"))
      {
        final String sClassName = sClass.substring (1);
        String sFieldName = sClassName.toUpperCase (Locale.US);
        sFieldName = sFieldName.replace ('-', '_');
        System.out.println (sFieldName.substring ("glyphicon-".length ()) + " (CBootstrapCSS." + sFieldName + "),");
      }
  }
}
