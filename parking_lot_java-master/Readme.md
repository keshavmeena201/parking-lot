# Description
This is a boilerplate code for the java based implementation of parking lot problem statement described below.
This code will be given to a candidate coming for an onsite interview so that they can take the implementation forward.

## Requirements

The project is built using Java 1.8. It uses Maven as the build system.

1. Java - 1.8.x
2. Maven - 3.x.x

## Building the application using maven

You can build and package the application in the form of a jar file using maven -

```
cd application
mvn clean package
```

The above command will produce a standalone jar file named `goparking.jar` in the `GoParking/target` directory.

## Running Tests

The `mvn package` command runs all the unit tests and also packages the application in the form of a jar file. If you just want to run the tests without packaging it, then you can use `mvn test` command.

```
cd application
mvn test
```

## Running the application

You can run the jar file created by the `mvn package` command like so -

```
java -jar target/application.jar
```

## Running the functional tests

```
./bin/run_functional_tests
```


The above command launches an interactive shell where you can type various parking lot commands and receive the output on the console. 

The application can also take input commands from a file. You can pass multiple commands separated by a newline in the file like so -

```
java -jar target/goparking.jar file_input.txt
```

# Problem Statement
I own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention.

When a car enters my parking lot, I want to have a ticket issued to the driver. The ticket issuing process includes us documenting the registration number (number plate) and the colour of the car and allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them). The customer should be allocated a parking slot which is nearest to the entry. At the exit the customer returns the ticket which then marks the slot they were using as being available.

Due to government regulation, the system should provide me with the ability to find out:

- Registration numbers of all cars of a particular colour.
- Slot number in which a car with a given registration number is parked.
- Slot numbers of all slots where a car of a particular colour is parked.

We interact with the system via a simple set of commands which produce a specific output. Please take a look at the example below, which includes all the commands you need to support - they're self explanatory. The system should allow input in two ways. Just to clarify, the same codebase should support both modes of input - we don't want two distinct submissions.
It should provide us with an interactive command prompt based shell where commands can be typed in.
It should accept a filename as a parameter at the command prompt and read the commands from that file.

# Commands which have already been implemented

- `create_parking_lot`
- `park`

# Test Cases to fix

- There is a parking validation based failing test case in the code which needs to be fixed.
- There are also domain level tests for the commands to be implemented next.


# Commands to implement

- `leave`
- `registration_numbers_for_cars_with_colour`
- `slot_numbers_for_cars_with_colour`
- `slot_number_for_registration_number`
- `slot_number_for_registration_number`

# Interactive Example
```
$ create_parking_lot 3 3
$ park KA-01-HH-1234 White Allocated slot number: 1
$ park KA-01-HH-9999 White Allocated slot number: 2
$ park KA-01-BB-0001 Black Allocated slot number: 3
$ park KA-01-HH-7777 Red Allocated slot number: 4
$ park KA-01-HH-2701 Blue Allocated slot number: 5
$ park KA-01-HH-3141 Black Allocated slot number: 6

$ leave 4
Slot number 4 is free

$ status
Slot
No.
Registration No
Colour
1

KA-01-HH-1234
White
2

KA-01-HH-9999
White
3

KA-01-BB-0001
Black
5

KA-01-HH-2701
Blue
6

KA-01-HH-3141
Black

$ park KA-01-P-333 White Allocated slot number: 4

$ park DL-12-AA-9999 White Sorry, parking lot is full

$ registration_numbers_for_cars_with_colour White KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333

$ slot_numbers_for_cars_with_colour White 1, 2, 4

$ slot_number_for_registration_number KA-01-HH-3141 6

$ slot_number_for_registration_number MH-04-AY-1111 Not found

$ exit

```
