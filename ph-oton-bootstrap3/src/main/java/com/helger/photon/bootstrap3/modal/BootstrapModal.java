/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.bootstrap3.modal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.sections.HCH4;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.jquery.JQuery;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSInvocation;
import com.helger.html.jscode.JSPackage;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.base.BootstrapCloseIcon;

public class BootstrapModal extends AbstractHCDiv <BootstrapModal>
{
  /**
   * This event fires immediately when the show instance method is called. If
   * caused by a click, the clicked element is available as the relatedTarget
   * property of the event.
   */
  public static final String JS_EVENT_SHOW = "show.bs.modal";
  /**
   * This event is fired when the modal has been made visible to the user (will
   * wait for CSS transitions to complete). If caused by a click, the clicked
   * element is available as the relatedTarget property of the event.
   */
  public static final String JS_EVENT_SHOWN = "shown.bs.modal";
  /**
   * This event is fired immediately when the hide instance method has been
   * called.
   */
  public static final String JS_EVENT_HIDE = "hide.bs.modal";
  /**
   * This event is fired when the modal has finished being hidden from the user
   * (will wait for CSS transitions to complete).
   */
  public static final String JS_EVENT_HIDDEN = "hidden.bs.modal";
  /**
   * This event is fired when the modal has loaded content using the remote
   * option.
   */
  public static final String JS_EVENT_LOADED = "loaded.bs.modal";

  public static final boolean DEFAULT_FADE = true;
  public static final boolean DEFAULT_SHOW_CLOSE = true;

  private final EBootstrapModalSize m_eSize;
  private boolean m_bFade = DEFAULT_FADE;
  private boolean m_bShowClose = DEFAULT_SHOW_CLOSE;
  private IHCNode m_aHeader;
  private IHCNode m_aBody;
  private IHCNode m_aFooter;

  public BootstrapModal ()
  {
    this (EBootstrapModalSize.DEFAULT);
  }

  public BootstrapModal (@Nonnull final EBootstrapModalSize eSize)
  {
    ValueEnforcer.notNull (eSize, "Size");
    ensureID ();
    m_eSize = eSize;
  }

  @Nonnull
  public EBootstrapModalSize getSize ()
  {
    return m_eSize;
  }

  public boolean isFade ()
  {
    return m_bFade;
  }

  @Nonnull
  public BootstrapModal setFade (final boolean bFade)
  {
    m_bFade = bFade;
    return this;
  }

  public boolean isShowClose ()
  {
    return m_bShowClose;
  }

  @Nonnull
  public BootstrapModal setShowClose (final boolean bShowClose)
  {
    m_bShowClose = bShowClose;
    return this;
  }

  @Nonnull
  public BootstrapModal setHeader (@Nullable final String sHeader)
  {
    return setHeader (HCTextNode.createOnDemand (sHeader));
  }

  @Nonnull
  public BootstrapModal setHeader (@Nullable final IHCNode aHeader)
  {
    m_aHeader = aHeader;
    return this;
  }

  @Nonnull
  public BootstrapModal setHeader (@Nullable final IHCNode... aHeader)
  {
    return setHeader (new HCNodeList ().addChildren (aHeader));
  }

  @Nonnull
  public BootstrapModal setHeader (@Nullable final Iterable <? extends IHCNode> aHeader)
  {
    return setHeader (new HCNodeList ().addChildren (aHeader));
  }

  @Nonnull
  public BootstrapModal setBody (@Nullable final String sBody)
  {
    return setBody (HCTextNode.createOnDemand (sBody));
  }

  @Nonnull
  public BootstrapModal setBody (@Nullable final IHCNode aBody)
  {
    m_aBody = aBody;
    return this;
  }

  @Nonnull
  public BootstrapModal setBody (@Nullable final IHCNode... aBody)
  {
    return setBody (new HCNodeList ().addChildren (aBody));
  }

  @Nonnull
  public BootstrapModal setBody (@Nullable final Iterable <? extends IHCNode> aBody)
  {
    return setBody (new HCNodeList ().addChildren (aBody));
  }

  @Nonnull
  public BootstrapModal setFooter (@Nullable final String sFooter)
  {
    return setFooter (HCTextNode.createOnDemand (sFooter));
  }

  @Nonnull
  public BootstrapModal setFooter (@Nullable final IHCNode aFooter)
  {
    m_aFooter = aFooter;
    return this;
  }

  @Nonnull
  public BootstrapModal setFooter (@Nullable final IHCNode... aFooter)
  {
    return setFooter (new HCNodeList ().addChildren (aFooter));
  }

  @Nonnull
  public BootstrapModal setFooter (@Nullable final Iterable <? extends IHCNode> aFooter)
  {
    return setFooter (new HCNodeList ().addChildren (aFooter));
  }

