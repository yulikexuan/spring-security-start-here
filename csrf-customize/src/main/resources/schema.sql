DROP TABLE IF EXISTS csrftoken CASCADE;

CREATE TABLE IF NOT EXISTS csrf_token (
    id SERIAL PRIMARY KEY,
    identifier VARCHAR(45) NULL,
    token TEXT NULL);