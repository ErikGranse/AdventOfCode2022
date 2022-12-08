package net.loonmagnet.kotlin

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.LinkedList
import java.util.Queue


fun findMarker(markerLen: Int, chars: CharArray): Int {
    val currentCharacters: Queue<Char> = LinkedList(mutableListOf())


    return 1;
}

@Test
fun testFindMarker() {
    Assertions.assertEquals(5, findMarker(4, "bvwbjplbgvbhsrlpgdmjqwftvncz".toCharArray()))
    Assertions.assertEquals(6, findMarker(4, "nppdvjthqldpwncqszvftbrmjlhg".toCharArray()))
    Assertions.assertEquals(10, findMarker(4, "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".toCharArray()))
    Assertions.assertEquals(11, findMarker(4, "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw".toCharArray()))

    Assertions.assertEquals(19, findMarker(14, "mjqjpqmgbljsphdztnvjfqwrcgsmlb".toCharArray()))
    Assertions.assertEquals(23, findMarker(14, "bvwbjplbgvbhsrlpgdmjqwftvncz".toCharArray()))
    Assertions.assertEquals(23, findMarker(14, "nppdvjthqldpwncqszvftbrmjlhg".toCharArray()))
    Assertions.assertEquals(29, findMarker(14, "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg".toCharArray()))
    Assertions.assertEquals(26, findMarker(14, "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw".toCharArray()))
}

