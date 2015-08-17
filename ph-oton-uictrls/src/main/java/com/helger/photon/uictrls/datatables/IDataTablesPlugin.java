package com.helger.photon.uictrls.datatables;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.hc.IHCConversionSettingsToNode;

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
   * Register custom resources required for this plugin.
   *
   * @param aConversionSettings
   *        Conversion settings to use. Never <code>null</code>.
   */
  void registerExternalResources (@Nonnull IHCConversionSettingsToNode aConversionSettings);
}
