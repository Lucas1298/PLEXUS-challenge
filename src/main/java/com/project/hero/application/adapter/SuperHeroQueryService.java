package com.project.hero.application.adapter;

import com.project.hero.domain.entity.SuperHero;

import java.util.Optional;

public interface SuperHeroQueryService {

    Optional<SuperHero> findSuperHero(Integer id);

}
