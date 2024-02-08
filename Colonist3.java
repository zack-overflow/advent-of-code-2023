import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Colonist3 {

    // would be better to just check when you see a character if it is not a period or digit
    private static HashSet<Character> findSymbols(String filename) {
        HashSet<Character> symbols = new HashSet<>();
        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (!Character.isDigit(c) && c != '.') {
                        symbols.add(c);
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return symbols;
    }

    public static void partNumbers(String filename) {
        int sumPartNumbers = 0;

        HashSet<Character> symbols = findSymbols(filename);

        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            String previousLine = null;
            String currentLine = scanner.nextLine();
            String nextLine;

            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();

                // check current line for symbols
                for (int i = 0; i < currentLine.length(); i++) {
                    char c = currentLine.charAt(i);
                    if (symbols.contains(c)) {
                        // check the box around the symbol for a number

                        // first, check the previous line for numbers above the symbol
                        if (previousLine != null) {
                            for (int j = i - 1; j <= i + 1; j++) {
                                if (Character.isDigit(previousLine.charAt(j))) {
                                    int newPartNumber = 0;
                                    // now do a search to the left and right of the number to find the full number


                                    // for (int k = 0; k+j < previousLine.length(); k++) {
                                    //     if (Character.isDigit(previousLine.charAt(k+j))) {
                                    //         newPartNumber = Integer.parseInt(previousLine.substring(j, j+k+1));
                                    //     } else {
                                    //         System.out.println(newPartNumber);
                                    //         break;
                                    //     }
                                    // }
                                    sumPartNumbers += newPartNumber;
                                }
                            }
                        }

                        // next, check the current line for numbers to the left and right of the symbol

                    }
                }

                

                previousLine = currentLine;
                currentLine = nextLine;
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static int partNumbers2(String filename) {
        int sumPartNumbers = 0;

        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            String previousLine = null;
            String currentLine = scanner.nextLine();
            String nextLine;

            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();

                // check current line for numbers
                for (int i = 0; i < currentLine.length(); i++) {
                    char c = currentLine.charAt(i);
                    if (Character.isDigit(c)) {
                        int firstDigitLoc = i;
                        int newPartNumber = Character.getNumericValue(c);

                        // find whole number
                        while (i+1 < currentLine.length() && Character.isDigit(currentLine.charAt(i+1))) {
                            newPartNumber = newPartNumber * 10 + Character.getNumericValue(currentLine.charAt(i+1));
                            i++;
                        }

                        // check the box around the number for a symbol
                        Boolean isPartNumber = false;

                        if (previousLine != null) {
                            for (int j = firstDigitLoc - 1; j <= i + 1 && j < previousLine.length(); j++) {
                                if (j == -1) {
                                    continue;
                                }

                                if (!Character.isDigit(previousLine.charAt(j)) && previousLine.charAt(j) != '.') {
                                    isPartNumber = true;
                                }
                            }
                        }
                        
                        // check next line for symbols
                        for (int j = firstDigitLoc - 1; j <= i + 1 && j < nextLine.length(); j++) {
                            if (j == -1) {
                                continue;
                            }

                            if (!Character.isDigit(nextLine.charAt(j)) && nextLine.charAt(j) != '.') {
                                isPartNumber = true;
                            }
                        }

                        // check current line for symbols
                        if (firstDigitLoc !=0) {
                            if (currentLine.charAt(firstDigitLoc-1) != '.') {
                                isPartNumber = true;
                            }
                        }

                        if (i+1 < currentLine.length()) {
                            if (currentLine.charAt(i+1) != '.') {
                                isPartNumber = true;
                            }
                        }
                        
                        if (isPartNumber) {
                            sumPartNumbers += newPartNumber;
                        }
                    }
                }

                // next, check the current line for numbers to the left and right of the symbol
                previousLine = currentLine;
                currentLine = nextLine;
            }

            // edge case for last line
            for (int i = 0; i < currentLine.length(); i++) {
                char c = currentLine.charAt(i);
                if (Character.isDigit(c)) {
                    int firstDigitLoc = i;
                    int newPartNumber = Character.getNumericValue(c);

                    // find whole number
                    while (i+1 < currentLine.length() && Character.isDigit(currentLine.charAt(i+1))) {
                        newPartNumber = newPartNumber * 10 + Character.getNumericValue(currentLine.charAt(i+1));
                        i++;
                    }

                    // check the box around the number for a symbol
                    Boolean isPartNumber = false;

                    if (previousLine != null) {
                        for (int j = firstDigitLoc - 1; j <= i + 1 && j < previousLine.length(); j++) {
                            if (j == -1) {
                                continue;
                            }

                            if (!Character.isDigit(previousLine.charAt(j)) && previousLine.charAt(j) != '.') {
                                isPartNumber = true;
                            }
                        }
                    }
                    
                    // check current line for symbols
                    if (firstDigitLoc !=0) {
                        if (currentLine.charAt(firstDigitLoc-1) != '.') {
                            isPartNumber = true;
                        }
                    }

                    if (i+1 < currentLine.length()) {
                        if (currentLine.charAt(i+1) != '.') {
                            isPartNumber = true;
                        }
                    }
                    
                    if (isPartNumber) {
                        sumPartNumbers += newPartNumber;
                    }
                }
            }
            
            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return sumPartNumbers;
    }
    
    public static int partNumbers3(String filename) {
        int sumGearRatios = 0;

        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            String previousLine = null;
            String currentLine = scanner.nextLine();
            String nextLine;

            // keeping track of overall index for part 2
            int currIndex = 0;

            // hashmap to keep track of part number when we see a asterisk
            HashMap<Integer, Integer> partNums = new HashMap<>();

            while (scanner.hasNextLine()) {
                nextLine = scanner.nextLine();

                // check current line for numbers
                for (int i = 0; i < currentLine.length(); i++) {
                    char c = currentLine.charAt(i);
                    if (Character.isDigit(c)) {
                        int firstDigitLoc = i;
                        int newPartNumber = Character.getNumericValue(c);

                        // find whole number
                        while (i+1 < currentLine.length() && Character.isDigit(currentLine.charAt(i+1))) {
                            newPartNumber = newPartNumber * 10 + Character.getNumericValue(currentLine.charAt(i+1));
                            i++;
                        }

                        // check the box around the number for a symbol
                        Boolean isPartNumber = false;

                        if (previousLine != null) {
                            for (int j = firstDigitLoc - 1; j <= i + 1 && j < previousLine.length(); j++) {
                                if (j == -1) {
                                    continue;
                                }

                                if (!Character.isDigit(previousLine.charAt(j)) && previousLine.charAt(j) == '*') {
                                    StringBuilder sb = new StringBuilder(previousLine);
                                    sb.setCharAt(j, 'x');
                                    previousLine = sb.toString();

                                    int relevantIndex = currIndex - currentLine.length() + j;
                                    partNums.put(relevantIndex, newPartNumber);
                                } else if (!Character.isDigit(previousLine.charAt(j)) && previousLine.charAt(j) =='x') {
                                    int retrievedPartNumber = partNums.get(currIndex - currentLine.length() + j);
                                    System.out.println("jaunt");
                                    System.out.println(retrievedPartNumber * newPartNumber);
                                    sumGearRatios += newPartNumber * retrievedPartNumber;

                                    StringBuilder sb = new StringBuilder(previousLine);
                                    sb.setCharAt(j, '$');
                                    previousLine = sb.toString();
                                }
                            }
                        }
                        
                        // check next line for symbols
                        for (int j = firstDigitLoc - 1; j <= i + 1 && j < nextLine.length(); j++) {
                            if (j == -1) {
                                continue;
                            }

                            if (!Character.isDigit(nextLine.charAt(j)) && nextLine.charAt(j) == '*') {
                                StringBuilder sb = new StringBuilder(nextLine);
                                sb.setCharAt(j, 'x');
                                nextLine = sb.toString();

                                int relevantIndex = currIndex + j + currentLine.length();
                                partNums.put(relevantIndex, newPartNumber);
                            } else if (!Character.isDigit(nextLine.charAt(j)) && nextLine.charAt(j) =='x') {
                                int retrievedPartNumber = partNums.get(currIndex + j + currentLine.length());
                                System.out.println("jaunt");
                                System.out.println(retrievedPartNumber * newPartNumber);
                                sumGearRatios += newPartNumber * retrievedPartNumber;

                                StringBuilder sb = new StringBuilder(nextLine);
                                sb.setCharAt(j, '$');
                                nextLine = sb.toString();
                            }
                        }

                        // check current line for symbols
                        if (firstDigitLoc !=0) {
                            if (currentLine.charAt(firstDigitLoc - 1) == '*') {
                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(firstDigitLoc - 1, 'x');
                                currentLine = sb.toString();

                                int relevantIndex = currIndex + firstDigitLoc - 1;
                                partNums.put(relevantIndex, newPartNumber);
                            } else if (currentLine.charAt(firstDigitLoc - 1) =='x') {
                                int retrievedPartNumber = partNums.get(currIndex + firstDigitLoc - 1);
                                System.out.println("jaunt");
                                System.out.println(retrievedPartNumber * newPartNumber);
                                sumGearRatios += newPartNumber * retrievedPartNumber;

                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(firstDigitLoc - 1, '$');
                                currentLine = sb.toString();
                            }
                        }

                        if (i+1 < currentLine.length()) {
                            if (currentLine.charAt(i+1) == '*') {
                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(i+1, 'x');
                                currentLine = sb.toString();

                                int relevantIndex = currIndex + i + 1;
                                partNums.put(relevantIndex, newPartNumber);
                            } else if (currentLine.charAt(i+1) =='x') {
                                int retrievedPartNumber = partNums.get(currIndex + i + 1);
                                System.out.println("jaunt");
                                System.out.println(retrievedPartNumber * newPartNumber);
                                sumGearRatios += newPartNumber * retrievedPartNumber;

                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(i+1, '$');
                                currentLine = sb.toString();
                            }
                        }
                    }
                }

                // prep for processing next line
                previousLine = currentLine;
                currentLine = nextLine;
                currIndex += currentLine.length();
            }

            // edge case for last line
            for (int i = 0; i < currentLine.length(); i++) {
                char c = currentLine.charAt(i);
                if (Character.isDigit(c)) {
                    int firstDigitLoc = i;
                    int newPartNumber = Character.getNumericValue(c);

                    // find whole number
                    while (i+1 < currentLine.length() && Character.isDigit(currentLine.charAt(i+1))) {
                        newPartNumber = newPartNumber * 10 + Character.getNumericValue(currentLine.charAt(i+1));
                        i++;
                    }

                    if (previousLine != null) {
                            for (int j = firstDigitLoc - 1; j <= i + 1 && j < previousLine.length(); j++) {
                                if (j == -1) {
                                    continue;
                                }

                                if (!Character.isDigit(previousLine.charAt(j)) && previousLine.charAt(j) == '*') {
                                    StringBuilder sb = new StringBuilder(previousLine);
                                    sb.setCharAt(j, 'x');
                                    previousLine = sb.toString();

                                    int relevantIndex = currIndex - currentLine.length() + j;
                                    partNums.put(relevantIndex, newPartNumber);
                                } else if (!Character.isDigit(previousLine.charAt(j)) && previousLine.charAt(j) =='x') {
                                    int retrievedPartNumber = partNums.get(currIndex - currentLine.length() + j);
                                    System.out.println("jaunt");
                                    System.out.println(retrievedPartNumber * newPartNumber);
                                    sumGearRatios += newPartNumber * retrievedPartNumber;

                                    StringBuilder sb = new StringBuilder(previousLine);
                                    sb.setCharAt(j, '$');
                                    previousLine = sb.toString();
                                }
                            }
                        }
                        
                        // check current line for symbols
                        if (firstDigitLoc !=0) {
                            if (currentLine.charAt(firstDigitLoc - 1) == '*') {
                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(firstDigitLoc - 1, 'x');
                                currentLine = sb.toString();

                                int relevantIndex = currIndex + firstDigitLoc - 1;
                                partNums.put(relevantIndex, newPartNumber);
                            } else if (currentLine.charAt(firstDigitLoc - 1) =='x') {
                                int retrievedPartNumber = partNums.get(currIndex + firstDigitLoc - 1);
                                System.out.println("jaunt");
                                System.out.println(retrievedPartNumber * newPartNumber);
                                sumGearRatios += newPartNumber * retrievedPartNumber;

                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(firstDigitLoc - 1, '$');
                                currentLine = sb.toString();
                            }
                        }

                        if (i+1 < currentLine.length()) {
                            if (currentLine.charAt(i+1) == '*') {
                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(i+1, 'x');
                                currentLine = sb.toString();

                                int relevantIndex = currIndex + i + 1;
                                partNums.put(relevantIndex, newPartNumber);
                            } else if (currentLine.charAt(i+1) =='x') {
                                int retrievedPartNumber = partNums.get(currIndex + i + 1);
                                System.out.println("jaunt");
                                System.out.println(retrievedPartNumber * newPartNumber);
                                sumGearRatios += newPartNumber * retrievedPartNumber;

                                StringBuilder sb = new StringBuilder(currentLine);
                                sb.setCharAt(i+1, '$');
                                currentLine = sb.toString();
                            }
                        }
                }
            }
            
            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return sumGearRatios;
    }
    public static void main(String[] args) {
        System.out.println("Colonist3");
        System.out.println(partNumbers3("/Users/zsg/Desktop/ps2/aoc2023/input3"));
    }
}
