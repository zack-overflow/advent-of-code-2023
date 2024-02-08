import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Colonist1 {
    // Class variables
    private static final Map<String, String> digitMap = createDigitMap();

    // Constructor(s)

    // Methods
    private static Map<String, String> createDigitMap() {
        Map<String, String> map = new HashMap<>();
        map.put("zero", "zero0zero");
        map.put("one", "one1one");
        map.put("two", "two2two");
        map.put("three", "three3three");
        map.put("four", "four4four");
        map.put("five", "five5five");
        map.put("six", "six6six");
        map.put("seven", "seven7seven");
        map.put("eight", "eight8eight");
        map.put("nine", "nine9nine");
        return map;
    }

    public static String replaceStrings(String original, Map<String, String> replacements) {
        String result = original;
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static int jaunty(String filename) {
        // Read the contents of the file line by line
        int sumCalibrationValues = 0;
        
        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process each line of the file
                char firstNumber = 'c';
                char lastNumber = 'c';
                boolean firstNumberSet = false;

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    // Process each character in the line
                    // if c is a number, record 
                    if (Character.isDigit(c)) {
                        if (! firstNumberSet) {
                            firstNumber = c;
                            firstNumberSet = true;
                        }

                        lastNumber = line.charAt(i);
                    }
                }

                if (firstNumberSet) {
                        System.out.println("firstNumber: " + firstNumber);
                        String combinedString = "" + firstNumber + lastNumber;
                        int calibrationValue = Integer.parseInt(combinedString);
                        sumCalibrationValues += calibrationValue;
                    } else {
                        System.out.println("Error: firstNumber not set");
                    }
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return sumCalibrationValues;
    }

    public static int jaunty2(String filename) {
        // Read the contents of the file line by line
        int sumCalibrationValues = 0;
        
        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String replacedLine = replaceStrings(line, digitMap);

                // Process each line of the file
                char firstNumber = 'c';
                char lastNumber = 'c';
                boolean firstNumberSet = false;

                for (int i = 0; i < replacedLine.length(); i++) {
                    char c = replacedLine.charAt(i);
                    // Process each character in the line
                    // if c is a number, record 
                    if (Character.isDigit(c)) {
                        if (! firstNumberSet) {
                            firstNumber = c;
                            firstNumberSet = true;
                        }

                        lastNumber = replacedLine.charAt(i);
                    }
                }

                if (firstNumberSet) {
                        System.out.println("firstNumber: " + firstNumber);
                        String combinedString = "" + firstNumber + lastNumber;
                        int calibrationValue = Integer.parseInt(combinedString);
                        sumCalibrationValues += calibrationValue;
                    } else {
                        System.out.println("Error: firstNumber not set");
                    }
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return sumCalibrationValues;
    }

    public static void main(String[] args) {
        System.out.println(jaunty("/Users/zsg/Desktop/ps2/aoc2023/input1"));
    }
    
}
