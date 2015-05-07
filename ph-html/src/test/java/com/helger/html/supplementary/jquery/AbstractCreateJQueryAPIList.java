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
package com.helger.html.supplementary.jquery;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.SystemProperties;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.io.file.filter.FilenameFilterEndsWith;
import com.helger.commons.io.file.iterate.FileSystemIterator;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.serialize.MicroReader;
import com.helger.commons.microdom.utils.MicroUtils;
import com.helger.commons.name.IHasName;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.version.Version;
import com.helger.commons.xml.EXMLParserFeature;
import com.helger.commons.xml.serialize.SAXReaderDefaultSettings;

abstract class AbstractCreateJQueryAPIList
{
  private static final String TYPE_ANY = "Anything";

  protected static enum EAPIType implements IHasName
  {
    METHOD ("method"),
    PROPERTY ("property"),
    SELECTOR ("selector");

    private final String m_sName;

    private EAPIType (@Nonnull @Nonempty final String sName)
    {
      m_sName = sName;
    }

    @Nonnull
    @Nonempty
    public String getName ()
    {
      return m_sName;
    }

    @Nonnull
    public static EAPIType getFromNameOrThrow (@Nullable final String sName)
    {
      return EnumHelper.getFromNameOrThrow (EAPIType.class, sName);
    }
  }

  private static final Set <String> FORBIDDEN_NAMES = CollectionHelper.newSet ("true", "false", "switch");

  static String _makeIdentifier (final String sName)
  {
    if (FORBIDDEN_NAMES.contains (sName))
      return "_" + sName;

    String sID = RegExHelper.getAsIdentifier (sName, "_").replaceAll ("_+", "_");
    sID = StringHelper.trimStart (sID, "_");
    sID = StringHelper.trimEnd (sID, "_");
    return sID;
  }

  protected static final class Argument
  {
    private final String m_sName;
    private final String m_sIdentifier;
    private final List <String> m_aTypes;
    private final Set <String> m_aJavaTypes;
    private final boolean m_bIsOptional;
    private final String m_sDescription;

    @Nonnull
    private static String [] _getJavaTypes (@Nonnull @Nonempty final String sType)
    {
      if (sType.equals ("Boolean"))
        return new String [] { "IJSExpression", "boolean" };
      if (sType.equals ("String"))
        return new String [] { "IJSExpression", "IJson", "IHCNode", "String" };
      if (sType.equals ("htmlString"))
        return new String [] { "IJSExpression", "IHCNode", "String" };
      if (sType.equals ("Integer"))
        return new String [] { "IJSExpression", "int", "long", "BigInteger" };
      if (sType.equals ("Number"))
        return new String [] { "IJSExpression", "int", "long", "BigInteger", "double", "BigDecimal" };
      if (sType.equals ("Selector"))
        return new String [] { "IJSExpression",
                              "IJQuerySelector",
                              "JQuerySelectorList",
                              "EHTMLElement",
                              "ICSSClassProvider" };
      if (sType.equals ("Function"))
        return new String [] { "IJSExpression", "JSAnonymousFunction" };
      if (sType.equals ("Object"))
        return new String [] { "IJSExpression" };
      if (sType.equals ("PlainObject"))
        return new String [] { "IJSExpression" };
      if (sType.equals ("Anything"))
        return new String [] { "IJSExpression" };
      if (sType.equals ("Array"))
        return new String [] { "IJSExpression", "JSArray" };
      if (sType.equals ("Element"))
        return new String [] { "IJSExpression", "EHTMLElement", "String" };
      if (sType.equals ("Elements"))
        return new String [] { "IJSExpression", "EHTMLElement...", "Iterable<EHTMLElement>", "String..." };
      if (sType.equals ("jQuery"))
        return new String [] { "IJSExpression", "JQueryInvocation" };

      // DOM document
      if (sType.equals ("document"))
        return new String [] { "IJSExpression" };

      // JQuery Deferred
      if (sType.equals ("Deferred"))
        return new String [] { "IJSExpression" };

      // JQuery Event
      if (sType.equals ("Event"))
        return new String [] { "IJSExpression" };

      // ????
      if (sType.equals ("jQuery object"))
        return new String [] { "IJSExpression", "JQueryInvocation" };
      throw new IllegalArgumentException ("Unknown type '" + sType + "'");
    }

