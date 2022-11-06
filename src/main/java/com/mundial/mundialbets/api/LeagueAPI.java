package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.LeagueEntity;
import com.mundial.mundialbets.Models.LeagueModel;
import com.mundial.mundialbets.Models.LeagueResponseModel;
import com.mundial.mundialbets.Models.UserScoreModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/leagues")
public interface LeagueAPI {
    @PutMapping( {"/edit", "/active"} )
    ResponseEntity<LeagueResponseModel> editLeague(@RequestBody LeagueModel leagueModel) throws Exception;

    @PostMapping
    ResponseEntity<LeagueEntity> addLeague(@RequestBody LeagueEntity leagueEntity);

    @PostMapping("/join")
    ResponseEntity<LeagueResponseModel> joinLeague(@RequestBody LeagueModel leagueModel) throws Exception;

    @DeleteMapping("/leave")
    ResponseEntity<String> leaveLeague(@RequestBody LeagueModel leagueModel) throws Exception;

    @GetMapping
    ResponseEntity<List<LeagueResponseModel>> getLeague();

    @GetMapping("/getScores")
    ResponseEntity<List<UserScoreModel>> getUsersScore(@RequestBody LeagueModel leagueModel) throws Exception;

    @GetMapping("/usersleagues")
    ResponseEntity<List<LeagueResponseModel>> getUsersLeagues(@RequestBody LeagueModel leagueModel) throws Exception;

    @GetMapping("/{id}")
    ResponseEntity<LeagueEntity> getLeagueById(@PathVariable Long id) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLeagueById(@PathVariable Long id) throws Exception;
}
