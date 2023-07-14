import util.CountDown;

import java.util.HashMap;
import java.util.Map;

public class Test {
    private int i = 1;
    static Map<String, Integer> votes = new HashMap<>();
    public static void main(String argv[]){
        int i = 2;

        //s.print();
        vote("小英");
        vote("小英");
        vote("小英");
        vote("小明");
        vote("小明");
        vote("小英");
        System.out.println(votes);
    }

    public static void vote(String name){
//        if ( !votes.containsKey(name)){
//            votes.put(name,1);
//        }else{
//            Integer counter = votes.get(name);
//            counter = counter + 1;
//            votes.put(name, counter);
//        }
        votes.computeIfAbsent(name, s -> {
          return 0;});
        votes.computeIfPresent(name, (s, integer) -> {
            return integer+1;
        });

    }

    public static void print(){
        //System.out.println(i);
    }
}