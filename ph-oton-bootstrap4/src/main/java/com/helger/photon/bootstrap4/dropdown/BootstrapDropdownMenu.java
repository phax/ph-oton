package com.helger.photon.bootstrap4.dropdown;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.HCButton;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonGroup;

/**
 * Bootstrap dropdown menu, without integration into surrounding objects.
 *
 * @author Philip Helger
 */
public class BootstrapDropdownMenu extends AbstractBootstrapDiv <BootstrapDropdownMenu>
{
  /**
   * This event fires immediately when the show instance method is called.
   */
  public static final String JS_EVENT_SHOW = "show.bs.dropdown";
  /**
   * This event is fired when the dropdown has been made visible to the user
   * (will wait for CSS transitions, to complete).
   */
  public static final String JS_EVENT_SHOWN = "shown.bs.dropdown";
  /**
   * This event is fired immediately when the hide instance method has been
   * called.
   */
  public static final String JS_EVENT_HIDE = "hide.bs.dropdown";
  /**
   * This event is fired when the dropdown has finished being hidden from the
   * user (will wait for CSS transitions, to complete).
   */
  public static final String JS_EVENT_HIDDEN = "hidden.bs.dropdown";

  public static final boolean DEFAULT_ALIGN_RIGHT = false;

  private boolean m_bAlignRight = DEFAULT_ALIGN_RIGHT;

  public BootstrapDropdownMenu ()
  {}

  public final boolean isAlignRight ()
  {
    return m_bAlignRight;
  }

  @Nonnull
  public final BootstrapDropdownMenu setAlignRight (final boolean bAlignRight)
  {
    m_bAlignRight = bAlignRight;
    return this;
  }

  @Nonnull
  public BootstrapDropdownItem createAndAddItem ()
  {
    return addAndReturnChild (new BootstrapDropdownItem ());
  }

  @Nonnull
  public BootstrapDropdownDivider createAndAddDivider ()
  {
    return addAndReturnChild (new BootstrapDropdownDivider ());
  }

  @Nonnull
  public BootstrapDropdownText createAndAddText ()
  {
    return addAndReturnChild (new BootstrapDropdownText ());
  }

  @Nonnull
  public BootstrapDropdownText createAndAddText (@Nullable final String sText)
  {
    final BootstrapDropdownText aText = new BootstrapDropdownText ();
    aText.addChild (sText);
    return addAndReturnChild (aText);
  }

  @Nonnull
  public BootstrapDropdownHeader createAndAddHeader ()
  {
    return addAndReturnChild (new BootstrapDropdownHeader ());
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (CBootstrapCSS.DROPDOWN_MENU);
    if (m_bAlignRight)
      addClass (CBootstrapCSS.DROPDOWN_MENU_RIGHT);
  }

  public static void assignMenuToButton (@Nonnull final IHCElementWithChildren <?> aContainer,
                                         @Nonnull final IHCElementWithChildren <?> aButton,
                                         @Nonnull final BootstrapDropdownMenu aMenu,
                                         @Nonnull final EBootstrapDropType eDropType,
                                         final boolean bUseSplitButton)
  {
    // Container div
    IHCElementWithChildren <?> aDropContainer = aContainer;

    // Ensure it is a button - hopefully redundant
    aButton.addClass (CBootstrapCSS.BTN);

    IHCElementWithChildren <?> aActionButton;
    if (bUseSplitButton)
    {
      final HCButton aSplitButton = new HCButton ();
      aSplitButton.addClasses (aButton.getAllClasses ());
      aSplitButton.addClass (CBootstrapCSS.DROPDOWN_TOGGLE_SPLIT);
      aSplitButton.addChild (new HCSpan ().addClass (CBootstrapCSS.SR_ONLY).addChild ("Toggle " + eDropType.name ()));

      if (eDropType == EBootstrapDropType.DROPLEFT)
      {
        final BootstrapButtonGroup aTmpGroup = new BootstrapButtonGroup ();
        aTmpGroup.addChild (aSplitButton);
        aTmpGroup.addChild (aMenu);
        aContainer.addChild (aTmpGroup);
        aContainer.addChild (aButton);
        aDropContainer = aTmpGroup;
      }
      else
      {
        aContainer.addChild (aButton);
        aContainer.addChild (aSplitButton);
        aContainer.addChild (aMenu);
      }

      aActionButton = aSplitButton;
    }
    else
    {
      aContainer.addChild (aButton);
      aContainer.addChild (aMenu);

      aActionButton = aButton;
    }

    aActionButton.addClass (CBootstrapCSS.DROPDOWN_TOGGLE);
    if (aActionButton.getElement () != EHTMLElement.BUTTON)
      aActionButton.setRole (EHTMLRole.BUTTON);
    aActionButton.ensureID ();
    aActionButton.customAttrs ().setDataAttr ("toggle", "dropdown");
    aActionButton.customAttrs ().setAriaHasPopup (true);
    aActionButton.customAttrs ().setAriaExpanded (false);

    // Labeled by original button!
    aMenu.customAttrs ().setAriaLabeledBy (aButton);

    // Container
    aDropContainer.addClass (eDropType);
  }
}
