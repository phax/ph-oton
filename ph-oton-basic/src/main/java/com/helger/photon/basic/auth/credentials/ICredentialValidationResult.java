package com.helger.photon.basic.auth.credentials;

import java.io.Serializable;

import com.helger.commons.state.ISuccessIndicator;
import com.helger.commons.text.display.IHasDisplayText;

/**
 * Base interface for the credential validation result.
 * 
 * @author Philip Helger
 */
public interface ICredentialValidationResult extends ISuccessIndicator, IHasDisplayText, Serializable
{
  /* empty */
}
