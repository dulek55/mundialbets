package com.mundial.mundialbets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("")
    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    @GetMapping("/{id}")
    public Team getById(@PathVariable("id") int id) {
        return teamRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Team> teams) {
        return teamRepository.save(teams);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Team updatedTeam) {
        Team team = teamRepository.getById(id);
        if (team != null) {
            team.setName(updatedTeam.getName());
            teamRepository.update(team);

            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Team updatedTeam) {
        Team team = teamRepository.getById(id);
        if (team != null) {
            if(updatedTeam.getName() != null) team.setName(updatedTeam.getName());
            teamRepository.update(team);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return teamRepository.delete(id);
    }
}
