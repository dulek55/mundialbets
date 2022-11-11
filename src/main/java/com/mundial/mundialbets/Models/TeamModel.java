package com.mundial.mundialbets.Models;

import com.mundial.mundialbets.Entities.TeamEntity;
import lombok.Getter;

@Getter
public class TeamModel {
    Long id;
    String countryName;
    String countryCode;

    public void makeModel(TeamEntity teamEntity) {
        this.id = teamEntity.getId();
        this.countryName = teamEntity.getCountryName();
        this.countryCode = teamEntity.getCountryCode();
    }
}
