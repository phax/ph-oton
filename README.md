# ph-oton

[![Build Status](https://travis-ci.org/phax/ph-oton.svg?branch=master)](https://travis-ci.org/phax/ph-oton)
﻿
This set of Java libraries forms a package to build Java web applications.

Contained subprojects are:
  * ph-oton-html - Java wrapper for all HTML elements and attributes
  * ph-oton-jscode - a Java code model to build structured JS code
  * ph-oton-jquery - an extension to ph-html-jscode to also support jQuery
  * ph-oton-atom - ATOM newsfeed stuff
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
  * ph-oton-bootstrap3 - Java Wrapper for the Bootstrap 3.x controls
  * ph-oton-bootstrap3-uictrls - special UI controls for Bootstrap 3
  * ph-oton-bootstrap3-pages - predefined UI pages with Bootstrap 3 styling
  * ph-oton-bootstrap3-stub - Servlet stub for Bootstrap 3 web applications
  * ph-oton-bootstrap3-demo - a standalone demo web application to be run in Tomcat or in provided Jetty
  * ph-oton-bootstrap4 - placeholder for Bootstrap 4 controls (still alpha) 
  * ph-oton-bootstrap4-uictrls - special UI controls for Bootstrap 4
  * ph-oton-bootstrap4-pages - predefined UI pages with Bootstrap 4 styling
  * ph-oton-bootstrap4-stub - Servlet stub for Bootstrap 4 web applications
  * ph-oton-bootstrap4-demo - a standalone demo web application to be run in Tomcat or in provided Jetty

# Requirements

* Java 1.8+ is required for building 
* Application server requirements:
    * At least Tomcat 8.x
    * Jetty 9.4.x with AnnotationConfiguration enabled
      
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

### Bootstrap 3

To use Bootstrap 3 front end use:

```xml
  <dependencies>
    <dependency>
      <groupId>com.helger.photon</groupId>
      <artifactId>ph-oton-bootstrap3-stub</artifactId>
    </dependency>
  </dependencies>
```

See the submodule `ph-oton-bootstrap3-demo` for a working example project with Bootstrap 3 UI.

Note: prior to v8.2.5 the Maven groupId was `com.helger`.

## News and noteworthy

* v8.2.9 - 2020-11-13
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
On Twitter: <a href="https://twitter.com/philiphelger">@philiphelger</a> |
Kindly supported by [YourKit Java Profiler](https://www.yourkit.com)