package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Main {
    static String filePath = "E:\\Repozytoria\\LetterCount\\example\\testFile.txt";
    static List<String[]> symbolCounterList = new ArrayList<>();
    static List<double[]> probabilityList = new ArrayList<>();
    static BufferedReader bufferedReader = null;
    static int fileLength = 0;

    // Functionalities: Do a bunch of magic to prepare a file to be read
    public static void fileUtility() throws FileNotFoundException {
        File file = new File(filePath);
        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        bufferedReader = new BufferedReader(input);
    }

    //Functionalities: Counts characters in a file
    private static void charCount() {

        String data;
        try {
            while ((data = bufferedReader.readLine()) != null) {
                fileLength += data.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Functionalities:
                1. Open file through fileUtility()
                2. Sets fileLength through charCount()
                3. Iterate through file and sends chars to searchAndSave()
            */
    private static void dictionaryCreation() {

        char[] cbuf = new char[1];

        try {
            fileUtility();
            charCount();

            for (int i = 0; i < fileLength; i++) {
                bufferedReader.read(cbuf);
                searchAndSave(cbuf[0]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Functionalities:
        1. Search letter through List
        2. Update letter counter
        3. Write new letter to List
    */
    private static void searchAndSave(char letter) {
        for (int i = 0; i < symbolCounterList.size(); i++) {
            if (symbolCounterList.get(i)[0] == Character.toString(letter)) {
                int counter = Integer.parseInt(symbolCounterList.get(i)[1]) + 1;
                symbolCounterList.set(i, new String[]{Character.toString(letter), Integer.toString(counter)});
                return;
            }
        }
        symbolCounterList.add(new String[]{Character.toString(letter), "1"});
    }

    /*Functionalities:
      1. Changes quantity to probability
      2. Displays a List
     */
    private static void dictionaryDisplay() {
        for (int i = 0; i < symbolCounterList.size(); i++) {
            double counter = Double.parseDouble(symbolCounterList.get(i)[1]) / fileLength;
            symbolCounterList.set(i, new String[]{symbolCounterList.get(i)[0], Double.toString(counter)});
        }
        System.out.println(symbolCounterList);
    }

    //Functionalities: Creates a List of info for counting an entropy
    private static void entropyInfoListCreation() {
        boolean isNumberFound = false;
        for (int j = 0; j < symbolCounterList.size(); j++) {
            for (int i = 0; i < probabilityList.size(); i++) {
                if (Double.parseDouble(symbolCounterList.get(j)[1]) == probabilityList.get(i)[0]) {
                    probabilityList.get(i)[1]++;
                    isNumberFound = true;
                    break;
                } else isNumberFound = false;
            }
            if (!isNumberFound) {
                probabilityList.add(new double[]{Double.parseDouble(symbolCounterList.get(j)[1]), 1});
            }
        }
    }

    /*Functionalities:
      1. Counts entropy
      2. Displays entropy
     */
    private static void entropyDisplay() {
        double entropy = 0;
        for (int i = 0; i < probabilityList.size(); i++) {
            double log = Math.log(Double.parseDouble(symbolCounterList.get(i)[1])) / Math.log(2);
            entropy = entropy - probabilityList.get(i)[1] * Double.parseDouble(symbolCounterList.get(i)[1]) * log;
        }
        System.out.println("Entropy of this file is" + entropy + " bit per symbol");
    }

    public static void main(String[] args) {
        dictionaryCreation();
        dictionaryDisplay();
        entropyInfoListCreation();
        entropyDisplay();
    }
}
