package br.com.mobi.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.mobi.model.Empregado;

public interface EmpregadoRepository extends CrudRepository<Empregado, Long> {
	
}
