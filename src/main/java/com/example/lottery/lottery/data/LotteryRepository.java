package com.example.lottery.lottery.data;

import com.example.lottery.users.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, String> {
    Optional<Lottery> findByName(String name);

}
