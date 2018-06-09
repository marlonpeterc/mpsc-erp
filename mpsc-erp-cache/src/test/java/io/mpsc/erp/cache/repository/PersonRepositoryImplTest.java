package io.mpsc.erp.cache.repository;

import io.mpsc.erp.cache.config.RedisConfig;
import io.mpsc.erp.cache.model.Person;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisConfig.class)
public class PersonRepositoryImplTest {

    // we only want to run this tests when redis is up an running
    @ClassRule
    public static RequiresRedisServer requiresServer = RequiresRedisServer.onLocalhost();

    @Autowired
    PersonRepository personRepository;

    @Test
    public void add() {
        Person p = new Person("Homer", "Simpson");
        StepVerifier.create(personRepository.add(p))
                .expectNext(true)
                .verifyComplete();

        Flux<String> person = personRepository.findPerson("Simpson");
        StepVerifier.create(person)
                .expectNext("{\"firstname\":\"Homer\",\"lastname\":\"Simpson\"}")
                .verifyComplete();

        Mono<Person> monoPerson = personRepository.findPersonBy("Simpson");
        StepVerifier.create(monoPerson)
                .expectNext(p)
                .verifyComplete();
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
    }
}