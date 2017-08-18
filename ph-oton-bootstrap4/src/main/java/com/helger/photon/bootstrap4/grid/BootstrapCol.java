package com.helger.photon.bootstrap4.grid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;

public class BootstrapCol extends AbstractHCDiv <BootstrapCol>
{
  private EBootstrapColOrder m_eOrder;
  private MarginBuilder m_aMargin;

  public BootstrapCol ()
  {}

  @Nonnull
  public BootstrapCol setOrder (@Nullable final EBootstrapColOrder eOrder)
  {
    m_eOrder = eOrder;
    return this;
  }

  @Nullable
  public EBootstrapColOrder getOrder ()
  {
    return m_eOrder;
  }

  @Nonnull
  public BootstrapCol setMargin (@Nullable final MarginBuilder aMargin)
  {
    m_aMargin = aMargin;
    return this;
  }

  @Nullable
  public MarginBuilder getMargin ()
  {
    return m_aMargin;
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (m_eOrder);
    addClass (m_aMargin);
  }

  /**
   * Utility class to build a column margin.
   *
   * @author Philip Helger
   */
  public static class MarginBuilder implements ICSSClassProvider
  {
    private EBootstrapEdgeType m_eEdgeType = EBootstrapEdgeType.ALL;
    private EBootstrapGridType m_eGridType = EBootstrapGridType.XS;
    private int m_nCount = -1;

    /**
     * Set the edge type. Default is "all".
     *
     * @param eEdgeType
     *        Edge type. May not be <code>null</code>.
     * @return this for chaining
     */
    @Nullable
    public MarginBuilder edgeType (@Nonnull final EBootstrapEdgeType eEdgeType)
    {
      ValueEnforcer.notNull (eEdgeType, "EdgeType");
      m_eEdgeType = eEdgeType;
      return this;
    }

    /**
     * Set the grid type. Default is "xs" which means valid for all.
     *
     * @param eGridType
     *        Grid type. May not be <code>null</code>.
     * @return this for chaining
     */
    @Nullable
    public MarginBuilder gridType (@Nonnull final EBootstrapGridType eGridType)
    {
      ValueEnforcer.notNull (eGridType, "GridType");
      m_eGridType = eGridType;
      return this;
    }

    /**
     * Number of grid elements to "margin"
     *
     * @param nCount
     *        Count from 0 to 5 or "-1" for "auto"
     * @return this for chaining
     */
    @Nonnull
    public MarginBuilder count (final int nCount)
    {
      ValueEnforcer.isTrue (nCount >= -1 && nCount <= 5, "Count is invalid");
      m_nCount = nCount;
      return this;
    }

    /**
     * Shortcut for <code>count (-1)</code>
     *
     * @return this for chaining
     */
    @Nonnull
    public MarginBuilder auto ()
    {
      return count (-1);
    }

    @Nonnull
    public String getCSSClass ()
    {
      String ret = "m";
      if (m_eEdgeType != null)
        ret += m_eEdgeType.getCSSClassNamePart ();
      ret += m_eGridType.getCSSClassNamePart () + "-";
      if (m_nCount == -1)
        ret += "auto";
      else
        ret += m_nCount;
      return ret;
    }
  }
}
