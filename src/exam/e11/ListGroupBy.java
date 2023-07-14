package exam.e11;

import java.util.*;

public class ListGroupBy {
    public static void main(String[] args) {
        List<String> classes = Arrays.asList("Java", ".Net", "CISCO", "iPhone App", "Python","AI智慧");
        Map<Integer,List<String>> map = new HashMap<>();
        /*
        重新分類產生一個Map，Key值是文字的長度,value型態則是List<String>
        例如:key值是4的，會有"Java",".Net","AI智慧"
            key值是5的，會有"CISCO"
            並按照字的長度依序列印出來，例如4=[Java,.Net,AI智慧],5=[CISCO]
            不存在的長度請不要印出來。
        請注意，不是要你產生以下程式，而是要利用程式邏輯重新組合原有的list
        List<String> words = Arrays.asList("Java",".Net","AI智慧");
        List<String> words1 = Arrays.asList("CISCO");
        List<String> words2 = Arrays.asList("Python");
        List<String> words3 = Arrays.asList("iphone App");
        map.put(4,words);
        map.put(5,words1);
        map.put(6,words2);
         */

    }
}
