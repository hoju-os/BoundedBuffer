import java.util.Random;

public class Consumer extends Thread {

    private static int MIN_CONSUMING_TIME = 30;
    private static int MAX_CONSUMING_TIME = 50;

    private Random random = new Random();
    private BoundedBuffer buffer;

    public Consumer(String name, BoundedBuffer b){
        super(name);
        buffer = b;
    }

    public void run(){
        try {
            while(true){
                int item = buffer.get();
                consumeItem(item);
            }
        } catch (InterruptedException e){}
    }

    private void consumeItem(int item) throws InterruptedException {
        randomWait(MIN_CONSUMING_TIME,MIN_CONSUMING_TIME);
    }

    private void randomWait(int minTime, int maxTime) throws InterruptedException {
        Thread.sleep((random.nextInt(maxTime - minTime + 1) + minTime));
    }

}
