package code.data.operator;

import code.data.user.User;

public class Session {
    public User userIn, userOut ;
    private long expectedLength ;
    private long startTime ;
    public int status = 0;

    public Session(User from, User to) {
        this.userIn = from ;
        this.userOut = to ;

        this.startTime = System.currentTimeMillis() ;
    }

    public Session(User from, User to, long expectedLength) {
        this.userIn = from ;
        this.userOut = to ;
        this.expectedLength = expectedLength ;
        status = 1;
        this.startTime = System.currentTimeMillis() ;
    }

    public long callTime(){
        return System.currentTimeMillis() - startTime;
    }

    public void speaking(){
        userIn.ut.uf.render();
        userOut.ut.uf.render();
        this.startTime = System.currentTimeMillis();
        SessionThread st = new SessionThread(this);
    }

    public boolean isFinished() {
        long currentTime = System.currentTimeMillis() ;
        return currentTime > startTime + expectedLength ;
    }

    public void finish() {
        this.userIn.notifyFree();
        this.userOut.notifyFree();
    }

    public long getLength() {
        return System.currentTimeMillis() - startTime ;
    }
}
