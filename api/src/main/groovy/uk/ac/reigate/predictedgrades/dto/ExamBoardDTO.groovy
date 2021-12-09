package uk.ac.reigate.predictedgrades.dto

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.domain.predictedgrades.ExamBoard

class ExamBoardDTO {
	
	@JsonProperty
    Integer id
	
	@JsonProperty
    String name
	
	@JsonProperty
    String identifier
	
	ExamBoardDTO(ExamBoard examBoard) {
		this.id = examBoard.id
		this.name = examBoard.name
		this.identifier = examBoard.identifier
	}
 
}
