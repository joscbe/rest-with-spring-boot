package br.com.joscbe.services;

import br.com.joscbe.exception.ResourceNotFoundException;
import br.com.joscbe.model.Person;
import br.com.joscbe.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all Person");

        return repository.findAll();
    }



    public Person findById(Long id) {
        logger.info("Finding one Person");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public Person create(Person person) {
        logger.info("Create one Person");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("updating one Person");

        Person entity = this.findById(person.getId());

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person");
        Person person = this.findById(id);

        repository.delete(person);
    }
}
