package com.springintro.usersystem.io;

public class ConsoleWriter implements OutputWriter {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }

    @Override
    public void writeLine(int n) {
        System.out.println(n);
    }
}
