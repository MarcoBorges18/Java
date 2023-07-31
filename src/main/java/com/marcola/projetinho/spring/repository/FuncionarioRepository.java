package com.marcola.projetinho.spring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.marcola.projetinho.spring.model.Funcionario;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, CrudRepository<Funcionario, Integer> {
    List<Funcionario> findByNomeLike(String nome);

    @Query("SELECT f FROM Funcionario f WHERE f.nome like %:nome% AND f.salario >= :salario AND f. = :data")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);

    @Query(nativeQuery = true, value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :date")
    List<Funcionario> findDataContratacaoMaior(LocalDate date);
}
