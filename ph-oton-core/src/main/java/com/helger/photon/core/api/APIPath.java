package com.helger.photon.core.api;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.http.EHTTPMethod;

/**
 * This class contains a combination of path and HTTP method.
 *
 * @author Philip Helger
 */
@Immutable
public class APIPath
{
  private final EHTTPMethod m_eMethod;
  private final String m_sPath;

  public APIPath (@Nonnull final EHTTPMethod eMethod, @Nonnull @Nonempty final String sPath)
  {
    m_eMethod = ValueEnforcer.notNull (eMethod, "Method");
    m_sPath = ValueEnforcer.notEmpty (sPath, "Path");
  }

  /**
   * @return The HTTP method as provided in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  public EHTTPMethod getHTTPMethod ()
  {
    return m_eMethod;
  }

  /**
   * @return The path as provided in the constructor. Neither <code>null</code>
   *         nor empty.
   */
  @Nonnull
  @Nonempty
  public String getPath ()
  {
    return m_sPath;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final APIPath rhs = (APIPath) o;
    return m_eMethod.equals (rhs.m_eMethod) && m_sPath.equals (rhs.m_sPath);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eMethod).append (m_sPath).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (null).append ("HTTPMethod", m_eMethod).append ("Path", m_sPath).toString ();
  }

  @Nonnull
  public static APIPath get (@Nonnull @Nonempty final String sPath)
  {
    return new APIPath (EHTTPMethod.GET, sPath);
  }

  @Nonnull
  public static APIPath post (@Nonnull @Nonempty final String sPath)
  {
    return new APIPath (EHTTPMethod.POST, sPath);
  }

  @Nonnull
  public static APIPath put (@Nonnull @Nonempty final String sPath)
  {
    return new APIPath (EHTTPMethod.PUT, sPath);
  }

  @Nonnull
  public static APIPath delete (@Nonnull @Nonempty final String sPath)
  {
    return new APIPath (EHTTPMethod.DELETE, sPath);
  }
}
