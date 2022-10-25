package com.mundial.mundialbets.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    LocalDate gameDate;
    String result;
    String resultAfterOvertime;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private TeamEntity homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private TeamEntity awayTeam;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<UserBetEntity> userBets;

    @OneToOne
    @JoinColumn(name="betApi_id")
    private BetApiEntity betApi;
}
