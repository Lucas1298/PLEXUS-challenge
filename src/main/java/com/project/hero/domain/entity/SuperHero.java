package com.project.hero.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Table
@Entity
@Audited
@Getter
@Setter
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    private String power;
}
