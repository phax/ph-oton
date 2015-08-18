package com.helger.photon.uictrls.datatables.plugins;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.state.ETriState;
import com.helger.commons.string.StringHelper;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

public class DTPButtonsButton
{
  public static final boolean DEFAULT_ENABLED = true;

  /** function action( e, dt, node, config ) */
  private JSAnonymousFunction m_aAction;
  /** function available( dt, config ) */
  private JSAnonymousFunction m_aAvailable;
  /** Set the class name for the button. */
  private HCHasCSSClasses m_aClassNames;
  /** function destroy( dt, node, config ) */
  private JSAnonymousFunction m_aDestroy;
  /** Set a button's initial enabled state. */
  private ETriState m_eEnabled = ETriState.UNDEFINED;
  /** Define which button type the button should be based on. */
  private String m_sExtend;
  /** function init( dt, node, config ) */
  private JSAnonymousFunction m_aInit;
  /** Define an activation key for a button. */
  private DTPButtonsButtonKey m_aKey;
  /** Set a name for each selection. */
  private String m_sName;
  /** Unique namespace for every button. */
  private String m_sNamespace;
  /**
   * The text to show in the button. Text, HTML or function text( dt, node,
   * config )
   */
  private IJSExpression m_aText;

  @Nonnull
  public DTPButtonsButton setAction (@Nullable final JSAnonymousFunction aAction)
  {
    m_aAction = aAction;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setAvailable (@Nullable final JSAnonymousFunction aAvailable)
  {
    m_aAvailable = aAvailable;
    return this;
  }

  public boolean containsClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return m_aClassNames.containsClass (aCSSClassProvider);
  }

  @Nonnull
  public DTPButtonsButton addClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.addClass (aCSSClassProvider);
    return this;
  }

  @Deprecated
  @Nonnull
  public DTPButtonsButton addClasses (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.addClasses (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public DTPButtonsButton addClasses (@Nullable final ICSSClassProvider... aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DTPButtonsButton addClasses (@Nullable final Iterable <? extends ICSSClassProvider> aCSSClassProviders)
  {
    m_aClassNames.addClasses (aCSSClassProviders);
    return this;
  }

  @Nonnull
  public DTPButtonsButton removeClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    m_aClassNames.removeClass (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public DTPButtonsButton removeAllClasses ()
  {
    m_aClassNames.removeAllClasses ();
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <ICSSClassProvider> getAllClasses ()
  {
    return m_aClassNames.getAllClasses ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllClassNames ()
  {
    return m_aClassNames.getAllClassNames ();
  }

  @Nullable
  public String getAllClassesAsString ()
  {
    return m_aClassNames.getAllClassesAsString ();
  }

  public boolean hasAnyClass ()
  {
    return m_aClassNames.hasAnyClass ();
  }

  @Nonnull
  public DTPButtonsButton setDestroy (@Nullable final JSAnonymousFunction aDestroy)
  {
    m_aDestroy = aDestroy;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setEnabled (final boolean bEnabled)
  {
    return setEnabled (ETriState.valueOf (bEnabled));
  }

  @Nonnull
  public DTPButtonsButton setEnabled (@Nonnull final ETriState eEnabled)
  {
    ValueEnforcer.notNull (eEnabled, "Enabled");
    m_eEnabled = eEnabled;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setExtend (@Nullable final String sExtend)
  {
    m_sExtend = sExtend;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setInit (@Nullable final JSAnonymousFunction aInit)
  {
    m_aInit = aInit;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setKey (@Nullable final DTPButtonsButtonKey aKey)
  {
    m_aKey = aKey;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setNamespace (@Nullable final String sNamespace)
  {
    m_sNamespace = sNamespace;
    return this;
  }

  @Nonnull
  public DTPButtonsButton setText (@Nullable final String sText)
  {
    return setText (sText == null ? null : JSExpr.lit (sText));
  }

  @Nonnull
  public DTPButtonsButton setText (@Nullable final IJSExpression aText)
  {
    m_aText = aText;
    return this;
  }

  @Nonnull
  public JSAssocArray getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_aAction != null)
      ret.add ("action", m_aAction);
    if (m_aAvailable != null)
      ret.add ("available", m_aAvailable);
    final String sClasses = getAllClassesAsString ();
    if (StringHelper.hasText (sClasses))
      ret.add ("className", sClasses);
    if (m_aDestroy != null)
      ret.add ("destroy", m_aDestroy);
    if (m_eEnabled.isDefined ())
      ret.add ("enabled", m_eEnabled.getAsBooleanValue (DEFAULT_ENABLED));
    if (StringHelper.hasText (m_sExtend))
      ret.add ("extend", m_sExtend);
    if (m_aInit != null)
      ret.add ("init", m_aInit);
    if (m_aKey != null)
      ret.add ("key", m_aKey.getAsJS ());
    if (StringHelper.hasText (m_sName))
      ret.add ("name", m_sName);
    if (StringHelper.hasText (m_sNamespace))
      ret.add ("namespace", m_sNamespace);
    if (m_aText != null)
      ret.add ("text", m_aText);
    return ret;
  }
}
