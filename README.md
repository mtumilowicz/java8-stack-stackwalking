# java8-stack-stackwalking

# manual
* `StackTraceElement` class represents a stack frame
    * useful methods:
        * `int getLineNumber()`
        * `String getClassName()`
        * `String getMethodName()`
* to get all frames of the current thread's stack
    * first element of the array is the top frame in the stack (last method invocation)
    * `StackTraceElement[] frames = new Throwable().getStackTrace();`
    * `StackTraceElement[] frames = Thread.currentThread().getStackTrace();`
* drawbacks:
    * `getStrackTrace()` returns entire stack - we may need only top 5 elements for example
    * no easy way to filter
    * `StackTraceElement` contains method names and class names, but we often need class references
    
# project description
1. for test purposes
    ```
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
            Arrays.stream(new Throwable().getStackTrace()) // getStackTrace on Throwable
                    .forEach(System.out::println);
            System.out.println("---");
    
            m4();
        }
    
        private void m4() {
            System.out.println("---");
            System.out.println("stack from m4():");
            Arrays.stream(Thread.currentThread().getStackTrace()) // getStackTrace on Thread
                    .forEach(System.out::println);
            System.out.println("---");
        }
    }
    ```
1. simulation
    ```
    @Test
    public void simulation() {
        new StackWalking().m1();
    }
    ```
    produces:
    ```
    ---
    stack from m3():
    StackWalking.m3(StackWalking.java:18)
    StackWalking.m2(StackWalking.java:12)
    StackWalking.m1(StackWalking.java:8)
    StackWalkingTest.simulation(StackWalkingTest.java:10)
    ... // other
    ---
    ---
    stack from m4():
    java.base/java.lang.Thread.getStackTrace(Thread.java:1606)
    StackWalking.m4(StackWalking.java:28)
    StackWalking.m3(StackWalking.java:22)
    StackWalking.m2(StackWalking.java:12)
    StackWalking.m1(StackWalking.java:8)
    StackWalkingTest.simulation(StackWalkingTest.java:10)
    .. // other
    ---
    ```