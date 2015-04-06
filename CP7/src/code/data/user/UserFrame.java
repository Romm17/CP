package code.data.user;

import code.screen.MyWindowAdapter;

import java.awt.*;
import java.util.ArrayList;

public class UserFrame extends Frame {

    private String userName;
    UserThread ut;
    private ArrayList<User> users;
    private ArrayList<Button> buttons;
    UserActionListener ual;
    Button accept, decline, disconnect, send;
    Label lIn, lOut, speaking, title, sendSms;
    TextArea ta;
    TextField tf;
    Button notused = new Button();

    public UserFrame(UserThread ut, String name, ArrayList<User> users){
        this.ut = ut;
        userName = name;
        this.setTitle(name);
        this.users = users;
        MyWindowAdapter mwa = new MyWindowAdapter(this);
        addWindowListener(mwa);
        ual = new UserActionListener(this);
        buttons = new ArrayList<Button>();
        int y = 75;
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).name != name){
                buttons.add(new Button(users.get(i).name));
                buttons.get(buttons.size() - 1).setLocation(20, y);
                buttons.get(buttons.size() - 1).setSize(50, 20);
                buttons.get(buttons.size() - 1).addActionListener(ual);
                y += 25;
            }
        }
        accept = new Button("Accept");
        accept.setLocation(50, 200);
        accept.setSize(50, 30);
        accept.addActionListener(ual);
        decline = new Button("Decline");
        decline.setLocation(150, 200);
        decline.setSize(50, 30);
        decline.addActionListener(ual);
        disconnect = new Button("Disconnect");
        disconnect.setLocation(150, 250);
        disconnect.setSize(70, 30);
        disconnect.addActionListener(ual);
        title = new Label("");
        title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        title.setSize(200, 30);
        title.setLocation(30, 40);
        lIn = new Label("Calling " + name + "...");
        lIn.setSize(100, 20);
        lIn.setLocation(50, 100);
        lOut = new Label(name + " calls");
        lOut.setSize(100, 20);
        lOut.setLocation(100, 100);
        speaking = new Label("Speaking...");
        speaking.setSize(100, 20);
        speaking.setLocation(100, 100);
        sendSms = new Label("Send sms");
        sendSms.setSize(100, 20);
        sendSms.setLocation(100, 70);
        ta = new TextArea(5, 30);
        ta.setSize(120, 50);
        ta.setLocation(100, 120);
        tf = new TextField(6);
        tf.setSize(50, 20);
        tf.setLocation(100, 90);
        send = new Button("Send");
        send.setLocation(170, 90);
        send.setSize(40, 20);
        send.addActionListener(ual);
        render();
    }

    public void render(){
        if(ut.currentUser.session == null){
            normalMode();
            return;
        }
        if(ut.currentUser.session.status == 0){
            normalMode();
            return;
        }
        if(ut.currentUser.session.status == 1)
            return;
        if(ut.currentUser.session.status == 2)
            speaking();

    }

    public void normalMode(){
        removeAll();
        for(Button b : buttons)
            add(b);
        add(send);
        add(sendSms);
        title.setText("Contacts");
        add(title);
        add(tf);
        add(ta);
        add(notused);
        remove(notused);
    }

    public void callOut(String name){
        removeAll();
        lIn.setText("Calling " + name + "...");
        title.setText("Outcoming call");
        add(title);
        add(lIn);
        add(notused);
        remove(notused);
    }

    public void callIn(String name){
        removeAll();
        title.setText("Incoming call");
        add(title);
        lOut.setText(name + " calls");
        add(lOut);
        add(accept);
        add(decline);
        add(notused);
        remove(notused);
    }

    public void speaking(){
        removeAll();
        title.setText("Speaking");
        add(title);
        add(disconnect);
        add(speaking);
        add(notused);
        remove(notused);
    }

    public void paint(Graphics g){

        g.setColor(Color.green);
        if(ut.currentUser.session != null) {
            if (ut.currentUser.session.status == 2) {
                long time = ut.currentUser.session.callTime();
                int s = (int) (time / 1000);
                int m = s / 60;
                int h = m / 60;
                g.setFont(new Font("Sans Serif", Font.ITALIC, 20));
                g.drawString(String.format("%02d:%02d:%02d", h, m, s), 50, 150);
            }
        }
        else{
            g.setColor(Color.blue);
            g.setFont(new Font("Sans Serif", Font.BOLD, 20));
            g.drawString("Recieved sms", 20, 200);
            for(int i = 0; i < ut.currentUser.sms.size(); i++) {
                g.setFont(new Font("Sans Serif", Font.PLAIN, 15));
                g.setColor(Color.BLACK);
                g.drawString(String.format("%5s: %s", ut.currentUser.sms.get(i).getKey(),  ut.currentUser.sms.get(i).getValue()), 20, 225 + 15 * i);
            }

        }
    }

}
