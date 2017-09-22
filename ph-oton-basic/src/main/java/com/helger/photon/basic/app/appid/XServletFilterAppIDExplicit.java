package com.helger.photon.basic.app.appid;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.photon.basic.app.locale.GlobalLocaleManager;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuObject;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.request.PhotonRequestParameters;
import com.helger.photon.basic.app.request.RequestParameterManager;
import com.helger.tree.withid.DefaultTreeItemWithID;
import com.helger.web.scope.IRequestWebScope;
import com.helger.xservlet.filter.IXServletHighLevelFilter;

/**
 * XServlet filter responsible for remembering the application ID in the current
 * request
 *
 * @author Philip Helger
 */
public final class XServletFilterAppIDExplicit implements IXServletHighLevelFilter
{
  private final String m_sAppID;

  public XServletFilterAppIDExplicit (@Nonnull @Nonempty final String sAppID)
  {
    m_sAppID = ValueEnforcer.notEmpty (sAppID, "AppID");
  }

  public void beforeRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    final PhotonGlobalState aGlobal = PhotonGlobalState.getInstance ();
    // It's important to create a session here!
    final PhotonSessionState aSession = PhotonSessionState.getInstance ();
    final String sAppID = m_sAppID;

    final PhotonState aGlobalState = aGlobal.state (sAppID);

    // Remember AppID in session
    aSession.setLastApplicationID (sAppID);
    final PhotonState aSessionState = aSession.state (sAppID);

    // Get menu tree
    IMenuTree aMenuTree = aSessionState.getMenuTree ();
    if (aMenuTree == null)
    {
      aMenuTree = aGlobalState.getMenuTree ();
      if (aMenuTree != null)
      {
        // Remember in session
        aSessionState.setMenuTree (aMenuTree);
      }
    }
    if (aMenuTree == null)
      throw new IllegalStateException ("Failed to resolve MenuTree for request!");

    // Run default request initialization (menu item and locale)
    final PhotonRequestParameters aParams = RequestParameterManager.getInstance ()
                                                                   .getParameterHandler ()
                                                                   .getParametersFromRequest (aRequestScope, aMenuTree);
    // determine menu item ID from request
    IMenuItemPage aMenuItem = aParams.getMenuItem ();
    if (aMenuItem == null)
    {
      // None provided in URL
      // -- check if one is in session
      aMenuItem = aSessionState.getMenuItem ();
      if (aMenuItem == null)
      {
        // None in request, none in session
        // -- check for default item
        final IMenuItemPage aDefaultMenuItem = aMenuTree.getDefaultMenuItem ();
        if (aDefaultMenuItem != null && aDefaultMenuItem.matchesDisplayFilter ())
          aMenuItem = aDefaultMenuItem;

        if (aMenuItem == null)
        {
          // Last fallback: use the first menu item
          final DefaultTreeItemWithID <String, IMenuObject> aRootItem = aMenuTree.getRootItem ();
          if (aRootItem.hasChildren ())
          {
            final IMenuItemPage aFirstMenuItem = aRootItem.findFirstChildMapped (aItem -> aItem.getData () instanceof IMenuItemPage &&
                                                                                          aItem.getData ()
                                                                                               .matchesDisplayFilter (),
                                                                                 aItem -> (IMenuItemPage) aItem.getData ());
            if (aFirstMenuItem != null)
              aMenuItem = aFirstMenuItem;
          }
        }
      }
    }

    if (aMenuItem == null)
      throw new IllegalStateException ("No menu item is present!");

    // Store in all scopes
    aGlobalState.setMenuItem (aMenuItem);
    aSessionState.setMenuItem (aMenuItem);

    // determine locale from request
    Locale aDisplayLocale = aParams.getLocale ();
    if (aDisplayLocale == null)
    {
      // Was a request locale set in session scope?
      final Locale aSessionDisplayLocale = aSessionState.getLocale ();
      if (aSessionDisplayLocale != null)
        aDisplayLocale = aSessionDisplayLocale;

      if (aDisplayLocale == null)
      {
        // Nothing specified - use default locale
        aDisplayLocale = GlobalLocaleManager.getInstance ().getDefaultLocale ();

        if (aDisplayLocale == null)
          throw new IllegalStateException ("No locale could be determined!");
      }
    }

    {
      // Store in all scopes
      aGlobalState.setLocale (aDisplayLocale);
      aSessionState.setLocale (aDisplayLocale);
    }

    RequestSettings.setRequestState (aRequestScope, sAppID, aSessionState);
  }

  public void afterRequest (@Nonnull final IRequestWebScope aRequestScope)
  {
    // empty
  }
}