    public Argument (final String sName,
                     final List <String> aTypes,
                     final boolean bIsOptional,
                     @Nullable final String sDescription)
    {
      if (StringHelper.hasNoText (sName))
        throw new IllegalArgumentException ("name");
      if (CollectionHelper.isEmpty (aTypes))
        throw new IllegalArgumentException ("types");
      m_sName = sName;
      m_sIdentifier = _makeIdentifier (sName);
      m_aTypes = aTypes;
      m_aJavaTypes = new LinkedHashSet <String> ();
      for (final String sType : aTypes)
        for (final String sType0 : _getJavaTypes (sType))
          m_aJavaTypes.add (sType0);
      m_bIsOptional = bIsOptional;
      m_sDescription = sDescription;
    }

    @Nonnull
    @Nonempty
    public String getName ()
    {
      return m_sName;
    }

    @Nonnull
    @Nonempty
    public String getIdentifier ()
    {
      return m_sIdentifier;
    }

    @Nonnegative
    public int getTypeCount ()
    {
      return m_aTypes.size ();
    }

    @Nonnull
    public String getTypeAtIndex (@Nonnegative final int nIndex)
    {
      return m_aTypes.get (nIndex);
    }

    @Nonnull
    @Nonempty
    @ReturnsMutableCopy
    public List <String> getAllTypes ()
    {
      return CollectionHelper.newList (m_aTypes);
    }

    @Nonnegative
    public int getJavaTypeCount ()
    {
      return m_aJavaTypes.size ();
    }

    @Nonnull
    @Nonempty
    @ReturnsMutableCopy
    public Set <String> getAllJavaTypes ()
    {
      return CollectionHelper.newOrderedSet (m_aJavaTypes);
    }

    @Nonnull
    public String getFirstJavaType ()
    {
      return CollectionHelper.getFirstElement (m_aJavaTypes);
    }

    public boolean isOptional ()
    {
      return m_bIsOptional;
    }

    @Nullable
    public String getDescription ()
    {
      return m_sDescription;
    }

    @Override
    public boolean equals (final Object o)
    {
      if (o == this)
        return true;
      if (!(o instanceof Argument))
        return false;
      final Argument rhs = (Argument) o;
      return m_aTypes.equals (rhs.m_aTypes);
    }

    @Override
    public int hashCode ()
    {
      return new HashCodeGenerator (this).append (m_aTypes).getHashCode ();
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (null).append ("name", m_sName).append ("types", m_aTypes).toString ();
    }
  }

  protected static final class Signature
  {
    private static final Version V1 = new Version (1);

    private final Version m_aAdded;
    private final List <Argument> m_aArgs = new ArrayList <Argument> ();

    public Signature (@Nonnull final Version aAdded)
    {
      m_aAdded = ValueEnforcer.notNull (aAdded, "Added");
    }

    void addArgument (@Nonnull final Argument aArg)
    {
      ValueEnforcer.notNull (aArg, "Arg");
      m_aArgs.add (aArg);
    }

    @Nonnull
    public Version getAdded ()
    {
      return m_aAdded;
    }

    public boolean isAddedAfter10 ()
    {
      return m_aAdded.isGreaterThan (V1);
    }

    @Nonnegative
    public int getArgumentCount ()
    {
      return m_aArgs.size ();
    }

    @Nonnull
    public Argument getArgumentAtIndex (@Nonnegative final int nIndex)
    {
      return m_aArgs.get (nIndex);
    }

    @Nonnull
    @ReturnsMutableCopy
    public List <Argument> getAllArguments ()
    {
      return CollectionHelper.newList (m_aArgs);
    }

    @Nonnegative
    public int getOptionalArgumentCount ()
    {
      int ret = 0;
      for (final Argument aArg : m_aArgs)
        if (aArg.isOptional ())
          ++ret;
      return ret;
    }

    public boolean containsArgumentWithName (final String sArgName)
    {
      for (final Argument aArg : m_aArgs)
        if (aArg.getName ().equals (sArgName))
          return true;
      return false;
    }

    @Nonnegative
    public int getArgumentsWithMultipleJavaTypesCount ()
    {
      int ret = 0;
      for (final Argument aArg : m_aArgs)
        if (aArg.getJavaTypeCount () > 1)
          ++ret;
      return ret;
    }

