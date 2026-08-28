--
-- ph-oton - add the job type to the long running job results (v10.4.0)
-- MySQL 8.x / MariaDB 10.x
--
-- Upgrades the 'long_running_job' table of V1__ph_oton_baseline.sql (ph-oton
-- 10.3.x) to ph-oton 10.4.0. Fresh and existing installations alike run V1 and
-- V2 in that order.
--
-- An installation whose schema already exists but was never managed by Flyway
-- should run 'flyway baseline -baselineVersion=1' first, so that only V2 is
-- applied to it.
--
-- Rows that already exist keep an empty job type, and therefore never match a
-- job type filter in
-- ILongRunningJobResultManager.forEachJobResult (String, Consumer).
--


ALTER TABLE long_running_job ADD COLUMN job_type VARCHAR(100);
CREATE INDEX idx_lrj_job_type ON long_running_job (job_type);
