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
function FormHelperClass(){
}
FormHelperClass.prototype = 
{
  patternProp : /\(\)$/,
  patternArray : /\[\]$/,
  
  /**
   * Get all form values
   * @param formid ID of the form to save the value - String
   * @param fieldPrefix the prefix to be appended to all array keys - String
   * @return a map where the key is prefix combined with the field name and the value is the field value 
   */
  getAllFormValues : function(formid,fieldPrefix) {
    var vals={};
    var elems=$('#'+formid).find('*');
    // Checkbox and array need "prop"
    elems.filter ('input[type="checkbox"], input[type="radio"]').each(
      function(){ vals[fieldPrefix+this.name+'()']=$(this).prop('checked'); }
    );
    // Value is an array
    elems.filter ('select[multiple="multiple"]').each(
      function(){ vals[fieldPrefix+this.name+'[]']=$(this).val(); }
    );
    // Regular string value
    elems.filter ('input[type="text"], input[type="hidden"], select[multiple!="multiple"], textarea').each(
      function(){ vals[fieldPrefix+this.name]=$(this).val(); }
    );
    // var i = 0; for (var x in vals) console.log(++i+" - "+x+"="+vals[x]);
    return vals;
  },

  setAllFormValues : function(formid,vals) {
    var val;
    for (var name in vals) {
      val = vals[name];
      if (this.patternProp.test (name))
        $('#'+formid+' [name="'+name.substring(0,name.length-2)+'"]').prop('checked', val);
      else
        if (this.patternArray.test (name))
          $('#'+formid+' [name="'+name.substring(0,name.length-2)+'"]').val(val);
        else
          $('#'+formid+' [name="'+name+'"]').val(val);
    }
  },
 
  // string,string
  updateElementDirect : function(updateFieldId,html){
    $('#'+updateFieldId).empty().append(html);
  },
  
  // string,string
  updateElementViaAjax : function(updateFieldId,ajaxUrl){
    // Update list of all store values
    $.ajax({
      url:ajaxUrl,
      success:function(data){
        if (data.value) 
          FormHelper.updateElementDirect (updateFieldId,data.value.html);
      }
    });
  },
  
  // array[map{id,url|html}]
  updateElements : function(updates) {
    for (var i in updates) {
      var update = updates[i];
      if (update.url)
        FormHelper.updateElementViaAjax(update.id,update.url);
      else
        FormHelper.updateElementDirect(update.id,update.html);
    }
  },
  
  // string,string,string,string,array[map{id,url|html}],array[map{id,url|html}]
  saveFormData : function(formid,fieldPrefix,pageID,ajaxUrl,successUpdates,errorUpdates) {
    var vals=FormHelper.getAllFormValues(formid,fieldPrefix);
    vals.$pageID=pageID;
    $.ajax({
      url:ajaxUrl,
      data:vals,
      success:function(data){
        FormHelper.updateElements(successUpdates);
      },
      error:function(data){
        FormHelper.updateElements(errorUpdates);
      }
    });
  },
  
  // jQuery-select-obj, array(array(value,text))
  // 2nd param changed in ph-webctrls 1.3.4 so that order is maintained
  //   was previously map<value,text>   
  setSelectOptions : function ($select,newOptions) {
    $select.empty(); // remove old options
    $.each(newOptions, function(index,obj) {
      $select.append($("<option></option>").attr("value",obj[0]).text(obj[1]));
    });    
  }
};

var FormHelper = window.FormHelper = new FormHelperClass();
