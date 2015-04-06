package code;

public class MyThread implements Runnable{
    Thread t;
    ReadAndWrite raw;

    public MyThread(ReadAndWrite raw){
        t = new Thread(this);
        this.raw = raw;
        t.start();
    }

    @Override
    public void run() {
        String sb = raw.getString();
        while (sb != null) {
            String temp[] = sb.split("[^a-zA-Z-']+");
            for (String s : temp) {
                if (s.equals("") || s.equals("\'") || s.equals("-"))
                    continue;
                Integer i = raw.getHm(s);
                if (i != null)
                    raw.putHm(s, i + 1);
                else
                    raw.putHm(s, 1);
                raw.count();
            }
            sb = raw.getString();
        }
    }

}
