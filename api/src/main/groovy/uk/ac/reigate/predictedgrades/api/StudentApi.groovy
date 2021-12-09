package uk.ac.reigate.predictedgrades.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import uk.ac.reigate.domain.predictedgrades.Student
import uk.ac.reigate.predictedgrades.dto.StudentDTO
import uk.ac.reigate.predictedgrades.dto.StudentInterviewDTO
import uk.ac.reigate.predictedgrades.exception.DataNotFoundException
import uk.ac.reigate.predictedgrades.service.StudentService

@RestController
class StudentApi {
    
    @Autowired
    StudentService studentService 
    
    @GetMapping(path = '/api/students')
    def getByLinkId(@RequestParam(name = 'LinkId', required = true) String linkId) {
        return new ResponseEntity(new StudentDTO(studentService.findByLinkId(linkId)), HttpStatus.OK)
    }
    
    @GetMapping(path = '/api/students/{studentId}')
    Student getById(@PathVariable Integer studentId) {
        return new ResponseEntity(new StudentDTO(studentService.findById(studentId)), HttpStatus.OK)
    }
	
	@PostMapping(path = '/api/students/{studentId}/pre-interview')
	ResponseEntity<StudentInterviewDTO> updatePreInterview(@PathVariable("studentId") Integer studentId,
            @RequestBody (required = true)StudentInterviewDTO studentInterviewDto) {
		if (studentInterviewDto.id && studentInterviewDto.linkId) {
			Student studentUpdate = studentService.updateInterview(studentInterviewDto)
		   return new ResponseEntity(new StudentInterviewDTO(studentUpdate), HttpStatus.CREATED)
        } else {
			throw new DataNotFoundException("Not Found")
		}
	}

    @PostMapping(path = '/api/students/{studentId}')
    Student update(Student student) {
        // TODO: to complete
        //return studentRepository.save(student)
    }
            
}
