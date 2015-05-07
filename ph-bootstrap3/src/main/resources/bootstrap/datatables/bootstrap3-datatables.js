/* Source: https://github.com/DataTables/Plugins/tree/master/integration/bootstrap/3 */
(function(){

/* Default class modification */
$.extend( $.fn.dataTableExt.oStdClasses, {
  "sWrapper": "dataTables_wrapper form-inline",
  "sFilterInput": "form-control input-sm",
  "sLengthSelect": "form-control input-sm",
  /* [ph] added button class */
  "sPageButton" : "btn"
} );

//In 1.10 we use the pagination renderers to draw the Bootstrap paging,
//rather than  custom plug-in
$.fn.dataTable.defaults.renderer = 'bootstrap';
$.fn.dataTable.ext.renderer.pageButton.bootstrap = function ( settings, host, idx, buttons, page, pages ) {
 var api = new $.fn.dataTable.Api( settings );
 var classes = settings.oClasses;
 var lang = settings.oLanguage.oPaginate;
 var btnDisplay, btnClass;

 var attach = function( container, buttons ) {
   var i, ien, node, button;
   var clickHandler = function ( e ) {
     e.preventDefault();
     if ( e.data.action !== 'ellipsis' ) {
       api.page( e.data.action ).draw( false );
     }
   };

   for ( i=0, ien=buttons.length ; i<ien ; i++ ) {
     button = buttons[i];

     if ( $.isArray( button ) ) {
       attach( container, button );
     }
     else {
       btnDisplay = '';
       btnClass = '';

       switch ( button ) {
         case 'ellipsis':
           btnDisplay = '&hellip;';
           btnClass = 'btn-default disabled';
           break;

         case 'first':
           btnDisplay = lang.sFirst;
           btnClass = button + ' btn-default' + (page > 0 ?
             '' : ' disabled');
           break;

         case 'previous':
           btnDisplay = lang.sPrevious;
           btnClass = button + ' btn-default' + (page > 0 ?
             '' : ' disabled');
           break;

         case 'next':
           btnDisplay = lang.sNext;
           btnClass = button + ' btn-default' + (page < pages-1 ?
             '' : ' disabled');
           break;

         case 'last':
           btnDisplay = lang.sLast;
           btnClass = button + ' btn-default' + (page < pages-1 ?
             '' : ' disabled');
           break;

         default:
           /** [ph] all buttons are btn-default except for the active one which is btn-primary */ 
           btnDisplay = button + 1;
           btnClass = page === button ?
             'btn-primary active' : 'btn-default';
           break;
       }

       if ( btnDisplay ) {
         /** [ph] using only a single <a> element for a button */
         node = $('<a>', {
             'href': '#',
             'class': classes.sPageButton+' '+btnClass,
             'aria-controls': settings.sTableId,
             'tabindex': settings.iTabIndex,
             'id': idx === 0 && typeof button === 'string' ?
               settings.sTableId +'_'+ button :
               null
           } )
           .html( btnDisplay )
           .appendTo( container );

         settings.oApi._fnBindAction(
           node, {action: button}, clickHandler
         );
       }
     }
   }
 };

 attach(
   /** ph added btn-group */
   $(host).empty().html('<div class="pagination btn-group"/>').children('div'),
   buttons
 );
}

})();