/*
 *==========================================================================
 * DBMS name:  PostgreSQL Version 9.6
 * Created on: April 2020
 * Reviewed on: April 2020
 * Version:    1.0
 *==========================================================================
 * Initial tables for broadcast database, it includes tables for:
 */

/* **********************************************
 * SEQUENCES                                    *
 ************************************************/
CREATE SEQUENCE adcp_seq START WITH 11 INCREMENT BY 1;
CREATE SEQUENCE adus_seq START WITH 11 INCREMENT BY 1;

CREATE SEQUENCE mscc_seq;
CREATE SEQUENCE mssb_seq;
CREATE SEQUENCE msct_seq;
CREATE SEQUENCE mscm_seq;
CREATE SEQUENCE msms_seq;
CREATE SEQUENCE msat_seq;
CREATE SEQUENCE msbm_seq;
CREATE SEQUENCE msss_seq;

/* **********************************************
 * TYPES                                        *
 ************************************************/

/* **********************************************
 * TABLES                                       *
 ************************************************/

/*==============================================================*/
/* Table: Company                                               */
/* Module: Administrator Management (ADMG).                     */
/*==============================================================*/
CREATE TABLE adcp_company (
  adcp_uid              BIGINT        NOT NULL,
  adcp_status           INTEGER       NOT NULL,
  adcp_name             VARCHAR(30)   NOT NULL,
  adcp_address          VARCHAR(40)   NOT NULL,
  adcp_contact_name     VARCHAR(40)   NOT NULL,
  adcp_contact_phone    VARCHAR(20)   NOT NULL,
  adcp_contact_email    VARCHAR(40)   NOT NULL,
  adcp_notes            VARCHAR(500)      NULL,
  adcp_create_date      TIMESTAMP     NOT NULL,
  adcp_last_update_date TIMESTAMP     NOT NULL
);

ALTER TABLE adcp_company
  ALTER COLUMN   adcp_uid              SET DEFAULT nextval('adcp_seq'),
  ALTER COLUMN   adcp_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   adcp_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   adcp_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_adcp_uid PRIMARY KEY (adcp_uid);

/*==============================================================*/
/* Table: User                                                  */
/* Module: Administrator Management (ADMG).                     */
/*==============================================================*/
CREATE TABLE adus_user (
  adus_uid              BIGINT        NOT NULL,
  adus_adcp_uid         BIGINT        NOT NULL,
  adus_status           INTEGER       NOT NULL,
  adus_ci               VARCHAR(11)   NOT NULL,
  adus_first_names      VARCHAR(70)   NOT NULL,
  adus_last_names       VARCHAR(70)   NOT NULL,
  adus_address          VARCHAR(500)  NOT NULL,
  adus_mobile_phone     VARCHAR(15)   NOT NULL,
  adus_phone_numbers    VARCHAR(70)       NULL,
  adus_email            VARCHAR(70)       NULL,
  adus_password         VARCHAR(70)   NOT NULL,
  adus_level            INTEGER       NOT NULL,
  adus_job_title        VARCHAR(40)   NOT NULL,
  adus_salary           NUMERIC(9, 2) NOT NULL,
  adus_create_date      TIMESTAMP     NOT NULL,
  adus_last_update_date TIMESTAMP     NOT NULL
);

ALTER TABLE adus_user
  ALTER COLUMN   adus_uid              SET DEFAULT nextval('adus_seq'),
  ALTER COLUMN   adus_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   adus_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   adus_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_adus_uid      PRIMARY KEY (adus_uid),
  ADD CONSTRAINT fk_adus_adcp_uid FOREIGN KEY (adus_adcp_uid) REFERENCES adcp_company(adcp_uid) ON UPDATE CASCADE ON DELETE CASCADE;

