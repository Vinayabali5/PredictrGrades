package uk.ac.reigate.predictedgrades.exception


class StudentQualificationExistsException extends ApiException {

    StudentQualificationExistsException() {
        this.message =  "Student qualification already exists."
    }
    
    StudentQualificationExistsException(String message) {
        this.message = message
    }

}
