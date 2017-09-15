package com.helger.photon.basic.app.appid;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.attr.IAttributeContainerAny;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.scope.mgr.ScopeManager;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public final class RequestSettings
{
  public static final String REQUEST_ATTR_APP_ID = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "app-id";
  public static final String REQUEST_ATTR_STATE = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL + "app-state";

  private RequestSettings ()
  {}

  @Nonnull
  @Nonempty
  public static String getApplicationID (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final String sAppID = aRequestScope.attrs ().getCastedValue (REQUEST_ATTR_APP_ID);
    if (StringHelper.hasNoText (sAppID))
      throw new IllegalStateException ("No app ID is present!");
    return sAppID;
  }

  @Nonnull
  public static PhotonState getState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    PhotonState aState = aRequestScope.attrs ().getCastedValue (REQUEST_ATTR_STATE);
    if (aState == null)
    {
      // Fallback to last saved state from session
      aState = PhotonSessionState.getInstance ().stateLastAppID ();
    }
    if (aState == null)
      throw new IllegalStateException ("No state is present!");
    return aState;
  }

  @Nonnull
  public static IMenuTree getMenuTree (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final IMenuTree aMenuTree = getState (aRequestScope).getMenuTree ();
    if (aMenuTree == null)
      throw new IllegalStateException ("No menu tree is present!");
    return aMenuTree;
  }

  @Nonnull
  public static IMenuItemPage getMenuItem (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final IMenuItemPage aMenuItem = getState (aRequestScope).getMenuItem ();
    if (aMenuItem == null)
      throw new IllegalStateException ("No menu item is present!");
    return aMenuItem;
  }

  @Nonnull
  @Nonempty
  public static String getMenuItemID (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return getMenuItem (aRequestScope).getID ();
  }

  @Nonnull
  public static Locale getDisplayLocale (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    final Locale aLocale = getState (aRequestScope).getLocale ();
    if (aLocale == null)
      throw new IllegalStateException ("No locale is available");
    return aLocale;
  }

  @Nonnull
  public static Locale getDisplayCountry (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return CountryCache.getInstance ().getCountry (getDisplayLocale (aRequestScope));
  }

  @Nonnull
  public static String getDisplayLanguage (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return getDisplayLocale (aRequestScope).getLanguage ();
  }

  static void set (@Nonnull final IRequestWebScope aRequestScope,
                   @Nonnull @Nonempty final String sAppID,
                   @Nonnull final PhotonState aState)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    ValueEnforcer.notNull (aState, "State");

    // Remember AppID in request
    final IAttributeContainerAny <String> aAttrs = aRequestScope.attrs ();
    aAttrs.putIn (REQUEST_ATTR_APP_ID, sAppID);
    aAttrs.putIn (REQUEST_ATTR_STATE, aState.getClone ());
  }
}
