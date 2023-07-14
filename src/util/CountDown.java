package util;

public class CountDown {
    
    private int seconds = 5;

    public void run(){
        for( int i=seconds ; i > 0 ; i--){
            System.out.println("倒數"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("時間到");
    }

    public static void main(String[] args) {
        CountDown countDown = new CountDown();
        countDown.run();
    }
}
