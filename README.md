# AirLine-Management-System


## Project Structure
![airport_mgms](https://user-images.githubusercontent.com/113486280/235760067-c9d5cba8-3126-4a93-9168-ea30c9d3e4cc.png)


## Project Layer
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/113486280/235760442-2ea97572-efc1-47ee-8e68-e3e955249b3c.png">

## Overview

The Airline Booking System is a web-based application that allows users to search and book flights offered by various airlines. The system is divided into three parts: the user's browser, the React front-end, and the SpringBoot back-end. The React front-end is further divided into a portal and three back-end management systems. The SpringBoot back-end is composed of multiple systems, including a Eureka server, a city list server, and three airline company sub-servers.


## Features

- Users can search and book flights offered by various airlines.
- Three back-end management systems allow airline company personnel to manage their services.
- The portal allows users to register, log in, view flight schedules, and book flights.
- The city list server helps other services query city information and protects data privacy.
- The Eureka server is a registry center that all the systems register on.
- The booking system allows users to book flights, and airline company personnel to approve or reject the booking.


## Technologies Used

- React: A JavaScript library used for building user interfaces. 
- SpringBoot: A Java framework used for building enterprise applications. 
- MongoDB: A NoSQL database used for storing and managing data. 
- Eureka: A registry center used for service discovery.
- Maven: A build tool used for managing dependencies.
- Servlets: A Java API used for handling HTTP requests and responses.
- JSP: A Java technology used for dynamically generating HTML, XML, or other types of documents.


## Implementation Details

- Utilized Maven for building and managing dependencies.
- Utilized Servlets for handling user requests and responses.
- Implemented MongoDB to store data and used converters to convert between documents and session/book classes.
- Created a filter to control user login and used a map to determine which actions require login.
- Utilized JSP to display web pages and implemented error and success pages.
- Split time into one-hour intervals using a Session Hourly Intervals class.
- Achieved complete functionality for booking flights from availability to confirmation, rejection, or cancellation.


## Conclusion

The Airline Booking System is a fully functional web application that provides a comprehensive solution for booking flights offered by various airlines. The system is built using modern technologies and provides a user-friendly interface for users and airline company personnel.
