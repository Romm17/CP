package code.screen;

import code.main.Society;
import code.data.Table;
import code.data.user.User;

import java.util.ArrayList;

public class FrameThread implements Runnable{
    Table d;
    public Thread t;
    ArrayList<User> users;

    public FrameThread(Table d, ArrayList<User> users){
        t = new Thread(this);
        this.d = d;
        this.users = users;
        t.start();
    }

    public void addUserThread(String name){
        int i = 0;
        for(i = 0; i < users.size(); i++)
            if(users.get(i).name.equals(name))
                break;
        Society.addUT(users.get(i));
    }

    @Override
    public void run(){
        MyFrame myFrame = new MyFrame(this, d, users);
        myFrame.setSize(600, 600);
        myFrame.setVisible(true);
        while(myFrame.isVisible()){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myFrame.repaint();
        }
    }
}
