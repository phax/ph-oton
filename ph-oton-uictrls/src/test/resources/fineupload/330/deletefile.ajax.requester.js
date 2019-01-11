/*
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
/** Generic class for sending non-upload ajax requests and handling the associated responses **/
/*globals qq, XMLHttpRequest*/
qq.DeleteFileAjaxRequestor = function(o) {
    "use strict";

    var requestor,
        options = {
            endpointStore: {},
            maxConnections: 3,
            customHeaders: {},
            paramsStore: {},
            demoMode: false,
            cors: {
                expected: false,
                sendCredentials: false
            },
            log: function(str, level) {},
            onDelete: function(id) {},
            onDeleteComplete: function(id, xhr, isError) {}
        };

    qq.extend(options, o);

    requestor = new qq.AjaxRequestor({
        method: 'DELETE',
        endpointStore: options.endpointStore,
        paramsStore: options.paramsStore,
        maxConnections: options.maxConnections,
        customHeaders: options.customHeaders,
        successfulResponseCodes: [200, 202, 204],
        demoMode: options.demoMode,
        log: options.log,
        onSend: options.onDelete,
        onComplete: options.onDeleteComplete
    });


    return {
        sendDelete: function(id, uuid) {
            requestor.send(id, uuid);
            options.log("Submitted delete file request for " + id);
        }
    };
};
