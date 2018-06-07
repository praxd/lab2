package com.unifil.br.lab01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unifil.br.lab01.entity.People;
import com.unifil.br.lab01.repository.PeopleRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class PeopleDbController {
	
	@Autowired
	PeopleRepository peopleRepository;
	
	public PeopleDbController(PeopleRepository people) {
		this.peopleRepository = people;
	}
	
	@RequestMapping(value="/pessoaDb", method = RequestMethod.GET)
    public List<People> lista() {       
        return this.peopleRepository.findAll(); 
    }
	
	@RequestMapping(value="/pessoaDb/{id}", method = RequestMethod.GET)
    public People listaUm(@PathVariable long id) {       
        return this.peopleRepository.findOne(id); 
    }
	
	@PostMapping("/pessoaDb")
    public People create(@ModelAttribute("people") People people) {
        this.peopleRepository.save(people);
        return people;
        //return "redirect:/pessoaDb";
    }
	
	@PutMapping("/pessoaDb/{id}")
	public People update(@PathVariable long id, String name) {
		People temp = this.peopleRepository.findOne(id);
		if (temp != null) { 
			temp.setName(name);
			this.peopleRepository.save(temp);
		}else {
			System.out.println("Pessoa não existe");
		}
		return temp;
	}
	
	@DeleteMapping("/pessoaDb/{id}")
	public People delete(@PathVariable long id) {
		this.peopleRepository.delete(id);
		return this.peopleRepository.findOne(id);
	}
		
}

