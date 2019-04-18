import java.util.concurrent.locks.*;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferMonitor extends BoundedBuffer{

    protected int numElements = 0;

    protected Lock l = new ReentrantLock();
    protected Condition fullBuffer = l.newCondition();
    protected Condition emptyBuffer = l.newCondition();

    public BoundedBufferMonitor(int size) {
        super(size);
    }

    public void put(int item) throws InterruptedException {
        String nameThread = Thread.currentThread().getName();
        System.out.println("Producer " + nameThread + ": Inserting item " + item + "... ");
        l.lock();
        try {
            while (numElements == buffer.length){
                fullBuffer.await();
            }
            buffer[in] = item;
            in = (in + 1) % buffer.length;
            numElements++;
            emptyBuffer.signal();
        } finally {
            l.unlock();
        }
        System.out.println(
                "Producer " + Thread.currentThread().getName() + " has inserted item " + item + ". " + getContent()
        );
    }

    public int get() throws InterruptedException {
        String nameThread = Thread.currentThread().getName();
        int item;
        l.lock();
        try {
            System.out.println("Consumer " + nameThread + ": Consuming item... ");
            while (numElements == 0) {
                emptyBuffer.await();
            }
            item = buffer[out];
            buffer[out] = -1;
            out = (out + 1) % buffer.length;
            numElements--;
            fullBuffer.signal();
        } finally {
            l.unlock();
        }
        System.out.println(
                "Consumer " + Thread.currentThread().getName() + " has consumed item " + item + ". " + getContent()
        );
        return item;
    }



}
