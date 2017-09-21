package br.com.mobi.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.mobi.model.Empregado;
import br.com.mobi.repository.EmpregadoRepository;

public class EmpregadoService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7747501188659035067L;
	@Autowired
	private EmpregadoRepository empregadoRepository;
	/**
	 * salva um empregado caso ele esteja com um departamento associado
	 * @param empregado
	 * @return
	 */
	public Empregado save(Empregado empregado) {
		if(empregado.getDepartamento() != null) {
			return 	this.empregadoRepository.save(empregado);
		}
		return null;
	}
	
	public Iterable<Empregado> findAll() {
		return this.empregadoRepository.findAll();
	}
	
	public Empregado findById(long id) {
		return this.findById(id);
	}
	
	public void delete(long id) {
		this.empregadoRepository.delete(id);
	}
}
