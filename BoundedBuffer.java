public abstract class BoundedBuffer {

    protected int[] buffer;
    protected int in = 0;
    protected int out = 0;

    public BoundedBuffer(int size){
        buffer = new int[size];
        for(int i = 0; i < buffer.length; i++)
            buffer[i] = -1;
    }

    public abstract void put(int item) throws InterruptedException;

    public abstract int get() throws InterruptedException;

    public String getContent(){
        String s = "[ ";
        for (int i = 0; i < buffer.length; i++){
            s += buffer[i] + " ";
        }
        s += "]";
        return s;
    }
}
