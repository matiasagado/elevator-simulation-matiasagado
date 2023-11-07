import java.io.FileInputStream;
import java.util.*;

public class ElevatorSimulation {

    class Elevator {

        private int capacity;
        private int currentFloor;
        private Queue<Passenger> passengers;

        // constructor method to create an elevator
        public Elevator(int capacity) {
            this.capacity = capacity;
            this.currentFloor = 0;                        // elevator starts at the ground-floor
            this.passengers = new LinkedList<>();
        }

        // Method to check if the elevator is full.
        public boolean isFull() {

            boolean full = false;
            if(passengers.size() >= capacity) {
                full = true;
            }
            return full;
        }

        // Method to move the elevator to a new floor.
        public void moveFloor(int floor) {
            currentFloor = floor;
        }

        // method to load a passenger onto the elevator.
        public void loadPassenger(Passenger passenger) {
            passengers.add(passenger);
        }

        // method to unload passengers at their destination
        public void unloadPassengers() {
            Iterator<Passenger> i = passengers.iterator();
            while (i.hasNext()) {
                Passenger passenger = i.next();
                if (passenger.getDestinationFloor() == currentFloor) {
                    i.remove();
                }
            }
        }
    }

    class Passenger {

        private int startingFloor;
        private int destinationFloor;
        private int passengerNumber;


        // constructor method to create a new passenger
        public Passenger(int passengerNumber, int startingFloor, int destinationFloor) {
            this.startingFloor = startingFloor;
            this.destinationFloor = destinationFloor;
            this.passengerNumber = passengerNumber;
        }

        // getter methods
        public int getPassengerNumber() {
            return passengerNumber;
        }

        public int getStartingFloor() {
            return startingFloor;
        }

        public int getDestinationFloor() {
            return destinationFloor;
        }
    }

    class Floors {

        private int numFloors;
        private int numElevators;

        // constructor method to create number of floors
        public Floors(int numFloors, int numElevators, int capacity) {
            this.numFloors = numFloors;
            this.numElevators = numElevators;


        }

        // method for getting the number of floors
        public int getNumFloors(int numFloors) {
            return numFloors;
        }
    }

    public static void main(String[] args) {

        // default values
        int numFloors = 32;
        double passengerProbability = 0.03;
        int numElevators = 1;
        int elevatorCapacity = 10;
        int duration = 500;



    }
}