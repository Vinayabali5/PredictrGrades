package uk.ac.reigate.predictedgrades.service

import javax.transaction.Transactional

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.domain.predictedgrades.Student
import uk.ac.reigate.predictedgrades.dto.StudentInterviewDTO
import uk.ac.reigate.predictedgrades.exception.DataNotFoundException
import uk.ac.reigate.predictedgrades.repository.StudentRepository

/**
 * This service is used to handle all requests for the Student data.
 * 
 * @author michael
 *
 */
@Service
class StudentService {
    
    @Autowired
    StudentRepository studentRepository
    
    /**
     * This method is used to retrieve a student entity from the database using the ID supplied. 
     * 
     * @param studentId the student ID to use for the search.
     * @return the student entity that matches the search.
     * @throws DataNotFoundException if the data is not found the this exception is thrown.
     */
    Student findById(Integer studentId) throws DataNotFoundException {
        return studentRepository.findById(studentId).orElseThrow{ 
            -> 
            new DataNotFoundException(String.format("Cannot find a student with the ID: %d", studentId)) 
        }
    }
    
    /**
     * This method is used to retrieve a student entity from the database using the linkId supplied.  
     * 
     * @param linkId the linkId to use for the search.
     * @return the student entity that matches the search. 
     * @throws DataNotFoundException if the data is not found the this exception is thrown.
     */
    Student findByLinkId(String linkId) throws DataNotFoundException {
        return studentRepository.findByLinkId(linkId).orElseThrow{ 
            -> 
            new DataNotFoundException(String.format("Student Record with LinkID: %s was not found.", linkId)) 
        }
    }
	
	/**
	 * This method is used to update the student interview details.
	 */
	Student updateInterview(StudentInterviewDTO studentInterviewDto) {
		Student student = findById(studentInterviewDto.id)
		if(student.id == studentInterviewDto.id && student.linkId == studentInterviewDto.linkId) {
			student.careerAims = studentInterviewDto.careerAims;
			student.workVoluntaryExperience = studentInterviewDto.workVoluntaryExperience;
			student.hobbiesInterestsOtherAchievements = studentInterviewDto.hobbiesInterestsOtherAchievements;
			student.favoriteSubject = studentInterviewDto.favoriteSubject;
			student.courseworkOrExams = studentInterviewDto.courseworkOrExams;
			student.reasonsForReigateCollege = studentInterviewDto.reasonsForReigateCollege;
			student.otherSchoolCollegeAppliedFor = studentInterviewDto.otherSchoolCollegeAppliedFor;
			student.learningSupportNeedsDetails = studentInterviewDto.learningSupportNeedsDetails;
			return save(student)
		} else {
			throw new DataNotFoundException("Not Found")
		}
	}
    
    /**
     * This method is used to save a student entity to the database. 
     * 
     * @param student the student entity to save.
     * @return the saved student entity.
     */
    @Transactional
    Student save(Student student) {
        return studentRepository.save(student)
    } 
    
}
