import java.util.*;
import java.io.*;

enum EDirection {
    // used in Elevator/ Passenger Classes;

    UP(1),
    IDLE(0),
    DOWN(-1);

    final int direction;

    EDirection(int direction) {
        this.direction = direction;
    }
}

public class ElevatorSimulation {

    static class Passenger {

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
            return (startingFloor < destinationFloor ? EDirection.UP.direction : EDirection.DOWN.direction);
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

    static class Elevator {

        private List<Passenger> passengersList;

        private String dataStructure;

        private int numFloors;
        private int currentFloor;
        private int capacity;
        private int direction;

        private final int duration = 1;

        public Elevator(String dataStructure, int numFloors, int capacity) {
            this.passengersList = "linked".equals(dataStructure) ? new LinkedList<>() : new ArrayList<>();
            this.dataStructure = dataStructure;
            this.numFloors = numFloors;
            this.currentFloor = 1; // 1 for ground-floor
            this.capacity = capacity;
            this.direction = EDirection.IDLE.direction;
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
                direction = EDirection.UP.direction;

            if (currentFloor == numFloors - 1)
                direction = EDirection.DOWN.direction;

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
                direction = EDirection.UP.direction;

            if(currentFloor == numFloors - 1)
                direction = EDirection.DOWN.direction;
        }

        // method to add passenger to elevator if capacity is not reached
        public List<Passenger> loadPassenger(List<Passenger> waitingPassengers) {

            List<Passenger> loadPassengers;
            loadPassengers = this.dataStructure.equals("linked") ? new LinkedList<>() : new ArrayList<>();

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

            arrivalPassengers = this.dataStructure.equals("linked") ? new LinkedList<>() : new ArrayList<>();

            for (Passenger passenger: passengersList){
                if (passenger.getDestinationFloor() == this.currentFloor){
                    arrivalPassengers.add(passenger);
                }
            }

            for (Passenger passenger : arrivalPassengers)
                passengersList.remove(passenger);

            if (isEmpty())
                this.direction = 0;

            return arrivalPassengers;
        }

        public boolean isFull() {
            return passengersList.size() >= capacity;
        }

        public boolean isEmpty() {
            return passengersList.isEmpty();
        }

        public int getCurrentFloor(){
            return this.currentFloor;
        }

        public int getDirection(){
            return this.direction;
        }
    }

    enum SimulationPassenger {
        // used in Simulation Class

        COUNT(0),
        INDEX(1);

        int value;
        SimulationPassenger(int value) {
            this.value = value;
        }
    }

    static class Simulation {

        private List<Long> listOfTimes;
        private List<List<Passenger>> floorPassengerList;
        private List<Elevator> elevatorList;

        private String dataStructure;
        private int floors;
        private double passengers;
        private int elevators;
        private int elevatorCapacity;
        private int duration;

        public Simulation() {
            dataStructure = "linked";
            floors = 32;
            passengers = 0.03;
            elevators = 1;
            elevatorCapacity = 10;
            duration = 500;
        }

