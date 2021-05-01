package ee.bcs.valiit.exeptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ApplicationExpetion.class)
    public ResponseEntity<Object> handleSampleException(ApplicationExpetion e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), 400),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Internal Error", 500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> wrongPasswordExeption(Exception e){
//        e.printStackTrace();
//        return new ResponseEntity<>(new ErrorResponse("Wrong password", 400),
//                HttpStatus.I_AM_A_TEAPOT);
//    }

}
