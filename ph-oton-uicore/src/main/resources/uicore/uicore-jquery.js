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
  // allow user to set any option except for dataType, cache, and url
  options = $.extend(options || {}, {
    dataType : "script",
    cache : true,
    url : url
  });
  // Use $.ajax() since it is more flexible than $.getScript
  // Return the jqXHR object so we can chain callbacks
  return jQuery.ajax(options);
};

// Escape a string to a regular expression
// See http://stackoverflow.com/questions/3446170/escape-string-for-use-in-javascript-regex
jQuery.escapeRegExp = function(str) {
  return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
}

function jqphClass(){}
jqphClass.prototype = {
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
          // => synchronize them so that the inline JS is only evaluated after 
          //    all external JS are loaded
          var left = data.externaljs.length;
          for(var js in data.externaljs){
            $.cachedScript (data.externaljs[js]).always(function() { 
              // One item less
              left--; 
            });
          }
          
          // synchronize via setTimeout (timeout = 100*50ms = 5secs)
          var timeout=100;
          var poll = function(){
            setTimeout(function (){
              if (left > 0) {
                // Still files left to load
                if (--timeout > 0) poll();
              }
              else {
                // All files loaded - eval inline JS
                aInlineJSEval();
              }  
            }, 50); 
          };
          poll ();
        } else {
          // Only external JS present
          // ==> no need to synchronize
          for(var js in data.externaljs){
            $.cachedScript (data.externaljs[js]);
          }
        }  
      }
      else{
        // No external JS - Maybe inline JS?
        if (aInlineJSEval)
          aInlineJSEval();
      }
      
      if(data.externalcss){
        // Include external CSS elements
        var firstcss=document.getElementsByTagName('link')[0];
        for(var css in data.externalcss){
          var cssNode=document.createElement('link');
          cssNode.href=data.externalcss[css];
          cssNode.type='text\/css';
          cssNode.rel='stylesheet';
          cssNode.title='dynamicallyLoadedCSS';
          firstcss.parentNode.insertBefore(cssNode,firstcss);
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
