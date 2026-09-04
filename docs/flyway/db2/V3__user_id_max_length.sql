--
-- ph-oton - widen all user ID columns to 45 characters (v10.5.0)
-- IBM DB2 LUW 11.1+
--
-- Upgrades the schema of V1__ph_oton_baseline.sql plus
-- V2__long_running_job_add_job_type.sql (ph-oton 10.4.0) to ph-oton 10.5.0.
--
-- IUser.USER_ID_MAX_LENGTH was 20 and is GlobalIDFactory.STRING_ID_MAX_LENGTH
-- (40) now, so 'secuser.id' and 'secusertoken.userid' can overflow. Every
-- column that holds a user ID is therefore unified to 45 characters - the
-- width the ID columns of the other security tables already use, and the width
-- phoss-SMP uses for all of its user ID columns.
--
-- 'long_running_job.id' deliberately stays at 40 characters: it holds a
-- persistent ID created by GlobalIDFactory, it is not a user ID.
--
-- Only the length of the columns is changed - no column is added, removed or
-- renamed, and the nullability stays as it was, so existing data is preserved.
--
-- An installation whose schema already exists but was never managed by Flyway
-- should run 'flyway baseline -baselineVersion=2' first, so that only V3 is
-- applied to it.
--


-- All columns of a table are changed in a single ALTER TABLE, because DB2 only
-- allows a limited number of REORG recommended operations on a table before a
-- REORG is required
ALTER TABLE audit
  ALTER COLUMN userid         SET DATA TYPE VARCHAR(45);
ALTER TABLE secrole
  ALTER COLUMN creationuserid SET DATA TYPE VARCHAR(45)
  ALTER COLUMN lastmoduserid  SET DATA TYPE VARCHAR(45)
  ALTER COLUMN deleteuserid   SET DATA TYPE VARCHAR(45);
ALTER TABLE secusergroup
  ALTER COLUMN creationuserid SET DATA TYPE VARCHAR(45)
  ALTER COLUMN lastmoduserid  SET DATA TYPE VARCHAR(45)
  ALTER COLUMN deleteuserid   SET DATA TYPE VARCHAR(45);
-- The primary key of 'secuser' and the user reference of 'secusertoken' hold a
-- user ID as well - these two were VARCHAR(20)
ALTER TABLE secuser
  ALTER COLUMN id             SET DATA TYPE VARCHAR(45)
  ALTER COLUMN creationuserid SET DATA TYPE VARCHAR(45)
  ALTER COLUMN lastmoduserid  SET DATA TYPE VARCHAR(45)
  ALTER COLUMN deleteuserid   SET DATA TYPE VARCHAR(45);
ALTER TABLE secusertoken
  ALTER COLUMN creationuserid SET DATA TYPE VARCHAR(45)
  ALTER COLUMN lastmoduserid  SET DATA TYPE VARCHAR(45)
  ALTER COLUMN deleteuserid   SET DATA TYPE VARCHAR(45)
  ALTER COLUMN userid         SET DATA TYPE VARCHAR(45);

-- DB2 puts a table into 'reorg pending' state after ALTER COLUMN SET DATA TYPE.
-- Run the following as an administrator afterwards - inserts into the table
-- fail until it was done:
CALL SYSPROC.ADMIN_CMD ('REORG TABLE audit');
CALL SYSPROC.ADMIN_CMD ('REORG TABLE secrole');
CALL SYSPROC.ADMIN_CMD ('REORG TABLE secusergroup');
CALL SYSPROC.ADMIN_CMD ('REORG TABLE secuser');
CALL SYSPROC.ADMIN_CMD ('REORG TABLE secusertoken');
