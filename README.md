<!DOCTYPE html>
<html>
<head>
</head>
<body>
  <h1>CPU Schedulers Simulator</h1>

  <h2>Table of Contents</h2>
  <ul>
    <li><a href="#introduction">Introduction</a></li>
    <li><a href="#implemented-algorithms">Implemented Algorithms</a></li>
    <li><a href="#graphical-user-interface">Graphical User Interface</a></li>
    <li><a href="#project-overview">Project Overview</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
  </ul>

  <h2 id="introduction">Introduction</h2>
  <p>
    Scheduling is a fundamental function of operating systems, and CPU scheduling plays a crucial role in resource utilization and system performance.
    The CPU Schedulers Simulator is a Java program that simulates various CPU scheduling algorithms.
    This project aims to provide a better understanding of different scheduling algorithms and their impact on process execution.
  </p>

  <h2 id="implemented-algorithms">Implemented Algorithms</h2>
  <p>
    The simulator currently implements the following CPU scheduling algorithms:
  </p>
  <ol>
    <li>Non-Preemptive Shortest-Job First (SJF) with context switching</li>
    <li>Shortest-Remaining Time First (SRTF) Scheduling with starvation prevention</li>
    <li>Non-preemptive Priority Scheduling with starvation prevention</li>
    <li>
      AG Scheduling:
      <ul>
        <li>Round Robin (RR) Scheduling with AG-Factor</li>
        <li>AG-Factor calculation based on a combination of priority, arrival time, burst time, and a random function</li>
        <li>Preemptive and non-preemptive scheduling based on AG-Factor</li>
      </ul>
    </li>
  </ol>

  <h2 id="graphical-user-interface">Graphical User Interface</h2>
  <p>
    The simulator features a graphical user interface (GUI) that provides a Simple visual representation of the process execution order.
  </p>

  <h2 id="project-overview">Project Overview</h2>
  <p>
    The CPU Schedulers Simulator allows you to input the necessary parameters for the simulation.
    These parameters include the number of processes, time quantum for round-robin scheduling, and context switching time.
    For each process, you provide the following details:
  </p>
  <ul>
    <li>Process Name</li>
    <li>Process Color (for graphical representation)</li>
    <li>Process Arrival Time</li>
    <li>Process Burst Time</li>
    <li>Process Priority Number (for priority scheduling)</li>
  </ul>
  <p>
    The simulator outputs the execution order of processes, waiting times, turnaround times, average waiting time, average turnaround time,
    and history updates of quantum time for AG Scheduling.
    Additionally, it generates a graphical representation of the process execution order.
  </p>

  <h2 id="usage">Usage</h2>
  <ol>
    <li>Clone the repository: <code>git clone https://github.com/your-username/cpu-schedulers-simulator.git</code></li>
    <li>Compile the Java source files: <code>javac *.java</code></li>
    <li>Run the simulator: <code>java Main</code></li>
  </ol>

  <h2 id="contributing">Contributing</h2>
  <p>
    Contributions to the CPU Schedulers Simulator project are welcome.
    If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request on the project's GitHub repository.
  </p>

</body>
</html>
