package com.project.hero.infrastructure.persistence.repository;

import com.project.hero.application.adapter.SuperHeroCommandService;
import com.project.hero.application.adapter.SuperHeroQueryService;
import com.project.hero.domain.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SuperHeroRespository extends JpaRepository<SuperHero, Integer>,JpaSpecificationExecutor<SuperHero>,SuperHeroCommandService, SuperHeroQueryService {

    default Optional<SuperHero> findSuperHero(Integer id) {
        return this.findById(id);
    }

    List<SuperHero> findByName(String name);

    default SuperHero update(SuperHero hero) {
        return ((CrudRepository<SuperHero, Integer>) this).save(hero);
    }

}
