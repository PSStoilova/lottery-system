package com.example.lottery.lottery.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, String> {
    Optional<Lottery> findByName(String name);

    List<Lottery> findByWinnerIsNullAndExpiresAtBefore(Instant expiresAt);
}
