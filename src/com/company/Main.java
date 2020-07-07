package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String filePath= "E:\\Repozytoria\\LetterCount\\example\\testFile.txt";
    static List<int[][]> symbolCounterList = new ArrayList<int[][]>();
    static BufferedReader bufferedReader = null;

    // Functionalities: Do a bunch of magic to prepare a file to be read
    public static void fileUtility() throws FileNotFoundException {


        File file = new File(filePath);
        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        bufferedReader = new BufferedReader(input);
    }

    //Functionalities: Counts characters in a file
    private static int charCount() {


        int charCount = 0;
        String data;
        try {
            while ((data = bufferedReader.readLine()) != null) {
                charCount += data.length();
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        return charCount;
    }

    /* Functionalities:
                1. Open file through fileUtility()
                2. Iterate through file and sends chars to saveToList()
            */
    private static void readFile(){

        char[] cbuf = new char[1];

        try{
            fileUtility();

            for(int i=0;i<charCount();i++) {
                bufferedReader.read(cbuf);
                saveToList(cbuf[0]);
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void saveToList(char letter){
        if(searchList(letter)){

        }
        else{

        }
    }

    private static boolean searchList(char letter){
        return true;
    }

    public static void main(String[] args) {
        readFile();

    }
}