/*==============================================================*/
/* Table: Class Channel (mscc)                                  */
/* Module: Message Management (MSMG).                           */
/*==============================================================*/
CREATE TABLE mscc_class_channel (
  mscc_uid              BIGINT       NOT NULL,
  mscc_status           INTEGER      NOT NULL,
  mscc_name             VARCHAR(80)  NOT NULL,
  mscc_create_date      TIMESTAMP    NOT NULL,
  mscc_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE mscc_class_channel
  ALTER COLUMN   mscc_uid              SET DEFAULT nextval('mscc_seq'),
  ALTER COLUMN   mscc_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   mscc_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   mscc_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_mscc_uid      PRIMARY KEY (mscc_uid);

/*==============================================================*/
/* Table: Subscriber (mssb)                                     */
/* Module: Message Management (MSMG).                           */
/*==============================================================*/
CREATE TABLE mssb_subscriber (
  mssb_uid              BIGINT       NOT NULL,
  mssb_status           INTEGER      NOT NULL,
  mssb_first_name       VARCHAR(80)  NOT NULL,
  mssb_last_name        VARCHAR(80)  NOT NULL,
  mssb_email            VARCHAR(100) NOT NULL,
  mssb_cellphone        VARCHAR(15)  NOT NULL,
  mssb_create_date      TIMESTAMP    NOT NULL,
  mssb_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE mssb_subscriber
  ALTER COLUMN   mssb_uid              SET DEFAULT nextval('mssb_seq'),
  ALTER COLUMN   mssb_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   mssb_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   mssb_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_mssb_uid      PRIMARY KEY (mssb_uid);

/*==============================================================*/
/* Table: Category (msct)                                       */
/* Module: Message Management (MSMG).                           */
/*==============================================================*/
CREATE TABLE msct_category (
  msct_uid              BIGINT       NOT NULL,
  msct_status           INTEGER      NOT NULL,
  msct_name             VARCHAR(80)  NOT NULL,
  msct_create_date      TIMESTAMP    NOT NULL,
  msct_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE msct_category
  ALTER COLUMN   msct_uid              SET DEFAULT nextval('msct_seq'),
  ALTER COLUMN   msct_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   msct_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   msct_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_msct_uid      PRIMARY KEY (msct_uid);

/*==============================================================*/
/* Table: Subscription (msss)                                   */
/* Module: Message Management (MSMG).                           */
/*==============================================================*/
CREATE TABLE msss_subscription (
  msss_uid              BIGINT       NOT NULL,
  msss_mscc_uid         BIGINT       NOT NULL,
  msss_mssb_uid         BIGINT       NOT NULL,
  msss_msct_uid         BIGINT       NOT NULL,
  msss_status           INTEGER      NOT NULL,
  msss_create_date      TIMESTAMP    NOT NULL,
  msss_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE msss_subscription
  ALTER COLUMN   msss_uid              SET DEFAULT nextval('msss_seq'),
  ALTER COLUMN   msss_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   msss_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   msss_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_msss_uid      PRIMARY KEY (msss_uid),
  ADD CONSTRAINT fk_msss_mscc_uid FOREIGN KEY (msss_mscc_uid) REFERENCES mscc_class_channel(mscc_uid) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT fk_msss_mssb_uid FOREIGN KEY (msss_mssb_uid) REFERENCES mssb_subscriber(mssb_uid) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT fk_msss_msct_uid FOREIGN KEY (msss_msct_uid) REFERENCES msct_category(msct_uid) ON UPDATE CASCADE ON DELETE CASCADE;

/*==============================================================*/
/* Table: Class Message (mscm)                                  */
/* Module: Supplier Management (SPMG).                          */
/*==============================================================*/
CREATE TABLE mscm_class_message (
  mscm_uid              BIGINT       NOT NULL,
  mscm_status           INTEGER      NOT NULL,
  mscm_name             VARCHAR(80)  NOT NULL,
  mscm_create_date      TIMESTAMP    NOT NULL,
  mscm_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE mscm_class_message
  ALTER COLUMN   mscm_uid              SET DEFAULT nextval('mscm_seq'),
  ALTER COLUMN   mscm_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   mscm_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   mscm_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_mscm_uid      PRIMARY KEY (mscm_uid);

/*==============================================================*/
/* Table: Message (msms)                                        */
/* Module: Message Management (MSMG).                           */
/*==============================================================*/
CREATE TABLE msms_message (
  msms_uid              BIGINT       NOT NULL,
  msms_mscm_uid         BIGINT       NOT NULL,
  msms_status           INTEGER      NOT NULL,
  msms_title            VARCHAR(80)  NOT NULL,
  msms_body             VARCHAR(160) NOT NULL,
  msms_create_date      TIMESTAMP    NOT NULL,
  msms_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE msms_message
  ALTER COLUMN   msms_uid              SET DEFAULT nextval('msms_seq'),
  ALTER COLUMN   msms_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   msms_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   msms_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_msms_uid      PRIMARY KEY (msms_uid),
  ADD CONSTRAINT fk_msms_mscm_uid FOREIGN KEY (msms_mscm_uid) REFERENCES mscm_class_message(mscm_uid) ON UPDATE CASCADE ON DELETE CASCADE;

/*==============================================================*/
/* Table: Attach (msat)                                         */
/* Module: Message Management (MSMG).                           */
/*==============================================================*/
CREATE TABLE msat_attach (
  msat_uid              BIGINT       NOT NULL,
  msat_msms_uid         BIGINT       NOT NULL,
  msat_status           INTEGER      NOT NULL,
  msat_url_file         VARCHAR(255)  NOT NULL,
  msat_name_file        VARCHAR(255) NOT NULL,
  msat_create_date      TIMESTAMP    NOT NULL,
  msat_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE msat_attach
  ALTER COLUMN   msat_uid              SET DEFAULT nextval('msat_seq'),
  ALTER COLUMN   msat_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   msat_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   msat_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_msat_uid      PRIMARY KEY (msat_uid),
  ADD CONSTRAINT fk_msat_msms_uid FOREIGN KEY (msat_msms_uid) REFERENCES msms_message(msms_uid) ON UPDATE CASCADE ON DELETE CASCADE;

/*==============================================================*/
/* Table: Broadcast Message (msbm)                              */
/* Module: Message Management (MSMG).                           */
/*==============================================================*/
CREATE TABLE msbm_broadcast_message (
  msbm_uid              BIGINT       NOT NULL,
  msbm_msct_uid         BIGINT       NOT NULL,
  msbm_msms_uid         BIGINT       NOT NULL,
  msbm_status           INTEGER      NOT NULL,
  msbm_create_date      TIMESTAMP    NOT NULL,
  msbm_last_update_date TIMESTAMP    NOT NULL
);

ALTER TABLE msbm_broadcast_message
  ALTER COLUMN   msbm_uid              SET DEFAULT nextval('msbm_seq'),
  ALTER COLUMN   msbm_create_date      SET DEFAULT NOW(),
  ALTER COLUMN   msbm_last_update_date SET DEFAULT NOW(),
  ALTER COLUMN   msbm_status           SET DEFAULT 1,
  ADD CONSTRAINT pk_msbm_uid      PRIMARY KEY (msbm_uid),
  ADD CONSTRAINT fk_msbm_msct_uid FOREIGN KEY (msbm_msct_uid) REFERENCES msct_category(msct_uid) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT fk_msbm_msms_uid FOREIGN KEY (msbm_msms_uid) REFERENCES msms_message(msms_uid) ON UPDATE CASCADE ON DELETE CASCADE;
