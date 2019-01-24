package softLaunch.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourseExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleProductNotFoundException
            (ClientNotFoundException e, HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails();
        error.setStatus(404l);
        error.setTimestamp(System.currentTimeMillis());
        error.setTitle("Cliente não cadastrado na base");

        return error;
    }

}