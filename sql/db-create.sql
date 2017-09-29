DROP TABLE payments;
DROP TABLE statuses;
DROP TABLE credit_cards;
DROP TABLE accounts;
DROP TABLE users;
DROP TABLE roles;
CREATE DATABASE IF NOT EXISTS payments;
USE payments;
CREATE TABLE roles
(
  role_id INT PRIMARY KEY NOT NULL,
  name    VARCHAR(32)     NOT NULL
);
CREATE UNIQUE INDEX roles_role_id_uindex
  ON roles (role_id);
CREATE UNIQUE INDEX roles_name_uindex
  ON roles (name);
CREATE TABLE users
(
  login    VARCHAR(32) PRIMARY KEY NOT NULL,
  password VARCHAR(64)             NOT NULL,
  fullname VARCHAR(32)             NOT NULL,
  role_id  INT DEFAULT 2,
  CONSTRAINT users_roles_role_id_fk FOREIGN KEY (role_id) REFERENCES roles (role_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  blocked  BOOLEAN DEFAULT FALSE   NOT NULL
);
CREATE UNIQUE INDEX users_login_uindex
  ON users (login);
CREATE TABLE accounts
(
  account_id VARCHAR(8) PRIMARY KEY NOT NULL,
  balance    INT             NOT NULL DEFAULT 0,
  name       VARCHAR(32)     NOT NULL,
  userLogin VARCHAR(32)     NOT NULL,
  CONSTRAINT accounts_users_login_fk FOREIGN KEY (userLogin) REFERENCES users (login)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  blocked BOOLEAN DEFAULT FALSE  NOT NULL
);
CREATE UNIQUE INDEX accounts_account_id_uindex
  ON accounts (account_id);
CREATE TABLE credit_cards
(
  card_id    VARCHAR(16) PRIMARY KEY NOT NULL,
  account_id VARCHAR(8)             NOT NULL,
  password VARCHAR(4)        NOT NULL,
  CONSTRAINT credit_cards_accounts_account_id_fk FOREIGN KEY (account_id) REFERENCES accounts (account_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE UNIQUE INDEX credit_cards_card_id_uindex
  ON credit_cards (card_id);
CREATE TABLE payments
(
  paymentId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  date VARCHAR(16) NOT NULL,
  status VARCHAR(16) DEFAULT "prepared" NOT NULL,
  senderId VARCHAR(8) NOT NULL,
  receiverId VARCHAR(8) NOT NULL,
  balance INT NOT NULL,
  userLogin VARCHAR(32) NOT NULL,
  CONSTRAINT payments_users_login_fk FOREIGN KEY (userLogin) REFERENCES users (login) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE UNIQUE INDEX payments_paymentId_uindex ON payments (paymentId);