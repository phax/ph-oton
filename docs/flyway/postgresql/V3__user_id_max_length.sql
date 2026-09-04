--
-- ph-oton - widen all user ID columns to 45 characters (v10.5.0)
-- PostgreSQL 12+
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


ALTER TABLE audit        ALTER COLUMN userid         TYPE VARCHAR(45);
ALTER TABLE secrole      ALTER COLUMN creationuserid TYPE VARCHAR(45);
ALTER TABLE secrole      ALTER COLUMN lastmoduserid  TYPE VARCHAR(45);
ALTER TABLE secrole      ALTER COLUMN deleteuserid   TYPE VARCHAR(45);
ALTER TABLE secusergroup ALTER COLUMN creationuserid TYPE VARCHAR(45);
ALTER TABLE secusergroup ALTER COLUMN lastmoduserid  TYPE VARCHAR(45);
ALTER TABLE secusergroup ALTER COLUMN deleteuserid   TYPE VARCHAR(45);
ALTER TABLE secuser      ALTER COLUMN creationuserid TYPE VARCHAR(45);
ALTER TABLE secuser      ALTER COLUMN lastmoduserid  TYPE VARCHAR(45);
ALTER TABLE secuser      ALTER COLUMN deleteuserid   TYPE VARCHAR(45);
ALTER TABLE secusertoken ALTER COLUMN creationuserid TYPE VARCHAR(45);
ALTER TABLE secusertoken ALTER COLUMN lastmoduserid  TYPE VARCHAR(45);
ALTER TABLE secusertoken ALTER COLUMN deleteuserid   TYPE VARCHAR(45);

-- The primary key of 'secuser' and the user reference of 'secusertoken' hold a
-- user ID as well - these two were VARCHAR(20)
ALTER TABLE secuser      ALTER COLUMN id             TYPE VARCHAR(45);
ALTER TABLE secusertoken ALTER COLUMN userid         TYPE VARCHAR(45);
