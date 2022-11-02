package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.TeamEntity;

public interface TeamRepositoryCustom{
    public TeamEntity getTeamByCountryCode(String code);
}