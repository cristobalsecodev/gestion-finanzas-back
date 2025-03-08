package com.gestionFinanzas.Shared.ExceptionHandler;

import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.*;
import com.gestionFinanzas.Shared.ExceptionHandler.Exceptions.IllegalArgumentException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(ApiException.class)
    public ProblemDetail handleApiException(ApiException exception) {

        return buildErrorDetail(
                HttpStatus.valueOf(exception.getStatusCode().value()),
                "An error occurred while communicating with an external API",
                exception.getMessage()
        );

    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ProblemDetail handleTokenNotFoundException(TokenNotFoundException exception) {

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                "The JWT token is missing from the request header"
        );

    }

    @ExceptionHandler(EmailException.class)
    public ProblemDetail handleEmailException(EmailException exception) {

        return buildErrorDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred while sending an email",
                exception.getMessage()
        );

    }

    @ExceptionHandler(ResourceConflictException.class)
    public ProblemDetail handleResourceConflictException(ResourceConflictException exception) {

        return buildErrorDetail(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                "A conflict occurred: The requested operation could not be completed due to a resource conflict"
        );

    }

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException exception) {

        return buildErrorDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                "The requested resource could not be found"
        );

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException exception) {

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                "Invalid argument provided: The value does not meet the required format or constraints expected by the service"
        );

    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException exception) {

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                "The request could not be processed due to invalid input"
        );

    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ProblemDetail handleUnprocessableEntityException(UnprocessableEntityException exception) {

        return buildErrorDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                "Invalid argument provided: The value does not meet the required format or constraints expected by the service"
        );

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

        if (exception.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolation) {
            String constraintName = extractConstraintName(constraintViolation);

            // Comprueba el tipo de restricción
            if ("unique_name_type_user".equals(constraintName)) {

                return buildErrorDetail(
                        HttpStatus.CONFLICT,
                        "A category with the same name and type already exists",
                        "Duplicate categories are not allowed"
                );

            }

            if("unique_name_type_user_category".equals(constraintName)) {

                return buildErrorDetail(
                        HttpStatus.CONFLICT,
                        "A subcategory with the same name already exists in this category",
                        "Duplicate subcategories are not allowed"
                );

            }
        }

        // Restricción genérica
        return buildErrorDetail(
                HttpStatus.CONFLICT,
                "Data constraint violation",
                exception.getMessage()
        );
    }

    // Extrae el nombre de la restricción
    private String extractConstraintName(org.hibernate.exception.ConstraintViolationException exception) {

        // Patrón para extraer texto entre comillas españolas «»
        Pattern pattern = Pattern.compile("«([^»]*)»");
        Matcher matcher = pattern.matcher(exception.getMessage());

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
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

        if(exception.getMessage().contains("JDBC") || exception instanceof DataAccessException) {

            errorDetail = buildErrorDetail(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "The database is temporarily unavailable. Please try again later",
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
