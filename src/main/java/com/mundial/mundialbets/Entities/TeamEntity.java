package com.mundial.mundialbets.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference(value = "homeTeam-game")
    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private Set<GameEntity> homeGames;

    @JsonManagedReference(value = "awayTeam-game")
    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    private Set<GameEntity> awayGames;
}
