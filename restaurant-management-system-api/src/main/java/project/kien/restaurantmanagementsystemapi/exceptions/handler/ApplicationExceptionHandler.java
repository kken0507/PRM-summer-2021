package project.kien.restaurantmanagementsystemapi.exceptions.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.kien.restaurantmanagementsystemapi.dtos.common.ErrorDto;
import project.kien.restaurantmanagementsystemapi.exceptions.*;
import project.kien.restaurantmanagementsystemapi.utils.constants.ConstantUtil;
import project.kien.restaurantmanagementsystemapi.utils.enums.ErrorStatus;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;


/**
 * This class will catch all runtime exception of the project
 * then send response to client with errorDto/list<errorDto></>, HttpStatus
 */

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ApplicationExceptionHandler.class);


    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(ResourceNotFoundException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.NOT_FOUND.value(),
                ConstantUtil.EXCEPTION_RESOURCE_NOT_FOUND,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /* For forbidden exception*/
    @ResponseBody
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleException(AccessDeniedException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.ACCESS_DENIED.getCode(),
                ErrorStatus.ACCESS_DENIED.getReason(),
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /* For invalid jwt */
    @ResponseBody
    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidTokenException exception) {
        logger.error(exception);
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.INVALID_TOKEN.getCode(),
                ErrorStatus.INVALID_TOKEN.getReason(),
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseBody
    @ExceptionHandler(value = ChangePasswordException.class)
    public ResponseEntity<ErrorDto> handleException(ChangePasswordException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.INVALID_DATA_FIELD.getCode(),
                ErrorStatus.INVALID_DATA_FIELD.getReason(),
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
     * Is thrown when an invalid request is received
     *   eg: request to deactivate an already inactive account
     * */
    @ResponseBody
    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidRequestException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                ConstantUtil.EXCEPTION_INVALID_REQUEST,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
     * Is thrown when a user using other role's API
     * */
    @ResponseBody
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ErrorDto> handleException(UnauthorizedException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.UNAUTHORIZED.value(),
                ConstantUtil.EXCEPTION_UNAUTHORIZED,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> handleException(RuntimeException exception) {
        logger.error(exception);
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ConstantUtil.EXCEPTION_UNEXPECTED_ERROR,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

  /*  @ResponseBody
    @ExceptionHandler(value = DisabledException.class)
    public ResponseEntity<ErrorDto> handleException(DisabledException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.UNAUTHENTICATED.getCode(),
                ErrorStatus.UNAUTHENTICATED.getReason(),
                "The user is disable");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseBody
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleException(BadCredentialsException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.UNAUTHENTICATED.getCode(),
                ErrorStatus.UNAUTHENTICATED.getReason(),
                "Invalid username or password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }*/

    @ResponseBody
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleException(DataIntegrityViolationException exception) {
        logger.error(exception.getMessage());
        ErrorDto errorDto = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.CONFLICT.value(),
                ConstantUtil.EXCEPTION_FK_DUPLICATED,
                "Data Integrity Violation");
        /*if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("FK_copy_book")) {
            errorDto.setMessage("Cannot delete this book because there are existing copies of this book");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_role_name")) {
            errorDto.setMessage("Role's name must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_account_email")) {
            errorDto.setMessage("Account's email must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_account_rfid")) {
            errorDto.setMessage("Account's rfid must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_bookCopy_barcode")) {
            errorDto.setMessage("Book copy's barcode must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_bookCopy_rfid")) {
            errorDto.setMessage("Book copy's rfid must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_patronType_name")) {
            errorDto.setMessage("Patron type's name must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_copyType_name")) {
            errorDto.setMessage("Book copy type's name must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_book_isbn")) {
            errorDto.setMessage("Book's ISBN must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_bookCopyPositioin_shelf_line")) {
            errorDto.setMessage("Position of shelf-line must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_genre_ddc")) {
            errorDto.setMessage("DDC must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_position_rfid")) {
            errorDto.setMessage("Position's rfid must be unique");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_SDCopy_rfid")) {
            errorDto.setMessage("The rfid in security gate table is duplicated");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorDto(LocalDateTime.now().toString(),
                            ErrorStatus.SYSTEM_ERROR.getCode(),
                            ErrorStatus.SYSTEM_ERROR.getReason(),
                            exception.getMessage())
            );
        }*/
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }


    /**
     * When user request date with wrong format yyyy-mm-dd
     */

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleException(HttpMessageNotReadableException exception) {
        logger.error(exception.getMessage());
        ErrorDto errorDto = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                ConstantUtil.EXCEPTION_VALIDATION_FAILED,
                exception.getMessage());
        if (exception.getMessage() != null
                && exception.getMessage().contains("java.time.LocalDate")) {
            errorDto.setMessage("Invalid date must has format: yyyy-mm-dd");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleException(MethodArgumentNotValidException exception) {
        StringBuilder msg = new StringBuilder();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            msg.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
        }
        logger.error(msg);
        ErrorDto errorDto = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                ConstantUtil.EXCEPTION_VALIDATION_FAILED,
                msg.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleException(ConstraintViolationException exception) {
        logger.error(exception.getMessage());
        ErrorDto errorDto = new ErrorDto(LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                ConstantUtil.EXCEPTION_VALIDATION_FAILED,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorDto> handleException(CustomException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                exception.getHttpStatus().value(),
                exception.getError(),
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseBody
    @ExceptionHandler(value = EmailException.class)
    public ResponseEntity<ErrorDto> handleException(EmailException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.SYSTEM_ERROR.getCode(),
                ErrorStatus.SYSTEM_ERROR.getReason(),
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseBody
    @ExceptionHandler(value = PrintBarcodeException.class)
    public ResponseEntity<ErrorDto> handleException(PrintBarcodeException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.SYSTEM_ERROR.getCode(),
                ErrorStatus.SYSTEM_ERROR.getReason(),
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseBody
    @ExceptionHandler(value = ImportFileException.class)
    public ResponseEntity<ErrorDto> handleException(ImportFileException exception) {
        logger.error(exception.getMessage());
        ErrorDto error = new ErrorDto(LocalDateTime.now().toString(),
                ErrorStatus.IMPORT_ERROR.getCode(),
                ErrorStatus.IMPORT_ERROR.getReason(),
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
