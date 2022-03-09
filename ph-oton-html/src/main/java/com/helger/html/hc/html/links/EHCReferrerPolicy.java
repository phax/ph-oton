/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.links;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.IHCHasHTMLAttributeValue;

/**
 * Referrer policy<br>
 * Source: https://w3c.github.io/webappsec-referrer-policy/#referrer-policy
 *
 * @author Philip Helger
 * @since 8.4.0.
 */
public enum EHCReferrerPolicy implements IHCHasHTMLAttributeValue
{
  /**
   * The empty string "" corresponds to no referrer policy, causing a fallback
   * to a referrer policy defined elsewhere, or in the case where no such
   * higher-level policy is available, falling back to the default referrer
   * policy. This happens in Fetch’s main fetch algorithm, for example.
   */
  NONE (""),
  /**
   * The simplest policy is "no-referrer", which specifies that no referrer
   * information is to be sent along with requests to any origin. The header
   * Referer will be omitted entirely.
   */
  NO_REFERRER ("no-referrer"),
  /**
   * The "no-referrer-when-downgrade" policy sends a request’s full referrerURL
   * stripped for use as a referrer for requests:
   * <ul>
   * <li>whose referrerURL and current URL are both potentially trustworthy
   * URLs, or</li>
   * <li>whose referrerURL is a non-potentially trustworthy URL.</li>
   * </ul>
   * Requests whose referrerURL is a potentially trustworthy URL and whose
   * current URL is a non-potentially trustworthy URL on the other hand, will
   * contain no referrer information. A Referer HTTP header will not be sent.
   */
  NO_REFERRER_WHEN_DOWNGRADE ("no-referrer-when-downgrade"),
  /**
   * The "same-origin" policy specifies that a request’s full referrerURL is
   * sent as referrer information when making same-origin-referrer requests.
   * <br>
   * Cross-origin-referrer requests, on the other hand, will contain no referrer
   * information. A Referer HTTP header will not be sent.
   */
  SAME_ORIGIN ("same-origin"),
  /**
   * The "origin" policy specifies that only the ASCII serialization of the
   * request’s referrerURL is sent as referrer information when making both
   * same-origin-referrer requests and cross-origin-referrer requests.
   */
  ORIGIN ("origin"),
  /**
   * The "strict-origin" policy sends the ASCII serialization of the origin of
   * the referrerURL for requests:
   * <ul>
   * <li>whose referrerURL and current URL are both potentially trustworthy
   * URLs, or</li>
   * <li>whose referrerURL is a non-potentially trustworthy URL.</li>
   * </ul>
   * Requests whose referrerURL is a potentially trustworthy URL and whose
   * current URL is a non-potentially trustworthy URL on the other hand, will
   * contain no referrer information. A Referer HTTP header will not be sent.
   */
  STRICT_ORIGIN ("strict-origin"),
  /**
   * The "origin-when-cross-origin" policy specifies that a request’s full
   * referrerURL is sent as referrer information when making
   * same-origin-referrer requests, and only the ASCII serialization of the
   * origin of the request’s referrerURL is sent as referrer information when
   * making cross-origin-referrer requests.
   */
  ORIGIN_WHEN_CROSS_ORIGIN ("origin-when-cross-origin"),
  /**
   * The "strict-origin-when-cross-origin" policy specifies that a request’s
   * full referrerURL is sent as referrer information when making
   * same-origin-referrer requests, and only the ASCII serialization of the
   * origin of the request’s referrerURL when making cross-origin-referrer
   * requests:
   * <ul>
   * <li>whose referrerURL and current URL are both potentially trustworthy
   * URLs, or</li>
   * <li>whose referrerURL is a non-potentially trustworthy URL.</li>
   * </ul>
   * Requests whose referrerURL is a potentially trustworthy URL and whose
   * current URL is a non-potentially trustworthy URL on the other hand, will
   * contain no referrer information. A Referer HTTP header will not be sent.
   */
  STRICT_ORIGIN_WHEN_CROSS_ORIGIN ("strict-origin-when-cross-origin"),
  /**
   * The "unsafe-url" policy specifies that a request’s full referrerURL is sent
   * along for both same-origin-referrer requests and cross-origin-referrer
   * requests.
   */
  UNSAFE_URL ("unsafe-url");

  /** Default policy: strict-origin-when-cross-origin */
  @Nonnull
  public static final EHCReferrerPolicy DEFAULT = STRICT_ORIGIN_WHEN_CROSS_ORIGIN;

  private final String m_sAttrValue;

  EHCReferrerPolicy (@Nonnull @Nonempty final String sAttrValue)
  {
    m_sAttrValue = sAttrValue;
  }

  @Nonnull
  @Nonempty
  public String getAttrValue ()
  {
    return m_sAttrValue;
  }

  @Nullable
  public static EHCReferrerPolicy getFromAttrValueOrNull (@Nullable final String sAttrValue)
  {
    if (StringHelper.hasNoText (sAttrValue))
      return null;
    return EnumHelper.findFirst (EHCReferrerPolicy.class, x -> x.hasAttrValue (sAttrValue));
  }
}
