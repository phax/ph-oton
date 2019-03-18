package com.helger.photon.core.resource;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;

public interface IWebSiteResourceBundleProvider
{
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <WebSiteResourceBundleSerialized> getResourceBundles (@Nonnull @Nonempty ICommonsList <WebSiteResourceWithCondition> aList,
                                                                     boolean bRegular);
}
