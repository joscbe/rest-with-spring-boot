package br.com.joscbe;

import br.com.joscbe.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all Person");
        var persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);

            persons.add(person);
        }

        return persons;
    }



    public Person findById(String id) {
        logger.info("Finding one Person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Debora");
        person.setLastName("Dantas");
        person.setAddres("Na casa do......");
        person.setGender("Fame");

        return person;
    }

    public Person create(Person person) {
        logger.info("Create one Person");
        return person;
    }

    public Person update(Person person) {
        logger.info("updating one Person");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting one Person");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Andressa " + i);
        person.setLastName("Bode");
        person.setAddres("Na nova Manaus");
        person.setGender("Fame");

        return person;
    }

}
