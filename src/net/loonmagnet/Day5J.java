package net.loonmagnet;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;

public class Day5J {

    public static void main(String... args) {
        HashMap<String, ArrayDeque<String>> stackMap = initializeStackMap();
        var lines = Utils.readFile("data/day5.txt");
        loadStacks(stackMap, lines);

        //Part 1
        for (int i = 10; i < lines.size(); i++) {
            String[] moves = lines.get(i).split("[a-z ]+");
            var containers = Integer.valueOf(moves[1]);
            var source = stackMap.get(moves[2]);
            var target = stackMap.get(moves[3]);
            for (int j = 0; j < containers; j++) {
                target.push(source.pop());
            }
        }

        printResult(stackMap);

        //Part 2
        stackMap = initializeStackMap();
        lines = Utils.readFile("data/day5.txt");
        loadStacks(stackMap, lines);

        //Part 1
        for (int i = 10; i < lines.size(); i++) {
            ArrayDeque<String> tempStack = new ArrayDeque<>();
            String[] moves = lines.get(i).split("[a-z ]+");
            var containers = Integer.valueOf(moves[1]);
            var source = stackMap.get(moves[2]);
            var target = stackMap.get(moves[3]);
            for (int j = 0; j < containers; j++) {
                tempStack.push(source.pop());
            }
            for (int j = 0; j < containers; j++) {
                target.push(tempStack.pop());
            }
        }

        printResult(stackMap);
    }

    private static HashMap<String, ArrayDeque<String>> initializeStackMap() {
        var stackMap = new HashMap<String, ArrayDeque<String>>();
        stackMap.put("1", new ArrayDeque<>());
        stackMap.put("2", new ArrayDeque<>());
        stackMap.put("3", new ArrayDeque<>());
        stackMap.put("4", new ArrayDeque<>());
        stackMap.put("5", new ArrayDeque<>());
        stackMap.put("6", new ArrayDeque<>());
        stackMap.put("7", new ArrayDeque<>());
        stackMap.put("8", new ArrayDeque<>());
        stackMap.put("9", new ArrayDeque<>());
        return stackMap;
    }

    private static void printResult(HashMap<String, ArrayDeque<String>> stackMap) {
        String result = "";
        for(int i = 1; i < 10; i++) {
            result = result + stackMap.get(String.valueOf(i)).peek();
        }
        System.out.println(result);
    }

    private static void loadStacks(HashMap<String, ArrayDeque<String>> stackMap, List<String> lines) {
        for (int i = 7; i >=0; i--) {
            var line = lines.get(i);
            Integer stackIndex = 1;
            for (int j = 1; j < line.length(); j = j+4) {
                String containerId = String.valueOf(line.charAt(j));
                if (!containerId.equals(" ")) {
                    var stack = stackMap.get(stackIndex.toString());
                    stack.push(containerId);
                }
                stackIndex++;
            }
        }
    }
}
