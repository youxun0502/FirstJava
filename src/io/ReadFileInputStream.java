package io;

import java.io.*;

public class ReadFileInputStream {

    public static void main(String[] args) {
        
        /*
        //copy io_1.txt到C:\
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
            char c = (char) fs.read();
            System.out.println("第一個byte為："+c);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if ( fs != null) {
                    fs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } */


        //改用try-with-resource
        try (FileInputStream fis = new FileInputStream("/Users/vincent/Desktop/io_1.txt");
             //BufferedInputStream bis = new BufferedInputStream(fis);
        ){
            //step 1
            //單一read
            System.out.println("檔案開始多少可以讀:"+fis.available()+"bytes");
            int b = fis.read();
            char c = (char) b;
            System.out.println("第一個byte為："+c);

            while ( (b = fis.read()) != -1){
                System.out.println((char) b);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }
}
