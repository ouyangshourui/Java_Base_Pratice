import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    static int num = 2;
    public static void main(String[] args) throws Exception{

      final   Semaphore oddSemaphore = new Semaphore(1);
      final   Semaphore evenSemaphore = new Semaphore(0);
       // new Thread(new DigitPrinter(i,oddSemaphore,evenSemaphore)).start();
       // new Thread(new DigitPrinter(i,evenSemaphore,oddSemaphore)).start();

        new Thread(new Runnable() {
            @Override

            public void run() {
                while (num <= 100){
                    try {
                        oddSemaphore.acquire();
                        System.out.println(num);
                        num=num-1;
                        evenSemaphore.release();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override

            public void run() {
                while (num <= 100){
                    try {
                        evenSemaphore.acquire();
                        System.out.println(num);
                        num=num+3;
                        oddSemaphore.release();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

}

class DigitPrinter implements Runnable{
    static int num;
    Semaphore curSemaphore;
    Semaphore nextSemaphore;
    public DigitPrinter(int num, Semaphore curSemaphore,Semaphore nextSemaphore) {
        this.num = num;
        this.curSemaphore = curSemaphore;
        this.nextSemaphore = nextSemaphore;
    }

    @Override
    public void run() {
        while (num <= 100){
            try {
                curSemaphore.acquire();
                System.out.println(num);
                ++num;
                nextSemaphore.release();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}