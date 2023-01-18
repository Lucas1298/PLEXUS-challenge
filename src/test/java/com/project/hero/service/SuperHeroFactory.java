package com.project.hero.service;

import com.project.hero.application.dto.SuperHeroDTO;
import com.project.hero.application.usecases.SaveSuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperHeroFactory {

    @Autowired
    private SaveSuperHeroService saveSuperHeroService;

    @Autowired
    private SuperHeroRequestBuilder superHeroRequestBuilder;

    public SuperHeroDTO create(){
        var req = superHeroRequestBuilder.build();
        return saveSuperHeroService.save(req);
    }
}
