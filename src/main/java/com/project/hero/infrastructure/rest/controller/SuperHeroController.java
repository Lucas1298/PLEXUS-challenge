package com.project.hero.infrastructure.rest.controller;

import com.project.hero.application.exceptions.SuperHeroNotFound;
import com.project.hero.application.mapper.SuperHeroMapper;
import com.project.hero.application.usecases.SaveSuperHeroService;
import com.project.hero.application.usecases.SuperHeroEliminatorService;
import com.project.hero.application.usecases.SuperHeroFinderService;
import com.project.hero.application.usecases.SuperHeroUpdaterService;
import com.project.hero.infrastructure.rest.request.SuperHeroeRequest;
import com.project.hero.infrastructure.rest.response.SuperHeroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/super-hero")
public class SuperHeroController {

    @Autowired
    private SaveSuperHeroService saveSuperHeroService;

    @Autowired
    private SuperHeroFinderService superHeroFinderService;

    @Autowired
    private SuperHeroMapper superHeroMapper;

    @Autowired
    private SuperHeroUpdaterService superHeroUpdaterService;

    @Autowired
    private SuperHeroEliminatorService superHeroEliminatorService;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SuperHeroResponse createHero(@RequestBody SuperHeroeRequest req){
        var hero = saveSuperHeroService.save(req);
        return superHeroMapper.toResponse(hero);
    }

    @GetMapping("/{id}")
    public SuperHeroResponse findHero(@PathVariable Integer id) throws SuperHeroNotFound {

        var hero = superHeroFinderService.findHero(id);

        return superHeroMapper.toResponse(hero);
    }

    @PutMapping(value = "/{id}",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SuperHeroResponse findHero(@RequestBody SuperHeroeRequest req, @PathVariable Integer id) throws SuperHeroNotFound {

        var hero = superHeroUpdaterService.update(req, id);

        return superHeroMapper.toResponse(hero);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteHero(@PathVariable Integer id){

        superHeroEliminatorService.delete(id);

    }

}
