package MultiThread.Daemon;

public class Deamon {
    public static void main(String[] args) {
            Thread thread = new Thread(new DeamonRunner(),"DeamonRunner");
            thread.setDaemon(true);
            thread.start();
        }
        static class DeamonRunner implements Runnable{

            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally {
                    System.out.println("finally run");
                }

            }

        }

    }

