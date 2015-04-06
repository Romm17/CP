package code.data;

import java.awt.*;
import java.util.ArrayList;

public class Table {

    private ArrayList<String> table;

    public Table(){
        table = new ArrayList<String>(10);
    }

    synchronized public void add(String d){
        if(table.size() == 10)
            table.remove(0);
        table.add(d);
    }

    public void ShowTable(Graphics g){
        for(int i = 0; i < table.size(); i++){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Sans Serif", Font.PLAIN, 15));
            g.drawString(table.get(i), 0 + 10, 310 + i * 30);
            g.drawLine(0, 320 + i * 30, 600, 320 + i * 30);
        }
    }
}
