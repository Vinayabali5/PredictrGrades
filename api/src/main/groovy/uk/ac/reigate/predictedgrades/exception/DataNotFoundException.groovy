package uk.ac.reigate.predictedgrades.exception

/**
 * This exception is used when a service or API call fails to find the data it is 
 * looking for. 
 * 
 * @author Michael Horgan
 *
 */
class DataNotFoundException extends ApiException {
    
    DataNotFoundException() {
        this.message =  "Data not found."
    }
    
    DataNotFoundException(String message) {
        this.message = message
    }
    
}
