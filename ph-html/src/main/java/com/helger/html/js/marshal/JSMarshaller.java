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
package com.helger.html.js.marshal;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collections.ArrayHelper;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.js.CJS;
import com.helger.html.js.IJSCodeProvider;
import com.helger.json.IJson;

/**
 * Marshaler class that converts Java Objects to their respective JavaScript
 * notation.
 *
 * @author Philip Helger
 */
@Immutable
public final class JSMarshaller
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JSMarshaller.class);
  private static final char [] CHARS_TO_MASK = new char [] { '"', '\'', '\\', '/', '\t', '\r', '\n', '\f' };
  private static final char [] CHARS_TO_MASK_REGEX = new char [] { '\\',
                                                                  '^',
                                                                  '$',
                                                                  '*',
                                                                  '+',
                                                                  '?',
                                                                  '|',
                                                                  '.',
                                                                  '-',
                                                                  '[',
                                                                  ']',
                                                                  '(',
                                                                  ')',
                                                                  '{',
                                                                  '}' };
  private static final char MASK_CHAR = '\\';
  private static final char MASK_CHAR_REGEX = '\\';

  /**
   * All reserved keywords of JS. see <a href=
   * "https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Reserved_Words"
   * >here</a><br>
   * technically the last few are not reserved words but they cannot be used as
   * identifiers.
   */
  private static final Set <String> RESERVED_KEYWORDS = CollectionHelper.newSet ("break",
                                                                                "case",
                                                                                "catch",
                                                                                "continue",
                                                                                "debugger",
                                                                                "default",
                                                                                "delete",
                                                                                "do",
                                                                                "else",
                                                                                "finally",
                                                                                "for",
                                                                                "function",
                                                                                "if",
                                                                                "in",
                                                                                "instanceof",
                                                                                "new",
                                                                                "return",
                                                                                "switch",
                                                                                "this",
                                                                                "throw",
                                                                                "try",
                                                                                "typeof",
                                                                                "var",
                                                                                "void",
                                                                                "while",
                                                                                "with",
                                                                                "class",
                                                                                "enum",
                                                                                "export",
                                                                                "extends",
                                                                                "import",
                                                                                "super",
                                                                                // non-reserved
                                                                                "true",
                                                                                "false",
                                                                                "null",
                                                                                "undefined");

  private JSMarshaller ()
  {}

  /**
   * Turn special characters into escaped characters conforming to JavaScript.
   * Handles complete character set defined in HTML 4.01 recommendation.<br>
   * Reference: <a href=
   * "http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Guide:Literals#String_Literals"
   * > Core JavaScript 1.5 Guide </a>
   *
   * @param sInput
   *        the input string
   * @return the escaped string
   */
  @Nullable
  public static String javaScriptEscape (@Nullable final String sInput)
  {
    if (StringHelper.hasNoText (sInput))
      return sInput;

    final char [] aInput = sInput.toCharArray ();
    if (!StringHelper.containsAny (aInput, CHARS_TO_MASK))
      return sInput;

    final char [] ret = new char [aInput.length * 2];
    int nIndex = 0;
    char cPrevChar = '\u0000';
    for (final char cCurrent : aInput)
    {
      switch (cCurrent)
      {
        case '"':
        case '\'':
        case '\\':
        case '/':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = cCurrent;
          break;
        case '\t':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = 't';
          break;
        case '\n':
          if (cPrevChar != '\r')
          {
            ret[nIndex++] = MASK_CHAR;
            ret[nIndex++] = 'n';
          }
          break;
        case '\r':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = 'n';
          break;
        case '\f':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = 'f';
          break;
        default:
          ret[nIndex++] = cCurrent;
          break;
      }
      cPrevChar = cCurrent;
    }

    return new String (ret, 0, nIndex);
  }

  @Nonnull
  public static String javaScriptEscapeForRegEx (final char cInput)
  {
    if (ArrayHelper.contains (CHARS_TO_MASK_REGEX, cInput))
      return new StringBuilder (2).append (MASK_CHAR_REGEX).append (cInput).toString ();
    return Character.toString (cInput);
  }

  /**
   * Turn special regular expression characters into escaped characters
   * conforming to JavaScript.<br>
   * Reference: <a href=
   * "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions"
   * >MDN Regular Expressions</a>
   *
   * @param sInput
   *        the input string
   * @return the escaped string
   */
  @Nullable
  public static String javaScriptEscapeForRegEx (@Nullable final String sInput)
  {
    if (StringHelper.hasNoText (sInput))
      return sInput;

    final char [] aInput = sInput.toCharArray ();
    if (!StringHelper.containsAny (aInput, CHARS_TO_MASK_REGEX))
      return sInput;

    // At last each character has one masking character
    final char [] ret = new char [aInput.length * 2];
    int nIndex = 0;
    for (final char cCurrent : aInput)
      if (ArrayHelper.contains (CHARS_TO_MASK_REGEX, cCurrent))
      {
        ret[nIndex++] = MASK_CHAR_REGEX;
        ret[nIndex++] = cCurrent;
      }
      else
        ret[nIndex++] = cCurrent;

    return new String (ret, 0, nIndex);
  }

  /**
   * Unescape a previously escaped string.<br>
   * Important: this is not a 100% reversion of
   * {@link #javaScriptEscape(String)} since the escaping method drops any '\r'
   * character and it will therefore not be unescaped!
   *
   * @param sInput
   *        The string to be unescaped. May be <code>null</code>.
   * @return The unescaped string.
   * @see #javaScriptEscape(String)
   */
  @Nullable
  public static String javaScriptUnescape (@Nullable final String sInput)
  {
    if (StringHelper.hasNoText (sInput))
      return sInput;

    final char [] aInput = sInput.toCharArray ();
    if (!ArrayHelper.contains (aInput, MASK_CHAR))
      return sInput;

    final char [] ret = new char [aInput.length];
    int nIndex = 0;
    char cPrevChar = '\u0000';
    for (int i = 0; i < aInput.length; i++)
    {
      final char cCurrent = aInput[i];
      if (cPrevChar == MASK_CHAR)
      {
        switch (cCurrent)
        {
          case '"':
          case '\'':
          case '\\':
          case '/':
            ret[nIndex++] = cCurrent;
            break;
          case 't':
            ret[nIndex++] = '\t';
            break;
          case 'n':
            ret[nIndex++] = '\n';
            break;
          case 'f':
            ret[nIndex++] = '\f';
            break;
          case 'x':
            final char cHex1 = aInput[++i];
            final char cHex2 = aInput[++i];
            ret[nIndex++] = (char) StringHelper.getHexByte (cHex1, cHex2);
            break;
          default:
            ret[nIndex++] = MASK_CHAR;
            ret[nIndex++] = cCurrent;
            break;
        }
        cPrevChar = 0;
      }
      else
      {
        if (cCurrent != MASK_CHAR)
          ret[nIndex++] = cCurrent;
        cPrevChar = cCurrent;
      }
    }

    // Last char is a mask char? append!
    if (cPrevChar == MASK_CHAR)
      ret[nIndex++] = MASK_CHAR;

    return new String (ret, 0, nIndex);
  }

  @Nullable
  private static JSType _autoDetectJSType (final Class <?> aClass)
  {
    if (ClassHelper.isStringClass (aClass))
      return JSType.STRING;
    if (ClassHelper.isCharacterClass (aClass))
      return JSType.STRING;
    if (ClassHelper.isBooleanClass (aClass))
      return JSType.BOOLEAN;
    if (ClassHelper.isFloatingPointClass (aClass))
      return JSType.DOUBLE;
    if (ClassHelper.isIntegerClass (aClass))
      return JSType.INT;
    if (ClassHelper.isArrayClass (aClass))
      return new JSArrayType (JSType.AUTO_DETECT);
    if (IJSCodeProvider.class.isAssignableFrom (aClass))
      return JSType.JS;
    if (Map.class.isAssignableFrom (aClass))
      return new JSMapType (JSType.AUTO_DETECT, JSType.AUTO_DETECT);
    if (Collection.class.isAssignableFrom (aClass))
      return new JSListType (JSType.AUTO_DETECT);
    if (IJson.class.isAssignableFrom (aClass))
      return JSType.JSON;
    s_aLogger.warn ("Failed to detect JS type of class " + aClass);
    return null;
  }

  @Nonnull
  private static JSType _getRealJSType (@Nullable final Object aObject, @Nonnull final JSType aSupposedType)
  {
    if (!aSupposedType.equals (JSType.AUTO_DETECT))
      return aSupposedType;

    if (aObject == null)
      return JSType.VOID;

    // auto-detect JS type!!!
    final JSType aRealType = _autoDetectJSType (aObject.getClass ());
    if (aRealType == null)
      throw new IllegalArgumentException ("Unsupported data type: " + aObject.getClass ());
    return aRealType;
  }

  private static void _toJSString (@Nullable final Object aObject,
                                   @Nonnull final JSType aType,
                                   @Nonnull final StringBuilder aSB,
                                   @Nonnegative final int nLevel,
                                   final boolean bWithSurroundingVar)
  {
    if (aObject == null)
      aSB.append (CJS.JS_NULL);
    else
    {
      switch (aType.getType ())
      {
        case BOOLEAN:
        case DOUBLE:
          // double: No check for "Number" because this destroys float values!
          aSB.append (aObject.toString ());
          break;
        case INT:
          if (aObject instanceof Number)
            aSB.append (Long.toString (((Number) aObject).longValue ()));
          else
            aSB.append (aObject.toString ());
          break;
        case HTML:
          if (aObject instanceof IHCNode)
            aSB.append (HCSettings.getAsHTMLString ((IHCNode) aObject));
          else
            aSB.append ((String) aObject);
          break;
        case JS:
          // Use JS as is
          if (aObject instanceof IJSCodeProvider)
            aSB.append (((IJSCodeProvider) aObject).getJSCode ());
          else
            aSB.append ((String) aObject);
          break;
        case JSON:
          aSB.append (((IJson) aObject).getAsString ());
          break;
        case STRING:
          // Note: use single quotes for use in HTML attributes!
          final String sValue = String.valueOf (aObject);
          aSB.append ('\'').append (javaScriptEscape (sValue)).append ('\'');
          break;
        case ARRAY:
        case LIST:
        {
          if (!(aType instanceof JSListType) && !(aType instanceof JSArrayType))
            throw new IllegalArgumentException ("object is not a list: " + aType);

          // get type of list elements
          final JSType aListType = ((IHasChildJSType) aType).getChildType ();

          // for all values (recursive)
          if (nLevel == 0 && bWithSurroundingVar)
            aSB.append ("var x=");
          aSB.append ('[');

          int i = 0;
          if (aType.getType () == EJSType.ARRAY)
          {
            // Handle arrays
            final Object [] aArray = (Object []) aObject;
            for (final Object aMember : aArray)
            {
              if (i++ > 0)
                aSB.append (',');
              _toJSString (aMember, _getRealJSType (aMember, aListType), aSB, nLevel + 1, bWithSurroundingVar);
            }
          }
          else
          {
            // Handle lists
            final Collection <?> aList = (Collection <?>) aObject;
            for (final Object aMember : aList)
            {
              if (i++ > 0)
                aSB.append (',');
              _toJSString (aMember, _getRealJSType (aMember, aListType), aSB, nLevel + 1, bWithSurroundingVar);
            }
          }
          aSB.append (']');
          if (nLevel == 0 && bWithSurroundingVar)
          {
            // JS "eval" should return the array!
            aSB.append (";x");
          }
          break;
        }
        case MAP:
        {
          if (!(aType instanceof JSMapType))
            throw new IllegalArgumentException ("object is not a map");

          final Map <?, ?> aMap = (Map <?, ?>) aObject;

          // get type of map elements
          final JSType aKeyType = ((JSMapType) aType).getKeyType ();
          final JSType aValueType = ((JSMapType) aType).getValueType ();

          // for all keys (recursive)
          if (nLevel == 0 && bWithSurroundingVar)
            aSB.append ("var x=");
          aSB.append ('{');

          int i = 0;
          for (final Entry <?, ?> aEntry : aMap.entrySet ())
          {
            if (i++ > 0)
              aSB.append (',');

            final Object aKey = aEntry.getKey ();
            final Object aValue = aEntry.getValue ();

            // append key and value
            _toJSString (aKey, _getRealJSType (aKey, aKeyType), aSB, nLevel + 1, bWithSurroundingVar);
            aSB.append (':');
            _toJSString (aValue, _getRealJSType (aValue, aValueType), aSB, nLevel + 1, bWithSurroundingVar);
          }
          aSB.append ('}');
          if (nLevel == 0 && bWithSurroundingVar)
          {
            // JS "eval" should return the array!
            aSB.append (";x");
          }
          break;
        }
        case VOID:
          // do nothing
          break;
        default:
          throw new IllegalStateException ("Unknown type: " + aType.getType ());
      }
    }
  }

  /**
   * Auto-detect the type of the passed object and convert it to a JS string. If
   * the type detection failed, an {@link IllegalArgumentException} is thrown.
   *
   * @param aObject
   *        The object to be converted. May be <code>null</code>. Note: works
   *        for atomic types and arrays, but <b>not</b> for collection types!
   * @return The string representation of the passed object.
   */
  @Nonnull
  public static String objectToJSString (@Nullable final Object aObject)
  {
    return objectToJSString (aObject, JSType.AUTO_DETECT, false);
  }

  @Nonnull
  public static String objectToJSString (@Nullable final Object aObject, @Nonnull final JSType aType)
  {
    return objectToJSString (aObject, aType, false);
  }

  @Nonnull
  public static String objectToJSString (@Nullable final Object aObject,
                                         @Nonnull final JSType aType,
                                         final boolean bWithSurroundingVar)
  {
    ValueEnforcer.notNull (aType, "Type");

    final StringBuilder aSB = new StringBuilder ();
    _toJSString (aObject, _getRealJSType (aObject, aType), aSB, 0, bWithSurroundingVar);
    return aSB.toString ();
  }

  public static boolean isJSIdentifier (@Nullable final String s)
  {
    if (StringHelper.hasNoText (s))
      return false;

    // Reserved word?
    if (RESERVED_KEYWORDS.contains (s))
      return false;

    final char [] aChars = s.toCharArray ();
    for (int i = 0; i < aChars.length; ++i)
    {
      if (i == 0)
      {
        if (!Character.isJavaIdentifierStart (aChars[i]))
          return false;
      }
      else
      {
        if (!Character.isJavaIdentifierPart (aChars[i]))
          return false;
      }
    }
    return true;
  }
}
