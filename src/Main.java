import java.util.*;

public class Main {
        public static void main(String[] args) {
                ArrayList<Process> test = new ArrayList<>();
                test.add(new Process(
                                "P1", "ss", 0, 17, 4,
                                4, 20));
                test.add(new Process(
                                "P2", "ss", 0, 3, 1,
                                4, 17));
                test.add(new Process(
                                "P3", "ss", 3, 6, 2,
                                4, 16));
                test.add(new Process(
                                "P4", "ss", 4, 10, 7,
                                4, 43));
                test.add(new Process(
                                "P5", "ss", 29, 4, 3,
                                4, 43));
                PriorityScheduling aa = new PriorityScheduling();
                aa.runPriorityScheduler(test);
        }
}