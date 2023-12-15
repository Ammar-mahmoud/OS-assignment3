import java.util.*;

public class PriorityScheduling {
    public void runPriorityScheduler(List<Process> processList) {
        int numProcesses = processList.size();

        Collections.sort(processList, Comparator.comparingInt(Process::getArrival_time));

        // Initialize priority queue to store processes based on priority
        PriorityQueue<Process> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(Process::getPriority_number));

        int timer = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        while (!processList.isEmpty() || !priorityQueue.isEmpty()) {
            // Add processes with the same arrival time
            while (!processList.isEmpty() && processList.get(0).getArrival_time() <= timer) {
                priorityQueue.add(processList.remove(0));
            }

            if (!priorityQueue.isEmpty()) {
                // Get the process with the highest priority
                Process currentProcess = priorityQueue.poll();

                int waitingTime = timer - currentProcess.getArrival_time();
                int turnaroundTime = waitingTime + currentProcess.getBurst_time();

                // Print the process information
                System.out.println("Process " + currentProcess.getName() + " finished at time "
                        + (timer + currentProcess.getBurst_time()));
                System.out.println("  Waiting Time: " + waitingTime);
                System.out.println("  Turnaround Time: " + turnaroundTime);

                // Update timer
                timer += currentProcess.getBurst_time();
                totalWaitingTime += waitingTime;
                totalTurnaroundTime += turnaroundTime;

                priorityQueue.forEach(process -> {
                    int newPriority = Math.max(process.getPriority_number() - 1, 0);
                    process.setPriority_number(newPriority);
                }); // solve starvation problem by decrementing the priorting number by 1
            }
        }

        double avgWaitingTime = (double) totalWaitingTime / numProcesses;
        double avgTurnaroundTime = (double) totalTurnaroundTime / numProcesses;

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }
}
