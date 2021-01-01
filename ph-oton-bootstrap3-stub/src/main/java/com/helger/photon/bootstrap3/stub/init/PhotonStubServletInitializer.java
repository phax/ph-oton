/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.stub.init;

import java.nio.charset.StandardCharsets;
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
import com.helger.photon.ajax.servlet.PhotonAjaxServlet;
import com.helger.photon.api.servlet.PhotonAPIServlet;
import com.helger.photon.bootstrap3.stub.PhotonStubServletContextListener;
import com.helger.photon.core.go.GoServlet;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.photon.core.servlet.AbstractObjectDeliveryHttpHandler;
import com.helger.photon.core.servlet.LogoutServlet;
import com.helger.photon.core.servlet.StreamServlet;
import com.helger.photon.core.userdata.UserStreamServlet;
import com.helger.photon.core.userdata.UserUploadServlet;
import com.helger.servlet.filter.CharacterEncodingFilter;

/**
 * This class dynamically registers the ph-oton servlets into the
 * {@link ServletContext} provided.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonStubServletInitializer
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonStubServletInitializer.class);
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

      LOGGER.info ("Registering default ph-oton listeners and servlets");

      {
        final FilterRegistration.Dynamic aFilter = aSC.addFilter ("CharacterEncodingFilter", CharacterEncodingFilter.class);
        if (aFilter != null)
        {
          // Filter is new
          aFilter.setAsyncSupported (true);
          aFilter.setInitParameter (CharacterEncodingFilter.INITPARAM_ENCODING, StandardCharsets.UTF_8.name ());
          aFilter.setInitParameter (CharacterEncodingFilter.INITPARAM_FORCE_ENCODING, Boolean.TRUE.toString ());
          aFilter.addMappingForUrlPatterns (null, false, "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("PhotonAjaxServlet", PhotonAjaxServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.addMapping (PhotonAjaxServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("PhotonAPIServlet", PhotonAPIServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.addMapping (PhotonAPIServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("StreamServlet", StreamServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.setInitParameter (AbstractObjectDeliveryHttpHandler.INITPARAM_ALLOWED_EXTENSIONS,
                                     AbstractObjectDeliveryHttpHandler.EXTENSION_MACRO_WEB_DEFAULT);
          aServlet.addMapping (StreamServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("UserStreamServlet", UserStreamServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.setInitParameter (AbstractObjectDeliveryHttpHandler.INITPARAM_ALLOWED_EXTENSIONS,
                                     AbstractObjectDeliveryHttpHandler.EXTENSION_MACRO_WEB_DEFAULT);
          aServlet.addMapping (UserStreamServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("UserUploadServlet", UserUploadServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.addMapping (UserUploadServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("LogoutServlet", LogoutServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.addMapping (LogoutServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("ResourceBundleServlet", ResourceBundleServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.setInitParameter (AbstractObjectDeliveryHttpHandler.INITPARAM_ALLOWED_EXTENSIONS, "js,css");
          aServlet.addMapping (ResourceBundleServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("GoServlet", GoServlet.class);
        if (aServlet != null)
        {
          aServlet.setAsyncSupported (true);
          aServlet.addMapping (GoServlet.SERVLET_DEFAULT_PATH + "/*");
        }
      }

      {
        aSC.addListener (PhotonStubServletContextListener.class);
      }

      LOGGER.info ("Finished registering default ph-oton listeners and servlets");
    }
  }
}
