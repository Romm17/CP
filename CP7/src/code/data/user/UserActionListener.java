package code.data.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserActionListener implements ActionListener{

    private UserFrame uf;

    public UserActionListener(UserFrame myFrame){
        this.uf = myFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().toString().equals("Accept")){
            System.out.println("Accept");
            uf.ut.currentUser.session.status = 2;
            uf.ut.currentUser.session.speaking();
        }
        else if(e.getActionCommand().toString().equals("Decline")){
            User temp = uf.ut.currentUser.session.userIn;
            uf.ut.currentUser.session = null;
            temp.session = null;
            temp.ut.uf.render();
        }
        else if(e.getActionCommand().toString().equals("Disconnect")){
            uf.ut.currentUser.session.status = 3;
        }
        else if(e.getActionCommand().toString().equals("Send")){
            System.out.println("Here");
            User temp = null;
            for(int i = 0; i < uf.ut.users.size(); i++)
                if(uf.ut.users.get(i).name.equals(uf.tf.getText())){
                    temp = uf.ut.users.get(i);
                    break;
                }
            uf.ut.currentUser.sendSms(temp, uf.ta.getText());
        }
        else{
            uf.ut.call(e.getActionCommand());
        }
        uf.render();
    }
}
