/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.collection.stack.NonBlockingStack;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.IHCTextNode;
import com.helger.html.hc.html.IHCMediaElementChild;
import com.helger.html.hc.html.embedded.IHCMediaElement;
import com.helger.html.hc.html.forms.HCOptGroup;
import com.helger.html.hc.html.forms.HCOption;
import com.helger.html.hc.html.grouping.HCDL;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.grouping.IHCDefinitionItem;
import com.helger.html.hc.html.grouping.IHCList;
import com.helger.html.hc.html.script.AbstractHCScriptInline;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCColGroup;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTBody;
import com.helger.html.hc.html.tabular.HCTFoot;
import com.helger.html.hc.html.tabular.HCTHead;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.tabular.IHCTable;
import com.helger.html.hc.html.tabular.IHCTablePart;
import com.helger.html.hc.html.textlevel.HCRuby;
import com.helger.html.hc.html.textlevel.IHCRubyChild;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.js.UnparsedJSCodeProvider;

@NotThreadSafe
final class MarkdownHCStack
{
  private final NonBlockingStack <IHCNode> m_aStack = new NonBlockingStack <> ();

  public MarkdownHCStack ()
  {
    m_aStack.push (new HCNodeList ());
  }

  public void push (@NonNull final IHCNode aNode)
  {
    append (aNode);
    m_aStack.push (aNode);
  }

  @NonNull
  public <T extends IHCNodeWithChildren <T>> T push (@NonNull final T aNode)
  {
    append (aNode);
    m_aStack.push (aNode);
    return aNode;
  }

  public void pop ()
  {
    // Never pop the fixed nodelist
    if (m_aStack.size () == 1)
      throw new MarkdownException ("Can't pop from empty stack");
    m_aStack.pop ();
  }

  @NonNull
  public HCNodeList getRoot ()
  {
    return (HCNodeList) m_aStack.get (0);
  }

  public void reset ()
  {
    // Ensure to create a new node list in case the object was appended!
    m_aStack.clear ();
    m_aStack.push (new HCNodeList ());
  }

  public void append (final char c)
  {
    append (new HCTextNode (c));
  }

  public void append (@Nullable final String s)
  {
    if (StringHelper.isNotEmpty (s))
      append (new HCTextNode (s));
  }

  public void append (@NonNull final IHCNode aNode)
  {
    ValueEnforcer.notNull (aNode, "Node");

    final IHCNode aParent = m_aStack.peek ();

    // Handle special cases
    if (aParent instanceof final IHCList <?, ?> aParentList && aNode instanceof HCLI)
      aParentList.addItem (aNode);
    else
      if (aParent instanceof final IHCMediaElement <?> aParentMedia &&
          aNode instanceof final IHCMediaElementChild <?> aMediaChild)
        aParentMedia.addChild (aMediaChild);
      else
        if (aParent instanceof final HCColGroup aParentColGroup && aNode instanceof final HCCol aCol)
          aParentColGroup.addChild (aCol);
        else
          if (aParent instanceof final HCDL aParentDL && aNode instanceof final IHCDefinitionItem <?> aItem)
            aParentDL.addChild (aItem);
          else
            if (aParent instanceof final HCOptGroup aParentOptGroup && aNode instanceof final HCOption aOption)
              aParentOptGroup.addChild (aOption);
            else
              if (aParent instanceof final HCOption aParentOption && aNode instanceof final HCTextNode aTextNode)
                aParentOption.addChild (aTextNode);
              else
                if (aParent instanceof final IHCTable <?> aParentTable)
                {
                  if (aNode instanceof final HCTHead aHead)
                    aParentTable.setHead (aHead);
                  else
                    if (aNode instanceof final HCTBody aBody)
                      aParentTable.setBody (aBody);
                    else
                      if (aNode instanceof final HCTFoot aFoot)
                        aParentTable.setFoot (aFoot);
                      else
                        if (aNode instanceof final HCRow aRow)
                          aParentTable.addBodyRow (aRow);
                        else
                          throw new MarkdownException ("Cannot add node " + aNode + " to " + aParent);
                }
                else
                  if (aParent instanceof final IHCTablePart <?> aParentTablePart && aNode instanceof final HCRow aRow)
                    aParentTablePart.addChild (aRow);
                  else
                    if (aParent instanceof final HCRow aParentRow && aNode instanceof final IHCCell <?> aCell)
                      aParentRow.addChild (aCell);
                    else
                      if (aParent instanceof final HCRuby aParentRuby && aNode instanceof final IHCRubyChild <?> aRubyChild)
                        aParentRuby.addChild (aRubyChild);
                      else
                        if (aParent instanceof final IHCNodeWithChildren <?> aRealParent)
                        {
                          if (aNode instanceof final HCTextNode aTextNode &&
                              aRealParent.getLastChild () instanceof final HCTextNode aParentLastTextNode)
                          {
                            // Append
                            aParentLastTextNode.addText (aTextNode.getText ());
                          }
                          else
                          {
                            // Set
                            aRealParent.addChild (aNode);
                          }
                        }
                        else
                          if (aParent instanceof final AbstractHCScriptInline <?> aParentScript &&
                              aNode instanceof final IHCTextNode <?> aTextNode)
                          {
                            if (aParentScript.getJSCodeProvider () instanceof final UnparsedJSCodeProvider aUnparsed)
                            {
                              // Append
                              aParentScript.setJSCodeProvider (new UnparsedJSCodeProvider (aUnparsed.getJSCode () +
                                                                                         aTextNode.getText ()));
                            }
                            else
                            {
                              // Set
                              aParentScript.setJSCodeProvider (new UnparsedJSCodeProvider (aTextNode.getText ()));
                            }
                          }
                          else
                            throw new MarkdownException ("Cannot add node " + aNode + " to " + aParent);
  }
}
