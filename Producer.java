import java.util.Random;

public class Producer extends Thread {

    private static int MAX_RANDOM = 100;
    private static int MIN_PRODUCING_TIME = 30;
    private static int MAX_PRODUCING_TIME = 50;

    private Random random = new Random();
    private BoundedBuffer buffer;

    public Producer(String name, BoundedBuffer b){
        super(name);
        buffer = b;
    }

    public void run(){
        try {
            while(true){
                int item = produceItem();
                buffer.put(item);
            }
        } catch (InterruptedException e){}
    }

    private int produceItem() throws InterruptedException {
        randomWait(MIN_PRODUCING_TIME, MAX_PRODUCING_TIME);
        int newItem = random.nextInt(MAX_RANDOM);
        return newItem;
    }

    private void randomWait(int minTime, int maxTime) throws InterruptedException {
        Thread.sleep((random.nextInt(maxTime - minTime + 1) + minTime));
    }

}
