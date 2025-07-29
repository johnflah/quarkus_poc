-- V1__create_car_table.sql

CREATE TABLE car (
                     id SERIAL PRIMARY KEY,
                     make VARCHAR(100) NOT NULL,
                     model VARCHAR(100) NOT NULL,
                     year INTEGER NOT NULL CHECK (year > 1885), -- first car invented ~1886
    colour VARCHAR(50) NOT NULL
);
