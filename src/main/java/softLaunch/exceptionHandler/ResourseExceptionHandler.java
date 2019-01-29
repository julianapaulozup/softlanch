package softLaunch.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourseExceptionHandler {

    @ExceptionHandler(ClientNotFoundInWhitelistException.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorDetails handleProductNotFoundException
            (ClientNotFoundInWhitelistException e, HttpServletRequest request) {

        ErrorDetails error = new ErrorDetails();
        error.setStatus(404L);
        error.setTimestamp(System.currentTimeMillis());
        error.setTitle("Cliente não cadastrado na base");

        return error;
    }

}