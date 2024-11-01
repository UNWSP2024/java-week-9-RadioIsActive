package src.JavaWeek9Programs;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class TestFace {

    static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        // The number of people (5 known faces)
        final int NUM_PEOPLE = 5;
        // The six measurements for each person
        double[][] measurements = new double[NUM_PEOPLE][6];
        // The fifteen ratios for each person
        double[][] ratios = new double[NUM_PEOPLE][15];
        String[] names = new String[NUM_PEOPLE];

        // Step 1: Read measurements from a file
        readMeasurementsFromFile(measurements, names);

        // Step 2: Compute the ratios for each known picture
        computeRatios(measurements, ratios);

        // Step 3: Get measurements for the mystery picture from user
        double[] mysteryMeasurements = getMysteryMeasurements();

        // Step 4: Compute ratios for the mystery picture
        double[] mysteryRatios = new double[15];
        computeRatios(new double[][]{mysteryMeasurements}, new double[][]{mysteryRatios});

        // Step 5: Calculate sum of squares percentage differences and find the best match
        findBestMatch(ratios, mysteryRatios, names);
    }

    private static void readMeasurementsFromFile(double[][] measurements, String[] names) {
        String fileName = "measurements.txt"; // Change this to your actual file path

        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            for (int i = 0; i < measurements.length; i++) {
                // Read the name
                names[i] = fileScanner.nextLine();
                // Read the six measurements
                for (int j = 0; j < 6; j++) {
                    measurements[i][j] = fileScanner.nextDouble();
                }
                // Move to the next line
                fileScanner.nextLine();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    private static void computeRatios(double[][] measurements, double[][] ratios) {
        for (int person = 0; person < measurements.length; person++) {
            double A = measurements[person][0];
            double B = measurements[person][1];
            double C = measurements[person][2];
            double D = measurements[person][3];
            double E = measurements[person][4];
            double F = measurements[person][5];

            ratios[person][0] = A / B;
            ratios[person][1] = A / C;
            ratios[person][2] = A / D;
            ratios[person][3] = A / E;
            ratios[person][4] = A / F;
            ratios[person][5] = B / C;
            ratios[person][6] = B / D;
            ratios[person][7] = B / E;
            ratios[person][8] = B / F;
            ratios[person][9] = C / D;
            ratios[person][10] = C / E;
            ratios[person][11] = C / F;
            ratios[person][12] = D / E;
            ratios[person][13] = D / F;
            ratios[person][14] = E / F;
        }
    }

    private static double[] getMysteryMeasurements() {
        double[] mysteryMeasurements = new double[6];
        System.out.println("Enter measurements for the mystery picture:");

        for (int i = 0; i < 6; i++) {
            System.out.print("Measurement #" + (i + 1) + ": ");
            mysteryMeasurements[i] = userInput.nextDouble();
        }
        return mysteryMeasurements;
    }

    private static void findBestMatch(double[][] ratios, double[] mysteryRatios, String[] names) {
        double minDifference = Double.MAX_VALUE;
        String bestMatch = "";
        
        for (int i = 0; i < ratios.length; i++) {
            double sumOfSquaresDiff = 0;
            for (int j = 0; j < mysteryRatios.length; j++) {
                double diff = (mysteryRatios[j] - ratios[i][j]) / ratios[i][j];
                sumOfSquaresDiff += diff * diff;
            }
            if (sumOfSquaresDiff < minDifference) {
                minDifference = sumOfSquaresDiff;
                bestMatch = names[i];
            }
        }

        System.out.println("The person associated with the mystery picture is: " + bestMatch);
    }
}

