/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webctrls.famfam;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.html.HCSpan;
import com.helger.webctrls.custom.IIcon;

/**
 * Contains all free FamFam 0.13 icons
 *
 * @author Philip Helger
 */
public enum EFamFamFlagIcon implements IIcon, IHasID <String>
{
  AD ("ad", "famfam-flag-ad"),
  AE ("ae", "famfam-flag-ae"),
  AF ("af", "famfam-flag-af"),
  AG ("ag", "famfam-flag-ag"),
  AI ("ai", "famfam-flag-ai"),
  AL ("al", "famfam-flag-al"),
  AM ("am", "famfam-flag-am"),
  AN ("an", "famfam-flag-an"),
  AO ("ao", "famfam-flag-ao"),
  AR ("ar", "famfam-flag-ar"),
  AS ("as", "famfam-flag-as"),
  AT ("at", "famfam-flag-at"),
  AU ("au", "famfam-flag-au"),
  AW ("aw", "famfam-flag-aw"),
  AX ("ax", "famfam-flag-ax"),
  AZ ("az", "famfam-flag-az"),
  BA ("ba", "famfam-flag-ba"),
  BB ("bb", "famfam-flag-bb"),
  BD ("bd", "famfam-flag-bd"),
  BE ("be", "famfam-flag-be"),
  BF ("bf", "famfam-flag-bf"),
  BG ("bg", "famfam-flag-bg"),
  BH ("bh", "famfam-flag-bh"),
  BI ("bi", "famfam-flag-bi"),
  BJ ("bj", "famfam-flag-bj"),
  BM ("bm", "famfam-flag-bm"),
  BN ("bn", "famfam-flag-bn"),
  BO ("bo", "famfam-flag-bo"),
  BR ("br", "famfam-flag-br"),
  BS ("bs", "famfam-flag-bs"),
  BT ("bt", "famfam-flag-bt"),
  BV ("bv", "famfam-flag-bv"),
  BW ("bw", "famfam-flag-bw"),
  BY ("by", "famfam-flag-by"),
  BZ ("bz", "famfam-flag-bz"),
  CA ("ca", "famfam-flag-ca"),
  CATALONIA ("catalonia", "famfam-flag-catalonia"),
  CC ("cc", "famfam-flag-cc"),
  CD ("cd", "famfam-flag-cd"),
  CF ("cf", "famfam-flag-cf"),
  CG ("cg", "famfam-flag-cg"),
  CH ("ch", "famfam-flag-ch"),
  CI ("ci", "famfam-flag-ci"),
  CK ("ck", "famfam-flag-ck"),
  CL ("cl", "famfam-flag-cl"),
  CM ("cm", "famfam-flag-cm"),
  CN ("cn", "famfam-flag-cn"),
  CO ("co", "famfam-flag-co"),
  CR ("cr", "famfam-flag-cr"),
  CS ("cs", "famfam-flag-cs"),
  CU ("cu", "famfam-flag-cu"),
  CV ("cv", "famfam-flag-cv"),
  CX ("cx", "famfam-flag-cx"),
  CY ("cy", "famfam-flag-cy"),
  CZ ("cz", "famfam-flag-cz"),
  DE ("de", "famfam-flag-de"),
  DJ ("dj", "famfam-flag-dj"),
  DK ("dk", "famfam-flag-dk"),
  DM ("dm", "famfam-flag-dm"),
  DO ("do", "famfam-flag-do"),
  DZ ("dz", "famfam-flag-dz"),
  EC ("ec", "famfam-flag-ec"),
  EE ("ee", "famfam-flag-ee"),
  EG ("eg", "famfam-flag-eg"),
  EH ("eh", "famfam-flag-eh"),
  ENGLAND ("england", "famfam-flag-england"),
  ER ("er", "famfam-flag-er"),
  ES ("es", "famfam-flag-es"),
  ET ("et", "famfam-flag-et"),
  EUROPEANUNION ("europeanunion", "famfam-flag-europeanunion"),
  FAM ("fam", "famfam-flag-fam"),
  FI ("fi", "famfam-flag-fi"),
  FJ ("fj", "famfam-flag-fj"),
  FK ("fk", "famfam-flag-fk"),
  FM ("fm", "famfam-flag-fm"),
  FO ("fo", "famfam-flag-fo"),
  FR ("fr", "famfam-flag-fr"),
  GA ("ga", "famfam-flag-ga"),
  GB ("gb", "famfam-flag-gb"),
  GD ("gd", "famfam-flag-gd"),
  GE ("ge", "famfam-flag-ge"),
  GF ("gf", "famfam-flag-gf"),
  GH ("gh", "famfam-flag-gh"),
  GI ("gi", "famfam-flag-gi"),
  GL ("gl", "famfam-flag-gl"),
  GM ("gm", "famfam-flag-gm"),
  GN ("gn", "famfam-flag-gn"),
  GP ("gp", "famfam-flag-gp"),
  GQ ("gq", "famfam-flag-gq"),
  GR ("gr", "famfam-flag-gr"),
  GS ("gs", "famfam-flag-gs"),
  GT ("gt", "famfam-flag-gt"),
  GU ("gu", "famfam-flag-gu"),
  GW ("gw", "famfam-flag-gw"),
  GY ("gy", "famfam-flag-gy"),
  HK ("hk", "famfam-flag-hk"),
  HM ("hm", "famfam-flag-hm"),
  HN ("hn", "famfam-flag-hn"),
  HR ("hr", "famfam-flag-hr"),
  HT ("ht", "famfam-flag-ht"),
  HU ("hu", "famfam-flag-hu"),
  ID ("id", "famfam-flag-id"),
  IE ("ie", "famfam-flag-ie"),
  IL ("il", "famfam-flag-il"),
  IN ("in", "famfam-flag-in"),
  IO ("io", "famfam-flag-io"),
  IQ ("iq", "famfam-flag-iq"),
  IR ("ir", "famfam-flag-ir"),
  IS ("is", "famfam-flag-is"),
  IT ("it", "famfam-flag-it"),
  JM ("jm", "famfam-flag-jm"),
  JO ("jo", "famfam-flag-jo"),
  JP ("jp", "famfam-flag-jp"),
  KE ("ke", "famfam-flag-ke"),
  KG ("kg", "famfam-flag-kg"),
  KH ("kh", "famfam-flag-kh"),
  KI ("ki", "famfam-flag-ki"),
  KM ("km", "famfam-flag-km"),
  KN ("kn", "famfam-flag-kn"),
  KP ("kp", "famfam-flag-kp"),
  KR ("kr", "famfam-flag-kr"),
  KW ("kw", "famfam-flag-kw"),
  KY ("ky", "famfam-flag-ky"),
  KZ ("kz", "famfam-flag-kz"),
  LA ("la", "famfam-flag-la"),
  LB ("lb", "famfam-flag-lb"),
  LC ("lc", "famfam-flag-lc"),
  LI ("li", "famfam-flag-li"),
  LK ("lk", "famfam-flag-lk"),
  LR ("lr", "famfam-flag-lr"),
  LS ("ls", "famfam-flag-ls"),
  LT ("lt", "famfam-flag-lt"),
  LU ("lu", "famfam-flag-lu"),
  LV ("lv", "famfam-flag-lv"),
  LY ("ly", "famfam-flag-ly"),
  MA ("ma", "famfam-flag-ma"),
  MC ("mc", "famfam-flag-mc"),
  MD ("md", "famfam-flag-md"),
  ME ("me", "famfam-flag-me"),
  MG ("mg", "famfam-flag-mg"),
  MH ("mh", "famfam-flag-mh"),
  MK ("mk", "famfam-flag-mk"),
  ML ("ml", "famfam-flag-ml"),
  MM ("mm", "famfam-flag-mm"),
  MN ("mn", "famfam-flag-mn"),
  MO ("mo", "famfam-flag-mo"),
  MP ("mp", "famfam-flag-mp"),
  MQ ("mq", "famfam-flag-mq"),
  MR ("mr", "famfam-flag-mr"),
  MS ("ms", "famfam-flag-ms"),
  MT ("mt", "famfam-flag-mt"),
  MU ("mu", "famfam-flag-mu"),
  MV ("mv", "famfam-flag-mv"),
  MW ("mw", "famfam-flag-mw"),
  MX ("mx", "famfam-flag-mx"),
  MY ("my", "famfam-flag-my"),
  MZ ("mz", "famfam-flag-mz"),
  NA ("na", "famfam-flag-na"),
  NC ("nc", "famfam-flag-nc"),
  NE ("ne", "famfam-flag-ne"),
  NF ("nf", "famfam-flag-nf"),
  NG ("ng", "famfam-flag-ng"),
  NI ("ni", "famfam-flag-ni"),
  NL ("nl", "famfam-flag-nl"),
  NO ("no", "famfam-flag-no"),
  NP ("np", "famfam-flag-np"),
  NR ("nr", "famfam-flag-nr"),
  NU ("nu", "famfam-flag-nu"),
  NZ ("nz", "famfam-flag-nz"),
  OM ("om", "famfam-flag-om"),
  PA ("pa", "famfam-flag-pa"),
  PE ("pe", "famfam-flag-pe"),
  PF ("pf", "famfam-flag-pf"),
  PG ("pg", "famfam-flag-pg"),
  PH ("ph", "famfam-flag-ph"),
  PK ("pk", "famfam-flag-pk"),
  PL ("pl", "famfam-flag-pl"),
  PM ("pm", "famfam-flag-pm"),
  PN ("pn", "famfam-flag-pn"),
  PR ("pr", "famfam-flag-pr"),
  PS ("ps", "famfam-flag-ps"),
  PT ("pt", "famfam-flag-pt"),
  PW ("pw", "famfam-flag-pw"),
  PY ("py", "famfam-flag-py"),
  QA ("qa", "famfam-flag-qa"),
  RE ("re", "famfam-flag-re"),
  RO ("ro", "famfam-flag-ro"),
  RS ("rs", "famfam-flag-rs"),
  RU ("ru", "famfam-flag-ru"),
  RW ("rw", "famfam-flag-rw"),
  SA ("sa", "famfam-flag-sa"),
  SB ("sb", "famfam-flag-sb"),
  SC ("sc", "famfam-flag-sc"),
  SCOTLAND ("scotland", "famfam-flag-scotland"),
  SD ("sd", "famfam-flag-sd"),
  SE ("se", "famfam-flag-se"),
  SG ("sg", "famfam-flag-sg"),
  SH ("sh", "famfam-flag-sh"),
  SI ("si", "famfam-flag-si"),
  SJ ("sj", "famfam-flag-sj"),
  SK ("sk", "famfam-flag-sk"),
  SL ("sl", "famfam-flag-sl"),
  SM ("sm", "famfam-flag-sm"),
  SN ("sn", "famfam-flag-sn"),
  SO ("so", "famfam-flag-so"),
  SR ("sr", "famfam-flag-sr"),
  ST ("st", "famfam-flag-st"),
  SV ("sv", "famfam-flag-sv"),
  SY ("sy", "famfam-flag-sy"),
  SZ ("sz", "famfam-flag-sz"),
  TC ("tc", "famfam-flag-tc"),
  TD ("td", "famfam-flag-td"),
  TF ("tf", "famfam-flag-tf"),
  TG ("tg", "famfam-flag-tg"),
  TH ("th", "famfam-flag-th"),
  TJ ("tj", "famfam-flag-tj"),
  TK ("tk", "famfam-flag-tk"),
  TL ("tl", "famfam-flag-tl"),
  TM ("tm", "famfam-flag-tm"),
  TN ("tn", "famfam-flag-tn"),
  TO ("to", "famfam-flag-to"),
  TR ("tr", "famfam-flag-tr"),
  TT ("tt", "famfam-flag-tt"),
  TV ("tv", "famfam-flag-tv"),
  TW ("tw", "famfam-flag-tw"),
  TZ ("tz", "famfam-flag-tz"),
  UA ("ua", "famfam-flag-ua"),
  UG ("ug", "famfam-flag-ug"),
  UM ("um", "famfam-flag-um"),
  US ("us", "famfam-flag-us"),
  UY ("uy", "famfam-flag-uy"),
  UZ ("uz", "famfam-flag-uz"),
  VA ("va", "famfam-flag-va"),
  VC ("vc", "famfam-flag-vc"),
  VE ("ve", "famfam-flag-ve"),
  VG ("vg", "famfam-flag-vg"),
  VI ("vi", "famfam-flag-vi"),
  VN ("vn", "famfam-flag-vn"),
  VU ("vu", "famfam-flag-vu"),
  WALES ("wales", "famfam-flag-wales"),
  WF ("wf", "famfam-flag-wf"),
  WS ("ws", "famfam-flag-ws"),
  YE ("ye", "famfam-flag-ye"),
  YT ("yt", "famfam-flag-yt"),
  ZA ("za", "famfam-flag-za"),
  ZM ("zm", "famfam-flag-zm"),
  ZW ("zw", "famfam-flag-zw");

  public static final ICSSClassProvider CSS_CLASS_ICON_FAMFAM_FLAG = DefaultCSSClassProvider.create ("icon-famfam-flag");

  private final String m_sID;
  private final String m_sCSSClass;

  private EFamFamFlagIcon (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sCSSClass)
  {
    m_sID = sID;
    m_sCSSClass = sCSSClass;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  @Nonempty
  public String getCSSClass ()
  {
    return m_sCSSClass;
  }

  @Nonnull
  public IHCElement <?> getAsNode ()
  {
    final HCSpan ret = new HCSpan ();
    ret.addClasses (CSS_CLASS_ICON_FAMFAM_FLAG, this);
    return ret;
  }

  @Nullable
  public static EFamFamFlagIcon getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDCaseInsensitiveOrNull (EFamFamFlagIcon.class, sID);
  }
}
