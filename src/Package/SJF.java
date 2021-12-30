package Package;



import java.util.*;


public class SJF {
	static ArrayList<Process> allProcesses = new ArrayList<Process>();
	public static void sch() {	
		int n= allProcesses.size(); //number of processes
		String pname[] = new String[n]; // process name
		int arrivalTime[] = new int[n]; 
		int burstTime[] = new int[n]; 
		int ct[] = new int[n]; //complete time
		int turnaround[] = new int[n];
		int waitingTime[] = new int[n];  
		int f[] = new int[n];  
		int st=0, tot=0;
		float avgwt=0, avgta=0;
		
		for (int i=0;i<n;i++)
	    {
	    	pname[i]= allProcesses.get(i).name;
	    	arrivalTime[i]= allProcesses.get(i).arrivalTime;
	    	burstTime[i]= allProcesses.get(i).burstTime;
	   
	    }
		boolean a = true;
		while(true)
		{
		int index=n, min=999;
		if (tot == n)
		break;
		for (int i=0; i<n; i++)
		{
		
		if ((arrivalTime[i] <= st) && (f[i] == 0) && (burstTime[i]<min))
		{
		min=burstTime[i];
		index=i;
		}
		}
		
		if (index==n)
		st++;
		else
		{
		ct[index]=st+burstTime[index];
		st+=burstTime[index];
		turnaround[index]=ct[index]-arrivalTime[index];
		waitingTime[index]=turnaround[index]-burstTime[index];
		f[index]=1;
		tot++;
		System.out.print(pname[index]+ " | ");
		}
		}
		System.out.println();
		System.out.println("p_name \t|   arrival \t|   burst \t|  turnaround \t|    waiting");
		for(int i=0;i<n;i++)
		{
		avgwt+= waitingTime[i];
		avgta+= turnaround[i];

		System.out.println(pname[i] +"\t|\t"+ arrivalTime[i]+"\t|\t"+ burstTime[i]  +"\t|\t"+ turnaround[i] +"\t|\t"+ waitingTime[i]);
		}
		System.out.println ("\nAverage turnaround time is "+ (float)(avgta/n));
		System.out.println ("Average waiting time is "+ (float)(avgwt/n));
	}
		
	 
	

public static void main(String args[])
{
	SJF.allProcesses = new ArrayList<Process>();
	Scanner sc = new Scanner(System.in);
System.out.println ("enter no of process:");
int x = sc.nextInt();
int arrivalTime;
int burstTime;
String name;

 
for(int i=0;i<x;i++)
{
	System.out.println("enter process " + (i+1) + " name:");
	name = sc.next();
System.out.println ("enter process " + (i+1) + " arrival time:");
arrivalTime = sc.nextInt();

System.out.println ("enter process " + (i+1) + " brust time:");
burstTime = sc.nextInt();

SJF.allProcesses.add(new Process(name, arrivalTime, burstTime));
}
SJF.sch();
sc.close();

}
}