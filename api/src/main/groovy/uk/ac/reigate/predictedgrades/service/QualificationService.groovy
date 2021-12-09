package uk.ac.reigate.predictedgrades.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.domain.predictedgrades.Qualification
import uk.ac.reigate.predictedgrades.exception.DataNotFoundException
import uk.ac.reigate.predictedgrades.repository.QualificationRepository

/**
 * This service is used to handle all requests for the Qualification data.
 * 
 * @author michael
 *
 */
@Service
class QualificationService {
    
    @Autowired
    QualificationRepository qualificationRepository 

    /**
     * This method is used to retrieve a qualification from the database using the ID supplied.
     *  
     * @param qualificationId the ID of the qualification to retrieve.
     * @return the qualification matching the ID. 
     * @throws DataNotFoundException if the data is not found the this exception is thrown.
     */
    Qualification findById(Integer qualificationId) throws DataNotFoundException {
        return qualificationRepository.findById(qualificationId).orElseThrow{ -> 
            new DataNotFoundException(String.format("Cannot find a qualification with the ID: %d.", qualificationId)) 
        }
    }

    /**
     * This method is used to retrieve the full list of qualifications from the database. 
     * 
     * @return a list of all qualifications.
     */
    List<Qualification> findAll() {
        return qualificationRepository.findAll()
    }
    
}
