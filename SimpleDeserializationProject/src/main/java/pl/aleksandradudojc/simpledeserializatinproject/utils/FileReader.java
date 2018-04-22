package pl.aleksandradudojc.simpledeserializatinproject.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
        public String getFileContent(String fileName) {
            ClassLoader classLoader = getClass().getClassLoader();
            File someFile = new File(classLoader.getResource(fileName).getFile());
            Scanner odczyt = null;
            try {
                odczyt = new Scanner(someFile);
            } catch (FileNotFoundException e) {
                System.out.println("Nie dostalismy pliku"+e.getMessage());
                e.printStackTrace();
            }
            String result = "";
            while (odczyt.hasNextLine()) {
                String line = odczyt.nextLine();
                result += line;
            }

            odczyt.close();
            return result;
        }

    }

