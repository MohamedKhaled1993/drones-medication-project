# DRONES

We have a fleet of 10 drones A drone is capable of carrying devices, 
other than cameras, and capable of delivering small loads. 
For our use case the load is medications

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Apis](#apis)
- [About-Service](#about-service)


## Features

- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone;

## Prerequisites

Specify any prerequisites or requirements for using your project.

- JDK (17) or later
- Maven (v3.2.4) or later
- GitHub

## Installation

To install this project, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/MohamedKhaled1993/drones-medication-project.git 
2. Make sure you are using JDK 19 and Maven 3.x
You can build the project and run the tests by running mvn clean package   

## Apis
```drones-controller

1- load a drone by medications
PUT  /api/drone/loadDrone/{droneSerial}

 [
    "MED1",
    "MED2",
    "MED3"
 ]
 
2-register new drone
 
POST /api/drone/add
{
    
    "serialNumber":"serial5",
    "model":"LIGHTWEIGHT",
    "weight":0,
    "batteryCapacity":54,
    "state":"IDLE"
}
3-get all medications loaded by drone
GET /api/drone/medicationsByDrone/{droneSerial}

4-check battery by drone serial
GET /api/drone/checkBatteryBySerial/{serial}

5-Get all available drones
GET /api/drone/available

6-Get all drones
GET /api/drone/


```
```medications-controller

1-Register Medication
POST
/api/medication/

```
## About-Service
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is below 25%;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

-It uses an in-memory database (H2) to store the data

All APIs are "self-documented" by Swagger OpenAPI docs
Run the server and browse to http://localhost:8080/swagger-ui/index.html#/

To view your H2 in-memory datbase
The 'test' profile runs on H2 in-memory database. 
To view and query the database you can browse to http://localhost:8080/h2-console.
Default username is 'sa' with password : password
