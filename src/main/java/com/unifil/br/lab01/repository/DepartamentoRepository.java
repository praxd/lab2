package com.unifil.br.lab01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifil.br.lab01.entity.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{
	
}
