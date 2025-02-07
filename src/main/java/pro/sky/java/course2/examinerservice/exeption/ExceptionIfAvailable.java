package pro.sky.java.course2.examinerservice.exeption;

public class ExceptionIfAvailable extends IllegalArgumentException {
    public ExceptionIfAvailable(String s) {
        super(s);
    }
    public ExceptionIfAvailable() {
    }
}