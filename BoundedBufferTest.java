public class BoundedBufferTest {

    public static void main(String[] args) {

        int size = 5;

        BoundedBuffer buffer = new BoundedBufferMonitor(size);

        Producer producer = new Producer(Integer.toString(1), buffer);
        Consumer consumer = new Consumer(Integer.toString(1), buffer);

        producer.start();
        consumer.start();

    }
}
