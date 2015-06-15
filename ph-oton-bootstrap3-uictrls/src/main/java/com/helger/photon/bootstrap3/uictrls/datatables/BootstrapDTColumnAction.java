package com.helger.photon.bootstrap3.uictrls.datatables;

import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.photon.uicore.css.CUICoreCSS;
import com.helger.photon.uictrls.datatables.DTColumn;

/**
 * Special action column. Has a special CSS class and is never sortable.
 * 
 * @author Philip Helger
 */
public class BootstrapDTColumnAction extends DTColumn
{
  private void _init ()
  {
    addClass (CUICoreCSS.CSS_CLASS_ACTION_COL);
    setSortable (false);
  }

  public BootstrapDTColumnAction (@Nullable final String sHeaderText)
  {
    super (sHeaderText);
    _init ();
  }

  public BootstrapDTColumnAction (@Nullable final IHCNode aHeaderNode)
  {
    super (aHeaderNode);
    _init ();
  }
}
