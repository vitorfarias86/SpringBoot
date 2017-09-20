package br.com.mobi.controller;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.mobi.model.Empregado;
import br.com.mobi.repository.DepartamentoRepository;
import br.com.mobi.repository.EmpregadoRepository;
@Controller
public class EmpregadoController implements Serializable {

	private static final long serialVersionUID = 3085620051908382519L;

	@Autowired
	private EmpregadoRepository empregadoRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

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
	public String save(@RequestParam("nome") String nome, @RequestParam("id_departamento") long idDepartamento, Model model) {
		Empregado emp = new Empregado();
		emp.setNome(nome);
		emp.setDepartamento(departamentoRepository.findOne(idDepartamento));
		empregadoRepository.save(emp);
		return findAll(model);
	}
	/**
	 * busca todos os empregados
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/empregado/findall", method = RequestMethod.GET)
	public String findAll(Model model){
		Iterable<Empregado> empregados = empregadoRepository.findAll();
		model.addAttribute("empregados", empregados);
		model.addAttribute("empregado", new Empregado());
		model.addAttribute("departamentos", departamentoRepository.findAll());
		
		return "empregado";
	}
	/**
	 * busca um empregado pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/empregado/findbyid", method = RequestMethod.GET)
	public String findById(@RequestParam("id") long id, Model model){
		model.addAttribute("empregado", empregadoRepository.findOne(id));
		
		return "empregado";
	}
	
	/**
	 * exclui um empregado pelo id
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/empregado/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("id") long id, Model model){
		empregadoRepository.delete(id);
		return findAll(model);
	}
}
