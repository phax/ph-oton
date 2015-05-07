/*
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
/**
 * Loads the Facebook SDK asynchronously
 * 
 * @param sAppID        
 *        {String} Your Facebook APP-ID
 * @param sLang 
 *        {String} The current display locale
 * @param sContainerID
          {String} The ID of the container element to which necessary DOM elements can be appended
 * @param bCheckLoginStatus [optional] 
 *        {Boolean} Whether or not to check the current login status 
 * @param bEnableCookies [optional] 
 *        {Boolean} Whether or not to enable cookies 
 * @param bUseXFBML [optional] 
 *        {Boolean} Whether or not to use XFBML 
 * @param fnOnLoad [optional] 
 *        {Function} An optional callback (without arguments) which will be executed on initialization 
 */
function facebookLoadSDKAsync (sAppID, sLang, sContainerID, bCheckLoginStatus, bEnableCookies, bUseXFBML, fnOnLoad)
{
  /* All Facebook functions should be included in this function, or at least initiated from here */
  window.fbAsyncInit = function() 
  {
    FB.init({
      appId: sAppID, 
      status: !!bCheckLoginStatus, 
      cookie: !!bEnableCookies,
      xfbml: !!bUseXFBML
    });
// API not yet working due to missing access token
//    FB.api('/me', function(response) {
//      console.log(response.name);
//      });
    if (typeof (fnOnLoad) === "function")
    {
      fnOnLoad();
    }
  };
}

function facebookLoadSDKjQuery (sAppID, sLang, sContainerID, bCheckLoginStatus, bEnableCookies, bUseXFBML, fnOnLoad)
{
  $.ajaxSetup({ cache: true });
  $.getScript('//connect.facebook.net/' + sLang + '/all.js', function(){
    FB.init({
      appId: sAppID, 
      status: !!bCheckLoginStatus, 
      cookie: !!bEnableCookies,
      xfbml: !!bUseXFBML
    });     
    if (typeof (fnOnLoad) === "function")
    {
      fnOnLoad();
    }
  });
}
