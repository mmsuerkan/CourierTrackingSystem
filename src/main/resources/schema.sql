CREATE TABLE IF NOT EXISTS STORES
(
    id   SERIAL  PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lat  DOUBLE PRECISION NOT NULL,
    long DOUBLE PRECISION NOT NULL
);