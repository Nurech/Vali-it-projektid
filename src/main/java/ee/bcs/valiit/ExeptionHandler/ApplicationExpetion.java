package ee.bcs.valiit.ExeptionHandler;

public class ApplicationExpetion extends RuntimeException {
    public ApplicationExpetion(String message) {
        super(message);
    }
}
