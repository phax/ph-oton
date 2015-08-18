package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.jscode.JSAssocArray;

public class DTPButtonsDom
{
  /** DOM configuration for individual button elements. */
  private DTPButtonsDomButton m_aButton;
  /** DOM configuration of a button container element. */
  private DTPButtonsDomButtonContainer m_aButtonContainer;
  /** DOM configuration of a button liner element. */
  private DTPButtonsDomButtonLiner m_aButtonLiner;
  /** DOM configuration of the collection display. */
  private DTPButtonsDomCollection m_aCollection;
  /** DOM configuration of the Buttons container element. */
  private DTPButtonsDomContainer m_aContainer;

  @Nonnull
  public DTPButtonsDom setButton (@Nullable final DTPButtonsDomButton aButton)
  {
    m_aButton = aButton;
    return this;
  }

  @Nonnull
  public DTPButtonsDom setButtonContainer (@Nullable final DTPButtonsDomButtonContainer aButtonContainer)
  {
    m_aButtonContainer = aButtonContainer;
    return this;
  }

  @Nonnull
  public DTPButtonsDom setButtonLiner (@Nullable final DTPButtonsDomButtonLiner aButtonLiner)
  {
    m_aButtonLiner = aButtonLiner;
    return this;
  }

  @Nonnull
  public DTPButtonsDom setCollection (@Nullable final DTPButtonsDomCollection aCollection)
  {
    m_aCollection = aCollection;
    return this;
  }

  @Nonnull
  public DTPButtonsDom setContainer (@Nullable final DTPButtonsDomContainer aContainer)
  {
    m_aContainer = aContainer;
    return this;
  }

  @Nonnull
  public JSAssocArray getAsJS ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_aButton != null)
      ret.add ("button", m_aButton.getAsJS ());
    if (m_aButtonContainer != null)
      ret.add ("buttonContainer", m_aButtonContainer.getAsJS ());
    if (m_aButtonLiner != null)
      ret.add ("buttonLiner", m_aButtonLiner.getAsJS ());
    if (m_aCollection != null)
      ret.add ("collection", m_aCollection.getAsJS ());
    if (m_aContainer != null)
      ret.add ("container", m_aContainer.getAsJS ());
    return ret;
  }
}
