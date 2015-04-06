package code.data.operator;

import code.main.Society;

public class SessionThread implements Runnable{

    Thread t;
    Session session;

    public SessionThread(Session session){
        super();
        t = new Thread(this);
        this.session = session;
        t.start();
    }

    @Override
    public void run(){
        while(session.status != 3){
            System.out.println("***Speaking***");
        }
        System.out.printf("#CALL__END:  %8s   call to                %8s   has just finished after %3.1f seconds.\n",
                session.userIn.name, session.userOut.name, session.getLength()/1000.0) ;
        Society.t.add(String.format("#CALL__END:  %8s   call to                %8s   has just finished after %3.1f seconds.\n",
                session.userIn.name, session.userOut.name, session.getLength() / 1000.0));
        session.status = 0;
        session.finish();
        session.userIn.session = null;
        session.userOut.session = null;
        session.userIn.ut.uf.render();
        session.userOut.ut.uf.render();
        session = null;
    }
}
