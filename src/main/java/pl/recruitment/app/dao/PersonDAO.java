package pl.recruitment.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.recruitment.app.model.Person;
import pl.recruitment.app.service.MessageSenderService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kuba on 2017-09-17.
 */

//in real use cases this class, and the controller,
//should have robust validation logic
@Component
public class PersonDAO implements PersonDataManipulation {

    private static final String PERSON_CREATION_QUEUE_DEFAULT_MSG = "Person created: ";
    private static final Logger _log = LoggerFactory.getLogger(PersonDAO.class);

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MessageSenderService messageSenderService;

    public List<Person> getAllPersons() {
        return em.createQuery("SELECT p FROM Person p").getResultList();
    }

    public Person getPerson(int id) {
        return null;
    }

    public void addPerson(Person newPerson) {

        em.persist(newPerson);
        messageSenderService.sendMessage(PERSON_CREATION_QUEUE_DEFAULT_MSG + newPerson);
    }

    public void editPerson(Person modifiedPerson, int personId) {

    }

    public void deletePerson(Person personToDelete) {

    }
}
