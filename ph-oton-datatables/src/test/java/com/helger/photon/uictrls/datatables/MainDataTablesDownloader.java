/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables;

import com.helger.photon.uictrls.datatables.supplementary.tools.InternalDataTablesDownloader;

/**
 * Download the DT components from https://www.datatables.net/download/index via the String in the
 * CDN tab. No Minify, no concatenate
 *
 * @author Philip Helger
 */
public class MainDataTablesDownloader
{
  public static void main (final String [] args) throws Exception
  {
    // 4 runs:
    // 1. Run with default styling
    // 2. Run with Bootstrap4 styling
    // 3. Run with Bootstrap5 styling
    // 4. Run with Bootstrap3 styling (in other project)
    // Note: CardView and Editor are deliberately excluded - they are licensed under
    // "DataTables Plus" (https://datatables.net/license/plus) and must not be redistributed
    // Note: SearchPanes and StateRestore are not yet available for DataTables 3
    final String sHTML = """
        <link href="https://cdn.datatables.net/3.0.2/css/dataTables.dataTables.css" rel="stylesheet" integrity="sha384-2k9uLoQeYmuou+pC4erXygdeL+yT3d6Ila9GLHKP6ePBTHikB2cJUBB+WvpOcROP" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/autofill/3.0.0/css/autoFill.dataTables.css" rel="stylesheet" integrity="sha384-S6wL1KQzdzEc0rseRZxPJ+VtlpKtJOZF9fx8uca5jQMt9dQvC0HxnaHQM0i7BPEX" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/buttons/4.0.2/css/buttons.dataTables.css" rel="stylesheet" integrity="sha384-/67MqXUrb5M8lncOr8u166MmUUgiBQBtQ37D5R9oATTpliKaUM71iqrcYC1vsOZK" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/colreorder/3.0.1/css/colReorder.dataTables.css" rel="stylesheet" integrity="sha384-dapP3zwVBSCCdqYliPq+ecNJW6A0aNeUSP8XX6lsBTrbjN2VqUmieLDfc/pdOUEH" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/columncontrol/2.0.1/css/columnControl.dataTables.css" rel="stylesheet" integrity="sha384-uSx4PlSXWzknPtubbkQ/YvP1SMPpDGRv/2ckZ8vfSvgeOlITpk3wZBBw5TCOxV8j" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/datetime/2.0.0/css/dataTables.dateTime.css" rel="stylesheet" integrity="sha384-ovJQ7MLb7CitVJhff2vQW38SAN5gjjV1jMYWTnRoN++4abrn0zRY+jpJo+tUDro2" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/fixedcolumns/6.0.0/css/fixedColumns.dataTables.css" rel="stylesheet" integrity="sha384-9JG2lxU+TXX1UtaFAijl8leEKHGfBOjm1iWk0qyKXDpV1PzE0As2QU2md6Dxeq7C" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/fixedheader/5.0.0/css/fixedHeader.dataTables.css" rel="stylesheet" integrity="sha384-hl1xkYXIMEGo4j0ehGwE3Ker3n4+tWpbzNiCkI5WtCTxoqtKSlRy2LvmtJoht4+b" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/keytable/3.0.0/css/keyTable.dataTables.css" rel="stylesheet" integrity="sha384-Bnu1fncvP4aHmgLGq7Cfk4RgwgEp7ohxvg2Es8UjLN1bBs6kL8gLQhGC65+NNS8/" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/responsive/4.0.2/css/responsive.dataTables.css" rel="stylesheet" integrity="sha384-C/hzwYVbk4pjVesbsKRs963q0BzbAnBJcjq/8bgYS9gBHjBJI8Vm6ZmGjBLwOVP0" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/rowgroup/2.0.0/css/rowGroup.dataTables.css" rel="stylesheet" integrity="sha384-gfkuCH4n/d+vjc+aAGhDs9S18x3uHB0DT/6yGaxAeKd/HtmCqFMTWwlwOkOc444d" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/rowreorder/2.0.0/css/rowReorder.dataTables.css" rel="stylesheet" integrity="sha384-CjLw63HMbChw9Dg/sMnt9LAwYSEfpOF8byhKuvO3nCVlxalnNXB9DvtoE6bP0cNv" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/scroller/3.0.0/css/scroller.dataTables.css" rel="stylesheet" integrity="sha384-q54B7qs5k0Tnvc0vJ99jrTlaiKdmIadI36lrnKc4lNjgkB8PvI9m3g/v3tPUjBKL" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/searchbuilder/2.0.0/css/searchBuilder.dataTables.css" rel="stylesheet" integrity="sha384-i17sKA6S8+uJJ8r68bsHujhGWbtp5UETfenKVbYF0mAi16atJ+y0sNGw+rbSbBZJ" crossorigin="anonymous">
        <link href="https://cdn.datatables.net/select/4.0.1/css/select.dataTables.css" rel="stylesheet" integrity="sha384-bJVtQsZyn27HEu9GjU7PW6xZqbNKzeeVAg6BufQwRGQt7CjP3yb3udpROQDtg/fo" crossorigin="anonymous">

        <script src="https://cdn.datatables.net/3.0.2/js/dataTables.js" integrity="sha384-lsU76fZ+EB6UAyoxGDD0WdpRP0TfShxzxk3Lv9AxuE3MaKBJDhMdvWSAqjC4Jf/B" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/autofill/3.0.0/js/dataTables.autoFill.js" integrity="sha384-W9nmhSQpbAGCZh+q4+anvCzLtWx7Rr3jp7mQ7VPNFi5FLgZnAg9dhSjh/TKBr1a1" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/buttons/4.0.2/js/dataTables.buttons.js" integrity="sha384-QTDlNPglBt+bb83EFuSkxpK3aCqgr1KJnLxdqurlScxvcUCSp9aJ+XG9oUzXsHVV" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/colreorder/3.0.1/js/dataTables.colReorder.js" integrity="sha384-NFfobpBTHl5V+F5AxFKL8V2WJd+FgIcE4BKz/ZRGcWKkgXu3Gr9WfBtClWVFOk8Q" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/columncontrol/2.0.1/js/dataTables.columnControl.js" integrity="sha384-rpZIuPeuUylv8K6YhrdqOb6k/KPP9wBgRuEHpiJvDFuUZS/nEAxMSuSEY82NhXrw" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/datetime/2.0.0/js/dataTables.dateTime.js" integrity="sha384-sgHb+X2CACTjehGuZbXjyMuO5JeXm7EWGpeB4P7LoeDe0oOB9iZFebaABeEeJN7g" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/fixedcolumns/6.0.0/js/dataTables.fixedColumns.js" integrity="sha384-ez8P5gnxifckFwdUHWnqPlSexbzvDb2s+5X4vsj/XvI43Gmo+QKfSuBsxHbVLGgI" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/fixedheader/5.0.0/js/dataTables.fixedHeader.js" integrity="sha384-o5HpbqY1mFMmrvY94sGNe7yVwJZAjyIe6bmj9/ZmY0ItOyDPhzN5y+JMkxJLikPf" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/keytable/3.0.0/js/dataTables.keyTable.js" integrity="sha384-LyOzENI3sCJ00KREQVHFtnMP4mVWQ0DOEugwLenRVtStVs246cDNoqIE6xEeO2hV" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/responsive/4.0.2/js/dataTables.responsive.js" integrity="sha384-A26BXbYqz6JYIbdD0q8m6VvWwWfzUt32qk+HznD59kqcd+tJGWSDtP+aMs8esvoM" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/rowgroup/2.0.0/js/dataTables.rowGroup.js" integrity="sha384-zMOXH4nZoW9ih1j9KbH9YOzOf/1tXHpombyIkE4AIH1G7hDnuD5KTTTNXyohJMYb" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/rowreorder/2.0.0/js/dataTables.rowReorder.js" integrity="sha384-BQAfkuRuj1wwEX1OHLnhOT2WhHJ/QUGiqIijcBILpIDg4Xwbrfl7K4CSbK4/OMm0" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/scroller/3.0.0/js/dataTables.scroller.js" integrity="sha384-oE01NMYfe1gxK0CgSc76OjlpswY9jLjpbnmiOhntm0BIRiHGU/pCQBqv6O6tg1fP" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/searchbuilder/2.0.0/js/dataTables.searchBuilder.js" integrity="sha384-FDywYvEH8FfH3i8xwg2txqES/4eQVhVX3Td/m6lq8Z0VQR/CIB5OR74ckkop3X65" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/select/4.0.1/js/dataTables.select.js" integrity="sha384-D3GTlyXVuDOpLwE6EnTrwueMlfApLnx+YULxqITbVoAx/CGmsuKwX40VAo/g3jrM" crossorigin="anonymous"></script>
                """;

    InternalDataTablesDownloader.downloadDataTables (sHTML, null);
  }
}
