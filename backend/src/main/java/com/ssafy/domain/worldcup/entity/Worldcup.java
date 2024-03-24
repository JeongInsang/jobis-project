package com.ssafy.domain.worldcup.entity;

import com.ssafy.domain.common.embeddable.Factor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worldcup")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Worldcup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worldcup_id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "img")
    private String img;

    @Embedded
    private Factor factor;

}
