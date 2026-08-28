--
-- ph-oton - baseline database schema for the JDBC backends of ph-oton-jdbc
-- PostgreSQL 12+
--
-- Copyright (C) 2014-2026 Philip Helger (www.helger.com)
-- Licensed under the Apache License, Version 2.0
--
-- This is the schema of ph-oton 10.3.x. V2 upgrades it to 10.4.0, so both
-- scripts run in order - a fresh installation simply migrates from an empty
-- database. See ../../README.md for details, caveats and the mapping of every
-- column to its source code constant.
--
-- The table names are the defaults. If the application passes a table name
-- customizer to the *ManagerJDBC constructors, adapt the names accordingly.
--


-- Written by AuditorJDBC (ph-oton-audit / ph-oton-jdbc).
-- Append only. 'id' is a surrogate key that is never read by
-- AuditorJDBC - it exists so that the physical insertion order is
-- preserved and so that the table has a primary key at all, because
-- 'dt' and 'userid' may be recorded more than once.
CREATE TABLE audit (
  id         SERIAL,                                                    -- surrogate key, ensures the order of entry
  dt         TIMESTAMP NOT NULL,
  userid     VARCHAR(40) NOT NULL,                                      -- GlobalIDFactory.STRING_ID_MAX_LENGTH
  actiontype VARCHAR(10) NOT NULL,                                      -- EAuditActionType.MAX_ID_LENGTH
  success    BOOLEAN NOT NULL,
  action     TEXT,                                                      -- JSON array
  CONSTRAINT pk_audit PRIMARY KEY (id)
);
CREATE INDEX idx_audit_dt ON audit (dt);

-- Written by LongRunningJobResultManagerJDBC.
-- 'job_data' contains the whole job result as XML.
-- 'id' is the globally unique ID of a single job execution.
-- V2 adds the 'job_type' column of ph-oton 10.4.0.
CREATE TABLE long_running_job (
  id       VARCHAR(40) NOT NULL,                                        -- GlobalIDFactory.STRING_ID_MAX_LENGTH
  start_dt TIMESTAMP NOT NULL,
  job_data TEXT NOT NULL,                                               -- XML
  CONSTRAINT pk_long_running_job PRIMARY KEY (id)
);
CREATE INDEX idx_lrj_start_dt ON long_running_job (start_dt);

-- Written by SystemMigrationManagerJDBC.
-- No primary key - a migration ID may be recorded several times,
-- e.g. once per failed and once per successful attempt.
CREATE TABLE sys_migration (
  migration_id VARCHAR(256) NOT NULL,                                   -- ISystemMigrationManager.MIGRATION_ID_MAX_LENGTH
  execution_dt TIMESTAMP NOT NULL,
  success      BOOLEAN NOT NULL,
  error_msg    TEXT
);
CREATE INDEX idx_sysmig_migration_id ON sys_migration (migration_id);

-- Written by SystemMessageManagerJDBC.
-- Holds at most one row - the manager updates without a WHERE clause
-- and only inserts if the update affected no row.
CREATE TABLE sys_message (
  messagetype VARCHAR(1) NOT NULL,                                      -- ESystemMessageType ID is a single char
  lastupdate  TIMESTAMP,
  message     TEXT
);

-- Written by RoleManagerJDBC.
CREATE TABLE secrole (
  id             VARCHAR(45) NOT NULL,                                  -- IRole.ROLE_ID_MAX_LENGTH
  creationdt     TIMESTAMP,
  creationuserid VARCHAR(40),
  lastmoddt      TIMESTAMP,
  lastmoduserid  VARCHAR(40),
  deletedt       TIMESTAMP,
  deleteuserid   VARCHAR(40),
  attrs          TEXT,                                                  -- JSON object
  name           VARCHAR(255) NOT NULL,                                 -- IRole.ROLE_NAME_MAX_LENGTH
  description    TEXT,
  CONSTRAINT pk_secrole PRIMARY KEY (id)
);

-- Written by UserGroupManagerJDBC.
-- 'userids' and 'roleids' are JSON arrays of the contained IDs -
-- there are deliberately no foreign keys to secuser / secrole.
CREATE TABLE secusergroup (
  id             VARCHAR(45) NOT NULL,                                  -- IUserGroup.USER_GROUP_ID_MAX_LENGTH
  creationdt     TIMESTAMP,
  creationuserid VARCHAR(40),
  lastmoddt      TIMESTAMP,
  lastmoduserid  VARCHAR(40),
  deletedt       TIMESTAMP,
  deleteuserid   VARCHAR(40),
  attrs          TEXT,                                                  -- JSON object
  name           VARCHAR(255) NOT NULL,                                 -- IUserGroup.USER_GROUP_NAME_MAX_LENGTH
  description    TEXT,
  userids        TEXT,                                                  -- JSON array
  roleids        TEXT,                                                  -- JSON array
  CONSTRAINT pk_secusergroup PRIMARY KEY (id)
);

-- Written by UserManagerJDBC.
-- 'loginname' is only unique among the non-deleted users, and that
-- uniqueness is enforced in Java - hence no UNIQUE constraint here.
CREATE TABLE secuser (
  id             VARCHAR(20) NOT NULL,                                  -- IUser.USER_ID_MAX_LENGTH
  creationdt     TIMESTAMP,
  creationuserid VARCHAR(40),
  lastmoddt      TIMESTAMP,
  lastmoduserid  VARCHAR(40),
  deletedt       TIMESTAMP,
  deleteuserid   VARCHAR(40),
  attrs          TEXT,                                                  -- JSON object
  loginname      VARCHAR(200) NOT NULL,                                 -- IUser.LOGIN_NAME_MAX_LENGTH
  email          VARCHAR(200),                                          -- IUser.EMAIL_ADDRESS_MAX_LENGTH
  pwalgo         VARCHAR(100) NOT NULL,                                 -- PasswordHash.ALGORITHM_NAME_MAX_LENGTH
  pwsalt         TEXT,                                                  -- not limited in code
  pwhash         TEXT NOT NULL,                                         -- not limited in code
  firstname      TEXT,                                                  -- not limited in code
  lastname       TEXT,                                                  -- not limited in code
  description    TEXT,
  locale         VARCHAR(20),                                           -- IUser.DESIRED_LOCALE_MAX_LENGTH
  lastlogindt    TIMESTAMP,
  logincount     INTEGER NOT NULL,
  failedlogins   INTEGER NOT NULL,
  disabled       BOOLEAN NOT NULL,
  CONSTRAINT pk_secuser PRIMARY KEY (id)
);
CREATE INDEX idx_secuser_loginname ON secuser (loginname);

-- Written by UserTokenManagerJDBC.
-- 'accesstokens' is a JSON array of all access tokens of this user token.
CREATE TABLE secusertoken (
  id             VARCHAR(45) NOT NULL,                                  -- IUserToken.USER_TOKEN_ID_MAX_LENGTH
  creationdt     TIMESTAMP,
  creationuserid VARCHAR(40),
  lastmoddt      TIMESTAMP,
  lastmoduserid  VARCHAR(40),
  deletedt       TIMESTAMP,
  deleteuserid   VARCHAR(40),
  attrs          TEXT,                                                  -- JSON object
  accesstokens   TEXT,                                                  -- JSON array
  userid         VARCHAR(20),                                           -- IUser.USER_ID_MAX_LENGTH
  description    TEXT,
  CONSTRAINT pk_secusertoken PRIMARY KEY (id)
);
