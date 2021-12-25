package Package;

import java.util.Comparator;

public class Process {
    public String name;
    public int arrivalTime,burstTime,priority,
            startTime, endTime, waitingTime,
            turnaroundTime, quantum, AGAT_Factor;
// default constructor
    Process() {
        name = "";
        arrivalTime=0;
        burstTime = 0;
        priority = 0;
        endTime = 0;
        AGAT_Factor = 0;
        startTime = 0;
        waitingTime = 0;
        quantum = 0;
        turnaroundTime = 0;
    }

// for AGAT Scheduler
    public Process(String name, int arrivalTime, int burstTime,
                   int priority,int quantum) {
        this.name = name;
        this.arrivalTime=arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.quantum = quantum;
        //calculate AGAT factor
    }
    // for Priority Scheduler
    public Process(String name, int arrivalTime, int burstTime,
                   int priority) {
        this.name = name;
        this.arrivalTime=arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
// sort processes by arrival time
    public static class ArrivalTimeComparator implements Comparator<Process> {

        // Function to compare
        public int compare(Process p1, Process p2) {
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        }
    }
    //print process data after finishing its processing
    @Override
    public String toString()
    {
        return name;
        //print rest info of process
    }

}

