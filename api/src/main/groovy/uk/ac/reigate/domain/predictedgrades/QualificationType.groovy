package uk.ac.reigate.domain.predictedgrades;

import javax.persistence.Entity
import javax.persistence.Table

import uk.ac.reigate.domain.BaseEntityNoIdentity

/**
 * This class is used to provide a data accessor object for the database. This object represents that 
 * data stored in the SQL table. 
 * 
 * @author Michael Horgan
 *
 */
@Entity
@Table(name = 'qualification_type', schema = 'predictedgrade')
class QualificationType extends BaseEntityNoIdentity {
    
    String type
    
}
