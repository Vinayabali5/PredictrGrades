package uk.ac.reigate.predictedgrades.system

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.multipart.MaxUploadSizeExceededException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import uk.ac.reigate.predictedgrades.dto.error.ErrorDto

@ControllerAdvice
class FileUploadExceptionHandler extends ResponseEntityExceptionHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger("Exception Handling - File Uploader");
    
	/**
	 * This method is used to handle the MaxUploadSizeExceededException. 
	 * 
	 * @param exception This is a reference to the exception for use during the method. 
	 * @return A ResponseEntity informing the end user of the problem. 
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ErrorDto> handleMaxSizeException(MaxUploadSizeExceededException exception) {
		LOGGER.error("File uploaded was too large")
		ErrorDto error = new ErrorDto(message: "The file you are trying to upload is too large.")
		ResponseEntity<ErrorDto> response = new ResponseEntity<ErrorDto> (error, HttpStatus.PAYLOAD_TOO_LARGE) 
		return response
	}
}
