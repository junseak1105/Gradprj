package com.gradprj.erp.exception;

import com.gradprj.erp.config.DefaultRes;
import com.gradprj.erp.config.ResponseMessages;
import com.gradprj.erp.config.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public DefaultRes DB_dup_except(Exception ex, Model model) {
        log.error("DefaultRes............" + ex.getMessage());
        log.error(model);
        return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessages.Data_duplicated);
    }

//    @ExceptionHandler(RuntimeException.class) // RuntimeException이 발생하면 해당 메소드가 실행됨
//    public String except(Exception ex, Model model) {
//        log.error("Exception............" + ex.getMessage());
//        model.addAttribute("exception", ex);
//        log.error(model);
//        return "error_page";
//    }


}
