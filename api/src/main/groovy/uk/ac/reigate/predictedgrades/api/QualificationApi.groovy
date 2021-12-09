package uk.ac.reigate.predictedgrades.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.domain.predictedgrades.Qualification
import uk.ac.reigate.predictedgrades.dto.QualificationDTO
import uk.ac.reigate.predictedgrades.service.QualificationService

@RestController
class QualificationApi {
    
    @Autowired
    QualificationService qualificationService  
    
    /**
     * This method is used to provide a HTTP end-point that lists all the qualifications.
     *  
     * @return 
     */
    @GetMapping(value = '/api/qualifications') 
    ResponseEntity<List<QualificationDTO>> getAll() {
        return new ResponseEntity(qualificationService.findAll().collect { Qualification it ->
            return new QualificationDTO(it)
        }, HttpStatus.OK)
    }
}
