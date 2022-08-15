# CtrlplusVehicleCirculationServices

This project was generated with [Spring Boot Initializer](https://start.spring.io/) version 2.7.2.

## Deploy with Docker

Required: [Docker instalation](https://docs.docker.com/engine/install/)

Run `sudo docker pull daniellincango/circulation-services:1.0.1` to pull web app image.

Run `sudo docker run -p 8080:8080 daniellincango/circulation-services:1.0.1` to deploy app.

See the app running in: [Circulation web app - Development](http://localhost:8081)

## Deploy locally

Run `gradle build` for dependency installation.
Run `./gradlew bootRun` for init development server.


## POSTMAN Collection

[REST Services](/Ctrlplus.postman_collection.json)
