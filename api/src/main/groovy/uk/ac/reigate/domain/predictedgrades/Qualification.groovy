package uk.ac.reigate.domain.predictedgrades;

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import uk.ac.reigate.domain.BaseEntity

/**
 * This class is used to provide a data accessor object for the database. This object represents that 
 * data stored in the SQL table. 
 * 
 * @author Michael Horgan
 *
 */
@Entity
@Table(name = 'qualification', schema = 'predictedgrade')
class Qualification extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = 'qualification_type_id', foreignKey = @ForeignKey(name = "FK_qualification__qualification_type"))
    QualificationType type
    
    @Column(name = 'title')
    String title
    
}
