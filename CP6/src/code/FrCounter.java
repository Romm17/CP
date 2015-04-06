package code;

public class FrCounter {
    public static void main(String args[]) {
        calc(System.getProperty("user.dir") + "/res/shakespeare.txt", 10);
    }

    public static void calc(String path, int ThreadNum){
        MyThread t[] = new MyThread[ThreadNum];
        ReadAndWrite raw = new ReadAndWrite(path);
        for(int i = 0; i < ThreadNum; i++)
            t[i] = new MyThread(raw);
        try {
            for(int i = 0; i < ThreadNum; i++)
                t[i].t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        raw.closeIn();
        for(String key : raw.hm.keySet()){
            raw.write(String.format("%s %f\n", key, raw.hm.get(key).doubleValue() / raw.getNumber() * 100).getBytes());
        }
        raw.closeOut();
    }

}
