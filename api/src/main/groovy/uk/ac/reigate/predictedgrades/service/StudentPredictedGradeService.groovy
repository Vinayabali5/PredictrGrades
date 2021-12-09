package uk.ac.reigate.predictedgrades.service

import javax.transaction.Transactional

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.domain.predictedgrades.StudentPredictedGrade
import uk.ac.reigate.predictedgrades.dto.StudentQualificationDTO
import uk.ac.reigate.predictedgrades.exception.DataNotFoundException
import uk.ac.reigate.predictedgrades.exception.InvalidDataSuppliedException
import uk.ac.reigate.predictedgrades.exception.StudentQualificationExistsException
import uk.ac.reigate.predictedgrades.repository.StudentPredictedGradeRepository

/**
 * This service is used to handle all requests for the StudentPredictedGrade data.
 * 
 * @author Michael Horgan
 *
 */
@Service
class StudentPredictedGradeService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentPredictedGradeService.class);

    @Autowired
    StudentPredictedGradeRepository studentQualificationRepository
    
    @Autowired
    StudentService studentService
    
    @Autowired
    QualificationService qualificationService
    
    @Autowired
    ExamBoardService examBoardService
    
    /**
     * This method is used to retrieve a student qualification using the ID supplied. 
     * 
     * @param studentQualificationId the student qualification ID to use for the search.
     * @return the student qualification entity that matches the search.
     */
    private StudentPredictedGrade findById(Integer studentQualificationId) {
        return studentQualificationRepository.findById(studentQualificationId).orElseThrow{ -> 
            new DataNotFoundException(String.format("Cannot find a student qualification with the ID: %d.", studentQualificationId)) 
        }
    }
    
    /**
     * This method is used to retrieve a student qualification from the student ID and the qualification ID.
     *  
     * @param studentId the student ID to use.
     * @param qualificationId the qualification ID to use.
     * @return the student qualification entity that matches the search. null if not found.
     */
    private StudentPredictedGrade findByStudentIdAndQualificationId(Integer studentId, Integer qualificationId) {
        return studentQualificationRepository.findByStudentIdAndQualificationId(studentId, qualificationId)
    }
    
    /**
     * This method is used to save a student qualification entity to the database. 
     * 
     * @param studentQualification the student qualification entity to save. 
     * @return the saved copy of the student qualification entity.
     */
    @Transactional
    private StudentPredictedGrade save(StudentPredictedGrade studentQualification) {
        return studentQualificationRepository.save(studentQualification)
    }
    
    /**
     * This method is used to delete a student qualification from the database.
     * 
     * @param studentQualificationId the ID for the student qualification entity.
     */
    @Transactional
    void deleteById(Integer studentQualificationId) {
        if (studentQualificationId == null) {
            // TODO: add exception
        } else {
            LOGGER.debug("Deleting StudentQualification with ID: $studentQualificationId")
            Optional<StudentPredictedGrade> qual = studentQualificationRepository.findById(studentQualificationId)
            if (qual.isPresent()) {
                studentQualificationRepository.deleteById(qual.value.id)
            } else {
                throw new DataNotFoundException("Cannot delete student qualification with ID: $studentQualificationId as this item does not exist.")
            }
    
        }
    }
    
    /**
     * This method is used to create a new student qualification based on a supplied
     * DTO object. 
     * 
     * @param dto the DTO object to use for the create. 
     * @return the saved student qualification.
     */
    StudentPredictedGrade createFromDto(StudentQualificationDTO dto) {
        if (dto == null || dto.studentId == null || dto.qualificationId == null) { 
            throw new InvalidDataSuppliedException("The supplied data does not match the required data for a successful update.")
        } else {
            LOGGER.debug("Attempting to create StudentQualification")
            StudentPredictedGrade studentQual = studentQualificationRepository.findByStudentIdAndQualificationId(dto.studentId, dto.qualificationId)
            if (studentQual != null) {
                throw new StudentQualificationExistsException("The student qualification already exists.")
            }
            StudentPredictedGrade newStudentQual = new StudentPredictedGrade(
                student: studentService.findById(dto.studentId),
                qualification: qualificationService.findById(dto.qualificationId),
                grade: dto.grade != null ? dto.grade.toUpperCase() : null,
                examBoard: dto.examBoardId != null ? examBoardService.findById(dto.examBoardId) : null
                )
            return save(newStudentQual)
        }
    }
    
    
    /**
     * This method is used to update a student qualification based on a supplied 
     * DTO object. 
     * 
     * @param dto the DTO object to use for the update. 
     * @return the saved and updated student qualification.
     */
    StudentPredictedGrade updateFromDto(StudentQualificationDTO dto) {
        if (dto == null || dto.id == null || dto.studentId == null || dto.qualificationId == null) { 
            throw new InvalidDataSuppliedException("The supplied data does not match the required data for a successful update.")
        } else {
            StudentPredictedGrade studentQual = findById(dto.id)
            if (studentQual == null) {
                new DataNotFoundException(String.format("Failed to update. Cannot find a student qualification with the ID: %d.", dto.id))
            }
            studentQual.qualification = qualificationService.findById(dto.qualificationId)
            studentQual.grade = dto.grade != null ? dto.grade.toUpperCase() : null
            studentQual.examBoard = dto.examBoardId != null ? examBoardService.findById(dto.examBoardId) : null
			LOGGER.info("Updating Student Qualification with ID: $studentQual.id")
            return save(studentQual)
        }
    }
}
