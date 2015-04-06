package code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    public static void main(String agrs[]){
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
        String s = new String("");
        FileInputStream is;
        try {
            is = new FileInputStream(System.getProperty("user.dir") + "/res/input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int temp;
        try {
            while((temp = is.read()) != -1){
                s += (char) temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        System.out.println(checkNumber(s));
    }

    public static void fromConsole(){
        String s = new String("");
        int counter = 5;
        boolean going = false;
        Scanner sc = new Scanner(System.in);
        while(!going && counter != 0) {
            try {
                System.out.print("Enter phone number: ");
                s = sc.nextLine();
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
        System.out.println(checkNumber(s));
    }

    public static boolean checkNumber(String number){
        Pattern p = Pattern.compile("^(\\+?[0-8] )?((\\([0-9]{3,4}\\) )|([0-9]{3,4} ))?(([0-9]{2,3} [0-9]{2,3} [0-9]{2,3})|([0-9]{2,3}-[0-9]{2,3}-[0-9]{2,3}))\r?\n?$");
        Matcher m = p.matcher(number);
        return m.matches();
    }
}
