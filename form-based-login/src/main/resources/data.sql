INSERT INTO authorities (name, auth) VALUES ('yul', 'write');
INSERT INTO authorities (name, auth) VALUES ('joel', 'read');
INSERT INTO authorities (name, auth) VALUES ('berry', 'read');
INSERT INTO authorities (name, auth) VALUES ('tecuser', 'write');
INSERT INTO authorities (name, auth) VALUES ('tecsys', 'write');

INSERT INTO users (name, pw, active) VALUES ('yul', 'yul', true);
INSERT INTO users (name, pw, active) VALUES ('joel', 'joel', true);
INSERT INTO users (name, pw, active) VALUES ('berry', 'berry', false);
INSERT INTO users (name, pw, active) VALUES ('tecuser', 'tecuser', true);
INSERT INTO users (name, pw, active) VALUES ('tecsys', 'tecsys', true);