package br.com.mobi.controller;
import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mobi.model.Departamento;
import br.com.mobi.model.Empregado;
import br.com.mobi.service.DepartamentoService;
import br.com.mobi.service.EmpregadoService;
@Controller
public class EmpregadoController implements Serializable {

	private static final long serialVersionUID = 3085620051908382519L;

	@Autowired
	private EmpregadoService empregadoService;
	
	@Autowired
	private DepartamentoService departamentoService;

	@RequestMapping(value="/")
	public String index() {
		return "index";
	}
	/**
	 * sava um empregado com seu respectivo departamento
	 * @param nome
	 * @param idDepartamento
	 * @return
	 */
	@RequestMapping(value = "/empregado/save", method = RequestMethod.POST)
	public String save(@Valid Empregado empregado, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "empregado-formulario";
		}
		empregadoService.save(empregado);
		return findAll(model);
	}
	/**
	 * busca todos os empregados
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/empregado/findall", method = RequestMethod.GET)
	public String findAll(Model model){
		Iterable<Empregado> empregados = empregadoService.findAll();
		model.addAttribute("empregados", empregados);
		
		return "empregado";
	}
	@ModelAttribute("todosDepartamentos")
	public List<Departamento> todosDepartamentos(){
		return (List<Departamento>) departamentoService.findAll();
	}
	
	@RequestMapping(value = "/empregado/show", method = RequestMethod.GET)
	public String showFormEmpregado(Model model) {
		model.addAttribute("empregado", new Empregado());
		return "empregado-formulario";
	}
	/**
	 * busca um empregado pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/empregado/edit/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable long id, Model model){
		model.addAttribute("empregado", empregadoService.findById(id));
		
		return "empregado-formulario";
	}
	
	/**
	 * exclui um empregado pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/empregado/delete/{id}", method = RequestMethod.GET)
	public String delete(@RequestParam("id") long id, Model model){
		empregadoService.delete(id);
		return findAll(model);
	}
}
