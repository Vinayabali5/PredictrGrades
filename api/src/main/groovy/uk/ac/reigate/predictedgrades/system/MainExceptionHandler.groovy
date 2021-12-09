package uk.ac.reigate.predictedgrades.system

import javax.servlet.http.HttpServletRequest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import uk.ac.reigate.predictedgrades.dto.error.ErrorDto
import uk.ac.reigate.predictedgrades.exception.ApiException
import uk.ac.reigate.predictedgrades.exception.DataNotFoundException
import uk.ac.reigate.predictedgrades.exception.InvalidDataSuppliedException

@ControllerAdvice
class MainExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger("Exception Handling");
    
	/**
	 * This method is used to handle the DataNotFoundException. 
	 * 
	 * @param exception This is a reference to the exception for use during the method. 
	 * @return A ResponseEntity informing the end user of the problem. 
	 */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorDto> dataNotFound(HttpServletRequest req, Exception exception) {
        LOGGER.info("** Data Not Found");
        DataNotFoundException ex = (DataNotFoundException) exception
        return new ResponseEntity<ErrorDto> (new ErrorDto(message: ex.message != null ? ex.message : "Not Found"), HttpStatus.NOT_FOUND)
    }

	/**
	 * This method is used to handle the InvalidDataSuppliedException. 
	 * 
	 * @param exception This is a reference to the exception for use during the method. 
	 * @return A ResponseEntity informing the end user of the problem. 
	 */
    @ExceptionHandler(InvalidDataSuppliedException.class)
    public ResponseEntity<ErrorDto> invalidDataSupplied(HttpServletRequest req, Exception exception) {
        LOGGER.info("** Invalid Data Supplied");
        InvalidDataSuppliedException ex = (InvalidDataSuppliedException) exception
        return new ResponseEntity<ErrorDto> (new ErrorDto(message: ex.message != null ? ex.message : "An error occurred."), HttpStatus.BAD_REQUEST)
    }
    
	/**
	 * This method is used to handle the HttpMessageNotReadableException. 
	 * 
	 * @param exception This is a reference to the exception for use during the method. 
	 * @return A ResponseEntity informing the end user of the problem. 
	 */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> httpMessageNotReadable(HttpServletRequest req, Exception exception) {
        LOGGER.info("** Http Message Not Readable");
        HttpMessageNotReadableException ex = (HttpMessageNotReadableException) exception
        return new ResponseEntity<ErrorDto> (new ErrorDto(message: ex.message != null ? ex.message : "An error occurred."), HttpStatus.BAD_REQUEST)
    }
    
	/**
	 * This method is used to handle the ApiException. 
	 * 
	 * @param exception This is a reference to the exception for use during the method. 
	 * @return A ResponseEntity informing the end user of the problem. 
	 */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDto> apiException(HttpServletRequest req, Exception exception) {
        LOGGER.info("** API Exception");
        ApiException ex = (ApiException) exception
        return new ResponseEntity<ErrorDto> (new ErrorDto(message: ex.message != null ? ex.message : "An error occurred."), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
