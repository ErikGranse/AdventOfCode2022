package net.loonmagnet;

import static java.lang.Integer.*;

public class Day4 {

    public static void main(String... args) {
        var data = Utils.readFile("data/day4.txt");

        var inclusive_ranges = 0;
        var overlap_ranges = 0;
        for (var line : data) {
            String[] ranges = line.split("[-,]");
            var leftLower = parseInt(ranges[0]);
            var leftUpper = parseInt(ranges[1]);
            var rightLower = parseInt(ranges[2]);
            var rightUpper = parseInt(ranges[3]);
            if(checkInclusive(leftLower, leftUpper, rightLower, rightUpper)) inclusive_ranges++;
            if(checkOverlap(leftLower, leftUpper, rightLower, rightUpper)) overlap_ranges++;
        }
        System.out.println(inclusive_ranges);
        System.out.println(overlap_ranges);
    }

    private static boolean checkInclusive(int leftLower, int leftUpper, int rightLower, int rightUpper) {
        return ((leftLower <= rightLower && leftUpper >= rightUpper) || (leftLower >= rightLower && leftUpper <= rightUpper));
    }

    private static boolean checkOverlap(int leftLower, int leftUpper, int rightLower, int rightUpper) {
        return checkInclusive(leftLower, leftUpper, rightLower, rightUpper) ||
                (rightLower <= leftLower && leftLower <= rightUpper)
                || (rightLower <= leftUpper && leftUpper <= rightUpper);
    }
}
