package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {


    public static void main(String ... args) {
        List<Integer> totals = parseValues();
        Collections.sort(totals);
        Collections.reverse(totals);

        System.out.println("Part 1: " + totals.get(0));
        System.out.println("Part 2: " + (Integer) totals.stream().limit(3).mapToInt(Integer::intValue).sum());
    }

    private static List<Integer> parseValues() {
        List<String> rawValues = Utils.readFile("data/day1.txt");
        List<Integer> totals = new ArrayList<>();

        int total = 0;
        for (String value : rawValues) {
            if(value.isEmpty()) {
                totals.add(total);
                total = 0;
            } else {
                total += Integer.parseInt(value);
            }
        }
        totals.add(total);
        return totals;
    }
}
