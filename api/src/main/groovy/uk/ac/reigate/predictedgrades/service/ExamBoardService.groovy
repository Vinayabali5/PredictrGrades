package uk.ac.reigate.predictedgrades.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import uk.ac.reigate.domain.predictedgrades.ExamBoard
import uk.ac.reigate.predictedgrades.exception.DataNotFoundException
import uk.ac.reigate.predictedgrades.repository.ExamBoardRepository

/**
 * This service is used to handle all requests for the ExamBoard data.
 * 
 * @author Michael Horgan
 *
 */
@Service
class ExamBoardService {
    
    @Autowired
    ExamBoardRepository examBoardRepository 

    /**
     * This method is used to retrieve a exam board from the database using the ID supplied.
     *  
     * @param examBoardId the ID of the exam board to retrieve.
     * @return the exam board matching the ID. 
     * @throws DataNotFoundException if the data is not found the this exception is thrown.
     */
    ExamBoard findById(Integer examBoardId) throws DataNotFoundException {
        return examBoardRepository.findById(examBoardId).orElseThrow{ -> 
            new DataNotFoundException(String.format("Cannot find a exam board with the ID: %d.", examBoardId)) 
        }
    }

    /**
     * This method is used to retrieve the full list of exam boards from the database. 
     * 
     * @return a list of all exam boards.
     */
    List<ExamBoard> findAll() {
        return examBoardRepository.findAll()
    }
    
}
