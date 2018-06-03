package io.mpsc.erp.cache.repository;

import io.mpsc.erp.cache.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    String PERSON_CACHE_KEY = "Person";

    Mono<Person> add(Person person);

    Mono<Person> findPersonBy(String lastname);

    void delete(Person person);

    Flux<Person> findAll();

}
