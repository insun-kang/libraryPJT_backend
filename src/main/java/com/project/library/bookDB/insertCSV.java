package com.project.library.bookDB;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class insertCSV {
    public static void main(String[] args) {
        insertCSV insertCSV = new insertCSV();
        insertCSV.readCSV();

    }
    public List<List<String >> readCSV(){
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File("C:\\Users\\kang\\Desktop\\libraryPJT\\backend\\src\\main\\java\\com\\project\\library\\bookDB\\booklist.csv");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while((line = br.readLine()) != null){
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(", ");
                aLine = Arrays.asList(lineArr);
                System.out.println(aLine);
                csvList.add(aLine);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try{
                if (br != null){
                    br.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return csvList;
    }
}
