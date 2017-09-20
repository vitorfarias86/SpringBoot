package br.com.mobi.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mobi.model.Departamento;
import br.com.mobi.repository.DepartamentoRepository;

@Controller
public class DepartamentoController implements Serializable{

	private static final long serialVersionUID = -8238824354422827381L;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	/**
	 * sava um departamento
	 * @param nome
	 * @return
	 */
	@RequestMapping(path = "/departamento/save", method = RequestMethod.POST)
	public String save(@RequestParam("nome") String nome, Model model) {
		Departamento emp = new Departamento();
		emp.setNome(nome);
		departamentoRepository.save(emp);
		return findAll(model);
	}
	
	/**
	 * busca todos os departamentos
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/departamento/findall", method = RequestMethod.GET)
	public String findAll(Model model){
		Iterable<Departamento> departamento = departamentoRepository.findAll();
		model.addAttribute("departamento", departamento);
		
		return "departamento";
	}
	
	/**
	 * busca um departamento pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/departamento/findbyid", method = RequestMethod.GET)
	public String findById(@RequestParam("id") long id, Model model){
		model.addAttribute("departamento", departamentoRepository.findOne(id));
		
		return "departamento";
	}
	
	/**
	 * exclui um departamento pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/departamento/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") long id, Model model){
		departamentoRepository.delete(id);
		return findAll(model);
	}
}
