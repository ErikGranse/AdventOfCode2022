package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.*;
import static java.lang.System.*;

public class Day7 {

    public static void main(String... args) {

        final Stack<String> stack = new Stack<>();
        final Map<String, Long> directories = new HashMap<>();

        List<String> data = new LinkedList<>(Utils.readFile("data/day7.txt"));

        for (var action : data) {
            var params = action.replace("$ ", "").split(" ");
            var command = params[0];

            switch (command) {
                case "cd" -> {
                    if (params[1].equals("..")) {
                        moveUp(stack, directories);
                    } else {
                        moveDown(stack, directories, params[1]);
                    }
                }
                case "dir", "ls" -> {
                    //noop
                }
                default -> directories.put(pwd(stack), Long.parseLong(command) + directories.get(pwd(stack)));
            }
        }

        do {
            moveUp(stack, directories);
        } while (stack.size() > 1);

        out.println(directories.keySet().stream().map(directories::get).filter(s -> s <= 100000).reduce(Long::sum).get());
        long neededSpace = 30000000 - (70000000 - directories.keySet().stream().map(directories::get).max(Long::compare).get());
        out.println(directories.keySet().stream().map(directories::get).filter(s -> s >= neededSpace).min(Long::compare).get());
    }

    private static String pwd(Stack<String> stack) {
        return stack.hashCode() + stack.peek();
    }

    private static void moveUp(Stack<String> stack, Map<String, Long> directories) {
        long size = directories.get(pwd(stack));
        if (stack.size() > 1) {
            stack.pop();
        }
        directories.put(pwd(stack), size + directories.get(pwd(stack)));
    }

    private static void moveDown(Stack<String> stack, Map<String, Long> directories, String dirName) {
        stack.add(dirName);
        directories.put(stack.hashCode() + dirName, 0L);
    }
}
