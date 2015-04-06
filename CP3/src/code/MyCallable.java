package code;

import java.util.concurrent.Callable;

class MyCallable<V> implements Callable<V> {

    int n;

    public MyCallable(int n){
        this.n = n;
    }

    @Override
    public V call() throws Exception {
        Long res = new Long(0);
        for(int i = 0; i <= n; i++){
            res += 2 << (i - (i % 2 == 0 ? 1 : -1));
        }
        return (V) res;
    }
}