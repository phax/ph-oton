package com.helger.photon.app.resource;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;

/**
 * An interface for merging resource bundles.
 *
 * @author Philip Helger
 * @since 8.2.0
 */
public interface IWebSiteResourceBundleProvider
{
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <WebSiteResourceBundleSerialized> getResourceBundles (@Nonnull @Nonempty ICommonsList <WebSiteResourceWithCondition> aList,
                                                                     boolean bRegular);
}
