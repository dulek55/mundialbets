package com.mundial.mundialbets.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String countryName;
    private String countryCode;

    @JsonBackReference(value = "homeTeam_game")
    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private Set<GameEntity> homeGames;

    @JsonBackReference(value = "awayTeam_game")
    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    private Set<GameEntity> awayGames;
}
