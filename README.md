# java8-stack-stackwalking

* `StackTraceElement` class represents a stack frame
    * useful methods:
        * `int getLineNumber()`
        * `String getClassName()`
        * `String getMethodName()`
* to get all frames of the current thread’s stack
    * first element of the array is the top frame in the stack (last method invocation)
    * `StackTraceElement[] frames = new Throwable().getStackTrace();`
    * `StackTraceElement[] frames = Thread.currentThread().getStackTrace();`
* drawbacks:
    * `getStrackTrace()` returns entire stack - we may need only top 5 for example
    * no easy way to filter
    * `StackTraceElement` contains method names and class names, but we often need class references