package Package;


import java.util.ArrayList;
import java.util.Scanner;

class SRTF{
	 static ArrayList<Process> allProcesses = new ArrayList<Process>();
	 static int contextSwitching=0;
	 
	 public void setContext(int contextSwitching) {
	    	SRTF.contextSwitching = contextSwitching;
	    }
	 public int getContext() {
		 return contextSwitching;
	 }
public static void SRTFScheduling() {
		int n= allProcesses.size(); //number of processes
		String pname[] = new String[n]; // process name
		int arrivalTime[] = new int[n];  
		int burstTime[] = new int[n];  
		int completionTime[] = new int[n];  
		int turnaround[] = new int[n];  
		int waitingTime[] = new int[n];  
		int isCompleted[]  = new int[n];  //  1 >> true , 0>> false
		int originalBurst[]  = new int[n];  //stores the original burst before execution
	    int i, startTime=0, total=0; //total number of completed processes
	    float avgWaitingTime=0, avgTurnaroundTime=0;
	    
	    
	   
 
	    for (i=0;i<n;i++)
	    {
	    	pname[i]= allProcesses.get(i).name;
	    	arrivalTime[i]= allProcesses.get(i).arrivalTime;
	    	burstTime[i]= allProcesses.get(i).burstTime;
	    	originalBurst[i]= burstTime[i];
	    	isCompleted[i]= 0;
	    }
	    
	    while(true){
	    	int min=200, index=n;
	    	if (total==n)
	    		break;
	    	
	    	for ( i=0; i<n; i++)
	    	{
	    		if ((arrivalTime[i]<=startTime) && (isCompleted[i]==0) && (burstTime[i]<min))
	    		{	
	    			min=burstTime[i];
	    			index=i;
	    		}
	    	}
	    	
	    	if (index == n) //if there is no process at time 0 we will increase the start time
	    		startTime++;
	    	else
	    	{
	    		burstTime[index]--; //the process with the shortest burst time starts to execute hence we decrease the burst time of it.
	    		System.out.print(pname[index]+ " | ");
	    		startTime++; // 0 > 1 > 2
	    		if (burstTime[index]==0) //finished
	    		{
	    			isCompleted[index]=1; //true
	    			startTime+=contextSwitching;
	    			completionTime[index]= startTime;
	    			total++; //increase total completed processes
	    			/*for(int y=0; y<n; y++) {
	    				burstTime[y]--; //Aging >> decrease burst time for all processes as time passes to avoid starvation 
	    			}*/
	    			
	    		}
	    	}
	    }
	    
	    for(i=0; i<n; i++)
	    {
	    	turnaround[i] = completionTime[i] - arrivalTime[i];
	    	waitingTime[i] = turnaround[i] - originalBurst[i];
	    	avgWaitingTime+= waitingTime[i];
	    	avgTurnaroundTime+= turnaround[i];
	    }
	    System.out.println();
	    System.out.println("p_name \t|   arrival \t|   burst \t|  turnaround \t|    waiting");
	    for(i=0; i<n; i++)
	    {
	    	System.out.println(pname[i] +"\t|\t"+ arrivalTime[i]+"\t|\t"+ originalBurst[i]  +"\t|\t"+ turnaround[i] +"\t|\t"+ waitingTime[i]);
	    }
	    
	    System.out.println("\nAverage turnaround time is: "+ (float)(avgTurnaroundTime/n));
	    System.out.println("Average waiting time is: "+ (float)(avgWaitingTime/n));
	   
	}

public static void main (String[] args) {
	SRTF.allProcesses = new ArrayList<Process>();
	SRTF sjf = new SRTF();
 int numOfPros;
int arrivalTime;
 int burstTime;
 String name;
 int context;
 
 Scanner scan = new Scanner(System.in);
 System.out.println("Enter the number of processes:   ");
 numOfPros = scan.nextInt();
 
 for (int i=0; i<numOfPros; ++i) {
	 System.out.println("Enter the process name, arrival time and burst time:  ");
	 name = scan.next();
	 arrivalTime = scan.nextInt(); 
	 burstTime = scan.nextInt();
	 
     
	 SRTF.allProcesses.add(new Process(name, arrivalTime, burstTime));

 }
 System.out.println("Enter context switching: ");
 context = scan.nextInt();
 sjf.setContext(context);
 SRTF.SRTFScheduling();
 scan.close();
 
}


}