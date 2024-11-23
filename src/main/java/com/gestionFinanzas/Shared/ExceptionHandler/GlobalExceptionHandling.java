package com.gestionFinanzas.Shared.ExceptionHandler;

import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.*;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.IllegalArgumentException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ApiException.class)
    public ProblemDetail ApiExceptionHandling(ApiException exception) {

        // Mensaje que se muestra en el front
        String messageForUser = "An error occurred while communicating with the external API";

        return buildErrorDetail(
                HttpStatus.valueOf(exception.getStatusCode().value()),
                messageForUser,
                exception.getMessage()
        );

    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ProblemDetail handleTokenNotFoundException(TokenNotFoundException exception) {

        // Mensaje que se muestra en el front
        String messageForUser = "The JWT token is missing from the request header";

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                messageForUser,
                exception.getMessage()
        );

    }

    @ExceptionHandler(EmailException.class)
    public ProblemDetail handleEmailException(EmailException exception) {

        String messageForUser = "An error occurred while sending an email";

        return buildErrorDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                messageForUser,
                exception.getMessage()
        );

    }

    @ExceptionHandler(ResourceConflictException.class)
    public ProblemDetail ResourceConflictExceptionHandling(ResourceConflictException exception) {

        String messageForUser = "A conflict occurred: The requested operation could not be completed due to a resource conflict";

        return buildErrorDetail(
                HttpStatus.CONFLICT,
                messageForUser,
                exception.getMessage()
        );

    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException exception) {

        String messageForUser = "The requested resource could not be found";

        return buildErrorDetail(
                HttpStatus.NOT_FOUND,
                messageForUser,
                exception.getMessage()
        );

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException exception) {

        String messageForUser = "Invalid argument provided: The value does not meet the required format or constraints expected by the service";

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                messageForUser,
                exception.getMessage()
        );

    }

    @ExceptionHandler(DataAccessException.class)
    public ProblemDetail handleIllegalArgumentException(DataAccessException exception) {

        String messageForUser = "An error occurred while trying to connect with the database. Please try again later";

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                messageForUser,
                exception.getMessage()
        );

    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception) {

        ProblemDetail errorDetail = null;

        if (exception instanceof BadCredentialsException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.UNAUTHORIZED,
                    "The username or password is incorrect",
                    exception.getMessage()

            );

        }

        if (exception instanceof AccountStatusException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    "The account is locked",
                    exception.getMessage()

            );

        }

        if (exception instanceof AccessDeniedException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    "You are not authorized to access this resource",
                    exception.getMessage()

            );

        }

        if (exception instanceof SignatureException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    "The JWT signature is invalid",
                    exception.getMessage()
            );

        }

        if (exception instanceof ExpiredJwtException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.FORBIDDEN,
                    "The JWT token has expired",
                    exception.getMessage()
            );

        }

        if (errorDetail == null) {

            errorDetail = buildErrorDetail(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unknown internal server error",
                    exception.getMessage()
            );

        }

        return errorDetail;
    }

    private ProblemDetail buildErrorDetail(HttpStatus statusCode, String messageForUser, String descriptionError) {

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(statusCode, messageForUser);
        errorDetail.setProperty("description", descriptionError);

        // Registramos el error en la consola
        log.error(descriptionError);

        return errorDetail;

    }

}
