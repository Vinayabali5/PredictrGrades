package uk.ac.reigate.predictedgrades.dto

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.domain.predictedgrades.Qualification

class QualificationDTO {
    
    @JsonProperty
    Integer id
    
    @JsonProperty
    Integer qualificationTypeId 
    
    @JsonProperty
    String title
    
    @JsonProperty
    String _fullTitle
    
    QualificationDTO(Qualification qualification) {
        this.id = qualification.id
        this.qualificationTypeId = qualification.type != null ? qualification.type.id : null
        this.title = qualification.title
        this._fullTitle = (qualification.type != null ? qualification.type.type + ' ' : '') + qualification.title
    }
    
    
}