  @Nonnull
  @Nonempty
  private String _getContentID ()
  {
    return getID () + "content";
  }

  @Nonnull
  @Nonempty
  private String _getTitleID ()
  {
    return getID () + "title";
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.MODAL).setRole (EHTMLRole.DIALOG);
    customAttrs ().setAriaHidden (true);
    if (m_bFade)
      addClass (CBootstrapCSS.FADE);

    final HCDiv aDialog = addAndReturnChild (new HCDiv ().addClasses (CBootstrapCSS.MODAL_DIALOG, m_eSize));
    final HCDiv aContent = aDialog.addAndReturnChild (new HCDiv ().addClass (CBootstrapCSS.MODAL_CONTENT)
                                                                  .setID (_getContentID ()));
    if (m_aHeader != null)
    {
      final String sTitleID = _getTitleID ();
      customAttrs ().setAriaLabeledBy (sTitleID);
      final HCDiv aHeader = aContent.addAndReturnChild (new HCDiv ().addClass (CBootstrapCSS.MODAL_HEADER));
      if (m_bShowClose)
      {
        final BootstrapCloseIcon aCloseIcon = new BootstrapCloseIcon ();
        aCloseIcon.customAttrs ().setDataAttr ("dismiss", "modal");
        aHeader.addChild (aCloseIcon);
      }
      aHeader.addChild (new HCH4 ().addClass (CBootstrapCSS.MODAL_TITLE).setID (sTitleID).addChild (m_aHeader));
    }
    if (m_aBody != null)
      aContent.addChild (new HCDiv ().addClass (CBootstrapCSS.MODAL_BODY).addChild (m_aBody));
    if (m_aFooter != null)
      aContent.addChild (new HCDiv ().addClass (CBootstrapCSS.MODAL_FOOTER).addChild (m_aFooter));
  }

  /**
   * @return JS invocation to open this modal dialog.
   */
  @Nonnull
  public JSInvocation jsModal ()
  {
    return JQuery.idRef (this).invoke ("modal");
  }

  /**
   * Activates your content as a modal. Accepts an optional options object.
   *
   * @param aBackdrop
   *        Includes a modal-backdrop element. Alternatively, specify static for
   *        a backdrop which doesn't close the modal on click.
   * @param aKeyboard
   *        Closes the modal when escape key is pressed
   * @param aShow
   *        Shows the modal when initialized.
   * @param sRemotePath
   *        If a remote URL is provided, content will be loaded one time via
   *        jQuery's load method and injected into the .modal-content div.
   * @return JS invocation to open this modal dialog with the specified options.
   */
  @Nonnull
  public JSPackage openModal (@Nullable final EBootstrapModalOptionBackdrop aBackdrop,
                              @Nullable final Boolean aKeyboard,
                              @Nullable final Boolean aShow,
                              @Nullable final String sRemotePath)
  {
    final JSPackage ret = new JSPackage ();

    final JSAssocArray aOptions = new JSAssocArray ();
    if (aBackdrop != null)
      aOptions.add ("backdrop", aBackdrop.getJSExpression ());
    if (aKeyboard != null)
      aOptions.add ("keyboard", aKeyboard.booleanValue ());
    if (aShow != null)
      aOptions.add ("show", aShow.booleanValue ());
    ret.add (jsModal ().arg (aOptions));

    if (StringHelper.hasText (sRemotePath))
    {
      // Load content into modal
      ret.add (JQuery.idRef (_getContentID ()).load (sRemotePath));
    }
    return ret;
  }

  /**
   * Manually toggles a modal. Returns to the caller before the modal has
   * actually been shown or hidden (i.e. before the shown.bs.modal or
   * hidden.bs.modal event occurs).
   *
   * @return JS invocation
   */
  @Nonnull
  public JSInvocation jsModalToggle ()
  {
    return jsModal ().arg ("toggle");
  }

  /**
   * Manually opens a modal. Returns to the caller before the modal has actually
   * been shown (i.e. before the shown.bs.modal event occurs).
   *
   * @return JS invocation
   */
  @Nonnull
  public JSInvocation jsModalShow ()
  {
    return jsModal ().arg ("show");
  }

  /**
   * Manually hides a modal. Returns to the caller before the modal has actually
   * been hidden (i.e. before the hidden.bs.modal event occurs).
   *
   * @return JS invocation
   */
  @Nonnull
  public JSInvocation jsModalHide ()
  {
    return jsModal ().arg ("hide");
  }

  /**
   * Readjusts the modal's positioning to counter a scrollbar in case one should
   * appear, which would make the modal jump to the left. Only needed when the
   * height of the modal changes while it is open.
   *
   * @return JS invocation
   */
  @Nonnull
  public JSInvocation jsModalHandleUpdate ()
  {
    return jsModal ().arg ("handleUpdate");
  }
}
