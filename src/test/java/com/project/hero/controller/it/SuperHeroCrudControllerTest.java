package com.project.hero.controller.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.project.hero.infrastructure.persistence.repository.SuperHeroRespository;
import com.project.hero.service.SuperHeroFactory;
import com.project.hero.service.SuperHeroRequestBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SuperHeroCrudControllerTest {

    @Autowired
    private SuperHeroRequestBuilder superHeroReqBuilder;

    @Autowired
    private SuperHeroRespository superHeroRepo;

    @Autowired
    private SuperHeroFactory superHeroFactory;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String path = "/v1/super-hero";

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void Given_AltaHeroe_When_ElUsuarioQuiereCargarUnHeroe_Then_HeroeCreado() throws Exception {
        var req = superHeroReqBuilder.build();

        this.mockMvc.perform(
                post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo(req.getName())))
                .andExpect(jsonPath("$.power", equalTo(req.getPower())));

        assertFalse(superHeroRepo.findByName(req.getName()).isEmpty());
    }


    @Test
    public void Given_UnHeroeCreado_When_SeBuscaPorId_Then_unHeroeEsEncontrado() throws Exception {
        var hero = superHeroFactory.create();
        var heroFindId = path.concat("/")
                .concat(String.valueOf(hero.getId()));

        this.mockMvc.perform(
                        get(heroFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(hero.getId())))
                .andExpect(jsonPath("$.name", equalTo(hero.getName())))
                .andExpect(jsonPath("$.power", equalTo(hero.getPower())));
    }

    @Test
    public void Given_NoHeroes_When_SeBuscaPorId_Then_NotFound() throws Exception {
        var superHeroFindId = path.concat("/")
                .concat(String.valueOf(new Faker().number().randomNumber()));

        this.mockMvc.perform(
                        get(superHeroFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void Given_UnHeroeCreado_When_SeBuscaActualizarElNombre_Then_unHeroeEsActualizado() throws Exception {
        var hero = superHeroFactory.create();

        var heroFindId = path.concat("/")
                .concat(String.valueOf(hero.getId()));

        hero.setName(new Faker().superhero().name());

        this.mockMvc.perform(
                        put(heroFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hero)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(hero.getId())))
                .andExpect(jsonPath("$.name", equalTo(hero.getName())))
                .andExpect(jsonPath("$.power", equalTo(hero.getPower())));
    }

    @Test
    public void Given_UnHeroeCreado_When_SeEliminaUnHeroe_Then_unHeroeEsEliminado() throws Exception {
        var hero = superHeroFactory.create();

        var heroFindId = path.concat("/")
                .concat(String.valueOf(hero.getId()));

        this.mockMvc.perform(
                        delete(heroFindId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertTrue(superHeroRepo.findById(hero.getId()).isEmpty());
    }


}
