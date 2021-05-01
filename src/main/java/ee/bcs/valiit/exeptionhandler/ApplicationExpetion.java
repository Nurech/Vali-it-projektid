package ee.bcs.valiit.exeptionhandler;

public class ApplicationExpetion extends RuntimeException {
    public ApplicationExpetion(String message) {
        super(message);
    }
}
