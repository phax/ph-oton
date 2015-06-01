tinymce.PluginManager.add('textmacro', function(editor) {
  
    editor.addButton('textmacro', {
        text: 'Macro',
        type: 'menubutton',
        icon: false,
        menu: [
				{
					text : 'Anrede',
					onclick : function() {
						tinymce.activeEditor.execCommand('mceInsertContent', false, "$greetingcomplete$ ");
					}
				},
				{
					text : 'Empfängername',
					onclick : function() {
						tinymce.activeEditor.execCommand('mceInsertContent', false, "$recipientname$ ");
					}
				},
				{
					text : 'Lieferdatum',
					onclick : function() {
						tinymce.activeEditor.execCommand('mceInsertContent', false, "$deliverydate$ ");
					}
				},
				{
					text : 'Rechnungsnummer',
					onclick : function() {
						tinymce.activeEditor.execCommand('mceInsertContent', false, "$invoicenumber$ ");
					}
				},
				{
					text : 'Senderadresse',
					onclick : function() {
						tinymce.activeEditor.execCommand('mceInsertContent', false, "$accountingareaaddress$ ");
					}
				},
				{
					text : 'Senderername',
					onclick : function() {
						tinymce.activeEditor.execCommand('mceInsertContent', false, "$sendername$ ");
					}
				},
				{	
					text : 'Typ',
					onclick : function() {
						tinymce.activeEditor.execCommand('mceInsertContent', false, "$objecttype$ ");
					}
				},
			]
    });
 });