        public Simulation(String configFile) {

            Properties config = new Properties();

            try {
                FileReader reader = new FileReader(configFile);
                config.load(reader);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {

                dataStructure = config.getProperty("structures");
                floors = Integer.parseInt(config.getProperty("floors"));
                passengers = Double.parseDouble(config.getProperty("passengers"));
                elevators = Integer.parseInt(config.getProperty("elevators"));
                elevatorCapacity = Integer.parseInt(config.getProperty("elevatorCapacity"));
                duration = Integer.parseInt(config.getProperty("duration"));

            } catch (Exception e) {

            }

            floors = (floors < 2) ? 32 : floors;
            passengers = ((passengers < 0) || (passengers > 1.0)) ? 0.03 : passengers;
            elevators = (elevators < 1) ? 1 : elevators;
            elevatorCapacity = (elevatorCapacity < 1) ? 10 : elevatorCapacity;
            duration = (duration < 1) ? 500 : duration;
        }
        private int findNearestPassenger(int currentFloor, int direction) {
            int minDistance = floors;
            int destinationFloor = -1;

            for (int i = 0; i < floors; i++) {
                if (i == currentFloor || floorPassengerList.get(i).isEmpty()) {
                    continue;
                }

                int currentDirection = Integer.compare(currentFloor, i);

                if (direction == 0 || currentDirection == direction) {
                    int curDis = Math.abs(currentFloor - i);

                    if (curDis < minDistance) {
                        minDistance = curDis;
                        destinationFloor = i;
                    }
                }
            }

            return destinationFloor;
        }

        public void runSimulation() {

            if (dataStructure.equals("linked")) {

                elevatorList = new LinkedList<>();
                for (int i = 0; i < elevators; i++) {
                    elevatorList.add(new Elevator(dataStructure, floors, elevatorCapacity));
                }

                floorPassengerList = new LinkedList<>();
                for (int i = 0; i < floors; i++) {
                    floorPassengerList.add(new LinkedList<Passenger>());
                }
                listOfTimes = new LinkedList<>();

            } else {

                elevatorList = new ArrayList<>();
                for (int i = 0; i < elevators; i++) {
                    elevatorList.add(new Elevator(dataStructure, floors, elevatorCapacity));
                }

                floorPassengerList = new ArrayList<>();
                for (int i = 0; i < floors; i++) {
                    floorPassengerList.add(new ArrayList<Passenger>());
                }
                listOfTimes = new ArrayList<>();
            }

            Random rand = new Random(System.currentTimeMillis());

            for (int i = 0 ; i < duration; i++) {

                generatePassenger(rand);
                moveElevator();
            }

        }

        private void generatePassenger(Random rand) {

            double passengerProb;
            int destinationFloor;

            for(int i = 0; i < floors; i++) {

                passengerProb = rand.nextDouble();
                if (passengerProb < passengers) {
                    destinationFloor = rand.nextInt(floors);
                    while (destinationFloor == i)
                        destinationFloor = rand.nextInt(floors);
                    floorPassengerList.get(i).add(new Passenger(SimulationPassenger.INDEX.value, i, destinationFloor));
                    SimulationPassenger.INDEX.value++;
                }
                SimulationPassenger.COUNT.value += floorPassengerList.get(i).size();
            }
        }

        private void moveElevator() {

            if (SimulationPassenger.COUNT.value > 0) {
                for (int i = 0; i < elevators; i++) {

                    Elevator currentElevator = elevatorList.get(i);
                    int tick = 5;
                    int destFloor = 0;

                    while (tick > 0) {

                        unloadPassenger(currentElevator);

                        List<Passenger> floorPassenger = floorPassengerList.get(currentElevator.getCurrentFloor());
                        loadPassenger(currentElevator, floorPassenger);

                        if(currentElevator.isEmpty()){
                            //find nearest waiting passenger
                            destFloor = findNearestPassenger(currentElevator.getCurrentFloor(), currentElevator.getDirection());
                            if(destFloor > -1){
                                int nMove = currentElevator.moveElevatorTo(destFloor, tick);
                                tick -= nMove;
                            }
                            else{
                                tick = 0;
                            }
                        }
                        else{
                            currentElevator.moveElevator();
                            tick--;
                        }
                    }
                }
            }
        }

        private void loadPassenger(Elevator currentElevator, List<Passenger> floorPassenger) {
            if(!currentElevator.isFull() && floorPassenger.size() > 0){
                List<Passenger> loadPassengers = currentElevator.loadPassenger(floorPassenger);
                for(Passenger p: loadPassengers){
                    floorPassenger.remove(p);
                }
            }
        }

        private void unloadPassenger(Elevator currentElevator) {
            if(!currentElevator.isEmpty()){
                List<Passenger> arrivedPassengers = currentElevator.unloadPassenger();
                for(Passenger p: arrivedPassengers){
                    listOfTimes.add(p.arriveFloor());
                }
            }
        }

        public void reportResults() {

            long shortestTime = Long.MAX_VALUE;
            long avgTime = 0;
            long longestTime = 0;

            for (Long time : listOfTimes){
                avgTime += time;
                if (shortestTime > time)
                    shortestTime = time;
                if (time > longestTime)
                    longestTime = time;
            }

            if (listOfTimes.size() >  0) {
                avgTime = avgTime / listOfTimes.size();
            }

            System.out.println("\nAverage travel time of passengers: " + avgTime + " ms.");
            System.out.println("Longest travel time of passenger: " + longestTime + " ms.");
            System.out.println("Shortest travel time of passenger: " + shortestTime + " ms.");
        }
    }

    public static void main(String[] args) {

        Simulation simulation;
        simulation = args.length > 0 ? new Simulation(args[0]) : new Simulation();
        simulation.runSimulation();
        simulation.reportResults();
    }
}