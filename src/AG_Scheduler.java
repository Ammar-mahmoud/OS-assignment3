import java.security.Key;
import java.util.*;

import static java.lang.Math.ceil;

public class AG_Scheduler {
    private Queue<Process> ReadyQueue;
    private List<Process>DieList;
    private Map<String,Integer>WaitingTimes;
    private Map<String,Integer>TurnaroundTimes;
    private List<Process> processList;
    private int Current_time;
    private List<Process> ConcreteprocessList;

    public AG_Scheduler(List<Process> processList) {
        this.DieList = new ArrayList<>();
        this.ReadyQueue=new LinkedList<>();
        this.WaitingTimes=new HashMap<>();
        this.TurnaroundTimes=new HashMap<>();
        this.Current_time=0;
        this.processList = processList;
        this.ConcreteprocessList=processList;
    }
    public void Print_history(Process process){
        System.out.println(process.getName()+" is Running Now");
        //System.out.println("Burst time " + process.getBurst_time());
        System.out.println("Quantum time " + process.getQuantum_time());
        System.out.println("-------------------------------------------");
        //System.out.println("AG Factor " + process.getAG_Factor());
        //System.out.println("Current Time now is "+Current_time);
        //System.out.println("Quantum Time History");
        //for(Process process1:processList){
        // System.out.println(process1.getName()+" "+process1.getQuantum_time());
        //}
    }
    public void updateReady(){
        for(Process process:processList){
            if(process.getArrival_time()==Current_time){
                ReadyQueue.add(process);
            }
        }
    }
    public int CalculateMean(){
        double numOfProcesses=0;
        double sum=0.0;
        for(Process p: processList){
            if (p.getArrival_time() <= Current_time && p.getBurst_time() != 0)
            {
                sum+=p.getQuantum_time();
                numOfProcesses++;
            }
        }
        return (int)ceil(sum/numOfProcesses);
    }

    public Process findProcessWithSmallestAGFactor(Process RunningProcess){
        Process SmallestAGProcess=RunningProcess;
        for(Process process:processList){
            if(process.getArrival_time()<=Current_time && process.getAG_Factor()<SmallestAGProcess.getAG_Factor() && process.getBurst_time()>0){
                SmallestAGProcess=process;
            }
        }
        //System.out.println("process with samllest AG Factor " +SmallestAGProcess.getName());
        return SmallestAGProcess;
    }
    public void runAGScheduler()
    {
        updateReady();
        Process p = ReadyQueue.poll();
        while (DieList.size() < processList.size() )
        {
            if (p.getBurst_time() == 0)
            {
                while (ReadyQueue.isEmpty())
                {
                    Current_time++;
                    updateReady();
                }
                p = ReadyQueue.poll();
            }
            boolean is_stoped = false;
            Print_history(p);
            for (int i = 0; i < p.getQuantum_time(); i++) {
                is_stoped = false;
                if(i<(int)ceil(p.getQuantum_time()/2.0))
                {
                    p.setBurst_time(p.getBurst_time()-1);
                    //System.out.println("in the first part and Burst time is " +p.getBurst_time());
                    //System.out.println("in the first part and Quatum time is " +i);
                }
                else
                {
                    // cheack smallest AG if the running is the smallest // burst time--
                    // continue
                    // if not push in ready and change quant time
                    Process smallProcess = findProcessWithSmallestAGFactor(p);
                    if(smallProcess == p){
                        p.setBurst_time(p.getBurst_time()-1);
                        //System.out.println("in the second  part and working process is  is " +p.getName());
                        //System.out.println("in the second  part and Burst time is " +p.getBurst_time());
                        //System.out.println("in the first part and Quatum time is " +i);
                    }
                    else{
                        //System.out.println("anther process smaller than this ");
                        is_stoped = true;
                        int unused_time=p.getQuantum_time()-i; // check this
                        p.setQuantum_time(p.getQuantum_time()+unused_time);
                        ReadyQueue.add(p);
                        //System.out.println("hello");
                        p = smallProcess;
                        if(p==ReadyQueue.peek()){
                            ReadyQueue.poll();
                        }
                        break;
                    }
                }
                if (p.getBurst_time() == 0)
                {
                    // put into die
                    // change qaunt time
                    // break
                    //System.out.println("process terminated");
                    p.setQuantum_time(0);
                    DieList.add(p);
                    Current_time++;
                    updateReady();
                    WaitingTimes.put(p.getName(),Current_time-p.getArrival_time()-p.getBurst_time());
                    TurnaroundTimes.put(p.getName(),Current_time-p.getArrival_time());
                    break;
                }
                // make sure you handle last loop
                // push in ready queue and call cacl mean function
                if(i==p.getQuantum_time()-1){
                    System.out.println("Quantum of the first process finishes");
                    int addedValue = (int) Math.ceil(CalculateMean() * 0.1);
                    p.setQuantum_time(p.getQuantum_time() + addedValue); // Original Quantum time + 10% of the mean of Quantum
                    ReadyQueue.add(p);
                    Current_time++;
                    updateReady();
                    break;
                }
                //System.out.println("ReadyQueue Before update ready in "+Current_time);
                //for(Process process:ReadyQueue){
                    //System.out.println(process.getName());
                //}
                Current_time++;
                updateReady();
                //System.out.println("ReadyQueue After update ready in "+Current_time);
                //for(Process process:ReadyQueue){
                    //System.out.println(process.getName());
               // }

            }
            if (DieList.size() == processList.size()) break;
            if (!is_stoped) {
                while (ReadyQueue.isEmpty())
                {
                    Current_time++;
                    updateReady();
                }
                p = ReadyQueue.poll();
                if (p.getBurst_time() == 0)
                {
                    while (ReadyQueue.isEmpty())
                    {
                        Current_time++;
                        updateReady();
                    }
                    p = ReadyQueue.poll();
                }
            }
        }
        System.out.println("We Finished in " + Current_time);
        int numofProcess=0;
        double TotalWaitingTime=0.0;
        for (Map.Entry<String, Integer> entry : WaitingTimes.entrySet()) {
            numofProcess = numofProcess + 1;
            String key = entry.getKey();
            Integer value = entry.getValue();
            TotalWaitingTime = TotalWaitingTime + value;
            System.out.println("Waiting Time for " + key + " is : " + value);
        }
        System.out.println("Average Waiting Time is : "+TotalWaitingTime/numofProcess);
        System.out.println("-----------------------------------------");
        double TotalTurnaroundTime=0.0;
        for (Map.Entry<String, Integer> entry : TurnaroundTimes.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            TotalTurnaroundTime=TotalTurnaroundTime+value;
            System.out.println("Turnaround Time for " + key + " is : " + value);
        }
        System.out.println("Average Turnaround Time is : "+TotalTurnaroundTime/numofProcess);

    }
}
