package br.com.mobi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mobi.model.Departamento;
import br.com.mobi.repository.DepartamentoRepository;
@Service
public class DepartamentoService {
	@Autowired
	private DepartamentoRepository departamentoRepository;

	public Departamento save(Departamento departamento) {

		Departamento dept = this.departamentoRepository.save(departamento);

		return dept;
	}

	public Iterable<Departamento> findAll() {
		return this.departamentoRepository.findAll();
	}

	public Departamento findById(Long id) {

		Departamento dept = this.departamentoRepository.findOne(id);

		return dept;
	}
	/**
	 * deleta um departamento se o departamento nao tiver empregados
	 * assossiados a ele
	 * @param id
	 */
	public void delete(Long id) {
		
		if(departamentsWithoutEmployee().contains(id)) {
			
			this.departamentoRepository.delete(id);
		}
	}
	/**
	 * Lista com os Ids dos departamentos sem empregados
	 * @return
	 */
	public List<Long> departamentsWithoutEmployee(){
		return this.departamentoRepository.findDepartmentWithoutEmployee();
	}
	/**
	 * int com o total de departametos sem empregados
	 * @return
	 */
	public int countDepartamentsWithoutEmployee() {
		return this.departamentsWithoutEmployee().size();
	}
}
