import java.util.concurrent.Semaphore;

public class DigitExample {
    public static void main(String[] args) {
        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);

    }
}

class DigitPrinterThread implements Runnable {
    int num;
    Semaphore curSemaphore;
    Semaphore nextSemaphore;

    public DigitPrinterThread(int num, Semaphore curSemaphore, Semaphore nextSemaphore) {
        this.num = num;
        this.curSemaphore = curSemaphore;
        this.nextSemaphore = nextSemaphore;
    }


    @Override
    public void run() {
        while (num <= 100) {
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





