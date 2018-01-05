/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import java.io.File;
import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.CommonsTreeSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsNavigableSet;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.file.IFileFilter;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.name.IHasName;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.system.SystemProperties;
import com.helger.commons.version.Version;
import com.helger.xml.EXMLParserFeature;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.serialize.MicroReader;
import com.helger.xml.microdom.util.MicroHelper;
import com.helger.xml.serialize.read.SAXReaderDefaultSettings;

abstract class AbstractCreateJQueryAPIList
{
  protected static final Logger s_aLogger = LoggerFactory.getLogger (AbstractCreateJQueryAPIList.class);
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

  private static final ICommonsSet <String> FORBIDDEN_NAMES = new CommonsHashSet <> ("true", "false", "switch");

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
    private final ICommonsList <String> m_aTypes;
    private final ICommonsOrderedSet <String> m_aJavaTypes;
    private final boolean m_bIsOptional;
    private final String m_sDescription;

    @Nonnull
    private static String [] _getJavaTypes (@Nonnull @Nonempty final String sType)
    {
      if (sType.equals ("Boolean"))
        return new String [] { "IJSExpression", "boolean" };
      if (sType.equals ("String") || sType.equals ("Text"))
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
      if (sType.equals ("Error"))
        return new String [] { "IJSExpression" };
      if (sType.equals ("Array"))
        return new String [] { "IJSExpression", "JSArray" };
      if (sType.equals ("ArrayLikeObject"))
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
      if (sType.equals ("jQuery object") || sType.equals ("Null"))
        return new String [] { "IJSExpression", "JQueryInvocation" };

      throw new IllegalArgumentException ("Unknown type '" + sType + "'");
    }

    public Argument (@Nonnull @Nonempty final String sName,
                     @Nonnull @Nonempty final ICommonsList <String> aTypes,
                     final boolean bIsOptional,
                     @Nullable final String sDescription)
    {
      ValueEnforcer.notEmpty (sName, "Name");
      ValueEnforcer.notEmpty (aTypes, "Types");
      m_sName = sName;
      m_sIdentifier = _makeIdentifier (sName);
      m_aTypes = aTypes;
      m_aJavaTypes = new CommonsLinkedHashSet <> ();
      for (final String sType : aTypes)
        for (final String sType0 : _getJavaTypes (sType))
          m_aJavaTypes.add (sType0);
      if ("attributeName".equals (sName))
        m_aJavaTypes.add ("IMicroQName");
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
    public ICommonsList <String> getAllTypes ()
    {
      return m_aTypes.getClone ();
    }

    @Nonnegative
    public int getJavaTypeCount ()
    {
      return m_aJavaTypes.size ();
    }

    @Nonnull
    @Nonempty
    @ReturnsMutableCopy
    public ICommonsOrderedSet <String> getAllJavaTypes ()
    {
      return m_aJavaTypes.getClone ();
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
      if (o == null || !getClass ().equals (o.getClass ()))
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
      return new ToStringGenerator (null).append ("name", m_sName).append ("types", m_aTypes).getToString ();
    }
  }

  protected static final class Signature
  {
    private static final Version V1 = new Version (1);

    private final Version m_aAdded;
    private final ICommonsList <Argument> m_aArgs = new CommonsArrayList <> ();

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
      return m_aAdded.isGT (V1);
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
    public ICommonsList <Argument> getAllArguments ()
    {
      return m_aArgs.getClone ();
    }

    @Nonnegative
    public int getOptionalArgumentCount ()
    {
      return m_aArgs.getCount (Argument::isOptional);
    }

    public boolean containsArgumentWithName (final String sArgName)
    {
      return m_aArgs.containsAny (aArg -> aArg.getName ().equals (sArgName));
    }

    @Nonnegative
    public int getArgumentsWithMultipleJavaTypesCount ()
    {
      return m_aArgs.getCount (aArg -> aArg.getJavaTypeCount () > 1);
    }

    @Override
    public boolean equals (final Object o)
    {
      if (o == this)
        return true;
      if (o == null || !getClass ().equals (o.getClass ()))
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
      return new ToStringGenerator (null).append ("added", m_aAdded).append ("args", m_aArgs).getToString ();
    }
  }

  protected static final class Entry
  {
    private static final ICommonsSet <String> PARENT_CLASS_NAMES = new CommonsHashSet <> ("clone", "eq", "not");

    private final EAPIType m_eAPIType;
    private final String m_sName;
    private final String m_sIdentifier;
    private final String m_sReturn;
    private final Version m_aDeprecated;
    private final Version m_aRemoved;
    private final ICommonsList <Signature> m_aSignatures = new CommonsArrayList <> ();

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
        s_aLogger.info ("Duplicate: " + aSignature);
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
    public ICommonsList <Signature> getAllSignatures ()
    {
      return m_aSignatures.getClone ();
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
    if (sJavaType.equals ("IMicroQName"))
      return "new MicroQName (\"foo\")";
    throw new IllegalArgumentException (sJavaType);
  }

