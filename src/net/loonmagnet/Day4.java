package net.loonmagnet;

public class Day4 {

    public static void main(String... args) {
        var data = Utils.readFile("data/day4.txt");

        var inclusive_ranges = 0;
        var overlap_ranges = 0;
        for (var line : data) {
            String[] ranges = line.split(",");
            String[] left = ranges[0].split("-");
            String[] right = ranges[1].split("-");
            var leftLower = Integer.parseInt(left[0]);
            var leftUpper = Integer.parseInt(left[1]);
            var rightLower = Integer.parseInt(right[0]);
            var rightUpper = Integer.parseInt(right[1]);
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
