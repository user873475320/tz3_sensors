The REST API is a service that receives data from
the "sensor" and stores it in the database. You can also get this data from the database.

There are 4 addresses in total in the application:
1. Sensor registration.
2. Adding a measurement from the sensor.
3. Getting all the measurements.
4. Getting the number of rainy days.

The application is divided into 2 parts:
1. The REST API of the application.
2. Java client that sends data to the REST API application - using the RestTemplate class.
