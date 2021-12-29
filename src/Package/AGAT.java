package Package;

import java.util.*;

public class AGAT {
    static int contextSwitch;
    public  int currentTime=0;
    public double V1,V2;
    public double averageWaitingTime = 0;
    public double averageTurnAroundTime = 0;
    public ArrayList<Process> processes = new ArrayList<Process>();

    public AGAT(ArrayList<Process> processes) {
        this.processes = processes;
        this.processes.sort(new Process.ArrivalTimeComparator());
    }

    public void calculateV1(){
        if (processes.get((processes.size()-1)).arrivalTime>10){
            V1=(processes.get((processes.size()-1)).arrivalTime)/10.0;
        }
        else V1=1;
    }
    public void calculateV2 (){
        ArrayList<Process> p = processes;
        p.sort(new Process.RemainingBurstComparator());
        if (p.get((p.size()-1)).remainingBurstTime>10){
            V2=(p.get((p.size()-1)).remainingBurstTime)/10.0;
        }
        else V2=1;
    }
    public void calculateAGATFactor (){
        for (Process p : processes){
            p.AGAT_Factor=(int)((10-p.priority)+Math.ceil(p.arrivalTime/V1)+Math.ceil(p.remainingBurstTime/V2));
        }
    }

    public void processAGAT(){
        calculateV1();
        calculateV2();
        calculateAGATFactor();


    }
}
