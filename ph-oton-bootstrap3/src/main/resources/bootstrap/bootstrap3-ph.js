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
