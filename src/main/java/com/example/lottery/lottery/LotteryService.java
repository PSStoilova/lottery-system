package com.example.lottery.lottery;

import com.example.lottery.ballot.data.Ballot;
import com.example.lottery.ballot.data.BallotRepository;
import com.example.lottery.lottery.data.Lottery;
import com.example.lottery.lottery.data.LotteryRepository;
import com.example.lottery.lottery.dto.LotteryDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;
    private final BallotRepository ballotRepository;

    public LotteryService(LotteryRepository lotteryRepository, BallotRepository ballotRepository) {
        this.lotteryRepository = lotteryRepository;
        this.ballotRepository = ballotRepository;
    }

    public LotteryDto createLottery(LotteryDto lotteryDto) {
        Lottery createdLottery = lotteryRepository.save(lotteryDto.toEntity());
        return LotteryDto.fromEntity(createdLottery);
    }

    public List<LotteryDto> fetchAll() {
        return lotteryRepository.findAll()
                .stream()
                .map(LotteryDto::fromEntity)
                .toList();
    }

    public void processWinningLotteries() {
        List<Lottery> pendingLotteries = lotteryRepository.findByWinnerIsNullAndExpiresAtBefore(Instant.now());
        pendingLotteries.forEach(this::processLottery);

    }

    private void processLottery(Lottery lottery) {
        Ballot winningBallot = ballotRepository.findRandomBallotByLotteryId(lottery.getId());
        lottery.setWinner(winningBallot);
        lotteryRepository.save(lottery);
    }
}
