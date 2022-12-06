package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.*;

public class Day6 {

    public static void main(String... args) {

        var data = Utils.readFile("data/day6.txt");

        var chars = data.get(0).toCharArray();
        findMarker(4, chars);
        findMarker(14, chars);
    }

    private static void findMarker(int minLength, char[] chars) {
        var pos = 0;

        Queue<Character> characters = new LinkedList<>();
        for (int i = 0; i < minLength; i ++) {
            characters.add(' ');
        }
        for (char val : chars) {
            pos++;
            characters.add(val);
            characters.remove();
            Set<Character> charset = new HashSet<>(characters);
            if (charset.size() == minLength && pos >= minLength) {
                break;
            }
        }
        System.out.println(pos);
    }
}
