package br.com.mobi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.mobi.model.Empregado;

public interface EmpregadoRepository extends CrudRepository<Empregado, Long> {
	List<Empregado> findAll();
}
