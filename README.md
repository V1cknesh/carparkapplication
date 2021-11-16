# Car Park Application
Sample Spring Boot Rest Application built using Spring Boot + JDBC + Swagger (for documentation)


# Problem statement

I own a multi-storey carpark that can hold up to 'n' cars at any given point in time.

Each slot is given a number starting with 1 and increasing with increasing distance from the entry points in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention.

When a car enters my parking lot, I want to have a ticket issues to the driver. The ticket issuing process includes documenting the registration nuumber (number plate) and the colour of the car and allocating an available parking lot to the car before actuually handling over a ticket to the driver (we assume the customers are nice enough to park in the slots allocated to them). The customer should be allocated a parking slot which is nearest to the entry. At the exit, the customer returns the ticket which then marks the slot they were using as available.

Due to government regulation, the system should provide me with the ability to find out

 - Registration numbers of all cars of a particular colour.
 - Slot number in which a car with a given registration number is parked.
 - Slot numbers of all the slots where a car of a particular colour is parked.
 
 Develop a set of REST API services to support the following functions.
 
 # This is a Spring Boot Maven Project
 
 To start the project run the command mvn spring-boot:run
 
 # To run the JUnit5 tests
 
 mvn test
 
 # Postman tests


# POST Request

Creating a parking lot

http://localhost:8080/carpark/create_parking_lot 

number_of_slots 5



# PUT Request

Parking the car

http://localhost:8080/carpark/park

registration_number HH-HH-HH-HH
colour Blue

http://localhost:8080/carpark/park

registration_number KA-01-XA-3241
colour White


http://localhost:8080/carpark/park

registration_number KA-01-HH-3241
colour White


http://localhost:8080/carpark/park

registration_number KA-05-XA-3241
colour Blue

http://localhost:8080/carpark/park

registration_number KA-31-XA-3241
colour Black


http://localhost:8080/carpark/park

registration_number KA-01-XA-1234

colour Red

# DELETE Request

Leaving the carpark

http://localhost:8080/carpark/leave

slot_number 4

# GET Request

Status of the car park at any given point in time

status

http://localhost:8080/carpark/status

Registration numbers of all cars of a particular colour.

http://localhost:8080/carpark/registration_numbers_for_cars_with_colour

colour Red

Slot numbers of all the slots where a car of a particular colour is parked.

http://localhost:8080/carpark/slot_numbers_for_cars_with_colour

colour Red

Slot number in which a car with a given registration number is parked.

http://localhost:8080/carpark/slot_number_for_registration_number

registration_number KA-01-HH-3241


# Swagger Documentation URL

http://localhost:8080/v2/api-docs

# Swagger UI url

http://localhost:8080/swagger-ui.html
