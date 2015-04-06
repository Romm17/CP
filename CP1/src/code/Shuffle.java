package code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Shuffle {
    public static int SIZE_X = 20;
    public static int SIZE_Y = 20;

    public static int arr[][];

    public static void arrayCreator(){
        Scanner sc = new Scanner(System.in);
        boolean correct;
        do{
            correct = true;
            try{
                System.out.print("Enter the horizontal size of array: ");
                SIZE_X = sc.nextInt();
                System.out.print("Enter the vertical size of array: ");
                SIZE_Y = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Size of array can be only integer. Try again");
                sc.nextLine();
                correct = false;
            }
        }
        while(!correct || SIZE_X < 1 || SIZE_Y < 1);
        arr = new int[SIZE_Y][SIZE_X];
        do{
            correct = true;
            try{
                System.out.println("Enter array: ");
                for(int i = 0; i < SIZE_Y; i++){
                    for(int j = 0; j < SIZE_X; j++) {
                        arr[i][j] = sc.nextInt();
                    }
                }
            } catch(InputMismatchException e){
                System.out.println("Please, enter only integer values. Try again");
                sc.nextLine();
                correct = false;
            }
        }
        while(!correct);
    }

    public static void arrayPrinter(){
        for(int i = 0; i < SIZE_Y; i++){
            for(int j = 0; j < SIZE_X; j++) {
                System.out.print(arr[i][j] + " ");
            }
        }
        System.out.println();
    }

    public static void swap(int y1, int x1, int y2, int x2){
        int curr = arr[y1][x1];
        arr[y1][x1] = arr[y2][x2];
        arr[y2][x2] = curr;
    }

    public static void shuffle() {
        int mustShuffle = SIZE_X * SIZE_Y / 4;
        if(mustShuffle > 1) {
            boolean temp[][] = new boolean[SIZE_Y][SIZE_X];
            int pos[] = new int[mustShuffle];
            for (int i = 0; i < mustShuffle; i++) {
                int ind = (int) Math.floor(Math.random() * SIZE_X * SIZE_Y);
                if (!temp[ind/SIZE_Y][ind%SIZE_X]) {
                    temp[ind/SIZE_Y][ind%SIZE_X] = true;
                    pos[i] = ind;
                } else
                    i--;
            }
            for (int i = 0; i < mustShuffle; i += 2) {
                if (i == mustShuffle - 1)
                    swap(pos[i]/SIZE_Y, pos[i]%SIZE_X, pos[0]/SIZE_Y, pos[0]%SIZE_X);
                else
                    swap(pos[i]/SIZE_Y, pos[i]%SIZE_X, pos[i + 1]/SIZE_Y, pos[i + 1]%SIZE_X);
            }
        }
    }

    public static void main(String args[]){
        arrayCreator();
        arrayPrinter();
        shuffle();
        arrayPrinter();
    }
}
