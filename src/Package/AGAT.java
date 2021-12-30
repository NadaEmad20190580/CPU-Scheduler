package Package;

import java.util.*;

public class AGAT {
    static int contextSwitch;
    public  int currentTime=0;
    public double V1,V2;
    public double averageWaitingTime = 0;
    public double averageTurnAroundTime = 0;
    public Process runningProcess ;
    public ArrayList<Process> processes = new ArrayList<>();
    public ArrayList<Process> readyQueue= new ArrayList<>();
    public ArrayList<Process> deadList = new ArrayList<>();

    public AGAT(ArrayList<Process> processes) {
        calculateV1();
        calculateV2();
        calculateAGATFactor();
        this.processes = processes;
        this.processes.sort(new Process.ArrivalTimeComparator());
        processAGAT();
    }

    public void calculateV1(){
        if(processes!=null){
            if (processes.get((processes.size()-1)).arrivalTime>10){
                V1=(processes.get((processes.size()-1)).arrivalTime)/10.0;
            }
            else V1=1;
        }
    }
    public void calculateV2 (){
        if (processes!=null){
            ArrayList<Process> p = processes;
            p.sort(new Process.RemainingBurstComparator());
            if (p.get((p.size()-1)).remainingBurstTime>10){
                V2=(p.get((p.size()-1)).remainingBurstTime)/10.0;
            }
            else V2=1;
        }
    }
    public void calculateAGATFactor (){
        for (Process p : processes){
            p.AGAT_Factor=(int)((10-p.priority)+Math.ceil(p.arrivalTime/V1)+Math.ceil(p.remainingBurstTime/V2));
        }
    }
    public int checkArrival(){
        int nextArrival;
        for (int i =0 ;i<processes.size();i++){
            while (processes.get(i).arrivalTime<=currentTime && !readyQueue.contains(processes.get(i))){
                readyQueue.add(processes.get(i));
            }
            break;
        }
        readyQueue.sort(new Process.ArrivalTimeComparator());
        nextArrival=readyQueue.get(readyQueue.size()-1).arrivalTime;
        for (int i=0;i<processes.size();i++){
            if(processes.get(i).arrivalTime>nextArrival){
                nextArrival=processes.get(i).arrivalTime;
                break;
            }
        }
        readyQueue.sort(new Process.AGATFactorComparator());
        return nextArrival;
    }
    public void executeProcess(){
        int start=currentTime,executionTime=0;
        checkArrival();
        runningProcess=readyQueue.get(0);
        executionTime=(int)Math.round(.04*runningProcess.quantum);
        int nextArrival=checkArrival();
        if(readyQueue.size()!=1){
                if(checkNext()){
                    runningProcess.remainingQuantum-=executionTime;
                }
                else{
                    runningProcess.remainingQuantum=0;
                    executionTime=runningProcess.quantum;
                }
                removeProcess();

        }
        else{
            executionTime=nextArrival-start;
            runningProcess.remainingQuantum-=executionTime;
        }
        runningProcess.remainingBurstTime-=executionTime;
        currentTime+=executionTime;//+contextSwitch;
        removeProcess();
        System.out.println(runningProcess.name+"\t from"+start + "to "+currentTime);
    }
    public boolean checkNext(){
        for (Process p : readyQueue){
            if(p.AGAT_Factor<runningProcess.AGAT_Factor){
                return true;
            }
        }
        return false;
    }
    public void removeProcess(){
        if (runningProcess!=null){
            if (runningProcess.remainingQuantum==0 && runningProcess.remainingBurstTime!=0){
                runningProcess.quantum+=2;
                runningProcess.remainingQuantum=runningProcess.quantum;
            }
            if (runningProcess.remainingQuantum!=0 && runningProcess.remainingBurstTime!=0){
                runningProcess.quantum+=runningProcess.remainingQuantum;
                runningProcess.remainingQuantum=runningProcess.quantum;
            }
            if (runningProcess.remainingBurstTime!=0){
                runningProcess.quantum=0;
                runningProcess.remainingQuantum=0;
                deadList.add(runningProcess);
            }
            readyQueue.remove(runningProcess);
        }
    }

    public void processAGAT(){
        // update ready queue with last arrived processes.
        while (deadList.size()!=processes.size()){
            executeProcess();
        }

    }
}
