/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsLinkedHashSet;
import com.helger.commons.collection.ext.ICommonsOrderedSet;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.random.RandomHelper;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.state.EContinue;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.URLHelper;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base class for stream and download servlet.
 *
 * @author Philip Helger
 * @since 3.7.0
 */
public abstract class AbstractObjectDeliveryServlet extends AbstractUnifiedResponseServlet
{
  public static final String INITPARAM_DENIED_FILENAMES = "deniedFilenames";
  public static final String INITPARAM_DENIED_EXTENSIONS = "deniedExtensions";
  public static final String INITPARAM_DENIED_REG_EXS = "deniedRegExs";
  public static final String INITPARAM_ALLOWED_FILENAMES = "allowedFilenames";
  public static final String INITPARAM_ALLOWED_EXTENSIONS = "allowedExtensions";
  public static final String INITPARAM_ALLOWED_REG_EXS = "allowedRegExs";
  public static final String INITPARAM_VALUE_WILDCARD = "*";
  public static final String EXTENSION_MACRO_WEB_DEFAULT = "$web-default$";

  protected static final String REQUEST_ATTR_OBJECT_DELIVERY_FILENAME = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL +
                                                                        "object-delivery.filename";
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractObjectDeliveryServlet.class);

  /**
   * Create a unique value per server startup. This is the ETag value for all
   * resources streamed from this servlet, since it uses only ClassPath
   * resources that may only change upon new initialisation of this class.
   * Therefore the ETag value is calculated only once and used to stream all
   * classpath resources.
   */
  protected static final String ETAG_VALUE_OBJECT_DELIVERY_SERVLET = '"' +
                                                                     Long.toString (RandomHelper.getRandom ()
                                                                                                .nextLong ()) +
                                                                     '"';

  private final ICommonsOrderedSet <String> m_aDeniedFilenames = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aDeniedExtensions = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aDeniedRegExs = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aAllowedFilenames = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aAllowedExtensions = new CommonsLinkedHashSet <> ();
  private final ICommonsOrderedSet <String> m_aAllowedRegExs = new CommonsLinkedHashSet <> ();
  private boolean m_bDeniedAllExtensions = false;
  private boolean m_bAllowedAllExtensions = false;

  protected AbstractObjectDeliveryServlet ()
  {}

  /**
   * Get the unified (=lowercased) version of the passed String
   *
   * @param sItem
   *        The string to unify. May not be <code>null</code>.
   * @return The unified version and never <code>null</code>.
   */
  @Nonnull
  protected static final String getUnifiedItem (@Nonnull final String sItem)
  {
    final String ret = sItem.toLowerCase (Locale.US);
    return ret;
  }

  /**
   * Helper function to convert the configuration string to a collection.
   *
   * @param aSet
   *        The set to be filled. May not be <code>null</code>.
   * @param sItemList
   *        The string to be separated to a list. Each item is separated by a
   *        ",".
   * @param bUnify
   *        To unify the found item by converting them all to lowercase. This
   *        makes only sense for file extensions but not for file names. This
   *        unification is only relevant because of the case insensitive file
   *        system on Windows machines.
   */
  private static void _initialFillSet (@Nonnull final ICommonsOrderedSet <String> aSet,
                                       @Nullable final String sItemList,
                                       final boolean bUnify)
  {
    ValueEnforcer.notNull (aSet, "Set");
    if (!aSet.isEmpty ())
      throw new IllegalArgumentException ("The provided set must be empty, but it is not: " + aSet);

    if (StringHelper.hasText (sItemList))
    {
      // Perform some default replacements to avoid updating all references at
      // once before splitting
      final String sRealItemList = StringHelper.replaceAll (sItemList,
                                                            EXTENSION_MACRO_WEB_DEFAULT,
                                                            "js,css,png,jpg,jpeg,gif,eot,svg,ttf,woff,woff2,map");

      for (final String sItem : StringHelper.getExploded (',', sRealItemList))
      {
        String sRealItem = sItem.trim ();
        if (bUnify)
          sRealItem = getUnifiedItem (sRealItem);

        // Add only non-empty items
        if (StringHelper.hasText (sRealItem))
          aSet.add (sRealItem);
      }
    }
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected final void onInit () throws ServletException
  {
    super.onInit ();

    _initialFillSet (m_aDeniedFilenames, getInitParameter (INITPARAM_DENIED_FILENAMES), false);
    _initialFillSet (m_aDeniedExtensions, getInitParameter (INITPARAM_DENIED_EXTENSIONS), true);
    _initialFillSet (m_aDeniedRegExs, getInitParameter (INITPARAM_DENIED_REG_EXS), false);
    m_bDeniedAllExtensions = m_aDeniedExtensions.contains (INITPARAM_VALUE_WILDCARD);

    _initialFillSet (m_aAllowedFilenames, getInitParameter (INITPARAM_ALLOWED_FILENAMES), false);
    _initialFillSet (m_aAllowedExtensions, getInitParameter (INITPARAM_ALLOWED_EXTENSIONS), true);
    _initialFillSet (m_aAllowedRegExs, getInitParameter (INITPARAM_ALLOWED_REG_EXS), false);
    m_bAllowedAllExtensions = m_aAllowedExtensions.contains (INITPARAM_VALUE_WILDCARD);

    if (s_aLogger.isDebugEnabled ())
    {
      s_aLogger.debug ("Settings for " +
                       getClass ().getName () +
                       ": " +
                       INITPARAM_DENIED_FILENAMES +
                       "=" +
                       m_aDeniedFilenames +
                       "; " +
                       INITPARAM_DENIED_EXTENSIONS +
                       "=" +
                       m_aDeniedExtensions +
                       "; " +
                       INITPARAM_DENIED_REG_EXS +
                       "=" +
                       m_aDeniedRegExs +
                       "; " +
                       INITPARAM_ALLOWED_FILENAMES +
                       "=" +
                       m_aAllowedFilenames +
                       "; " +
                       INITPARAM_ALLOWED_EXTENSIONS +
                       "=" +
                       m_aAllowedExtensions +
                       "; " +
                       INITPARAM_ALLOWED_REG_EXS +
                       "=" +
                       m_aAllowedRegExs);
    }

    // Short hint, as this may render the whole servlet senseless...
    if (m_bDeniedAllExtensions)
      s_aLogger.warn ("All extensions are denied in " +
                      getClass ().getName () +
                      ". This means that this servlet will not deliver any resource!");
    else
      if (m_aAllowedFilenames.isEmpty () && m_aAllowedExtensions.isEmpty () && m_aAllowedRegExs.isEmpty ())
        s_aLogger.warn ("No allowance rules are defined in " +
                        getClass ().getName () +
                        ". This means that this servlet will not deliver any resource!");
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final ICommonsOrderedSet <String> getAllDeniedFilenames ()
  {
    return m_aDeniedFilenames.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final ICommonsOrderedSet <String> getAllDeniedExtensions ()
  {
    return m_aDeniedExtensions.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final ICommonsOrderedSet <String> getAllDeniedRegExs ()
  {
    return m_aDeniedRegExs.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final ICommonsOrderedSet <String> getAllAllowedFilenames ()
  {
    return m_aAllowedFilenames.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final ICommonsOrderedSet <String> getAllAllowedExtensions ()
  {
    return m_aAllowedExtensions.getClone ();
  }

  @Nonnull
  @ReturnsMutableCopy
  protected final ICommonsOrderedSet <String> getAllAllowedRegExs ()
  {
    return m_aAllowedRegExs.getClone ();
  }

  protected final boolean isDenyAllExtensions ()
  {
    return m_bDeniedAllExtensions;
  }

  protected final boolean isAllowAllExtensions ()
  {
    return m_bAllowedAllExtensions;
  }

  protected final boolean isValidFilenameAccordingToTheRules (@Nullable final String sRelativeFilename)
  {
    final String sFilename = FilenameHelper.getWithoutPath (sRelativeFilename);
    final String sUnifiedExt = getUnifiedItem (FilenameHelper.getExtension (sFilename));

    // Avoid directory listings, because when an existing directory is passed
    // the URLResource of that directory will return a directory listing, which
    // is surely out of scope for a delivery servlet!
    if (StringHelper.endsWith (sRelativeFilename, "/") || StringHelper.hasNoText (sFilename))
    {
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Denied object with name '" + sRelativeFilename + "' because it is empty");
      return false;
    }

    // Denial has precedence
    if (m_aDeniedFilenames.contains (sFilename))
    {
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Denied object with name '" +
                         sRelativeFilename +
                         "' because it is in the denied filenames list");
      return false;
    }

    if (m_bDeniedAllExtensions || m_aDeniedExtensions.contains (sUnifiedExt))
    {
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Denied object with name '" +
                         sRelativeFilename +
                         "' because it is in the denied extension list");
      return false;
    }

    if (!m_aDeniedRegExs.isEmpty ())
      for (final String sDeniedRegEx : m_aDeniedRegExs)
        if (RegExHelper.stringMatchesPattern (sDeniedRegEx, sFilename))
        {
          if (s_aLogger.isDebugEnabled ())
            s_aLogger.debug ("Denied object with name '" +
                             sRelativeFilename +
                             "' because it is in the denied regex list");
          return false;
        }

    // Allowance comes next
    if (m_aAllowedFilenames.contains (sFilename))
    {
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Allowed object with name '" +
                         sRelativeFilename +
                         "' because it is in the allowed filenames list");
      return true;
    }

    if (m_bAllowedAllExtensions || m_aAllowedExtensions.contains (sUnifiedExt))
    {
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Allowed object with name '" +
                         sRelativeFilename +
                         "' because it is in the allowed extension list");
      return true;
    }

    if (!m_aAllowedRegExs.isEmpty ())
      for (final String sAllowedRegEx : m_aAllowedRegExs)
        if (RegExHelper.stringMatchesPattern (sAllowedRegEx, sFilename))
        {
          if (s_aLogger.isDebugEnabled ())
            s_aLogger.debug ("Allowed object with name '" +
                             sRelativeFilename +
                             "' because it is in the allowed regex list");
          return true;
        }

    // Neither denied nor allowed -> deny for the sake of security
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Denied object with name '" + sRelativeFilename + "' because it is neither denied nor allowed");
    return false;
  }

  protected static final boolean isPossibleDirectoryTraversalRequest (@Nonnull final String sFilename)
  {
    return sFilename.indexOf ("/..") >= 0 ||
           sFilename.indexOf ("../") >= 0 ||
           sFilename.indexOf ("\\..") >= 0 ||
           sFilename.indexOf ("..\\") >= 0;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    // cut the leading "/"
    final String sFilename = URLHelper.urlDecode (aRequestScope.getPathWithinServlet ());

    if (StringHelper.hasNoText (sFilename) ||
        !isValidFilenameAccordingToTheRules (sFilename) ||
        isPossibleDirectoryTraversalRequest (sFilename))
    {
      // Send the same error code as if it is simply not found to confuse
      // attackers :)
      s_aLogger.warn ("Illegal delivery request '" + sFilename + "'");
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
      return EContinue.BREAK;
    }

    // Filename seems to be safe
    aRequestScope.setAttribute (REQUEST_ATTR_OBJECT_DELIVERY_FILENAME, sFilename);
    return EContinue.CONTINUE;
  }

  @Override
  @Nullable
  protected final String getSupportedETag (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return ETAG_VALUE_OBJECT_DELIVERY_SERVLET;
  }

  protected abstract void onDeliverResource (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                             @Nonnull UnifiedResponse aUnifiedResponse,
                                             @Nonnull String sFilename) throws IOException;

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException, IOException
  {
    // The request has been checked and the filename is valid for delivery
    final String sFilename = aRequestScope.getAttributeAsString (REQUEST_ATTR_OBJECT_DELIVERY_FILENAME);
    onDeliverResource (aRequestScope, aUnifiedResponse, sFilename);

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Delivered object with name '" + sFilename + "'");
  }
}
