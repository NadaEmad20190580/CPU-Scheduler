package Package;

        import java.util.Comparator;

public class Process {
    public String name;
    public boolean dead=false;
    public int arrivalTime;
    public int burstTime;
    public int remainingBurstTime;
    public int priority;
    public int startTime;
    public int endTime;
    public int waitingTime;
    public int turnaroundTime;
    public int quantum;
    public int remainingQuantum;
    public int AGAT_Factor;
    // for AGAT Scheduler
    public Process(String name, int arrivalTime, int burstTime,
                   int priority,int quantum) {
        this.name = name;
        this.arrivalTime=arrivalTime;
        this.burstTime = burstTime;
        this.remainingBurstTime=burstTime;
        this.priority = priority;
        this.quantum = quantum;
        this.remainingQuantum = quantum;

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
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Process)) return false;
        Process o = (Process) obj;
        return o.name==this.name;
    }
    // sort processes by arrival time
    public static class ArrivalTimeComparator implements Comparator<Process> {

        // Function to compare
        public int compare(Process p1, Process p2) {
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        }
    }
    public static class BurstTimeComparator implements Comparator<Process> {

        // Function to compare
        public int compare(Process p1, Process p2) {
            return Integer.compare(p1.burstTime, p2.burstTime);
        }
    }
    public void execute(){
        // calculate TurnAroundTime,...
        System.out.println(toString());
    }
    @Override
    public String toString()
    {
        String output=name+"\t"+arrivalTime+"\t"+burstTime;
        return output;
        //print rest info of process
    }
    public static class RemainingBurstComparator implements Comparator<Process> {

        // Function to compare
        public int compare(Process p1, Process p2) {
            return Integer.compare(p1.remainingBurstTime, p2.remainingBurstTime);
        }
    }
    public static class AGATFactorComparator implements Comparator<Process> {

        // Function to compare
        public int compare(Process p1, Process p2) {
            return Integer.compare(p1.AGAT_Factor, p2.AGAT_Factor);
        }
    }

}


