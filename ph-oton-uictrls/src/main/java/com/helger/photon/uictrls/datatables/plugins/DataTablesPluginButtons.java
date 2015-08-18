package com.helger.photon.uictrls.datatables.plugins;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSAtom;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uictrls.EUICtrlsCSSPathProvider;
import com.helger.photon.uictrls.EUICtrlsJSPathProvider;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.DataTablesDom;

public class DataTablesPluginButtons extends AbstractDataTablesPlugin
{
  public static final String DEFAULT_NAME = "main";

  /** List of buttons to be created. */
  private final List <Object> m_aButtons = new ArrayList <> ();
  /** Options to control the DOM structure Buttons creates. */
  private DTPButtonsDom m_aDom;
  /** Set a name for the instance for the group selector. */
  private String m_sName;

  public DataTablesPluginButtons ()
  {
    super ("buttons");
  }

  @Nonnull
  @ReturnsMutableCopy
  @OverrideOnDemand
  protected DataTablesDom createDom ()
  {
    return new DataTablesDom ();
  }

  @OverrideOnDemand
  protected void weaveIntoDom (@Nonnull final DataTablesDom aDom)
  {
    aDom.setPosition (0).addCustom ("B").openDiv ("clear").closeDiv ();
  }

  @Override
  public void finalizeDataTablesSettings (@Nonnull final DataTables aDT)
  {
    DataTablesDom aDom = aDT.directGetDom ();
    if (aDom == null)
    {
      aDom = createDom ();
      aDT.setDom (aDom);
    }
    weaveIntoDom (aDom);
  }

  @Nonnull
  public DataTablesPluginButtons addButton (@Nonnull @Nonempty final String sButton)
  {
    ValueEnforcer.notEmpty (sButton, "Button");
    m_aButtons.add (sButton);
    return this;
  }

  @Nonnull
  public DataTablesPluginButtons addButton (@Nonnull final DTPButtonsButton aButton)
  {
    ValueEnforcer.notNull (aButton, "Button");
    m_aButtons.add (aButton);
    return this;
  }

  @Nonnull
  public DataTablesPluginButtons setDom (@Nullable final DTPButtonsDom aDom)
  {
    m_aDom = aDom;
    return this;
  }

  @Nonnull
  public DataTablesPluginButtons setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();

    if (!m_aButtons.isEmpty ())
    {
      final JSArray aButtons = new JSArray ();
      for (final Object aObj : m_aButtons)
        if (aObj instanceof String)
          aButtons.add ((String) aObj);
        else
          aButtons.add (((DTPButtonsButton) aObj).getAsJS ());
      ret.add ("buttons", aButtons);
    }
    if (m_aDom != null)
      ret.add ("dom", m_aDom.getAsJS ());
    if (StringHelper.hasText (m_sName))
      ret.add ("name", m_sName);

    if (ret.size () == 1)
    {
      final IJSExpression aValue = ret.get (new JSAtom ("buttons"));
      if (aValue != null)
        return aValue;
    }

    if (ret.isEmpty ())
    {
      // No properties present
      return JSExpr.TRUE;
    }
    return ret;
  }

  @Override
  public void registerExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    PhotonJS.registerJSIncludeForThisRequest (EUICtrlsJSPathProvider.DATATABLES_BUTTONS);
    PhotonCSS.registerCSSIncludeForThisRequest (EUICtrlsCSSPathProvider.DATATABLES_BUTTONS);
    for (final Object aObj : m_aButtons)
      if (aObj instanceof DTPButtonsButton)
        ((DTPButtonsButton) aObj).registerExternalResources (aConversionSettings);
  }
}
