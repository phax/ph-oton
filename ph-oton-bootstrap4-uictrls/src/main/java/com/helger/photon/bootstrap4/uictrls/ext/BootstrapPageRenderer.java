package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import com.helger.commons.lang.GenericReflection;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.menu.MenuItemDeterminatorCallback;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap4.breadcrumb.BootstrapBreadcrumb;
import com.helger.photon.bootstrap4.breadcrumb.BootstrapBreadcrumbProvider;
import com.helger.photon.bootstrap4.ext.BootstrapSystemMessage;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.context.LayoutExecutionContext;
import com.helger.photon.core.app.error.InternalErrorBuilder;
import com.helger.photon.core.app.layout.CLayout;
import com.helger.photon.uicore.page.IWebPage;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.forcedredirect.ForcedRedirectException;
import com.helger.xservlet.forcedredirect.ForcedRedirectManager;

/**
 * Common page part renderings.
 *
 * @author Philip Helger
 * @since 8.1.2
 */
public final class BootstrapPageRenderer
{
  private BootstrapPageRenderer ()
  {}

  @Nonnull
  public static BootstrapBreadcrumb getBreadcrumb (@Nonnull final ILayoutExecutionContext aLEC)
  {
    final BootstrapBreadcrumb aBreadcrumbs = BootstrapBreadcrumbProvider.createBreadcrumb (aLEC);
    aBreadcrumbs.addClasses (CBootstrapCSS.D_NONE, CBootstrapCSS.D_SM_BLOCK);
    return aBreadcrumbs;
  }

  @Nonnull
  public static IHCElement <?> getMenuContent (@Nonnull final ILayoutExecutionContext aLEC)
  {
    // Main menu
    final IMenuTree aMenuTree = aLEC.getMenuTree ();
    final MenuItemDeterminatorCallback aCallback = new MenuItemDeterminatorCallback (aMenuTree,
                                                                                     aLEC.getSelectedMenuItemID ());
    final IHCElement <?> aMenu = BootstrapMenuItemRenderer.createSideBarMenu (aLEC, aMenuTree, aCallback);
    return aMenu;
  }

  @Nonnull
  public static <LEC extends LayoutExecutionContext, WPEC extends WebPageExecutionContext> HCNodeList getPageContent (@Nonnull final LEC aLEC,
                                                                                                                      @Nonnull final BiFunction <LEC, IWebPage <WPEC>, WPEC> aWPECFactory)
  {
    // Get the requested menu item
    final IMenuItemPage aSelectedMenuItem = aLEC.getSelectedMenuItem ();
    final IRequestWebScopeWithoutResponse aRequestScope = aLEC.getRequestScope ();

    // Resolve the page of the selected menu item (if found)
    IWebPage <WPEC> aDisplayPage;
    if (aSelectedMenuItem.matchesDisplayFilter ())
    {
      // Only if we have display rights!
      aDisplayPage = GenericReflection.uncheckedCast (aSelectedMenuItem.getPage ());
    }
    else
    {
      // No rights -> goto start page
      aDisplayPage = GenericReflection.uncheckedCast (aLEC.getMenuTree ().getDefaultMenuItem ().getPage ());
    }

    // Result gathering
    final HCNodeList ret = new HCNodeList ();

    // First add the system message
    ret.addChild (BootstrapSystemMessage.createDefault ());

    // Handle HTTP errors case here
    if ("true".equals (aRequestScope.params ().getAsString ("httpError")))
    {
      final String sHttpStatusCode = aRequestScope.params ().getAsString ("httpStatusCode");
      final String sHttpStatusMessage = aRequestScope.params ().getAsString ("httpStatusMessage");
      final String sHttpRequestURI = aRequestScope.params ().getAsString ("httpRequestUri");
      ret.addChild (new BootstrapErrorBox ().addChild ("HTTP error " +
                                                       sHttpStatusCode +
                                                       " (" +
                                                       sHttpStatusMessage +
                                                       ")" +
                                                       (StringHelper.hasText (sHttpRequestURI) ? " for request URI " +
                                                                                                 sHttpRequestURI
                                                                                               : "")));
    }
    else
    {
      // Add the forced redirect content here
      if (aRequestScope.params ().containsKey (ForcedRedirectManager.REQUEST_PARAMETER_PRG_ACTIVE))
        ret.addChild ((IHCNode) ForcedRedirectManager.getLastForcedRedirectContent (aDisplayPage.getID ()));
    }

    // Create correct WPEC
    final WPEC aWPEC = aWPECFactory.apply (aLEC, aDisplayPage);

    // Add page header
    ret.addChild (aDisplayPage.getHeaderNode (aWPEC));

    try
    {
      // Main fill content
      aDisplayPage.getContent (aWPEC);

      // Add created content to result
      ret.addChild (aWPEC.getNodeList ());
    }
    catch (final ForcedRedirectException ex)
    {
      // Re-throw
      throw ex;
    }
    catch (final Exception ex)
    {
      // Catch error in building body content
      new InternalErrorBuilder ().setThrowable (ex)
                                 .setRequestScope (aRequestScope)
                                 .setDisplayLocale (aLEC.getDisplayLocale ())
                                 .addErrorMessage ("html-error-filling-page-content")
                                 .setUIErrorHandlerFor (ret)
                                 .handle ();
    }

    return ret;
  }

  @Nonnull
  public static HCNodeList getPageContent (@Nonnull final LayoutExecutionContext aLEC)
  {
    return getPageContent (aLEC, WebPageExecutionContext::new);
  }

  @Nonnull
  public static HCDiv getMenuAndPageNextToEachOther (@Nonnull final LayoutExecutionContext aLEC)
  {
    final HCDiv aRow = new HCDiv ().addClass (CBootstrapCSS.D_MD_FLEX);
    final HCDiv aCol1 = aRow.addAndReturnChild (new HCDiv ().addClass (CBootstrapCSS.D_MD_FLEX)
                                                            .addClass (CBootstrapCSS.MR_2));
    final HCDiv aCol2 = aRow.addAndReturnChild (new HCDiv ().addClass (CBootstrapCSS.FLEX_FILL));

    // We need a wrapper span for easy AJAX content replacement
    aCol1.addClass (CBootstrapCSS.D_PRINT_NONE)
         .addChild (new HCSpan ().setID (CLayout.LAYOUT_AREAID_MENU)
                                 .addChild (BootstrapPageRenderer.getMenuContent (aLEC)))
         .addChild (new HCDiv ().setID (CLayout.LAYOUT_AREAID_SPECIAL));

    // content - determine is exactly same as for view
    aCol2.addChild (BootstrapPageRenderer.getPageContent (aLEC, WebPageExecutionContext::new));

    return aRow;
  }
}
