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
package com.helger.photon.icon.tools.supplementary;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsTreeSet;
import com.helger.commons.collection.impl.ICommonsList;
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
import com.helger.photon.icon.EIconCSSPathProvider;

public class MainExtractFontAwesome4CSSClasses
{
  public static void main (final String [] args)
  {
    final CascadingStyleSheet aCSS = CSSReader.readFromStream (new ClassPathResource (EIconCSSPathProvider.FONT_AWESOME4.getCSSItemPath (true)),
                                                               StandardCharsets.UTF_8,
                                                               ECSSVersion.CSS30);
    final ICommonsSet <String> aClasses = new CommonsTreeSet <> ();
    final ICommonsSet <String> aClassesIcon = new CommonsTreeSet <> ();
    CSSVisitor.visitCSS (aCSS, new DefaultCSSVisitor ()
    {
      @Override
      public void onStyleRuleSelector (@Nonnull final CSSSelector aSelector)
      {
        final ICommonsList <ICSSSelectorMember> aMembers = aSelector.getAllMembers ();
        for (final ICSSSelectorMember aMember : aMembers)
          if (aMember instanceof CSSSelectorSimpleMember)
          {
            final CSSSelectorSimpleMember aSM = (CSSSelectorSimpleMember) aMember;
            if (aSM.isClass ())
              aClasses.add (aSM.getValue ());
            else
              if (aSM.isPseudo () && aMembers.size () == 2 && aSM == aMembers.get (1))
                aClassesIcon.add (((CSSSelectorSimpleMember) aMembers.get (0)).getValue ());
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
      sFieldName = StringHelper.replaceAll (sFieldName, '-', '_');
      System.out.println ("public static final ICSSClassProvider " +
                          sFieldName +
                          " = DefaultCSSClassProvider.create (\"" +
                          sClassName +
                          "\");");
    }

    System.out.println ();

    // Icons
    for (final String sClass : aClassesIcon)
      if (sClass.startsWith (".fa-"))
      {
        final String sClassName = sClass.substring (1);
        String sFieldName = sClassName.toUpperCase (Locale.US);
        sFieldName = StringHelper.replaceAll (sFieldName, '-', '_');
        System.out.println (sFieldName.substring ("fa-".length ()) + " (CFontAwesome4CSS." + sFieldName + "),");
      }
  }
}
