package com.mundial.mundialbets.Models;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GameModel {
    String homeTeamCode;
    String awayTeamCode;
    LocalDateTime dateTime;
}
