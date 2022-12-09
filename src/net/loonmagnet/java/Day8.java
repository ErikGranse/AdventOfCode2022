package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class Day8 {

    public static void main(String... args) {

        final var data = Utils.readFile("data/day8.txt");
        final int dimY = data.size();
        final int dimX = data.get(0).length();

        int[][] trees = initialize(data, dimY, dimX);
        System.out.printf("Part 1: %d%n", part1(dimY, dimX, trees));
        System.out.printf("Part 2: %d%n", part2(dimY, dimX, trees));
    }

    protected static int[][] initialize(List<String> data, int dimY, int dimX) {
        int[][] trees = new int[dimX][dimY];

        for (int y = 0; y < dimY; y++) {
            var row = data.get(y).toCharArray();
            for (int x = 0; x < dimX; x++) {
                trees[x][y] = Character.getNumericValue(row[x]);
            }
        }
        return trees;
    }

    protected static int part2(int dimY, int dimX, int[][] trees) {
        int maxVisibility = 0;
        for (int yCoord = 0; yCoord < dimY; yCoord++) {
            for (int xCoord = 0; xCoord < dimX; xCoord++) {
                int totalVisibility = 1;
                int directionalVisibility = 0;
                int height = trees[xCoord][yCoord];

                //look right
                for (int x = xCoord + 1; x < dimX; x++) {
                    directionalVisibility++;
                    if(height <= trees[x][yCoord]) break;
                }
                totalVisibility = totalVisibility * directionalVisibility;
                //look left
                directionalVisibility = 0;
                for (int x = xCoord - 1; x >= 0; x--) {
                    directionalVisibility++;
                    if(height <= trees[x][yCoord]) break;
                }
                totalVisibility = totalVisibility * directionalVisibility;
                //look up
                directionalVisibility = 0;
                for (int y = yCoord + 1; y < dimY; y++) {
                    directionalVisibility++;
                    if(height <= trees[xCoord][y]) break;
                }
                totalVisibility = totalVisibility * directionalVisibility;
                //look down
                directionalVisibility = 0;
                for (int y = yCoord - 1; y >= 0; y--) {
                    directionalVisibility++;
                    if(height <= trees[xCoord][y]) break;
                }
                totalVisibility = totalVisibility * directionalVisibility;

                if (totalVisibility > maxVisibility) maxVisibility = totalVisibility;
            }
        }
        return maxVisibility;
    }

    protected static int part1(int dimY, int dimX, int[][] trees) {
        Set<Knot> visibleTrees = new HashSet<>();

        //left to right
        for (int y = 0; y < dimY; y++) {
            int maxHeight = -1;
            for (int x = 0; x < dimX; x++) {
                maxHeight = checkVisibility(visibleTrees, trees, y, maxHeight, x);
                if (maxHeight == 9) break;
            }
        }
        //right to left
        for (int y = 0; y < dimY; y++) {
            int maxHeight = -1;
            for (int x = dimX - 1; x >= 0; x--) {
                maxHeight = checkVisibility(visibleTrees, trees, y, maxHeight, x);
                if (maxHeight == 9) break;
            }
        }
        //top to bottom
        for (int x = 0; x < dimX; x++) {
            int maxHeight = -1;
            for (int y = 0; y < dimY; y++) {
                maxHeight = checkVisibility(visibleTrees, trees, y, maxHeight, x);
                if (maxHeight == 9) break;
            }
        }
        //bottom to top
        for (int x = 0; x < dimX; x++) {
            int maxHeight = -1;
            for (int y = dimY - 1; y >= 0; y--) {
                maxHeight = checkVisibility(visibleTrees, trees, y, maxHeight, x);
                if (maxHeight == 9) break;
            }
        }

        return visibleTrees.size();
    }

    private static int checkVisibility(Set<Knot> visibleTrees, int[][] trees, int y, int maxHeight, int x) {
        if (trees[x][y] > maxHeight) {
            visibleTrees.add(new Knot(x, y));
            maxHeight = trees[x][y];
        }
        return maxHeight;
    }
}

record Coordinate(int x, int y) {} //Why doesn't Java have tuples built in?!?

class Day8Test {

    static List<String> data;
    static int dimY;
    static int dimX;

    static int[][] trees;

    @BeforeAll
    public static void setup() {
        data = Utils.readFile("data/day8-sample.txt");
        dimY = data.size();
        dimX = data.get(0).length();
        trees = Day8.initialize(data, dimY, dimX);

    }
    @Test
    public void testPart1() {
        Assertions.assertEquals(21, Day8.part1(dimY, dimX, trees));
    }

    @Test
    public void testPart2() {
        Assertions.assertEquals(8, Day8.part2(dimY, dimX, trees));
    }
}
