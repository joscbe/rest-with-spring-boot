package br.com.joscbe.services;

import br.com.joscbe.data.dto.PersonDTO;
import br.com.joscbe.exception.ResourceNotFoundException;
import br.com.joscbe.model.Person;
import br.com.joscbe.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.joscbe.mapper.ObjectMapper.parseListObjects;
import static br.com.joscbe.mapper.ObjectMapper.parseObject;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll() {
        logger.info("Finding all Person");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }



    public PersonDTO findById(Long id) {
        logger.info("Finding one Person");

        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        return parseObject(person, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Create one Person");

        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("updating one Person");

        Person entity = parseObject(this.findById(person.getId()), Person.class);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one Person");
        Person person = parseObject(this.findById(id), Person.class);

        repository.delete(person);
    }
}
