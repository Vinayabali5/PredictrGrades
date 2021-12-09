package uk.ac.reigate.domain.predictedgrades;

import java.time.LocalDate

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.persistence.Table

import org.hibernate.annotations.DynamicUpdate

import com.fasterxml.jackson.annotation.JsonManagedReference

import uk.ac.reigate.domain.BaseEntityNoIdentity

/**
 * This class is used to provide a data accessor object for the database. This object represents that 
 * data stored in the SQL table. 
 * 
 * @author Michael Horgan
 *
 */
@Entity
@Table(name = 'student', schema = 'predictedgrade')
@DynamicUpdate(true)
class Student extends BaseEntityNoIdentity {
    
    @Column(name = 'surname', updatable = false)
    String surname
    
    @Column(name = 'first_name', updatable = false)
    String firstName
    
    @Column(name = 'dob', updatable = false)
    LocalDate dateOfBirth
    
    @Column(name = 'link_id', unique = true, updatable = false)
    String linkId
   
    @OneToMany(mappedBy = 'student', fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<StudentPredictedGrade> qualifications
	
	@Column(name = 'career_aims')
	String careerAims
	
	@Column(name = 'work_voluntary_experience')
	String workVoluntaryExperience
	
	@Column(name = 'hobbies_interests_other_achievements')
	String hobbiesInterestsOtherAchievements
	
	@Column(name = 'favorite_subject')
	String favoriteSubject
	
	@Column(name = 'coursework_or_exams')
	String courseworkOrExams
	
	@Column(name = 'reasons_for_reigate_college')
	String reasonsForReigateCollege
	
	@Column(name = 'other_school_college_applied_for')
	String otherSchoolCollegeAppliedFor
	
	@Column(name = 'learning_support_needs_details')
	String learningSupportNeedsDetails
}
