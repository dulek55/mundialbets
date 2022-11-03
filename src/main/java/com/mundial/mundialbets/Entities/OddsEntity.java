package com.mundial.mundialbets.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odds")
public class OddsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float homeTeamWin;
    private Float homeTeamWinOrDraw;
    private Float draw;
    private Float awayTeamWin;
    private Float awayTeamWinOrDraw;
    @OneToOne
    @JoinColumn(name="game_id")
    private GameEntity game;
}
