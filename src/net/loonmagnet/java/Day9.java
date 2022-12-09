package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

public class Day9 {

    public static void main(String... args) {

        moveRope(2);
        moveRope(10);
    }

    private static void moveRope(int length) {
        List<Knot> knots = getRope(length);

        Set<Knot> locations = new HashSet<>();
        for(String line : Utils.readFile("data/day9.txt")) {
            String[] fields = line.split(" ");
            for (int i = 0; i < Integer.parseInt(fields[1]); i++) {
                knots.set(0, knots.get(0).move(fields[0]));
                for (int kn = 1; kn < length; kn++) {
                    knots.set(kn, knots.get(kn).follow(knots.get(kn - 1)));
                }
                locations.add(knots.get(length - 1));
            }
        }
        System.out.println(locations.size());
    }

    static List<Knot> getRope(int length) {
        List<Knot> knots = new ArrayList<>();
        for (int i = 0; i < length; i++) knots.add(new Knot(0,0));
        return knots;
    }
}



record Knot(int x, int y) {

    Knot move(String dir) {
        switch (dir) {
            case "U": return new Knot(this.x, this.y + 1);
            case "D": return new Knot(this.x, this.y - 1);
            case "L": return new Knot(this.x - 1, this.y);
            case "R": return new Knot(this.x + 1, this.y);
            default: throw new RuntimeException("Didn't expect that direction!");
        }
    }

    Knot follow(Knot head) {
        int xDist = head.x() - this.x;
        int yDist = head.y() - this.y;
        if (abs(xDist) == 2) {
            int newX = this.x + Integer.signum(xDist);
            int newY = this.y + (yDist == 0 ? 0 : Integer.signum(yDist));
            return new Knot(newX, newY);
        } else if (abs(yDist) == 2) {
            int newX = this.x + (xDist == 0 ? 0 : Integer.signum(xDist));
            int newY = this.y + Integer.signum(yDist);
            return new Knot(newX, newY);
        }
        return this;
    }
}

