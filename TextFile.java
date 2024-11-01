package src.JavaWeek9Programs;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class TextFile {

	static Scanner datainput = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
						   // Change me to your own file and path (done!)
		String fileName = "/home/radio_is_active/formyclass.txt";
		File textFile = new File(fileName);
		Scanner datainput = new Scanner(textFile);
		String s;
		int words = 0;
        int letters = 0;
        int sentences = 0;
		int[] counter = new int[26];

		while (datainput.hasNextLine()) {
			words++;
			s = datainput.nextLine();
			s = s.trim();
			System.out.println(s);
			for (int j = 0; j < s.length(); j++) {
				if ((s.charAt(j) == ' ') && (s.charAt(j + 1) != ' ')) {
					words++;
				}
				int ascii = (int) (s.charAt(j));
				if ((ascii >= 97) && (ascii <= 122)) {
					counter[ascii - 97]++;
				}
				if ((ascii >= 65) && (ascii <= 90)) {
					counter[ascii - 65]++;
				}
                if ((ascii >= 97) && (ascii <= 122)) {
                    counter[ascii - 97]++;
                    letters++; // Count lowercase letters
                }
                if ((ascii >= 65) && (ascii <= 90)) {
                    counter[ascii - 65]++;
                    letters++; // Count uppercase letters
                }
                if ((s.charAt(j) == '.') || (s.charAt(j) == '!') || (s.charAt(j) == '?')) {
                    sentences++;
                }

			}
		}

        double avrgLetters = (double) letters / words;
        double avrgWords = (double) words / sentences;

        avrgLetters = Math.round(avrgLetters * 10.0) / 10.0;
        avrgWords = Math.round(avrgWords * 10.0) / 10.0;

        System.out.println("\n===============");
        System.out.println("DOCUMENT STATS");
        System.out.println("===============");
		System.out.println("Word count: " + words);
        System.out.println("Letter count: " + letters);
        System.out.println("Sentence count: " + sentences);
        System.out.println("Average letters per word: " + avrgLetters);
        System.out.println("Average words per sentence: " + avrgWords);

		datainput.close();	
	}
}
