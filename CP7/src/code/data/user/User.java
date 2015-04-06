package code.data.user;

import code.main.Society;
import code.data.operator.Operator;
import code.data.operator.Session;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    public String name;
    public Operator operator;
    public double money;
    public UserThread ut;
    public ArrayList<Pair<String, String>> sms;


    public Session session;

    public User(String name, double money, Operator operator) {
        this.name = name;
        this.money = money;
        this.operator = operator;
        ut = null;
        sms = new ArrayList<Pair<String, String>>();
    }

    public boolean changeOperator(Operator operator, boolean premium) {
        if (this.operator == operator)
            return true;
        if (operator.getStandardSubscriptionCost() > this.money)
            return false;
        this.money -= operator.getStandardSubscriptionCost();
        this.operator = operator;
        return true;
    }

    public void checkSession() {
        if (session != null && session.isFinished()) {
            session.finish();
            session = null;
        }
    }

    private boolean busy;

    public boolean isBusy() {
        checkSession();
        return this.busy;
    }

    public boolean call(User u, long expectedLength) {
        if (u == this)
            return false;
        if (u.isBusy()) {
            System.out.printf("#CALL_BUSY:  %8s   tried to call          %8s   but they were busy.\n", this.name, u.name);
            Society.t.add(String.format("#CALL_BUSY:  %8s   tried to call          %8s   but they were busy.\n", this.name, u.name));
            return false;
        }
        if(this.money < this.operator.getCallCost(u.operator.getName())) {
            System.out.printf("#CALL_CASH:  %8s   tried to call          %8s   but it costs %.2f while he had %.2f only.\n",
                    this.name, u.name,
                    this.operator.getCallCost(u.operator.getName()),
                    this.money);
            Society.t.add(String.format("#CALL_CASH:  %8s   tried to call          %8s   but it costs %.2f while he had %.2f only.\n",
                    this.name, u.name,
                    this.operator.getCallCost(u.operator.getName()),
                    this.money));
            return false ;
        }
        ut.uf.callOut(u.name);
        if(u.ut == null)
            u.ut = new UserThread(u, Society.users);
        u.ut.uf.callIn(name);
        System.out.printf("#CALLSTART:  %8s   called                 %8s   successfully.\n", this.name, u.name);
        Society.t.add(String.format("#CALLSTART:  %8s   called                 %8s   successfully.\n", this.name, u.name));
        this.money -= this.operator.getCallCost(u.operator.getName());
        this.busy = u.busy = true;
        this.session = u.session = new Session(this, u, expectedLength);
        return true;
    }

    public boolean sendSms(User u, String text) {
        System.out.println("In sms");
        if (u == this)
            return false;
        if(u == null){
            System.out.printf("#SMS__CASH:  %8s   tried to send sms to   ???? \n", this.name);
            Society.t.add(String.format("#SMS__CASH:  %8s   tried to send sms to   ???? \n", this.name));
            return false ;
        }
        if(this.money < this.operator.getSmsCost(u.operator.getName())) {
            System.out.printf("#SMS__CASH:  %8s   tried to send sms to   %8s   but it costs %.2f while he had %.2f only.\n",
                    this.name, u.name,
                    this.operator.getSmsCost(u.operator.getName()),
                    this.money);
            Society.t.add(String.format("#SMS__CASH:  %8s   tried to send sms to   %8s   but it costs %.2f while he had %.2f only.\n",
                    this.name, u.name,
                    this.operator.getSmsCost(u.operator.getName()),
                    this.money));
            return false ;
        }
        System.out.printf("#SMS__SENT:  %8s   sent sms to            %8s   successfully.\n", this.name, u.name);
        Society.t.add(String.format("#SMS__SENT:  %8s   sent sms to            %8s   successfully.\n", this.name, u.name));
        this.money -= this.operator.getSmsCost(u.operator.getName());
        u.sms.add(new Pair(name, text));
        return true;
    }

    public void notifyFree() {
        this.busy = false;
    }
}