package net.loonmagnet.java;

import net.loonmagnet.util.Utils;

import java.util.HashSet;
import java.util.Set;

public class Day8 {

    public static void main(String... args) {

        final var data = Utils.readFile("data/day8.txt");
        final int dimY = data.size();
        final int dimX = data.get(0).length();

        int[][] trees = new int[dimX][dimY];

        for (int y = 0; y < dimY; y++) {
            var row = data.get(y).toCharArray();
            for (int x = 0; x < dimX; x++) {
                trees[x][y] = Character.getNumericValue(row[x]);
            }
        }
        System.out.printf("Part 1: %d%n", part1(dimY, dimX, trees));
        System.out.printf("Part 2: %d%n", getMaxVisibility(dimY, dimX, trees));
    }

    private static int getMaxVisibility(int dimY, int dimX, int[][] trees) {
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

    private static int part1(int dimY, int dimX, int[][] trees) {
        Set<Coordinate> visibleTrees = new HashSet<>();

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

    private static int checkVisibility(Set<Coordinate> visibleTrees, int[][] trees, int y, int maxHeight, int x) {
        if (trees[x][y] > maxHeight) {
            visibleTrees.add(new Coordinate(x, y));
            maxHeight = trees[x][y];
        }
        return maxHeight;
    }
}

record Coordinate(int x, int y) {} //Why doesn't Java have tuples built in?!?
