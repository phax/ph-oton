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
package com.helger.html.hc.html5;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.microdom.EMicroNodeType;
import com.helger.commons.microdom.IMicroDocumentType;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.microdom.impl.AbstractMicroNode;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;

/**
 * HTML5 document type representation
 * 
 * @author Philip Helger
 */
@Immutable
public class HTML5DocumentType extends AbstractMicroNode implements IMicroDocumentType
{
  public HTML5DocumentType ()
  {}

  @Nonnull
  public EMicroNodeType getType ()
  {
    return EMicroNodeType.DOCUMENT_TYPE;
  }

  @Nonnull
  public String getNodeName ()
  {
    return "#doctype";
  }

  @Nonnull
  public String getQualifiedName ()
  {
    return EHTMLElement.HTML.getElementName ();
  }

  @Nullable
  public String getPublicID ()
  {
    return null;
  }

  @Nullable
  public String getSystemID ()
  {
    return null;
  }

  @Nonnull
  public String getHTMLRepresentation ()
  {
    return "<!DOCTYPE " + getQualifiedName () + ">";
  }

  @Nonnull
  public IMicroDocumentType getClone ()
  {
    // Must be a new object because of the potentially assigned parent
    return new HTML5DocumentType ();
  }

  public boolean isEqualContent (@Nullable final IMicroNode aNode)
  {
    return aNode instanceof HTML5DocumentType;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).toString ();
  }
}
