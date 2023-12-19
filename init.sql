CREATE TABLE stores
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)     NOT NULL,
    lat  DOUBLE PRECISION NOT NULL,
    lng  DOUBLE PRECISION NOT NULL
);



INSERT INTO stores (name, lat, lng)
VALUES ('Ataşehir MMM Migros', 40.9923307, 29.1244229),
       ('Novada MMM Migros', 40.986106, 29.1161293),
       ('Beylikdüzü 5M Migros', 41.0066851, 28.6552262),
       ('Ortaköy MMM Migros', 41.055783, 29.0210292),
       ('Caddebostan MMM Migros', 40.9632463, 29.0630908);


