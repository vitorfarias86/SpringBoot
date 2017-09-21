package br.com.mobi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.mobi.model.Departamento;

public interface DepartamentoRepository extends CrudRepository<Departamento, Long>{

	
	@Query("SELECT d.id from Empregado e RIGHT JOIN e.departamento as d WHERE e.departamento IS NULL")
	List<Long> findDepartmentWithoutEmployee();
 
}
