# Currency Exchange Application

This is an application for converting currency. It uses Spring Boot for the backend with JPA connecting to a PostgreSQL server.
It also uses Angular with Bootstrap for the frontend.  

The database is initialized with static values for the Hungarian Forint exchange rates at the start of the backend app.
The rates can be edited in the data.sql file.

## How to run

The frontend, backend and database server run independent of each other.

- create a database manually or run currency-db, to start the PostgreSQL database from Docker container. (download postgres:14.3-alpine image from docker hub beforehand)
- start the backend api from a development environment or by running docker_build and currency-api_run, to build and run
the backend from Docker container.
- start the frontend from a development environment with Angular CLI, after installing the necessary packages for Angular.
- open a web client and navigate to http://localhost:4200/ for the client interface.