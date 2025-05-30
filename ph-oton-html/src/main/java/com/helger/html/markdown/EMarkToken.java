/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.markdown;

/**
 * Markdown token enumeration.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt
 */
enum EMarkToken
{
  /** No token. */
  NONE,
  /** &#x2a; */
  EM_STAR, // x*x
  /** _ */
  EM_UNDERSCORE, // x_x
  /** &#x2a;&#x2a; */
  STRONG_STAR, // x**x
  /** __ */
  STRONG_UNDERSCORE, // x__x
  /** ~~ */
  STRIKE, // x~~x
  /** ` */
  CODE_SINGLE, // `
  /** `` */
  CODE_DOUBLE, // ``
  /** [ */
  LINK, // [
  /** &lt; */
  HTML, // <
  /** ![ */
  IMAGE, // ![
  /** &amp; */
  ENTITY, // &
  /** \ */
  ESCAPE, // \x
  /** Extended: ^ */
  SUPER, // ^
  /** Extended: (C) */
  X_COPY, // (C)
  /** Extended: (R) */
  X_REG, // (R)
  /** Extended: (TM) */
  X_TRADE, // (TM)
  /** Extended: &lt;&lt; */
  X_LAQUO, // <<
  /** Extended: >> */
  X_RAQUO, // >>
  /** Extended: -- */
  X_NDASH, // --
  /** Extended: --- */
  X_MDASH, // ---
  /** Extended: &#46;&#46;&#46; */
  X_HELLIP, // ...
  /** Extended: "x */
  X_RDQUO, // "
  /** Extended: x" */
  X_LDQUO, // "
  /** [[ */
  X_LINK_OPEN, // [[
  /** ]] */
  X_LINK_CLOSE, // ]]
}
