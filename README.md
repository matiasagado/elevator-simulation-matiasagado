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

  ### EDirection

    - Represents elevator directions: UP, IDLE, DOWN.
    - Defines constants for each direction and their associated integer values.
    - Used in Elevator and Passenger classes.
