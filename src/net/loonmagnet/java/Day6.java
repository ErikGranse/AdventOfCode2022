package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.*;

public class Day6 {

    public static void main(String... args) {

        var data = Utils.readFile("data/day6.txt");
        var chars = data.get(0).toCharArray();

        var pos = 0;
        Queue<Character> characters = new LinkedList<>(Arrays.asList(' ', ' ', ' ', ' '));
        for (char val : chars) {
            pos++;
            characters.add(val);
            characters.remove();
            Set<Character> charset = new HashSet<>(characters);
            if (charset.size() == 4 && pos >= 4) {
                break;
            }
        }
        System.out.println(pos);
    }
}
