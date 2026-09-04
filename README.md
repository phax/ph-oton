# ph-oton

<!-- ph-badge-start -->
[![Sonatype Central](https://maven-badges.sml.io/sonatype-central/com.helger.photon/ph-oton-parent-pom/badge.svg)](https://maven-badges.sml.io/sonatype-central/com.helger.photon/ph-oton-parent-pom/)
[![javadoc](https://javadoc.io/badge2/com.helger.photon/ph-oton-html/javadoc.svg)](https://javadoc.io/doc/com.helger.photon/ph-oton-html)

> If this project saved you some time or made your day a little easier, a star would mean a lot — it helps others find it too.
<!-- ph-badge-end -->

This set of Java libraries forms a package to build Java web applications.

Contained subprojects are:
* ph-oton-html - Java wrapper for all HTML elements and attributes
* ph-oton-markdown - Java wrapper for Markdown processing (since 10.2.0; previously in ph-oton-html)
* ph-oton-jscode - a Java code model to build structured JS code
* ph-oton-jquery - an extension to ph-html-jscode to also support jQuery
* ph-oton-atom - ATOM newsfeed stuff
* ph-oton-io - basic IO stuff (since 9.2.0; previously in ph-oton-app)
* ph-oton-app - basic application stuff
* ph-oton-audit - basic auditing stuff
* ph-oton-ajax - basic AJAX stuff
* ph-oton-api - basic API stuff
* ph-oton-security - security elements (user, user groups, roles etc.)
* ph-oton-exchange - data exchange (import and export)
* ph-oton-connect - connectivity modules
* ph-oton-mgrs - basic managers (since 10.2.0; previously in ph-oton-core)
* ph-oton-core - basic web stuff
* ph-oton-uicore - basic web UI stuff
* ph-oton-icon - icon library. **Deprecated for removal** - the icon libraries moved to the separate project https://github.com/phax/ph-oton-icon (Maven group `com.helger.photon.icon`)
* ph-oton-tinymce4 - TinyMCE4 wrapper
* ph-oton-datatables- Datatables.net wrapper
* ph-oton-uictrls - misc web UI controls
* ph-oton-jetty - wrapper for Jetty to simply use as main
* ph-oton-jdbc - provides certain JDBC-based manager implementations (since 8.4.2)

# Requirements

* Java 17+ is required for building 
* Application server requirements:
    * At least Tomcat 10.1.x (JakartaEE 10)
    * Jetty 12.x with AnnotationConfiguration enabled
    
Note: actual frontend bindings were moved to separate projects:
* Bootstrap v3: https://github.com/phax/ph-oton-bootstrap3
* Bootstrap v4: https://github.com/phax/ph-oton-bootstrap4
* Bootstrap v5: https://github.com/phax/ph-oton-bootstrap5 (work in progress)

## Maven usage

Replace `x.y.z` with the effective version number.

```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>com.helger.photon</groupId>
        <artifactId>ph-oton-parent-pom</artifactId>
        <version>x.y.z</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```

Note: prior to v8.2.5 the Maven groupId was `com.helger`.

## News and noteworthy

v10.5.0 - work in progress
* Added the new method `ISystemMessageManager.getSystemMessageData ()` that returns all system message fields at once.
  It is now the only method an implementation needs to provide, and it allows a backend to read everything in a single step - the JDBC implementation previously issued one `SELECT` per field.
* Deprecated the methods `ISystemMessageManager.getLastUpdateDT ()`, `getMessageType ()`, `getSystemMessage ()` and `hasSystemMessage ()` for removal.
  They are now `default` methods that delegate to `getSystemMessageData ()`.
* Added the copy constructor `SystemMessageData (ISystemMessageData)` and the method `SystemMessageData.getClone ()` - the class now implements `ICloneable`.
* **Security fix**: `IUserTokenManager.getUserTokenOfTokenString (String)` now only considers access tokens that are valid now (`IAccessToken.isValidNow ()`) and that are not revoked.
  Previously an expired access token was still resolved.
* **Security fix**: `UserTokenAuthCredentialValidatorSPI` now also checks the state of the user owning the token and returns `ELoginResult.USER_IS_DELETED` or `ELoginResult.USER_IS_DISABLED` accordingly.
  Previously a token of a deleted or disabled user was still valid.
* **Security fix**: a user that is deleted, disabled or whose password is changed, is now logged out immediately.
  Previously he stayed logged in until his session timed out.
  This is implemented in the new class `UserModificationLogoutCallback` that is registered by default in `PhotonSecurityManager`.
  The internal password hash algorithm upgrade performed during login does not trigger a logout.
* **Security fix**: when a session containing a logged in user is activated (e.g. after an application server restart with session persistence), the user state is now checked again.
  A user that was deleted or disabled in the meantime, or that is already logged in elsewhere, is no longer logged in again.
* **Security fix**: `LoggedInUserManager.loginUser (...)` now checks whether the current session already has a user *before* an existing login of the user to be logged in is terminated.
  Previously a login failing with `ELoginResult.SESSION_ALREADY_HAS_USER` could log out that user's other session as a side effect.
* **Security fix**: `LoggedInUserManager.loginUser (...)` now spends the same amount of time on password hashing if no user could be resolved, so that the response time no longer discloses whether a login name exists.
  A failed login without a resolvable user is now also audited.
* Added the new method `LoggedInUserManager.getInstanceIfInstantiated ()`.
* `GlobalUserIDProvider.DEFAULT_SUPPLIER` no longer throws an exception if no global scope is present.
* `PhotonSecurityManager.FactoryXML.createAuditMgr ()`, `AuditManagerJDBC` and `ObjectLockManager` now use `GlobalUserIDProvider` instead of `LoggedInUserManager` directly, so that alternative ways of authentication are correctly attributed.
* `LoggedInUserManager` no longer executes the `IUserLogoutCallback`s while holding the internal write lock during a login.
* The mutable fields of `LoginInfo` are now `volatile`, because a `LoginInfo` is shared between all requests of a session.
* **Breaking API change**: moved the class `LoginThrottlePerIP` from package `com.helger.photon.core.login` (ph-oton-core) to `com.helger.photon.security.login` (ph-oton-security), so that it can be reused by non-UI authentication.
* `AuditorJDBC` now extends `AbstractAuditor` instead of implementing `IAuditor` directly, so that the contained `ICurrentUserIDProvider` and the `IAuditActionStringProvider` can be changed after instantiation, as for all other auditors.
  The method `AuditorJDBC.createAuditItem (...)` was therefore replaced by `handleAuditItem (IAuditItem)`.
* Added the new class `RequestUserIDProvider` (ph-oton-security) that remembers the ID of a user that was authenticated for the current request only - e.g. a REST API call using HTTP Basic Auth or a Bearer token - without creating an HTTP session.
* `GlobalUserIDProvider.DEFAULT_SUPPLIER` now prefers the user ID of the `RequestUserIDProvider` over the session user ID of the `LoggedInUserManager`.
  Therefore stateless authenticated requests are now correctly attributed in the audit log and in the created business objects, without any additional configuration.
* `IUser.USER_ID_MAX_LENGTH` is `GlobalIDFactory.STRING_ID_MAX_LENGTH` (45) instead of 20 now, so that a user ID created by the default ID factory is no longer trimmed.
  Added the new Flyway script `V3__user_id_max_length.sql` in `docs/flyway/<dialect>/` for that.
  It widens every column holding a user ID to 45 characters - `audit.userid`, the `creationuserid`, `lastmoduserid` and `deleteuserid` of `secrole`, `secusergroup`, `secuser` and `secusertoken`, as well as `secuser.id` and `secusertoken.userid`, which were 20 characters and would overflow otherwise.
  `long_running_job.id` stays at 40 characters, because it holds a persistent ID from `GlobalIDFactory` and not a user ID. Only the length of the columns is changed, so existing data is preserved.
  See `docs/README.md` for details.

v10.4.0 - 2026-08-30
* Requires at least ph-commons 12.4.0
* Updated to DataTables 3.0.2 + current plugins: AutoFill 3.0.0, Buttons 4.0.2, ColReorder 3.0.1, ColumnControl 2.0.1, DateTime 2.0.0, FixedColumns 6.0.0, FixedHeader 5.0.0, KeyTable 3.0.0, Responsive 4.0.2, RowGroup 2.0.0, RowReorder 2.0.0, Scroller 3.0.0, SearchBuilder 2.0.0 and Select 4.0.1.
  Note that DataTables 3 no longer requires jQuery.
  **Breaking API change**: removed the enum entries `EDataTablesJSPathProvider.DATATABLES_BUTTONS_COLVIS`, `DATATABLES_BUTTONS_HTML5` and `DATATABLES_BUTTONS_PRINT`. Buttons 4 merged `buttons.colVis.js`, `buttons.html5.js` and `buttons.print.js` into `dataTables.buttons.js`, so the separate files no longer exist - registering `EDataTablesJSPathProvider.DATATABLES_BUTTONS` is now sufficient for all button types.
  **Breaking API change**: removed the enum entries `EDTPButtonsButtonType.COPY_FLASH`, `CSV_FLASH`, `EXCEL_FLASH` and `PDF_FLASH`. The Flash based buttons were dropped in Buttons 2 already and resolved to nothing at runtime.
  **Breaking API change**: removed the constants `CDataTablesComponentVersion.SEARCH_PANES` and `STATE_RESTORE` incl. the respective resources. SearchPanes and StateRestore are not yet ported to DataTables 3 and their DataTables 2 versions are incompatible with it.
  Added the new enum entries `DATATABLES_COLUMN_CONTROL`, `DATATABLES_DATE_TIME` and `DATATABLES_SEARCH_BUILDER` to `EDataTablesCSSPathProvider` and `EDataTablesJSPathProvider`. The resources were shipped before, but were not reachable from Java.
  Added the new enum entries `DATATABLES_COLUMN_CONTROL_BOOTSTRAP4` and `DATATABLES_SEARCH_BUILDER_BOOTSTRAP4` to `EDataTablesB4CSSPathProvider`.
  Added entries for ColReorder, ColumnControl, FixedColumns, FixedHeader, KeyTable, RowGroup, RowReorder, Scroller, SearchBuilder and Select to `EDataTablesB4JSPathProvider` - DataTables 3 ships Bootstrap integration JS for these extensions for the first time, previously only the CSS existed.
  The identical entries were added to the two Bootstrap 5 counterparts.
  Note: the CardView and Editor extensions that the DataTables 3 download builder offers are deliberately not included, because they are licensed under "DataTables Plus" and must not be redistributed.
  Fixed the SearchHighlight plugin (`DataTablesPluginSearchHighlight`), that stopped working with DataTables 3. It read the initialisation options of a table from `settings.oInit`, which DataTables 3 renamed to `settings.init` - the resulting `TypeError` aborted the `init.dt` handler, so no search term was ever highlighted. The bundled `dataTables.searchHighlight.js` now reads both and additionally ignores foreign "init" events that bubble up to the document.
  The bundled SearchHighlight plugin was updated from v1.0.1 to v1.1.0, which highlights the search term of a single column as well - in its own colour, using the new CSS class `column_highlight` in `dataTables.searchHighlight.css`. The global search term keeps using `highlight`.
  **Breaking API change**: removed `DataTables.isJQueryUI ()`, `setJQueryUI (boolean)` and the constant `DataTables.DEFAULT_JQUERY_UI`. The `jQueryUI` initialisation option was dropped in DataTables 2 already, so the emitted option had no effect any more.
* Reworked the CSP violation reporting in `CSPReportingXServletHandler` and added the new package `com.helger.photon.core.csp`.
  The handler now also accepts the Reporting API format (`application/reports+json`) next to the legacy format (`application/csp-report`) and normalizes both into the new class `CSPReport`, which additionally carries the User-Agent, the remote address, the receipt date time and the query parameters of the report URI. The latter are the only way to add server side context to a report, because the report body itself is created by the browser from a fixed set of fields.
  Added `ICSPReportClassifier` to separate actionable reports from the browser internal and browser extension noise that reaches every public reporting endpoint. The default implementation classifies a report as noise if its "source-file" has a scheme other than `http` or `https`; an absent "source-file" is deliberately not treated as a noise signal. The classification is part of `CSPReport` and can be replaced per application via `CSPReportingXServletHandler.setClassifier (...)`. Noise is logged on the info level instead of the warning level.
  Added `CSPReportingEndpoint` that ties the name of the CSP `report-to` directive to the URI of the `Reporting-Endpoints` HTTP response header and of the legacy `report-uri` directive, so that the three cannot drift apart, and `ICSPReportingParameterProvider` for an application to contribute the query parameters of the report URI (default: none).
  **Incompatible change**: the `CSPReportingXServletHandler` constructor now takes a `Consumer <? super CSPReport>` instead of a `Consumer <? super IJsonObject>`. Use `CSPReportingXServletHandler.asJsonConsumer (...)` to keep an existing JSON based consumer.
  **Incompatible change**: a request with a content type other than `application/csp-report` or `application/reports+json` is now answered with HTTP 415 instead of being parsed as JSON.
  **Behaviour change**: the legacy `csp-report` envelope is now unwrapped, so a consumer receives the violation fields themselves. As a side effect the duplicate filtering by "blocked-uri" works for the first time - it read the field off the envelope before and therefore never matched.
* Added the new class `SingleRunLock` in package `com.helger.photon.security.lock`. It is a process wide "only one at a time" lock for expensive activities like long running jobs, that additionally remembers since when and by whom the running activity was started, so that a rejected caller can be told what is going on.
* Added the new method `ILongRunningJobResultManager.deleteResult (String)` incl. the implementations in `LongRunningJobResultManager` (XML backend) and `LongRunningJobResultManagerJDBC` (SQL backend).
  Previously long running job results could only be added and read, but never be removed - for results of type `FILE` that meant, that the referenced files piled up without any way to clean them up from the application.
  Note: deleting a job result does not delete a possibly referenced result file on disk.
  Custom implementations of `ILongRunningJobResultManager` need to implement the new method.
* Integrated [ph-telemetry](https://github.com/phax/ph-telemetry) 1.0.1 into `LongRunningJobManager` - submodule `ph-oton-mgrs` now depends on `com.helger.telemetry:ph-telemetry`.
  Every long running job execution is now covered by the span `photon.longrunningjob.execute` (started in `onStartJob`, closed in `onEndJob`), and the instruments `photon.longrunningjob.started`, `photon.longrunningjob.ended`, `photon.longrunningjob.running` and `photon.longrunningjob.duration` are emitted.
  Emission goes through the vendor neutral ph-telemetry facades only - without a registered `ITelemetryTracerSPI` / `ITelemetryMeterSPI` everything degrades to cheap no-ops, so no observability backend is required.
* Added the new classes `CLongRunningJobTelemetry` (the constant span, metric and attribute names) and `LongRunningJobMetrics` (the metric instruments), so that applications can reference the literally same names in dashboards, alerting rules and tests
* **Breaking API change**: removed the method `ILongRunningJob.getJobID ()`. A single job execution is identified by the globally unique ID that `LongRunningJobManager.onStartJob` creates and that is available as `LongRunningJobData.getID ()`, so a separately provided job ID was redundant.
  `LongRunningJobManager.onStartJob` now uses `GlobalIDFactory.getNewPersistentStringID ()` instead of `getNewStringID ()` - applications therefore need a registered persistent ID factory.
* Added the new method `ILongRunningJob.getJobType ()` together with the constant `ILongRunningJob.JOB_TYPE_MAX_LENGTH` (100). The job type is a coarse grained category that groups multiple jobs together - like `import` or `export` - and it can be used to filter the persisted job results.
  It is persisted and available via the new method `LongRunningJobData.getJobType ()`. For job results that were written before v10.4.0 it is `null`, so they never match a job type filter.
  All implementations of `ILongRunningJob` need to implement the new method.
* Added the new methods `ILongRunningJobResultManager.forEachJobResult (String, Consumer)` and `ILongRunningJobResultManager.getAllJobResults (String)` to iterate respectively retrieve the job results of a single job type only. Passing `null` as the job type means "no filtering".
  `forEachJobResult` invokes the consumer per job result instead of building the full list first - the SQL backend deserializes row by row and therefore no longer needs to keep the whole table in memory.
  `getAllJobResults ()` and `getAllJobResults (String)` are now `default` methods based on `forEachJobResult`, so custom implementations of `ILongRunningJobResultManager` only need to implement the new `forEachJobResult` method.
* **Breaking SQL schema change**: the table `long_running_job` used by `LongRunningJobResultManagerJDBC` needs the new nullable column `job_type VARCHAR(100)`; an index on it is recommended. Existing rows keep an empty job type and therefore never match a job type filter.
* Added the DDL of all tables used by `ph-oton-jdbc` as Flyway migrations in the new folder `docs/flyway`, for MySQL, PostgreSQL, DB2, SQL Server and Oracle. See `docs/README.md` for the usage, the origin of all column lengths and the known caveats.
  The scripts were cross checked against the production schema of [phoss-SMP](https://github.com/phax/phoss-SMP), which runs these managers on all five databases - hence the surrogate key `audit.id`, `SMALLINT` instead of `BOOLEAN` on DB2 and the unbounded text types for `secuser.pwsalt` / `pwhash` / `firstname` / `lastname`
* **Breaking API change**: the public constructor `LongRunningJobData (String, IMultilingualText, String)` was changed to `LongRunningJobData (String, String, IMultilingualText, String)` - the new second parameter is the job type
* Added the new server side mode `EDataTablesServerSideMode.ON_DEMAND` for DataTables, that keeps nothing in the session, next to the existing (and still default) mode `PRERENDERED`.
  In the mode `PRERENDERED` the whole table is rendered up-front and stored in the session as a `DataTablesServerData`, so that paging, sorting and filtering can be applied on that snapshot. The memory consumption of that snapshot is proportional to the number of rows times the number of sessions, which does not scale to large tables.
  In the mode `ON_DEMAND` every AJAX request is instead answered by the application via the new interface `IDataTablesOnDemandDataProvider`, so only the rows of the currently requested page are ever rendered. Select the mode via `DataTables.setServerSideMode (...)` and point the AJAX URL to the new `AjaxExecutorDataTablesOnDemand` instead of `AjaxExecutorDataTables`.
  The request is provided as `DataTablesOnDemandRequest`, which converts the DataTables parameters into a data store agnostic `IPagingSpec` (see ph-commons 12.4.0). The sort field name of a column is taken from the `name` property of the column - see `DataTablesColumnDef.setName (String)` - and falls back to the 0-based column index. Note that the field names and the search text come from the client and must be treated as untrusted input.
  The answer is provided as `DataTablesOnDemandResult`, consisting of the rows of the page plus the total and the filtered row count.
* Added the new method `DataTablesServerDataRow.getAsJson (HCSpecialNodes)` incl. the constants `DT_ROW_ID`, `DT_ROW_CLASS`, `DT_ROW_DATA` and `DT_ROW_ATTR`. `AjaxExecutorDataTables` uses it as well now, so that both server side modes create the identical JSON.
* **Incompatible change**: `DTSSResponseData` uses `long` instead of `int` for the total and the filtered record count - this affects the constructor as well as `getTotalRecords ()` and `getTotalDisplayRecords ()`.
* Fixed the DataTables server side search to no longer create an empty first search term, if the entered search text starts with whitespace. An empty search term matches every row and therefore silently weakened the filter.
* Reduced the memory consumption of the DataTables server side mode `PRERENDERED` (`DataTablesServerData`) by roughly half, without any behaviour change.
  `DataTablesServerDataCell` cached up to four representations of every single cell at the same time and never released any of them: the HC node tree, a full micro DOM copy of it, the HTML string and the plain text string. The micro DOM copy is now only an intermediate that is created on demand and discarded again, and the HC node tree is released as soon as both derived strings are present.
  Measured with 4.000 rows x 6 columns = 24.000 cells: the retained size of a snapshot dropped from 15.913 KiB to 9.588 KiB (-40%) when a single page of 25 rows is rendered, and from 19.123 KiB to 7.055 KiB (-63%) when all rows are rendered. The emitted HTML and the extracted text content are unchanged.
  The price is that the micro DOM is created twice for the cells of the currently displayed page - once for the text content and once for the HTML - which only affects the rows actually sent to the client.
* **Breaking API change**: removed the methods `DataTablesServerDataCell.getContent ()` and `setContent (IHCNodeList)`. The cell content is now released after rendering, so it can no longer be handed out. Both methods had no caller.
  Also removed the `writeObject`/`readObject` methods of `DataTablesServerDataCell` - the class does not implement `Serializable`, so they were never called. Note that `DataTablesServerData` implements `IHasUIState` (and therefore `Serializable`), but neither `DataTablesServerDataRow` nor `DataTablesServerDataCell` do, so a session containing a DataTables snapshot cannot be serialized anyway.
* `DataTablesServerDataCell.getHTMLString ()` is now annotated `@NonNull` instead of `@Nullable` - it never returned `null` before either.
* **Breaking API change**: the constructor of `AbstractLongRunningJobRunnable` takes the job type instead of the job ID as its first parameter, and the method `getJobID ()` was removed from it
* **Breaking API change**: the telemetry attribute constant `CLongRunningJobTelemetry.ATTR_JOB_ID` (`photon.job.id`) was replaced by `ATTR_JOB_TYPE` (`photon.job.type`), because the job type is now the low cardinality dimension the metrics are grouped by. `ATTR_JOB_EXECUTION_ID` (`photon.job.execution.id`) is unchanged.
* All `CompletableFuture` instances created by `PhotonWorkerPool` (`run`, `runThrowing`, `supply` and `supplyThrowing`) now have an `exceptionally` handler that logs otherwise unnoticed asynchronous failures (e.g. `Error`s or rejected executions). Previously such exceptions were only contained in the returned future and were silently lost if the caller never evaluated it.

v10.3.1 - 2026-08-12
* Requires at least ph-web 11.4.3
* Updated to ph-masterdata 8.2.0

v10.3.0 - 2026-07-25
* Updated to Jetty 12.1.10
* Deprecated submodule `ph-oton-icon` for removal - the icon libraries moved to the separate project https://github.com/phax/ph-oton-icon (Maven group `com.helger.photon.icon`)
* Added throttling on login, if unknown user names are used

v10.2.3 - 2026-05-16
* Updated to ph-commons 12.2.5
* Updated to ph-db 8.4.0
* Updated to Jetty 12.1.9
* Updated the default password hash algorithm to `PBKDF2_SHA256_100000_48`
* Removed OSGI bundling
* Added class `GlobalUserIDProvider` to provide an application wide current user ID. See [#31](https://github.com/phax/ph-oton/issues/31)
* Storing the new session scope in `LoginInfo` after session ID renewal on login
* Added method `EHTMLEntity.htmlUnescape (String)`
* Added an additional constructor to `SftpSettings`
* SFTP connection settings now expose the connection timeout as `java.time.Duration getConnectionTimeout ()`. The new configuration key `*.connectiontimeout` accepts duration strings (e.g. `10s`, `1m 30s`) parsed via `IConfig.getAsConfigDuration`. The previous `int getConnectionTimeoutMillis ()` API and `*.connectiontimeoutms` configuration key remain available and are marked `@Deprecated (forRemoval = true)`
* Deprecated old entries in `EStandardMetaElement`

v10.2.2 - 2026-03-02
* Fixed a regression on login, that killed the web-scope management

v10.2.1 - 2026-03-02
* Extracted class `AbstractHCExtSelect`
* Change HTTP session ID after successful login. See [#28](https://github.com/phax/ph-oton/issues/28) - thx @andreasa-winenet
* Added `ISftpSettingsHost.getKnownHostsPath ()`
* Added class `SftpMaxParallelRunner`

v10.2.0 - 2026-02-21
* Updated to Jetty 12.1.6
* Extracted new submodule `ph-oton-mgrs`
* Extracted new submodule `ph-oton-markdown` for Markdown handling
* Extracted the interface `ISystemMigrationManager` and provided an SQL backend via `SystemMigrationManagerJDBC`
* Moved the class `SystemMigrationManager` into package `com.helger.photon.mgrs.sysmigration`
* Created new class `PhotonCMSManager` and moved access to the `FavoriteManager` there
* Extracted interface `ISystemMessageManager` and provided an SQL backend via `SystemMessageManagerJDBC`
* Extracted interface `ILongRunningJobResultManager` and provided an SQL backend via `LongRunningJobResultManagerJDBC`
* Moved class `PhotonBasicManager` to the new submodule `ph-oton-mgrs`

v10.1.2 - 2026-01-10
* Added `IHCAttrContainer.setAria(Current|Disabled)`
* Updated to prism.js 1.3.0
* Updated to Datatables 2.3.5 + current plugins
* Added new internal class `PhotonInternalUnparsedJS`
* Fixed an error that `MarkdownProcessor` created nested `<td>` elements
* Made sure `UserGroupManagerJDBC` works with a single DB connection

v10.1.1 - 2025-12-14
* Updated to Jetty 12.1.5
* Extended API of `ConstantCSSPathProvider` and `ConstantJSPathProvider`
* Fixed an error in `PageViewExternalHTMLCleanser` that may lead to paths with two consecutive slashes (`//`)

v10.1.0 - 2025-11-16
* Updated to Jetty 12.1.4
* Updated to ph-commons 12.1.0
* Using JSpecify annotations
* Made sure the `DefaultCSSClassProvider` uses a thread-safe Map internally

v10.0.2 - 2025-10-23
* Updated to Jetty 12.1.3
* In case an application uses a custom server URL, the `StaticServerInfo` is now also using the path component from the provided URL instead of the `ServletContext` path

v10.0.1 - 2025-09-19
* Updated to Jetty 12.1.1
* Made `JettyStarter` `ResourceFactory` customizable
* Created new class `PhotonResourceFactory` and using it as the default in `JettyStarter`
* Added new class `SpringBootURIToURLConverter`

v10.0.0 - 2025-08-25
* Requires Java 17 as the minimum version
* Updated to ph-commons 12.0.0
* Updated to Jetty 12.1.0
* Removed all code marked as deprecated for removal
* Made two constructors of `Tenant` and `AccountingArea` public
* Moved the Bootstrap 4 binding to https://github.com/phax/ph-oton-bootstrap4

v9.3.2 - 2025-05-31
* Updated to Jetty 11.0.25
* Ensuring that BigDecimals with a negative scale are correctly formatted in JS. See [#23](https://github.com/phax/ph-oton/issues/23) - thx @domids
* Cache tab on Administration page "Statistics" now shows the cache hit and miss percentage
* Read-only `AbstractHCControl` no longer set the tabindex to `-1`

v9.3.1 - 2025-03-05
* Re-added class `ChartPaletteDefault` in package `com.helger.photon.uictrls.chart`
* Made sure that dynamically loaded JS and CSS also get the `nonce` attributes, if applicable

v9.3.0 - 2025-02-26
* Requires ph-web 10.4.0
* Updated to Bootstrap Icons 1.11.3
* Removed support for chart.js v1.x
* Updated list of global attributes in `IHCElement`
* `PhotonWorkerPool` now has a silent mode option
* The `InternalErrorBuilder` can now deal with custom email settings. See [#21](https://github.com/phax/ph-oton/issues/21)
* The default administration page `Statistics` got a "Download" buttons. See [#22](https://github.com/phax/ph-oton/issues/22)
* Moved classes `CSRFManager` and `CSRFSessionManager`  to submodule `ph-oton-app`
* Class `CSRFManager` now creates nonces with 32 instead of 64 bytes and uses Base64 instead of Base16 encoding.
* Added builders for `ConstantJSPathProvider` and `ConstantCSSPathProvider`
* Added classes `JSLet` and `JSConst` and using `let` by default in JS `for` and `for in` loops
* Added class `JSParam` and using it for function params and catch clauses (instead of `JSVar`)

v9.2.9 - 2025-02-03
* Updated to ph-web 10.3.0
* Added reusable SFTP helper classes to package `com.helger.photon.connect.sftp`
* `AbstractLoginManager` no longer stores the user agent by default

v9.2.8 - 2025-01-21
* Added support for Google ReCaptcha v3
* Updated to Log4J 2.24.3
* Updated to DataTables 2.2.1
* Updated to ph-web 10.2.x

v9.2.7 - 2024-10-03
* Updated to Jetty 11.0.24
* Updated to Log4J 2.24.0
* Allowed `.cjs` and `.mjs` as valid JavaScript file extensions
* `type` attribute of `script` element was changed from `IMimeType` to `String`
* Improved consistency of `aria-label`, `aria-labelledby`, `aria-description`, `aria-describedby`

v9.2.6 - 2024-08-09
* Updated to Jetty 11.0.22
* Fixed an error in DataTables max page calculation if length menu with only "all" is present
* Updated to DataTables 2.1.3

v9.2.5 - 2024-06-30
* Updated to Jetty 11.0.21
* Updated to DataTables 2.0.8 and updated translations
* Using `addAriaLabeledBy` in `connectFormControlWithLabel`. See [#18](https://github.com/phax/ph-oton/issues/18) - thx @NikovacsDev
* API extensions for DataTables `aria-label` in pagination area

v9.2.4 - 2024-04-25
* Updated to DataTables 2.0.5 and latest plugin versions
* Chart.js V4 API improvements 

v9.2.3 - 2024-04-18
* Updated to Log4J 2.23.1
* Updated to jQuery 3.7.1
* Updated to moment.js 2.30.1
* Added more charts.js v4 support
* Moved the Bootstrap 3 binding to https://github.com/phax/ph-oton-bootstrap3

v9.2.2 - 2024-03-28
* Updated to ph-commons 11.1.5
* Updated to Jetty 11.0.20
* Created Java 21 compatibility
* The default folder structure for internal errors was changed from `YYYY/MM` to `YYYY/MM/DD`
* Added initial support for charts.js v4

v9.2.1 - 2023-09-26
* Updated to Jetty 11.0.16
* Fixed DateTime picker error in September for en-gb locale [Java 17]

v9.2.0 - 2023-08-20
* Extracted submodule `ph-oton-io` for dependency minimized inclusion

v9.1.4 - 2023-08-17
* Updated to DataTables 1.13.6 and latest plugin versions
* Updated to ph-web 10.1.5
* Avoid Exception on "Delete all" in failed mail page with Bootstrap4 UI

v9.1.3 - 2023-07-31
* Updated to ph-commons 11.1

v9.1.2 - 2023-07-24
* Updated to DataTables 1.13.5 and latest plugin versions
* Updated to jQuery 3.7.0
* Updated to ph-web 10.1.2

v9.1.1 - 2023-05-03
* Updated to Jetty 11.0.15
* Updated to jQuery 3.6.4
* Added new enumeration `EHCAutoComplete` and the possibility to use in the HC edits
* Updated to DataTables 1.13.4 and latest plugin versions

v9.1.0 - 2023-02-24
* Using Servlet API 5.0.0 as the baseline: **JakartaEE 9, Java 11+, Apache Tomcat v10.0.x, Jetty 11.x**
* Switched all namespaces from `javax.servlet` to `jakarta.servlet`
* Updated to Jetty 11.0.13
* Updated to Log2J 2.20.0
* Updated to Jetty 11.0.13
* Incorporated UserToken JDBC manager from v8.4.5

v9.0.1 - 2025-05-06 [backport]
* Updated to ph-commons 11.2.1
* Updated to ph-web 10.0.2
* Updated to Jetty 10.0.25
* Moved the Bootstrap 3 binding to https://github.com/phax/ph-oton-bootstrap3

v9.0.0 - 2023-02-14
* Using Java 11 as the baseline
* Updated to ph-commons 11
* Using **Servlet API 4.0.1** as the baseline: **JakartaEE 8, Java 11+, Apache Tomcat v9.x, Jetty 10.0.x**
* Updated to Jetty 10.0.13
* Updated from `org.apache.logging.log4j:log4j-slf4j-impl` to `org.apache.logging.log4j:log4j-slf4j2-impl` for SLF4J 2.x

v8.4.5 - 2023-02-23
* Updated to Jetty 9.4.50
* Updated to Log2J 2.20.0
* Fixed default caching option for non GET requests
* Extended some API to adopt to the new UserToken requirements 
* Added UserToken manager for JDBC

v8.4.4 - 2023-01-23
* Updated to jQuery 3.6.3
* Updated to DataTables 1.13.1 and latest plugin versions
* Added methods `setAriaRequired`, `addToAriaDescribedBy` and `setAriaInvalid` to class `IHCAttrContainer`
* Added method `IHCElement.withCustomAttrs`
* Made some general accessibility improvements

v8.4.3 - 2022-10-04
* Updated to Jetty 9.4.49
* Updated to Log2J 2.19.0
* Extended the interface `ISystemMessageRenderer` with a display text
* Renamed Java method `var` to `variable` in `JS*` classes
* Fixed a potential NPE when trying to login with a non-existing user
* Fixed an issue in chartjs v1 CSS
* Changed chart.js package from `com.helger.photon.uictrls.chart` to `com.helger.photon.uictrls.chart.v1` (backwards incompatible change)

v8.4.2 - 2022-09-07
* Updated to jQuery 3.6.1
* Updated to jQuery Migrate 3.4.0
* Updated to js-cookie v3.0.1
* Updated to js-storage v1.0.4
* Updated to Moment.js 2.29.4
* Updated PDFObject to v2.2.8
* Integrated ph-oton-jdbc submodule
* The default minimum password length changed from 6 to 8
* Deprecated `HCSWFObject`

v8.4.1 - 2022-08-17
* Updated to Apache HttpClient 5.x
* Updated to Bootstrap 4.6.2
* Updated to Jetty 9.4.48
* Updated to Log2J 2.18.0
* Updated to ph-web 9.7.1
* Made class `DoNothingAuditor` final
* Added class `DoNothingAuditManager`
* Class `AbstractLoginManager` now has the possibility to specify a waiting time in case of a failed login
* Deprecated classes `HCUniversalAnalytics` and `HCGoogleAnalytics`
* Added support for Google Analytics V4
* Fixed an error in the MarkDown "safe mode" processing
* The `UITextFormatter` and the `BootstrapSystemMessage` MarkDown renderer now use the "safe mode" as the default

v8.4.0 - 2022-04-04
* Updated to Jetty 9.4.46
* Updated chart.js from 1.0.2 to 1.1.1
* Updated to Bootstrap Icons 1.8.1
* Updated to DataTables 1.11.5 and latest plugin versions
* Updated the list of predefined `aria-` attributes
* Disabled the "directory listing" in the JettyStarter by default
* Disabled sending the server version and the `X-Powered-By` header in JettyStarter by default
* Added support for HTML element `<dialog>`
* Moved class `HCHgroup` from package `.deprecated` to `.section`
* Moved class `HCMenu` from package `.interactive` to `.grouping`
* Added classes `HCMap`, `HCArea`, `EHCReferrerPolicy`
* Improved the overall HTML compliance according to the latest standard
* Dropped the specific HTML 5 annotations
* `HCMenu` now takes `HCLI` children instead of `HCMenuItem` children
* Extended HTML accessibility API - thx to @Dafnik
* The default script mode for inline scripts was changed from `PLAIN_TEXT_WRAPPED_IN_COMMENT` to `PLAIN_TEXT_NO_ESCAPE`
* Dropped support for Internet Explorer

v8.3.6 - 2022-01-04
* Updated Log2J to 2.17.1 - fixes CVE-2021-44832 - see https://logging.apache.org/log4j/2.x/security.html
* Made redirect URL after login customizable in `AbstractLoginManager`

v8.3.5 - 2021-12-21
* Updated Log2J to 2.17.0 - fixes CVE-2021-45105 - see https://logging.apache.org/log4j/2.x/security.html

v8.3.4 - 2021-12-14
* Updated Log2J to 2.16.0 - fixes CVE-2021-45046 - see https://www.lunasec.io/docs/blog/log4j-zero-day/
* Corrected error in DataTables 1.11.3 integration that lead to CSP errors because too much was included

v8.3.3 - 2021-12-13
* Updated Log2J to 2.15.0 - fixes CVE-2021-44228 - see https://www.lunasec.io/docs/blog/log4j-zero-day/
* Updated DataTables to 1.11.3

v8.3.2 - 2021-11-24
* Updated to Bootstrap 4.6.1
* Updated to FineUploader 5.16.2
* Updated to Jetty 9.4.44
* Updated to ph-commons 10.1.4
* Made the `IAuditManager` customizable in the PhotonSecurityManager
* Added built-in Base64 encode and decode pages in the "Utilities" menu area
* Added class `CountingSftpProgressMonitor`
* Fixed some SonarQube issues - nothing serious

v8.3.1 - 2021-08-05
* Updated to Jetty 9.4.43
* Updated to ph-web 9.6.1
* Updated DataTables to 1.10.24
* Updated Bootstrap4 DateTimePicker to 5.39.0
* Updated FontAwesome to 5.15.4
* Added Bootstrap Icons 1.5.0 in ph-oton-icon
* Improved the way, Jetty dependencies are handled in the POM
* `BootstrapForm` and `BootstrapViewForm` handle splitting with negative values for certain grid elements
* Made the `BootstrapFileUpload` placeholder label customizable
* Added the possibility to add an ID on the `BootstrapNav` of a `BootstrapTabBox`
* Made class `BootstrapCardCollapsible` more customizable
* Added custom jQuery function `setReadOnly`
* Made the BootstrapDateTimePicker default icon customizable - [#9](https://github.com/phax/ph-oton/issues/9)
* Hiding environment variables that start with `SECRET_` - see [#10](https://github.com/phax/ph-oton/issues/10)

v8.3.0 - 2021-03-22
* Updated to Apache commons-net 3.8.0
* Updated to Bootstrap 4.6.0
* Updated to Jetty 9.4.36
* Updated to jQuery 3.6.0
* Updated to ph-commons 10
* The name of the WebFragment `ph-oton-bootstrap3-stub` changed to `ph_oton_bootstrap4_stub`
* The name of the WebFragment `ph-oton-bootstrap4-stub` changed to `ph_oton_bootstrap3_stub`
* Extended `HCExtImg` to correctly remove the servlet context if it is present
* Added `BootstrapSelect2` as a Bootstrap 4 theme of Select2
* Added new predefined page to change the log level at runtime (when using Log4J 2.x)

v8.2.9 - 2020-11-18
* Updated to Apache commons-net 3.7.2
* Updated to Bootstrap 4.5.3
* Updated to Jetty 9.4.34
* Updated to Log4j 2.14.0
* Improved handling of generic children for HTML tables
* Added new sanity method `BootstrapFormGroup.setLabelForCheckBox`
* Added CSS classes to `ELabelType` directly

v8.2.8 - 2020-09-17
* Updated to Jakarta JAXB 2.3.3

v8.2.7 - 2020-09-15
* Updated to ph-commons 9.4.8
* Improved long running job error handling
* Extended `ConfigurationFileManager` API
* Using Popper UMD JS instead of the default distribution

v8.2.6 - 2020-08-20
* Updated animate.css to 4.1.0
* Updated to Apache commons-net 3.7
* Updated to autoNumeric 1.9.46
* Updated to Bootstrap 4.5.2
* Updated to CookieConsent 3.1.1
* Updated to Jetty 9.4.31
* Updated to popper.js 1.16.1-lts
* Updated to PrismJS 1.2.1
* Updated to Select2 4.0.13
* Removed autosize 1.8
* Improved customizability of `ExporterExcel`
* Fixed XML serialization of long running job data
* Reworked the PrismJS API and made the plugins more customizable
* Extended the Long running job API
* Added new class `PhotonWorkerPool` as a shared worker pool
* Improved coding style thanks to Sonar

v8.2.5 - 2020-05-26
* Changed Maven groupId to `com.helger.photon`
* Updated to ph-web 9.3.0 (new Maven groupId)

v8.2.4 - 2020-05-25
* Updated to Bootstrap 4.5.0
* Updated to Jetty 9.4.29
* Updated to jQuery 3.5.1
* Updated to Log4j 2.13.3
* Updated to ph-commons 9.4.4
* Added new classes `SessionBackedRequestFieldBoolean` and `SessionBackedRequestFieldBooleanMultiValue`
* Added new configuration file syntax `JSON`
* Fixed an issue with the configuration file ID for the tab
* Extracted `IRoleManager` from `RoleManager`
* Extracted `IUserManager` from `UserManager`
* Extracted `IUserGroupManager` from `UserGroupManager`
* Made `PhotonUnifiedResponse` chainable

v8.2.3 - 2020-02-19
* Fixed an error with the temporary directory name creation in `JettyStarter`
* Updated to ph-web 9.1.10
* Made `HttpClientSettings` configurable for `ReCaptchaServerSideValidator`
* Improved API to be more sensitive on encoded and decoded URI paths
* Added different date time representations to the default `InternalErrorMetadata`
* Added a new Admin page for performing a remote HTTP client call
* Extended traits interfaces `IHCTrait` and `IHCBootstrap4Trait`

v8.2.2 - 2020-02-12
* Updated to Jetty 9.4.26
* Updated to Bootstrap 4.4.1
* Updated to log4j 2.13.0
* Made a `StubObject` constructor public
* Added a version number constant to access information at runtime
* Extended `JettyStarter` to create multiple contexts
* Added possibility to add "redirects" to the menu tree to allow to change the URLs of existing items without breaking existing bookmarks
* Extracted `IUserTokenManager` interface
* Added possibility to globally configure if internal errors should be send as email and/or saved as XML
* Unified the `WebPageActionHandler` API (backwards incompatible change)
* Added traits interfaces `IHCTrait` and `IHCBootstrap4Trait` as syntactic sugar
* Added a new system page showing the content of the System Truststore (cacerts)

v8.2.1 - 2019-10-08
* Updated to log4j 2.12.1
* Updated to Jetty 9.4.21
* Updated to ph-web 9.1.4
* Updated to ph-commons 9.3.7
* Fixed the automatic module name of project `ph-oton-bootstrap4-pages`
* `BootstrapLoginHTMLProvider` now uses a form URL without the leading hostname
* Updated to HandleBars 4.x

v8.2.0 - 2019-06-24
* Improved B4 InputGroup API for easier extension
* Updated to Jetty 9.4.19
* Improved Bootstrap 4 inline form UI creation
* Improved API path ambiguity resolution API (interface `IAPIPathAmbiguityResolver` et. al.)
* Added new API Interface `IAPIRegistry` and let `GlobalAPIInvoker` implement it
* Clearly separate between `AjaxRegistry` and `AjaxInvoker` similar to new API API 
* Reworked module structure (created `ph-oton-app`, `ph-oton-audit`, `ph-oton-ajax`, `ph-oton-api`, removed `ph-oton-basic`)
* Updated to jQuery 3.4.1
* Requires ph-commons 9.3.3
* Requires ph-web 9.1.2
* `CheckDiskUsableSpaceJob` can now be scheduled more than once for different paths
* Application startup time is now displayed in the "System Properties" Administration page
* Added support for `loading` attribute in `HCImg` and `HCIFrame`
* Started extending audit library to be more flexible and efficient - not used yet

v8.1.3 - 2019-02-18
* Updated to Bootstrap 4.3.1
* Updated to Bootstrap 3.4.1
* Bootstrap 4 `BootstrapInputGroup` API was reworked, so that the parent/child relationship holds true (incompatible)
* Updated to log4j 2.11.2
* `ISimpleWebExecutionContext` was extended with the `LoggedInUser`
* API API was extended to support only different MIME types
* Added an exception mapper to the API handling
* Requires ph-web 9.1.1

v8.1.2 - 2019-01-07
* Added B4 tooltips, Jumbotron and Modal dialog
* Added class `BootstrapPageRenderer` for consistent page rendering
* Added B4 collapsible Card
* Small improvement in rendering memory consumption and speed
* Extracted `IDataTablesLengthMenu` interface
* Added B4 file upload control
* B4 `BootstrapHelper` became `BootstrapFormHelper`

v8.1.1 - 2018-11-25
* Fixed error in quoting of B3 user group management page
* Added special CSS Class to B4 `BootstrapPageHeader`
* Changed B4 `BootstrapDateTimePicker` initialization to correctly handle the default values
* Extended `FavoriteManager` API
* Updated to Jetty 9.4.14
* Changed B4 `BootstrapDateTimePicker` to not use currentDate by default 
* Requires ph-commons 9.2.0

v8.1.0 - 2018-10-25
* Extended the `Favorite` APIs
* Initial versions of the subproject `ph-oton-bootstrap4-uictrls` is available 
* Initial versions of the subproject `ph-oton-bootstrap4-pages` is available 
* Initial versions of the subproject `ph-oton-bootstrap4-demo` is available 
* Internal errors are stored in an additional "month" sub-directory
* Internal error storage paths are now customizable  
* Added FontAwesome 5.2.0 free icons
* Improved state API slightly
* User group and role user interfaces were improved
* Updated to ph-web 9.0.5
* Updated to popper 1.14.4
* Added bulk export format "JSON (simple)" to have one without type information
* Added a new Bootstrap 4 DateTime picker UI ctrl
* Updated to Jetty 9.4.12
* Updated to TinyMCE 4.8.2
* Separated `HCPrismJS` and `TypeaheadEdit` in Bootstrap 3 and 4 versions
* Dropped jQuery 1.x and 2.x files
* Updated DataTables to 1.10.18
* Added new base page "Port checker"
* Improved JettyStarter customizability

v8.0.2 - 2018-07-25
* Updated to Bootstrap 4.1.3
* Reworked meta element API so that `<meta charset="...">` can be handled
* Fixed OSGI ServiceProvider configuration
* Updated to Jetty 9.4.11
* Updated to ph-commons 9.1.3
* Updated to ph-web 9.0.2
* Extended `*HCSelect` APIs for multi values
* The "Change log" page was removed
* Extended `JSSwitch` API
* Improved signatures of `AbstractJSBlock` (binary incompatible)
* Updated to ph-masterdata 6.1.0

v8.0.1 - 2018-04-18
* Updated to Jetty 9.4.9
* Updated to Bootstrap 4.0.0
* Bootstrap3Panel type can be changed
* Replaced some `StubjObject` with `IBusinessObject`

v8.0.0 - 2018-01-05
* Updated to ph-commons 9.0.0
* Renamed 'Client' to 'Tenant' and moved to ph-tenancy in ph-masterdata project
* Updated to Bootstrap 4.0.0-beta
* Updated to TinyMCE 4.6.6
* Updated to Jetty 9.4.8
* Extracted ph-oton-atom
* Initial versions of the subproject `ph-oton-bootstrap4-stub` is available 

v7.1.2 - 2017-07-19
* Fixed an error with path handling of resource bundles when deployed as JAR

v7.1.1 - 2017-07-18
* Requires ph-commons 8.6.6
* ServletContext base path can now be any URL (instead of a mandatory File)
* Made `ServletStatusManager` non-static
* Improved JS API (better Json support)

v7.1.0 - 2017-07-05
* Updated to ph-web 8.8.x
* Improved rendering speed through internal optimizations
* Updated to Bootstrap Datetime picker 2.4.4
* Updated to Jetty 9.4.6
* `CharacterEncodingFilter` more flexible
* Updated to DataTables 1.10.15

v7.0.5 - 2017-03-30
* Updated to Jetty 9.4.3
* Updated to ph-schedule 3.6.1
* Improved SystemMessage API

v7.0.4 - 2017-03-09
* Updated to Jetty 9.4.2
* API extensions
* Updated to Apache commons-net 3.6
* Improved internal error handling API
* Internal changes in the ph-oton jobs.
* Removed some deprecated methods

v7.0.3 - 2017-01-10
* Integrated ph-html into ph-oton
* Binds to ph-commons 8.6.0

v7.0.2 - 2017-01-03
* Updated to Jetty 9.4.0
* Binds to ph-commons 8.5.6
* Binds to ph-web 8.7.0
* Improved default request parameter handling customizability
* New artefact `ph-oton-icon`

v7.0.1 - 2016-11-14
* Improvements in request tracking logging
* Misc small improvements in different areas
* Updated to Jetty 9.3.14.v20161028

v7.0.0 - 2016-10-24
* Requires ph-commons 8.5.2

v7.0.0-beta3 - 2016-09-22
* Based on ph-commons 8.5.x

v7.0.0-beta2 - 2016-08-31
* Based on ph-commons 8.4.x

v7.0.0-beta1 - 2016-07-27
* Updated to Java 1.8
* Based on ph-commons 8.2.x

v6.2.0 - 2015-12-03 
* extracted security module
* added support for app and user token management
* Last release for Java 1.7

v6.1.0 - 2015-10-02 
* merged web actions and ajax functions

v6.0.0 - 2015-09-14 
* first ph-oton release based on old webbasics and webctrls etc.
* requires ph-commons 6.x

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
It is appreciated if you star the GitHub project if you like it.
