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
package com.helger.photon.basic.app.request;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsIterable;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLParameter;
import com.helger.commons.url.URLParameterList;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * An implementation of {@link IRequestParameterHandler} that takes the request
 * parameters from the URL path in the fixed order locale than menu item. It
 * build URLs in the form <code>basePath/<i>locale</i>/<i>menuItem</code>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@Immutable
public class RequestParameterHandlerURLPathOrdered implements IRequestParameterHandler
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (RequestParameterHandlerURLPathOrdered.class);

  /**
   * Default constructor
   */
  public RequestParameterHandlerURLPathOrdered ()
  {}

  @Nonnull
  protected URLParameterList getParametersFromPath (@Nonnull final String sPath)
  {
    // Use paths for standard menu items
    final URLParameterList ret = new URLParameterList ();

    final IRequestParameterManager aRequestParamMgr = ApplicationRequestManager.getRequestMgr ();
    boolean bHasLocale = false;
    boolean bHasMenuItem = false;

    for (final String sParamValue : StringHelper.getExploded ('/', StringHelper.trimStartAndEnd (sPath, "/")))
    {
      String sParamName = null;
      if (!bHasLocale && aRequestParamMgr.getLocaleFromParameterIfValid (sParamValue) != null)
      {
        // Valid locale was provided
        sParamName = aRequestParamMgr.getRequestParamNameLocale ();
        bHasLocale = true;
      }
      else
        if (!bHasMenuItem && aRequestParamMgr.getMenuItemFromParameterIfValid (sParamValue) != null)
        {
          // Valid menu item was provided
          sParamName = aRequestParamMgr.getRequestParamNameMenuItem ();
          bHasMenuItem = true;
        }
        else
        {
          s_aLogger.warn ("Ignoring superfluous parameter '" + sParamValue + "'");
        }

      // ignore all other parameters
      if (sParamName != null)
        ret.add (sParamName, sParamValue);
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public URLParameterList getParametersFromRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return getParametersFromPath (aRequestScope.getPathInfo ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public URLParameterList getParametersFromURL (@Nonnull final ISimpleURL aURL)
  {
    return getParametersFromPath (aURL.getPath ());
  }

  @Nonnull
  public SimpleURL buildURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull @Nonempty final String sBasePath,
                             @Nullable final ICommonsIterable <? extends URLParameter> aParameters)
  {
    // Encode menuitem parameter into path
    final StringBuilder aFullPath = new StringBuilder (sBasePath);
    if (aParameters != null)
    {
      final IRequestParameterManager aRequestParamMgr = ApplicationRequestManager.getRequestMgr ();
      final String sParamNameLocale = aRequestParamMgr.getRequestParamNameLocale ();
      final String sParamNameMenuItem = aRequestParamMgr.getRequestParamNameMenuItem ();

      String sLocale = null;
      String sMenuItemID = null;
      for (final URLParameter aParam : aParameters)
      {
        final String sParamName = aParam.getName ();
        if (sParamName.equals (sParamNameLocale))
          sLocale = aParam.getValue ();
        else
          if (sParamName.equals (sParamNameMenuItem))
            sMenuItemID = aParam.getValue ();
          else
            s_aLogger.warn ("Ignoring superfluous provided parameter '" +
                            sParamName +
                            "' with value '" +
                            aParam.getValue () +
                            "'");
      }

      if (StringHelper.hasText (sLocale))
        aFullPath.append ('/').append (sLocale);
      if (StringHelper.hasText (sMenuItemID))
        aFullPath.append ('/').append (sMenuItemID);
    }

    return new SimpleURL (aRequestScope.encodeURL (aFullPath.toString ()));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
