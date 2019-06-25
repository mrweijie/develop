package stu.java.spring;

import java.io.PrintStream;

public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest(){
        stream.println("lalalalalalalalala");
    }

    public void singAfterQuest(){
        stream.println("lbbbbbbbbbbbb");
    }
}
