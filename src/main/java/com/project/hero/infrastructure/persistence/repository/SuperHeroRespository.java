package com.project.hero.infrastructure.persistence.repository;

import com.project.hero.application.adapter.SuperHeroCommandService;
import com.project.hero.domain.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuperHeroRespository extends JpaRepository<SuperHero, Integer>,JpaSpecificationExecutor<SuperHero>,SuperHeroCommandService {

    List<SuperHero> findByName(String name);

}
