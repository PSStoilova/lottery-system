package com.example.lottery.ballot;

import com.example.lottery.ballot.data.Ballot;
import com.example.lottery.ballot.data.BallotRepository;
import com.example.lottery.ballot.dto.BallotDto;
import com.example.lottery.ballot.dto.BallotRequest;
import com.example.lottery.users.data.UserRepository;
import com.example.lottery.users.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class BallotService {

    private final BallotRepository ballotRepository;
    private final UserRepository userRepository;


    public BallotService(BallotRepository ballotRepository, UserRepository userRepository) {
        this.ballotRepository = ballotRepository;
        this.userRepository = userRepository;
    }

    public BallotDto registerBallot(BallotRequest request) {
        Ballot ballot = new Ballot(
                userRepository.findByEmail(request.email()).orElseThrow(() -> new RuntimeException("User's email was not found."))
        );
        ballotRepository.save(ballot);
        return BallotDto.fromEntity(ballot);
    }
}
