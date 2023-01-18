package com.project.hero.application.adapter;

import com.project.hero.domain.entity.SuperHero;

public interface SuperHeroCommandService {

    SuperHero save(SuperHero superHero);

    SuperHero update(SuperHero hero);

}
