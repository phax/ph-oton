/*
 * Copyright (C) 2018-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.traits;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.traits.IHCTrait;
import com.helger.photon.bootstrap4.alert.BootstrapDangerBox;
import com.helger.photon.bootstrap4.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap4.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap4.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap4.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap4.alert.BootstrapWarnBox;
import com.helger.photon.bootstrap4.badge.BootstrapBadge;
import com.helger.photon.bootstrap4.badge.EBootstrapBadgeType;

/**
 * Traits interface to add simpler UI codes.<br>
 * Generated by MainCreateHCBootstrap4TraitsCode
 *
 * @author Philip Helger
 * @since 8.2.2
 */
public interface IHCBootstrap4Trait extends IHCTrait
{
  @Nonnull
  default BootstrapBadge badge (final int nValue)
  {
    return new BootstrapBadge ().addChild (Integer.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badge (final long nValue)
  {
    return new BootstrapBadge ().addChild (Long.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badge (@Nullable final IHCNode aNode)
  {
    return new BootstrapBadge ().addChild (aNode);
  }

  @Nonnull
  default BootstrapBadge badge (@Nullable final String s)
  {
    return new BootstrapBadge ().addChild (s);
  }

  @Nonnull
  default BootstrapBadge badge (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapBadge ().addChildren (aNodes);
  }

  @Nonnull
  default BootstrapBadge badge (@Nullable final String... aTexts)
  {
    return new BootstrapBadge ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapBadge badgeDanger ()
  {
    return new BootstrapBadge (EBootstrapBadgeType.DANGER);
  }

  @Nonnull
  default BootstrapBadge badgeDanger (final int nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.DANGER).addChild (Integer.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeDanger (final long nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.DANGER).addChild (Long.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeDanger (@Nullable final IHCNode aNode)
  {
    return new BootstrapBadge (EBootstrapBadgeType.DANGER).addChild (aNode);
  }

  @Nonnull
  default BootstrapBadge badgeDanger (@Nullable final String s)
  {
    return new BootstrapBadge (EBootstrapBadgeType.DANGER).addChild (s);
  }

  @Nonnull
  default BootstrapBadge badgeDanger (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapBadge (EBootstrapBadgeType.DANGER).addChildren (aNodes);
  }

  @Nonnull
  default BootstrapBadge badgeDanger (@Nullable final String... aTexts)
  {
    return new BootstrapBadge ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapBadge badgeInfo ()
  {
    return new BootstrapBadge (EBootstrapBadgeType.INFO);
  }

  @Nonnull
  default BootstrapBadge badgeInfo (final int nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.INFO).addChild (Integer.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeInfo (final long nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.INFO).addChild (Long.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeInfo (@Nullable final IHCNode aNode)
  {
    return new BootstrapBadge (EBootstrapBadgeType.INFO).addChild (aNode);
  }

  @Nonnull
  default BootstrapBadge badgeInfo (@Nullable final String s)
  {
    return new BootstrapBadge (EBootstrapBadgeType.INFO).addChild (s);
  }

  @Nonnull
  default BootstrapBadge badgeInfo (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapBadge (EBootstrapBadgeType.INFO).addChildren (aNodes);
  }

  @Nonnull
  default BootstrapBadge badgeInfo (@Nullable final String... aTexts)
  {
    return new BootstrapBadge ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapBadge badgePrimary ()
  {
    return new BootstrapBadge (EBootstrapBadgeType.PRIMARY);
  }

  @Nonnull
  default BootstrapBadge badgePrimary (final int nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.PRIMARY).addChild (Integer.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgePrimary (final long nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.PRIMARY).addChild (Long.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgePrimary (@Nullable final IHCNode aNode)
  {
    return new BootstrapBadge (EBootstrapBadgeType.PRIMARY).addChild (aNode);
  }

  @Nonnull
  default BootstrapBadge badgePrimary (@Nullable final String s)
  {
    return new BootstrapBadge (EBootstrapBadgeType.PRIMARY).addChild (s);
  }

  @Nonnull
  default BootstrapBadge badgePrimary (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapBadge (EBootstrapBadgeType.PRIMARY).addChildren (aNodes);
  }

  @Nonnull
  default BootstrapBadge badgePrimary (@Nullable final String... aTexts)
  {
    return new BootstrapBadge ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapBadge badgeSuccess ()
  {
    return new BootstrapBadge (EBootstrapBadgeType.SUCCESS);
  }

  @Nonnull
  default BootstrapBadge badgeSuccess (final int nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.SUCCESS).addChild (Integer.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeSuccess (final long nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.SUCCESS).addChild (Long.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeSuccess (@Nullable final IHCNode aNode)
  {
    return new BootstrapBadge (EBootstrapBadgeType.SUCCESS).addChild (aNode);
  }

  @Nonnull
  default BootstrapBadge badgeSuccess (@Nullable final String s)
  {
    return new BootstrapBadge (EBootstrapBadgeType.SUCCESS).addChild (s);
  }

  @Nonnull
  default BootstrapBadge badgeSuccess (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapBadge (EBootstrapBadgeType.SUCCESS).addChildren (aNodes);
  }

  @Nonnull
  default BootstrapBadge badgeSuccess (@Nullable final String... aTexts)
  {
    return new BootstrapBadge ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapBadge badgeWarn ()
  {
    return new BootstrapBadge (EBootstrapBadgeType.WARNING);
  }

  @Nonnull
  default BootstrapBadge badgeWarn (final int nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.WARNING).addChild (Integer.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeWarn (final long nValue)
  {
    return new BootstrapBadge (EBootstrapBadgeType.WARNING).addChild (Long.toString (nValue));
  }

  @Nonnull
  default BootstrapBadge badgeWarn (@Nullable final IHCNode aNode)
  {
    return new BootstrapBadge (EBootstrapBadgeType.WARNING).addChild (aNode);
  }

  @Nonnull
  default BootstrapBadge badgeWarn (@Nullable final String s)
  {
    return new BootstrapBadge (EBootstrapBadgeType.WARNING).addChild (s);
  }

  @Nonnull
  default BootstrapBadge badgeWarn (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapBadge (EBootstrapBadgeType.WARNING).addChildren (aNodes);
  }

  @Nonnull
  default BootstrapBadge badgeWarn (@Nullable final String... aTexts)
  {
    return new BootstrapBadge ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapDangerBox danger ()
  {
    return new BootstrapDangerBox ();
  }

  @Nonnull
  default BootstrapDangerBox danger (@Nullable final IHCNode aNode)
  {
    return new BootstrapDangerBox ().addChild (aNode);
  }

  @Nonnull
  default BootstrapDangerBox danger (@Nullable final String s)
  {
    return new BootstrapDangerBox ().addChild (s);
  }

  @Nonnull
  default BootstrapDangerBox danger (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapDangerBox ().addChildren (aNodes);
  }

  @Nonnull
  default BootstrapDangerBox danger (@Nullable final String... aTexts)
  {
    return new BootstrapDangerBox ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapErrorBox error ()
  {
    return new BootstrapErrorBox ();
  }

  @Nonnull
  default BootstrapErrorBox error (@Nullable final IHCNode aNode)
  {
    return new BootstrapErrorBox ().addChild (aNode);
  }

  @Nonnull
  default BootstrapErrorBox error (@Nullable final String s)
  {
    return new BootstrapErrorBox ().addChild (s);
  }

  @Nonnull
  default BootstrapErrorBox error (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapErrorBox ().addChildren (aNodes);
  }

  @Nonnull
  default BootstrapErrorBox error (@Nullable final String... aTexts)
  {
    return new BootstrapErrorBox ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapInfoBox info ()
  {
    return new BootstrapInfoBox ();
  }

  @Nonnull
  default BootstrapInfoBox info (@Nullable final IHCNode aNode)
  {
    return new BootstrapInfoBox ().addChild (aNode);
  }

  @Nonnull
  default BootstrapInfoBox info (@Nullable final String s)
  {
    return new BootstrapInfoBox ().addChild (s);
  }

  @Nonnull
  default BootstrapInfoBox info (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapInfoBox ().addChildren (aNodes);
  }

  @Nonnull
  default BootstrapInfoBox info (@Nullable final String... aTexts)
  {
    return new BootstrapInfoBox ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapQuestionBox question ()
  {
    return new BootstrapQuestionBox ();
  }

  @Nonnull
  default BootstrapQuestionBox question (@Nullable final IHCNode aNode)
  {
    return new BootstrapQuestionBox ().addChild (aNode);
  }

  @Nonnull
  default BootstrapQuestionBox question (@Nullable final String s)
  {
    return new BootstrapQuestionBox ().addChild (s);
  }

  @Nonnull
  default BootstrapQuestionBox question (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapQuestionBox ().addChildren (aNodes);
  }

  @Nonnull
  default BootstrapQuestionBox question (@Nullable final String... aTexts)
  {
    return new BootstrapQuestionBox ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapSuccessBox success ()
  {
    return new BootstrapSuccessBox ();
  }

  @Nonnull
  default BootstrapSuccessBox success (@Nullable final IHCNode aNode)
  {
    return new BootstrapSuccessBox ().addChild (aNode);
  }

  @Nonnull
  default BootstrapSuccessBox success (@Nullable final String s)
  {
    return new BootstrapSuccessBox ().addChild (s);
  }

  @Nonnull
  default BootstrapSuccessBox success (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapSuccessBox ().addChildren (aNodes);
  }

  @Nonnull
  default BootstrapSuccessBox success (@Nullable final String... aTexts)
  {
    return new BootstrapSuccessBox ().addChildren (aTexts);
  }

  @Nonnull
  default BootstrapWarnBox warn ()
  {
    return new BootstrapWarnBox ();
  }

  @Nonnull
  default BootstrapWarnBox warn (@Nullable final IHCNode aNode)
  {
    return new BootstrapWarnBox ().addChild (aNode);
  }

  @Nonnull
  default BootstrapWarnBox warn (@Nullable final String s)
  {
    return new BootstrapWarnBox ().addChild (s);
  }

  @Nonnull
  default BootstrapWarnBox warn (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    return new BootstrapWarnBox ().addChildren (aNodes);
  }

  @Nonnull
  default BootstrapWarnBox warn (@Nullable final String... aTexts)
  {
    return new BootstrapWarnBox ().addChildren (aTexts);
  }
}
