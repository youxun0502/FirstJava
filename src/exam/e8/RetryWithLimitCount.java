package exam.e8;

import java.util.concurrent.ThreadLocalRandom;

public class RetryWithLimitCount {
    public static void main(String[] args) {
        /**
         * getRandomDouble()可以取得0-1之間的一個亂數，為double值，將其放大10倍以取得0-10之間的整數,但可能會丟出Exception
         * 題目：總共需取得10組整數
         * 限制：1.每一組，呼叫getRandomDouble()取得數字如果發生Exception，則要重新取，最多只能試3次。也就是如果連續取3次都發生Exception，則印出「無法取得」。
         *      最後將取得的結果以及呼叫次數列印出來
         *      2.catch exception的block，不能再寫try catch，例如
         *        catch(IllegalStateException ex){
         *           try {} catch....
         *        }
         * 例如：
         * 第1次取得 3 呼叫2次
         * 第2次取得 無法取得
         * 第3次取得 4 呼叫1次
         * ..
         * 第10次取得 5
         */
        double random = getRandomDouble();

    }

    // DO NOT CHANGE
    public static double getRandomDouble() throws IllegalStateException{

        double nextValue = ThreadLocalRandom.current().nextDouble();
        if ( (int)(nextValue*10) %2 == 1 ){
            throw new IllegalStateException("Something wrong, please try again!");
        }
        return nextValue;
    }

}
