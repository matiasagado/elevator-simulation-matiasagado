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

        // Method to check if the elevator is at capacity.
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
        public void unloadPassenger() {
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
        private int id;
        private int arrivalTick;

        // constructor method to create a new passenger
        public Passenger(int id, int startingFloor, int destinationFloor, int arrivalTick) {
            this.startingFloor = startingFloor;
            this.destinationFloor = destinationFloor;
            this.id = id;
            this.arrivalTick = arrivalTick;
        }

        // getter methods
        public int getId() {
            return id;
        }

        public int getStartingFloor() {
            return startingFloor;
        }

        public int getDestinationFloor() {
            return destinationFloor;
        }

        public int getArrivalTick() {
            return arrivalTick;
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