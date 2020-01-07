package finalproject.ninegag.controller;

import finalproject.ninegag.exceptions.BadRequestException;
import finalproject.ninegag.exceptions.NotFoundException;
import finalproject.ninegag.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class AbstractController {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(Exception e){
        ErrorDTO errorDTO = new ErrorDTO(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                e.getClass().getName());
        return errorDTO;
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleSQLExceptions(Exception e){
        ErrorDTO errorDTO = new ErrorDTO(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                e.getClass().getName());
        return errorDTO;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequests(Exception e){
        ErrorDTO errorDTO = new ErrorDTO(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                e.getClass().getName());
        return errorDTO;
    }

}
