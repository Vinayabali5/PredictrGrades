package uk.ac.reigate.predictedgrades.dto

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.domain.predictedgrades.QualificationType

class QualificationTypeDTO {
    
    @JsonProperty
    Integer id
    
    @JsonProperty
    String type
    
    QualificationTypeDTO(QualificationType qualificationType) {
        this.id = qualificationType.id
        this.type= qualificationType.type
    }
    
}
