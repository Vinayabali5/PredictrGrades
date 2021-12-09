package uk.ac.reigate.predictedgrades.dto

import java.time.LocalDate

import javax.persistence.Column

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.domain.predictedgrades.Student
import uk.ac.reigate.domain.predictedgrades.StudentPredictedGrade

class StudentDTO {

    @JsonProperty
    Integer id
    
    @JsonProperty
    String surname
    
    @JsonProperty
    String firstName
    
    @JsonProperty
    LocalDate dateOfBirth
    
    @JsonProperty
    String linkId
    
    @JsonProperty
    List<StudentPredictedGrade> qualifications
	
	@JsonProperty
	String careerAims
	
	@JsonProperty
	String workVoluntaryExperience
	
	@JsonProperty
	String hobbiesInterestsOtherAchievements
	
	@JsonProperty
	String favoriteSubject
	
	@JsonProperty
	String courseworkOrExams
	
	@JsonProperty
	String reasonsForReigateCollege
	
	@JsonProperty
	String otherSchoolCollegeAppliedFor
    
	@JsonProperty
	String learningSupportNeedsDetails
	
    StudentDTO(Student student) {
        this.id = student.id
        this.surname = student.surname
        this.firstName = student.firstName
        this.dateOfBirth = student.dateOfBirth
        this.linkId = student.linkId
        this.qualifications = student.qualifications
		this.careerAims = student.careerAims
		this.workVoluntaryExperience = student.workVoluntaryExperience
		this.hobbiesInterestsOtherAchievements = student.hobbiesInterestsOtherAchievements
		this.favoriteSubject = student.favoriteSubject
		this.courseworkOrExams = student.courseworkOrExams
		this.reasonsForReigateCollege = student.reasonsForReigateCollege
		this.otherSchoolCollegeAppliedFor = student.otherSchoolCollegeAppliedFor
		this.learningSupportNeedsDetails = student.learningSupportNeedsDetails
    }

}
