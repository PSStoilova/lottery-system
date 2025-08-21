package com.example.lottery.ballot;

import com.example.lottery.ballot.data.Ballot;
import com.example.lottery.ballot.data.BallotRepository;
import com.example.lottery.ballot.dto.BallotDto;
import com.example.lottery.ballot.dto.BallotRequest;
import com.example.lottery.lottery.data.Lottery;
import com.example.lottery.lottery.data.LotteryRepository;
import com.example.lottery.users.data.User;
import com.example.lottery.users.data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BallotService.class)
public class BallotServiceTest {
    private static final String EMAIL = "petya123@gmail.com";
    @MockitoBean
    private BallotRepository ballotRepository;
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private LotteryRepository lotteryRepository;

    @Autowired
    private BallotService ballotService;


    @Test
    public void testRegisterBallot() {
        String lotteryName = "lottery1";
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(new User("petya", "pet", EMAIL, "123")));
        when(lotteryRepository.findByName(lotteryName)).thenReturn(Optional.of(new Lottery(lotteryName, List.of(), Instant.now().plus(1, ChronoUnit.HOURS))));

        BallotDto ballotDto = ballotService.registerBallot(new BallotRequest(EMAIL, lotteryName));

        assertEquals(EMAIL, ballotDto.email());
        assertEquals(lotteryName, ballotDto.lotteryName());
        assertEquals("petya", ballotDto.username());
        verify(ballotRepository).save(any(Ballot.class));
    }

    @Test
    public void testFetchAllBallotsPerUser() {
        when(ballotRepository.findAllByUser_Email(EMAIL)).thenReturn(List.of(new Ballot(new User(), new Lottery())));
        List<BallotDto> ballots = ballotService.fetchAllBallotsPerUser(EMAIL);
        assertEquals(1, ballots.size());
    }

    @Test
    public void testFetchBallots() {
        when(ballotRepository.findAllByUser_EmailAndSubmissionDateBetween(eq(EMAIL), any(Instant.class), any(Instant.class)))
                .thenReturn(List.of(new Ballot(new User(), new Lottery())));
        List<BallotDto> ballots = ballotService.fetchBallots(EMAIL, LocalDate.now());
        assertEquals(1, ballots.size());
    }
}
