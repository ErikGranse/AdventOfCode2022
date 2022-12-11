package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.*;

public class Day10 {

    public static void main(String ... args) {

        int totCycles = 0;
        int xreg = 1;
        int dataPoints = 0;
        int xPixel = 0;
        int yPixel = 0;
        String[][] pixels = initializeScreen();

        Queue<String[]> operations =
                new LinkedList<>(Utils.readFile("data/day10.txt").stream().map(line -> line.split(" ")).toList());

        Operation operation = getOperation(operations.remove()); //priming read
        while (operation != null) {
            operation.tick();
            totCycles++;
            dataPoints += getDataPoint(totCycles, xreg);

            String pixel = List.of(xreg - 1, xreg, xreg + 1).contains(xPixel) ? "X" : " ";
            pixels[yPixel][xPixel] = pixel;
            xPixel = xPixel < 39 ? xPixel + 1 : 0;
            if (xPixel == 0 ) yPixel = yPixel < 5 ? yPixel + 1 : 0;

            if (operation.isComplete()) {
                xreg += operation.resolve();
                operation = operations.isEmpty() ? null : getOperation(operations.remove());
            }
        }
        System.out.println(dataPoints);
        drawScreen(pixels);
    }

    private static void drawScreen(String[][] pixels) {
        for (int y = 0; y < 6; y++) {
            StringBuffer buffer = new StringBuffer();
            for (int x = 0; x < 40; x++) {
                buffer.append(pixels[y][x]);
            }
            System.out.println(buffer);
        }
    }

    private static String[][] initializeScreen() {
        String[][] px = new String[6][40];
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 40; x++) {
                px[y][x] = " ";
            }
        }
        return px;
    }

    private static int getDataPoint(int totCycles, int xreg) {
        if((totCycles - 20) % 40 == 0) {
            System.out.printf("Cycle: %d; xreg: %d, extended: %d%n", totCycles, xreg, totCycles * xreg);
            return (xreg * totCycles);
        }
        return 0;
    }

    static Operation getOperation(String... args) {
        if (args[0].equals("addx")) return new Operation(2, Integer.parseInt(args[1]));
        else return new Operation(1);
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
