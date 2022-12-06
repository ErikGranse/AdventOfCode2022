package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.HashMap;
import java.util.Map;

public class Day2 {

    public static void main(String... args) {

        var rounds = Utils.readFile("data/day2.txt");
        System.out.println("Part 1: " + rounds.stream().map(Day2::resolve1).reduce(Integer::sum).get());
        System.out.println("Part 2: " + rounds.stream().map(Day2::resolve2).reduce(Integer::sum).get());
    }

    private static Integer resolve1(String round) {
        String[] values = round.split(" ");
        var them = throwMap().get(values[0]);
        var me = throwMap().get(values[1]);

        if (them + 1 == me || them - 2 == me) {
            return 6 + me;
        } else if (them == me) {
            return 3 + me;
        }
        return me;
    }

    private static Integer resolve2(String round) {
        String[] values = round.split(" ");
        var them = throwMap().get(values[0]);
        var target = throwMap().get(values[1]);

        switch (target) {
            case 1 -> {
                return them == 1 ? them + 2 : them - 1;
            }
            case 2 -> {
                return them + 3;
            }
            case 3 -> {
                return (them == 3 ? them - 2 : them + 1) + 6;
            }
        }
        return 0;
    }
    private static Map<String, Integer> throwMap() {
        var throwMap = new HashMap<String, Integer>();
        throwMap.put("A", 1);
        throwMap.put("B", 2);
        throwMap.put("C", 3);
        throwMap.put("X", 1);
        throwMap.put("Y", 2);
        throwMap.put("Z", 3);
        return throwMap;
    }
}
