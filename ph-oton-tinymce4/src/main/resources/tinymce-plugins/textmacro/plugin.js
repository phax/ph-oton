/*
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
tinymce.PluginManager.add('textmacro', function(editor) {
	var settings = editor.settings;
	var macro_title = settings.macro_title || 'Macro';
	var macro_patterns = settings.macro_patterns || {};
	
	var entries = [];
	for(var key in macro_patterns)
	{
		var createOnClick = function(txt){
			return function(){
			  tinymce.activeEditor.execCommand('mceInsertContent', false, txt);
			};
		};
		entries.push({
			text : key,
			onclick : createOnClick(macro_patterns[key])
		});
	}
  
    editor.addButton('textmacro', {
        text: macro_title,
        type: 'menubutton',
        icon: false,
        menu: entries,
    });
 });