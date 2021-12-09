package uk.ac.reigate.predictedgrades.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.sun.istack.NotNull

/**
 * This class is used to provide a representation of a StudentQualification. This uses on the IDs for 
 * the linked components.
 * 
 * @author Michael Horgan
 *
 */
class StudentQualificationDTO {
    
    @JsonProperty
    Integer id
    
    @JsonProperty
    @NotNull
    Integer studentId
    
    @JsonProperty
    @NotNull
    Integer qualificationId
    
    @JsonProperty
    @NotNull
    String grade
    
    @JsonProperty
    Integer examBoardId
    
	@JsonProperty
	String mockGrade
	
}
