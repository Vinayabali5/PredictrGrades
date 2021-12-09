package uk.ac.reigate.predictedgrades.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import uk.ac.reigate.domain.predictedgrades.QualificationType

@Repository
public interface QualificationTypeRepository extends CrudRepository<QualificationType, Integer> {
}
