# Polynomial Expression Simplifier

A Java Spring Boot application that simplifies polynomial expressions and provides additional functionality like evaluating expressions for a given value of x.
Features

* Simplify polynomial expressions (e.g., `2*x^2 + 3*x - 5 + x^2 + x` → `3*x^2 + 4*x - 5`)
* Evaluate expressions as mathematical functions (f(x))
* REST API for interacting with the simplifier
* MySQL database integration for persistence
  
### Prerequisites

Ensure the following are installed on your system:

    Java Development Kit (JDK) 17 or higher
        Verify: java -version
    Maven (for building and running the application)
        Verify: mvn -version
    MySQL Server
        Verify: MySQL should be running and accessible.
        Create a database (e.g., polynomial_db) and configure its credentials in application.properties.

## Getting Started

### Clone the Repository

`git clone git@github.com:AndyShylnikov/polynom-parser.git`

`cd polynom-parser`

Setup MySQL Database

* Start your MySQL server.
* Create a database for the project:

`CREATE DATABASE polynomial_db;`

Edit `src/main/resources/application.properties` to match your MySQL configuration:

    spring.datasource.url=jdbc:mysql://localhost:3306/polynomial_db
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

### Build the Application

Run the following command to build the application:

    ./mvnw clean install

Run the Application

To start the Spring Boot application:

    ./mvnw spring-boot:run

The application will be available at: http://localhost:8080.

#### Endpoints

    Method	Endpoint	    Description

    POST	/polynomial 	     Simplifies the given polynomial string or returns function result 

### How to use
1. In case if POST body will contain only `expression` field, API will return simplified expression:
  For example, if we will send next request:

        curl --location 'localhost:8080/polynomial' \
          --header 'Content-Type: application/json' \
          --data '{
            "expression": "(x^2 + 2) * (x - 1) + (2 * (x + 3) * (x - 2))"
        }'
  Response will be next:

        {
          "expression": "x^3 + x^2 + 4*x - 14"
        }
2. In case if body will have also `argument` field, API will calculate and return result:

       curl --location 'localhost:8080/polynomial' \
         --header 'Content-Type: application/json' \
         --data '{
           "expression": "(x^2 + 2) * (x - 1) + (2 * (x + 3) * (x - 2))",
           "argument": 4
       }'
  Response will be next:
  
      {
        "result": 82
      }

3. In case of some error, app will return 400 status.

### Running Tests

Run unit tests with Maven:

./mvnw test