/**
 * Created by Grace on 4/20/2015.
 *
 * This program will indicate whether you can win a game where you start with n number of bears. You can then return some bears, but you must follow these rules:
 If n is even, then you may give back exactly n/2 bears.
 If n is divisible by 3 or 4, then you may multiply the last two digits of n and give back this many bears.
 If n is divisible by 5, then you may give back exactly 42 bears.
 The goal of the game is to end up with EXACTLY 42 bears.
 */

import java.io.*;

public class TeddyBear {
    /**
     *
     * @param num The number of bears the user starts out with.
     * @return Whether it is possible for the user to win starting out that number of bears.
     */
    public static boolean bear(int num) {
        if (num < 42) {
            return false;
        }
        else if (num == 42) {
            return true;
        }

        if (num % 2 == 0 && num % 5 == 0){
            return bear(num/2) ||  bear (num-42);
        }
        else if (num % 2 == 0 && (num % 3 == 0 || num % 4 == 0) && num % 5 != 0){
            return bear(num / 2) || bear (num - (num%10 * (num % 100)/ 10));
        }
        else if ((num %3 == 0 || num % 4 == 0) && num % 5 == 0 && num % 2 != 0 ){
            return  bear(num - 42);
        }

        return false;
    }

    /**
     *
     * @param input A string to store the user input.
     * @param buff A BufferedReader to read in the user input.
     * @return An integer, the number of bears to start with.
     * @throws IOException
     */
    public static int inputValidator(String input, BufferedReader buff) throws IOException{
        boolean valid = false;
        int numOfBears = 0;
        do {
            try {
                valid = true;
                input = buff.readLine();
                numOfBears = Integer.parseInt(input);

            }
            catch (NumberFormatException n) {
                valid = false;
                System.out.println("Invalid input. Please enter a number: ");
            }
        }while (valid == false);
        return numOfBears;
    }

    public static boolean more(BufferedReader buff) throws IOException{
        String resp = null;
        System.out.println("Would you like to input another number(y/n)?");
        resp = buff.readLine();
        while (!resp.equals("n") && !resp.equals("N") && !resp.equals("y") && !resp.equals("Y")){
            System.out.println("Invalid response. Would you like to input another number(y/n)?");
            resp = buff.readLine();
        }
        if (resp.equals("y") || resp.equals("Y")){
            return true;
        }
        return false;
    }

    public static void main (String[] args) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String resp = null;
        do {
            System.out.println("Please enter the number of bears you want to start with: ");
            int numOfBears = inputValidator(resp, buff);
            String win = bear(numOfBears) ? "You are able to win." : "Sorry, you are not able to win.";
            System.out.println(win);
        }while (more(buff));
    }
}
