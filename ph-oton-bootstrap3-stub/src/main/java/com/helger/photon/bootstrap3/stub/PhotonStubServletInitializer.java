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
package com.helger.photon.bootstrap3.stub;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.charset.CCharset;
import com.helger.photon.core.action.servlet.PublicApplicationActionServlet;
import com.helger.photon.core.action.servlet.SecureApplicationActionServlet;
import com.helger.photon.core.ajax.servlet.PublicApplicationAjaxServlet;
import com.helger.photon.core.ajax.servlet.SecureApplicationAjaxServlet;
import com.helger.photon.core.go.GoServlet;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.photon.core.servlet.AbstractObjectDeliveryServlet;
import com.helger.photon.core.servlet.CharacterEncodingFilter;
import com.helger.photon.core.servlet.LogoutServlet;
import com.helger.photon.core.servlet.StreamServlet;
import com.helger.photon.core.userdata.UserStreamServlet;
import com.helger.photon.core.userdata.UserUploadServlet;

/**
 * This class dynamically registers the ph-oton servlets into the
 * {@link ServletContext} provided.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonStubServletInitializer
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonStubServletInitializer.class);
  private static final AtomicBoolean s_aInitialized = new AtomicBoolean (false);

  @PresentForCodeCoverage
  private static final PhotonStubServletInitializer s_aInstance = new PhotonStubServletInitializer ();

  private PhotonStubServletInitializer ()
  {}

  /**
   * @return <code>true</code> if the servlet registration already took place,
   *         <code>false</code> otherwise.
   */
  public static boolean areServletsRegistered ()
  {
    return s_aInitialized.get ();
  }

  /**
   * Register all ph-oton servlets to the passed {@link ServletContext}.
   * 
   * @param aSC
   *        The {@link ServletContext} to use. May not be <code>null</code>.
   */
  public static void registerServlets (@Nonnull final ServletContext aSC)
  {
    ValueEnforcer.notNull (aSC, "ServletContext");

    if (s_aInitialized.compareAndSet (false, true))
    {
      // Check SC version
      if (aSC.getMajorVersion () < 3)
        throw new IllegalStateException ("At least servlet version 3 is required! Currently running version " +
                                         aSC.getMajorVersion () +
                                         "." +
                                         aSC.getMinorVersion ());

      s_aLogger.info ("Registering default ph-oton listeners and servlets");

      {
        final FilterRegistration.Dynamic aFilter = aSC.addFilter ("CharacterEncodingFilter",
                                                                  CharacterEncodingFilter.class);
        if (aFilter != null)
        {
          // Filter is new
          aFilter.setInitParameter (CharacterEncodingFilter.INITPARAM_ENCODING, CCharset.CHARSET_UTF_8);
          aFilter.setInitParameter (CharacterEncodingFilter.INITPARAM_FORCE_ENCODING, Boolean.TRUE.toString ());
          aFilter.addMappingForUrlPatterns (null, false, "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("SecureApplicationActionServlet",
                                                                     SecureApplicationActionServlet.class);
        if (aServlet != null)
          aServlet.addMapping (SecureApplicationActionServlet.SERVLET_DEFAULT_PATH + "/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("PublicApplicationActionServlet",
                                                                     PublicApplicationActionServlet.class);
        if (aServlet != null)
          aServlet.addMapping (PublicApplicationActionServlet.SERVLET_DEFAULT_PATH + "/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("SecureApplicationAjaxServlet",
                                                                     SecureApplicationAjaxServlet.class);
        if (aServlet != null)
          aServlet.addMapping (SecureApplicationAjaxServlet.SERVLET_DEFAULT_PATH + "/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("PublicApplicationAjaxServlet",
                                                                     PublicApplicationAjaxServlet.class);
        if (aServlet != null)
          aServlet.addMapping (PublicApplicationAjaxServlet.SERVLET_DEFAULT_PATH + "/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("StreamServlet", StreamServlet.class);
        if (aServlet != null)
        {
          aServlet.setInitParameter (AbstractObjectDeliveryServlet.INITPARAM_ALLOWED_EXTENSIONS,
                                     AbstractObjectDeliveryServlet.EXTENSION_MACRO_WEB_DEFAULT);
          aServlet.addMapping (StreamServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("UserStreamServlet", UserStreamServlet.class);
        if (aServlet != null)
        {
          aServlet.setInitParameter (AbstractObjectDeliveryServlet.INITPARAM_ALLOWED_EXTENSIONS,
                                     AbstractObjectDeliveryServlet.EXTENSION_MACRO_WEB_DEFAULT);
          aServlet.addMapping (UserStreamServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("UserUploadServlet", UserUploadServlet.class);
        if (aServlet != null)
          aServlet.addMapping (UserUploadServlet.SERVLET_DEFAULT_PATH + "/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("LogoutServlet", LogoutServlet.class);
        if (aServlet != null)
          aServlet.addMapping (LogoutServlet.SERVLET_DEFAULT_PATH + "/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("ResourceBundleServlet",
                                                                     ResourceBundleServlet.class);
        if (aServlet != null)
        {
          aServlet.setInitParameter (AbstractObjectDeliveryServlet.INITPARAM_ALLOWED_EXTENSIONS, "js,css");
          aServlet.addMapping (ResourceBundleServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("GoServlet", GoServlet.class);
        if (aServlet != null)
          aServlet.addMapping (GoServlet.SERVLET_DEFAULT_PATH + "/*");
      }

      {
        aSC.addListener (PhotonStubConfigurationListener.class);
      }

      s_aLogger.info ("Finished registering default ph-oton listeners and servlets");
    }
  }
}
