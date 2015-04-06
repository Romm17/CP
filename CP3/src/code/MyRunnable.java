package code;

public class MyRunnable implements Runnable{

    Thread t;
    int pos;
    int n;

    public MyRunnable(int i, int n){
        t = new Thread(this);
        pos = i;
        this.n = n;
        t.start();
    }
    @Override
    public void run() {
        long res = 0;
        for(int i = 0; i <= n; i++){
            res += 2 << (i - (i % 2 == 0 ? 1 : -1));
        }
        Worker.setRes(pos, res);
    }
}
