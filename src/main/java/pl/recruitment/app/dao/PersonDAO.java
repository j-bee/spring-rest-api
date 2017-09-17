package pl.recruitment.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.recruitment.app.controller.PersonController;
import pl.recruitment.app.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuba on 2017-09-17.
 */

//in real use cases this class, and the controller,
//should have robust validation logic
@Component
public class PersonDAO implements PersonDataManipulation {

    private static final Logger _log = LoggerFactory.getLogger(PersonDAO.class);
    @PersistenceContext
    private EntityManager em;

    public List<Person> getAllPersons() {
        return em.createQuery("SELECT p FROM Person p").getResultList();
    }

    public Person getPerson(int id) {
        return null;
    }

    public void addPerson(Person newPerson) {
        em.persist(newPerson);
    }

    public void editPerson(Person modifiedPerson, int personId) {

    }

    public void deletePerson(Person personToDelete) {

    }
}
