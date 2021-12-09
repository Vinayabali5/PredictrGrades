package uk.ac.reigate.predictedgrades.api

import org.springframework.web.bind.annotation.PathVariable

interface ICrudApi<Obj, ID> {
    
    List<Obj> getAll()
    
    Obj getById(@PathVariable ID id)
    
    Obj create(Obj obj)
    
    Obj update(Obj obj)
    
    void delete(Obj obj)
    
}
