package src.JavaWeek9Programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DecimalData {

    static Scanner datainput = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
						   // Change me to your own file and path (done!)
		String fileName = "/home/radio_is_active/decimaldata.txt";
		File dataFile = new File(fileName);
		try {
            Scanner scanner = new Scanner(dataFile);
            
            int quantity = 0;
            double sum = 0.0; 
            double max = Double.NEGATIVE_INFINITY; // Initialize max to the smallest value
            double min = Double.POSITIVE_INFINITY; // Initialize min to the largest value
            
            while (scanner.hasNextDouble()) {
                double number = scanner.nextDouble();
                quantity++;
                sum += number;

                // Update max and min
                if (number > max) {
                    max = number;
                }
                if (number < min) {
                    min = number;
                }
            }

            // Calculate average
            double average = quantity > 0 ? sum / quantity : 0;

            // Calculate range
            double range = (max*10) - (min*10);
            range = range / 10;

            // Display statistics
            System.out.println("\n     STATISTICS");
            System.out.println("Quantity of numbers: " + quantity);
            System.out.printf("Average (mean): %.2f\n", average);
            System.out.println("Largest number: " + max);
            System.out.println("Smallest number: " + min);
            System.out.println("Range of the data: " + range);

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
	}
}
