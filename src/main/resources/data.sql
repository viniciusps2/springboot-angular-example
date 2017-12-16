INSERT INTO aircraft(id, model, serial_number) values
    (1, 'Phenom 100', '123'),
    (2, 'Phenom 300', '456'),
    (3, 'Phenom 600', '789')
;

INSERT INTO city(id, name, state_code) values
    (1, 'Guarulhos', 'SP'),
    (2, 'Congonhas', 'SP')
;

INSERT INTO flight(id, aircraft_id, origin_city_id, destination_city_id,
                   departure_date, arrival_date, flight_status) values
    (1, 1, 1, 2, '2017-01-01 10:00:00', '2017-01-01 11:00:00', 1),
    (2, 1, 2, 1, '2017-01-01 11:00:00', '2017-01-01 12:00:00', 2),
    (3, 1, 2, 1, '2017-01-02 13:00:00', '2017-01-02 14:00:00', 3),
;