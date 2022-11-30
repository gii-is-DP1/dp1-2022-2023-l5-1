package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		Person person4 = new Person();
		Person person5 = new Person();

		person.setFirstName("Joaquín");
		person.setLastName("Arregui Díaz");
		person1.setFirstName("Francisco Antonio");
		person1.setLastName("Campos Campos");
		person2.setFirstName("Jesús");
		person2.setLastName("Cárdenas Conejo");
		person3.setFirstName("José Luis");
		person3.setLastName("Cobo Ariza");
		person4.setFirstName("Guillermo");
		person4.setLastName("Galeano de Paz");
		person5.setFirstName("Juan");
		person5.setLastName("Romero González");
		
		persons.add(person);
		persons.add(person2);
		persons.add(person3);
		persons.add(person4);
		persons.add(person5);

		model.put("persons", persons);
		model.put("title", "Samurai Sword");
		model.put("group", "L5-1");

	    return "welcome";
	  }
}
