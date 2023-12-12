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

            Process process = new Process(name,color, arrivalTime, burstTime, priority, quantumTime,contextSwitchingTime);
            processes.add(process);
        }
        // Sort processes based on arrival time
        Collections.sort(processes, Comparator.comparingInt(p -> p.getArrival_time()));

        // Run schedulers
        //runSJFScheduler(processes);
        //runSRTFScheduler(processes);
        //runPriorityScheduler(processes);
        AG_Scheduler agScheduler=new AG_Scheduler(processes);
        agScheduler.runAGScheduler();
        scanner.close();
    }
}
