package uk.ac.reigate.predictedgrades.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.domain.predictedgrades.QualificationType
import uk.ac.reigate.predictedgrades.dto.QualificationDTO
import uk.ac.reigate.predictedgrades.dto.QualificationTypeDTO
import uk.ac.reigate.predictedgrades.repository.QualificationTypeRepository

@RestController
class QualificationTypeApi {
    
    @Autowired
    QualificationTypeRepository qualificationTypeRepository  
    
    @GetMapping(value = '/api/qualification-types') 
    ResponseEntity<List<QualificationDTO>> getAll() {
        return new ResponseEntity(qualificationTypeRepository.findAll().collect { QualificationType it ->
            return new QualificationTypeDTO(it)
        }, HttpStatus.OK)
        
        
    }
}
