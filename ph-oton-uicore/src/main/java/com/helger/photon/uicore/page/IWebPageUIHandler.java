package com.helger.photon.uicore.page;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.photon.uicore.css.CUICoreCSS;

public interface IWebPageUIHandler
{
  @Nullable
  default IHCElementWithChildren <?> createActionHeader ()
  {
    return new HCDiv ().addClass (CUICoreCSS.CSS_CLASS_ACTION_HEADER);
  }

  @Nullable
  default IHCElementWithChildren <?> createActionHeader (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return createActionHeader ().addChild (sText);
  }

  @Nonnull
  default IHCElementWithChildren <?> createDataGroupHeader ()
  {
    return new HCDiv ().addClass (CUICoreCSS.CSS_CLASS_DATAGROUP_HEADER);
  }

  @Nullable
  default IHCElementWithChildren <?> createDataGroupHeader (@Nullable final String sText)
  {
    if (StringHelper.hasNoText (sText))
      return null;
    return createDataGroupHeader ().addChild (sText);
  }
}
