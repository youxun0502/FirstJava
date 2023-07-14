
public class PrimeNumber {
    public static void main(String[] args) {

        int count = 0;
        long start = System.currentTimeMillis();
//        for (int n = 2; n < 1_000_000; n++) {
//            for (int j = 2; j <= n ; j++) {
//                if ( j < n && n%j == 0 ){
//                    break;
//                }
//                if ( j == n ){
//                    count++;
//                    break;
//                }
//            }
//        }
        long end = System.currentTimeMillis();
        System.out.println("count = " + count);
        System.out.println("Cost = "+(end - start));
        count = 0;
        for (int n = 2; n < 1_000_000; n++) {
            if ( n == 2 || n == 3){
                count++;
                continue;
            }
            int sqrt = (int) Math.sqrt(n);//3開根號,1.7多，sqrt會為1
            for (int j = 2; j <= sqrt; j++) { //3會跳過
                if ( j < sqrt && n%j == 0 ){
                    break;
                }
                if ( j == sqrt && n%j !=0 ){
//                    System.out.println(n);
                    count++;
                }
            }
        }
        System.out.println("count = " + count);
    }
}
