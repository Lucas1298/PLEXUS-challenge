package com.project.hero.application.usecases;

import com.project.hero.application.adapter.SuperHeroQueryService;
import com.project.hero.application.dto.SuperHeroDTO;
import com.project.hero.application.exceptions.SuperHeroNotFound;
import com.project.hero.application.mapper.SuperHeroMapper;
import com.project.hero.domain.entity.SuperHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class SuperHeroFinderService {

    @Autowired
    private SuperHeroQueryService superHeroQueryService;

    @Autowired
    private SuperHeroMapper superHeroMapper;

    public SuperHeroDTO findHero(Integer id) throws SuperHeroNotFound {
        Optional<SuperHero> getHero = superHeroQueryService.findSuperHero(id);

        return superHeroMapper.toDTO(getHero.orElseThrow(SuperHeroNotFound::new));
    }

}
