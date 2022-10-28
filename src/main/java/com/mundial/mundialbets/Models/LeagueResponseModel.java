package com.mundial.mundialbets.Models;

import com.mundial.mundialbets.Entities.LeagueEntity;
import com.mundial.mundialbets.Entities.UserEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class LeagueResponseModel {
    Long id;
    String name;
    boolean active;
    List<String> users;

    public void makeLeagueResponse(LeagueEntity leagueEntity) {
        this.users = new ArrayList<>();
        this.id = leagueEntity.getId();
        this.name = leagueEntity.getName();
        this.active = leagueEntity.isActive();
        for (UserEntity userEntity : leagueEntity.getUsers()) {
            this.users.add(userEntity.getId().toString());
        }
    }
}
