CREATE TABLE contacts
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    mail   TEXT NOT NULL,
    message   TEXT NOT NULL
);