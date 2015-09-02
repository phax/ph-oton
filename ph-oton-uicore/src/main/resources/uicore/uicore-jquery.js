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
// Extend jQuery
(function($)
{
  // Disable an element
  $.fn.disable = function()
  {
    return $(this).each(function()
    {
      $(this).prop('disabled', true);
    });
  };
  // Enable an element
  $.fn.enable = function()
  {
    return $(this).each(function()
    {
      $(this).prop('disabled', false);
    });
  };
  // Enable or disable an element
  $.fn.setDisabled = function(bDisabled)
  {
    return $(this).each(function()
    {
      $(this).prop('disabled', bDisabled);
    });
  };
  
  // Check a checkbox
  $.fn.check = function()
  {
    return $(this).each(function()
    {
      $(this).prop('checked', true);
    });
  };
  // uncheck a checkbox
  $.fn.uncheck = function()
  {
    return $(this).each(function()
    {
      $(this).prop('checked', false);
    });
  };
  // check or uncheck a checkbox
  $.fn.setChecked = function(bChecked)
  {
    return $(this).each(function()
    {
      $(this).prop('checked', bChecked);
    });
  };
  
  // Source: http://stackoverflow.com/questions/2830542/prevent-double-submission-of-forms-in-jquery
  // jQuery plugin to prevent double submission of forms
  $.fn.preventDoubleSubmission = function() {
    $(this).on('submit',function(e){
      var $form = $(this);
      if ($form.data('submitted') === true) {
        // Previously submitted - don't submit again
        e.preventDefault();
      } else {
        // Mark it so that the next submit can be ignored
        $form.data('submitted', true);
        // Disable all reset and submit buttons within the form
        $(':reset,:submit', $form).disable();
      }
    });

    // Keep chainability
    return this;
  };
})(jQuery);

// Set a default AJAX error handler
$(document).ajaxError (function(event, jqXHR, ajaxSettings, thrownError) {
  if (!window.ajaxSetupErrorShown) {
    window.ajaxSetupErrorShown=true;
    
    alert('AJAX error invoking '+ajaxSettings.url+
          '\nStatus=' + jqXHR.statusText+' ('+jqXHR.status+')'+
          (thrownError ? '\nError thrown='+thrownError : ''));
  }
});

jQuery.cachedScript = function(url, options) {
  if (console.log)
    console.log ("Dynamically loading Script "+url);

  // allow user to set any option except for dataType, cache, and url
  options = jQuery.extend(options || {}, {
    dataType : "script",
    cache : true,
    url : url
  });
  // Use $.ajax() since it is more flexible than $.getScript
  // Return the jqXHR object so we can chain callbacks
  return jQuery.ajax(options);
};

jQuery.scriptCache = [];

jQuery.cachedScriptWithJSCache = function(url, options, fctSuccess) {
  if (jQuery.inArray(url, jQuery.scriptCache) >= 0) {
    // No need to try again - already loaded
    if (false && console.log)
      console.log ("Script "+url+ " was already loaded - not loading again");
    fctSuccess();
  }
  else {
    jQuery.cachedScript (url, options).success(function () {
      fctSuccess();
      // Remember that it was already loaded
      jQuery.scriptCache.push(url);
    });
  }
};

// Escape a string to a regular expression
// See http://stackoverflow.com/questions/3446170/escape-string-for-use-in-javascript-regex
jQuery.escapeRegExp = function(str) {
  return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
}

function jqphClass(){}
jqphClass.prototype = {
  /**
   * Load the provided JS URLs in the correct order
   * @param jsUrls Array of JavaScript resource URLs
   * @param finalCallback Function to be executed after the last script was loaded
   */  
  loadJSInOrder:function(jsUrls,finalCallback){
    var index = 0;
    function _loadCurrent (){
      if (index < jsUrls.length) {
        jQuery.cachedScriptWithJSCache (jsUrls[index], null, function() { 
          // Recursive load next item
          index++;
          _loadCurrent ();
        });
      }
      else {
        if (finalCallback)
          finalCallback();
      }
    }
    // load first
    _loadCurrent();
  },
    
  /**
   * Default jQuery AJAX success handler for ph AJAX server side components
   * @param data PlainObject The data returned from the server, formatted according to the dataType parameter
   * @param textStatus String a string describing the status
   * @param xhr jqXHR the jqXHR (in jQuery 1.4.x, XMLHttpRequest) object
   * @param callbackFctStart function Callback to be included before the inclusions take place
   * @param callbackFctEnd function Callback to be executed after the inclusions took place
   */
  jqueryAjaxSuccessHandler:function(data,textStatus,xhr,callbackFctStart,callbackFctEnd){
    if(data.success){
      if (callbackFctStart) {
        // Invoke callback before the inclusions
        callbackFctStart(data.value,textStatus,xhr);
      }
      
      // Do we have inline JS before external?
      if (data.inlinejsBeforeExternal) {
        // eval now
        $.globalEval(data.inlinejsBeforeExternal);
      }
      
      // Do we have inline JS after external?
      var aInlineJSEval;
      if(data.inlinejsAfterExternal){
        // Include inline JS
        aInlineJSEval = function() { $.globalEval(data.inlinejsAfterExternal); }
      }
  
      if(data.externaljs){
        // Include external JS elements
        if (aInlineJSEval) {
          // external JS and inline JS is present
          this.loadJSInOrder (data.externaljs, aInlineJSEval);
        } else {
          // Only external JS present
          this.loadJSInOrder (data.externaljs);
        }  
      }
      else{
        // No external JS - Maybe inline JS?
        if (aInlineJSEval)
          aInlineJSEval();
      }
      
      var head=document.head || document.getElementsByTagName('head')[0];
      var createStyle = function(media,content) {
        var cssNode=document.createElement('style');
        cssNode.type='text\/css';
        cssNode.title='dynamicallyLoadedCSS';
        cssNode.media=media;
        if (cssNode.styleSheet)
          cssNode.styleSheet.cssText=content;
        else
          cssNode.appendChild(document.createTextNode(content));
        return cssNode;
      };
      
      // Inline CSS before externals?
      if(data.inlinecssBeforeExternal) {
        for(var css in data.inlinecssBeforeExternal){
          head.appendChild(createStyle (data.inlinecssBeforeExternal[css].media,
                                        data.inlinecssBeforeExternal[css].content));
        }
      }
      
      // Externa CSS elements present?
      if(data.externalcss){
        for(var css in data.externalcss){
          var cssNode=document.createElement('link');
          cssNode.href=data.externalcss[css].href;
          cssNode.type='text\/css';
          cssNode.rel='stylesheet';
          cssNode.title='dynamicallyLoadedCSS';
          cssNode.media=data.externalcss[css].media;
          head.appendChild(cssNode);
        }
      }

      // Inline CSS after externals?
      if(data.inlinecssAfterExternal) {
        for(var css in data.inlinecssAfterExternal){
          head.appendChild(createStyle (data.inlinecssAfterExternal[css].media,
                                        data.inlinecssAfterExternal[css].content));
        }
      }
      
      if (callbackFctEnd) {
        // Invoke callback after the inclusions
        callbackFctEnd(data.value,textStatus,xhr);
      }
    }else{
      var msg='Error invoking ph AJAX function!';
      if(data.errormessage){
        window.alert(msg+' '+data.errormessage);
      }else{
        window.alert(msg);
      }
    }
  }
};

var jqph = window.jqph = new jqphClass();
