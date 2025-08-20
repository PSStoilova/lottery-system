package com.example.lottery.ballot.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotRepository extends JpaRepository<Ballot,String> {

}
