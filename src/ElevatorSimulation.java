import java.io.FileInputStream;
import java.util.*;

public class ElevatorSimulation {

    class Elevator {
        private int capacity;
        private int currentFloor;
        private Queue<Passenger> passengers;

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