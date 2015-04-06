package code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyParser {
    public static void main(String args[]) {
        action(System.getProperty("user.dir") + "/res/src.java");
    }

    public static int action(String path){
        FileInputStream is;
        FileOutputStream os;
        try{
            is = new FileInputStream(path);
            os = new FileOutputStream(System.getProperty("user.dir") + "/res/res.java");
        }catch(FileNotFoundException e) {
            return -1;
        }
        char ch = 0;
        try {
            ch = (char) is.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        while(ch != 65535) {
            String sb = new String("");
            while (ch != '\n') {
                sb += "" + ch;
                try {
                    ch = (char) is.read();
                } catch (IOException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            if(sb.matches("//.*\r*\n*")){
                sb = sb.replaceAll("//.*\r*\n*", "");
            }
            else{
                sb = sb.replaceAll("//.*\r*\n*", "") + "\n";
            }
            if(flag){
                if(sb.matches(".*\\*/.*\r*\n*")){
                    flag = false;
                    sb = sb.replaceAll(".*\\*/\n*", "");
                }
                else{
                    sb = "";
                }
            }
            else{
                if(sb.matches(".*/\\*.*\r*\n*")){
                    sb = sb.replaceAll("/\\*.*\n*", "");
                    flag = true;
                }
            }
            try {
                os.write((sb).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
            try {
                ch = (char) is.read();
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

}
