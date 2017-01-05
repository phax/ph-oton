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
package com.helger.html.jquery.supplementary.main;

import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.CommonsLinkedHashSet;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsOrderedSet;
import com.helger.commons.collection.multimap.IMultiMapListBased;
import com.helger.commons.collection.multimap.MultiTreeMapArrayListBased;
import com.helger.commons.string.StringHelper;

public class MainCreateJQueryAPIList extends AbstractCreateJQueryAPIList
{
  public static void main (final String [] args) throws Exception
  {
    // Read all data
    final ICommonsList <Entry> aAllEntries = readAllEntries ();
    final ICommonsList <String> aLines = new CommonsArrayList<> ();

    // All selectors
    if (false)
      for (final Entry aEntry : aAllEntries)
        if (aEntry.getAPIType () == EAPIType.SELECTOR && aEntry.getSignatureCount () == 1)
        {
          final Signature aSignature = aEntry.getSignatureAtIndex (0);
          if (aSignature.getArgumentCount () == 0)
          {
            if (aSignature.isAddedAfter10 ())
              aLines.add ("// @since jQuery " + aSignature.getAdded ().getAsString (false));
            if (aEntry.isDeprecated ())
            {
              aLines.add ("// @deprecated");
              aLines.add ("// Deprecated since jQuery " + aEntry.getDeprecated ().getAsString (false));
            }
            aLines.add ("public static final IJQuerySelector " +
                        aEntry.getIdentifier () +
                        " = new JQuerySelector (\":" +
                        aEntry.getName () +
                        "\");");
          }
        }

    // Selectors with arguments
    if (false)
      for (final Entry aEntry : aAllEntries)
        if (aEntry.getAPIType () == EAPIType.SELECTOR &&
            (aEntry.getSignatureCount () > 1 || aEntry.getSignatureAtIndex (0).getArgumentCount () > 0))
        {
          final String sPrefix = "public static IJQuerySelector " + aEntry.getIdentifier ();
          for (final Signature aSignature : aEntry.getAllSignatures ())
          {
            String sRealPrefix = sPrefix;
            if (aEntry.isRemoved ())
              sRealPrefix = "// Removed in jQuery " + aEntry.getRemoved ().getAsString (false) + "\n" + sRealPrefix;
            if (aEntry.isDeprecated ())
              sRealPrefix = "// @deprecated\n// Deprecated since jQuery " +
                            aEntry.getDeprecated ().getAsString (false) +
                            "\n" +
                            sRealPrefix;
            if (aSignature.isAddedAfter10 ())
              sRealPrefix = "// @since jQuery " + aSignature.getAdded ().getAsString (false) + "\n" + sRealPrefix;

            if (aSignature.getArgumentCount () == 0)
            {
              aLines.add (sRealPrefix + "();");
            }
            else
            {
              String sLine = sRealPrefix + "(";
              boolean bFirst = true;
              for (final Argument aArg : aSignature.getAllArguments ())
              {
                if (bFirst)
                  bFirst = false;
                else
                  sLine += ", ";

                sLine += "{" + StringHelper.getImploded ('/', aArg.getAllJavaTypes ()) + "} " + aArg.getIdentifier ();
              }
              aLines.add (sLine + ");");
            }
          }
        }

    // All properties
    if (false)
      for (final Entry aEntry : aAllEntries)
        if (aEntry.getAPIType () == EAPIType.PROPERTY)
          for (final Signature aSignature : aEntry.getAllSignatures ())
          {
            String sLine = "JSFieldRef " + aEntry.getName () + "();";
            if (aEntry.isRemoved ())
              sLine = "// Removed in jQuery " + aEntry.getRemoved ().getAsString (false) + "\n" + sLine;
            if (aEntry.isDeprecated ())
              sLine = "// @deprecated Deprecated since jQuery " +
                      aEntry.getDeprecated ().getAsString (false) +
                      "\n" +
                      sLine;
            if (aSignature.isAddedAfter10 ())
              sLine = "// @since jQuery " + aSignature.getAdded ().getAsString (false) + "\n" + sLine;

            if (aSignature.getArgumentCount () > 0)
              throw new IllegalStateException (aEntry.getName ());
            aLines.add (sLine);
          }

    // The following prefixes are contained:
    // "callbacks."
    // "deferred."
    // "event."
    // "jQuery."

    // Methods without parameter handling
    if (false)
    {
      // Collect all methods with the same name
      final IMultiMapListBased <String, Entry> aUsed = new MultiTreeMapArrayListBased<> ();
      for (final Entry aEntry : aAllEntries)
        if (aEntry.getAPIType () == EAPIType.METHOD)
          aUsed.putSingle (aEntry.getName (), aEntry);

      // static methods- for JQuery.java
      if (false)
        for (final ICommonsList <Entry> aEntries : aUsed.values ())
        {
          boolean bIsDeprecated = true;
          boolean bIsPartiallyDeprecated = false;
          final ICommonsOrderedSet <String> aReturnTypes = new CommonsLinkedHashSet<> ();
          final ICommonsOrderedSet <String> aDeprecatedVersions = new CommonsLinkedHashSet<> ();
          for (final Entry aEntry : aEntries)
          {
            aReturnTypes.add (aEntry.getReturnOrVoid ());
            if (aEntry.isDeprecated ())
            {
              aDeprecatedVersions.add (aEntry.getDeprecated ().getAsString (false));
              bIsPartiallyDeprecated = true;
            }
            else
              bIsDeprecated = false;
          }

          final Entry aEntry = aEntries.get (0);

          // Static methods are handled in class jQuery
          if (aEntry.isStaticMethod ())
          {
            String sSince = null;
            if (aEntries.size () == 1 &&
                aEntry.getSignatureCount () == 1 &&
                aEntry.getSignatureAtIndex (0).isAddedAfter10 ())
              sSince = aEntry.getSignatureAtIndex (0).getAdded ().getAsString (false);

            aLines.add ("/**");
            if (!bIsDeprecated && bIsPartiallyDeprecated)
              aLines.add ("* Certain versions of this method are deprecated since jQuery " +
                          StringHelper.getImploded (" or ", aDeprecatedVersions));
            aLines.add (" * @return The invocation of the static jQuery function <code>" +
                        aEntry.getName () +
                        "()</code> with return type " +
                        StringHelper.getImploded (" or ", aReturnTypes));
            if (bIsDeprecated)
              aLines.add ("* @deprecated Deprecated since jQuery " +
                          StringHelper.getImploded (" or ", aDeprecatedVersions));
            if (sSince != null)
              aLines.add (" * @since jQuery " + sSince);
            aLines.add (" */");
            aLines.add ("@Nonnull");
            if (bIsDeprecated)
              aLines.add ("@Deprecated");
            aLines.add ("public static JQueryInvocation " +
                        StringHelper.trimStart (aEntry.getIdentifier (), "jQuery_") +
                        " ()");
            aLines.add ("{ return new JQueryInvocation (JQueryProperty.jQueryField (), \"" +
                        aEntry.getName ().substring ("jQuery.".length ()) +
                        "\"); }");
          }
        }
    }

    for (final String sLine : aLines)
      System.out.println (sLine);
  }
}
