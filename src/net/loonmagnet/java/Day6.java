package net.loonmagnet.java;

import net.loonmagnet.util.Utils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static net.loonmagnet.java.Day6.findMarker;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6 {

    public static void main(String... args) {

        var data = Utils.readFile("data/day6.txt");

        var chars = data.get(0).toCharArray();
        System.out.println(findMarker(4, chars));
        System.out.println(findMarker(14, chars));
    }

    protected static int findMarker(int minLength, char[] chars) {
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
        return pos;
    }
}

class Day6Test {

    @Test
    public void testValues() {
        assertEquals(5, findMarker(4, "bvwbjplbgvbhsrlpgdmjqwftvncz".toCharArray()));
        assertEquals(6, findMarker(4, "nppdvjthqldpwncqszvftbrmjlhg".toCharArray()));
        assertEquals(10, findMarker(4, "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".toCharArray()));
        assertEquals(11, findMarker(4, "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw".toCharArray()));

        assertEquals(19, findMarker(14, "mjqjpqmgbljsphdztnvjfqwrcgsmlb".toCharArray()));
        assertEquals(23, findMarker(14, "bvwbjplbgvbhsrlpgdmjqwftvncz".toCharArray()));
        assertEquals(23, findMarker(14, "nppdvjthqldpwncqszvftbrmjlhg".toCharArray()));
        assertEquals(29, findMarker(14, "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".toCharArray()));
        assertEquals(26, findMarker(14, "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw".toCharArray()));
    }
}