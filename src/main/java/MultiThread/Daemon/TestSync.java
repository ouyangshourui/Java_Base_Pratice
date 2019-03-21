package MultiThread.Daemon;

public class TestSync
{
    public synchronized void run1()
    {
        for (int i = 0; i < 1000; i++)
        {
            System.out.println("execute run1");
            try
            {
                Thread.sleep(1000);
                this.notifyAll();
                this.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

    public synchronized void run2()
    {
        for (int i = 0; i < 1000; i++)
        {
            System.out.println("execute run2");
            try
            {
                Thread.sleep(1000);
                this.notifyAll();
                this.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}