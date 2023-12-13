import java.util.*;

public class View {
    public void view()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        System.out.print("Enter Round Robin Time Quantum: ");
        int quantumTime = scanner.nextInt();
        System.out.print("Enter Context Switching Time: ");
        int contextSwitchingTime = scanner.nextInt();
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter details for process " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("Color: ");
            String color= scanner.next();
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            System.out.print("Priority: ");
            int priority = scanner.nextInt();

            Process process = new Process(name,color, arrivalTime, burstTime, priority, quantumTime);
            processes.add(process);
        }
        // Sort processes based on arrival time
        Collections.sort(processes, Comparator.comparingInt(p -> p.getArrival_time()));
        // Run schedulers
        System.out.println("Enter Which Scheduling Algorithm You want to Apply ");
        System.out.println("For SJF Scheduler Enter --------------> 1");
        System.out.println("For SRTF Scheduler Enter -------------> 2");
        System.out.println("For Priority Scheduler Enter ---------> 3");
        System.out.println("For AG Scheduler Enter ---------------> 4");
        int ch=scanner.nextInt();
        if(ch==1) {
            System.out.println("-------------------------Test for SJF Scheduler-------------------------");
            SJF sjf = new SJF(processes, contextSwitchingTime);
            sjf.runSJFScheduler();
        }
        else if(ch==2){
            System.out.println("-------------------------Test for SRTF Scheduler-------------------------");
            //SRTF srtf = new SRTF(processes, contextSwitchingTime);
            //srtf.runSRTFScheduler();
        }
        else if(ch==3) {
            System.out.println("-------------------------Test for Priority Scheduler-------------------------");
            PriorityScheduling priorityScheduling = new PriorityScheduling();
            priorityScheduling.runPriorityScheduler(processes);
        }
        else if (ch==4) {
            System.out.println("-------------------------Test for AG_Scheduler-------------------------");
            //ArrayList<Process> test = new ArrayList<>();
            //test.add(new Process("P1","ss", 0,17,4, 4));
            //test.add(new Process("P2","ss", 3,6,9, 4));
            //test.add(new Process("P3","ss", 4,10,2, 4));
            //test.add(new Process("P4","ss", 29,4,8, 4));
            AG_Scheduler agScheduler = new AG_Scheduler(processes, 0);
            agScheduler.runAGScheduler();
        }
        else{
            System.out.println("Invalid Choice");
        }
        scanner.close();
    }
}
