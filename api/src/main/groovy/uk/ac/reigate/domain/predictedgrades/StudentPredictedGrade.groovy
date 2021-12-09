package uk.ac.reigate.domain.predictedgrades;

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

import com.fasterxml.jackson.annotation.JsonBackReference
import com.sun.istack.NotNull

import uk.ac.reigate.domain.BaseEntity

/**
 * This class is used to provide a data accessor object for the database. This object represents that 
 * data stored in the SQL table. 
 * 
 * @author Michael Horgan
 *
 */
@Entity
@Table(name = 'student_predicted_grade', schema = 'predictedgrade')
class StudentPredictedGrade extends BaseEntity {
    
    @OneToOne
    @JoinColumn(name = 'student_id', foreignKey = @ForeignKey(name = "FK_student_predicted_grade__student"))
    @NotNull
    @JsonBackReference
    Student student
    
    @OneToOne
    @JoinColumn(name = 'qualification_id', foreignKey = @ForeignKey(name = "FK_student_predicted_grade__qualification"))
    @NotNull
    Qualification qualification
    
    @Column(name = 'grade')
    @NotNull
    String grade
    
    @OneToOne
    @JoinColumn(name = "exam_board_id", foreignKey = @ForeignKey(name = "FK_student_predicted_grade__exam_board"))
    ExamBoard examBoard
    	
}
