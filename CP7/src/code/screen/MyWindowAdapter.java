package code.screen;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowAdapter extends WindowAdapter{
    Frame myFrame;

    public MyWindowAdapter(Frame myFrame){
        this.myFrame = myFrame;
    }

    @Override
    public void windowClosing(WindowEvent e){
        myFrame.setVisible(false);
        myFrame.dispose();
    }
}
