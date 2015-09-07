package de.codenorm.certification.web.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/api")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        System.out.println("nameaa1 = " + name);
        System.out.println("dSDsdsaSDasd ");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}