package com.helger.html.js;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.style.PrivateAPI;

/**
 * A special ph-oton internal class that is used to differentiate arbitrary "unparsed" JS from
 * predefined JS.
 *
 * @author Philip Helger
 * @since 10.1.2
 */
@PrivateAPI
public final class PhotonInternalUnparsedJS extends UnparsedJSCodeProvider
{
  public PhotonInternalUnparsedJS (@NonNull final String sJSCode)
  {
    super (sJSCode);
  }
}
