package com.marcola.projetinho.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.marcola.projetinho.spring.model.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>{
    
}
