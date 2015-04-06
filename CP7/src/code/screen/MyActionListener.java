package code.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener{

    private MyFrame myFrame;

    public MyActionListener(MyFrame myFrame){
        this.myFrame = myFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        myFrame.ft.addUserThread(e.getActionCommand());
    }
}
