CREATE TABLE car
(
    id    INTEGER PRIMARY KEY ,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    cost  INTEGER
);

CREATE TABLE driver
(
    id                     INTEGER PRIMARY KEY,
    name                   TEXT NOT NULL DEFAULT 'Алеша',
    age                    INTEGER CHECK (age >= 18),
    is_have_driver_license BOOLEAN       DEFAULT (FALSE),
    car_id                 INTEGER REFERENCES car (id)
);
