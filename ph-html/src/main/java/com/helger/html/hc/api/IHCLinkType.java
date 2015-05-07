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
package com.helger.html.hc.api;

/**
 * Base interface for link types (used in {@link com.helger.html.hc.html.HCLink}
 * )
 *
 * @author Philip Helger
 */
public interface IHCLinkType extends IHCHasHTMLAttributeValue
{
  /**
   * @return <code>true</code> if links of this type can occur more than once,
   *         <code>false</code> if it may occur only once.
   */
  boolean isAllowedMoreThanOnce ();
}
