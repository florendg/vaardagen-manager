CREATE EXTENSION if not exists "uuid-ossp";
DROP TABLE  if exists TRIP_LOG;

CREATE TABLE TRIP_LOG
(
    id                UUID default uuid_generate_v4() primary key,
    TRIP_NUMBER       INTEGER,
    DEPARTURE_HARBOUR VARCHAR(100),
    DEPARTURE_DATE    DATE,
    ARRIVAL_HARBOUR   VARCHAR(100),
    ARRIVAL_DATE      DATE
);