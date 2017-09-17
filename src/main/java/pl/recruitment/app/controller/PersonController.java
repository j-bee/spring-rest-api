package pl.recruitment.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.recruitment.app.model.Person;
import pl.recruitment.app.service.PersonService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Kuba on 2017-09-17.
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    private static final Logger _log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Person> getPerson(@PathVariable("id") int personId) {

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
    @ResponseBody Person editPerson(@PathVariable("id") int personId, @RequestBody Person personToModify) {
        _log.debug("editing person: " + personToModify);
        personService.editPerson(personToModify, personId);
        _log.debug("After editing: " + personService.getAllPersons());
        return personToModify;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<Person>> getAllPersons() {

        List<Person> personList = personService.getAllPersons();
        return new ResponseEntity(personList, HttpStatus.OK);
    }

    //https://www.mkyong.com/spring-mvc/spring-rest-api-validation/
    @RequestMapping(method = RequestMethod.GET)
    public String getDefaultPage() {

        _log.debug("getDefaultPage...");
        return "index";
    }

}
