-- R__insert_demo_car_data.sql

-- Clean out existing demo data
DELETE FROM car;

-- Insert fresh demo data
INSERT INTO car (make, model, year, colour) VALUES
                                                ('Toyota', 'Corolla', 2020, 'Silver'),
                                                ('Ford', 'Mustang', 2018, 'Red'),
                                                ('Tesla', 'Model 3', 2021, 'White'),
                                                ('Honda', 'Civic', 2019, 'Blue');
