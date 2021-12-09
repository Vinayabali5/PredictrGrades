package uk.ac.reigate.predictedgrades.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import uk.ac.reigate.domain.predictedgrades.StudentPredictedGrade

@Repository
public interface StudentPredictedGradeRepository extends CrudRepository<StudentPredictedGrade, Integer> {
    
    StudentPredictedGrade findByStudentIdAndQualificationId(Integer studentId, Integer qualificationId)
    
    @Modifying
    @Query("delete from StudentPredictedGrade spg where spg.id=:id")
    void  deleteById(@Param('id') Integer id)
}
