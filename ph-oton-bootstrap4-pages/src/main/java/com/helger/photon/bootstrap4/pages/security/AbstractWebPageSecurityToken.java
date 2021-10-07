/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.security;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.datetime.PDTToString;
import com.helger.commons.id.IHasID;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.badge.BootstrapBadge;
import com.helger.photon.bootstrap4.badge.EBootstrapBadgeType;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.revocation.IRevocationStatus;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractWebPageSecurityToken <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext>
                                                   extends
                                                   AbstractWebPageSecurityObjectWithAttributes <DATATYPE, WPECTYPE>
{
  public static final int TOKEN_STRING_MIN_LENGTH = 16;

  @Translatable
  protected enum EBaseText implements IHasDisplayTextWithArgs
  {
    LABEL_TOKEN_STRING ("Zugriffs-Token", "Access token"),
    HELPTEXT_TOKEN_STRING ("Hier kann ein existierender Zugriffs-Token (Mindestlänge von " +
                           TOKEN_STRING_MIN_LENGTH +
                           ") von einem anderen System eingegeben werden. Wenn das Feld leer gelassen wird, wird ein neuer zufälliger Zugriffs-Token erstellt.",
                           "An existing access token (minimum length of " +
                                                                                                                                                                 TOKEN_STRING_MIN_LENGTH +
                                                                                                                                                                 ") from another system can be provided here. If the field stays empty, a new random access token is created."),
    LABEL_ACCESS_TOKENS ("Zugriffs-Token", "Access tokens"),
    SHOW_REVOKED ("Zurückgezogen von {0} um {1}; Begründung: {2}", "Revoked by {0} on {1}; reason: {2}"),
    SHOW_INVALID_NOW ("Jetzt nicht mehr gültig", "Not valid now"),
    SHOW_VALID_NOW ("Jetzt gültig", "Valid now"),
    SHOW_ACCESS_TOKEN ("Zugriffs-Token: ", "Access token: "),
    SHOW_NOT_BEFORE ("Gültig ab: {0}", "Not before: {0}"),
    SHOW_NOT_AFTER ("Gültig bis: {0}", "Not after: {0}"),
    ERR_TOKEN_STRING_TOO_SHORT ("Das Zugriffs-Token ist zu kurz. Es muss mindestens " +
                                TOKEN_STRING_MIN_LENGTH +
                                " Zeichen haben.",
                                "The access token is too short. It must have at least " +
                                                   TOKEN_STRING_MIN_LENGTH +
                                                   " characters."),
    ERR_TOKEN_STRING_IN_USE ("Das Zugriffs-Token ist bereits vergeben und kann nicht nochmal vergeben werden.",
                             "The access token is already in use and cannot be assigned again."),
    LABEL_REASON ("Begründung", "Reason"),
    ERR_REASON_EMPTY ("Es muss eine Bgründung angegeben werden!", "A reason must be provided!"),
    REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_SUCCESS ("Das alte Zugriffs-Token von ''{0}'' wurde widerrufen und ein Neues wurde erfolgreich erstellt.",
                                                "The old access token of ''{0}'' was revoked and a new access token was successfully created."),
    REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_HEADER ("Das alte Zugriffs-Token von ''{0}'' widerrufen und ein Neues erstellen",
                                               "Revoke the old access token of ''{0}'' and create a new access token"),
    CREATE_NEW_ACCESS_TOKEN_SUCCESS ("Das neue Zugriffs-Token für ''{0}'' wurde erfolgreich erstellt.",
                                     "A new access token for ''{0}'' was successfully created."),
    CREATE_NEW_ACCESS_TOKEN_HEADER ("Ein neues Zugriffs-Token für ''{0}'' erstellen",
                                    "Create a new access token for ''{0}''"),
    REVOKE_ACCESS_TOKEN_SUCCESS ("Das alte Zugriffs-Token von ''{0}'' wurde erfolgreich widerrufen.",
                                 "The old access token of ''{0}'' was successfully revoked."),
    REVOKE_ACCESS_TOKEN_HEADER ("Das alte Zugriffs-Token von ''{0}'' widerrufen",
                                "Revoke the old access token of ''{0}''"),
    TITLE_ACTION_CREATE_NEW_ACCESS_TOKEN ("Neuen Zugriffs-Token für ''{0}'' erzeugen",
                                          "Create new access token for ''{0}''"),
    TITLE_ACTION_REVOKE_ACCESS_TOKEN ("Zugriffs-Token für ''{0}'' zurückziehen", "Revoke access token for ''{0}''");

    private final IMultilingualText m_aTP;

    EBaseText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public AbstractWebPageSecurityToken (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public AbstractWebPageSecurityToken (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    super (sID, aName);
  }

  public AbstractWebPageSecurityToken (@Nonnull @Nonempty final String sID,
                                       @Nonnull final String sName,
                                       @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public AbstractWebPageSecurityToken (@Nonnull @Nonempty final String sID,
                                       @Nonnull final IMultilingualText aName,
                                       @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Nonnull
  public static IHCNode createAccessTokenListUI (@Nonnull final ICommonsList <? extends IAccessToken> aAccessTokens,
                                                 @Nonnull final Locale aDisplayLocale)
  {
    final HCUL aAT = new HCUL ();
    // Reverse so that the newest token is on top
    for (final IAccessToken aToken : aAccessTokens.reverse ())
    {
      final IRevocationStatus aRevocationStatus = aToken.getRevocationStatus ();

      final HCNodeList aItem = new HCNodeList ();
      if (aRevocationStatus.isRevoked ())
      {
        // Revoked
        final String sUserName = SecurityHelper.getUserDisplayName (aRevocationStatus.getRevocationUserID (),
                                                                    aDisplayLocale);
        aItem.addChild (new BootstrapBadge (EBootstrapBadgeType.DANGER).addChild (EBaseText.SHOW_REVOKED.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                 sUserName,
                                                                                                                                 PDTToString.getAsString (aRevocationStatus.getRevocationDateTime (),
                                                                                                                                                          aDisplayLocale),
                                                                                                                                 aRevocationStatus.getRevocationReason ())));
      }
      else
      {
        // Not revoked
        if (!aToken.isValidNow ())
          aItem.addChild (new BootstrapBadge (EBootstrapBadgeType.DANGER).addChild (EBaseText.SHOW_INVALID_NOW.getDisplayText (aDisplayLocale)));
        else
          aItem.addChild (new BootstrapBadge (EBootstrapBadgeType.SUCCESS).addChild (EBaseText.SHOW_VALID_NOW.getDisplayText (aDisplayLocale)));
      }
      aItem.addChild (new HCDiv ().addChild (EBaseText.SHOW_ACCESS_TOKEN.getDisplayText (aDisplayLocale))
                                  .addChild (SecurityUIHelper.createAccessTokenNode (aToken.getTokenString ())));
      aItem.addChild (new HCDiv ().addChild (EBaseText.SHOW_NOT_BEFORE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                               PDTToString.getAsString (aToken.getNotBefore (),
                                                                                                                        aDisplayLocale))));
      if (aToken.getNotAfter () != null)
        aItem.addChild (new HCDiv ().addChild (EBaseText.SHOW_NOT_BEFORE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                 PDTToString.getAsString (aToken.getNotAfter (),
                                                                                                                          aDisplayLocale))));
      aAT.addItem (aItem);
    }
    return aAT;
  }
}
