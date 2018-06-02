package io.mpsc.erp.rest.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/check")
public class CheckController {

    @GetMapping("/me")
    public Mono<ResponseEntity<String>> check() {
        return Mono.just(new ResponseEntity<>("Success!", HttpStatus.ACCEPTED));
    }


}
