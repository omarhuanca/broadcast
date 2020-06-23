/* **********************************************
 * TRIGGERS (Views)                             *
 ************************************************/

/* **********************************************
 * FUNCTIONS (Views)                            *
 ************************************************/

/* **********************************************
 * VIEWS                                        *
 ************************************************/

/* **********************************************
 * TABLES                                       *
 ************************************************/
DROP TABLE IF EXISTS adus_user;
DROP TABLE IF EXISTS adcp_company;

DROP TABLE IF EXISTS msbm_broadcast_message;
DROP TABLE IF EXISTS msss_subscription;
DROP TABLE IF EXISTS msat_attach;
DROP TABLE IF EXISTS msms_message;
DROP TABLE IF EXISTS mscm_class_message;
DROP TABLE IF EXISTS mscc_class_channel;
DROP TABLE IF EXISTS mssb_subscriber;
DROP TABLE IF EXISTS msct_category;

/* **********************************************
 * TYPES                                        *
 ************************************************/

/* **********************************************
 * SEQUENCES                                    *
 ************************************************/
DROP SEQUENCE IF EXISTS adcp_seq;
DROP SEQUENCE IF EXISTS adus_seq;

DROP SEQUENCE IF EXISTS msbm_seq;
DROP SEQUENCE IF EXISTS msss_seq;
DROP SEQUENCE IF EXISTS msat_seq;
DROP SEQUENCE IF EXISTS msms_seq;
DROP SEQUENCE IF EXISTS mscm_seq;
DROP SEQUENCE IF EXISTS mscc_seq;
DROP SEQUENCE IF EXISTS mssb_seq;
DROP SEQUENCE IF EXISTS msct_seq;
