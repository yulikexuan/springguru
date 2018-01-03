//: guru.springframework.controllers.ControllerExceptionHandlers.java


package guru.springframework.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@ControllerAdvice
public class ControllerExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception e) {
        log.error(">>>>>>> Handling NumberFormatException: " + e.getMessage());
        return this.handleException(e, "400error");
    }

    private ModelAndView handleException(Exception e, String viewName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject("exception", e);

        return mav;
    }

}///~