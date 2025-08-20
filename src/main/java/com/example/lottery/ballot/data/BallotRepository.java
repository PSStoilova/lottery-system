package com.example.lottery.ballot.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, String> {
    List<Ballot> findAllByUser_Email(String email);

}
