import java.util.*;
import java.io.*;

public class ElevatorSimulation {

    class Passenger {

        private int startingFloor;
        private int destinationFloor;
        private int id;
        private long arrivalTime;

        public Passenger(int startingFloor, int destinationFloor, int id) {
            this.startingFloor = startingFloor;
            this.destinationFloor = destinationFloor;
            this.id = id;
            arrivalTime = System.currentTimeMillis();
        }

        public long arriveFloor(){
            return System.currentTimeMillis() - arrivalTime;
        }

        public int getDirection() {
            // 1 is for going up, -1 is for going down
            return startingFloor < destinationFloor ? 1 : -1;
        }

        // Getter Methods

        public int getStartingFloor() {
            return startingFloor;
        }

        public int getDestinationFloor() {
            return destinationFloor;
        }

        public long getArrivalTime() {
            return arrivalTime;
        }

        // Setter Methods

        public void setStartingFloor(int startingFloor) {
            this.startingFloor = startingFloor;
        }

        public void setDestinationFloor(int destinationFloor) {
            this.destinationFloor = destinationFloor;
        }

        public void setArrivalTime(long arrivalTime) {
            this.arrivalTime = arrivalTime;
        }
    }

    class Elevator {

        private List<Passenger> passengersList;
        private String dataStructure;
        private int numFloors;
        private int currentFloor;
        private int capacity;
        private int direction;

        private final int eUP = 1;
        private final int eDOWN = -1;
        private final int duration = 1;

        public Elevator(String dataStructure, int numFloors, int capacity) {
            this.passengersList = "linked".equals(structure) ? new LinkedList<>() : new ArrayList<>();
            this.dataStructure = dataStructure;
            this.numFloors = numFloors;
            this.currentFloor = 1; // 1 for 1st floor
            this.capacity = capacity;
            this.direction = 0; // 0 is idle
        }

        // method to move the elevator's direction
        public int moveElevatorTo(int destinationFloor, int remain) {
            // Determine the direction of movement
            this.direction = Integer.compare(destinationFloor, currentFloor);

            // Calculate the number of steps to move
            int numSteps = Math.min(Math.abs(currentFloor - destinationFloor), remain);

            // Update the current floor
            currentFloor += numSteps * direction;

            // Adjust direction when reaching the top or bottom floor
            if (currentFloor == 0)
                direction = eUP;

            if (currentFloor == numFloors - 1)
                direction = eDOWN;

            // Introduce a delay based on the number of steps
            try {
                Thread.sleep(numSteps * duration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return numSteps;
        }

        /*
         * move the elevator up or down based on 'direction'
         * change direction if elevator reaches the top or bottom
         */
        public void moveElevator() {

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            currentFloor += direction;

            if(currentFloor == 0)
                direction = eUP;

            if(currentFloor == numFloors - 1)
                direction = eDOWN;
        }

        // method to add passenger to elevator if capacity is not reached
        public List<Passenger> loadPassenger(List<Passenger> waitingPassengers) {

            List<Passenger> loadPassengers;
            if(this.dataStructure.equals("linked"))
                loadPassengers = new LinkedList<>();
            else
                loadPassengers = new ArrayList<>();

            for (Passenger passenger : waitingPassengers) {
                if (isFull()) {
                    break;
                }

                int passengerDirection = passenger.getDirection();

                if (passengerDirection == direction || direction == 0) {
                    direction = passengerDirection;
                    loadPassengers.add(passenger);
                    passengersList.add(passenger);
                }
            }
            return loadPassengers;
        }

        // method to remove passenger from the elevator
        public List<Passenger> unloadPassenger() {

            List<Passenger> arrivalPassengers;

            if(this.dataStructure.equals("linked"))
                arrivalPassengers = new LinkedList<>();
            else
                arrivalPassengers = new ArrayList<>();

            for(Passenger passenger: passengersList){
                if(passenger.getDestinationFloor() == this.currentFloor){
                    arrivalPassengers.add(passenger);
                }
            }

            for(Passenger passenger : arrivalPassengers)
                passengersList.remove(passenger);

            if(isEmpty())
                this.direction = 0;

            return arrivalPassengers;
        }

        public boolean isFull() {
            // return true if elevator capacity is reached
            return passengersList.size() >= capacity;
        }

        public boolean isEmpty() {
            // return true if elevator capacity is reached
            return passengersList.size() == 0;
        }

        public int getCurrentFloor(){
            return this.currentFloor;
        }

        public int getDirection(){
            return this.direction;
        }
    }
}