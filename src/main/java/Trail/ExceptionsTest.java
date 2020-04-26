package Trail;

import java.io.FileNotFoundException;

public class ExceptionsTest {

    // It seems to me that "throws" label has no effect on unchecked exceptions?

    public static void throwsUncheckedException() {
        throw new RuntimeException();
    }

    public static void throwsUncheckedExceptionWithThrows() throws RuntimeException {
        throw new RuntimeException();
    }

    public static void catchUncheckedExceptionsAndHandle() {
        try {
            throwsUncheckedException();
        } catch (RuntimeException e){
            System.out.println("I caught an unchecked exception from a method that doesn't throw!");
        }
        try {
            throwsUncheckedExceptionWithThrows();
        } catch (RuntimeException e) {
            System.out.println("I caught an unchecked exception from a method that throws!");
        }
    }
    public static void callUncheckedExceptionsAndIgnore() {
        throwsUncheckedException();
        throwsUncheckedExceptionWithThrows();
    }

    // Must specify 'throws'
    public static void throwsCheckedException() throws java.io.FileNotFoundException {
        throw new java.io.FileNotFoundException();
    }

    public static void catchCheckedExceptionAndHandle() {
        try {
            throwsCheckedException();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Caught a checked expression!");
        }
    }
    // Must specify 'throws' -- exception goes up call stack.
    public static void callCheckedExceptionAndIgnore() throws java.io.FileNotFoundException {
        throwsCheckedException();
    }

    public static void main(String[] s) {
        catchCheckedExceptionAndHandle();

        try {
            callCheckedExceptionAndIgnore(); // Will not compile without testing.
        } catch (FileNotFoundException e) {
            System.out.println("Main caught a checked exception!");
        }


        catchUncheckedExceptionsAndHandle();
        try {
            callUncheckedExceptionsAndIgnore();
        } catch (RuntimeException e) {
            System.out.println("Main caught an unchecked exception from a method that does not throw!");
        }
        callUncheckedExceptionsAndIgnore(); // Crash
    }
}
