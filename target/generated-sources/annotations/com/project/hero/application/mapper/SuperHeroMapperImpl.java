package com.project.hero.application.mapper;

import com.project.hero.application.dto.SuperHeroDTO;
import com.project.hero.domain.entity.SuperHero;
import com.project.hero.infrastructure.rest.request.SuperHeroeRequest;
import com.project.hero.infrastructure.rest.response.SuperHeroResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-17T20:16:25-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class SuperHeroMapperImpl implements SuperHeroMapper {

    @Override
    public SuperHero toEntity(SuperHeroeRequest req) {
        if ( req == null ) {
            return null;
        }

        SuperHero superHero = new SuperHero();

        superHero.setName( req.getName() );
        superHero.setPower( req.getPower() );

        return superHero;
    }

    @Override
    public SuperHeroDTO toDTO(SuperHero superHero) {
        if ( superHero == null ) {
            return null;
        }

        SuperHeroDTO superHeroDTO = new SuperHeroDTO();

        superHeroDTO.setId( superHero.getId() );
        superHeroDTO.setName( superHero.getName() );
        superHeroDTO.setPower( superHero.getPower() );

        return superHeroDTO;
    }

    @Override
    public SuperHeroResponse toResponse(SuperHeroDTO hero) {
        if ( hero == null ) {
            return null;
        }

        SuperHeroResponse.SuperHeroResponseBuilder superHeroResponse = SuperHeroResponse.builder();

        superHeroResponse.id( hero.getId() );
        superHeroResponse.name( hero.getName() );
        superHeroResponse.power( hero.getPower() );

        return superHeroResponse.build();
    }
}
