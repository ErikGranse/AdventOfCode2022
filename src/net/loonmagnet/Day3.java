package net.loonmagnet;

import java.util.*;

public class Day3 {

    public static void main(String... args) {
        var rucksacks = Utils.readFile("data/day3.txt");

        int priorityTotal = 0;
        for (var rucksack : rucksacks) {
            Character[] chars = rucksack.chars()
                    .mapToObj(c -> (char) c).toArray(Character[]::new);

            int splitSize = chars.length / 2;

            Set<Character> compartment1 = new HashSet<>(List.of(Arrays.copyOfRange(chars, 0, splitSize)));
            Set<Character> compartment2 = new HashSet<>(List.of(Arrays.copyOfRange(chars, splitSize, chars.length)));

            compartment1.retainAll(compartment2);
            Character[] value = compartment1.toArray(new Character[0]);
            int asciiValue = Utils.convertToNumeric(value[0]);
            priorityTotal += asciiValue;
        }
        System.out.println(priorityTotal);

        priorityTotal = 0;
        for (int i = 0; i < rucksacks.size(); i += 3) {
            Set<Character> compartment1 = getCharacters(rucksacks, i);
            Set<Character> compartment2 = getCharacters(rucksacks, i+1);
            Set<Character> compartment3 = getCharacters(rucksacks, i+2);

            compartment1.retainAll(compartment2);
            compartment1.retainAll(compartment3);
            Character[] value = compartment1.toArray(new Character[0]);
            priorityTotal += Utils.convertToNumeric(value[0]);
        }
        System.out.println(priorityTotal);
    }

    private static Set<Character> getCharacters(List<String> rucksacks, int i) {
        Character[] chars = rucksacks.get(i).chars()
                .mapToObj(c -> (char) c).toArray(Character[]::new);

        return new HashSet<>(List.of(chars));
    }

}
