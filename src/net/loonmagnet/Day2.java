package net.loonmagnet;

import java.util.HashMap;
import java.util.Map;

public class Day2 {

    public static void main(String... args) {

        var rounds = Utils.readFile("data/day2.txt");
        System.out.println("Part 1: " + rounds.stream().map(scores1()::get).reduce(Integer::sum).get());
        System.out.println("Part 2: " + rounds.stream().map(scores2()::get).reduce(Integer::sum).get());

    }

    private static Map<String, Integer> scores1() {
        var scores = new HashMap<String, Integer>();

        scores.put("A X", 3 + 1);
        scores.put("A Y", 6 + 2);
        scores.put("A Z", 0 + 3);
        scores.put("B X", 0 + 1);
        scores.put("B Y", 3 + 2);
        scores.put("B Z", 6 + 3);
        scores.put("C X", 6 + 1);
        scores.put("C Y", 0 + 2);
        scores.put("C Z", 3 + 3);

        return scores;
    }

    private static Map<String, Integer> scores2() {
        var scores = new HashMap<String, Integer>();

        scores.put("A X", 0 + 3);
        scores.put("A Y", 3 + 1);
        scores.put("A Z", 6 + 2);
        scores.put("B X", 0 + 1);
        scores.put("B Y", 3 + 2);
        scores.put("B Z", 6 + 3);
        scores.put("C X", 0 + 2);
        scores.put("C Y", 3 + 3);
        scores.put("C Z", 6 + 1);

        return scores;
    }
}
