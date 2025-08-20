package com.example.lottery.ballot.data;

import com.example.lottery.users.data.User;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;

@Entity
@Table(name = "ballots")
public class Ballot {
    @Id
    @GeneratedValue(generator = "uuid")
    @UuidGenerator
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "submission_date", nullable = false)
    private Instant submissionDate = Instant.now();

    @Column(name = "winning", nullable = false)
    private boolean winning = false;

    public Ballot() {
    }

    public Ballot(User user) {
        this.user = user;
        this.submissionDate = Instant.now();
        this.winning = false ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Instant submissionDate) {
        this.submissionDate = submissionDate;
    }

    public boolean isWinning() {
        return winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }
}

