import java.util.*;

public class PriorityScheduling {

    public void runPriorityScheduler(List<Process> processList) {
        List<Process> originalProcessList = new ArrayList<>(processList);
        Map<Process, Integer> originalPriorities = new HashMap<>();
        for (Process process : originalProcessList) {
            originalPriorities.put(process, process.getPriority_number());
        }
        int numProcesses = processList.size();

        Collections.sort(processList, Comparator.comparingInt(Process::getArrival_time));

        PriorityQueue<Process> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(Process::getPriority_number));

        int timer = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (!processList.isEmpty() || !priorityQueue.isEmpty()) {
            // Add processes with the same arrival time to the priority queue
            while (!processList.isEmpty() && processList.get(0).getArrival_time() <= timer) {
                priorityQueue.add(processList.remove(0));
            }

            if (!priorityQueue.isEmpty()) {
                // Get the process with the highest priority from the priority queue
                Process currentProcess = priorityQueue.poll();

                int waitingTime = timer - currentProcess.getArrival_time();
                int turnaroundTime = waitingTime + currentProcess.getBurst_time();
                System.out.println("------------------");

                System.out.println("Process " + currentProcess.getName() + " finished at time "
                        + (timer + currentProcess.getBurst_time()));
                System.out.println("  Waiting Time: " + waitingTime);
                System.out.println("  Turnaround Time: " + turnaroundTime);

                // Update timer
                timer += currentProcess.getBurst_time();
                totalWaitingTime += waitingTime;
                totalTurnaroundTime += turnaroundTime;
            }
        }

        // Restore original priorities without any edits
        for (Process process : originalProcessList) {
            process.setPriority_number(originalPriorities.get(process));
        }

        processList.clear();
        processList.addAll(originalProcessList);

        double avgWaitingTime = (double) totalWaitingTime / numProcesses;
        double avgTurnaroundTime = (double) totalTurnaroundTime / numProcesses;
        System.out.println("==================================");
        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);

    }
}