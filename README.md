# REST API using Camel and Spring Boot

Application using Spring boot, Camel REST API 

This application allows you to get all books and to add a
book. Once book is added the processor will return a new book which includes
the store name.


# Pre-requisites
```
Install JDK8 
Install Maven
Install Docker
```

## Command Line Usage

```Run on command line
mvn spring-boot:run

```

## Docker Usage

```Create image 
docker build -t camel-rest .

docker run -p 9090:9090 camel-rest
```

## Invoking HTTP Requests

GET HTTP request to load all books

```
http://localhost:9090/books
```

Example Pay load returned

```
[{"id":1,"name":"Book A","author":"Author A","price":100.0},{"id":2,"name":"Book B","author":"Author B","price":200.0},{"id":3,"name":"Book C","author":"Author C","price":300.0},{"id":4,"name":"Book D","author":"Author D","price":400.0}]
```

POST HTTP request to add a book 

```
http://localhost:9090/books
```

Example Body Pay load

HEADER: Content-Type application/json

```
{
    "id":5,
    "name":"New Book",
    "author":"New author",
    "price":22
}
```

GET HTTP request to load all books with store details

```
http://localhost:9090/books-store
```
Example Pay load returned

```
[{"id":5,"name":"New Book","author":null,"price":22.0,"storeName":"Store Name"}]
```

## Running test
```
mvn clean install
mvn test
```