# Sample Movie Database (SMDB) API Services

This repository contains the source code for the Sample Movie Database (SMDB) project developed as part of Spring Boot training. The project aims to build API services for managing movie, TV show, and person data, along with functionality for generating sample data and performing searches.

## Introduction
The Sample Movie Database (SMDB) is an online database providing information about movies, TV shows, and people involved in the entertainment industry.

## Technology Stack
* Java 21
* Spring Boot
* Maven
* MySQL Database
* Actuator Swagger
* log4j2

## Functional Requirements
## Product Perspective
The SMDB project aims to provide API services for managing movie, TV show, and person data. The primary functionality includes:

* Defining domain models for movies, TV shows, people, and best actor nominations.
* Generating sample data for testing and development purposes.
* Implementing search functionality to retrieve information from the database.
 ## Functionality
Domain Model
The system includes the following entities:

* Movies: Title, director, actors, producers, genre, production budget, release year.
* TV Shows: Title, director, actors, producers, genre, production budget per episode, start and end year, number of episodes.
* People: Name, profession, salary, salary type.
* Best Actor Nominations: Actor, year, genre, nomination result.

<b>Generate Sample Data</b> <br/>
Generate sample data for movies, TV shows, people, and best actor nominations to populate the database.

<b>Search</b> <br/>
Search functionality to query and calculate cost for movies, TV shows based on various criteria.

## Non-functional Requirements
## Software Design
* Follow best practices for software design, including the use of interfaces and abstract classes.
* Maintain proper Java packaging nomenclature.
## Logging
* Implement a logging policy to trace user activity and debug erroneous calls.
## Exceptions
* Define a policy for handling exceptions, including invalid requests and error responses.
* Use a GlobalExceptionHandler to manage exceptions and return appropriate JSON error messages.
## Response Details
* Ensure every API call returns a JSON document, including error responses.
* Use proper HTTP status codes to indicate the success or failure of API requests.

## API Documentation
* The API documentation is available using Actuator Swagger. Access the Swagger UI at http://localhost:8080/swagger-ui.html.

## Acknowledgments
This project was developed as part of a learning exercise in the Code.Hub bootcamp. I would like to express my gratitude to the following individuals and organizations for their support and contributions:

Code.Hub: A leading technology education provider that offers comprehensive bootcamp programs and training courses. Their expert instructors and well-structured curriculum have been instrumental in our learning journey.
Instructors: Our dedicated instructors who provided guidance, knowledge, and support throughout the bootcamp. Their expertise and passion for teaching have been invaluable.
