package com.project.hero.application.usecases;

import com.project.hero.application.adapter.SuperHeroCommandService;
import com.project.hero.application.adapter.SuperHeroQueryService;
import com.project.hero.application.dto.SuperHeroDTO;
import com.project.hero.application.exceptions.SuperHeroNotFound;
import com.project.hero.application.mapper.SuperHeroMapper;
import com.project.hero.infrastructure.rest.request.SuperHeroeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SuperHeroUpdaterService {

    @Autowired
    private SuperHeroCommandService superHeroCommandService;

    @Autowired
    private SuperHeroQueryService superHeroQueryService;

    @Autowired
    private SuperHeroMapper superHeroMapper;

    public SuperHeroDTO update(SuperHeroeRequest heroReq, Integer id) throws SuperHeroNotFound {
        var oldSuperHeroOpt = superHeroQueryService.findSuperHero(id);

        var oldHero = oldSuperHeroOpt.orElseThrow(SuperHeroNotFound::new);

        var superHeroUpdated = superHeroMapper.updateEntity(oldHero, heroReq);

        superHeroUpdated = superHeroCommandService.update(superHeroUpdated);

        return superHeroMapper.toDTO(superHeroUpdated);

    }
}
