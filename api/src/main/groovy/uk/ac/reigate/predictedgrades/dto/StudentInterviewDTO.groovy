package uk.ac.reigate.predictedgrades.dto

import java.time.LocalDate

import javax.persistence.Column

import com.fasterxml.jackson.annotation.JsonProperty

import uk.ac.reigate.domain.predictedgrades.Student
import uk.ac.reigate.domain.predictedgrades.StudentPredictedGrade

class StudentInterviewDTO {

    @JsonProperty
    Integer id
    
    @JsonProperty
    String linkId
	
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
	
	StudentInterviewDTO() {}
    
    StudentInterviewDTO(Student student) {
        this.id = student.id
        this.linkId = student.linkId
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
