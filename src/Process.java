import java.util.Random;

public class Process {
    private String Name;
    private String color;
    private int arrival_time;
    private int burst_time;
    private int priority_number;
    private int quantum_time;
    private int AG_Factor;
    private int ID; // Make ID an instance variable

    // Use a static variable to keep track of the next available ID
    private static int nextID = 1;

    public Process(String Name, String color, int arrival_time, int burst_time, int priority_number, int quantumTime) {
        this.Name = Name;
        this.color = color;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.priority_number = priority_number;
        this.quantum_time = quantumTime;
        this.AG_Factor = calcAGFactor();
        this.ID = nextID++; // Assign unique ID and increment nextID
    }

    public Process(String name, String color, int arrival_time, int burst_time, int priority_number, int quantum_time,
            int AG_Factor) {
        Name = name;
        this.color = color;
        this.arrival_time = arrival_time;
        this.burst_time = burst_time;
        this.priority_number = priority_number;
        this.quantum_time = quantum_time;
        this.AG_Factor = AG_Factor;
    }

    private int calcAGFactor() {
        Random random = new Random();
        int RF = random.nextInt(20 + 1);
        if (RF < 10) {
            return RF + burst_time + arrival_time;
        } else if (RF > 10) {
            return 10 + burst_time + arrival_time;
        } else {
            return priority_number + burst_time + arrival_time;
        }
    }

    public String getName() {
        return Name;
    }

    public String getColor() {
        return color;
    }

    public int getAG_Factor() {
        return AG_Factor;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public int getBurst_time() {
        return burst_time;
    }

    public int getPriority_number() {
        return priority_number;
    }

    public int getQuantum_time() {
        return quantum_time;
    }

    public void setBurst_time(int burst_time) {
        this.burst_time = burst_time;
    }

    public void setPriority_number(int priority_number) {
        this.priority_number = priority_number;
    }

    public void setQuantum_time(int quantum_time) {
        this.quantum_time = quantum_time;
    }

    public int getID() {
        return ID;
    }
}