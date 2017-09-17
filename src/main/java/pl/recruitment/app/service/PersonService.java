package pl.recruitment.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.recruitment.app.dao.PersonDAO;
import pl.recruitment.app.dao.PersonDataManipulation;
import pl.recruitment.app.model.Person;

import java.util.List;

/**
 * Created by Kuba on 2017-09-17.
 */
@Component
public class PersonService {

    @Autowired
    private PersonDataManipulation personDAO;

    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personDAO.getAllPersons();
    }

    @Transactional
    public Person getPerson(int id) {return personDAO.getPerson(id);}

    @Transactional
    public void addPerson(Person newPerson) {personDAO.addPerson(newPerson);}

    @Transactional
    public void editPerson(Person modifiedPerson, int personId) {personDAO.editPerson(modifiedPerson, personId);}

    @Transactional
    public void deletePerson(Person personToDelete) {personDAO.deletePerson(personToDelete);}

    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
}
