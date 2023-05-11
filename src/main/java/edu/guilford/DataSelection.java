package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataSelection {
    
    public static void main(String[] args) {
        //scanning the file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        //getting the file from the resources folder and then reading it
        String filename = "src/main/resources/" + scanner.nextLine();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            //this creates a hashmap objected 
            //stores String and integers 
            HashMap<String, Integer> countWords = new HashMap<>(); 
            String line = reader.readLine(); 
            //reads each line in the file the user entered and makes everything lowercase  
            //removes all non-alphanumeric characters using the regular expression [^a-zA-Z0-9 ], 
            //and then splits the line into individual words 
            while (line != null) {
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
                for (String word : words) {
                    if (countWords.containsKey(word)) {
                        countWords.put(word, countWords.get(word) + 1);
                    } else {
                        countWords.put(word, 1);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            //taking the countWords hashmap and converting it into Map.Entry objects 
            //so that each word represents either a key or a value in the Map.Entry object
            List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(countWords.entrySet());
            FileWriter writer = new FileWriter("src/main/resources/wordlist.txt"); 
            //this counts how many times a word pops up and puts it in the wordlist.txt file
            for (Map.Entry<String, Integer> entry : sortedList) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.close();
            System.out.println("The amount of word occurances was written in: src/main/resources/wordlist.txt");
            
            //this is asking the user to enter a word to search for and outputs the number of times it appears in the file
            System.out.print("Enter a word to search for: ");
            String searchWord = scanner.nextLine().toLowerCase();
            // the word is used as a key to get how many times it appears in the hash file 
            if (countWords.containsKey(searchWord)) {
                int count = countWords.get(searchWord);
                System.out.println("'" + searchWord + "' appears " + count + " times in the text file.");
            } else {
                System.out.println("'" + searchWord + "' does not appear in the text file.");
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
    }
}



    


