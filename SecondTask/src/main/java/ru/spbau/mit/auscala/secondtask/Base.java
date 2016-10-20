package ru.spbau.mit.auscala.secondtask;

import scala.reflect.ScalaSignature;
import scala.runtime.TraitSetter;

@ScalaSignature(bytes = "\u0006\u0001\u001d2q!\u0001\u0002\u0011\u0002\u0007\u0005QA\u0001\u0003CCN,'\"A\u0002\u0002\u000fq*W\u000e\u001d;z}\r\u00011C\u0001\u0001\u0007!\t9!\"D\u0001\t\u0015\u0005I\u0011!B:dC2\f\u0017BA\u0006\t\u0005\u0019\te.\u001f*fM\")Q\u0002\u0001C\u0001\u001d\u00051A%\u001b8ji\u0012\"\u0012a\u0004\t\u0003\u000fAI!!\u0005\u0005\u0003\tUs\u0017\u000e\u001e\u0005\b'\u0001\u0001\r\u0011\"\u0001\u0015\u0003\u0005AX#A\u000b\u0011\u0005\u001d1\u0012BA\f\t\u0005\rIe\u000e\u001e\u0005\b3\u0001\u0001\r\u0011\"\u0001\u001b\u0003\u0015Ax\fJ3r)\ty1\u0004C\u0004\u001d1\u0005\u0005\t\u0019A\u000b\u0002\u0007a$\u0013\u0007\u0003\u0004\u001f\u0001\u0001\u0006K!F\u0001\u0003q\u0002B\u0001\u0002\t\u0001\t\u0006\u0004%\t\u0001F\u0001\u0002s\"A!\u0005\u0001E\u0001B\u0003&Q#\u0001\u0002zA!)A\u0005\u0001D\u0001)\u0005\u0019am\\8\t\u000b\u0019\u0002A\u0011\u0001\u000b\u0002\u0007\u001d|w\u000e")
public interface Base {
    int x();

    @TraitSetter
    void setX(int var1);

    int y();

    int foo();

    int goo();

    abstract class AbstractBase {
        public static int y(Base instance) {
            return instance.goo();
        }

        public static int goo(Base instance) {
            return instance.x() + 2;
        }

        public static void init(Base instance) {
            instance.setX(1);
        }
    }
}
