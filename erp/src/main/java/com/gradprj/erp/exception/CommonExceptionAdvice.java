package com.gradprj.erp.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {
    @ExceptionHandler(Exception.class) //Exception.class 예외에 대해서 처리함.
    public String except(Exception ex, Model model) {
        log.error("Exception............" + ex.getMessage());
        model.addAttribute("exception", ex);
        log.error(model);
        return "error_page";
    }
}
