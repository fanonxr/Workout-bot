package com.fanonx.chatbot_demo.searcher.text;

import com.fanonx.chatbot_demo.commons.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextSearch {
    /**
     * Text search class to handle brute force search on a text file.
     * */
    private static final Logger logger = LoggerFactory.getLogger(TextSearch.class);
    private String filePath = Constants.txtData;

    /**
     * method to loop over the individual txt files and write them to one big txt file.
     * */
    private void readTxtFileAndWriteToOneBigFile(File file, String nameOfOutFile) {
        try {
            // loop over the file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nameOfOutFile, true));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.append(line);
            }
            bufferedReader.close();
            bufferedWriter.close();

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    /*
    * Loop over the list of dirs and write them to a one txt file.
    * */
    public void readDir(String filePath, String fileName) {
        File[] dir = new File(filePath).listFiles();

        for (File file: dir) {
            readTxtFileAndWriteToOneBigFile(file, fileName + ".txt");
        }
    }
}
