package com.project.hero.infrastructure.rest.controller;

import com.project.hero.application.dto.SuperHeroDTO;
import com.project.hero.application.mapper.SuperHeroMapper;
import com.project.hero.application.usecases.SaveSuperHeroService;
import com.project.hero.infrastructure.rest.request.SuperHeroeRequest;
import com.project.hero.infrastructure.rest.response.SuperHeroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/super-hero")
public class SuperHeroController {

    @Autowired
    private SaveSuperHeroService saveSuperHeroService;

    @Autowired
    private SuperHeroMapper superHeroMapper;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SuperHeroResponse createHero(@RequestBody SuperHeroeRequest req){
        var hero = saveSuperHeroService.save(req);
        return superHeroMapper.toResponse(hero);
    }
}
