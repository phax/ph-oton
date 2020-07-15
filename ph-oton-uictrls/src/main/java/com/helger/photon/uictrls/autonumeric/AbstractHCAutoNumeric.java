/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.autonumeric;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.AbstractHCInput;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.jquery.JQuery;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSGlobal;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.html.IHCNodeWithJSOptions;
import com.helger.html.request.IHCRequestField;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;

/**
 * jQuery autoNumeric plugin from
 *
 * <pre>
 * http://www.decorplanit.com/plugin/
 * </pre>
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractHCAutoNumeric <IMPLTYPE extends AbstractHCAutoNumeric <IMPLTYPE>> extends AbstractHCInput <IMPLTYPE>
                                            implements
                                            IHCNodeWithJSOptions
{
  /** The special CSS class to use for numeric inputs */
  public static final ICSSClassProvider CSS_CLASS_AUTO_NUMERIC_EDIT = DefaultCSSClassProvider.create ("auto-numeric-edit");

  public static final int DEFAULT_MIN_VALUE = -999999999;

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("s_aRWLock")
  private static String s_sDefaultThousandSeparator = null;

  private String m_sThousandSeparator;
  private String m_sDecimalSeparator;
  private Integer m_aDecimalPlaces;
  private BigDecimal m_aMin;
  private BigDecimal m_aMax;
  private EAutoNumericLeadingZero m_eLeadingZero;
  private EAutoNumericRoundingMode m_eRoundingMode;

  public AbstractHCAutoNumeric (@Nullable final IHCRequestField aRF, @Nonnull final Locale aDisplayLocale)
  {
    // Don't use NUMBER here - will create ugly spin buttons
    super (EHCInputType.TEXT);
    if (aRF != null)
    {
      setName (aRF.getFieldName ());
      setValue (aRF.getRequestValue ());
    }
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");

    ensureID ();
    addClass (CSS_CLASS_AUTO_NUMERIC_EDIT);

    // Because the default min value is 0 (in v1.8.2) and we want negative
    // values by default!
    m_aMin = BigDecimal.valueOf (DEFAULT_MIN_VALUE);

    final DecimalFormatSymbols m_aDFS = DecimalFormatSymbols.getInstance (aDisplayLocale);
    m_sThousandSeparator = Character.toString (m_aDFS.getGroupingSeparator ());
    m_sDecimalSeparator = Character.toString (m_aDFS.getDecimalSeparator ());

    // Assign default
    final String sDefaultThousandSeparator = getDefaultThousandSeparator ();
    if (sDefaultThousandSeparator != null)
      m_sThousandSeparator = sDefaultThousandSeparator;
  }

  @Nullable
  public static String getDefaultThousandSeparator ()
  {
    return s_aRWLock.readLockedGet ( () -> s_sDefaultThousandSeparator);
  }

  public static void setDefaultThousandSeparator (@Nullable final String sDefaultThousandSeparator)
  {
    s_aRWLock.writeLockedGet ( () -> s_sDefaultThousandSeparator = sDefaultThousandSeparator);
  }

  @Nullable
  public String getThousandSeparator ()
  {
    return m_sThousandSeparator;
  }

  @Nonnull
  public IMPLTYPE setThousandSeparator (@Nullable final String sThousandSeparator)
  {
    m_sThousandSeparator = sThousandSeparator;
    return thisAsT ();
  }

  @Nullable
  public String getDecimalSeparator ()
  {
    return m_sDecimalSeparator;
  }

  @Nonnull
  public IMPLTYPE setDecimalSeparator (@Nullable final String sDecimalSeparator)
  {
    m_sDecimalSeparator = sDecimalSeparator;
    return thisAsT ();
  }

  @Nullable
  public Integer getDecimalPlaces ()
  {
    return m_aDecimalPlaces;
  }

  @Nonnull
  public IMPLTYPE setDecimalPlaces (final int nDecimalPlaces)
  {
    m_aDecimalPlaces = Integer.valueOf (nDecimalPlaces);
    return thisAsT ();
  }

  private void _checkMinMax ()
  {
    if (m_aMin != null && m_aMax != null && m_aMin.compareTo (m_aMax) > 0)
      throw new IllegalArgumentException ("Min (" + m_aMin + ") must be <= max (" + m_aMax + ")!");
  }

  @Nullable
  public BigDecimal getMin ()
  {
    return m_aMin;
  }

  @Nonnull
  public IMPLTYPE setMin (final long nMin)
  {
    return setMin (BigDecimal.valueOf (nMin));
  }

  @Nonnull
  public IMPLTYPE setMin (final double dMin)
  {
    return setMin (BigDecimal.valueOf (dMin));
  }

  @Nonnull
  public IMPLTYPE setMin (@Nullable final BigDecimal aMin)
  {
    m_aMin = aMin;
    _checkMinMax ();
    return thisAsT ();
  }

  @Nullable
  public BigDecimal getMax ()
  {
    return m_aMax;
  }

  @Nonnull
  public IMPLTYPE setMax (final long nMax)
  {
    return setMax (BigDecimal.valueOf (nMax));
  }

  @Nonnull
  public IMPLTYPE setMax (final double dMax)
  {
    return setMax (BigDecimal.valueOf (dMax));
  }

  @Nonnull
  public IMPLTYPE setMax (@Nullable final BigDecimal aMax)
  {
    m_aMax = aMax;
    _checkMinMax ();
    return thisAsT ();
  }

  @Nullable
  public EAutoNumericLeadingZero getLeadingZero ()
  {
    return m_eLeadingZero;
  }

  @Nonnull
  public IMPLTYPE setLeadingZero (@Nullable final EAutoNumericLeadingZero eLeadingZero)
  {
    m_eLeadingZero = eLeadingZero;
    return thisAsT ();
  }

  @Nullable
  public EAutoNumericRoundingMode getRoundingMode ()
  {
    return m_eRoundingMode;
  }

  @Nonnull
  public IMPLTYPE setRoundingMode (@Nullable final EAutoNumericRoundingMode eRoundingMode)
  {
    m_eRoundingMode = eRoundingMode;
    return thisAsT ();
  }

  @Nonnull
  public static JSInvocation invoke (@Nonnull final IJSExpression aExpr)
  {
    return aExpr.invoke ("autoNumeric");
  }

  @Nonnull
  public JSInvocation invoke ()
  {
    return invoke (JQuery.idRef (this));
  }

  @Nonnull
  public JSInvocation autoNumericInit ()
  {
    return invoke ().arg ("init");
  }

  @Nonnull
  public JSInvocation autoNumericInit (@Nonnull final JSAssocArray aOptions)
  {
    return autoNumericInit ().arg (aOptions);
  }

  @Nonnull
  public JSInvocation autoNumericDestroy ()
  {
    return invoke ().arg ("destroy");
  }

  @Nonnull
  public JSInvocation autoNumericUpdate ()
  {
    return invoke ().arg ("update");
  }

  @Nonnull
  public JSInvocation autoNumericSet ()
  {
    return invoke ().arg ("set");
  }

  @Nonnull
  public JSInvocation autoNumericSet (final int nValue)
  {
    return autoNumericSet ().arg (nValue);
  }

  @Nonnull
  public JSInvocation autoNumericSet (@Nonnull final BigDecimal aValueToSet)
  {
    return autoNumericSet ().arg (aValueToSet);
  }

  @Nonnull
  public JSInvocation autoNumericSet (@Nonnull final IJSExpression aExpr)
  {
    return autoNumericSet ().arg (aExpr);
  }

  @Nonnull
  public JSInvocation autoNumericGet ()
  {
    // Remember: the result is a String!!
    return invoke ().arg ("get");
  }

  @Nonnull
  public JSInvocation autoNumericGetAsFloat ()
  {
    return JSGlobal.parseFloat (autoNumericGet ());
  }

  @Nonnull
  public JSInvocation autoNumericGetString ()
  {
    return invoke ().arg ("getString");
  }

  @Nonnull
  public JSInvocation autoNumericGetArray ()
  {
    return invoke ().arg ("getArray");
  }

  @Nonnull
  public JSInvocation autoNumericGetSettings ()
  {
    return invoke ().arg ("getSettings");
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getJSOptions ()
  {
    final JSAssocArray aArgs = new JSAssocArray ();

    if (m_sThousandSeparator != null)
      aArgs.add ("aSep", m_sThousandSeparator);
    if (m_sDecimalSeparator != null)
      aArgs.add ("aDec", m_sDecimalSeparator);
    if (m_aDecimalPlaces != null)
      aArgs.add ("mDec", m_aDecimalPlaces.intValue ());
    if (m_aMin != null)
      aArgs.add ("vMin", m_aMin.toString ());
    if (m_aMax != null)
      aArgs.add ("vMax", m_aMax.toString ());
    if (m_eLeadingZero != null)
      aArgs.add ("lZero", m_eLeadingZero.getID ());
    if (m_eRoundingMode != null)
      aArgs.add ("mRound", m_eRoundingMode.getID ());

    return aArgs;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // Add special JS code
    aTargetNode.addChild (new HCAutoNumericJS (this));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForcedRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForcedRegistration);

    // Register resources
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.AUTONUMERIC);
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.AUTONUMERIC);
  }
}
