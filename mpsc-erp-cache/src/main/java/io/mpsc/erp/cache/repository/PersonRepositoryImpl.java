package io.mpsc.erp.cache.repository;

import io.mpsc.erp.cache.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.util.ByteUtils;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private ReactiveRedisOperations<String, Person> operations;

    @Override
    public Mono<Boolean> add(Person person) {
        ReactiveValueOperations<String, Person> valueOperations = operations.opsForValue();
        return valueOperations.set(person.getLastname(), person);
    }

    @Override
    public Flux<String> findPerson(String lastname) {
        return operations.execute(conn -> conn.stringCommands().get(ByteBuffer.wrap(lastname.getBytes())))
                .map(ByteUtils::getBytes)
                .map(String::new);
    }

    @Override
    public Mono<Person> findPersonBy(String lastname) {
        ReactiveValueOperations<String, Person> valueOperations = operations.opsForValue();
        Mono<Person> person = valueOperations.get(lastname);
        return person;
    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public Flux<Person> findAll() {
        return null;
    }
}
