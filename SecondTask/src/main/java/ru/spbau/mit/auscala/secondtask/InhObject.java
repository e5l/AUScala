package ru.spbau.mit.auscala.secondtask;

public final class InhObject implements Runnable {
    public static final InhObject instance = new InhObject();

    private InhObject() {
    }

    @Override
    public void run() {
    }

    private int foo() {
        return 4;
    }
}
