//: guru.springframework.controllers.ControllerExceptionHandlers.java


package guru.springframework.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;


@Slf4j
@ControllerAdvice
public class ControllerExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NumberFormatException.class, WebExchangeBindException.class})
    public String handleNumberFormatException(Exception e, Model model) {

        log.error(">>>>>>> Handling WebExchangeBindException & " + "NumberFormatException: " + e.getMessage());

        model.addAttribute("exception", e);

        /*
         * Return the template name of "400error.html"
         */
        return "400error";
    }

}///~