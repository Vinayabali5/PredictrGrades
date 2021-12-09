package uk.ac.reigate.domain.predictedgrades

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

import uk.ac.reigate.domain.BaseEntityNoIdentity

@Entity
@Table(name = "exam_board", schema = 'predictedgrade')
class ExamBoard extends BaseEntityNoIdentity {

    @Column(name = "name")
    String name
    
    @Column(name = "board_identifier")
    String identifier

}
