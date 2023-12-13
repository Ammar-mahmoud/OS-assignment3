import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SJF {
    private List<Process> processes;
    private int contextSwitchingTime;
    private int currentTime;
    private PriorityQueue<Process> readyQueue;

    public SJF(List<Process> processList, int contextSwitchingTime) {
        this.processes = processList;
        this.contextSwitchingTime = contextSwitchingTime;
        this.currentTime = 0;
        this.readyQueue = new PriorityQueue<>(Comparator
                .comparingInt(Process::getBurst_time)
                .thenComparingInt(Process::getArrival_time));
    }

    public void runSJFScheduler() {
        int totalProcesses = processes.size();
        int[] waitingTime = new int[totalProcesses];
        int[] turnaroundTime = new int[totalProcesses];
        System.out.println("Process Execution Order:");

        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            while (!processes.isEmpty() && processes.get(0).getArrival_time() <= currentTime) {
                readyQueue.add(processes.remove(0));
            }

            Process shortestJob = readyQueue.poll();

            if (shortestJob != null) {
                System.out.println("Executing Process " + shortestJob.getID() + " at time " + currentTime);

                waitingTime[shortestJob.getID() - 1] = currentTime - shortestJob.getArrival_time();
                currentTime += shortestJob.getBurst_time();
                turnaroundTime[shortestJob.getID() - 1] = currentTime - shortestJob.getArrival_time();

                currentTime += contextSwitchingTime;
            } else {
                currentTime++;
            }
        }
        printResults(waitingTime, turnaroundTime);
    }

    private void printResults(int[] waitingTime, int[] turnaroundTime) {
        System.out.println("\nWaiting Time for each process:");
        for (int i = 0; i < waitingTime.length; i++) {
            System.out.println("Process " + (i + 1) + ": " + waitingTime[i]);
        }

        System.out.println("\nTurnaround Time for each process:");
        for (int i = 0; i < turnaroundTime.length; i++) {
            System.out.println("Process " + (i + 1) + ": " + turnaroundTime[i]);
        }

        double avgWaitingTime = calculateAverage(waitingTime);
        double avgTurnaroundTime = calculateAverage(turnaroundTime);

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    private double calculateAverage(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }
}
