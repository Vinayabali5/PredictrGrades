package uk.ac.reigate.predictedgrades.api

import javax.transaction.Transactional

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.predictedgrades.dto.StudentQualificationDTO
import uk.ac.reigate.predictedgrades.service.StudentPredictedGradeService

/**
 * This class is used to provide a set of HTTP end point that are related to the operations 
 * performed on the StudentQualification data objects. 
 * 
 * @author Michael Horgan
 *
 */
@RestController
class StudentQualificationApi {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentQualificationApi.class);

    @Autowired
    StudentPredictedGradeService studentQualificationService  
    
    /**
     * This method is used to provide a HTTP end point for adding a new StudentQualification to the database.
     *  
     * @param studentQualification a DTO object that represents the StudentQualification to add.
     * @return a ResponseEntity with the return code 200 and a copy of the saved student qualification.
     */
    @PostMapping(path = '/api/student-qualifications')
    def addStudentQualification(@RequestBody(required = true) StudentQualificationDTO studentQualification) {
        return new ResponseEntity(studentQualificationService.createFromDto(studentQualification), HttpStatus.OK)
    }
    
    /**
     * This method is used to provide a HTTP end point for updating an existing StudentQualification in the database. 
     * 
     * @param studentQualification a DTO object that represents the StudentQualification to update. 
     * @return a ResponseEntity with the return code 200 and a copy of the saved student qualification.
     */
    @PutMapping(path = '/api/student-qualifications')
    def updateStudentQualification(@RequestBody(required = true) StudentQualificationDTO studentQualification) {
        return new ResponseEntity(studentQualificationService.updateFromDto(studentQualification), HttpStatus.OK)
    }
    
    /**
     * This method is used to provide a HTTP end point for deleting an existing StudentQualification from
     * the database. 
     * 
     * @param id the ID for the StudentQualification to delete. 
     * @return a ResponseEntity with the return code 204 - No Content when successful·
     */
    @Transactional
    @DeleteMapping(path = '/api/student-qualifications/{id}')
    def deleteStudentQualification(@PathVariable Integer id) {
        studentQualificationService.deleteById(id)
        return new ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
