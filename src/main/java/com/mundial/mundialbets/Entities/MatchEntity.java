package com.mundial.mundialbets.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matches")
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private Set<UserBetEntity> userBets;
}
