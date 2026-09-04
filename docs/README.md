# Database schema for the ph-oton JDBC backends

`ph-oton-jdbc` contains SQL based implementations of the managers that are
otherwise persisted as XML files. The library itself never creates any table -
that is the responsibility of the application. This folder contains the DDL
for all tables that `ph-oton-jdbc` reads and writes, ready to be used as
[Flyway](https://flywaydb.org/) migrations.

The schema matches **ph-oton 10.5.0** after applying all migrations.

## Layout

```
docs/flyway/<dialect>/V1__ph_oton_baseline.sql
docs/flyway/<dialect>/V2__long_running_job_add_job_type.sql
docs/flyway/<dialect>/V3__user_id_max_length.sql
```

| Dialect folder | Target |
|---|---|
| `mysql` | MySQL 8.x / MariaDB 10.x |
| `postgresql` | PostgreSQL 12+ |
| `db2` | IBM DB2 LUW 11.1+ |
| `sqlserver` | Microsoft SQL Server 2016+ |
| `oracle` | Oracle 12c+ |

Point Flyway at the folder of your database, e.g.

```
flyway.locations=filesystem:docs/flyway/postgresql
```

## V1, V2 and V3

`V1__ph_oton_baseline.sql` creates the schema of ph-oton 10.3.x.
`V2__long_running_job_add_job_type.sql` upgrades it to 10.4.0 by adding the
`job_type` column to `long_running_job` plus its index.
`V3__user_id_max_length.sql` upgrades it to 10.5.0 by widening every column
that holds a user ID to 45 characters.

All scripts run in order, so there is nothing to choose:

* **New installation** - point Flyway at the folder and run `flyway migrate`
  against the empty database. V1, V2 and V3 are applied one after the other.
* **Existing installation that was never managed by Flyway** - run
  `flyway baseline -baselineVersion=<n>` against the existing schema first,
  where `<n>` is the version it already has: `1` for a ph-oton 10.3.x schema
  and `2` for a 10.4.0 one.

Job results that already exist keep an empty `job_type` and therefore never
match a job type filter in
`ILongRunningJobResultManager.forEachJobResult (String, Consumer)`.

V3 changes the length of the columns only - no column is added, removed or
renamed, and the nullability stays as it was, so existing data is preserved.
It affects `audit.userid`, the `creationuserid` / `lastmoduserid` /
`deleteuserid` of all four security tables, and - because
`IUser.USER_ID_MAX_LENGTH` is `GlobalIDFactory.STRING_ID_MAX_LENGTH` (40) since
10.5.0 and was 20 before - also `secuser.id` and `secusertoken.userid`, which
would overflow otherwise. `long_running_job.id` is deliberately left at 40: it
holds a persistent ID from `GlobalIDFactory`, not a user ID.

## Tables

| Table | Written by | Notes |
|---|---|---|
| `audit` | `AuditorJDBC` | Append only. `id` is a surrogate identity key that the manager never reads |
| `long_running_job` | `LongRunningJobResultManagerJDBC` | `id` is the globally unique execution ID, `job_data` the job result as XML |
| `sys_migration` | `SystemMigrationManagerJDBC` | No primary key - one row per migration attempt |
| `sys_message` | `SystemMessageManagerJDBC` | Holds at most one row |
| `secrole` | `RoleManagerJDBC` | |
| `secusergroup` | `UserGroupManagerJDBC` | `userids` / `roleids` are JSON arrays |
| `secuser` | `UserManagerJDBC` | |
| `secusertoken` | `UserTokenManagerJDBC` | `accesstokens` is a JSON array |

All security tables share the business object columns `creationdt`,
`creationuserid`, `lastmoddt`, `lastmoduserid`, `deletedt`, `deleteuserid` and
`attrs` (a JSON object).

## Where the column lengths come from

Every `VARCHAR` length is taken from the constant that
`DBValueHelper.getTrimmedToLength` is called with in the respective manager:

| Column(s) | Length | Constant |
|---|---|---|
| `secuser.id`, `secusertoken.userid`, `*.creationuserid`, `*.lastmoduserid`, `*.deleteuserid`, `audit.userid` | 45 | `IUser.USER_ID_MAX_LENGTH` = `GlobalIDFactory.STRING_ID_MAX_LENGTH` = 40 - see below |
| `long_running_job.id` | 40 | `GlobalIDFactory.STRING_ID_MAX_LENGTH` |
| `secrole.id` | 45 | `IRole.ROLE_ID_MAX_LENGTH` |
| `secusergroup.id` | 45 | `IUserGroup.USER_GROUP_ID_MAX_LENGTH` |
| `secusertoken.id` | 45 | `IUserToken.USER_TOKEN_ID_MAX_LENGTH` |
| `audit.actiontype` | 10 | `EAuditActionType.MAX_ID_LENGTH` |
| `long_running_job.job_type` | 100 | `ILongRunningJob.JOB_TYPE_MAX_LENGTH` |
| `secuser.pwalgo` | 100 | `PasswordHash.ALGORITHM_NAME_MAX_LENGTH` |
| `secuser.loginname`, `secuser.email` | 200 | `IUser.LOGIN_NAME_MAX_LENGTH`, `IUser.EMAIL_ADDRESS_MAX_LENGTH` |
| `secuser.locale` | 20 | `IUser.DESIRED_LOCALE_MAX_LENGTH` |
| `secrole.name`, `secusergroup.name` | 255 | `IRole.ROLE_NAME_MAX_LENGTH`, `IUserGroup.USER_GROUP_NAME_MAX_LENGTH` |
| `sys_migration.migration_id` | 256 | `ISystemMigrationManager.MIGRATION_ID_MAX_LENGTH` |
| `sys_message.messagetype` | 1 | `ESystemMessageType` IDs are a single character |

**The user ID columns are the one exception to that rule**: they are 45
characters wide although the managers trim to 40, so that every column holding a
user ID has the same width as the `id` columns of `secrole`, `secusergroup` and
`secusertoken`, and so that raising `GlobalIDFactory.STRING_ID_MAX_LENGTH` again
does not immediately require another migration. Widening was done by V3; the
baseline still creates them with the narrower widths of ph-oton 10.4.0.

**Not derived from the code**: `secuser.pwsalt`, `secuser.pwhash`,
`secuser.firstname` and `secuser.lastname` are written without any length
restriction, so they use the unbounded text type of the respective dialect
(`LONGTEXT`, `TEXT`, `CLOB(16M)`, `NVARCHAR(MAX)`, `CLOB`) - the same as
phoss-SMP does. A `VARCHAR(255)` would be big enough for the default password
hashing (a 32 byte salt is 64 characters hex encoded), but it silently breaks
as soon as an application stores longer first / last names.

The `audit.id`, respectively surrogate key, columns are not derived from the
code either - see the first caveat below.

`NOT NULL` is only used where the Java side guarantees a value. Everything
else is nullable, which is the default in all five dialects and therefore
written without an explicit `NULL` keyword.

## Caveats

* **No foreign keys.** `secusergroup.userids` / `secusergroup.roleids` and
  `secusertoken.userid` reference other tables logically, but the managers
  neither create nor rely on referential integrity. Adding foreign keys would
  break the delete handling, which is a soft delete via `deletedt`.
* **`loginname` is not unique in the database.** Uniqueness is checked in
  `UserManagerJDBC` and only applies to non-deleted users, so a `UNIQUE`
  constraint would reject legitimate data.
* **`LIMIT` is used by the audit reader.** `AuditorJDBC.getLastAuditItems` and
  `getEarliestAuditDate` emit `... LIMIT ?` respectively `... LIMIT 1`. That
  syntax works on MySQL and PostgreSQL, but not on DB2, SQL Server or Oracle,
  which need `FETCH FIRST n ROWS ONLY` respectively `TOP n`. The DDL in this
  folder is fine on all five, but those two read methods are currently not
  portable.
* **`audit.id` is not written by ph-oton.** `AuditorJDBC` neither inserts
  nor selects it, so it is purely a database side surrogate key. It exists
  because `dt` plus `userid` is not unique - the same user can be audited more
  than once within the same timestamp - and because a table without a primary
  key is a problem for MySQL Group Replication / Galera and for DB2
  replication. phoss-SMP has had exactly this column since its SMP 5.2 schema.
* **Boolean columns.** SQL Server uses `BIT`, MySQL and PostgreSQL use
  `BOOLEAN`. DB2 uses `SMALLINT` (0 / 1) - `BOOLEAN` would need LUW 11.1+ and
  `SMALLINT` is what phoss-SMP runs in production. Oracle has no native
  boolean column type before 23ai, so `NUMBER(1)` is used. Reading a number
  back as a boolean always works, because ph-commons registers a `Number` to
  `Boolean` converter (`intValue () != 0`). Writing a `java.lang.Boolean`
  parameter into a `NUMBER(1)` / `SMALLINT` column is proven to work with the
  `ojdbc` and DB2 drivers that phoss-SMP uses.
* **`job_type` is redundant.** The value is also contained in the `job_data`
  XML of the same row. The column exists so that the job type can be filtered
  in SQL (`WHERE job_type=?`) without parsing the XML.
* **`long_running_job.id` is a persistent ID.** It comes from
  `GlobalIDFactory.getNewPersistentStringID ()` via
  `LongRunningJobManager.onStartJob`, so the application must have a persistent
  ID factory registered.
* **Table names.** The scripts use the default table names. All
  `*ManagerJDBC` constructors take a table name customizer
  (`Function <String, String>`); if your application prefixes or renames
  tables, adapt the scripts accordingly.
* **Only the MySQL and the PostgreSQL scripts have been executed as they
  stand** - V1, V2 and V3 were run in that order against a MySQL 9.6 and a
  PostgreSQL 18 container, and the resulting column definitions were verified.
  The DB2, Oracle and SQL Server scripts were derived from the SQL statements in
  `ph-oton-jdbc` and cross checked against the phoss-SMP schema (see below), but
  they were never run against a live database. Please review them before
  applying them to a production system.

## Cross check against phoss-SMP

[phoss-SMP](https://github.com/phax/phoss-SMP) installs the ph-oton JDBC
managers via `PhotonBasicManagerFactoryJDBC` / `PhotonSecurityManagerFactoryJDBC`
with a table name customizer that prefixes every table with `smp_`, and it has
been running that schema on MySQL, PostgreSQL, Oracle, DB2 and SQL Server since
SMP 5.2. Its Flyway scripts in `phoss-smp-backend-sql/src/main/resources/db/`
are therefore the closest thing to a field test of this DDL.

Adopted from there: the `audit` surrogate key, `SMALLINT` instead of `BOOLEAN`
on DB2, and the unbounded text types for `secuser.pwsalt` / `pwhash` /
`firstname` / `lastname`.

The user ID columns agree since phoss-SMP 8.4.0: its migration `V36` widens
`smp_audit.userid` and the `creationuserid` / `lastmoduserid` / `deleteuserid`
of all four security tables from `VARCHAR(20)` to `VARCHAR(45)`, which is what
V3 does here, and `smp_secuser.id` / `smp_secusertoken.userid` were `VARCHAR(45)`
there from the beginning.

Deliberately **not** adopted, because the phoss-SMP schema predates the
constants that `ph-oton-jdbc` trims to today:

| Column(s) | phoss-SMP | Here | Reason |
|---|---|---|---|
| `long_running_job.id` | `VARCHAR(45)` | `VARCHAR(40)` | `GlobalIDFactory.STRING_ID_MAX_LENGTH` is 40, and it is not a user ID |
| `secuser.logincount`, `secuser.failedlogins` | nullable | `NOT NULL` | `UserManagerJDBC` always writes a value; phoss-SMP allows `NULL` because of its own migration from a pre-ph-oton user table |
| `secuser.pwsalt` | `NOT NULL` | nullable | `PasswordHash.getSaltAsString ()` may return `null` |
| MySQL character set and timestamps | `utf8`, `datetime` | `utf8mb4`, `DATETIME(6)` | `utf8` is the 3 byte legacy encoding, and second resolution loses the ordering of audit entries |
| SQL Server string columns | `varchar` | `NVARCHAR` | `NVARCHAR` does not depend on the database collation being UTF-8 |
| `idx_lrj_start_dt`, `idx_secuser_loginname` | mostly missing | present | all reads of `long_running_job` are `ORDER BY start_dt`, and `UserManagerJDBC` looks users up by `loginname` |

phoss-SMP also has the additional tables `smp_settings` and `tomcat_sessions`.
Those are not part of ph-oton - the first belongs to `SMPSettingsManagerJDBC`,
the second to Tomcat's `PersistentManager`.

One lesson from there is already reflected above: phoss-SMP originally created
`long_running_job.job_data` as MySQL `text`, which overflows at 64 KB, and had
to widen it to `MEDIUMTEXT` in a later migration. The MySQL script in this
folder uses `LONGTEXT` from the start.
