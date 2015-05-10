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

import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.charset.CCharset;
import com.helger.photon.core.action.servlet.PublicApplicationActionServlet;
import com.helger.photon.core.action.servlet.SecureApplicationActionServlet;
import com.helger.photon.core.ajax.servlet.PublicApplicationAjaxServlet;
import com.helger.photon.core.ajax.servlet.SecureApplicationAjaxServlet;
import com.helger.photon.core.resource.ResourceBundleServlet;
import com.helger.photon.core.servlet.AbstractObjectDeliveryServlet;
import com.helger.photon.core.servlet.CharacterEncodingFilter;
import com.helger.photon.core.servlet.LogoutServlet;
import com.helger.photon.core.servlet.StreamServlet;
import com.helger.photon.core.userdata.UserStreamServlet;
import com.helger.photon.core.userdata.UserUploadServlet;

@ThreadSafe
public final class PhotonStubInitializer
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonStubInitializer.class);
  private static final AtomicBoolean s_aInitialized = new AtomicBoolean (false);

  @PresentForCodeCoverage
  private static final PhotonStubInitializer s_aInstance = new PhotonStubInitializer ();

  private PhotonStubInitializer ()
  {}

  public static boolean isInitialized ()
  {
    return s_aInitialized.get ();
  }

  public static void init (@Nonnull final ServletContext aSC)
  {
    if (s_aInitialized.compareAndSet (false, true))
    {
      if (aSC.getMajorVersion () < 3)
        throw new IllegalStateException ("At least servlet version 3 is required!");

      s_aLogger.info ("Registering default ph-oton listeners and servlets");

      {
        final FilterRegistration.Dynamic aFilter = aSC.addFilter ("CharacterEncodingFilter",
                                                                  CharacterEncodingFilter.class);
        aFilter.setInitParameter (CharacterEncodingFilter.INITPARAM_ENCODING, CCharset.CHARSET_UTF_8);
        aFilter.setInitParameter (CharacterEncodingFilter.INITPARAM_FORCE_ENCODING, Boolean.TRUE.toString ());
        aFilter.addMappingForUrlPatterns (null, false, "/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("SecureApplicationActionServlet",
                                                                     SecureApplicationActionServlet.class);
        aServlet.addMapping ("/secureaction/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("PublicApplicationActionServlet",
                                                                     PublicApplicationActionServlet.class);
        aServlet.addMapping ("/publicaction/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("SecureApplicationAjaxServlet",
                                                                     SecureApplicationAjaxServlet.class);
        aServlet.addMapping ("/secureajax/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("PublicApplicationAjaxServlet",
                                                                     PublicApplicationAjaxServlet.class);
        aServlet.addMapping ("/publicajax/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("StreamServlet", StreamServlet.class);
        aServlet.setInitParameter (AbstractObjectDeliveryServlet.INITPARAM_ALLOWED_EXTENSIONS,
                                   AbstractObjectDeliveryServlet.EXTENSION_MACRO_WEB_DEFAULT);
        aServlet.addMapping ("/stream/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("UserStreamServlet", UserStreamServlet.class);
        aServlet.setInitParameter (AbstractObjectDeliveryServlet.INITPARAM_ALLOWED_EXTENSIONS,
                                   AbstractObjectDeliveryServlet.EXTENSION_MACRO_WEB_DEFAULT);
        aServlet.addMapping ("/user/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("UserUploadServlet", UserUploadServlet.class);
        aServlet.addMapping ("/userUpload/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("LogoutServlet", LogoutServlet.class);
        aServlet.addMapping ("/logout/*");
      }

      {
        final ServletRegistration.Dynamic aServlet = aSC.addServlet ("ResourceBundleServlet",
                                                                     ResourceBundleServlet.class);
        aServlet.setInitParameter (AbstractObjectDeliveryServlet.INITPARAM_ALLOWED_EXTENSIONS, "js,css");
        aServlet.addMapping ("/resbundle/*");
      }

      {
        aSC.addListener (PhotonStubServletContextListener.class);
      }

      s_aLogger.info ("Finished registering default ph-oton listeners and servlets");
    }
  }
}