    @Override
    public boolean equals (final Object o)
    {
      if (o == this)
        return true;
      if (!(o instanceof Signature))
        return false;
      final Signature rhs = (Signature) o;
      return m_aArgs.equals (rhs.m_aArgs);
    }

    @Override
    public int hashCode ()
    {
      return new HashCodeGenerator (this).append (m_aArgs).getHashCode ();
    }

    @Override
    public String toString ()
    {
      return new ToStringGenerator (null).append ("added", m_aAdded).append ("args", m_aArgs).toString ();
    }
  }

  protected static final class Entry
  {
    private static final Set <String> PARENT_CLASS_NAMES = CollectionHelper.newSet ("clone", "eq", "not");

    private final EAPIType m_eAPIType;
    private final String m_sName;
    private final String m_sIdentifier;
    private final String m_sReturn;
    private final Version m_aDeprecated;
    private final Version m_aRemoved;
    private final List <Signature> m_aSignatures = new ArrayList <Signature> ();

    public Entry (@Nonnull final EAPIType eAPIType,
                  @Nonnull @Nonempty final String sName,
                  @Nullable final String sReturn,
                  @Nullable final Version aDeprecated,
                  @Nullable final Version aRemoved)
    {
      m_eAPIType = eAPIType;
      m_sName = sName;
      m_sIdentifier = PARENT_CLASS_NAMES.contains (sName) ? "_" + sName : _makeIdentifier (sName);
      m_sReturn = sReturn;
      m_aDeprecated = aDeprecated;
      m_aRemoved = aRemoved;
    }

    void addSignature (@Nonnull final Signature aSignature)
    {
      m_aSignatures.add (aSignature);
    }

    boolean containsSignature (@Nonnull final Signature aSignature)
    {
      final boolean b = m_aSignatures.contains (aSignature);
      if (b && false)
        System.out.println ("Duplicate: " + aSignature);
      return b;
    }

    void addSignature (@Nonnegative final int nIndex, @Nonnull final Signature aSignature)
    {
      m_aSignatures.add (nIndex, aSignature);
    }

    @Nonnull
    public EAPIType getAPIType ()
    {
      return m_eAPIType;
    }

    @Nonnull
    @Nonempty
    public String getName ()
    {
      return m_sName;
    }

    @Nonnull
    @Nonempty
    public String getIdentifier ()
    {
      return m_sIdentifier;
    }

    public boolean hasReturn ()
    {
      return StringHelper.hasText (m_sReturn);
    }

    @Nullable
    public String getReturn ()
    {
      return m_sReturn;
    }

    @Nonnull
    public String getReturnOrVoid ()
    {
      return hasReturn () ? getReturn () : "void";
    }

    public boolean isDeprecated ()
    {
      return m_aDeprecated != null;
    }

    @Nullable
    public Version getDeprecated ()
    {
      return m_aDeprecated;
    }

    public boolean isRemoved ()
    {
      return m_aRemoved != null;
    }

    @Nullable
    public Version getRemoved ()
    {
      return m_aRemoved;
    }

    @Nonnegative
    public int getSignatureCount ()
    {
      return m_aSignatures.size ();
    }

    @Nonnull
    public Signature getSignatureAtIndex (@Nonnegative final int nIndex)
    {
      return m_aSignatures.get (nIndex);
    }

    @Nonnull
    @ReturnsMutableCopy
    public List <Signature> getAllSignatures ()
    {
      return CollectionHelper.newList (m_aSignatures);
    }

    public boolean isStaticMethod ()
    {
      return m_eAPIType == EAPIType.METHOD && m_sName.startsWith ("jQuery.");
    }
  }

  @Nonnull
  protected static String _getAnnotation (@Nonnull final String sJavaType)
  {
    if (sJavaType.equals ("boolean") ||
        sJavaType.equals ("double") ||
        sJavaType.equals ("int") ||
        sJavaType.equals ("long"))
      return "";
    return "@Nonnull ";
  }

