package com.project.hero.application.mapper;

import com.project.hero.application.dto.SuperHeroDTO;
import com.project.hero.domain.entity.SuperHero;
import com.project.hero.infrastructure.rest.request.SuperHeroeRequest;
import com.project.hero.infrastructure.rest.response.SuperHeroResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuperHeroMapper {

    SuperHero toEntity(SuperHeroeRequest req);

    SuperHeroDTO toDTO(SuperHero superHero);

    SuperHeroResponse toResponse(SuperHeroDTO hero);
}
