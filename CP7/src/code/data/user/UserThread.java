package code.data.user;

import code.data.user.User;
import code.data.user.UserFrame;

import java.awt.*;
import java.util.ArrayList;

public class UserThread implements Runnable{

    Thread t;
    public UserFrame uf;
    ArrayList<User> users;
    User currentUser;

    public UserThread(User currentUser, ArrayList<User> users){
        super();
        this.currentUser = currentUser;
        this.users = users;
        t = new Thread(this);
        uf = new UserFrame(this, currentUser.name, users);
        t.start();
    }

    public void call(String userName){
        int i = 0;
        for(; i < users.size(); i++)
            if(users.get(i).name == userName)
                break;
        currentUser.call(users.get(i), 5000);
    }

    public void run(){
        uf.setBackground(Color.PINK);
        uf.setSize(250, 400);
        uf.setVisible(true);
        while(uf.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            uf.repaint();
        }
        currentUser.ut = null;
        uf.dispose();
    }

}
