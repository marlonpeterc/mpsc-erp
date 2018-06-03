package io.mpsc.erp.cache.repository;

import io.mpsc.erp.cache.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private ReactiveRedisOperations<String, Person> operations;

    @Override
    public Mono<Person> add(Person person) {
        ReactiveValueOperations<String, Person> valueOperations = operations.opsForValue();
        Mono<Person> cachedPerson = valueOperations.get(PERSON_CACHE_KEY)
                .switchIfEmpty(valueOperations.get(PERSON_CACHE_KEY).flatMap(it ->
                        valueOperations.set(PERSON_CACHE_KEY, it, Duration.ofSeconds(60)).then(Mono.just(it))));
        return cachedPerson;
    }

    @Override
    public Mono<Person> findPersonBy(String lastname) {
        return null;
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public Flux<Person> findAll() {
        return null;
    }
}
