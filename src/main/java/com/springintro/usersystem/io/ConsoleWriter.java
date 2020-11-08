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

    @Override
    public void write(String text) {
        System.out.print(text);
    }

    @Override
    public void write(int n) {
        System.out.print(n);
    }
}
