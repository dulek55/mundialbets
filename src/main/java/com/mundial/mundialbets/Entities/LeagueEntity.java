package com.mundial.mundialbets.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leagues")
public class LeagueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active = true;

    @JsonIgnoreProperties
    @ManyToMany
    @JoinTable(name = "league_users")
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "league", cascade = CascadeType.ALL)
    private List<UserBetEntity> userBets;

    public void addUser(UserEntity userEntity) {
        this.users.add(userEntity);
    }

    public void removeUser(UserEntity userEntity) {
        this.users.remove(userEntity);
    }

    public boolean isUserInLeague(UserEntity userEntity) {
        return users.contains(userEntity);
    }
}