  @Nonnull
  public static ICommonsList <Entry> readAllEntries ()
  {
    int nFiles = 0;
    int nSignatures = 0;
    int nArguments = 0;

    // [Array, Boolean, Callbacks, Deferred, Element, Function, Integer, Number,
    // Object, PlainObject, Promise, String, XMLDocument, boolean, jQuery,
    // jqXHR, undefined]
    final ICommonsNavigableSet <String> aAllReturnTypes = new CommonsTreeSet <> ();

    // [Anything, Array, Boolean, Deferred, Element, Elements, Event,
    // Function, Integer, Number, Number/String, Object, PlainObject, Selector,
    // String, document, htmlString, jQuery, jQuery object]
    final ICommonsNavigableSet <String> aAllArgTypes = new CommonsTreeSet <> ();

    final ICommonsList <Entry> aAllEntries = new CommonsArrayList <> ();

    if (false)
      SAXReaderDefaultSettings.setFeatureValue (EXMLParserFeature.XINCLUDE, Boolean.TRUE);
    SystemProperties.setPropertyValue ("org.apache.xerces.xni.parser.XMLParserConfiguration",
                                       "org.apache.xerces.parsers.XIncludeParserConfiguration");

    for (final File aFile : new FileSystemIterator ("src/test/resources/jquery/entries").withFilter (IFileFilter.filenameEndsWith (".xml")))
    {
      final IMicroDocument aDoc = MicroReader.readMicroXML (aFile);
      final IMicroElement eRoot = aDoc.getDocumentElement ();
      ICommonsList <IMicroElement> aEntries;
      if (eRoot.getTagName ().equals ("entries"))
        aEntries = eRoot.getAllChildElements ("entry");
      else
        aEntries = new CommonsArrayList <> (eRoot);

      for (final IMicroElement eEntry : aEntries)
      {
        final String sAPIType = eEntry.getAttributeValue ("type");
        final EAPIType eAPIType = EAPIType.getFromNameOrThrow (sAPIType);
        final String sName = eEntry.getAttributeValue ("name");
        final String sReturn = eEntry.getAttributeValue ("return");
        final String sDeprecated = eEntry.getAttributeValue ("deprecated");
        final Version aDeprecated = sDeprecated == null ? null : Version.parse (sDeprecated);
        final String sRemoved = eEntry.getAttributeValue ("removed");
        final Version aRemoved = sRemoved == null ? null : Version.parse (sRemoved);

        final Entry aEntry = new Entry (eAPIType, sName, sReturn, aDeprecated, aRemoved);

        // Return is only relevant for type "method"
        if (StringHelper.hasText (sReturn))
          aAllReturnTypes.add (sReturn);

        for (final IMicroElement eSignature : eEntry.getAllChildElements ("signature"))
        {
          final String sAdded = eSignature.getFirstChildElement ("added").getTextContent ();
          final Version aAdded = Version.parse (sAdded);
          final Signature aSignature = new Signature (aAdded);

          for (final IMicroElement eArg : eSignature.getAllChildElements ("argument"))
          {
            final String sOrigArgName = eArg.getAttributeValue ("name");
            final String sArgType = eArg.getAttributeValue ("type");
            final boolean bIsOptional = eArg.hasAttribute ("optional") ? StringParser.parseBool (eArg.getAttributeValue ("optional"))
                                                                       : false;

            final ICommonsList <String> aTypes = new CommonsArrayList <> ();
            if (StringHelper.hasNoTextAfterTrim (sArgType))
            {
              for (final IMicroElement eArgType : eArg.getAllChildElements ("type"))
                for (final String sRealArgType : StringHelper.getExploded ('/', eArgType.getAttributeValue ("name")))
                  aTypes.add (sRealArgType);
            }
            else
              for (final String sRealArgType : StringHelper.getExploded ('/', sArgType.trim ()))
                aTypes.add (sRealArgType);

            final String sDescription = MicroHelper.getChildTextContent (eArg, "desc");

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

    aAllEntries.sort (Comparator.comparing (Entry::getName));

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
            s_aLogger.info (aEntry.getName () + " " + nArgs + " - " + nOptCount);
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

    s_aLogger.info ("Scanned " +
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
      s_aLogger.info ("Returns: " + aAllReturnTypes);
      s_aLogger.info ("Arg Types: " + aAllArgTypes);
    }
    return aAllEntries;
  }
}
