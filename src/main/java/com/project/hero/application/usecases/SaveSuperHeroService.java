package com.project.hero.application.usecases;

import com.project.hero.application.adapter.SuperHeroCommandService;
import com.project.hero.application.dto.SuperHeroDTO;
import com.project.hero.application.mapper.SuperHeroMapper;
import com.project.hero.infrastructure.rest.request.SuperHeroeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveSuperHeroService {

    @Autowired
    private SuperHeroCommandService superHeroCommandService;

    @Autowired
    private SuperHeroMapper superHeroMapper;

    public SuperHeroDTO save(SuperHeroeRequest req) {
        var superHero = superHeroCommandService.save(superHeroMapper.toEntity(req));
        return superHeroMapper.toDTO(superHero);
    }
}
