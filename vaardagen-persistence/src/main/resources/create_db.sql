DROP database if exists TRIP_LOG_DB;
DROP role if exists trip_log_user;
DROP EXTENSION if exists "uuid-ossp";

CREATE EXTENSION "uuid-ossp";

--- Script to setup the database
CREATE ROLE trip_log_user WITH LOGIN PASSWORD 'demo';

CREATE DATABASE TRIP_LOG_DB OWNER trip_log_user;


