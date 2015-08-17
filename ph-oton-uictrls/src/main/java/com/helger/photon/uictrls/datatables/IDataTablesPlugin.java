package com.helger.photon.uictrls.datatables;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSVar;

/**
 * Base interface for DataTables plugins.
 *
 * @author Philip Helger
 */
public interface IDataTablesPlugin
{
  /**
   * @return The name used as the key for the JS options.
   */
  @Nonnull
  @Nonempty
  String getName ();

  /**
   * Apply all necessary information to the owning DataTables object. This can
   * be used to e.g. wave stuff into the DOM.
   *
   * @param aDT
   *        DataTables to use. Never <code>null</code>.
   */
  void finalizeDataTablesSettings (@Nonnull DataTables aDT);

  /**
   * Add custom plugin JS initialization code.
   *
   * @param aDT
   *        DataTables to use. Never <code>null</code>.
   * @param aJSCode
   *        The JS package to append to. Never <code>null</code>.
   * @param aJSTable
   *        The reference to the DataTables JS object
   */
  void addInitJS (@Nonnull DataTables aDT, @Nonnull JSPackage aJSCode, @Nonnull JSVar aJSTable);

  /**
   * Register custom resources required for this plugin.
   *
   * @param aConversionSettings
   *        Conversion settings to use. Never <code>null</code>.
   */
  void registerExternalResources (@Nonnull IHCConversionSettingsToNode aConversionSettings);

  /**
   * @return The initialization parameters to be added to the DT init
   *         JavaScript.
   */
  @Nonnull
  IJSExpression getInitParams ();
}
