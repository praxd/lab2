package com.unifil.br.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unifil.br.lab01.entity.People;
import com.unifil.br.lab01.repository.PeopleRepository;



@Controller
public class SiteController {
	
	public SiteController(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}
	
	@Autowired
	PeopleRepository peopleRepository;
	
	@GetMapping("/Teste")
	public String index(Map<String, Object> model) {
		List<String> listTecnica = new ArrayList();
		listTecnica.add("Java");
		listTecnica.add("Eclipse");
		listTecnica.add("Spring Boot");
		listTecnica.add("Mustache");
		listTecnica.add("Bootstrap");
		listTecnica.add("MySql");
		
		model.put("lista", listTecnica);
		model.put("developer", "Gabriel Praxedes");
		return "index";
	}
	
	@PostMapping("/pessoa")
	public String create(@ModelAttribute("pessoa") People people) {
		this.peopleRepository.save(people);
		System.out.println(people.getName());
		return "redirect:/pessoa/lista";		
	}
	
	@GetMapping("/pessoa")
	public String add(Map<String, Object> model) {
		People p = new People();
		
		model.put("pessoa", p);
		return "pessoa/new";
	}
	
	@GetMapping("/pessoa/lista")
	public String listaPessoa(Map<String, Object> model) {
		model.put("pessoas", this.peopleRepository.findAll());
		return "pessoa/index";
	}
	
	@RequestMapping(value="/pessoa/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Map<String, Object> model) {               
        
        model.put("pessoa", this.peopleRepository.findOne(id));
        return "pessoa/edit";
    }
	
	@PutMapping("/pessoa/{id}")
	public String update(@PathVariable long id, String name) {
		People temp = this.peopleRepository.findOne(id);
		if (temp != null) { 
			temp.setName(name);
			this.peopleRepository.save(temp);
		}else {
			System.out.println("Pessoa não existe");
		}
		return "redirect:/pessoa/lista";
	}
}
