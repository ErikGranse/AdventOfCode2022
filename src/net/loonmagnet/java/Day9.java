package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.*;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;

public class Day9 {

    public static void main(String... args) {
        moveRope(2);
        moveRope(10);
    }

    private static void moveRope(int length) {
        final List<Knot> knots = new ArrayList<>(Collections.nCopies(length, new Knot(0, 0)));
        final Set<Knot> locations = new HashSet<>();

        for (String line : Utils.readFile("data/day9.txt")) {
            final String[] fields = line.split(" ");
            for (int i = 0; i < Integer.parseInt(fields[1]); i++) {
                knots.set(0, knots.get(0).move(fields[0])); //move head
                for (int kn = 1; kn < length; kn++) {
                    knots.set(kn, knots.get(kn).follow(knots.get(kn - 1)));
                }
                locations.add(knots.get(length - 1));
            }
        }
        System.out.println(locations.size());
    }
}

record Knot(int x, int y) {

    Knot move(String dir) {
        switch (dir) {
            case "U": return new Knot(this.x, this.y + 1);
            case "D": return new Knot(this.x, this.y - 1);
            case "L": return new Knot(this.x - 1, this.y);
            case "R": return new Knot(this.x + 1, this.y);
            default:
                throw new RuntimeException("Didn't expect that direction!");
        }
    }

    Knot follow(Knot head) {
        final int xDist = head.x() - this.x;
        final int yDist = head.y() - this.y;
        if (abs(xDist) == 2) {
            return new Knot(this.x + signum(xDist), this.y + signum(yDist));
        } else if (abs(yDist) == 2) {
            return new Knot(this.x + signum(xDist), this.y + signum(yDist));
        }
        return this;
    }
}

