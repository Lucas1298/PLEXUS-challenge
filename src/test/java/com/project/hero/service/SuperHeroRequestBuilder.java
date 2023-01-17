package com.project.hero.service;

import com.github.javafaker.Faker;
import com.project.hero.infrastructure.rest.request.SuperHeroeRequest;
import org.springframework.stereotype.Service;

@Service
public class SuperHeroRequestBuilder {

    private final Faker faker = new Faker();

    public SuperHeroeRequest build() {
        return SuperHeroeRequest.builder()
                .name(faker.superhero().name())
                .power(faker.superhero().power())
                .build();

    }
}
