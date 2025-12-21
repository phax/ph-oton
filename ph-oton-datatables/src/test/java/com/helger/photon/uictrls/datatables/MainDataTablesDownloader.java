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
    final String sHTML = "<link href=\"https://cdn.datatables.net/2.3.5/css/dataTables.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-Mw5guIRycD83x5DtxfWszikU1vklt1UrSxQqUQUm4fsj8LtCk2Sb0mSD8kEpmf7U\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/autofill/2.7.1/css/autoFill.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-E6sscKUdgP7JGCEj86QHu7cOS5MObIXILQbbjaJhmk/OCtH71GIJwE7fbl2TWcWf\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/buttons/3.2.5/css/buttons.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-tsSOpW74BV1og7WBDDvGDHNfy+fzoOsmcdjpy79NlzdeffYarxQKgsOK2HK/9jeF\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/colreorder/2.1.2/css/colReorder.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-dapP3zwVBSCCdqYliPq+ecNJW6A0aNeUSP8XX6lsBTrbjN2VqUmieLDfc/pdOUEH\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/columncontrol/1.1.1/css/columnControl.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-Hg75KLicDOD6Nd34641dZmth7FvpyUsHtfTslTIQFOCJjnuLkvfA9KitKXUMtIN6\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/datetime/1.6.2/css/dataTables.dateTime.css\" rel=\"stylesheet\" integrity=\"sha384-7gVhgCBvVkFm8CjELsmoaaDJyWITKmo5b7imOimmD95IVzEyhS92YJ3jlcHfGc1l\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/fixedcolumns/5.0.5/css/fixedColumns.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-9JG2lxU+TXX1UtaFAijl8leEKHGfBOjm1iWk0qyKXDpV1PzE0As2QU2md6Dxeq7C\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/fixedheader/4.0.5/css/fixedHeader.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-uu6ZKQqRyOP/dGz/g6GnXcldGomtbvPHeUok89+s3zw0whjxW5ImJPs7K79p2ZUQ\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/keytable/2.12.2/css/keyTable.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-eiWC8/mQmnSUmdWa7GsvDZRdPJDHupN6RtRQgNW0kSawSdByNJAJ7vgoNNUxhHHH\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/responsive/3.0.7/css/responsive.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-inDoREwjvy5La3vgUWcIczhlGcfXAt1V1DsIU/yHRTbfL3W/HfMnReyF4xwS7S+x\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/rowgroup/1.6.0/css/rowGroup.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-bei2bpb7orBDGkDdrxxnvqdYpRnpXwKe5JxL3It/9i0qLCepqOWVYsKn0NSb47um\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/rowreorder/1.5.0/css/rowReorder.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-spiALtRus8lUtPCGbY0iEcVB9CVUlXdqhVcS/dTW1liXFAHtgIOO9BO8l3Nv+tQl\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/scroller/2.4.3/css/scroller.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-fvFMooh85/CFhRcmgNLO/DEXj4/h8h4Fz2s0Wtq2hPU/s7z0rLzrk77ID2JS+YUg\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/searchbuilder/1.8.4/css/searchBuilder.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-/48kFoHqarATJ1hzWnG7DP62ekWPicvpQbO5o0Q4DEYBvZ5+U4kq5yq/sJflgCJe\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/searchpanes/2.3.5/css/searchPanes.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-NvPvD7DuPwP9OE4kOjOVC84X3UXKoY0rfcpo474d34wG/ejp5EM+dmmQlc9yfewr\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/select/3.1.3/css/select.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-Kcn9jd7zuLVw0Z2CqnwrvI6ITtpMOAc+ah3b+te7G7vey5N+u5XHP7FWtCHE8Pce\" crossorigin=\"anonymous\">\n" +
                         "<link href=\"https://cdn.datatables.net/staterestore/1.4.3/css/stateRestore.dataTables.css\" rel=\"stylesheet\" integrity=\"sha384-TYurINZPMCWGH4wjMLEji4RKP3Re/q52J5Nx/Eh/A022XwjTmjaeoSBH25ipUB4j\" crossorigin=\"anonymous\">\n" +
                         " \n" +
                         "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.js\" integrity=\"sha384-w52cgKJL63XVo8/Wwyl+z8ly0lI51gzCtqADl8pHQTXUXkF08iRa7D+sjSmCyHp+\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/2.3.5/js/dataTables.js\" integrity=\"sha384-/JORYWDk6F/SvOM/lLxtVVRQanR9mF8OmN4mBPwpeicZGC/jGSlDTNKMNDeHWnVM\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/autofill/2.7.1/js/dataTables.autoFill.js\" integrity=\"sha384-oagHw1yugW9updeZwMFO6bmQnishdCEl7gWAcexUy7CnddsFvRIUalIuo+9WZsqJ\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/dataTables.buttons.js\" integrity=\"sha384-SyOBgiETMg1CpC29RFM/2e0S+rlVmbftfSjVTeG22H8ihiIwugfxQTWJ0dkEuqc7\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/buttons.colVis.js\" integrity=\"sha384-t/MXDvQw7etUQsFfET7E2PfnVZerTgIVxF5shlbaT7U2998GEQvYqgKJV8k7RJoa\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/buttons.html5.js\" integrity=\"sha384-FWBlOAPJ9FRQJgtP00zQNtd1wuox/BRlVTkHuOojgy2TQl+opJzVx4GhRlbaPLhB\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/buttons/3.2.5/js/buttons.print.js\" integrity=\"sha384-LbFUMCazrGiuf4Z4oi2HrQJ8m+YVatlOZ7fl/yPPMlcHX+ya9tg6M4IBwDxcbMjS\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/colreorder/2.1.2/js/dataTables.colReorder.js\" integrity=\"sha384-DB2bQzIbPrSZbyTcjM6QD9K5REU5hbGMthqbeZ4sygWhX5taehWrXI0CE0ggYrb+\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/columncontrol/1.1.1/js/dataTables.columnControl.js\" integrity=\"sha384-97/ppI0+GoqPyCT65fhAeT6tIUSo5qck6cEB8EO6msUmG7MT+/JRYwT0elqylnok\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/datetime/1.6.2/js/dataTables.dateTime.js\" integrity=\"sha384-I0rL1eK8/4jZ1njbsVwTibN/q+IvWmYk6aLQ9TvNAUUbFNps5B5fh1Z2/H5uWERY\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/fixedcolumns/5.0.5/js/dataTables.fixedColumns.js\" integrity=\"sha384-BPFbg0oSBJMoGXcby9UBo5gWRKTu8EHqGyw2Z3ZtrNOPA0pbmjHHXP9e/zOswmse\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/fixedheader/4.0.5/js/dataTables.fixedHeader.js\" integrity=\"sha384-l5NXooq6ADHTtMI23CogAsiDHPnNwQqMoxWLln3SQvurWyJ4475GmZKSPgcXc10Z\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/keytable/2.12.2/js/dataTables.keyTable.js\" integrity=\"sha384-hBecIgrOqCbd2ZIoop7DCCsMK0XW1ERfcMEFMk9vyLcJOqeNlir+XGSe0pUL3JFk\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/responsive/3.0.7/js/dataTables.responsive.js\" integrity=\"sha384-vVealWQwusW7xpCxT5a1xFzPAmskqmgoxBm8X4rB/ssViRDEeY1YuPdIjz04vjbY\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/rowgroup/1.6.0/js/dataTables.rowGroup.js\" integrity=\"sha384-pz3bm7o3k3nRZJhZexCAjScDjiXV1rh+XvASOWwpP54Iw1cpnf5OMhQVgA20HGPC\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/rowreorder/1.5.0/js/dataTables.rowReorder.js\" integrity=\"sha384-mBc+RASAA7jtvOQzw1AxLDYuzQYn+M6d5MjAmjCSWctHnS4xHJr9BpM71rRNh2A2\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/scroller/2.4.3/js/dataTables.scroller.js\" integrity=\"sha384-cCDhK6VsxVGKfl0shwjJr2UXaCzEpxhSnd7C8Uan8yABW71pdY3iaz8aVBklw8uz\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/searchbuilder/1.8.4/js/dataTables.searchBuilder.js\" integrity=\"sha384-uQOvV9BmCAyJAeGboLgXTBfcKh34+IkCHep3jQw5mpQBLXXvPL4740U3N5PRHiex\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/searchpanes/2.3.5/js/dataTables.searchPanes.js\" integrity=\"sha384-QV/xsakwxg24XxzPb6XOZwQ4Kax9VmGBjuiBx+czKmRnevneDTmqTHwdujbn4awh\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/select/3.1.3/js/dataTables.select.js\" integrity=\"sha384-q/VS6T+Q4YoTiJWPVvhHcAUBZZjBXnjt+5VwF+mDgtQ5qHEM4ufO1oCpiVYQ0D+W\" crossorigin=\"anonymous\"></script>\n" +
                         "<script src=\"https://cdn.datatables.net/staterestore/1.4.3/js/dataTables.stateRestore.js\" integrity=\"sha384-2uepfLnXHOdbkfdv+IT8L8AffDZ/WsWIJwLZBatC8VeGqELBDtjGpiBqKnSefvG/\" crossorigin=\"anonymous\"></script>";

    InternalDataTablesDownloader.downloadDataTables (sHTML, null);
  }
}
