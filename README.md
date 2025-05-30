# ph-oton

[![javadoc](https://javadoc.io/badge2/com.helger.photon/ph-oton-parent-pom/javadoc.svg)](https://javadoc.io/doc/com.helger.photon/ph-oton-parent-pom)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.helger.photon/ph-oton-parent-pom/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.helger.photon/ph-oton-parent-pom) 

This set of Java libraries forms a package to build Java web applications.

Contained subprojects are:
  * ph-oton-html - Java wrapper for all HTML elements and attributes
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
  * ph-oton-core - basic web stuff
  * ph-oton-uicore - basic web UI stuff
  * ph-oton-icon - icon library
  * ph-oton-tinymce4 - TinyMCE4 wrapper
  * ph-oton-datatables- Datatables.net wrapper
  * ph-oton-uictrls - misc web UI controls
  * ph-oton-jetty - wrapper for Jetty to simply use as main 
  * ph-oton-bootstrap4 - placeholder for Bootstrap 4 controls (still alpha) 
  * ph-oton-bootstrap4-uictrls - special UI controls for Bootstrap 4
  * ph-oton-bootstrap4-pages - predefined UI pages with Bootstrap 4 styling
  * ph-oton-bootstrap4-stub - Servlet stub for Bootstrap 4 web applications
  * ph-oton-bootstrap4-demo - a standalone demo web application to be run in Tomcat or in provided Jetty

# Requirements

* Java 11+ is required for building 
* Application server requirements:
    * At least Tomcat 10.1.x
    * Jetty 11.x with AnnotationConfiguration enabled
      
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

### Bootstrap 4

To use Bootstrap 4 front end use:

```xml
  <dependencies>
    <dependency>
      <groupId>com.helger.photon</groupId>
      <artifactId>ph-oton-bootstrap4-stub</artifactId>
    </dependency>
  </dependencies>
```

See the submodule `ph-oton-bootstrap4-demo` for a working example project with Bootstrap 4 UI.

Note: prior to v8.2.5 the Maven groupId was `com.helger`.

## News and noteworthy

* v9.3.2 - 2025-05-31
    * Updated to Jetty 11.0.25
    * Ensuring that BigDecimals with a negative scale are correctly formatted in JS. See [#23](https://github.com/phax/ph-oton/issues/23) - thx @domids
    * Cache tab on Administration page "Statistics" now shows the cache hit and miss percentage
    * Read-only `AbstractHCControl` no longer set the tabindex to `-1`
* v9.3.1 - 2025-03-05
    * Re-added class `ChartPaletteDefault` in package `com.helger.photon.uictrls.chart`
    * Made sure that dynamically loaded JS and CSS also get the `nonce` attributes, if applicable
* v9.3.0 - 2025-02-26
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
* v9.2.9 - 2025-02-03
    * Updated to ph-web 10.3.0
    * Added reusable SFTP helper classes to package `com.helger.photon.connect.sftp`
    * `AbstractLoginManager` no longer stores the user agent by default
* v9.2.8 - 2025-01-21
    * Added support for Google ReCaptcha v3
    * Updated to Log4J 2.24.3
    * Updated to DataTables 2.2.1
    * Updated to ph-web 10.2.x
* v9.2.7 - 2024-10-03
    * Updated to Jetty 11.0.24
    * Updated to Log4J 2.24.0
    * Allowed `.cjs` and `.mjs` as valid JavaScript file extensions
    * `type` attribute of `script` element was changed from `IMimeType` to `String`
    * Improved consistency of `aria-label`, `aria-labelledby`, `aria-description`, `aria-describedby`
* v9.2.6 - 2024-08-09
    * Updated to Jetty 11.0.22
    * Fixed an error in DataTables max page calculation if length menu with only "all" is present
    * Updated to DataTables 2.1.3
* v9.2.5 - 2024-06-30
    * Updated to Jetty 11.0.21
    * Updated to DataTables 2.0.8 and updated translations
    * Using `addAriaLabeledBy` in `connectFormControlWithLabel`. See [#18](https://github.com/phax/ph-oton/issues/18) - thx @NikovacsDev
    * API extensions for DataTables `aria-label` in pagination area
* v9.2.4 - 2024-04-25
    * Updated to DataTables 2.0.5 and latest plugin versions
    * Chart.js V4 API improvements 
* v9.2.3 - 2024-04-18
    * Updated to Log4J 2.23.1
    * Updated to jQuery 3.7.1
    * Updated to moment.js 2.30.1
    * Added more charts.js v4 support
    * Moved the Bootstrap 3 binding to https://github.com/phax/ph-oton-bootstrap3
* v9.2.2 - 2024-03-28
    * Updated to ph-commons 11.1.5
    * Updated to Jetty 11.0.20
    * Created Java 21 compatibility
    * The default folder structure for internal errors was changed from `YYYY/MM` to `YYYY/MM/DD`
    * Added initial support for charts.js v4
* v9.2.1 - 2023-09-26
    * Updated to Jetty 11.0.16
    * Fixed DateTime picker error in September for en-gb locale [Java 17]
* v9.2.0 - 2023-08-20
    * Extracted submodule `ph-oton-io` for dependency minimized inclusion
* v9.1.4 - 2023-08-17
    * Updated to DataTables 1.13.6 and latest plugin versions
    * Updated to ph-web 10.1.5
    * Avoid Exception on "Delete all" in failed mail page with Bootstrap4 UI
* v9.1.3 - 2023-07-31
    * Updated to ph-commons 11.1
* v9.1.2 - 2023-07-24
    * Updated to DataTables 1.13.5 and latest plugin versions
    * Updated to jQuery 3.7.0
    * Updated to ph-web 10.1.2
* v9.1.1 - 2023-05-03
    * Updated to Jetty 11.0.15
    * Updated to jQuery 3.6.4
    * Added new enumeration `EHCAutoComplete` and the possibility to use in the HC edits
    * Updated to DataTables 1.13.4 and latest plugin versions
* v9.1.0 - 2023-02-24
    * Using Servlet API 5.0.0 as the baseline: **JakartaEE 9, Java 11+, Apache Tomcat v10.0.x, Jetty 11.x**
    * Switched all namespaces from `javax.servlet` to `jakarta.servlet`
    * Updated to Jetty 11.0.13
    * Updated to Log2J 2.20.0
    * Updated to Jetty 11.0.13
    * Incorporated UserToken JDBC manager from v8.4.5
* v9.0.1 - 2025-05-06 [backport]
    * Updated to ph-commons 11.2.1
    * Updated to ph-web 10.0.2
    * Updated to Jetty 10.0.25
    * Moved the Bootstrap 3 binding to https://github.com/phax/ph-oton-bootstrap3
* v9.0.0 - 2023-02-14
    * Using Java 11 as the baseline
    * Updated to ph-commons 11
    * Using **Servlet API 4.0.1** as the baseline: **JakartaEE 8, Java 11+, Apache Tomcat v9.x, Jetty 10.0.x**
    * Updated to Jetty 10.0.13
    * Updated from `org.apache.logging.log4j:log4j-slf4j-impl` to `org.apache.logging.log4j:log4j-slf4j2-impl` for SLF4J 2.x
* v8.4.5 - 2023-02-23
    * Updated to Jetty 9.4.50
    * Updated to Log2J 2.20.0
    * Fixed default caching option for non GET requests
    * Extended some API to adopt to the new UserToken requirements 
    * Added UserToken manager for JDBC
* v8.4.4 - 2023-01-23
    * Updated to jQuery 3.6.3
    * Updated to DataTables 1.13.1 and latest plugin versions
    * Added methods `setAriaRequired`, `addToAriaDescribedBy` and `setAriaInvalid` to class `IHCAttrContainer`
    * Added method `IHCElement.withCustomAttrs`
    * Made some general accessibility improvements
* v8.4.3 - 2022-10-04
    * Updated to Jetty 9.4.49
    * Updated to Log2J 2.19.0
    * Extended the interface `ISystemMessageRenderer` with a display text
    * Renamed Java method `var` to `variable` in `JS*` classes
    * Fixed a potential NPE when trying to login with a non-existing user
    * Fixed an issue in chartjs v1 CSS
    * Changed chart.js package from `com.helger.photon.uictrls.chart` to `com.helger.photon.uictrls.chart.v1` (backwards incompatible change)
* v8.4.2 - 2022-09-07
    * Updated to jQuery 3.6.1
    * Updated to jQuery Migrate 3.4.0
    * Updated to js-cookie v3.0.1
    * Updated to js-storage v1.0.4
    * Updated to Moment.js 2.29.4
    * Updated PDFObject to v2.2.8
    * Integrated ph-oton-jdbc submodule
    * The default minimum password length changed from 6 to 8
    * Deprecated `HCSWFObject`
* v8.4.1 - 2022-08-17
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
* v8.4.0 - 2022-04-04
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
* v8.3.6 - 2022-01-04
    * Updated Log2J to 2.17.1 - fixes CVE-2021-44832 - see https://logging.apache.org/log4j/2.x/security.html
    * Made redirect URL after login customizable in `AbstractLoginManager`
* v8.3.5 - 2021-12-21
    * Updated Log2J to 2.17.0 - fixes CVE-2021-45105 - see https://logging.apache.org/log4j/2.x/security.html
* v8.3.4 - 2021-12-14
    * Updated Log2J to 2.16.0 - fixes CVE-2021-45046 - see https://www.lunasec.io/docs/blog/log4j-zero-day/
    * Corrected error in DataTables 1.11.3 integration that lead to CSP errors because too much was included
* v8.3.3 - 2021-12-13
    * Updated Log2J to 2.15.0 - fixes CVE-2021-44228 - see https://www.lunasec.io/docs/blog/log4j-zero-day/
    * Updated DataTables to 1.11.3
* v8.3.2 - 2021-11-24
    * Updated to Bootstrap 4.6.1
    * Updated to FineUploader 5.16.2
    * Updated to Jetty 9.4.44
    * Updated to ph-commons 10.1.4
    * Made the `IAuditManager` customizable in the PhotonSecurityManager
    * Added built-in Base64 encode and decode pages in the "Utilities" menu area
    * Added class `CountingSftpProgressMonitor`
    * Fixed some SonarQube issues - nothing serious
* v8.3.1 - 2021-08-05
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
* v8.3.0 - 2021-03-22
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
* v8.2.9 - 2020-11-18
    * Updated to Apache commons-net 3.7.2
    * Updated to Bootstrap 4.5.3
    * Updated to Jetty 9.4.34
    * Updated to Log4j 2.14.0
    * Improved handling of generic children for HTML tables
    * Added new sanity method `BootstrapFormGroup.setLabelForCheckBox`
    * Added CSS classes to `ELabelType` directly
* v8.2.8 - 2020-09-17
    * Updated to Jakarta JAXB 2.3.3
* v8.2.7 - 2020-09-15
    * Updated to ph-commons 9.4.8
    * Improved long running job error handling
    * Extended `ConfigurationFileManager` API
    * Using Popper UMD JS instead of the default distribution
* v8.2.6 - 2020-08-20
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
* v8.2.5 - 2020-05-26
    * Changed Maven groupId to `com.helger.photon`
    * Updated to ph-web 9.3.0 (new Maven groupId)
* v8.2.4 - 2020-05-25
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
* v8.2.3 - 2020-02-19
    * Fixed an error with the temporary directory name creation in `JettyStarter`
    * Updated to ph-web 9.1.10
    * Made `HttpClientSettings` configurable for `ReCaptchaServerSideValidator`
    * Improved API to be more sensitive on encoded and decoded URI paths
    * Added different date time representations to the default `InternalErrorMetadata`
    * Added a new Admin page for performing a remote HTTP client call
    * Extended traits interfaces `IHCTrait` and `IHCBootstrap4Trait`
* v8.2.2 - 2020-02-12
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
* v8.2.1 - 2019-10-08
    * Updated to log4j 2.12.1
    * Updated to Jetty 9.4.21
    * Updated to ph-web 9.1.4
    * Updated to ph-commons 9.3.7
    * Fixed the automatic module name of project `ph-oton-bootstrap4-pages`
    * `BootstrapLoginHTMLProvider` now uses a form URL without the leading hostname
    * Updated to HandleBars 4.x
* v8.2.0 - 2019-06-24
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
* v8.1.3 - 2019-02-18
    * Updated to Bootstrap 4.3.1
    * Updated to Bootstrap 3.4.1
    * Bootstrap 4 `BootstrapInputGroup` API was reworked, so that the parent/child relationship holds true (incompatible)
    * Updated to log4j 2.11.2
    * `ISimpleWebExecutionContext` was extended with the `LoggedInUser`
    * API API was extended to support only different MIME types
    * Added an exception mapper to the API handling
    * Requires ph-web 9.1.1
* v8.1.2 - 2019-01-07
    * Added B4 tooltips, Jumbotron and Modal dialog
    * Added class `BootstrapPageRenderer` for consistent page rendering
    * Added B4 collapsible Card
    * Small improvement in rendering memory consumption and speed
    * Extracted `IDataTablesLengthMenu` interface
    * Added B4 file upload control
    * B4 `BootstrapHelper` became `BootstrapFormHelper`
* v8.1.1 - 2018-11-25
    * Fixed error in quoting of B3 user group management page
    * Added special CSS Class to B4 `BootstrapPageHeader`
    * Changed B4 `BootstrapDateTimePicker` initialization to correctly handle the default values
    * Extended `FavoriteManager` API
    * Updated to Jetty 9.4.14
    * Changed B4 `BootstrapDateTimePicker` to not use currentDate by default 
    * Requires ph-commons 9.2.0
* v8.1.0 - 2018-10-25
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
* v8.0.2 - 2018-07-25
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
* v8.0.1 - 2018-04-18
    * Updated to Jetty 9.4.9
    * Updated to Bootstrap 4.0.0
    * Bootstrap3Panel type can be changed
    * Replaced some `StubjObject` with `IBusinessObject`
* v8.0.0 - 2018-01-05
    * Updated to ph-commons 9.0.0
    * Renamed 'Client' to 'Tenant' and moved to ph-tenancy in ph-masterdata project
    * Updated to Bootstrap 4.0.0-beta
    * Updated to TinyMCE 4.6.6
    * Updated to Jetty 9.4.8
    * Extracted ph-oton-atom
    * Initial versions of the subproject `ph-oton-bootstrap4-stub` is available 
* v7.1.2 - 2017-07-19
    * Fixed an error with path handling of resource bundles when deployed as JAR
* v7.1.1 - 2017-07-18
    * Requires ph-commons 8.6.6
    * ServletContext base path can now be any URL (instead of a mandatory File)
    * Made `ServletStatusManager` non-static
    * Improved JS API (better Json support)
* v7.1.0 - 2017-07-05
    * Updated to ph-web 8.8.x
    * Improved rendering speed through internal optimizations
    * Updated to Bootstrap Datetime picker 2.4.4
    * Updated to Jetty 9.4.6
    * `CharacterEncodingFilter` more flexible
    * Updated to DataTables 1.10.15
* v7.0.5 - 2017-03-30
    * Updated to Jetty 9.4.3
    * Updated to ph-schedule 3.6.1
    * Improved SystemMessage API
* v7.0.4 - 2017-03-09
    * Updated to Jetty 9.4.2
    * API extensions
    * Updated to Apache commons-net 3.6
    * Improved internal error handling API
    * Internal changes in the ph-oton jobs.
    * Removed some deprecated methods
* v7.0.3 - 2017-01-10
    * Integrated ph-html into ph-oton
    * Binds to ph-commons 8.6.0
* v7.0.2 - 2017-01-03
    * Updated to Jetty 9.4.0
    * Binds to ph-commons 8.5.6
    * Binds to ph-web 8.7.0
    * Improved default request parameter handling customizability
    * New artefact `ph-oton-icon`
* v7.0.1 - 2016-11-14
    * Improvements in request tracking logging
    * Misc small improvements in different areas
    * Updated to Jetty 9.3.14.v20161028
* v7.0.0 - 2016-10-24
    * Requires ph-commons 8.5.2
* v7.0.0-beta3 - 2016-09-22
    * Based on ph-commons 8.5.x
* v7.0.0-beta2 - 2016-08-31
    * Based on ph-commons 8.4.x
* v7.0.0-beta1 - 2016-07-27
    * Updated to Java 1.8
    * Based on ph-commons 8.2.x
* v6.2.0 - 2015-12-03 
    * extracted security module
    * added support for app and user token management
    * Last release for Java 1.7
* v6.1.0 - 2015-10-02 
    * merged web actions and ajax functions
* v6.0.0 - 2015-09-14 
    * first ph-oton release based on old webbasics and webctrls etc.
    * requires ph-commons 6.x

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
It is appreciated if you star the GitHub project if you like it.