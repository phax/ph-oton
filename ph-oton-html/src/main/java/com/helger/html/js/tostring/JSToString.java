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
package com.helger.html.js.tostring;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.lang.ClassHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.js.CJS;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.JSMarshaller;
import com.helger.json.IJson;

public final class JSToString
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JSToString.class);

  private JSToString ()
  {}

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
    if (IHasJSCode.class.isAssignableFrom (aClass))
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
            aSB.append (HCRenderer.getAsHTMLStringWithoutNamespaces ((IHCNode) aObject));
          else
            aSB.append ((String) aObject);
          break;
        case JS:
          // Use JS as is
          if (aObject instanceof IHasJSCode)
            aSB.append (((IHasJSCode) aObject).getJSCode ());
          else
            aSB.append ((String) aObject);
          break;
        case JSON:
          aSB.append (((IJson) aObject).getAsJsonString ());
          break;
        case STRING:
          // Note: use single quotes for use in HTML attributes!
          final String sValue = String.valueOf (aObject);
          aSB.append ('\'').append (JSMarshaller.javaScriptEscape (sValue)).append ('\'');
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
          for (final Map.Entry <?, ?> aEntry : aMap.entrySet ())
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
}
