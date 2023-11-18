# elevator-simulation-matiasagado

This Java program simulates the movement of elevators in a building with multiple floors and passengers waiting on each floor.
The simulation considers factors such as passenger arrival, times, elevator movement, and passenger travel times. 

## Table of Contents
- Overview
- Classes
- Configuration
- Simulation Results

## Overview

The program consists of the following classes:

- EDirection: An enumeration representing elevator directions (UP, IDLE, DOWN).
- Passenger: Represents a passenger with starting and destination floors, an ID, and arrival time.
- Elevator: Simulates an elevator with a list of passengers, current floor, capacity, and movement methods.
- SimulationPassenger: An enumeration used in the simulation for passenger counting and indexing.
- Simulation: Orchestrates the elevator simulation, including passenger generation, elevator movement, and reporting results.
- ElevatorSimulation: The main class containing the main method to run the simulation.

## Classes

#### EDirection

- Represents elevator directions: UP, IDLE, DOWN.
- Defines constants for each direction and their associated integer values.
- Used in Elevator and Passenger classes.
  
#### Passenger
- Represents a passenger in the elevator system.
- Stores starting floor, destination floor, ID, and arrival time.
- Provides methods to calculate arrival floor and determine passenger direction.

#### SimulationPassenger
- Enumeration used in the simulation for passenger counting and indexing.
- Includes constants for COUNT and INDEX values.
  
#### Simulation
- Orchestrates the elevator simulation.
- Manages elevators, floor passenger lists, and simulation parameters.
- Generates passengers, moves elevators, and reports simulation results.
  
##### ElevatorSimulation
- Main class containing the main method to run the elevator simulation.
- Parses command-line arguments for configuration file (optional).
- Creates a Simulation object, runs the simulation, and reports the results.

## Usage
To run the elevator simulation, execute the main method in the ElevatorSimulation class. Optionally, you can provide a configuration file as a command-line argument.

    java ElevatorSimulation [configFile]

## Configuration

The program can be configured using a properties file. The default configuration is used if no file is provided.

Default Configuration:

- Floors: 32
- Passengers: 0.03
- Elevators: 1
- Elevator Capacity: 10
- Duration: 500
- Data Structure: linked
  
To customize the configuration, create a properties file with the following keys:

- structures
- floors
- passengers
- elevators
- elevatorCapacity
- duration

## Simulation Results

The simulation reports average, longest, and shortest travel times of passengers. The results include the time it takes for passengers to travel from their starting floor to their destination floor.
