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