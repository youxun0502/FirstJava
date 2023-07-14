package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StockReader {
    public static void main(String[] args) throws MalformedURLException {
        //google: 股票價格 json
        System.out.println("請輸入欲查詢的日期格式（yyyyMMdd):");
        Scanner scanner = new Scanner(System.in);
//        String urlString = String.format("https://www.twse.com.tw/exchangeReport/MI_INDEX?response=csv&date=%s&type=ALL", date);
//        URL url = new URL(urlString);
        URL url = new URL("https://www.twse.com.tw/exchangeReport/MI_INDEX?response=csv&date=20221004&type=ALL");
        try (InputStream inputStream = url.openStream();
             InputStreamReader isr = new InputStreamReader(inputStream, "MS950");
             BufferedReader br = new BufferedReader(isr)
        ) {

            String line;
            Map<String, Stock> stocks = new HashMap<>();
            while ( (line = br.readLine()) != null){
                //System.out.println(line);
                String[] tokens = line.split("\",\"");
                //=",
                /*if ( tokens.length > 1) {
                    System.out.println(tokens[1]);
                }*/
                String field1 = tokens[0].replace("=", "");
                field1 = field1.replace("\"", "");
                if ( isStockNumber(field1)) {
                    //System.out.println(field1);
                    Stock stock = new Stock();
                    stock.setName(tokens[1]);
                    stock.setStockID(field1);
                    //價格處理變成方法
                    stock.setOpen(getPrice(tokens[5]));
                    stock.setHigh(getPrice(tokens[6]));
                    stock.setLow(getPrice(tokens[7]));
                    stock.setClose(getPrice(tokens[8]));
                    stocks.put(field1, stock);
                }
            }
            //step 1

            //System.out.println(stocks.get(stockNumber));
            System.out.println("請輸入股票代號或名稱:");
            String stockNumber;
            while ( (stockNumber = scanner.nextLine()).length() > 0 ){
                if ( stocks.containsKey(stockNumber)) {
                    System.out.println(stocks.get(stockNumber));
                }else{
                    Set<String> keys = stocks.keySet();
                    for (String id : keys) {
                        Stock stock = stocks.get(id);
                        if ( stock.getName().contains(stockNumber)){
                            System.out.println(stock);
                        }
                    }
                }
                System.out.println("請輸入股票代號或名稱:");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean isStockNumber(String field){
        if ( field.length() < 4){
            return false;
        }
        for (int i = 0; i <  4 ; i++) {
            if ( !Character.isDigit(field.charAt(i)) ){
                return  false;
            }
        }
        return true;
    }
    public static double getPrice(String price){
        if ( price.equals("--")){
            return 0;
        }
        return Double.parseDouble(price.replace(",", ""));
    }
}
