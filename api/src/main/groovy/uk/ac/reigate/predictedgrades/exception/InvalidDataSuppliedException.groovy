package uk.ac.reigate.predictedgrades.exception

/**
 * This exception is used when the user supplies data to an API end point that
 * does not match the expected input structure.
 * 
 * @author Michael Horgan
 *
 */
class InvalidDataSuppliedException  extends ApiException {
    
    InvalidDataSuppliedException() {
        this.message =  "Invalid data supplied."
    }
    
    InvalidDataSuppliedException(String message) {
        this.message = message
    }
    
}
