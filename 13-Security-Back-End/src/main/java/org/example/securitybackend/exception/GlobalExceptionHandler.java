package org.example.securitybackend.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.example.securitybackend.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "User Not Found",
                ex.getMessage()
        );
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public APIResponse handleBadCredentialsException(BadCredentialsException ex) {
        return new APIResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Invalid Credentials",
                ex.getMessage()
        );
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public APIResponse handleExpiredJwtException(ExpiredJwtException ex) {
        return new APIResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "expired token",
                ex.getMessage()
        );
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public APIResponse HandleRunTimeException(RuntimeException ex) {
        return new APIResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "error occurred",
                ex.getMessage()
        );
    }
}
