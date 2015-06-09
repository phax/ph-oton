package com.helger.photon.bootstrap3.pages.sysinfo;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.charset.CCharset;
import com.helger.commons.id.IHasID;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.name.IHasDescription;
import com.helger.commons.string.StringHelper;
import com.helger.photon.uictrls.prism.EPrismLanguage;

@NotThreadSafe
public class ConfigurationFile implements IHasID <String>, IHasDescription
{
  private final IReadableResource m_aRes;
  private String m_sDescription;
  private Charset m_aDefaultCharset = CCharset.CHARSET_UTF_8_OBJ;
  private EPrismLanguage m_eSyntaxHighlightLanguage = EPrismLanguage.NONE;

  /**
   * Constructor
   *
   * @param aRes
   *        The resource that points to the relevant configuration file.
   */
  public ConfigurationFile (@Nonnull final IReadableResource aRes)
  {
    m_aRes = ValueEnforcer.notNull (aRes, "Resource");
  }

  @Nonnull
  public String getID ()
  {
    return m_aRes.getPath ();
  }

  @Nonnull
  public IReadableResource getResource ()
  {
    return m_aRes;
  }

  public boolean exists ()
  {
    return m_aRes.exists ();
  }

  @Nullable
  public String getContentAsString ()
  {
    return getContentAsString (m_aDefaultCharset);
  }

  @Nullable
  public String getContentAsString (@Nonnull final Charset aCharset)
  {
    return StreamUtils.getAllBytesAsString (m_aRes, aCharset);
  }

  /**
   * @return The optional description of this configuration file. May be
   *         <code>null</code>.
   */
  @Nullable
  public String getDescription ()
  {
    return m_sDescription;
  }

  /**
   * @return <code>true</code> if this configuration file has a description.
   */
  public boolean hasDescription ()
  {
    return StringHelper.hasText (m_sDescription);
  }

  /**
   * Set the description to use.
   * 
   * @param sDescription
   *        The description for this configuration file. May be
   *        <code>null</code>.
   * @return this
   */
  @Nonnull
  public ConfigurationFile setDescription (@Nullable final String sDescription)
  {
    m_sDescription = sDescription;
    return this;
  }

  @Nonnull
  public Charset getDefaultCharset ()
  {
    return m_aDefaultCharset;
  }

  @Nonnull
  public ConfigurationFile setDefaultCharset (@Nonnull final Charset aDefaultCharset)
  {
    m_aDefaultCharset = ValueEnforcer.notNull (aDefaultCharset, "DefaultCharset");
    return this;
  }

  @Nonnull
  public EPrismLanguage getSyntaxHighlightLanguage ()
  {
    return m_eSyntaxHighlightLanguage;
  }

  @Nonnull
  public ConfigurationFile setSyntaxHighlightLanguage (@Nonnull final EPrismLanguage eSyntaxHighlightLanguage)
  {
    m_eSyntaxHighlightLanguage = ValueEnforcer.notNull (eSyntaxHighlightLanguage, "SyntaxHighlightLanguage");
    return this;
  }
}
