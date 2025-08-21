package com.example.lottery.ballot;

import com.example.lottery.ballot.data.Ballot;
import com.example.lottery.ballot.data.BallotRepository;
import com.example.lottery.ballot.dto.BallotDto;
import com.example.lottery.ballot.dto.BallotRequest;
import com.example.lottery.lottery.data.Lottery;
import com.example.lottery.lottery.data.LotteryRepository;
import com.example.lottery.users.data.User;
import com.example.lottery.users.data.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BallotService {

    private final BallotRepository ballotRepository;
    private final UserRepository userRepository;
    private final LotteryRepository lotteryRepository;


    public BallotService(BallotRepository ballotRepository, UserRepository userRepository, LotteryRepository lotteryRepository) {
        this.ballotRepository = ballotRepository;
        this.userRepository = userRepository;
        this.lotteryRepository = lotteryRepository;
    }

    public BallotDto registerBallot(BallotRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("User's email was not found."));
        Lottery lottery = lotteryRepository.findByName(request.lotteryName()).orElseThrow(() -> new RuntimeException("Lottery name was not found."));

        if (lottery.getExpiresAt().isBefore(Instant.now())) {
            throw new RuntimeException("This lottery has already expired.");
        }

        Ballot ballot = new Ballot(
                user, lottery
        );
        ballotRepository.save(ballot);
        return BallotDto.fromEntity(ballot);
    }

    public List<BallotDto> fetchAllBallotsPerUser(String email) {
        return ballotRepository
                .findAllByUser_Email(email)
                .stream()
                .map(BallotDto::fromEntity)
                .toList();
    }

    public List<BallotDto> fetchBallots(String email, LocalDate day) {
        Instant startDate = day.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant endDate = startDate.plus(1, ChronoUnit.DAYS);
        return ballotRepository.findAllByUser_EmailAndSubmissionDateBetween(email, startDate, endDate).stream()
                .map(BallotDto::fromEntity)
                .toList();
    }
}
