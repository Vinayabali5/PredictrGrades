package uk.ac.reigate.predictedgrades.dto.error

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This object is used as a data transmission object to feedback error message to the front end.  
 * 
 * @author Michael Horgan
 *
 */
class ErrorDto {
    
    @JsonProperty
    String message
}
