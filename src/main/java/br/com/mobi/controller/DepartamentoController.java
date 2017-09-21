package br.com.mobi.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.mobi.model.Departamento;
import br.com.mobi.service.DepartamentoService;

@Controller
public class DepartamentoController implements Serializable{

	private static final long serialVersionUID = -8238824354422827381L;
	
	@Autowired
	private DepartamentoService departamentoService;

	/**
	 * sava um departamento
	 * @param nome
	 * @return
	 */
	@RequestMapping(path = "/departamento/save", method = RequestMethod.POST)
	public String save(@Valid Departamento departamento, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "departamento-formulario";
		}
		departamentoService.save(departamento);
		return findAll(model);
	}
	
	/**
	 * busca todos os departamentos
	 * @param model
	 * @return
	 */
	@RequestMapping(path = "/departamento/findall", method = RequestMethod.GET)
	public String findAll(Model model){
		Iterable<Departamento> departamento = departamentoService.findAll();
		model.addAttribute("departamentos", departamento);
		model.addAttribute("departamentoSemEmpregado", departamentoService.countDepartamentsWithoutEmployee());
		return "departamento";
	}
	@RequestMapping(value = "/departamento/show", method = RequestMethod.GET)
	public String showFormEmpregado(Model model) {
		model.addAttribute("departamento", new Departamento());
		return "departamento-formulario";
	}
	/**
	 * busca um departamento pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/departamento/edit/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable long id, Model model){
		model.addAttribute("departamento", departamentoService.findById(id));
		
		return "departamento-formulario";
	}
	
	/**
	 * exclui um departamento pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/departamento/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable long id, Model model){
		departamentoService.delete(id);
		return findAll(model);
	}
}
