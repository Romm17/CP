package code.main;

import code.data.Table;
import code.data.operator.Operator;
import code.data.user.User;
import code.data.user.UserThread;
import code.screen.FrameThread;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class Society {
    public static ArrayList<User> users;
    static ArrayList<Operator> operators;
    static ScheduledExecutorService usersThread ;
    public static Table t;
    public static UserThread ut[];

    public static void addUT(User u){
        if(u.ut != null)
            return;
        else{
            u.ut = new UserThread(u, users);
        }
    }

    public static void main(String args[]) {
        operators = new ArrayList<Operator>();

        operators.add(new Operator("MTS", 0, 1, 5));
        operators.get(0).setCallCost("MTS", 0.2);
        operators.get(0).setSmsCost("MTS", 0.05);
        operators.get(0).setCallCost("Kievstar", 0.5);
        operators.get(0).setSmsCost("Kievstar", 0.2);
        operators.get(0).setCallCost("Life", 0.4);
        operators.get(0).setSmsCost("Life", 0.15);

        operators.add(new Operator("Kievstar", 1, 1, 5));
        operators.get(1).setCallCost("MTS", 0.5);
        operators.get(1).setSmsCost("MTS", 0.2);
        operators.get(1).setCallCost("Kievstar", 0.2);
        operators.get(1).setSmsCost("Kievstar", 0.05);
        operators.get(1).setCallCost("Life", 0.4);
        operators.get(1).setSmsCost("Life", 0.15);

        operators.add(new Operator("Life", 2, 1, 5));
        operators.get(2).setCallCost("MTS", 0.5);
        operators.get(2).setSmsCost("MTS", 0.2);
        operators.get(2).setCallCost("Kievstar", 0.4);
        operators.get(2).setSmsCost("Kievstar", 0.15);
        operators.get(2).setCallCost("Life", 0.2);
        operators.get(2).setSmsCost("Life", 0.05);

        users = new ArrayList<User>();

        users.add(new User("Zeka", 1, operators.get(0)));
        users.add(new User("Roma", 2, operators.get(2)));
        users.add(new User("Vanya", 0.5, operators.get(2)));
        users.add(new User("Serega", 0.8, operators.get(0)));
        users.add(new User("Vova", 0.4, operators.get(1)));

        t = new Table();
        FrameThread ft = new FrameThread(t, users);

        ut = new UserThread[users.size()];

        users.get(1).sendSms(users.get(2), "Hello!");
    }
}
