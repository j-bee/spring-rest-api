package pl.recruitment.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.recruitment.app.controller.PersonController;
import pl.recruitment.app.model.Person;
import pl.recruitment.app.service.PersonService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2017-09-17.
 */

//in real use cases this class, and the controller,
//should have robust validation logic
@Repository
public class PersonDAOSimpleVersion implements PersonDataManipulation {

    private static final Logger _log = LoggerFactory.getLogger(PersonDAO.class);
    private List<Person> persons;

    //mock use only, replace by DB
    public PersonDAOSimpleVersion() {
        persons = new ArrayList<Person>();
        /*Person p1 = new Person(1, "Jack", "1st Avenue", 39);
        Person p2 = new Person(2, "Mary", "Roosevelt Street", 28);
        Person p3 = new Person(3, "Robert", "10th Alley", 45);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);*/
    }

    public List<Person> getAllPersons() {
        return persons;
    }

    public Person getPerson(int id) {
        for(Person p : persons) {
            if(p.getId() == id) {
                _log.debug("Returning person: " +p);
                return p;
            }
        }
        _log.debug(String.format("No person with id: %d exists... returning null", id));

        //this can cause unhandled encoding/data type in web service communication
        return null;
    }

    public void addPerson(Person newPerson) {
        persons.add(newPerson);
    }

    //research topic: how to validate the incoming data
    //easily and efficiently? possibly in controller via "binding model/binding result"?
    //http://codetutr.com/2013/05/28/spring-mvc-form-validation/
    public void editPerson(Person modifiedPerson, int personId) {

        Person oldPerson = null;
        for(Person p : persons) {
            if(p.getId() == personId) {
                oldPerson = p;
                break;
            }
        }
        if(oldPerson == null) {
            _log.debug("No such person, cannot edit...");
        }
        else {
            oldPerson.setId(modifiedPerson.getId());
            oldPerson.setAddress(modifiedPerson.getAddress());
            oldPerson.setAge(modifiedPerson.getAge());
            oldPerson.setName(modifiedPerson.getName());
        }
    }

    public void deletePerson(Person personToDelete) {
        persons.remove(personToDelete);
    }
}

/*


package pl.recruitment.app.dao;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
        import org.springframework.stereotype.Repository;
        import pl.recruitment.app.controller.PersonController;
        import pl.recruitment.app.model.Person;

        import java.util.ArrayList;
        import java.util.List;

*/
/**
 * Created by Kuba on 2017-09-17.
 *//*


//in real use cases this class, and the controller,
//should have robust validation logic
@Repository
public class PersonDAO implements PersonDataManipulation {

    private static final Logger _log = LoggerFactory.getLogger(PersonDAO.class);
    private List<Person> persons;

    //mock use only, replace by DB
    public PersonDAO() {
        persons = new ArrayList<Person>();
        */
/*Person p1 = new Person(1, "Jack", "1st Avenue", 39);
        Person p2 = new Person(2, "Mary", "Roosevelt Street", 28);
        Person p3 = new Person(3, "Robert", "10th Alley", 45);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);*//*

    }

    public List<Person> getAllPersons() {
        return persons;
    }

    public Person getPerson(int id) {
        for(Person p : persons) {
            if(p.getId() == id) {
                _log.debug("Returning person: " +p);
                return p;
            }
        }
        _log.debug(String.format("No person with id: %d exists... returning null", id));

        //this can cause unhandled encoding/data type in web service communication
        return null;
    }

    public void addPerson(Person newPerson) {
        persons.add(newPerson);
    }

    //research topic: how to validate the incoming data
    //easily and efficiently? possibly in controller via "binding model/binding result"?
    //http://codetutr.com/2013/05/28/spring-mvc-form-validation/
    public void editPerson(Person modifiedPerson, int personId) {

        Person oldPerson = null;
        for(Person p : persons) {
            if(p.getId() == personId) {
                oldPerson = p;
                break;
            }
        }
        if(oldPerson == null) {
            _log.debug("No such person, cannot edit...");
        }
        else {
            oldPerson.setId(modifiedPerson.getId());
            oldPerson.setAddress(modifiedPerson.getAddress());
            oldPerson.setAge(modifiedPerson.getAge());
            oldPerson.setName(modifiedPerson.getName());
        }
    }

    public void deletePerson(Person personToDelete) {
        persons.remove(personToDelete);
    }
}
*/



        /*package pl.recruitment.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.recruitment.app.model.Person;
import pl.recruitment.app.service.PersonService;

import javax.validation.Valid;
import javax.xml.transform.Result;
/*

*/
/**
 * Created by Kuba on 2017-09-17.
 *//*

@Controller
@RequestMapping(value = "/person")
public class PersonController {

    private static final Logger _log = LoggerFactory.getLogger(pl.recruitment.app.controller.PersonController.class);

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Person> getPerson(@PathVariable("id") int personId) {

        _log.debug("getPerson(), ID: " + personId);
        Person person = personService.getPerson(personId);
        ResponseEntity<Person> responseEntity;
        if (person == null) {
            _log.debug("HttpStatus.NOT_FOUND...");
            responseEntity = new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        } else {
            _log.debug("Returning person: " + person);
            responseEntity = new ResponseEntity<Person>(person, HttpStatus.OK);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable("id") int personId) {
        _log.debug("Deleting employee:");
        Person person = personService.getPerson(personId);
        ResponseEntity<Person> responseEntity;

        if (person == null) {
            _log.debug("HttpStatus.NOT_FOUND...");
            HttpHeaders headers = new HttpHeaders();
            headers.add("info", "no such person in DB");
            responseEntity = new ResponseEntity(headers, HttpStatus.BAD_REQUEST);
        } else {
            _log.debug("Deleting person: " + person);
            personService.deletePerson(person);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        }

        _log.debug("After deleting: " + personService.getAllPersons());
        return responseEntity;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Person addPerson(@Valid @RequestBody Person personToAdd, Errors errors) {

        if(errors.hasErrors()) {
            _log.debug("Errors: " + errors.getAllErrors());
        }

        else {
            _log.debug("adding person: " + personToAdd);
            personService.addPerson(personToAdd);
            _log.debug("After adding: " + personService.getAllPersons());
        }

        return personToAdd;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    Person editPerson(@PathVariable("id") int personId, @RequestBody Person personToModify) {
        _log.debug("editing person: " + personToModify);
        personService.editPerson(personToModify, personId);
        _log.debug("After editing: " + personService.getAllPersons());
        return personToModify;
    }

    //https://www.mkyong.com/spring-mvc/spring-rest-api-validation/
    @RequestMapping(method = RequestMethod.GET)
    public String getDefaultPage() {

        _log.debug("getDefaultPage...");
        return "index";
    }

}
*/
