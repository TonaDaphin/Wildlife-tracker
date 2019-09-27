# Wildlife Tracker
### Author: Tona Daphin
## Description
This is a Wildlife tracker application that allows wildlife reservation companies to keep track of animals by the help of rangers.

## Technologies used

1. Java 
2. Spark core 2.12
4. Spark Handlebars
5. Junit 5
6. Postgres database

##Database

CREATE TABLE animals(id SERIAL PRIMARY KEY,health varchar, age varchar, type varchar,name varchar);
CREATE TABLE locations(id SERIAL PRIMARY KEY, name varchar);
CREATE TABLE rangers(id SERIAL PRIMARY KEY, rangerName varchar , rangerId int);
CREATE TABLE sightings(id SERIAL PRIMARY KEY, ranger varchar , location varchar, animalint);

## Live link

[Click here to redirect](https://github.com/tonadaphin/Wildlife_tracker.git)


## Copyright & License

Tona Daphin <br>
This project is licensed under the MIT License 


