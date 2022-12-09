package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

public class Day9 {

    public static void main(String... args) {

        Knot head = new Knot(0,0);
        Knot tail = new Knot(0,0);
        Set<Knot> locations = new HashSet<>();
        for(String line : Utils.readFile("data/day9.txt")) {
            String[] fields = line.split(" ");
            for (int i = 0; i < Integer.parseInt(fields[1]); i++) {
                head = head.move(fields[0]);
                tail = tail.follow(head);
                locations.add(tail);
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

