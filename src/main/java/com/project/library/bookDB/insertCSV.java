package com.project.library.bookDB;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class insertCSV {
    public static void main(String[] args) throws IOException {
        readCSV readcsv = new readCSV("C:\\Users\\insun\\Desktop\\project\\library\\src\\main\\java\\com\\project\\library\\bookDB\\booklist.csv");
        String[] line=null;
        while((line = readcsv.nextRead())!=null){
            for(String a : line){
                System.out.print(a +" ");
            }
            System.out.println("----------------------------------------------");
        }

    }
}
