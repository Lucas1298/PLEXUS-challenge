package com.project.hero.application.usecases;

import com.project.hero.application.adapter.SuperHeroCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperHeroEliminatorService {

    @Autowired
    private SuperHeroCommandService superHeroCommandService;

    public void delete(Integer id) {
        superHeroCommandService.delete(id);
    }


}
