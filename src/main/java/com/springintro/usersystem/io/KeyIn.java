// http://www.java2s.com/Tutorials/Java/IO_How_to/Console/Create_a_Console_menu.htm
package com.springintro.usersystem.io;

public class KeyIn {

    //*******************************
    //   support methods
    //*******************************
    //Method to display the user's prompt string
    public static void printPrompt(String prompt) {
        System.out.print(prompt + " ");
        System.out.flush();
    }

    //Method to make sure no data is available in the
    //input stream
    public static void inputFlush() {
        int dummy;
        int bAvail;

        try {
            while ((System.in.available()) != 0)
                dummy = System.in.read();
        } catch (java.io.IOException e) {
            System.out.println("Input error");
        }
    }

    public static String inString() {
        int aChar;
        String s = "";
        boolean finished = false;

        while (!finished) {
            try {
                aChar = System.in.read();
                if (aChar < 0 || (char) aChar == '\n')
                    finished = true;
                else if ((char) aChar != '\r')
                    s = s + (char) aChar; // Enter into string
            }

            catch (java.io.IOException e) {
                System.out.println("Input error");
                finished = true;
            }
        }

        return s;
    }

    public static int inInt(String prompt) {
        while (true) {
            inputFlush();
            printPrompt(prompt);
            try {
                return Integer.parseInt(inString().trim());
            }

            catch (NumberFormatException e) {
                System.out.println("Invalid input. Not an integer");
            }
        }
    }
}
