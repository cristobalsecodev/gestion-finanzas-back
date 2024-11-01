package com.gestionFinanzas.Shared.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ApiException.class)
    public ProblemDetail ApiExceptionHandling(ApiException exception) {

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(exception.getStatusCode(), exception.getMessage());
        errorDetail.setProperty("description", "The JWT token is missing from the request header");

        return errorDetail;

    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ProblemDetail handleTokenNotFoundException(TokenNotFoundException exception) {

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        errorDetail.setProperty("description", "The JWT token is missing from the request header");

        return errorDetail;

    }

    @ExceptionHandler(ResourceConflictException.class)
    public ProblemDetail ResourceConflictExceptionHandling(ResourceConflictException exception) {

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
        errorDetail.setProperty("description", "A conflict occurred: " + exception.getMessage());

        return errorDetail;

    }

    private ResponseEntity<InfoResponse> buildResponse(String message, Integer statusCode) {
        return ResponseEntity.status(statusCode).body(new InfoResponse(message, statusCode));
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        if (exception instanceof BadCredentialsException) {

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "The username or password is incorrect");

            return errorDetail;
        }

        if (exception instanceof AccountStatusException) {

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked");

        }

        if (exception instanceof AccessDeniedException) {

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "You are not authorized to access this resource");

        }

        if (exception instanceof SignatureException) {

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT signature is invalid");

        }

        if (exception instanceof ExpiredJwtException) {

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The JWT token has expired");

        }

        if (errorDetail == null) {

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");

        }

        return errorDetail;
    }

}
