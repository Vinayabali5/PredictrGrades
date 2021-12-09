package uk.ac.reigate.predictedgrades.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.domain.predictedgrades.ExamBoard
import uk.ac.reigate.predictedgrades.dto.ExamBoardDTO
import uk.ac.reigate.predictedgrades.service.ExamBoardService

@RestController
class ExamBoardApi {
    
    @Autowired
    ExamBoardService examBoardService  
    
    /**
     * This method is used to provide a HTTP end-point that lists all the exam boards.
     *  
     * @return 
     */
    @GetMapping(value = '/api/exam-boards') 
    ResponseEntity<List<ExamBoardDTO>> getAll() {
        return new ResponseEntity(examBoardService.findAll().collect { ExamBoard it ->
            return new ExamBoardDTO(it)
        }, HttpStatus.OK)
    }
}
