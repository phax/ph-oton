# ph-oton

[![Build Status](https://travis-ci.org/phax/ph-oton.svg?branch=master)](https://travis-ci.org/phax/ph-oton)
﻿
This set of Java libraries forms a package to build Java web applications.

Contained subprojects are:
  * ph-oton-html - Java wrapper for all HTML elements and attributes
  * ph-oton-jscode - a Java code model to build structured JS code
  * ph-oton-jquery - an extension to ph-html-jscode to also support jQuery
  * ph-oton-basic - basic application stuff
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

Release history:
  * v7.1.0 - work in progress
    * Updated to ph-web 8.8.0
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

# Requirements
  * Java 1.8+ is required for building 
  * Application server requirements:
      * At least Tomcat 8.x
      * Jetty 9.3.x with AnnotationConfiguration enabled
      
## Maven usage

```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>com.helger</groupId>
        <artifactId>ph-oton-parent-pom</artifactId>
        <version>7.0.5</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
  
  <dependencies>
    <dependency>
      <groupId>com.helger</groupId>
      <artifactId>ph-oton-bootstrap3-stub</artifactId>
    </dependency>
  </dependencies>
```


---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodeingStyleguide.md) |
On Twitter: <a href="https://twitter.com/philiphelger">@philiphelger</a>
