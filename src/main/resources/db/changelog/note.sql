CREATE TABLE notes
(
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    user_id INT8      NOT NULL,
    title   TEXT,
    notes   TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);