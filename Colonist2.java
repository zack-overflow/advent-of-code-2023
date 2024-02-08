import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class GameConstraint {
    private int max_red;
    private int max_green;
    private int max_blue;

    public GameConstraint(int max_red, int max_green, int max_blue) {
        this.max_red = max_red;
        this.max_green = max_green;
        this.max_blue = max_blue;
    }

    public int[] parsePullStr(String gameStr) {
        int[] pullArr = new int[3];
        String[] gameStrArray = gameStr.split(", ");
        for (int i = 0; i < gameStrArray.length; i++) {
            String[] info = gameStrArray[i].split(" ");
            int number = Integer.parseInt(info[0]);
            String color = info[1];
            if (color.equals("red")) {
                pullArr[0] = number;
            } else if (color.equals("green")) {
                pullArr[1] = number;
            } else if (color.equals("blue")) {
                pullArr[2] = number;
            }
        }

        return pullArr;
    }

    public int findPower(String[] pullsArr) {
        int biggestRed = 0;
        int biggestGreen = 0;
        int biggestBlue = 0;

        for (int i = 0; i < pullsArr.length; i++) {
            int[] pullArr = parsePullStr(pullsArr[i]);
            int red = pullArr[0];
            int green = pullArr[1];
            int blue = pullArr[2];

            // update information on most of each color seen so far
            if (red > biggestRed) {
                biggestRed = red;
            }

            if (green > biggestGreen) {
                biggestGreen = green;
            }

            if (blue > biggestBlue) {
                biggestBlue = blue;
            }
        }

        int power = biggestRed * biggestGreen * biggestBlue;
        return power;
    }

    public Boolean checkGame(String[] pullsArr) {
        for (int i = 0; i < pullsArr.length; i++) {
            int[] pullArr = parsePullStr(pullsArr[i]);
            int red = pullArr[0];
            int green = pullArr[1];
            int blue = pullArr[2];

            if (!compareGame(red, green, blue)) {
                return false;
            }
        }

        return true;
    }
    
    public Boolean compareGame(int red, int green, int blue) {
        if (red > this.max_red) {
            return false;
        }

        if (green > this.max_green) {
            return false;
        }

        if (blue > this.max_blue) {
            return false;
        }

        return true;

    }
}

public class Colonist2 {
    public static final int RED_CUBES = 12;
    public static final int GREEN_CUBES = 13;
    public static final int BLUE_CUBES = 14;

    public int grabId(String idStr) {
        String[] idStrArr = idStr.split(" ");
        int id = Integer.parseInt(idStrArr[1]);
        return id;
    }
    
    public String[] separatePulls(String ln) {
        String[] pulls = ln.split("; ");
        return pulls;
    }

    public int getIds(String filename) {
        int sumInvalidIds = 0;
        
        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            GameConstraint game = new GameConstraint(RED_CUBES, GREEN_CUBES, BLUE_CUBES);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] idAndPulls = line.split(": ");

                int id = grabId(idAndPulls[0]);
                String[] pulls = separatePulls(idAndPulls[1]);
                Boolean valid = game.checkGame(pulls);

                if (valid) {
                    System.out.println("id: " + id);
                    sumInvalidIds += id;
                }
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return sumInvalidIds;
    }

    public int sumPower(String filename) {
        int sumPower = 0;
        
        try {
            // Create a File object with the file path
            File file = new File(filename);

            // Create a Scanner object to read the file
            Scanner scanner = new Scanner(file);

            GameConstraint game = new GameConstraint(RED_CUBES, GREEN_CUBES, BLUE_CUBES);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] idAndPulls = line.split(": ");
                String[] pulls = separatePulls(idAndPulls[1]);
                int power = game.findPower(pulls);
                sumPower += power;
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        return sumPower;
    }

    public static void main(String[] args) {
        String filename = "/Users/zsg/Desktop/ps2/aoc2023/input2";
        Colonist2 colonist = new Colonist2();
        int sumInvalidIds = colonist.getIds(filename);
        System.out.println("sumInvalidIds: " + sumInvalidIds);

        int sumPower = colonist.sumPower(filename);
        System.out.println("sumPower: " + sumPower);
    }
}