  @Nonnull
  protected static String _getTestValue (@Nonnull final String sJavaType)
  {
    if (sJavaType.equals ("boolean"))
      return "true";
    if (sJavaType.equals ("double"))
      return "123.456";
    if (sJavaType.equals ("int"))
      return "3456";
    if (sJavaType.equals ("long"))
      return "87654321L";
    if (sJavaType.equals ("BigInteger"))
      return "BigInteger.valueOf (3456)";
    if (sJavaType.equals ("BigDecimal"))
      return "BigDecimal.valueOf (12.3456)";
    if (sJavaType.equals ("String"))
      return "\"foo\"";
    if (sJavaType.equals ("String..."))
      return "\"foo\", \"bar\"";
    if (sJavaType.equals ("IJSExpression"))
      return "JSExpr.lit (\"foo\")";
    if (sJavaType.equals ("JQueryInvocation"))
      return "JQuery.idRef (\"foo\")";
    if (sJavaType.equals ("IJQuerySelector"))
      return "JQuerySelector.eq (0)";
    if (sJavaType.equals ("JQuerySelectorList"))
      return "new JQuerySelectorList (JQuerySelector.lt (3))";
    if (sJavaType.equals ("EHTMLElement"))
      return "EHTMLElement.DIV";
    if (sJavaType.equals ("EHTMLElement..."))
      return "EHTMLElement.DIV, EHTMLElement.SPAN";
    if (sJavaType.equals ("Iterable<EHTMLElement>"))
      return "CollectionHelper.newList (EHTMLElement.DIV, EHTMLElement.SPAN)";
    if (sJavaType.equals ("ICSSClassProvider"))
      return "DefaultCSSClassProvider.create (\"cssclass\")";
    if (sJavaType.equals ("IHCNode"))
      return "new HCDiv ().addChild (\"foo\")";
    if (sJavaType.equals ("IJson"))
      return "new JsonObject ().add (\"foo\", 5)";
    if (sJavaType.equals ("JSAnonymousFunction"))
      return "new JSAnonymousFunction ()";
    if (sJavaType.equals ("JSArray"))
      return "new JSArray ().add (1).add (2)";
    throw new IllegalArgumentException (sJavaType);
  }

