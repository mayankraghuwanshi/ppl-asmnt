# Employee Service
## prerequisite
`JDK 8` </br>
`maven 3.6.3` </br>
`Ide` </br>

## Steps
1. clone this project using following command.
```
git clone https://github.com/mayankraghuwanshi/ppl-asmnt.git
```
2. Build this project.
```
cd ppl-asmnt 
mvn clean install
```
3. Run this project.
```
cd employeeserviceImplementation
mvn spring-boot:run
```



## create new employee
url : localhost:8080/v1/bfs/employees/ </br>
method : <strong>POST</strong> </br>
RequestBody : </br>
```
{ 
  "first_name" : "Mayank",
  "last_name" : "Raghuvanshi",
  "date_of_birth" : "12/08/1997",
  "address" : {
  	"line1":"Dream city",
  	"line2" : "Panki",
  	"city": "Kanpur",
  	"country" : "india",
  	"zip_code" : "208020"
  }
}
```
## Response 200 OK
```
{ 
  "first_name" : "Mayank",
  "last_name" : "Raghuvanshi",
  "date_of_birth" : "12/08/1997",
  "address" : {
  	"line1":"Dream city",
  	"line2" : "Panki",
  	"city": "Kanpur",
  	"country" : "india",
  	"zip_code" : "208020"
  }
}
```

## Exception 400 Bad Request

```
{
    "timestamp": "2021-02-20T17:08:16.425+0000",
    "message": "Request validation failed",
    "errors": {
        "firstName": "size must be between 1 and 255",
        "lastName": "size must be between 1 and 255",
        "address.city": "must not be null",
        "address.line1": "must not be null",
        "dateOfBirth": "must not be null",
        "address.zipCode": "must not be null",
        "address.country": "must not be null"
    }
}
```

## get employee by id
url : localhost:8080/v1/bfs/employees/{employeeId} </br>
method : GET </br>
## Response 200 OK
```
{ 
  "first_name" : "Mayank",
  "last_name" : "Raghuvanshi",
  "date_of_birth" : "12/08/1997",
  "address" : {
  	"line1":"Dream city",
  	"line2" : "Panki",
  	"city": "Kanpur",
  	"country" : "india",
  	"zip_code" : "208020"
  }
}

```
## NotFound 404 Not Found
```
{
    "timestamp": "2021-02-20T17:11:35.555+0000",
    "message": "Employee with id 10 not found!"
}
```

## Employee

|name      | type   | mandatory      |additional          |
|----------|--------|----------------|--------------------|
|first_name| String | yes            | minLen(1), maxLen(50)|
|last_name | String | yes            | minLen(1), maxLen(50)|
|date_of_birth| Date | yes           | pattern(dd/mm/yyyy)|
|address| Object (Address) | yes |line1,line2,city,country,zip_code|

## Address

|name|type|mandatory|additional|
|----|----|---------|----------|
|line1|String|yes|minLen(1),maxLen(50)|
|line2|String|no|minLen(1),maxLen(50)|
|city | String| yes| minLen(1),maxLen(50)|
|country| String|yes|minLen(1),maxLen(50)|
|zip_code| String| yes| minLen(6),maxLen(6)|

## Error code
|code|description|
|----|-----------|
|FC0T5|When failure occurred while casting address object to string|
|FC5T0| When failure occurred while casting address string to object|
|FCNE|When failure occurred while creating new employee|


## Description: </br>

This service has two APIs. One to create an employee resource and second to retrieve it by id. This service is using **H2** in memory database to persist the employee object.
Address in Employee object is getting stored in h2 as a **BLOB** (binary large object). **Unit tests** are present in the employeeserviceImplementation itself in **test** package.

You can found a **postman collection** [here](https://www.getpostman.com/collections/8e845bcdca8a36c17d3b)

