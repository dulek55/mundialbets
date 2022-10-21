package com.mundial.mundialbets.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<UserBetEntity> usersBets;

    @ManyToMany(mappedBy = "users")
    List<LeagueEntity> leagues = new ArrayList<>();
}
