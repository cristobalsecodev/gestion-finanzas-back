package com.gestionFinanzas.Shared.ExceptionHandler;

import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.*;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.IllegalArgumentException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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

        String description = "An error occurred while communicating with the external API. Please check the service status or try again later";

        return buildErrorDetail(
                HttpStatus.valueOf(exception.getStatusCode().value()),
                exception.getMessage(),
                description
        );

    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ProblemDetail handleTokenNotFoundException(TokenNotFoundException exception) {

        String description = "The JWT token is missing from the request header";

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                description
        );

    }

    @ExceptionHandler(EmailException.class)
    public ProblemDetail handleEmailException(EmailException exception) {

        return buildErrorDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                exception.getMessage()
        );

    }

    @ExceptionHandler(ResourceConflictException.class)
    public ProblemDetail ResourceConflictExceptionHandling(ResourceConflictException exception) {

        String description = "A conflict occurred: The requested operation could not be completed due to a resource conflict";

        return buildErrorDetail(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                description
        );

    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException exception) {

        String description = "The requested resource could not be found. Please verify the input and try again";

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        errorDetail.setProperty("description", exception.getMessage());

        return buildErrorDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                description
        );

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException exception) {

        String description = "Invalid argument provided: The value does not meet the required format or constraints expected by the service";

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                description
        );

    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {

        ProblemDetail errorDetail = null;

        if (exception instanceof BadCredentialsException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.UNAUTHORIZED,
                    exception.getMessage(),
                    "The username or password is incorrect"
            );

        }

        if (exception instanceof AccountStatusException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    exception.getMessage(),
                    "The account is locked"
            );

        }

        if (exception instanceof AccessDeniedException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    exception.getMessage(),
                    "You are not authorized to access this resource"
            );

        }

        if (exception instanceof SignatureException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    exception.getMessage(),
                    "The JWT signature is invalid"
            );

        }

        if (exception instanceof ExpiredJwtException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    exception.getMessage(),
                    "The JWT token has expired"
            );

        }

        if (errorDetail == null) {

            errorDetail = buildErrorDetail(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    exception.getMessage(),
                    "Unknown internal server error"
            );

        }

        return errorDetail;
    }

    private ProblemDetail buildErrorDetail(HttpStatus statusCode, String message, String description) {

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(statusCode, message);
        errorDetail.setProperty("description", description);

        return errorDetail;

    }

}
