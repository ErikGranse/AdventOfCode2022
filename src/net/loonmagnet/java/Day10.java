package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Day10 {

    public static void main(String ... args) {

        int totCycles = 0;
        int val = 1;
        var dataPoints = new ArrayList<Integer>();

        Queue<String[]> operations =
                new LinkedList<>(Utils.readFile("data/day10.txt").stream().map(line -> line.split(" ")).toList());

        Operation operation = getOperation(operations.remove());
        while (operation != null) {
            operation.tick();
            totCycles++;
            if((totCycles - 20) % 40 == 0) {
                System.out.printf("Cycle: %d; val: %d, extended: %d%n", totCycles, val, totCycles * val);
                dataPoints.add(val * totCycles);
            }
            if (operation.isComplete()) {
                val += operation.resolve();
                operation = operations.isEmpty() ? null : getOperation(operations.remove());
//                System.out.printf("Operation %d on cycle %d; current value is %d%n", operation.cycles, totCycles, val);
            }

        }

        System.out.println(dataPoints.stream().reduce(Integer::sum));
    }

    static Operation getOperation(String... args) {
        return switch (args[0]) {
            case "addx" -> new Operation(2, Integer.parseInt(args[1]));
            default -> new Operation(1);
        };
    }
}

class Operation {
    int cycles;
    int val;

    Operation(int cycles, int val) {
        this.cycles = cycles;
        this.val = val;
    }

    Operation(int cycles) {
        this.cycles = cycles;
        this.val = 0;
    }

    void tick() { cycles--; }
    boolean isComplete() { return cycles < 1; }
    int resolve() { return val; }
}
