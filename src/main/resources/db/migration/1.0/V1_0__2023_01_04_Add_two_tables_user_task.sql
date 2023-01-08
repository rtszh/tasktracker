CREATE TABLE users
-- CREATE TABLE user
(
    id    BIGSERIAL NOT NULL PRIMARY KEY,
    login VARCHAR(50) UNIQUE
);

CREATE TABLE task
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    title       VARCHAR(50),
    description VARCHAR(255),
    user_id     BIGINT,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);