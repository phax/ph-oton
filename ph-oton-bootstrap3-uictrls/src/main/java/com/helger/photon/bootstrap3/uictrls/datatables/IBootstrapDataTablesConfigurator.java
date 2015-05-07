package com.helger.photon.bootstrap3.uictrls.datatables;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCTable;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public interface IBootstrapDataTablesConfigurator
{
  /**
   * Configure the default datatables
   *
   * @param aWPEC
   *        Current web page execution context
   * @param aTable
   *        Source table
   * @param aDataTables
   *        The newly created datatables object
   */
  void configure (@Nonnull IWebPageExecutionContext aWPEC,
                  @Nonnull IHCTable <?> aTable,
                  @Nonnull BootstrapDataTables aDataTables);
}