  @Nonnull
  public static List <Entry> readAllEntries ()
  {
    int nFiles = 0;
    int nSignatures = 0;
    int nArguments = 0;

    // [Array, Boolean, Callbacks, Deferred, Element, Function, Integer, Number,
    // Object, PlainObject, Promise, String, XMLDocument, boolean, jQuery,
    // jqXHR, undefined]
    final Set <String> aAllReturnTypes = new TreeSet <String> ();

    // [Anything, Array, Boolean, Deferred, Element, Elements, Event,
    // Function, Integer, Number, Number/String, Object, PlainObject, Selector,
    // String, document, htmlString, jQuery, jQuery object]
    final Set <String> aAllArgTypes = new TreeSet <String> ();

    final List <Entry> aAllEntries = new ArrayList <Entry> ();

    if (false)
      SAXReaderDefaultSettings.setFeatureValue (EXMLParserFeature.XINCLUDE, Boolean.TRUE);
    SystemProperties.setPropertyValue ("org.apache.xerces.xni.parser.XMLParserConfiguration",
                                       "org.apache.xerces.parsers.XIncludeParserConfiguration");

    for (final File aFile : FileSystemIterator.create (new File ("src/test/resources/jquery/api"),
                                                       new FilenameFilterEndsWith (".xml")))
    {
      final IMicroDocument aDoc = MicroReader.readMicroXML (aFile);
      final IMicroElement eRoot = aDoc.getDocumentElement ();
      List <IMicroElement> aEntries;
      if (eRoot.getTagName ().equals ("entries"))
        aEntries = eRoot.getAllChildElements ("entry");
      else
        aEntries = CollectionHelper.newList (eRoot);

      for (final IMicroElement eEntry : aEntries)
      {
        final String sAPIType = eEntry.getAttributeValue ("type");
        final EAPIType eAPIType = EAPIType.getFromNameOrThrow (sAPIType);
        final String sName = eEntry.getAttributeValue ("name");
        final String sReturn = eEntry.getAttributeValue ("return");
        final String sDeprecated = eEntry.getAttributeValue ("deprecated");
        final Version aDeprecated = sDeprecated == null ? null : new Version (sDeprecated);
        final String sRemoved = eEntry.getAttributeValue ("removed");
        final Version aRemoved = sRemoved == null ? null : new Version (sRemoved);

        final Entry aEntry = new Entry (eAPIType, sName, sReturn, aDeprecated, aRemoved);

        // Return is only relevant for type "method"
        if (StringHelper.hasText (sReturn))
          aAllReturnTypes.add (sReturn);

        for (final IMicroElement eSignature : eEntry.getAllChildElements ("signature"))
        {
          final String sAdded = eSignature.getFirstChildElement ("added").getTextContent ();
          final Version aAdded = new Version (sAdded);
          final Signature aSignature = new Signature (aAdded);

          for (final IMicroElement eArg : eSignature.getAllChildElements ("argument"))
          {
            final String sOrigArgName = eArg.getAttributeValue ("name");
            final String sArgType = eArg.getAttributeValue ("type");
            final boolean bIsOptional = eArg.hasAttribute ("optional") ? StringParser.parseBool (eArg.getAttributeValue ("optional"))
                                                                      : false;

            final List <String> aTypes = new ArrayList <String> ();
            if (StringHelper.hasNoTextAfterTrim (sArgType))
            {
              for (final IMicroElement eArgType : eArg.getAllChildElements ("type"))
                for (final String sRealArgType : StringHelper.getExploded ('/', eArgType.getAttributeValue ("name")))
                  aTypes.add (sRealArgType);
            }
            else
              for (final String sRealArgType : StringHelper.getExploded ('/', sArgType.trim ()))
                aTypes.add (sRealArgType);

            final String sDescription = MicroUtils.getChildTextContent (eArg, "desc");

            // Happens in callbacks.fireWith
            if (aTypes.isEmpty ())
              aTypes.add (TYPE_ANY);

            aAllArgTypes.addAll (aTypes);

            // Make argument name unique
            String sArgName = sOrigArgName;
            // Skip function parameters
            final int i = sArgName.indexOf ('(');
            if (i > 0)
              sArgName = sArgName.substring (0, i);
            int nArgIndex = 1;
            while (aSignature.containsArgumentWithName (sArgName))
              sArgName = sOrigArgName + nArgIndex++;

            aSignature.addArgument (new Argument (sArgName, aTypes, bIsOptional, sDescription));

            ++nArguments;
          }

          if (!aEntry.containsSignature (aSignature))
            aEntry.addSignature (aSignature);
          ++nSignatures;
        }
        if (aEntry.getAPIType () == EAPIType.METHOD && aEntry.getName ().equals ("jQuery"))
        {
          // ignore this entry as this is explicitly handled in class JQuery
        }
        else
          aAllEntries.add (aEntry);
      }
      ++nFiles;
    }

    Collections.sort (aAllEntries, new Comparator <Entry> ()
    {
      public int compare (final Entry o1, final Entry o2)
      {
        return o1.getName ().compareTo (o2.getName ());
      }
    });

    // Build unique signatures where optional arguments are present
    for (final Entry aEntry : aAllEntries)
    {
      int nSigIndex = 0;
      for (final Signature aSig : aEntry.getAllSignatures ())
      {
        final int nOptCount = aSig.getOptionalArgumentCount ();
        if (nOptCount > 0)
        {
          final int nArgs = aSig.getArgumentCount ();
          if (false)
            System.out.println (aEntry.getName () + " " + nArgs + " - " + nOptCount);
          for (int i = nOptCount; i >= 1; --i)
          {
            final int nRemainingArgs = nArgs - i;
            final Signature aClone = new Signature (aSig.getAdded ());
            for (int j = 0; j < nRemainingArgs; ++j)
              aClone.addArgument (aSig.getArgumentAtIndex (j));
            if (!aEntry.containsSignature (aClone))
              aEntry.addSignature (nSigIndex++, aClone);
          }
        }
        ++nSigIndex;
      }
    }

    System.out.println ("Scanned " +
                        nFiles +
                        " files, " +
                        aAllEntries.size () +
                        " entries, " +
                        nSignatures +
                        " signatures and " +
                        nArguments +
                        " arguments");
    if (false)
    {
      System.out.println ("Returns: " + aAllReturnTypes);
      System.out.println ("Arg Types: " + aAllArgTypes);
    }
    return aAllEntries;
  }
}
