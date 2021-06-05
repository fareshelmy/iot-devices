# IOT Devices
    
## How to build an executable jar

To generate a build with dependencies:
1) clone the repo to your local machine
  
2) From terminal:
 
  	`$ cd <path to the repo>`
  
	`$ mvn clean install`


 ** You will find your target jar at `<path to repo>/target/`


## How to run
1) You need MySQL database installed on your machine. If you don't have MySQL database installed, please install it from here https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/
2) Run the SQL script attached with the project to auto create the iot-device schema and insert a sample data by executing this command in terminal:

    `$ mysql -h "127.0.0.1" -u "<username>" [-p] < "<path to repo>/iot-devices.sql"`
    
    You will be prompted to type in the database password in case you choose the -p option
    
3) To start the application, you have two options:
    1) From Terminal:

        `$ java -jar <path to repo>/target/iot-0.0.1-SNAPSHOT.jar`

    2) From Workspace:
      Proceed to `/iot-devices/src/main/java/com/iot/Application.java` and run the main method
      
## How to use
To use the app API, you can use Postman of any other API calling applications. 
There are three main endpoints that can be used:

  1) A GET method to get all devices waiting for activation. This endpoint can be accessed by:
      
      `localhost:8080/devices/waiting`

  2) A PUT method to update a specific device status. This endpoint can be accessed by:
      
      `localhost:8080/devices/{deviceId}/updateStatus/{status}`
      
      ** accepted statuses: active, waiting, blocked, deactivated

      example: `localhost:8080/devices/3/updateStatus/active`

  3) A GET method to get all devices available for sale, sorted by country name. This endpoint can be accessed by:
      
      `localhost:8080/devices/available`
  
## How to run tests
To run unit tests for the project from terminal:
  
`$ cd <path to the repo>`
	
`$ mvn test`
