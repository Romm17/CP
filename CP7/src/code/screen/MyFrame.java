package code.screen;

import code.data.Table;
import code.data.user.User;

import java.awt.*;
import java.util.ArrayList;

public class MyFrame extends Frame{

    FrameThread ft;
    Table table;
    ArrayList<User> users;
    ArrayList<Button> buttons;

    public MyFrame(FrameThread ft, Table table, ArrayList<User> users){
        super();
        this.ft = ft;
        this.table = table;
        MyWindowAdapter mwa = new MyWindowAdapter(this);
        MyActionListener mal = new MyActionListener(this);
        addWindowListener(mwa);
        this.users = users;
        buttons = new ArrayList<Button>();
        for(int i = 0; i < users.size(); i++){
            buttons.add(new Button(users.get(i).name));
            buttons.get(i).setSize(50, 20);
            buttons.get(i).setLocation(50 + 55 * i, 100);
            add(buttons.get(buttons.size() - 1));
            buttons.get(i).addActionListener(mal);
        }
        Button notused = new Button("notused");
        add(notused);
        remove(notused);
    }

    public void paint(Graphics g){
        g.setColor(Color.blue);
        table.ShowTable(g);
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 600, 280);
        g.setColor(Color.blue);
        g.setFont(new Font("Sans Serif", Font.BOLD, 30));
        g.drawString("Who are you?", 100, 70);
    }

}
