CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS account (
  id                        VARCHAR(60) DEFAULT gen_random_uuid() PRIMARY KEY,
  platform                  VARCHAR     NOT NULL,
  username                  VARCHAR     NOT NULL,
  password                  VARCHAR     NOT NULL,
  wallet                    VARCHAR(60) NOT NULL,
  insert_time               TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS wallet (
  id                        VARCHAR(60) DEFAULT gen_random_uuid() PRIMARY KEY,
  seed                      VARCHAR    NOT NULL,
  insert_time               TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS token (
  token_id                  VARCHAR     PRIMARY KEY,
  token_name                VARCHAR     NOT NULL,
  decimals                  INTEGER     NOT NULL,
  insert_time               TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction (
  id                        VARCHAR(60) DEFAULT gen_random_uuid() PRIMARY KEY,
  from_display              VARCHAR NULL,
  from_account              VARCHAR(60) NOT NULL,
  from_wallet               VARCHAR(60) NOT NULL,
  to_display                VARCHAR NULL,
  to_account                VARCHAR(60) NOT NULL,
  to_wallet                 VARCHAR(60) NOT NULL,
  amount                    BIGINT NOT NULL,
  token                     VARCHAR NULL,
  token_amount              FLOAT NULL,
  succeeded                 CHAR(1) NOT NULL,
  comment                   VARCHAR NULL,
  insert_time               TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);