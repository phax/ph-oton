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

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * Block emitter interface. An example for a code block emitter is given below:
 *
 * <pre>
 * public void emitBlock (StringBuilder out, List &lt;String&gt; lines, String meta)
 * {
 *   out.append (&quot;&lt;pre&gt;&lt;code&gt;&quot;);
 *   for (final String s : lines)
 *   {
 *     for (int i = 0; i &lt; s.length (); i++)
 *     {
 *       final char c = s.charAt (i);
 *       switch (c)
 *       {
 *         case '&amp;':
 *           out.append (&quot;&amp;amp;&quot;);
 *           break;
 *         case '&lt;':
 *           out.append (&quot;&amp;lt;&quot;);
 *           break;
 *         case '&gt;':
 *           out.append (&quot;&amp;gt;&quot;);
 *           break;
 *         default:
 *           out.append (c);
 *           break;
 *       }
 *     }
 *     out.append ('\n');
 *   }
 *   out.append (&quot;&lt;/code&gt;&lt;/pre&gt;\n&quot;);
 * }
 * </pre>
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 * @since 0.7
 */
public interface IMarkdownBlockEmitter
{
  /**
   * This method is responsible for outputting a markdown block and for any
   * needed pre-processing like escaping HTML special characters.
   *
   * @param out
   *        The StringBuilder to append to
   * @param lines
   *        List of lines
   * @param meta
   *        Meta information as a single String (if any) or empty String
   */
  void emitBlock (@NonNull MarkdownHCStack out, @NonNull List <String> lines, @NonNull String meta);
}
