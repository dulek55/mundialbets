package com.mundial.mundialbets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Team> getAll() {
        return jdbcTemplate.query("SELECT id, name FROM team",
                BeanPropertyRowMapper.newInstance(Team.class));
    }

    public Team getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name FROM team WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Team.class), id);
    }

    public int save(List<Team> teams) {
        teams.forEach(team -> jdbcTemplate
                .update("INSERT INTO team(name) VALUES(?)",
                        team.getName()
                ));
        return 1;
    }

    public int update(Team team) {
        return jdbcTemplate.update("UPDATE team SET name=? WHERE id=?",
                team.getName(), team.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM team WHERE id=?", id);
    }
}
