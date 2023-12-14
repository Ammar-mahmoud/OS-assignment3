import java.nio.file.WatchEvent;
import java.sql.Time;
import java.util.*;

public class SRTF {
    private Map<String,Integer> WaitingTimes;
    private Map<String,Integer> TurnaroundTimes;
    private int CurrentTimer;
    private List<Process> processList;
    private Queue<String> ProcessOrder;

    public SRTF(List<Process> processList) {
        this.WaitingTimes = new HashMap<>();
        for(Process process:processList){
            WaitingTimes.put(process.getName(),process.getBurst_time());
        }
        this.TurnaroundTimes = new HashMap<>();
        this.CurrentTimer = 0;
        this.processList = processList;
        this.ProcessOrder = new ArrayDeque<>();
    }
    public static Process findMinBurstTimeProcess(List<Process> processes) {
        if (processes == null || processes.isEmpty()) {
            return null;
        }
        Process minBurst = processes.get(0);
        for (Process process : processes) {
            if (process.getBurst_time() < minBurst.getBurst_time()) {
                minBurst = process;
            }
        }
        return minBurst;
    }

    private void PrintOrder(Queue<String> processOrder) {
        System.out.println("-----\nThe order of the Process: ");
        int devices = 1;
        for (String p : processOrder){
            System.out.println(devices + " - Process => " + p);
            devices++;
        }
        System.out.println("------");
    }

    private void PrintWatingTime(Map<String, Integer> waitingTimes) {
        System.out.println("The Process WaitingTime: ");
        double devices = 1;
        double TotalWaitingTime = 0;
        for (Map.Entry<String, Integer> entry : waitingTimes.entrySet()){
            System.out.println("Waiting Time for " + entry.getKey() + " is => " + entry.getValue());
            TotalWaitingTime += entry.getValue();
            devices++;
        }
        System.out.println("-----");
        System.out.println("Average Waiting Time is: " + ((double)TotalWaitingTime / (double)(devices-1)));
        System.out.println("-----");
    }

    private void PrintTurnaroudTime(Map<String, Integer> turnaroundTimes) {
        System.out.println("The Process Turnaround: ");
        double devices = 0;
        double TotalTurnaourndTime = 0;
        for (Map.Entry<String, Integer> entry : turnaroundTimes.entrySet()){
            System.out.println("Turnaround Time for " + entry.getKey() + " is => " + entry.getValue());
            devices++;
            TotalTurnaourndTime += entry.getValue();
        }
        System.out.println("-----");
        System.out.println("Average Turnaround Time is: " + ((double)TotalTurnaourndTime / (double)(devices)));
        System.out.println("-----");
    }

    public void runSRTFScheduler() {
        while (!processList.isEmpty()) {
            List<Process> currentProcesses = new ArrayList<>();
            for (Process process : processList) {
                if (process.getArrival_time() <= CurrentTimer) {
                    currentProcesses.add(process);
//                    process.setPriority_number(process.getPriority_number() + 1);
                }
            }

            if (!currentProcesses.isEmpty()) {
                Process currentTask = Collections.min(currentProcesses, Comparator.comparingInt(Process::getBurst_time));
                String currentTaskName = currentTask.getName();
                ProcessOrder.add(currentTask.getName());
                if (!WaitingTimes.containsKey(currentTaskName)) {
                    WaitingTimes.put(currentTaskName, CurrentTimer - currentTask.getArrival_time() - WaitingTimes.get(currentTaskName));
                }
                currentTask.setBurst_time(currentTask.getBurst_time() - 1);
                CurrentTimer++;

                if (currentTask.getBurst_time() == 0) {
                    processList.remove(currentTask);
                    TurnaroundTimes.put(currentTaskName, CurrentTimer - currentTask.getArrival_time());
                }
            }
            else {
                CurrentTimer++; // Increment the timer if there are no eligible processes
            }
        }
        PrintOrder(ProcessOrder);
        PrintWatingTime(WaitingTimes);
        PrintTurnaroudTime(TurnaroundTimes);
    }
}
