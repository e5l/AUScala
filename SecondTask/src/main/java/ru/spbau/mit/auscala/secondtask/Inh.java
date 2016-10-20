package ru.spbau.mit.auscala.secondtask;

import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\u0006\u0001M2A!\u0001\u0002\u0001\u000b\t\u0019\u0011J\u001c5\u000b\u0003\r\tq\u0001P3naRLhh\u0001\u0001\u0014\u0007\u00011A\u0002\u0005\u0002\b\u00155\t\u0001BC\u0001\n\u0003\u0015\u00198-\u00197b\u0013\tY\u0001B\u0001\u0004B]f\u0014VM\u001a\t\u0003\u001b9i\u0011AA\u0005\u0003\u001f\t\u0011AAQ1tK\")\u0011\u0003\u0001C\u0001%\u00051A(\u001b8jiz\"\u0012a\u0005\t\u0003\u001b\u0001AQ!\u0006\u0001\u0005\u0002Y\t1AZ8p+\u00059\u0002CA\u0004\u0019\u0013\tI\u0002BA\u0002J]R<Qa\u0007\u0002\t\u0002q\t1!\u00138i!\tiQDB\u0003\u0002\u0005!\u0005adE\u0002\u001e?\u001d\u0002\"\u0001I\u0013\u000e\u0003\u0005R!AI\u0012\u0002\t1\fgn\u001a\u0006\u0002I\u0005!!.\u0019<b\u0013\t1\u0013E\u0001\u0004PE*,7\r\u001e\t\u0003A!J!!K\u0011\u0003\u0011I+hN\\1cY\u0016DQ!E\u000f\u0005\u0002-\"\u0012\u0001\b\u0005\u0006[u!\tAL\u0001\u0004eVtG#A\u0018\u0011\u0005\u001d\u0001\u0014BA\u0019\t\u0005\u0011)f.\u001b;\t\u000bUiB\u0011\u0002\f")
public class Inh implements Base {
    private int x;
    private /*final*/ int y;
    private volatile boolean flag;

    public static void run() {
        InhObject.instance.run();
    }

    @Override
    public int x() {
        return this.x;
    }

    @TraitSetter
    @Override
    public void setX(int x) {
        this.x = x;
    }

    private synchronized int yLazyCompute() {
        if (!this.flag) {
            this.y = AbstractBase.y(this);
            this.flag = true;
        }

        return this.y;
    }

    @Override
    public int y() {
        return this.flag ? this.y : this.yLazyCompute();
    }

    @Override
    public int goo() {
        return AbstractBase.goo(this);
    }

    @Override
    public int foo() {
        return 3;
    }

    public Inh() {
        AbstractBase.init(this);
    }

}

