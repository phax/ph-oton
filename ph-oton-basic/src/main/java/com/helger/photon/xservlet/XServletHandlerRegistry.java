package com.helger.photon.xservlet;

import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsEnumMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.string.StringHelper;
import com.helger.http.EHTTPMethod;

final class XServletHandlerRegistry
{
  /** The main handler map */
  private final ICommonsMap <EHTTPMethod, IXServletHandler> m_aHandler = new CommonsEnumMap <> (EHTTPMethod.class);

  public XServletHandlerRegistry ()
  {}

  /**
   * Register a handler for the provided HTTP method. If another handler is
   * already registered, the new registration overwrites the old one.
   *
   * @param eHTTPMethod
   *        The HTTP method to register for. May not be <code>null</code>.
   * @param aHandler
   *        The handler to register. May not be <code>null</code>.
   */
  void registerHandler (@Nonnull final EHTTPMethod eHTTPMethod, @Nonnull final IXServletHandler aHandler)
  {
    ValueEnforcer.notNull (eHTTPMethod, "HTTPMethod");
    ValueEnforcer.notNull (aHandler, "Handler");

    if (m_aHandler.containsKey (eHTTPMethod))
      throw new IllegalStateException ("An HTTP handler for HTTP method " + eHTTPMethod + " is already registered!");
    m_aHandler.put (eHTTPMethod, aHandler);
  }

  @Nonnull
  @ReturnsMutableCopy
  EnumSet <EHTTPMethod> getAllowedHTTPMethods ()
  {
    // Return all methods for which handlers are registered
    final EnumSet <EHTTPMethod> ret = EnumSet.copyOf (m_aHandler.keySet ());
    if (!ret.contains (EHTTPMethod.GET))
    {
      // If GET is not supported, HEAD is also not supported
      ret.remove (EHTTPMethod.HEAD);
    }
    return ret;
  }

  @Nonnull
  String getAllowedHttpMethodsString ()
  {
    return StringHelper.getImplodedMapped (", ", getAllowedHTTPMethods (), EHTTPMethod::getName);
  }

  @Nullable
  IXServletHandler getHandler (@Nonnull final EHTTPMethod eHttpMethod)
  {
    return m_aHandler.get (eHttpMethod);
  }
}
