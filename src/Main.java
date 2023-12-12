import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//       View view = new View();
//       view.view();
        ArrayList<Process> test = new ArrayList<>();
        test.add(new Process(
                "P1","ss", 0,17,4,
                4,0,20));
        test.add(new Process(
                "P2","ss", 3,6,9,
                4,0,17));
        test.add(new Process(
                "P3","ss", 4,10,2,
                4,0,16));
        test.add(new Process(
                "P4","ss", 29,4,8,
                4,0,43));
        AG_Scheduler agScheduler = new AG_Scheduler(test);
        agScheduler.runAGScheduler();
    }
}