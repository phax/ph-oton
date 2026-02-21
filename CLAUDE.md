# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

ph-oton is a multi-module Java library stack for building Java web applications. It provides HTML element wrappers, JS code generation, jQuery integration, security, AJAX/API handling, UI controls, and embedded Jetty support. Published to Maven Central under group `com.helger.photon`.

Current version: 10.2.0-SNAPSHOT (requires Java 17+, JakartaEE 10 / Tomcat 10.1.x / Jetty 12.x).

## Build Commands

```bash
# Full build with tests
mvn install

# Build without tests
mvn install -DskipTests

# Build a single module
mvn install -pl ph-oton-html

# Build a single module with dependencies
mvn install -pl ph-oton-core -am

# Run tests for a single module
mvn test -pl ph-oton-html

# Run a single test class
mvn test -pl ph-oton-html -Dtest=com.helger.html.hc.html.tabular.HCTableTest

# CI build (what GitHub Actions runs)
mvn --batch-mode --update-snapshots install
```

## Module Dependency Layers

The modules form a layered dependency graph (lower layers depend on nothing above them):

1. **Foundation**: `ph-oton-html` (HTML elements), `ph-oton-jscode` (JS code model), `ph-oton-atom` (ATOM feeds)
2. **Extensions**: `ph-oton-jquery` (extends jscode), `ph-oton-io` (basic IO)
3. **Application**: `ph-oton-app` (app basics, CSRF, resource bundling), `ph-oton-audit`, `ph-oton-ajax`, `ph-oton-api`
4. **Security & Data**: `ph-oton-security` (users, roles, groups), `ph-oton-exchange` (import/export)
5. **Connectivity**: `ph-oton-connect` (SFTP, etc.), `ph-oton-mgrs` (managers), `ph-oton-jdbc`
6. **Web Core**: `ph-oton-core` (servlets, menu tree, request handling, SMTP)
7. **UI**: `ph-oton-uicore` (web pages), `ph-oton-icon`, `ph-oton-tinymce4`, `ph-oton-datatables`, `ph-oton-uictrls`
8. **Runtime**: `ph-oton-jetty` (embedded Jetty runner)

Bootstrap 4 UI binding lives in a separate repository: https://github.com/phax/ph-oton-bootstrap4

## Key Architectural Patterns

- **OSGi bundles**: All modules are packaged as OSGi bundles using `maven-bundle-plugin` with `Automatic-Module-Name` declarations.
- **SPI-based initialization**: Modules use `IThirdPartyModuleProviderSPI` for service loading. Most modules have an `SPITest` to verify SPI configuration.
- **MicroTypeConverter**: XML serialization/deserialization uses the ph-commons `MicroTypeConverter` pattern (see `*MicroTypeConverter` classes).
- **Manager pattern**: Singleton managers (e.g., `PhotonCoreManager`, `PhotonBasicManager`, `PhotonCMSManager`) manage lifecycle of sub-managers for security, SMTP, system messages, etc. Many managers have both XML-DAO and JDBC backend implementations.
- **Menu tree**: Navigation is built via `MenuTree` with `MenuItemPage` entries. Pages extend `AbstractWebPage`.
- **Execution contexts**: Request processing flows through `ISimpleWebExecutionContext` → `ILayoutExecutionContext` carrying locale, menu state, and logged-in user.
- **HC (HTML Component) model**: HTML is built programmatically using `HC*` classes (e.g., `HCDiv`, `HCTable`, `HCA`) rather than templates.

## Package Naming

All source is under `com.helger.photon.*` except the HTML/JS foundation modules which use `com.helger.html.*`.

## Testing

Tests use JUnit 4 (`junit:junit`). Test support comes from `ph-unittest-support-ext`. CI tests against Java 17, 21, and 25.

## Coding Style

Follows the [phax coding styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md). Uses JSpecify nullability annotations.
