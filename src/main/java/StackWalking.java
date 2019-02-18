import java.util.Arrays;

/**
 * Created by mtumilowicz on 2019-02-18.
 */
class StackWalking {
    void m1() {
        m2();
    }

    private void m2() {
        m3();
    }

    private void m3() {
        System.out.println("---");
        System.out.println("stack from m3():");
        Arrays.stream(new Throwable().getStackTrace())
                .forEach(System.out::println);
        System.out.println("---");

        m4();
    }

    private void m4() {
        System.out.println("---");
        System.out.println("stack from m4():");
        Arrays.stream(Thread.currentThread().getStackTrace())
                .forEach(System.out::println);
        System.out.println("---");
    }
}
