package com.example.lottery.lottery.data;

import com.example.lottery.ballot.data.Ballot;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "lotteries")
public class Lottery {

    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "lottery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ballot> ballots;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt = Instant.now();

    public Lottery() {
    }

    public Lottery(String name, List<Ballot> ballots, Instant expiresAt) {
        this.name = name;
        this.ballots = ballots;
        this.expiresAt = expiresAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ballot> getBallots() {
        return ballots;
    }

    public void setBallots(List<Ballot> ballots) {
        this.ballots = ballots;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
