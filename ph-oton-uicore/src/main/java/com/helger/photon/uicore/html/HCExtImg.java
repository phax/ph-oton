/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html;

import com.helger.base.string.StringHelper;
import com.helger.commons.gfx.ImageDataManager;
import com.helger.html.hc.html.embedded.AbstractHCImg;
import com.helger.io.resource.IReadableResource;
import com.helger.photon.io.WebFileIO;
import com.helger.servlet.ServletContextPathHolder;
import com.helger.servlet.request.RequestHelper;
import com.helger.url.ISimpleURL;
import com.helger.url.SimpleURL;

import jakarta.annotation.Nonnull;

/**
 * An extended image that tries to deduce the extent from the image payload.
 *
 * @author Philip Helger
 */
public class HCExtImg extends AbstractHCImg <HCExtImg>
{
  private final IReadableResource m_aImgResource;

  public HCExtImg (@Nonnull final ISimpleURL aSrc)
  {
    setSrc (aSrc);

    // Remove the session ID (if any)
    String sCleanPath = RequestHelper.getWithoutSessionID (aSrc.getPath ());

    // Remove the context path (if any)
    final String sContextPath = ServletContextPathHolder.getContextPath ();
    if (StringHelper.isNotEmpty (sContextPath) && sCleanPath.startsWith (sContextPath))
    {
      // URI contains context path
      final String sPath = sCleanPath.substring (sContextPath.length ());
      sCleanPath = sPath.length () > 0 ? sPath : "/";
    }

    final String sPureSrc = new SimpleURL (sCleanPath, aSrc.params (), aSrc.getAnchor ()).getAsString ();
    m_aImgResource = WebFileIO.getServletContextIO ().getResource (sPureSrc);
    setExtent (ImageDataManager.getInstance ().getImageSize (m_aImgResource));
  }

  /**
   * @return The readable resource that was used to determine the image size. It
   *         might be a non-existing resource.
   * @since 8.2.10
   */
  @Nonnull
  public final IReadableResource getImgResource ()
  {
    return m_aImgResource;
  }
}
