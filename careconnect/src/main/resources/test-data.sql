INSERT INTO users (username, password, enabled) VALUES ('admin', '{noop}adminpass', true);
INSERT INTO users (username, password, enabled) VALUES ('user', '{noop}userpass', true);

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);