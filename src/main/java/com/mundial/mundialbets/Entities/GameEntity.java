package com.mundial.mundialbets.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDateTime gameDate;
    String result;
    String resultAfterOvertime;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private TeamEntity homeTeam;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private TeamEntity awayTeam;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<UserBetEntity> userBets;

    @OneToOne
    @JoinColumn(name="betApi_id")
    private BetApiEntity betApi;
}
