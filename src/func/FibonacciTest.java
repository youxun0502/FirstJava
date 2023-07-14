package func;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FibonacciTest {

    
    public long f_long(int n){
        if (n <= 1 ) return 1;
        return f_long(n - 1) + f_long(n - 2);
    }


    /**
     * 利用cache long[]來儲存之算過的值以加快速度
     * @param n
     * @param cache
     * @return
     */
    public long f_longArrayCache(int n,long[] cache){
        if (n <= 1 ) return 1;
        if ( cache[n-1] == 0 ){
            cache[n-1] = f_longArrayCache(n - 1, cache) + f_longArrayCache(n - 2, cache);
        }
        return cache[n-1];
    }
    public long f_long2(int n){
        long n0 = 0;
        long n1 = 1;
        for (int i = 0; i < n; i++) {
            long temp = n1;
            n1 = n1 + n0;
            n0 = temp;
        }
        return n1;
    }

    public BigInteger f_BigInteger(int n,BigInteger[] cache){
        if (n <= 1 ) return BigInteger.ONE;
        if ( cache[n-1] == null ){
            //表示cache中沒有值
            cache[n - 1] = f_BigInteger(n - 2, cache).add(f_BigInteger(n - 1, cache));
        }
        return cache[n-1];
    }

    
    public static void main(String[] args) {
        FibonacciTest fi = new FibonacciTest();
        for (int i = 1; i <= 100; i++) {
            System.out.println("i = " + i + ",Fibonacci =" + fi.f_long2(i));
        }
    }
}
