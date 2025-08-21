package com.example.lottery.ballot.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, String> {
    List<Ballot> findAllByUser_EmailAndSubmissionDateBetween(
            String email,
            Instant startDate,
            Instant endDate
    );

    List<Ballot> findAllByUser_Email(String email);

    @Query(value = "SELECT * FROM ballots b WHERE b.lottery_id = :lotteryId ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Ballot findRandomBallotByLotteryId(@Param("lotteryId") String lotteryId);
}
