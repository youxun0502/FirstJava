package thread;

public class FoodMonsterGameWait extends Thread{


    private volatile int food = 5;
    public void eat(){
        synchronized (this) {
            while (food == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //因為有wait，所以雖然在while迴圈中，但其實沒有執行了，不會浪費cpu等待
            System.out.println("等待結束");
        }
        for(int i=0 ; i < 5 ;i++){
            food--;
            System.out.println(Thread.currentThread().getName()+"吃掉一個剩下"+food+"個");
        }
        System.out.println(Thread.currentThread().getName()+":吃完了");
    }

    public void feed(){
        synchronized (this){
            food = 5;
            System.out.println("通知"+this.getName()+" 有食物了");
            this.notifyAll();//會喚醒之前wait的執行緒
        }
    }

    @Override
    public void run() {
        while ( true ) eat();
    }


}
