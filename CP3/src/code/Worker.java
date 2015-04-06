package code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.*;

public class Worker {

    private static long res[];
    private static MyRunnable threads[];
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("How do you want to input data?");
        System.out.println("1. From file");
        System.out.println("2. From console");
        int choice;
        try{
            choice = sc.nextInt();
        } catch(InputMismatchException e){
            e.printStackTrace();
            choice = -1;
        }
        switch (choice){
            case 1:
                fromFile();
                break;
            case 2:
                fromConsole();
                break;
            default:
                return;
        }
    }

    public static void fromFile() {
        int n;
        int t;
        FileInputStream is;
        try {
            is = new FileInputStream(System.getProperty("user.dir") + "/res/input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String sb = new String("");
        int temp;
        try {
            while((temp = is.read()) != -1){
                sb += (char) temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String res[] = sb.split(" ");
        n = Integer.parseInt(res[0]);
        t = Integer.parseInt(res[1]);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(actionByExecutors(n, t));
    }

    public static void fromConsole(){
        int n = 0;
        int t = 0;
        int counter = 5;
        boolean going = false;
        Scanner sc = new Scanner(System.in);
        while(!going && counter != 0) {
            try {
                System.out.print("Enter n: ");
                n = sc.nextInt();
                System.out.print("Enter number fo threads: ");
                t = sc.nextInt();
                going = true;
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Incorrect input! Try again. You have " + --counter + " posibilities.");
                going = false;
            }
        }
        if(counter == 0){
            return;
        }
        System.out.println(actionByThreadPool(n, t));
    }

    synchronized public static void setRes(int i, long val){
        res[i] = val;
    }

    public static boolean actionByThreadPool(int n, int threadNum){
        res = new long[threadNum];
        threads = new MyRunnable[threadNum];
        for(int i = 0; i < threadNum; i++){
            threads[i] = new MyRunnable(i, n);
        }
        for(int i = 0; i < threadNum; i++)
            try {
                threads[i].t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        for(int i = 1; i < threadNum; i++)
            if(res[i] != res[i-1]){
                return false;
            }
        return true;
    }

    public static boolean actionByExecutors(int n, int threadNum) {
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        Future<?> future[] = new Future[threadNum];
        for(int i = 0; i < threadNum; i++){
            future[i] = service.submit(new MyCallable<Long>(n));
        }
        for(int i = 1; i < threadNum; i++)
            try {
                if(!future[i].get().equals(future[i-1].get())){
                    service.shutdown();
                    return false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            service.shutdown();
        return true;
    }

}