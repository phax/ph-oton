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
/* Internet Explorer 10 doesn't differentiate device width 
   from viewport width, and thus doesn't properly apply the 
   media queries in Bootstrap's CSS. To address this, you can
   optionally include the following CSS and JavaScript to work
   around this problem until Microsoft issues a fix. */
if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
  var msViewportStyle = document.createElement("style");
  msViewportStyle.appendChild(document.createTextNode("@-ms-viewport{width:auto!important}"));
  document.querySelector('head').appendChild(msViewportStyle);
}

$(function () {
  var nua = navigator.userAgent;
  var isAndroid = (nua.indexOf('Mozilla/5.0') > -1 && nua.indexOf('Android ') > -1 && nua.indexOf('AppleWebKit') > -1 && nua.indexOf('Chrome') === -1);
  if (isAndroid) {
    $('select.form-control').removeClass('form-control').css('width', '100%');
  }
});


/** Auto show Tabs */
//http://stackoverflow.com/questions/7862233/twitter-bootstrap-tabs-go-to-specific-tab-on-page-reload-or-hyperlink?rq=1
$(document).ready(function() {
  var hash = document.location.hash;
  // Use prefix to create a non-existing ID so that no scrolling occurs!
  var prefix = "tab_";
  if (hash) {
    $('.nav-tabs a[href="'+hash.replace(prefix,"")+'"]').tab('show');
  }
  
  // Change hash for page-reload
  $('.nav-tabs a').on('shown.bs.tab', function (e) {
    var realHash = e.target.hash.replace("#", "#" + prefix);
    if (history.replaceState) {
      history.replaceState ({}, null, realHash); 
    } else {
      // Polyfill for old browsers
      window.location.hash = realHash; 
    }
  });
});