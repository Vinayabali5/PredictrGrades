package uk.ac.reigate.predictedgrades.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import uk.ac.reigate.domain.predictedgrades.Student

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    
    Optional<Student> findByLinkId(String linkId)
    
}
