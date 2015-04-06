package code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ReadAndWrite {
    private FileInputStream is;
    private FileOutputStream os;
    public HashMap<String, Integer> hm;
    private long chislo;

    public ReadAndWrite(String path){
        try {
            is = new FileInputStream(path);
            os = new FileOutputStream(System.getProperty("user.dir") + "/res/res.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        chislo = 0;
        hm = new HashMap<String, Integer>();
    }

    public char read(){
        int i = 0;
        try {
            i = is.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (char) i;
    }

    synchronized String getString(){
        String sb = new String("");
        char ch = read();
        if(ch == 65535)
            return null;
        while (ch != '\n') {
            sb += "" + ch;
            ch = read();
        }
        return sb;
    }

    public void write(byte arr[]){
        try {
            os.write(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public Integer getHm(String s){
        return hm.get(s);
    }

    synchronized public void putHm(String s, int i){
        hm.put(s, i);
    }

    public void closeIn(){
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeOut(){
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized public long getNumber(){return chislo;}
    synchronized public void count(){chislo++;}

}
