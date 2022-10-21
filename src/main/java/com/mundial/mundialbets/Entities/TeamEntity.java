package com.mundial.mundialbets.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String countryName;
    private String countryCode;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private Set<GameEntity> homeGames;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    private Set<GameEntity> awayGames;
}
