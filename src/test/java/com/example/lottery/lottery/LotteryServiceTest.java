package com.example.lottery.lottery;

import com.example.lottery.ballot.data.BallotRepository;
import com.example.lottery.lottery.data.Lottery;
import com.example.lottery.lottery.data.LotteryRepository;
import com.example.lottery.lottery.dto.LotteryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = LotteryService.class)
public class LotteryServiceTest {
    @MockitoBean
    private LotteryRepository lotteryRepository;
    @MockitoBean
    private BallotRepository ballotRepository;

    @Autowired
    private LotteryService lotteryService;

    @Test
    public void testCreateLottery() {
        String lotteryName = "lottery1";
        when(lotteryRepository.save(any())).thenReturn(new Lottery(lotteryName, List.of(), Instant.now()));
        LotteryDto lotteryDto = lotteryService.createLottery(new LotteryDto(lotteryName, null, null));

        assertEquals(lotteryName, lotteryDto.name());
    }

    @Test
    public void testFetchAll() {
        String lotteryName = "lottery1";
        when(lotteryRepository.findAll()).thenReturn(List.of(new Lottery(lotteryName, List.of(), Instant.now())));
        List<LotteryDto> lotteryDtos = lotteryService.fetchAll();

        assertEquals(1, lotteryDtos.size());
        assertEquals(lotteryName, lotteryDtos.getFirst().name());
    }

    @Test
    public void testProcessWinningLotteries() {
        when(lotteryRepository.findByWinnerIsNullAndExpiresAtBefore(any())).thenReturn(List.of(
                new Lottery("l1", List.of(), Instant.now()),
                new Lottery("l2", List.of(), Instant.now())
        ));
        lotteryService.processWinningLotteries();

        verify(ballotRepository, times(2)).findRandomBallotByLotteryId(any());
        verify(lotteryRepository, times(2)).save(any(Lottery.class));
    }
}
