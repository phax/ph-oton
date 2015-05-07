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
package com.helger.photon.uicore.js;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.html.hc.html.HCScript;

/**
 * A Script to determine, if local storage is available.
 * 
 * @author Philip Helger
 */
public class JSLocalStorageDeterminator extends HCScript
{
  public static final String VARNAME = "g_aLocalStorage";

  public JSLocalStorageDeterminator ()
  {
    this (VARNAME);
  }

  public JSLocalStorageDeterminator (@Nonnull @Nonempty final String sVarName)
  {
    super ("var " +
           sVarName +
           "=(function(){var uid=new Date,storage,result;try {" +
           "(storage=window.localStorage).setItem(uid, uid);" +
           "result=storage.getItem(uid)==uid;" +
           "storage.removeItem(uid);" +
           "return result && storage;" +
           "} catch(e) {}" +
           "}());");
  }
}
