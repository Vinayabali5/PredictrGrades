package uk.ac.reigate.predictedgrades.exception

/**
 * This exception is used as an exception template. Extend this to automatically add basic 
 * exception handling by the API.
 *  
 * @author Michael Horgan
 *
 */
class ApiException extends RuntimeException {
    
    String message
    
    ApiException() {
        this.message =  "Unspecified error occurred."
    }
    
    ApiException(String message) {
        this.message = message
    }
}
