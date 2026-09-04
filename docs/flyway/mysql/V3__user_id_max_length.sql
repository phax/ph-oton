--
-- ph-oton - widen all user ID columns to 45 characters (v10.5.0)
-- MySQL 8.x / MariaDB 10.x
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


-- Note: MySQL replaces the whole column definition, so 'NOT NULL' must be
-- repeated where it applies
ALTER TABLE audit        MODIFY userid         VARCHAR(45) NOT NULL;
ALTER TABLE secrole      MODIFY creationuserid VARCHAR(45);
ALTER TABLE secrole      MODIFY lastmoduserid  VARCHAR(45);
ALTER TABLE secrole      MODIFY deleteuserid   VARCHAR(45);
ALTER TABLE secusergroup MODIFY creationuserid VARCHAR(45);
ALTER TABLE secusergroup MODIFY lastmoduserid  VARCHAR(45);
ALTER TABLE secusergroup MODIFY deleteuserid   VARCHAR(45);
ALTER TABLE secuser      MODIFY creationuserid VARCHAR(45);
ALTER TABLE secuser      MODIFY lastmoduserid  VARCHAR(45);
ALTER TABLE secuser      MODIFY deleteuserid   VARCHAR(45);
ALTER TABLE secusertoken MODIFY creationuserid VARCHAR(45);
ALTER TABLE secusertoken MODIFY lastmoduserid  VARCHAR(45);
ALTER TABLE secusertoken MODIFY deleteuserid   VARCHAR(45);

-- The primary key of 'secuser' and the user reference of 'secusertoken' hold a
-- user ID as well - these two were VARCHAR(20)
ALTER TABLE secuser      MODIFY id             VARCHAR(45) NOT NULL;
ALTER TABLE secusertoken MODIFY userid         VARCHAR(45);
