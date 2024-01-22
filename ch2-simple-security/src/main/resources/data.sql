INSERT INTO authorities (username, authority) VALUES ('yul', 'write');
INSERT INTO authorities (username, authority) VALUES ('joel', 'read');
INSERT INTO authorities (username, authority) VALUES ('berry', 'read');

INSERT INTO users (username, password, enabled) VALUES ('yul', 'yul', true);
INSERT INTO users (username, password, enabled) VALUES ('joel', 'joel', true);
INSERT INTO users (username, password, enabled) VALUES ('berry', 'berry', false);