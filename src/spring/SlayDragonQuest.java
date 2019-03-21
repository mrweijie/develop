package spring;

import java.io.PrintStream;

public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    @Override
    public void embark() {
        System.out.println("SlayDragonQuest....");
    }

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }
}

