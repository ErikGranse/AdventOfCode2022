package net.loonmagnet.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Reads all lines from a file and returns the lines in a list of strings.
     *
     * @param filename the file to be read
     * @return a list of all lines in the file, or an empty list if the file cannot be read
     */
    public static List<String> readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e);
            return Collections.emptyList();
        }
    }

    /**
     * Converts a character in the set {@code [a-Z]} character to a numeric value where `a`  = 1 and `Z` = 52. Conversion is
     * based on ASCII, so this function will return values for non-ASCII or non-alpha characters, but those values are not
     * anticipated for the purposes of this function.

     * @param character which should be lower- or upper-case A through Z
     * @return the 1-based index of an ordered array from `a` to `Z`
     */
    public static int convertToNumeric(Character character) {
        int asciiValue = (int) character;
        return asciiValue > 90 ? asciiValue - 96 : asciiValue - 38;
    }
}
