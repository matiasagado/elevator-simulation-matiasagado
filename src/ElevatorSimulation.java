import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;

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
        private List<Elevator> numElevators;
        private List<Queue<Passenger>> floorQueue;

        // constructor method to create number of floors
        public Floors(int numFloors, int numElevators, int capacity) {
            this.numFloors = numFloors;
            this.numElevators = new ArrayList<Elevator>();

            // initialize number of elevators
            for ( int i = 0; i < numElevators; i++) {
                this.numElevators.add(new Elevator(capacity));
            }

            // initialize queue for passengers on each floor
            for(int i = 0; i < numFloors; i++) {
                this.floorQueue.add(new LinkedList<>());
            }

        }
        // method for getting floor queues
        public List<Queue<Passenger>> getFloorQueue() {
            return floorQueue;
        }

        // method to add a passenger to elevator queue from floor queue



        // method for getting the number of elevators
        public List<Elevator> getNumElevators() {
            return numElevators;
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

        // check to see if property file is given
        if (args.length > 0) {
            try {
                FileInputStream fileInput = new FileInputStream(args[0]);
                Properties properties = new Properties();
                properties.load(fileInput);

                fileInput.close();
            } catch (IOException e) {
                System.err.println("Error: Couldn't read property file.");
            }
        }

        // create simulation instance

        // run the simulation

        // print results
    }
}