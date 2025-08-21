package com.example.lottery.integration;

import com.example.lottery.ballot.BallotService;
import com.example.lottery.ballot.dto.BallotDto;
import com.example.lottery.ballot.dto.BallotRequest;
import com.example.lottery.lottery.LotteryService;
import com.example.lottery.lottery.data.Lottery;
import com.example.lottery.lottery.data.LotteryRepository;
import com.example.lottery.lottery.dto.LotteryDto;
import com.example.lottery.users.UserService;
import com.example.lottery.users.dto.UserDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
public class IntegrationTest {
    @Autowired
    private LotteryRepository lotteryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private BallotService ballotService;

    @Disabled
    @Transactional
    @Test
    public void testLottery() {
        UserDto userDto1 = userService.addUser(new UserDto("petya1", "pet1", "petya1@gmail.com", "1"));
        UserDto userDto2 = userService.addUser(new UserDto("petya2", "pet2", "petya2@gmail.com", "2"));

        LotteryDto lotteryDto1 = lotteryService.createLottery(new LotteryDto("test-lottery1", null, null));
        LotteryDto lotteryDto2 = lotteryService.createLottery(new LotteryDto("test-lottery2", null, null));

        ballotService.registerBallot(new BallotRequest(userDto1.email(), lotteryDto2.name()));
        ballotService.registerBallot(new BallotRequest(userDto2.email(), lotteryDto1.name()));


        Lottery lottery1 = lotteryRepository.findByName(lotteryDto1.name()).orElseThrow();
        lottery1.setExpiresAt(Instant.now().minus(2, ChronoUnit.DAYS));
        lotteryRepository.save(lottery1);

        Lottery lottery2 = lotteryRepository.findByName(lotteryDto2.name()).orElseThrow();
        lottery2.setExpiresAt(Instant.now().minus(2, ChronoUnit.DAYS));
        lotteryRepository.save(lottery2);


        lotteryService.processWinningLotteries();

        List<LotteryDto> wonLotteries = lotteryService.fetchAll();

        LotteryDto lotteryWon1 = wonLotteries.stream()
                .filter(l -> l.name().equals(lotteryDto1.name()))
                .findFirst()
                .orElseThrow();

        LotteryDto lotteryWon2 = wonLotteries.stream()
                .filter(l -> l.name().equals(lotteryDto2.name()))
                .findFirst()
                .orElseThrow();

        Assertions.assertEquals(userDto2.name(), lotteryWon1.winner());
        Assertions.assertEquals(userDto1.name(), lotteryWon2.winner());
    }
}
