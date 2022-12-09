package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {


    public static void main(String ... args) {
        takeTwo(null);
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

    private static void takeTwo(List<String> data) {

        List<String> intList = List.of("a", "b", "c", "", "d", "", "e", "f");

        int[] indexes =
                Stream.of(IntStream.of(-1), IntStream.range(0, intList.size()).filter(i -> intList.get(i).isBlank()), IntStream.of(intList.size())).flatMapToInt(s -> s).toArray();
        List<List<String>> subSets =
                IntStream.range(0, indexes.length - 1)
                        .mapToObj(i -> intList.subList(indexes[i] + 1, indexes[i + 1]))
                        .collect(Collectors.toList());

//        List<Integer> lastPartition = subSets.get(2);
//        List<Integer> expectedLastPartition = Lists.<Integer> newArrayList(7, 8);
//        assertThat(subSets.size(), equalTo(3));
//        assertThat(lastPartition, equalTo(expectedLastPartition));
        System.out.println(indexes);
        System.out.println(subSets);
    }
}
