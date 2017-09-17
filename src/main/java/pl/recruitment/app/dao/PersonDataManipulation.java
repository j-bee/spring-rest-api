package pl.recruitment.app.dao;

import pl.recruitment.app.model.Person;

import java.util.List;

/**
 * Created by Kuba on 2017-09-17.
 */
public interface PersonDataManipulation {

    public List<Person> getAllPersons();

    public Person getPerson(int id);

    public void addPerson(final Person newPerson);

    public void editPerson(final Person modifiedPerson, int personId);

    public void deletePerson(final Person personToDelete);
}
