INSERT INTO aircraft(id, model, serial_number) values
(1, 'Phenom 100', '123'),
(2, 'Phenom 300', '456'),
(3, 'Phenom 600', '789')
;

INSERT INTO airport(id, code, location, country) values
(1, 'ATL', 'Atlanta, Georgia', 'United States'), 
(2, 'PEK', 'Chaoyang-Shunyi, Beijing', 'China'), 
(3, 'DXB', 'Garhoud, Dubai', 'United Arab Emirates'), 
(4, 'LAX', 'Los Angeles, California', 'United States'), 
(5, 'HND', 'Ōta, Tokyo', 'Japan'), 
(6, 'GRU', 'Guarulhos, São Paulo', 'Brazil')
;

INSERT INTO pilot(id, first_name, last_name) values
(1, 'Vinicius', 'Sanches'),
(2, 'Fulano', 'Silva')
;

INSERT INTO airline(id, name) values
(1, 'TAM'),
(2, 'Azul'),
(3, 'Gol')
;

INSERT INTO flight(id, airline_id, pilot_id, aircraft_id, origin_id, destination_id,
            departure_date, arrival_date, status) values
(1, 1, 1, 1, 1, 2, '2017-01-01 10:00:00', '2017-01-01 11:00:00', 1),
(2, 2, 1, 2, 2, 1, '2017-02-01 11:00:00', '2017-01-01 12:00:00', 2),
(3, 2, 2, 2, 2, 1, '2017-03-02 13:00:00', '2017-01-02 14:00:00', 3),
;