
---
# Drone Dispatch Application

## About
* REST API service that allows Drone dispatch medications.
---

# Requirements
* Java 11 SDK or newer.
* Maven 3.6 or newer.
---
# Installation | Build
* The service can be installed by running system shell (command line) in the project folder and using the below maven
  command.

```
mvn clean install
```
---
# Usage | Run

* To run the service, locate the `/target folder` in the project folder and enter the below command.
* Note that `/target folder` - is created after a successful installation/build.

```
java -jar drones-1.0-SNAPSHOT.jar
```
---
# Swagger

* After running the application, the swagger documentation can be found in http://localhost:8080/swagger-ui/index.html#/

---
# APIs
## Register a drone:
- Url: http://localhost:8080/drones
- Request type: POST
- Request body Sample
```
{
    "model":"heavyweight"
}
```

## Load a drone with medication items:
- Url: http://localhost:8080/drones/{serialNumber}/medications
- Request type: PUT
- Request Parameters Sample
```
   [1]
```
## Get loaded medication items for a given drone:
- Url: http://localhost:8080/drone/{serialNumber}/medications
- Request type: GET
- Request Parameter Sample
```
{
       KEY : serialnumber 
       VALUE: 0d02ccbb-328e-41a4-830b-c261d51cce2a
}
```
## Get drones available for loading:
- Url: `http://localhost:8080/drones/available
- Request type: GET


## Get battery level for a given drone:
- Url: http://localhost:8080/drone/{serialNumber}/battery
- Request type: GET
- Request Parameter Sample
```
{
       KEY : serialnumber 
       VALUE: 0d02ccbb-328e-41a4-830b-c261d51cce2a
}
```
---
