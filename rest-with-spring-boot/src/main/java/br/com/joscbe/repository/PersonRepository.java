package br.com.joscbe.repository;

import br.com.joscbe.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
